package com.channel2.mobile.ui.Chats.controllers;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.Chats.Helpers.ChatCallbacks;
import com.channel2.mobile.ui.Chats.models.ChatItemType;
import com.channel2.mobile.ui.Chats.models.ChatMediaItem;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.Chats.models.ChatTopic;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.advertising.InterstitialManager;
import com.channel2.mobile.ui.advertising.InterstitialStatus;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.helpers.CustomLinearLayoutManager;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.ItemManager;
import com.channel2.mobile.ui.lobby.models.teasers.LobbyTeaser;
import com.channel2.mobile.ui.network.ApiService;
import com.channel2.mobile.ui.reports.CustomBroadcastReceiver;
import com.channel2.mobile.ui.reports.TransparentWebView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private String action;
    private int animateViewPosition;
    ChatCallbacks chatCallbacks;
    private int currentFirstVisible;
    private boolean exitToGallery;
    private int firstVisibleInListview;
    boolean hasLiveDataObserver;
    private TextView headerSectionText;
    private long highlightMessageId;
    private int id;
    private boolean interstitialAdClosed;
    private boolean isFromTab;
    private boolean isViewLoaded;
    private final Context mContext;
    public CustomLinearLayoutManager mLayoutManager;
    private final MainActivity mainActivity;
    private String messageType;
    int numberOfItemOnPause;
    private int page;
    private String partner;
    private int position;
    private int rcScrollParam;
    private ChatRecyclerViewAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private ImageButton scrollDown;
    private Animation sectionHeaderfadeAnimation;
    private long sharedMessageId;
    private long theMostUpdatedChatItemId;
    private long timeEnteredScreen;
    private long timeOnScreen;
    private ChatTopic topic;
    private int topicId;
    private String topicName;
    private Button tryAgainButton;
    private ConstraintLayout tryAgainError;
    private TextView unreadMessagesText;
    private String utm_medium;
    private String utm_source;
    private View view;

    public ChatViewHolder(View view, Context context, MainActivity mainActivity, ChatCallbacks chatCallbacks) {
        super(view);
        this.position = 0;
        this.page = 1;
        this.currentFirstVisible = 0;
        this.firstVisibleInListview = 0;
        this.rcScrollParam = 0;
        this.theMostUpdatedChatItemId = 0L;
        this.exitToGallery = false;
        this.hasLiveDataObserver = false;
        this.messageType = "";
        this.interstitialAdClosed = false;
        this.numberOfItemOnPause = 0;
        this.timeEnteredScreen = 0L;
        this.timeOnScreen = -1L;
        this.topicId = 1;
        this.partner = "AppNavBar";
        this.isFromTab = false;
        this.utm_source = "";
        this.utm_medium = "";
        this.highlightMessageId = 0L;
        this.sharedMessageId = 0L;
        this.mContext = context;
        this.view = view;
        this.chatCallbacks = chatCallbacks;
        this.mainActivity = mainActivity;
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.scrollDown = (ImageButton) view.findViewById(R.id.scrollDown);
        this.unreadMessagesText = (TextView) view.findViewById(R.id.unreadMessagesText);
        this.headerSectionText = (TextView) view.findViewById(R.id.headerSectionText);
        this.isViewLoaded = true;
        view.setTag(this);
    }

    public int getTopicId() {
        return this.topicId;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public int getRcScrollParam() {
        return this.rcScrollParam;
    }

    public long getTimeOnScreen() {
        return this.timeOnScreen;
    }

    public void resetTimeOnScreen() {
        this.timeEnteredScreen = 0L;
        this.timeOnScreen = -1L;
    }

    public void setViewResources(int i) {
        this.position = i;
        ChatTopic chatTopic = ChatManager.getInstance().chatTopics.get(i);
        this.topic = chatTopic;
        this.topicId = chatTopic.getTopicId();
        this.topicName = this.topic.getTopicName();
        String background = this.topic.getBackground();
        if (background != null && background.length() > 0) {
            Glide.with(this.view).load(background).into((ImageView) this.view.findViewById(R.id.chatTabBG));
        }
        if (ChatManager.getInstance().chatItemsArray != null && ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).size() > 0) {
            init();
        } else {
            tryAgain();
        }
    }

    public void processArgs(Bundle bundle) {
        if (bundle != null) {
            if (bundle.containsKey("partner")) {
                this.partner = bundle.getString("partner");
            }
            if (bundle.containsKey("id")) {
                this.id = bundle.getInt("id");
            }
            if (bundle.containsKey("messageId")) {
                this.highlightMessageId = bundle.getLong("messageId");
                if (this.partner.equals("SharedChatMessage")) {
                    this.sharedMessageId = this.highlightMessageId;
                }
            }
            if (bundle.containsKey("action")) {
                this.action = bundle.getString("action");
            }
            if (bundle.containsKey("utm_source") && !Objects.equals(bundle.getString("utm_source"), "")) {
                this.utm_source = bundle.getString("utm_source");
            }
            if (bundle.containsKey("utm_medium") && !Objects.equals(bundle.getString("utm_medium"), "")) {
                this.utm_medium = bundle.getString("utm_medium");
            }
            this.isFromTab = bundle.containsKey("isFromTab") && bundle.getBoolean("isFromTab");
        }
    }

    public void init() {
        this.scrollDown.setOnClickListener(this);
        CustomLinearLayoutManager customLinearLayoutManager = new CustomLinearLayoutManager(this.mContext);
        this.mLayoutManager = customLinearLayoutManager;
        customLinearLayoutManager.setSmoothScrollbarEnabled(true);
        this.mLayoutManager.setReverseLayout(true);
        this.recyclerView.setItemViewCacheSize(20);
        this.recyclerView.scrollToPosition(0);
        this.recyclerView.setLayoutManager(this.mLayoutManager);
        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setItemAnimator(null);
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                try {
                    ChatViewHolder.this.scrolled(i, i2);
                    ChatViewHolder chatViewHolder = ChatViewHolder.this;
                    chatViewHolder.currentFirstVisible = chatViewHolder.mLayoutManager.findFirstVisibleItemPosition();
                    if (ChatViewHolder.this.currentFirstVisible > ChatViewHolder.this.firstVisibleInListview) {
                        Log.i("RecyclerView scrolled: ", "scroll up!");
                        if (ChatViewHolder.this.currentFirstVisible % 10 == 0) {
                            ChatViewHolder chatViewHolder2 = ChatViewHolder.this;
                            chatViewHolder2.rcScrollParam = chatViewHolder2.currentFirstVisible;
                        }
                    } else {
                        Log.i("RecyclerView scrolled: ", "scroll down!");
                    }
                    ChatViewHolder chatViewHolder3 = ChatViewHolder.this;
                    chatViewHolder3.firstVisibleInListview = chatViewHolder3.currentFirstVisible;
                } catch (Exception unused) {
                    Log.i("", "");
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) throws Resources.NotFoundException {
                super.onScrollStateChanged(recyclerView, i);
                ChatViewHolder.this.scrollStateChanged(i);
            }
        });
        this.recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(View view) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(View view) {
                RecyclerView.ViewHolder childViewHolder = ChatViewHolder.this.recyclerView.getChildViewHolder(view);
                if (childViewHolder instanceof ChatTabViewModel) {
                    ((ChatTabViewModel) childViewHolder).attachedToWindow();
                }
            }
        });
        ChatRecyclerViewAdapter chatRecyclerViewAdapter = new ChatRecyclerViewAdapter(ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId), new ItemManager(), this.topic, new LobbyFragmentHandler() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder.3
            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void enableVerticleScroll(boolean z) {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onListRefreshed() {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onPauseVideo(LobbyTeaser lobbyTeaser, FrameLayout frameLayout, ImageView imageView) {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onPlayVideo(LobbyTeaser lobbyTeaser, FrameLayout frameLayout, ImageView imageView) {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onClick(Item item, Bundle bundle) {
                ChatViewHolder.this.click(item, bundle);
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void readMore(int i) {
                ChatViewHolder.this.recyclerAdapter.notifyItemChanged(i);
                ChatViewHolder.this.scrollMessageToTop(i);
            }
        });
        this.recyclerAdapter = chatRecyclerViewAdapter;
        this.recyclerView.setAdapter(chatRecyclerViewAdapter);
        this.recyclerAdapter.setChatItems(ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId));
        this.theMostUpdatedChatItemId = ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).get(0).getMessageID();
        if (this.position == 0) {
            onResume();
        }
        this.isViewLoaded = true;
        Log.i("chatLifeCycle", "VH init complete");
    }

    private void initHighlightMessage() {
        if (!this.isViewLoaded || this.highlightMessageId <= 0) {
            return;
        }
        if (this.interstitialAdClosed) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$initHighlightMessage$0();
                }
            }, 800L);
            return;
        }
        InterstitialStatus value = InterstitialManager.getInstance().interstitialStateLiveData.getValue();
        Log.i("chatLifeCycle", "VH  initHighlightMessage and status = " + value);
        if (value == InterstitialStatus.SHOW || value == InterstitialStatus.LOADING) {
            return;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$initHighlightMessage$1();
            }
        }, 800L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initHighlightMessage$0() {
        highlightMessage(this.highlightMessageId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initHighlightMessage$1() {
        highlightMessage(this.highlightMessageId);
    }

    private void refresh() {
        try {
            ChatRecyclerViewAdapter chatRecyclerViewAdapter = this.recyclerAdapter;
            if (chatRecyclerViewAdapter != null) {
                chatRecyclerViewAdapter.notifyDataSetChanged();
            }
            this.theMostUpdatedChatItemId = ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).get(0).getMessageID();
            if (this.unreadMessagesText.getVisibility() != 0 || ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).size() - this.numberOfItemOnPause <= 0) {
                return;
            }
            if (ChatManager.getInstance().unreadMessagesCount != null) {
                ChatManager.getInstance().unreadMessagesCount.put(Integer.valueOf(this.topicId), Integer.valueOf(ChatManager.getInstance().unreadMessagesCount.get(Integer.valueOf(this.topicId)) != null ? ChatManager.getInstance().unreadMessagesCount.get(Integer.valueOf(this.topicId)).intValue() : 0));
            }
            if (ChatManager.getInstance().unreadMessagesCount.get(Integer.valueOf(this.topicId)) != null) {
                this.unreadMessagesText.setText(String.valueOf(ChatManager.getInstance().unreadMessagesCount.get(Integer.valueOf(this.topicId))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tryAgain() {
        this.tryAgainError = (ConstraintLayout) this.view.findViewById(R.id.errorDialog);
        Button button = (Button) this.view.findViewById(R.id.tryAgainButton);
        this.tryAgainButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$tryAgain$3(view);
            }
        });
        this.tryAgainError.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$tryAgain$3(View view) {
        ChatManager.getInstance().fetchItems(1, this.topicId, this.mContext, new ApiService.ResponseHandler() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder$$ExternalSyntheticLambda5
            @Override // com.channel2.mobile.ui.network.ApiService.ResponseHandler
            public final void onSuccess() throws Resources.NotFoundException {
                this.f$0.lambda$tryAgain$2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$tryAgain$2() throws Resources.NotFoundException {
        this.tryAgainError.setVisibility(8);
        this.recyclerView.setAlpha(0.0f);
        init();
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.chat_fade_in_selection);
        animationLoadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                ChatViewHolder.this.recyclerView.setAlpha(1.0f);
            }
        });
        this.recyclerView.startAnimation(animationLoadAnimation);
        ChatManager.getInstance().startFirebaseListener();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String processText(com.channel2.mobile.ui.Chats.models.ChatReportItem r8) {
        /*
            r7 = this;
            int r0 = r8.getNumberOfMedias()
            java.lang.String r1 = ""
            if (r0 <= 0) goto L9d
            r2 = 0
            com.channel2.mobile.ui.Chats.models.ChatMediaItem r2 = r8.getChatMedia(r2)
            long r3 = r2.getMediaTypeId()
            int r3 = (int) r3
            r4 = 2
            r5 = 1
            if (r3 != r4) goto L2d
            java.lang.String r1 = "video"
            r7.messageType = r1
            if (r0 != r5) goto L21
            java.lang.String r0 = "סרטון"
            goto L25
        L21:
            java.lang.String r0 = r7.getMultiFilesDescription(r8)
        L25:
            r1 = r0
            java.lang.String r0 = "📹 "
        L29:
            r6 = r1
            r1 = r0
            r0 = r6
            goto L44
        L2d:
            if (r3 != r5) goto L43
            java.lang.String r1 = "picture"
            r7.messageType = r1
            if (r0 != r5) goto L3a
            java.lang.String r0 = "תמונה"
            goto L3e
        L3a:
            java.lang.String r0 = r7.getMultiFilesDescription(r8)
        L3e:
            r1 = r0
            java.lang.String r0 = "📷 "
            goto L29
        L43:
            r0 = r1
        L44:
            java.lang.String r3 = r2.getMediaContent()
            if (r3 == 0) goto L68
            java.lang.String r3 = r2.getMediaContent()
            int r3 = r3.length()
            if (r3 <= 0) goto L68
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r1)
            java.lang.String r0 = r2.getMediaContent()
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            goto L9b
        L68:
            java.lang.String r2 = r8.getMessageContent()
            if (r2 == 0) goto L8c
            java.lang.String r2 = r8.getMessageContent()
            int r2 = r2.length()
            if (r2 <= 0) goto L8c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            java.lang.String r8 = r8.getMessageContent()
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            goto L9b
        L8c:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r1)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
        L9b:
            r1 = r8
            goto Lc2
        L9d:
            java.lang.String r0 = r8.getMessageContent()
            if (r0 == 0) goto Lc2
            java.lang.String r0 = r8.getMessageContent()
            int r0 = r0.length()
            if (r0 <= 0) goto Lc2
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            java.lang.String r8 = r8.getMessageContent()
            r0.append(r8)
            java.lang.String r1 = r0.toString()
            java.lang.String r8 = "text"
            r7.messageType = r8
        Lc2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder.processText(com.channel2.mobile.ui.Chats.models.ChatReportItem):java.lang.String");
    }

    private String getMultiFilesDescription(ChatReportItem chatReportItem) {
        int size = chatReportItem.getChatMedias().size();
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            ChatMediaItem chatMediaItem = chatReportItem.getChatMedias().get(i);
            if (chatMediaItem.getMediaTypeId() == 1) {
                z = true;
            }
            if (chatMediaItem.getMediaTypeId() == 2) {
                z2 = true;
            }
        }
        String str = size + StringUtils.SPACE;
        if (z && z2) {
            return str + "קבצים";
        }
        if (!z2) {
            return str + "תמונות";
        }
        if (z) {
            return "";
        }
        return str + "סרטונים";
    }

    private void highlightMessage(long j) {
        int tabMessageIndexById;
        Log.i("messageTiming", "VH highlightMessage");
        Log.i("chatLifeCycle", "VH highlightMessage");
        if (j == 0 || (tabMessageIndexById = ChatManager.getInstance().chatItemsArray.getTabMessageIndexById(j, this.topicId)) == -1) {
            return;
        }
        View viewFindViewByPosition = this.mLayoutManager.findViewByPosition(tabMessageIndexById);
        int height = viewFindViewByPosition != null ? viewFindViewByPosition.getHeight() : 20;
        this.mLayoutManager.scrollToPositionWithOffset(tabMessageIndexById, (this.recyclerView.getHeight() / 2) - (height / 2));
        Log.d("highlightMessage", "position: " + tabMessageIndexById + " viewHeight: " + height);
        animateViewBackground(tabMessageIndexById, 100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrolled(int i, int i2) {
        try {
            int iFindFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
            int iFindLastVisibleItemPosition = this.mLayoutManager.findLastVisibleItemPosition();
            if (iFindFirstVisibleItemPosition > 0) {
                this.scrollDown.setVisibility(0);
                if (ChatManager.getInstance().unreadMessagesCount.get(Integer.valueOf(this.topicId)).intValue() > 0) {
                    this.unreadMessagesText.setVisibility(0);
                }
            } else {
                ChatManager.getInstance().unreadMessagesCount.put(Integer.valueOf(this.topicId), 0);
                this.scrollDown.setVisibility(8);
                this.unreadMessagesText.setVisibility(8);
            }
            if (ChatManager.getInstance().hasMorePages.get(Integer.valueOf(this.topicId)) != null && ChatManager.getInstance().hasMorePages.get(Integer.valueOf(this.topicId)).booleanValue() && ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).size() - 1 == iFindLastVisibleItemPosition) {
                ChatManager.getInstance().hasMorePages.put(Integer.valueOf(this.topicId), false);
                this.page++;
                ChatManager.getInstance().fetchMoreItems(this.topicId, this.mContext);
            }
            ChatReportItem chatReportItem = ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).get(iFindLastVisibleItemPosition);
            if (chatReportItem.getChatItemType() == ChatItemType.sectionHeader || chatReportItem.getChatItemType() == ChatItemType.typing || chatReportItem.getChatItemType() == ChatItemType.unreadMessages) {
                return;
            }
            this.headerSectionText.setText(ChatManager.getInstance().getDateString(chatReportItem.getUpdatedDate()));
            this.headerSectionText.setVisibility(0);
        } catch (Exception unused) {
            Log.i("", "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollStateChanged(int i) throws Resources.NotFoundException {
        if (i == 1) {
            fadeIn(this.headerSectionText);
        } else if (i == 0) {
            fadeOut(this.headerSectionText);
        }
    }

    void scrollMessageToTop(int i) {
        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(this.mContext) { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder.5
            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected int getVerticalSnapPreference() {
                return 0;
            }
        };
        this.animateViewPosition = i;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder.6
            @Override // java.lang.Runnable
            public void run() {
                linearSmoothScroller.setTargetPosition(ChatViewHolder.this.animateViewPosition);
                ChatViewHolder.this.mLayoutManager.startSmoothScroll(linearSmoothScroller);
            }
        }, 10L);
    }

    void scrollDown() {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(this.recyclerView.getContext()) { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder.7
            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 1.0f / displayMetrics.densityDpi;
            }
        };
        linearSmoothScroller.setTargetPosition(0);
        this.mLayoutManager.startSmoothScroll(linearSmoothScroller);
        ChatManager.getInstance().unreadMessagesCount.put(Integer.valueOf(this.topicId), 0);
        this.scrollDown.setVisibility(8);
        this.unreadMessagesText.setVisibility(8);
    }

    void fadeOut(final View view) throws Resources.NotFoundException {
        view.setAlpha(1.0f);
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.chat_fade_out_selection);
        this.sectionHeaderfadeAnimation = animationLoadAnimation;
        animationLoadAnimation.setDuration(800L);
        this.sectionHeaderfadeAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder.8
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                view.setAlpha(0.0f);
            }
        });
        view.startAnimation(this.sectionHeaderfadeAnimation);
    }

    void fadeIn(View view) {
        stopAnimation(view, this.sectionHeaderfadeAnimation);
        view.setAlpha(1.0f);
    }

    private void animateViewBackground(int i, int i2) {
        this.animateViewPosition = i;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws Resources.NotFoundException {
                this.f$0.lambda$animateViewBackground$4();
            }
        }, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$animateViewBackground$4() throws Resources.NotFoundException {
        View viewFindViewByPosition = this.mLayoutManager.findViewByPosition(this.animateViewPosition);
        if (viewFindViewByPosition != null) {
            RecyclerView.ViewHolder childViewHolder = this.recyclerView.getChildViewHolder(viewFindViewByPosition);
            if (childViewHolder instanceof ChatTabViewModel) {
                ((ChatTabViewModel) childViewHolder).animateSelection();
            }
        }
    }

    void stopAnimation(View view, Animation animation) {
        if (animation == null || view.getAnimation() == null) {
            return;
        }
        animation.cancel();
        view.clearAnimation();
        animation.setAnimationListener(null);
    }

    void updateData() {
        if (ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(this.topicId)) != null) {
            this.recyclerAdapter.notifyItemRangeChanged(0, ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(this.topicId)).intValue());
        }
        if (ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(this.topicId)) != null && ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(this.topicId)).intValue() == 1) {
            this.recyclerAdapter.notifyItemInserted(1);
        }
        this.recyclerAdapter.notifyItemInserted(0);
    }

    public void onNewLiveDataMessage() {
        ArrayList<ChatReportItem> chatItems = ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId);
        if (chatItems == null || chatItems.isEmpty()) {
            return;
        }
        final ChatReportItem chatReportItem = chatItems.get(0);
        if (chatReportItem != null && chatReportItem.getChatItemType() != ChatItemType.sectionHeader) {
            Log.d("ReportersChatFragment", "ChatMessageList Updated" + chatReportItem.getMessageID());
            Log.i("chatLifeCycle", "VH ChatMessageList Updated with id " + chatReportItem.getMessageID());
            if (this.theMostUpdatedChatItemId != ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).get(0).getMessageID()) {
                this.theMostUpdatedChatItemId = ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).get(0).getMessageID();
                if (this.mLayoutManager.findFirstVisibleItemPosition() != 0 || (this.mLayoutManager.findFirstVisibleItemPosition() == 0 && ChatManager.getInstance().isUnreadMessagesItemVisible.get(Integer.valueOf(this.topicId)) != null && ChatManager.getInstance().isUnreadMessagesItemVisible.get(Integer.valueOf(this.topicId)).booleanValue())) {
                    ChatManager.getInstance().unreadMessagesCount.put(Integer.valueOf(this.topicId), Integer.valueOf(ChatManager.getInstance().unreadMessagesCount.get(Integer.valueOf(this.topicId)) != null ? ChatManager.getInstance().unreadMessagesCount.get(Integer.valueOf(this.topicId)).intValue() + 1 : 0));
                    ChatManager.getInstance().unreadMessagesCountForSession.put(Integer.valueOf(this.topicId), Integer.valueOf(ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(this.topicId)) != null ? ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(this.topicId)).intValue() + 1 : 0));
                    if (ChatManager.getInstance().unreadMessagesCount.get(Integer.valueOf(this.topicId)) != null) {
                        this.unreadMessagesText.setText(String.valueOf(ChatManager.getInstance().unreadMessagesCount.get(Integer.valueOf(this.topicId))));
                        this.unreadMessagesText.setVisibility(0);
                    }
                    if (ChatManager.getInstance().isUnreadMessagesItemVisible.get(Integer.valueOf(this.topicId)) != null && !ChatManager.getInstance().isUnreadMessagesItemVisible.get(Integer.valueOf(this.topicId)).booleanValue()) {
                        ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).get(0).setFirst(true);
                    }
                    if (ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(this.topicId)) == null) {
                        ChatManager.getInstance().unreadMessagesItem.put(Integer.valueOf(this.topicId), new ChatReportItem());
                        ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(this.topicId)).setChatItemType(ChatItemType.unreadMessages);
                        ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).add(ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(this.topicId)).intValue(), ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(this.topicId)));
                    }
                    if (ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(this.topicId)) != null) {
                        if (ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(this.topicId)).intValue() == 1) {
                            ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(this.topicId)).setMessageContent("הודעה אחת שלא נקראה");
                        } else {
                            ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(this.topicId)).setMessageContent(ChatManager.getInstance().unreadMessagesCountForSession.get(Integer.valueOf(this.topicId)) + " הודעות שלא נקראו");
                        }
                    }
                    ChatManager.getInstance().isUnreadMessagesItemVisible.put(Integer.valueOf(this.topicId), true);
                }
                if (this.mLayoutManager.findFirstVisibleItemPosition() == 0) {
                    chatReportItem.setChatItemTempType(ChatItemType.typing);
                    scrollDown();
                    Log.d("ReportersChatFragment", "VH Typing: start: " + chatReportItem.getMessageID());
                    Log.i("chatLifeCycle", "VH Typing: start with id " + chatReportItem.getMessageID());
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder$$ExternalSyntheticLambda7
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onNewLiveDataMessage$5(chatReportItem);
                        }
                    }, 1500L);
                }
            }
        }
        updateData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onNewLiveDataMessage$5(ChatReportItem chatReportItem) {
        chatReportItem.setChatItemTempType(chatReportItem.getChatItemType());
        this.recyclerAdapter.notifyItemChanged(0);
        if (this.mLayoutManager.findFirstVisibleItemPosition() == 0) {
            scrollDown();
        }
        Log.d("ReportersChatFragment", "TVH yping: stop: " + chatReportItem.getMessageID());
        Log.i("chatLifeCycle", "VH Typing: stop with id " + chatReportItem.getMessageID());
    }

    public void onPause() {
        Log.i("chatLifeCycle", "VH onPause");
        if (ChatManager.getInstance().isUnreadMessagesItemVisible.get(Integer.valueOf(this.topicId)) != null && ChatManager.getInstance().isUnreadMessagesItemVisible.get(Integer.valueOf(this.topicId)).booleanValue() && ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(this.topicId)) != null) {
            ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).remove(ChatManager.getInstance().unreadMessagesItem.get(Integer.valueOf(this.topicId)));
            ChatManager.getInstance().unreadMessagesItem.put(Integer.valueOf(this.topicId), null);
            ChatManager.getInstance().isUnreadMessagesItemVisible.put(Integer.valueOf(this.topicId), false);
        }
        ChatManager.getInstance().unreadMessagesCountForSession.put(Integer.valueOf(this.topicId), 0);
        ChatRecyclerViewAdapter chatRecyclerViewAdapter = this.recyclerAdapter;
        if (chatRecyclerViewAdapter != null) {
            this.numberOfItemOnPause = chatRecyclerViewAdapter.getItemCount();
        }
        if (!this.exitToGallery && this.sharedMessageId != 0) {
            int tabMessageIndexById = ChatManager.getInstance().chatItemsArray.getTabMessageIndexById(this.sharedMessageId, this.topicId);
            if (tabMessageIndexById != -1) {
                ChatReportItem chatReportItem = ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).get(tabMessageIndexById);
                if (chatReportItem.messageState == ChatReportItem.MessageState.special || chatReportItem.messageState == ChatReportItem.MessageState.blue) {
                    ChatManager.getInstance().chatItemsArray.removeItemFromTab(chatReportItem);
                    this.theMostUpdatedChatItemId = ChatManager.getInstance().chatItemsArray.getChatItems(true, this.topicId).get(0).getMessageID();
                } else {
                    chatReportItem.messageState = ChatReportItem.MessageState.regular;
                }
            }
            this.sharedMessageId = 0L;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("Display", this.rcScrollParam);
        bundle.putString("chatName", this.topicName);
        FirebaseAnalytics.getInstance(this.mContext).logEvent("Rc_Scroll", bundle);
        this.timeOnScreen += System.currentTimeMillis() - this.timeEnteredScreen;
        Bundle bundle2 = new Bundle();
        bundle2.putString("Time", Utils.getFormatDuration(this.timeOnScreen, "HH:mm:ss"));
        bundle2.putString("chatName", this.topicName);
        FirebaseAnalytics.getInstance(this.mainActivity).logEvent("RC_time", bundle2);
        Log.i("chatLifeCycleTime", "VH timeOnScreen: " + Utils.getFormatDuration(this.timeOnScreen, "HH:mm:ss"));
        this.highlightMessageId = 0L;
        this.partner = "AppNavBar";
        ChatRecyclerViewAdapter chatRecyclerViewAdapter2 = this.recyclerAdapter;
        if (chatRecyclerViewAdapter2 != null) {
            chatRecyclerViewAdapter2.notifyDataSetChanged();
        }
    }

    public void onResume() {
        if (!this.exitToGallery) {
            reportAnalyticsEvents();
        }
        refresh();
        Log.i("chatLifeCycle", "VH onResume");
        this.timeEnteredScreen = System.currentTimeMillis();
        if (this.highlightMessageId != 0) {
            initHighlightMessage();
        }
        this.exitToGallery = false;
        this.messageType = "";
    }

    public void onInterstitialAdClosed() {
        Log.i("chatLifeCycle", "VH onInterstitialAdClosed");
        this.interstitialAdClosed = true;
        initHighlightMessage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void click(Item item, Bundle bundle) {
        Log.i("chatLifeCycle", "VH clicl" + bundle);
        if (bundle != null) {
            long j = bundle.getLong("mediaID");
            if (bundle.getString("clickType") != null && bundle.getString("clickType").equals("click")) {
                click(item, j);
            }
            if (MainConfig.main.getCurrentBooleanParam("enable_chat_share") && bundle.getString("clickType") != null && bundle.getString("clickType").equals("longClick")) {
                longClick(item, j);
            }
        }
    }

    private void click(Item item, long j) {
        final ChatReportItem chatReportItem = (ChatReportItem) item;
        switch (AnonymousClass9.$SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType[chatReportItem.getChatItemType().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                ChatReportItem originalChatItem = ChatManager.getInstance().chatItemsArray.getOriginalChatItem(chatReportItem);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chatReportItem", originalChatItem);
                bundle.putLong("mediaId", j);
                bundle.putString("chatName", this.topicName);
                ChatCallbacks chatCallbacks = this.chatCallbacks;
                if (chatCallbacks != null) {
                    this.exitToGallery = true;
                    chatCallbacks.onInflateGallery(bundle);
                    break;
                }
                break;
            case 8:
            case 9:
            case 10:
            case 11:
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$click$6(chatReportItem);
                    }
                }, 800L);
                break;
        }
    }

    /* renamed from: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType;

        static {
            int[] iArr = new int[ChatItemType.values().length];
            $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType = iArr;
            try {
                iArr[ChatItemType.collage.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType[ChatItemType.gallery.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType[ChatItemType.mediaAndText.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType[ChatItemType.mediaX1.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType[ChatItemType.mediaX2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType[ChatItemType.mediaVideo.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType[ChatItemType.mediaVideoAndText.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType[ChatItemType.linkReply.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType[ChatItemType.mediaAndTextReply.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType[ChatItemType.mediaReply.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$models$ChatItemType[ChatItemType.textReply.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$click$6(ChatReportItem chatReportItem) {
        highlightMessage(chatReportItem.getReply().getMessageID());
    }

    private void longClick(Item item, long j) {
        new CustomBroadcastReceiver(new CustomBroadcastReceiver.BroadcastReceiverCallBack() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatViewHolder$$ExternalSyntheticLambda6
            @Override // com.channel2.mobile.ui.reports.CustomBroadcastReceiver.BroadcastReceiverCallBack
            public final void onBroadCastReceive(String str) {
                this.f$0.onReceive(str);
            }
        });
        ChatReportItem chatReportItem = (ChatReportItem) item;
        String str = chatReportItem.getName() + StringUtils.SPACE + this.mContext.getResources().getString(R.string.share_chat_title) + StringUtils.LF;
        String strReplace = MainConfig.main.getCurrentParam("chat_share_url").replace("%MESSAGE_ID%", String.valueOf(chatReportItem.getMessageID()));
        if (chatReportItem.isSplitted() && j != 0) {
            strReplace = strReplace + ("&autoId=" + j);
        }
        String str2 = str + (processText(chatReportItem) + StringUtils.LF) + strReplace;
        ChatCallbacks chatCallbacks = this.chatCallbacks;
        if (chatCallbacks != null) {
            chatCallbacks.onInflateShare(str2);
        }
        Log.i("shared", str2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.scrollDown) {
            try {
                int iFindFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
                Bundle bundle = new Bundle();
                bundle.putString("Height", String.valueOf(iFindFirstVisibleItemPosition - (iFindFirstVisibleItemPosition % 10)));
                bundle.putString("chatName", this.topicName);
                FirebaseAnalytics.getInstance(this.mainActivity).logEvent("RC_click_arrow", bundle);
                scrollDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onReceive(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("partner", "ChatMessageShare");
        bundle.putString("appShare", str);
        bundle.putString("messageType", this.messageType);
        bundle.putString("chatName", this.topicName);
        FirebaseAnalytics.getInstance(this.mainActivity).logEvent("RC_share", bundle);
        Log.i("reportMetrics", "VH appShare = " + str);
        Log.i("reportMetrics", "VH messageType = " + this.messageType);
    }

    public void reportAnalyticsEvents() {
        String str;
        Log.i("chatLifeCycle", "reportAnalyticsEvents");
        try {
            Log.i("reportMetrics", "Chat");
            FirebaseAnalytics.getInstance(this.mainActivity).setCurrentScreen(this.mainActivity, "/chats", null);
            Bundle bundle = new Bundle();
            bundle.putString("Partner", this.partner);
            bundle.putString("chatName", this.topicName);
            if (this.partner.equals("SharedChatMessage")) {
                bundle.putString("utm_source", this.utm_source);
                bundle.putString("utm_medium", this.utm_medium);
            }
            FirebaseAnalytics.getInstance(this.mainActivity).logEvent("RC_Main", bundle);
            String strReplace = MainConfig.main.getCurrentSource("reportMetrics").replace("%GUID%", "31750a2610f26110VgnVCM1000005201000aRCRD").replace("%VCM_ID%", "RC_Main").replace("%CONTENT_TYPE%", "reportersChat").replace("%FRIENDLY_URL%", "/reportersChat");
            if (this.isFromTab) {
                str = this.partner;
            } else {
                str = this.topicName + "_" + this.partner;
            }
            String strReplace2 = strReplace.replace("%PARTNER%", str).replace("%TOPIC_NAME%", this.topicName);
            long j = this.highlightMessageId;
            if (j > 0) {
                strReplace2 = strReplace2.replace("%MESSAGE_ID%", String.valueOf(j));
            }
            Log.i("checkReport", "PARTNER = " + this.partner);
            Log.i("chatLifeCycle", "PARTNER = " + this.partner);
            String strValueOf = "";
            if (strReplace2.contains("EVENT_NAME")) {
                strReplace2 = strReplace2.replace("%EVENT_NAME%", "");
            }
            long j2 = this.highlightMessageId;
            String strReplace3 = strReplace2.replace("%MESSAGE_ID%", j2 == 0 ? "" : String.valueOf(j2));
            StringBuilder sb = new StringBuilder("MESSAGE_ID = ");
            long j3 = this.highlightMessageId;
            sb.append(j3 == 0 ? "" : String.valueOf(j3));
            Log.i("checkReport", sb.toString());
            StringBuilder sb2 = new StringBuilder("MESSAGE_ID = ");
            long j4 = this.highlightMessageId;
            if (j4 != 0) {
                strValueOf = String.valueOf(j4);
            }
            sb2.append(strValueOf);
            Log.i("chatLifeCycle", sb2.toString());
            TransparentWebView.report(this.mainActivity, strReplace3);
            this.isFromTab = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("reportMetrics", "partner = " + this.partner);
    }
}
