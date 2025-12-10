package com.channel2.mobile.ui.Chats.controllers;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.media3.common.C;
import com.channel2.mobile.ui.Chats.Helpers.RecordFirebaseMessage;
import com.channel2.mobile.ui.Chats.models.ChatItemType;
import com.channel2.mobile.ui.Chats.models.ChatItemsArray;
import com.channel2.mobile.ui.Chats.models.ChatMediaItem;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.Chats.models.ChatTopic;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.network.ApiService;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ChatManager {
    private static final String TAG = "WSManager";
    private static ChatManager instance;
    private ChildEventListener childEventListener;
    private ResponseHandler mainActivityHandler;
    private DatabaseReference myRef;
    private Query myRefQuery;
    public ArrayList<ChatTopic> chatTopics = new ArrayList<>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    public ChatItemsArray chatItemsArray = new ChatItemsArray();
    private HashMap<Integer, ArrayList<ChatReportItem>> queue = new HashMap<>();
    private HashMap<Integer, Boolean> chatItemsArrayLocked = new HashMap<>();
    private HashMap<Integer, Boolean> chatOnScreen = new HashMap<>();
    private HashMap<Integer, Boolean> isFetching = new HashMap<>();
    public HashMap<Integer, Boolean> hasMorePages = new HashMap<>();
    private HashMap<Integer, Integer> currentPage = new HashMap<>();
    private boolean firebase_enable = false;
    private int chat_tab_page_count = 100;
    private int chat_tab_max_page = 30;
    public HashMap<Integer, Integer> unreadMessagesCount = new HashMap<>();
    public HashMap<Integer, Integer> unreadMessagesCountForSession = new HashMap<>();
    public HashMap<Integer, ChatReportItem> unreadMessagesItem = new HashMap<>();
    public HashMap<Integer, Boolean> isUnreadMessagesItemVisible = new HashMap<>();
    private HashMap<Integer, Handler> timer = new HashMap<>();
    private HashMap<Integer, Runnable> timerRunnable = new HashMap<>();
    private int counter = 0;

    public interface ResponseHandler {
        void onFailure();

        void onSuccess(int i);

        void onSuccess(JSONObject jSONObject) throws JSONException;
    }

    private ChatManager() {
        init();
    }

    public static synchronized ChatManager getInstance() {
        if (instance == null) {
            instance = new ChatManager();
        }
        return instance;
    }

    private void init() {
        try {
            this.myRef = this.database.getReference(MainConfig.main.getCurrentParam("firebase_child"));
            this.firebase_enable = MainConfig.main.getCurrentParam("firebase_enable").contentEquals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            JSONObject chatTopics = MainConfig.main.getChatTopics();
            JSONArray jSONArrayOptJSONArray = chatTopics.optJSONArray("topics");
            if (chatTopics != null && chatTopics.length() > 0) {
                this.chat_tab_page_count = chatTopics.optInt("chat_tab_page_count", 100);
                this.chat_tab_max_page = chatTopics.optInt("chat_tab_max_page", 30);
            }
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                return;
            }
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                ChatTopic chatTopic = new ChatTopic(jSONArrayOptJSONArray.optJSONObject(i));
                this.chatTopics.add(chatTopic);
                final int topicId = chatTopic.getTopicId();
                this.queue.put(Integer.valueOf(topicId), new ArrayList<>());
                this.chatItemsArrayLocked.put(Integer.valueOf(topicId), true);
                this.chatOnScreen.put(Integer.valueOf(topicId), false);
                this.isFetching.put(Integer.valueOf(topicId), false);
                this.hasMorePages.put(Integer.valueOf(topicId), true);
                this.currentPage.put(Integer.valueOf(topicId), 1);
                this.unreadMessagesCount.put(Integer.valueOf(topicId), 0);
                this.unreadMessagesCountForSession.put(Integer.valueOf(topicId), 0);
                this.unreadMessagesItem.put(Integer.valueOf(topicId), null);
                this.isUnreadMessagesItemVisible.put(Integer.valueOf(topicId), false);
                this.timer.put(Integer.valueOf(topicId), null);
                this.timerRunnable.put(Integer.valueOf(topicId), new Runnable() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatManager$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$init$0(topicId);
                    }
                });
                this.chatItemsArray.init(chatTopic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchItems(int i, final int i2, Context context, final ApiService.ResponseHandler responseHandler) {
        Log.d("checkAppStart", "fetchItems: topicId = " + i2 + " | isFetching = " + this.isFetching.get(Integer.valueOf(i2)));
        if (this.isFetching.get(Integer.valueOf(i2)) == null || this.isFetching.get(Integer.valueOf(i2)).booleanValue()) {
            return;
        }
        this.isFetching.put(Integer.valueOf(i2), true);
        ApiService.fetchChatItems(context, i, i2, new ResponseHandler() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatManager.1
            @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
            public void onSuccess(int i3) {
            }

            @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
            public void onSuccess(JSONObject jSONObject) {
                int responseTopic = ChatManager.this.getResponseTopic(jSONObject);
                if (responseTopic > 0) {
                    if (!ChatManager.this.chatItemsArray.isInitialized.get(Integer.valueOf(responseTopic)).booleanValue()) {
                        ChatManager.this.chatItemsArray.initArray(jSONObject, responseTopic, true);
                    } else if (ChatManager.this.chatItemsArray.getChatItems(true, responseTopic).size() > 0) {
                        Log.d(ChatManager.TAG, "fetchItems onSuccess chatItemsArray.getChatItems().size()" + ChatManager.this.chatItemsArray.getChatItems(true, responseTopic).size());
                        ChatManager.this.chatItemsArray.addArrayToArray(jSONObject, responseTopic);
                    }
                    ChatManager.this.hasMorePages.put(Integer.valueOf(responseTopic), true);
                    ChatManager.this.isFetching.put(Integer.valueOf(responseTopic), false);
                }
                ApiService.ResponseHandler responseHandler2 = responseHandler;
                if (responseHandler2 != null) {
                    responseHandler2.onSuccess();
                }
            }

            @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
            public void onFailure() {
                Log.d("ChatManager", "fetchItems service failed");
                ChatManager.this.isFetching.put(Integer.valueOf(i2), false);
                ChatManager.this.hasMorePages.put(Integer.valueOf(i2), true);
                responseHandler.onSuccess();
            }
        });
    }

    public void fetchNewItemsFromService(int i, final ApiService.ResponseHandler responseHandler) {
        ApiService.fetchChatItems(null, 1, i, new ResponseHandler() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatManager.2
            @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
            public void onFailure() {
            }

            @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
            public void onSuccess(int i2) {
            }

            @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
            public void onSuccess(JSONObject jSONObject) {
                ChatReportItem chatReportItem;
                try {
                    int responseTopic = ChatManager.this.getResponseTopic(jSONObject);
                    if (responseTopic > -1) {
                        ChatManager.this.chatItemsArray.updateChatWithArray(jSONObject, responseTopic);
                        for (int i2 = 0; i2 < ChatManager.this.chatItemsArray.getChatItems(true, responseTopic).size() && ((chatReportItem = ChatManager.this.chatItemsArray.getChatItems(true, responseTopic).get(i2)) == null || (chatReportItem.messageState != ChatReportItem.MessageState.regular && chatReportItem.messageState != ChatReportItem.MessageState.shared)); i2++) {
                        }
                        responseHandler.onSuccess();
                        ChatManager.this.mainActivityHandler.onSuccess(responseTopic);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* renamed from: com.channel2.mobile.ui.Chats.controllers.ChatManager$3, reason: invalid class name */
    class AnonymousClass3 implements ResponseHandler {
        final /* synthetic */ Context val$context;
        final /* synthetic */ int val$topicId;

        @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
        public void onSuccess(int i) {
        }

        AnonymousClass3(int i, Context context) {
            this.val$topicId = i;
            this.val$context = context;
        }

        @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
        public void onSuccess(JSONObject jSONObject) throws JSONException {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("root");
            ChatReportItem chatReportItem = ChatManager.getInstance().chatItemsArray.getChatItems(true, this.val$topicId).get(ChatManager.instance.chatItemsArray.getChatItems(true, this.val$topicId).size() - 1);
            if (chatReportItem != null) {
                int messageID = (int) chatReportItem.getMessageID();
                int i = 0;
                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                    if (jSONArrayOptJSONArray.getInt(i2) == messageID) {
                        i = i2;
                    }
                }
                int i3 = ((i + 1) / ChatManager.this.chat_tab_page_count) + 1;
                if (i3 == ((Integer) ChatManager.this.currentPage.get(Integer.valueOf(this.val$topicId))).intValue()) {
                    i3++;
                }
                Log.d("ChatManager", "fetchItems: page = " + i3 + " currentPage " + ChatManager.this.currentPage + "indexInArray = " + i);
                if (i3 <= ((Integer) ChatManager.this.currentPage.get(Integer.valueOf(this.val$topicId))).intValue() || i3 >= ChatManager.this.chat_tab_max_page) {
                    return;
                }
                ChatManager.this.fetchItems(i3, this.val$topicId, this.val$context, new ApiService.ResponseHandler() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatManager$3$$ExternalSyntheticLambda0
                    @Override // com.channel2.mobile.ui.network.ApiService.ResponseHandler
                    public final void onSuccess() {
                        this.f$0.lambda$onSuccess$0();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSuccess$0() {
            ChatManager.this.startFirebaseListener();
        }

        @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
        public void onFailure() {
            Log.d("ChatManager", "fetchMoreItems service failed");
            ChatManager.this.hasMorePages.put(Integer.valueOf(this.val$topicId), true);
        }
    }

    public void fetchMoreItems(int i, Context context) {
        ApiService.getChatIndexes(context, i, new AnonymousClass3(i, context));
    }

    public void fetchItemByID(long j, int i, final ResponseHandler responseHandler) {
        ApiService.getChatItemById(null, j, i, new ResponseHandler() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatManager.4
            @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
            public void onSuccess(int i2) {
            }

            @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
            public void onSuccess(JSONObject jSONObject) throws JSONException {
                if (jSONObject.has("messageID")) {
                    responseHandler.onSuccess(jSONObject);
                } else {
                    responseHandler.onFailure();
                }
            }

            @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
            public void onFailure() {
                responseHandler.onFailure();
            }
        });
    }

    public int getResponseTopic(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("root");
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0 || (jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(0)) == null) {
                return 1;
            }
            return new ChatReportItem(jSONObjectOptJSONObject).getTopicID();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        r0.setMedias(r0.getChatMedia(r4));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.channel2.mobile.ui.Chats.models.ChatReportItem splitMessageByAutoId(org.json.JSONObject r4, long r5) {
        /*
            r3 = this;
            com.channel2.mobile.ui.Chats.models.ChatReportItem r0 = new com.channel2.mobile.ui.Chats.models.ChatReportItem     // Catch: java.lang.Exception -> L2e
            r0.<init>(r4)     // Catch: java.lang.Exception -> L2e
            r1 = 0
            int r4 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r4 == 0) goto L2d
            r4 = 0
        Lc:
            java.util.ArrayList r1 = r0.getChatMedias()     // Catch: java.lang.Exception -> L2e
            int r1 = r1.size()     // Catch: java.lang.Exception -> L2e
            if (r4 >= r1) goto L2d
            com.channel2.mobile.ui.Chats.models.ChatMediaItem r1 = r0.getChatMedia(r4)     // Catch: java.lang.Exception -> L2e
            long r1 = r1.getAutoId()     // Catch: java.lang.Exception -> L2e
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 != 0) goto L2a
            com.channel2.mobile.ui.Chats.models.ChatMediaItem r4 = r0.getChatMedia(r4)     // Catch: java.lang.Exception -> L2e
            r0.setMedias(r4)     // Catch: java.lang.Exception -> L2e
            goto L2d
        L2a:
            int r4 = r4 + 1
            goto Lc
        L2d:
            return r0
        L2e:
            r4 = move-exception
            r4.printStackTrace()
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.channel2.mobile.ui.Chats.controllers.ChatManager.splitMessageByAutoId(org.json.JSONObject, long):com.channel2.mobile.ui.Chats.models.ChatReportItem");
    }

    public void startFirebaseListener() {
        if (this.firebase_enable && this.childEventListener == null) {
            Query queryStartAt = this.myRef.orderByChild("updatedDate/time").startAt(this.chatItemsArray.getNewestItem().getUpdatedDate() + 1);
            this.myRefQuery = queryStartAt;
            this.childEventListener = queryStartAt.addChildEventListener(new ChildEventListener() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatManager.5
                @Override // com.google.firebase.database.ChildEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                @Override // com.google.firebase.database.ChildEventListener
                public void onChildAdded(DataSnapshot dataSnapshot, String str) {
                    Log.d(ChatManager.TAG, "onChildAdded");
                    ChatManager.this.recordFirebaseMessage();
                    ChatReportItem chatReportItem = new ChatReportItem(dataSnapshot);
                    chatReportItem.setFromFirebase(true);
                    Log.d("updateChatWithArray", "onChildAdded chatItem: " + chatReportItem.getMessageContent() + "firstItem.getUpdatedDate: " + chatReportItem.getUpdatedDate());
                    if (ChatManager.this.chatItemsArray == null || ChatManager.this.chatItemsArray.masterChatItemsArray == null || ChatManager.this.chatItemsArray.isChatItemExsists(chatReportItem)) {
                        return;
                    }
                    ChatManager.this.chatItemsArray.masterChatItemsArray.add(0, chatReportItem);
                    ArrayList<ChatReportItem> arrayListSplitMessage = ChatManager.this.splitMessage(chatReportItem);
                    if (arrayListSplitMessage != null) {
                        Log.d(ChatManager.TAG, "splittedItemsArray " + arrayListSplitMessage.size());
                        for (int i = 0; i < arrayListSplitMessage.size(); i++) {
                            ChatManager.this.handleNewFirebaseMessage(arrayListSplitMessage.get(i));
                        }
                        return;
                    }
                    ChatManager.this.handleNewFirebaseMessage(chatReportItem);
                }

                @Override // com.google.firebase.database.ChildEventListener
                public void onChildMoved(DataSnapshot dataSnapshot, String str) {
                    Log.d(ChatManager.TAG, "onChildMoved key");
                }

                @Override // com.google.firebase.database.ChildEventListener
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Log.d(ChatManager.TAG, "onChildRemoved key");
                }

                @Override // com.google.firebase.database.ChildEventListener
                public void onChildChanged(DataSnapshot dataSnapshot, String str) {
                    Log.d(ChatManager.TAG, "onChildChanged key");
                }
            });
        }
    }

    public void stopListener() {
        ChildEventListener childEventListener;
        Query query = this.myRefQuery;
        if (query == null || (childEventListener = this.childEventListener) == null) {
            return;
        }
        query.removeEventListener(childEventListener);
        this.myRefQuery = null;
        this.childEventListener = null;
    }

    public ArrayList<ChatReportItem> splitMessage(ChatReportItem chatReportItem) {
        boolean z;
        ArrayList<ChatReportItem> arrayList = new ArrayList<>();
        if ((chatReportItem.getChatItemType() != ChatItemType.mediaAndTextReply && chatReportItem.getChatItemType() != ChatItemType.mediaAndText && chatReportItem.getChatItemType() != ChatItemType.mediaVideo && chatReportItem.getChatItemType() != ChatItemType.mediaX1 && chatReportItem.getChatItemType() != ChatItemType.mediaX2 && chatReportItem.getChatItemType() != ChatItemType.gallery && chatReportItem.getChatItemType() != ChatItemType.collage) || chatReportItem.getChatMedias() == null) {
            return null;
        }
        if (chatReportItem.getChatMedias().size() == 2) {
            z = true;
            break;
        }
        if (chatReportItem.getChatMedias().size() > 2) {
            Iterator<ChatMediaItem> it = chatReportItem.getChatMedias().iterator();
            while (it.hasNext()) {
                ChatMediaItem next = it.next();
                if (next.getMediaContent() != null && next.getMediaContent().length() > 0) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (!z) {
            return null;
        }
        for (int i = 0; i < chatReportItem.getChatMedias().size(); i++) {
            ChatMediaItem chatMediaItem = chatReportItem.getChatMedias().get(i);
            ChatReportItem chatReportItem2 = new ChatReportItem(chatReportItem);
            chatReportItem2.setSplitted(true);
            chatReportItem2.setMedias(chatMediaItem);
            chatReportItem2.setChatItemType();
            arrayList.add(chatReportItem2);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewFirebaseMessage(ChatReportItem chatReportItem) {
        int topicID = chatReportItem.getTopicID();
        if (this.chatOnScreen.get(Integer.valueOf(topicID)) != null) {
            if (this.chatOnScreen.get(Integer.valueOf(topicID)).booleanValue()) {
                chatReportItem.setTyping(true);
            } else {
                chatReportItem.setTyping(false);
            }
            if (!this.chatItemsArrayLocked.get(Integer.valueOf(topicID)).booleanValue()) {
                Log.d("queue", "chatItemsArrayLocked = false");
                this.chatItemsArrayLocked.put(Integer.valueOf(topicID), true);
            }
            this.queue.get(Integer.valueOf(topicID)).add(chatReportItem);
            if (this.timer.get(Integer.valueOf(topicID)) == null) {
                startTimer(topicID);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateChat, reason: merged with bridge method [inline-methods] */
    public void lambda$init$0(int i) {
        Log.d("queue", "update chat");
        if (this.queue.get(Integer.valueOf(i)).size() > 0) {
            Log.d("queue", "queue.size() > 0");
            this.chatItemsArrayLocked.put(Integer.valueOf(i), true);
            this.chatItemsArray.insertChatItem(this.queue.get(Integer.valueOf(i)).get(0), true);
            this.queue.get(Integer.valueOf(i)).remove(0);
            if (this.queue.get(Integer.valueOf(i)).size() > 0) {
                startTimer(i);
                return;
            } else {
                this.timer.put(Integer.valueOf(i), null);
                this.chatItemsArrayLocked.put(Integer.valueOf(i), false);
                return;
            }
        }
        Log.d("queue", "queue.size() == 0");
        this.chatItemsArrayLocked.put(Integer.valueOf(i), false);
        this.timer.put(Integer.valueOf(i), null);
    }

    private void startTimer(int i) {
        if (this.timer.get(Integer.valueOf(i)) != null) {
            this.timer.get(Integer.valueOf(i)).removeCallbacks(this.timerRunnable.get(Integer.valueOf(i)));
        }
        this.timer.put(Integer.valueOf(i), new Handler(Looper.getMainLooper()));
        this.timer.get(Integer.valueOf(i)).postDelayed(this.timerRunnable.get(Integer.valueOf(i)), C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordFirebaseMessage() {
        if (MainConfig.main.getCurrentSource("recordFirebaseMessage") != null) {
            new RecordFirebaseMessage(null, MainConfig.main.getCurrentSource("recordFirebaseMessage"), new RecordFirebaseMessage.ResponseHandler() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatManager.6
                @Override // com.channel2.mobile.ui.Chats.Helpers.RecordFirebaseMessage.ResponseHandler
                public void onFailure() {
                }

                @Override // com.channel2.mobile.ui.Chats.Helpers.RecordFirebaseMessage.ResponseHandler
                public void onSuccess(String str) {
                }
            });
        }
    }

    public void setChatOnScreen(boolean z, int i) {
        this.chatOnScreen.put(Integer.valueOf(i), Boolean.valueOf(z));
    }

    public boolean isChatOnScreen(int i) {
        if (this.chatOnScreen.get(Integer.valueOf(i)) != null) {
            return this.chatOnScreen.get(Integer.valueOf(i)).booleanValue();
        }
        return false;
    }

    public String getDateString(long j) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j);
        if (calendar2.get(5) == calendar.get(5)) {
            return "היום";
        }
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("he_IL"));
        return simpleDateFormat.format(date);
    }

    public boolean isSameDay(long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar2.setTimeInMillis(j2);
        return calendar.get(5) == calendar2.get(5);
    }

    public void appEnterBackground() {
        this.counter = 0;
        stopListener();
    }

    public void appEnterForeground(ResponseHandler responseHandler) {
        this.mainActivityHandler = responseHandler;
        startFirebaseListener();
    }

    public int getTopicPositionById(int i) {
        for (int i2 = 0; i2 < this.chatTopics.size(); i2++) {
            if (this.chatTopics.get(i2).getTopicId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public void resetApp() {
        for (int i = 0; i < this.chatTopics.size(); i++) {
            this.chatItemsArray.resetTopics(this.chatTopics.get(i));
        }
        instance = null;
    }
}
