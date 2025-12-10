package com.channel2.mobile.ui.Chats.models;

import android.os.Build;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.channel2.mobile.ui.Chats.controllers.ChatManager;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.ToLongFunction;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ChatItemsArray {
    public ArrayList<ChatReportItem> masterChatItemsArray;
    private HashMap<Integer, ArrayList<ChatReportItem>> lobbyChatItems = new HashMap<>();
    private HashMap<Integer, ArrayList<ChatReportItem>> tabFragmentChatItems = new HashMap<>();
    private MutableLiveData<ChatReportItem> newItemObservation = new MutableLiveData<>();
    public HashMap<Integer, Boolean> isInitialized = new HashMap<>();

    public void init(ChatTopic chatTopic) {
        this.lobbyChatItems.put(Integer.valueOf(chatTopic.getTopicId()), new ArrayList<>(chatTopic.getChat_lobby_teaser_count()));
        this.tabFragmentChatItems.put(Integer.valueOf(chatTopic.getTopicId()), new ArrayList<>());
        this.isInitialized.put(Integer.valueOf(chatTopic.getTopicId()), false);
    }

    public void initArray(JSONObject jSONObject, int i, Boolean bool) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("root");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            return;
        }
        if (this.masterChatItemsArray == null) {
            this.masterChatItemsArray = new ArrayList<>();
        }
        for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
            try {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                if (jSONObjectOptJSONObject != null) {
                    ChatReportItem chatReportItem = new ChatReportItem(jSONObjectOptJSONObject);
                    chatReportItem.setFirst(true);
                    chatReportItem.setTyping(false);
                    if (bool.booleanValue()) {
                        addChatItem(chatReportItem, true);
                    } else {
                        insertChatItem(chatReportItem, true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.isInitialized.put(Integer.valueOf(i), true);
    }

    public void addArrayToArray(JSONObject jSONObject, int i) {
        try {
            ChatReportItem chatReportItem = ChatManager.getInstance().chatItemsArray.getChatItems(true, i).get(ChatManager.getInstance().chatItemsArray.getChatItems(true, i).size() - 1);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("root");
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                return;
            }
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                if (jSONObjectOptJSONObject != null) {
                    ChatReportItem chatReportItem2 = new ChatReportItem(jSONObjectOptJSONObject);
                    if (chatReportItem2.getUpdatedDate() < chatReportItem.getUpdatedDate()) {
                        addChatItem(chatReportItem2, false);
                    }
                }
            }
            this.newItemObservation.setValue(this.lobbyChatItems.get(Integer.valueOf(i)).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateChatWithArray(JSONObject jSONObject, int i) {
        ChatReportItem chatReportItem = null;
        for (int i2 = 0; i2 < ChatManager.getInstance().chatItemsArray.getChatItems(true, i).size() && ((chatReportItem = ChatManager.getInstance().chatItemsArray.getChatItems(true, i).get(i2)) == null || (chatReportItem.messageState != ChatReportItem.MessageState.regular && chatReportItem.messageState != ChatReportItem.MessageState.shared)); i2++) {
        }
        if (chatReportItem != null) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("root");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                for (int i3 = 0; i3 < jSONArrayOptJSONArray.length(); i3++) {
                    try {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i3);
                        if (jSONObjectOptJSONObject != null) {
                            ChatReportItem chatReportItem2 = new ChatReportItem(jSONObjectOptJSONObject);
                            if (chatReportItem2.getUpdatedDate() > chatReportItem.getUpdatedDate()) {
                                arrayList.add(chatReportItem2);
                                Log.d("updateChatWithArray", "chatReportItem.getUpdatedDate: " + chatReportItem2.getUpdatedDate() + " updatedChatItem.getUpdatedDate: " + chatReportItem.getUpdatedDate());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            Log.d("updateChatWithArray", "newItems.size: " + arrayList.size());
            if (arrayList.size() > 0) {
                ChatManager.getInstance().unreadMessagesCountForSession.put(Integer.valueOf(i), Integer.valueOf(ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(i)).intValue() + arrayList.size()));
                if (ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(i)) == null) {
                    ChatManager.getInstance().unreadMessagesItem.put(Integer.valueOf(i), new ChatReportItem());
                    ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(i)).setChatItemType(ChatItemType.unreadMessages);
                    ChatManager.getInstance().chatItemsArray.getChatItems(true, i).add(0, ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(i)));
                }
                if (ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(i)).intValue() == 1) {
                    ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(i)).setMessageContent("הודעה אחת שלא נקראה");
                } else {
                    ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(i)).setMessageContent(ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(i)) + " הודעות שלא נקראו");
                }
                ChatManager.getInstance().isUnreadMessagesItemVisible.put(Integer.valueOf(i), true);
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ChatReportItem chatReportItem3 = (ChatReportItem) arrayList.get(size);
                    chatReportItem3.setTyping(false);
                    this.masterChatItemsArray.add(chatReportItem3);
                    insertChatItem(chatReportItem3, false);
                    Log.d("updateChatWithArray", chatReportItem3.getMessageContent());
                }
            }
        }
    }

    public ArrayList<ChatReportItem> getChatItems(boolean z, int i) {
        return (z ? this.tabFragmentChatItems : this.lobbyChatItems).get(Integer.valueOf(i));
    }

    public void addChatItem(ChatReportItem chatReportItem, boolean z) {
        this.masterChatItemsArray.add(chatReportItem);
        ArrayList<ChatReportItem> arrayListSplitMessage = ChatManager.getInstance().splitMessage(chatReportItem);
        if (arrayListSplitMessage != null) {
            for (int size = arrayListSplitMessage.size() - 1; size >= 0; size--) {
                addChatItemAfterSplit(arrayListSplitMessage.get(size));
            }
        } else {
            addChatItemAfterSplit(chatReportItem);
        }
        if (z) {
            this.newItemObservation.setValue(chatReportItem);
        }
    }

    public void addChatItemAfterSplit(ChatReportItem chatReportItem) {
        try {
            int topicID = chatReportItem.getTopicID();
            Iterator<ChatTopic> it = ChatManager.getInstance().chatTopics.iterator();
            int chat_lobby_teaser_count = 5;
            while (it.hasNext()) {
                ChatTopic next = it.next();
                if (next.getTopicId() == topicID) {
                    chat_lobby_teaser_count = next.getChat_lobby_teaser_count();
                }
            }
            if (this.lobbyChatItems.get(Integer.valueOf(topicID)).size() > 0 && this.lobbyChatItems.get(Integer.valueOf(topicID)).size() < chat_lobby_teaser_count && this.lobbyChatItems.get(Integer.valueOf(topicID)).get(this.lobbyChatItems.get(Integer.valueOf(topicID)).size() - 1).getName().equals(chatReportItem.getName())) {
                this.lobbyChatItems.get(Integer.valueOf(topicID)).get(this.lobbyChatItems.get(Integer.valueOf(topicID)).size() - 1).setFirst(false);
            }
            if (this.lobbyChatItems.get(Integer.valueOf(topicID)).size() < chat_lobby_teaser_count) {
                ChatReportItem chatReportItem2 = new ChatReportItem();
                chatReportItem2.copy(chatReportItem);
                this.lobbyChatItems.get(Integer.valueOf(topicID)).add(chatReportItem2);
            }
            addToTabFragmentChatItems(chatReportItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertChatItem(ChatReportItem chatReportItem, boolean z) {
        try {
            int topicID = chatReportItem.getTopicID();
            Iterator<ChatTopic> it = ChatManager.getInstance().chatTopics.iterator();
            int chat_lobby_teaser_count = 5;
            while (it.hasNext()) {
                ChatTopic next = it.next();
                if (next.getTopicId() == topicID) {
                    chat_lobby_teaser_count = next.getChat_lobby_teaser_count();
                }
            }
            ChatReportItem chatReportItem2 = new ChatReportItem();
            chatReportItem2.copy(chatReportItem);
            if (this.lobbyChatItems.get(Integer.valueOf(topicID)).size() == chat_lobby_teaser_count) {
                this.lobbyChatItems.get(Integer.valueOf(topicID)).remove(this.lobbyChatItems.get(Integer.valueOf(topicID)).size() - 1);
                this.lobbyChatItems.get(Integer.valueOf(topicID)).get(this.lobbyChatItems.get(Integer.valueOf(topicID)).size() - 1).setFirst(true);
            }
            if (this.lobbyChatItems.get(Integer.valueOf(topicID)).size() > 0 && this.lobbyChatItems.get(Integer.valueOf(topicID)).get(0).getName().equals(chatReportItem2.getName())) {
                chatReportItem2.setFirst(false);
            } else {
                chatReportItem2.setFirst(true);
            }
            this.lobbyChatItems.get(Integer.valueOf(topicID)).get(0).setTyping(false);
            this.lobbyChatItems.get(Integer.valueOf(topicID)).add(0, chatReportItem2);
            insertToTabFragmentChatItems(0, chatReportItem, z);
            if (z) {
                this.newItemObservation.setValue(chatReportItem2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToTabFragmentChatItems(ChatReportItem chatReportItem) {
        try {
            int topicID = chatReportItem.getTopicID();
            if (this.tabFragmentChatItems.get(Integer.valueOf(topicID)).size() > 0 && !ChatManager.getInstance().isSameDay(chatReportItem.getUpdatedDate(), this.tabFragmentChatItems.get(Integer.valueOf(topicID)).get(this.tabFragmentChatItems.get(Integer.valueOf(topicID)).size() - 1).getUpdatedDate())) {
                ChatReportItem chatReportItem2 = new ChatReportItem();
                chatReportItem2.setChatItemType(ChatItemType.sectionHeader);
                chatReportItem2.setMessageContent(ChatManager.getInstance().getDateString(this.tabFragmentChatItems.get(Integer.valueOf(topicID)).get(this.tabFragmentChatItems.get(Integer.valueOf(topicID)).size() - 1).getUpdatedDate()));
                this.tabFragmentChatItems.get(Integer.valueOf(topicID)).add(chatReportItem2);
            } else if (this.tabFragmentChatItems.get(Integer.valueOf(topicID)).size() > 0 && this.tabFragmentChatItems.get(Integer.valueOf(topicID)).get(this.tabFragmentChatItems.get(Integer.valueOf(topicID)).size() - 1).getName().equals(chatReportItem.getName())) {
                Log.d("AddToTabFragment", "last name" + this.tabFragmentChatItems.get(Integer.valueOf(topicID)).get(this.tabFragmentChatItems.get(Integer.valueOf(topicID)).size() - 1).getName() + "curren name" + chatReportItem.getName());
                this.tabFragmentChatItems.get(Integer.valueOf(topicID)).get(this.tabFragmentChatItems.get(Integer.valueOf(topicID)).size() + (-1)).setFirst(false);
            }
            this.tabFragmentChatItems.get(Integer.valueOf(topicID)).add(chatReportItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertToTabFragmentChatItems(int i, ChatReportItem chatReportItem, boolean z) {
        try {
            int topicID = chatReportItem.getTopicID();
            if (this.tabFragmentChatItems.get(Integer.valueOf(topicID)).size() > 0 && chatReportItem.getChatItemType() != ChatItemType.unreadMessages && this.tabFragmentChatItems.get(Integer.valueOf(topicID)).get(0).getChatItemType() != ChatItemType.unreadMessages && !ChatManager.getInstance().isSameDay(chatReportItem.getUpdatedDate(), this.tabFragmentChatItems.get(Integer.valueOf(topicID)).get(0).getUpdatedDate())) {
                ChatReportItem chatReportItem2 = new ChatReportItem();
                chatReportItem2.setChatItemType(ChatItemType.sectionHeader);
                chatReportItem2.setMessageContent(ChatManager.getInstance().getDateString(chatReportItem.getUpdatedDate()));
                this.tabFragmentChatItems.get(Integer.valueOf(topicID)).add(i, chatReportItem2);
                chatReportItem.setFirst(true);
                if (z) {
                    this.newItemObservation.setValue(chatReportItem);
                }
            } else if (this.tabFragmentChatItems.get(Integer.valueOf(topicID)).size() > 0 && this.tabFragmentChatItems.get(Integer.valueOf(topicID)).get(0).getName().equals(chatReportItem.getName())) {
                chatReportItem.setFirst(false);
            }
            this.tabFragmentChatItems.get(Integer.valueOf(topicID)).add(i, chatReportItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeItemFromTab(ChatReportItem chatReportItem) {
        try {
            int topicID = chatReportItem.getTopicID();
            getMessageIndexByItem(chatReportItem, this.tabFragmentChatItems.get(Integer.valueOf(topicID)));
            this.tabFragmentChatItems.get(Integer.valueOf(topicID)).remove(chatReportItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMessageIndexByItem(ChatReportItem chatReportItem, ArrayList<ChatReportItem> arrayList) {
        Iterator<ChatReportItem> it = arrayList.iterator();
        while (it.hasNext()) {
            ChatReportItem next = it.next();
            if (next.getMessageID() == chatReportItem.getMessageID()) {
                return arrayList.indexOf(next);
            }
        }
        return -1;
    }

    public ChatReportItem getChatByMessageId(long j) {
        try {
            Iterator<ChatReportItem> it = this.masterChatItemsArray.iterator();
            while (it.hasNext()) {
                ChatReportItem next = it.next();
                if (next.getMessageID() == j) {
                    return next;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTabMessageIndexById(long j, int i) {
        int indexByTopicId = -1;
        try {
            if (i > 0) {
                indexByTopicId = getIndexByTopicId(j, i);
            } else {
                Iterator<ChatTopic> it = ChatManager.getInstance().chatTopics.iterator();
                while (true) {
                    if (it.hasNext()) {
                        int indexByTopicId2 = getIndexByTopicId(j, it.next().getTopicId());
                        if (indexByTopicId2 > -1) {
                            indexByTopicId = indexByTopicId2;
                            break;
                        }
                    }
                }
            }
            break;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indexByTopicId;
    }

    private int getIndexByTopicId(long j, int i) {
        try {
            Iterator<ChatReportItem> it = this.tabFragmentChatItems.get(Integer.valueOf(i)).iterator();
            while (it.hasNext()) {
                ChatReportItem next = it.next();
                if (next.getMessageID() == j) {
                    return this.tabFragmentChatItems.get(Integer.valueOf(i)).indexOf(next);
                }
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public MutableLiveData<ChatReportItem> getNewItemObservation() {
        return this.newItemObservation;
    }

    public boolean isChatItemExsists(ChatReportItem chatReportItem) {
        for (int i = 0; i < this.masterChatItemsArray.size(); i++) {
            if (this.masterChatItemsArray.get(i).getMessageID() == chatReportItem.getMessageID()) {
                return true;
            }
        }
        return false;
    }

    public ChatReportItem getOriginalChatItem(ChatReportItem chatReportItem) {
        Iterator<ChatReportItem> it = this.masterChatItemsArray.iterator();
        while (it.hasNext()) {
            ChatReportItem next = it.next();
            if (next.getMessageID() == chatReportItem.getMessageID()) {
                return next;
            }
        }
        return chatReportItem;
    }

    public ChatReportItem getNewestItem() {
        ArrayList<ChatReportItem> arrayList = this.masterChatItemsArray;
        if (arrayList != null) {
            if (Build.VERSION.SDK_INT >= 24) {
                arrayList.sort(Comparator.comparingLong(new ToLongFunction() { // from class: com.channel2.mobile.ui.Chats.models.ChatItemsArray$$ExternalSyntheticLambda0
                    @Override // java.util.function.ToLongFunction
                    public final long applyAsLong(Object obj) {
                        return ((ChatReportItem) obj).getUpdatedDate();
                    }
                }).reversed());
            } else {
                Collections.sort(arrayList, new Comparator() { // from class: com.channel2.mobile.ui.Chats.models.ChatItemsArray$$ExternalSyntheticLambda1
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return ChatItemsArray.lambda$getNewestItem$0((ChatReportItem) obj, (ChatReportItem) obj2);
                    }
                });
            }
            return arrayList.get(0);
        }
        return new ChatReportItem();
    }

    static /* synthetic */ int lambda$getNewestItem$0(ChatReportItem chatReportItem, ChatReportItem chatReportItem2) {
        return (int) (chatReportItem2.getUpdatedDate() - chatReportItem.getUpdatedDate());
    }

    public void resetTopics(ChatTopic chatTopic) {
        this.isInitialized.put(Integer.valueOf(chatTopic.getTopicId()), false);
        this.lobbyChatItems.remove(Integer.valueOf(chatTopic.getTopicId()));
        this.tabFragmentChatItems.remove(Integer.valueOf(chatTopic.getTopicId()));
        this.masterChatItemsArray = null;
    }
}
