package com.channel2.mobile.ui.Chats.controllers;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.lifecycle.Observer;
import androidx.media3.common.PlaybackException;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.Chats.Helpers.ChatCallbacks;
import com.channel2.mobile.ui.Chats.controllers.ChatManager;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.Chats.models.ChatTopic;
import com.channel2.mobile.ui.Chats.models.ChatTutorialDialog;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.advertising.InterstitialManager;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.configs.models.Tab;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.models.ads.DFP;
import com.channel2.mobile.ui.pushNotification.DeepLinkManager;
import com.channel2.mobile.ui.reports.CustomBroadcastReceiver;
import com.channel2.mobile.ui.video.PlayerActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.permutive.android.EventProperties;
import com.permutive.android.PageTracker;
import java.util.Calendar;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ChatsFragment extends CustomFragment implements ChatCallbacks, TabLayout.OnTabSelectedListener {
    private MultiChatsAdapter adapter;
    private Bundle args;
    private ImageView chatLoader;
    private int currentTopicId;
    private int goToTopicId;
    int gotoTabPosition;
    private long highlightMessageId;
    private int id;
    private boolean isViewLoaded;
    private Context mContext;
    private MainActivity mainActivity;
    private PageTracker pageTracker;
    private String partner;
    private TabLayout tabLayout;
    View view;
    private ViewPager2 viewPager;
    private long autoId = 0;
    boolean exitToGallery = false;
    private boolean isReportLock = false;
    int currentTabPosition = 0;

    private enum TabState {
        INIT,
        SELECTED,
        UNSELECTED
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabReselected(TabLayout.Tab tab) {
    }

    public ChatsFragment() {
        this.currentTopicId = ChatManager.getInstance().chatTopics.size() > 0 ? ChatManager.getInstance().chatTopics.get(this.currentTabPosition).getTopicId() : 0;
        this.highlightMessageId = 0L;
        this.goToTopicId = 0;
        this.gotoTabPosition = 0;
        this.isViewLoaded = false;
    }

    public static ChatsFragment newInstance(String str, int i, Bundle bundle) {
        ChatsFragment chatsFragment = new ChatsFragment();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("id", i);
        bundle.putString("action", str);
        chatsFragment.setArguments(bundle);
        return chatsFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            setArgs(getArguments());
            int i = getArguments().getInt("id");
            this.id = i;
            setTabId(i);
            MainActivity mainActivity = this.mainActivity;
            if (mainActivity != null && mainActivity.bottomNavigationView != null) {
                this.mainActivity.bottomNavigationView.selectItem(this.id);
            }
            try {
                setArguments(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.view == null) {
            this.view = layoutInflater.inflate(R.layout.fragment_chats, viewGroup, false);
            init();
        }
        this.tabLayout = (TabLayout) this.view.findViewById(R.id.tabs);
        if (ChatManager.getInstance().chatTopics.size() < 2) {
            this.tabLayout.setVisibility(8);
        }
        this.viewPager = (ViewPager2) this.view.findViewById(R.id.pager);
        this.chatLoader = (ImageView) this.view.findViewById(R.id.chatLoader);
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("chatLifeCycle", "F onAttach");
        this.mContext = context;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            this.mainActivity = mainActivity;
            if (mainActivity.adContainer != null) {
                this.mainActivity.adContainer.setVisibility(8);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        ChatViewHolder chatViewHolder;
        PageTracker pageTracker;
        super.onPause();
        Log.i("chatLifeCycle", "F onPause");
        if (MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) && (pageTracker = this.pageTracker) != null) {
            pageTracker.pause();
            Log.i("permutive", "F permutive_pause");
        }
        ChatViewHolder chatViewHolder2 = (ChatViewHolder) ((RecyclerView) this.viewPager.getChildAt(0)).findViewHolderForAdapterPosition(this.currentTabPosition);
        if (chatViewHolder2 != null) {
            chatViewHolder2.onPause();
        }
        if (this.exitToGallery) {
            return;
        }
        for (int i = 0; i < ChatManager.getInstance().chatTopics.size(); i++) {
            try {
                chatViewHolder = (ChatViewHolder) ((RecyclerView) this.viewPager.getChildAt(0)).findViewHolderForAdapterPosition(i);
            } catch (Exception e) {
                e.printStackTrace();
                chatViewHolder = null;
            }
            if (chatViewHolder != null) {
                long timeOnScreen = chatViewHolder.getTimeOnScreen();
                if (timeOnScreen > -1) {
                    String topicName = chatViewHolder.getTopicName();
                    Bundle bundle = new Bundle();
                    bundle.putString("Time", Utils.getFormatDuration(timeOnScreen, "HH:mm:ss") + "_" + topicName);
                    bundle.putString("chatName", topicName);
                    FirebaseAnalytics.getInstance(this.mainActivity).logEvent("RC_time", bundle);
                    Log.i("firebaseReport", "VH " + topicName + " timeOnScreen: " + Utils.getFormatDuration(timeOnScreen, "HH:mm:ss") + "_" + topicName);
                    Bundle bundle2 = new Bundle();
                    StringBuilder sb = new StringBuilder();
                    sb.append(chatViewHolder.getRcScrollParam());
                    sb.append("_");
                    sb.append(topicName);
                    bundle2.putString("Display", sb.toString());
                    bundle2.putString("chatName", topicName);
                    FirebaseAnalytics.getInstance(this.mContext).logEvent("Rc_Scroll", bundle2);
                    Log.i("firebaseReport", "VH " + topicName + " last height: " + chatViewHolder.getRcScrollParam() + "_" + topicName);
                    chatViewHolder.resetTimeOnScreen();
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.highlightMessageId = 0L;
        this.isReportLock = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        PageTracker pageTracker;
        super.onDestroyView();
        if (!MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) || (pageTracker = this.pageTracker) == null) {
            return;
        }
        try {
            pageTracker.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("permutive", "F permutive_Off");
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        PageTracker pageTracker;
        super.onResume();
        MainActivity.isBackFromGallery = false;
        Log.i("chatLifeCycle", "F onResume and messageId = " + this.highlightMessageId);
        try {
            if (MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) && (pageTracker = this.pageTracker) != null) {
                pageTracker.resume();
                Log.i("permutive", "F permutive_resume");
            }
            if (this.highlightMessageId > 0) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatsFragment$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onResume$0();
                    }
                }, 500L);
            } else {
                Log.i("chatLifeCycle", "F initViewPager 3");
                initViewPager();
            }
            this.exitToGallery = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isAdded() && !MainActivity.isInterstitialShowed && MainConfig.main.getCurrentBooleanParam("interstitial_chatTab_enable") && !DeepLinkManager.getInstance().isOpenedByLink().booleanValue() && InterstitialManager.getInstance().hasInterstitialReady()) {
            InterstitialManager.getInstance().createInterstitial(new DFP(MainConfig.main.getCurrentParam("homePageIu") + MainConfig.main.getCurrentParam("secondPreloadUI")), requireActivity(), "", true);
            MainActivity.isInterstitialShowed = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onResume$0() {
        int tabMessageIndexById;
        ChatReportItem chatByMessageId = ChatManager.getInstance().chatItemsArray.getChatByMessageId(this.highlightMessageId);
        if (chatByMessageId != null) {
            this.goToTopicId = chatByMessageId.getTopicID();
            if (this.partner.equals("SharedChatMessage") && (tabMessageIndexById = ChatManager.getInstance().chatItemsArray.getTabMessageIndexById(this.highlightMessageId, this.goToTopicId)) != -1) {
                ChatManager.getInstance().chatItemsArray.getChatItems(true, this.goToTopicId).get(tabMessageIndexById).messageState = ChatReportItem.MessageState.shared;
            }
            Log.i("chatLifeCycle", "F initViewPager 1");
            initViewPager();
            return;
        }
        if (this.partner.equals("SharedChatMessage")) {
            ChatManager.getInstance().fetchItemByID(this.highlightMessageId, this.currentTopicId, new AnonymousClass1());
        } else {
            initViewPager();
        }
    }

    /* renamed from: com.channel2.mobile.ui.Chats.controllers.ChatsFragment$1, reason: invalid class name */
    class AnonymousClass1 implements ChatManager.ResponseHandler {
        @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
        public void onSuccess(int i) {
        }

        AnonymousClass1() {
        }

        @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
        public void onSuccess(JSONObject jSONObject) {
            Log.i("messageTiming", "F answer from service");
            Log.i("chatLifeCycle", "F answer from service");
            try {
                ChatReportItem chatReportItemSplitMessageByAutoId = ChatManager.getInstance().splitMessageByAutoId(jSONObject, ChatsFragment.this.autoId);
                chatReportItemSplitMessageByAutoId.setTyping(false);
                chatReportItemSplitMessageByAutoId.messageState = ChatReportItem.MessageState.special;
                chatReportItemSplitMessageByAutoId.setUpdatedDate(System.currentTimeMillis());
                StringBuilder sb = new StringBuilder("פורסם ב-");
                sb.append(ChatsFragment.this.isToday(chatReportItemSplitMessageByAutoId.getSharedDate()) ? chatReportItemSplitMessageByAutoId.getSharedTimeAsString() : chatReportItemSplitMessageByAutoId.getSharedDateAndTimeAsString());
                chatReportItemSplitMessageByAutoId.setName(sb.toString());
                ChatManager.getInstance().chatItemsArray.insertToTabFragmentChatItems(0, chatReportItemSplitMessageByAutoId, false);
                ChatsFragment.this.goToTopicId = chatReportItemSplitMessageByAutoId.getTopicID();
                Log.i("chatLifeCycle", "F initViewPager 2");
                ChatsFragment.this.initViewPager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
        public void onFailure() {
            final Snackbar snackbarMake = Snackbar.make(ChatsFragment.this.getView(), (CharSequence) null, PlaybackException.ERROR_CODE_DRM_UNSPECIFIED);
            Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarMake.getView();
            View viewInflate = LayoutInflater.from(ChatsFragment.this.mContext).inflate(R.layout.chat_snackbar_message_too_old, (ViewGroup) ChatsFragment.this.view, false);
            snackbarLayout.setPadding(0, 0, 0, 0);
            snackbarLayout.addView(viewInflate);
            ((ImageView) viewInflate.findViewById(R.id.closeMessage)).setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatsFragment$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    snackbarMake.dismiss();
                }
            });
            snackbarMake.show();
        }
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void onInterstitialAdClosed() {
        ChatViewHolder chatViewHolder;
        super.onInterstitialAdClosed();
        Log.i("chatLifeCycle", "F onInterstitialAdClosed");
        ViewPager2 viewPager2 = this.viewPager;
        if (viewPager2 == null || (chatViewHolder = (ChatViewHolder) ((RecyclerView) viewPager2.getChildAt(0)).findViewHolderForAdapterPosition(this.currentTabPosition)) == null) {
            return;
        }
        chatViewHolder.onInterstitialAdClosed();
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void fragmentOnResume(Activity activity) {
        super.fragmentOnResume(activity);
        Log.i("chatLifeCycle", "F fragmentOnResume");
        try {
            if (this.mainActivity == null) {
                this.mainActivity = (MainActivity) activity;
            }
            if (this.mainActivity == null) {
                this.mainActivity = (MainActivity) getActivity();
            }
            MainActivity mainActivity = this.mainActivity;
            if (mainActivity != null) {
                mainActivity.swipeRefreshLayout.setEnabled(false);
                this.mainActivity.toolbarParams.setScrollFlags(0);
                this.mainActivity.exitFullScreen(this);
                if (this.mainActivity.bottomNavigationView != null) {
                    this.mainActivity.bottomNavigationView.selectItem(getTabId());
                }
                this.mainActivity.adContainer.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DeepLinkManager.getInstance().setOpenedByLink(false);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void refresh() {
        super.refresh();
    }

    public void init() {
        if (this.mContext == null) {
            this.mContext = getContext();
        }
        if (this.mContext != null) {
            setHeader();
        }
        setFragmentContainerId(R.id.chat_fragments_container);
        if (MainConfig.main.getCurrentBooleanParam("enable_chat_share") && Utils.getBoolFromPreferences(this.mContext, "isChatFirstTime")) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatsFragment$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$init$1();
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$1() {
        initTutorial();
        Utils.saveBoolToPreferences(this.mContext, "isChatFirstTime", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewPager() {
        if (this.exitToGallery) {
            return;
        }
        this.chatLoader.setVisibility(8);
        if (this.goToTopicId > 0) {
            this.gotoTabPosition = ChatManager.getInstance().getTopicPositionById(this.goToTopicId);
        }
        if (this.gotoTabPosition >= 0) {
            if (this.viewPager.getCurrentItem() != this.gotoTabPosition) {
                this.viewPager.post(new Runnable() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatsFragment$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$initViewPager$2();
                    }
                });
            }
            Log.i("chatLifeCycle", "F viewPager init. setCurrentItem at position " + this.gotoTabPosition);
            if (this.adapter == null) {
                MultiChatsAdapter multiChatsAdapter = new MultiChatsAdapter();
                this.adapter = multiChatsAdapter;
                this.viewPager.setAdapter(multiChatsAdapter);
                this.viewPager.setOffscreenPageLimit(ChatManager.getInstance().chatTopics.size());
                new TabLayoutMediator(this.tabLayout, this.viewPager, new TabLayoutMediator.TabConfigurationStrategy() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatsFragment$$ExternalSyntheticLambda1
                    @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
                    public final void onConfigureTab(TabLayout.Tab tab, int i) {
                        this.f$0.lambda$initViewPager$3(tab, i);
                    }
                }).attach();
                this.tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
                observeChatLiveData();
                return;
            }
            int i = this.gotoTabPosition;
            if (i == this.currentTabPosition) {
                setViewHolderArgs(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViewPager$2() {
        this.viewPager.setCurrentItem(this.gotoTabPosition, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViewPager$3(TabLayout.Tab tab, int i) {
        tab.setCustomView(initTabView(i));
    }

    private void initTutorial() {
        ChatTutorialDialog chatTutorialDialog = new ChatTutorialDialog(this.mContext);
        chatTutorialDialog.show();
        ((Window) Objects.requireNonNull(chatTutorialDialog.getWindow())).setLayout(-1, -1);
        chatTutorialDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        ((ViewGroup) chatTutorialDialog.getWindow().getDecorView()).getChildAt(0).startAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.chat_fade_in_selection));
    }

    private View initTabView(int i) throws Resources.NotFoundException {
        View viewInflate = View.inflate(getContext(), R.layout.fragment_chat_tab_layout, null);
        setTabViewResources(viewInflate, i, TabState.INIT);
        return viewInflate;
    }

    private void setTabViewResources(View view, int i, TabState tabState) throws Resources.NotFoundException {
        try {
            ChatTopic chatTopic = ChatManager.getInstance().chatTopics.get(i);
            int color = requireContext().getResources().getColor(R.color.grey6C6E70);
            String iconUnselected = chatTopic.getIconUnselected();
            String iconSelected = chatTopic.getIconSelected();
            TextView textView = (TextView) view.findViewById(R.id.tabText);
            ImageView imageView = (ImageView) view.findViewById(R.id.iconUnSelected);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.iconSelected);
            int i2 = AnonymousClass2.$SwitchMap$com$channel2$mobile$ui$Chats$controllers$ChatsFragment$TabState[tabState.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        return;
                    }
                    setUnSelected(imageView2, imageView, textView, color);
                    return;
                } else {
                    if (chatTopic.getColor() != null && chatTopic.getColor().length() > 0) {
                        color = Color.parseColor("#" + chatTopic.getColor());
                    }
                    setSelected(imageView2, imageView, textView, color);
                    return;
                }
            }
            textView.setText(chatTopic.getTopicTitle());
            if (iconSelected != null && iconSelected.length() != 0) {
                imageView2.setVisibility(0);
                imageView.setVisibility(0);
                Glide.with(view).load(iconUnselected).into(imageView);
                Glide.with(view).load(iconSelected).into(imageView2);
            } else {
                imageView2.setVisibility(8);
                imageView.setVisibility(8);
            }
            if (chatTopic.getTopicId() == this.currentTopicId) {
                if (chatTopic.getColor() != null && chatTopic.getColor().length() > 0) {
                    color = Color.parseColor("#" + chatTopic.getColor());
                }
                setSelected(imageView2, imageView, textView, color);
                return;
            }
            setUnSelected(imageView2, imageView, textView, color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.channel2.mobile.ui.Chats.controllers.ChatsFragment$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$channel2$mobile$ui$Chats$controllers$ChatsFragment$TabState;

        static {
            int[] iArr = new int[TabState.values().length];
            $SwitchMap$com$channel2$mobile$ui$Chats$controllers$ChatsFragment$TabState = iArr;
            try {
                iArr[TabState.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$controllers$ChatsFragment$TabState[TabState.SELECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$Chats$controllers$ChatsFragment$TabState[TabState.UNSELECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void setSelected(ImageView imageView, ImageView imageView2, TextView textView, int i) {
        if (imageView.getVisibility() != 8) {
            imageView.setVisibility(0);
        }
        if (imageView2.getVisibility() != 8) {
            imageView2.setVisibility(4);
        }
        this.tabLayout.setSelectedTabIndicatorColor(i);
        textView.setTextColor(i);
    }

    private void setUnSelected(ImageView imageView, ImageView imageView2, TextView textView, int i) {
        if (imageView.getVisibility() != 8) {
            imageView.setVisibility(4);
        }
        if (imageView2.getVisibility() != 8) {
            imageView2.setVisibility(0);
        }
        textView.setTextColor(i);
    }

    private void setHeader() {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.mContext).inflate(R.layout.header_lobby, (ViewGroup) null, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) frameLayout.findViewById(R.id.logoContainer);
        Tab tab = MainConfig.main.getFooter().tabs.get(getTabId());
        if (tab.headerTitle.length() > 0) {
            TextView textView = (TextView) frameLayout.findViewById(R.id.channelTitle);
            ImageView imageView = (ImageView) frameLayout.findViewById(R.id.appLogo);
            imageView.setVisibility(0);
            float f = getContext().getApplicationContext().getResources().getDisplayMetrics().density;
            imageView.getLayoutParams().height = (int) (18.0f * f);
            ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams()).setMargins(0, (int) (f * 11.0f), 0, 0);
            imageView.setImageResource(R.drawable.chat_header_icon);
            textView.setVisibility(0);
            textView.setText(tab.headerTitle);
        }
        constraintLayout.setVisibility(0);
        this.mainActivity.navigationManager.addHeader(getTabId(), frameLayout);
    }

    public void setArgs(Bundle bundle) {
        this.args = bundle;
        if (bundle.containsKey("partner")) {
            this.partner = bundle.getString("partner");
        }
        if (bundle.containsKey("messageId")) {
            this.highlightMessageId = bundle.getLong("messageId");
        }
        if (bundle.containsKey("autoId")) {
            this.autoId = bundle.getLong("autoId");
        }
        if (bundle.containsKey("goToTopicId")) {
            this.goToTopicId = bundle.getInt("goToTopicId");
        }
    }

    private void setViewHolderArgs(int i) {
        ChatViewHolder chatViewHolder = (ChatViewHolder) ((RecyclerView) this.viewPager.getChildAt(0)).findViewHolderForAdapterPosition(i);
        if (chatViewHolder != null) {
            Bundle bundle = this.args;
            if (bundle != null) {
                chatViewHolder.processArgs(bundle);
                this.args = null;
            }
            chatViewHolder.onResume();
        }
    }

    void observeChatLiveData() {
        if (ChatManager.getInstance().chatItemsArray != null) {
            ChatManager.getInstance().chatItemsArray.getNewItemObservation().observe(this, new Observer() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatsFragment$$ExternalSyntheticLambda2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$observeChatLiveData$4((ChatReportItem) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observeChatLiveData$4(ChatReportItem chatReportItem) {
        ViewPager2 viewPager2;
        ChatViewHolder chatViewHolder;
        Log.d("ReportersChatFragment", "F observe live data onChanged");
        Log.i("chatLifeCycle", "F observe live data onChanged");
        if (chatReportItem.getTopicID() != this.currentTopicId || (viewPager2 = this.viewPager) == null || (chatViewHolder = (ChatViewHolder) ((RecyclerView) viewPager2.getChildAt(0)).findViewHolderForAdapterPosition(this.currentTabPosition)) == null) {
            return;
        }
        chatViewHolder.onNewLiveDataMessage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isToday(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return Calendar.getInstance().get(5) == calendar.get(5);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void reportAnalyticsEvents(Activity activity) {
        super.reportAnalyticsEvents(activity);
        Log.i("chatLifeCycle", "F reportAnalyticsEvents");
        if (MainActivity.isBackFromGallery || this.isReportLock) {
            return;
        }
        try {
            Log.i("reportMetrics", "Chat");
            Uri uri = Uri.parse("https://www.mako.co.il/reportersChat");
            if (MainConfig.main.getCurrentBooleanParam(activity.getResources().getString(R.string.idx_enable))) {
                this.pageTracker = MainActivity.permutive.trackPage(new EventProperties.Builder().build(), "/reportersChat", uri, null);
                Log.i("permutive", "F permutive_On");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.isReportLock = true;
    }

    @Override // com.channel2.mobile.ui.Chats.Helpers.ChatCallbacks
    public void onInflateGallery(Bundle bundle) {
        Intent intent = new Intent(this.mContext, (Class<?>) PlayerActivity.class);
        intent.putExtras(bundle);
        this.mainActivity.resultActivityLauncher.launch(intent);
        this.exitToGallery = true;
    }

    @Override // com.channel2.mobile.ui.Chats.Helpers.ChatCallbacks
    public void onInflateShare(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "שתפו עם", PendingIntent.getBroadcast(this.mContext, 0, new Intent(this.mContext, (Class<?>) CustomBroadcastReceiver.class), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL).getIntentSender()));
    }

    public void setExitToGallery(boolean z) {
        this.exitToGallery = z;
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabSelected(TabLayout.Tab tab) throws Resources.NotFoundException {
        setTabViewResources(tab.getCustomView(), tab.getPosition(), TabState.SELECTED);
        this.currentTabPosition = tab.getPosition();
        int topicId = ChatManager.getInstance().chatTopics.get(tab.getPosition()).getTopicId();
        this.currentTopicId = topicId;
        this.goToTopicId = topicId;
        if (this.args == null) {
            Bundle bundle = new Bundle();
            this.args = bundle;
            bundle.putString("partner", this.partner);
            this.args.putBoolean("isFromTab", true);
        }
        Log.i("checkReportsForChats", "" + this.args);
        setViewHolderArgs(this.currentTabPosition);
        Log.i("chatLifeCycle", "F onTabSelected at position " + this.currentTabPosition + " and topcId is " + this.currentTopicId);
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabUnselected(TabLayout.Tab tab) throws Resources.NotFoundException {
        setTabViewResources(tab.getCustomView(), tab.getPosition(), TabState.UNSELECTED);
        ChatViewHolder chatViewHolder = (ChatViewHolder) ((RecyclerView) this.viewPager.getChildAt(0)).findViewHolderForAdapterPosition(tab.getPosition());
        if (chatViewHolder != null) {
            chatViewHolder.onPause();
        }
        this.partner = ChatManager.getInstance().chatTopics.get(tab.getPosition()).getTopicName() + "Tab";
        StringBuilder sb = new StringBuilder("F onTabUnselected at position ");
        sb.append(tab.getPosition());
        Log.i("chatLifeCycle", sb.toString());
    }

    private class MultiChatsAdapter extends RecyclerView.Adapter<ChatViewHolder> {
        private MultiChatsAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ChatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_chat_vh, (ViewGroup) null);
            viewGroup2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            return new ChatViewHolder(viewGroup2, viewGroup.getContext(), ChatsFragment.this.mainActivity, ChatsFragment.this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ChatViewHolder chatViewHolder, int i) {
            if (ChatsFragment.this.gotoTabPosition == 0 && i == 0 && ChatsFragment.this.args != null) {
                chatViewHolder.processArgs(ChatsFragment.this.args);
                ChatsFragment.this.args = null;
            }
            chatViewHolder.setViewResources(i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ChatManager.getInstance().chatTopics.size();
        }
    }
}
