package com.channel2.mobile.ui.lobby.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.Chats.controllers.ChatManager;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.advertising.InterstitialManager;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.configs.models.Tab;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.header.CheckHeaderStyle;
import com.channel2.mobile.ui.header.CustomHeaderManager;
import com.channel2.mobile.ui.header.Header;
import com.channel2.mobile.ui.header.HeaderVisibility;
import com.channel2.mobile.ui.helpers.CustomVideoControlsHandler;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragment;
import com.channel2.mobile.ui.lobby.models.ads.AdArrivedListener;
import com.channel2.mobile.ui.lobby.models.ads.AdSponsored;
import com.channel2.mobile.ui.lobby.models.ads.DFP;
import com.channel2.mobile.ui.lobby.models.ads.LobbyAd;
import com.channel2.mobile.ui.lobby.models.ads.LobbyAdViewHolder;
import com.channel2.mobile.ui.lobby.models.firstReport.LobbyFirstReport;
import com.channel2.mobile.ui.lobby.models.items.FetchItems;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.ItemManager;
import com.channel2.mobile.ui.lobby.models.items.ItemType;
import com.channel2.mobile.ui.lobby.models.items.ItemsArray;
import com.channel2.mobile.ui.lobby.models.items.LinkItem;
import com.channel2.mobile.ui.lobby.models.sections.SectionChatReportLobby;
import com.channel2.mobile.ui.lobby.models.sections.SectionHorizontalPaging;
import com.channel2.mobile.ui.lobby.models.teasers.LobbyTeaser;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemMediumVideo;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemRegularVideo;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemSpecialVideo;
import com.channel2.mobile.ui.lobby.views.CustomVideoControls;
import com.channel2.mobile.ui.network.ApiService;
import com.channel2.mobile.ui.pushNotification.DeepLinkManager;
import com.channel2.mobile.ui.reports.TransparentWebView;
import com.channel2.mobile.ui.routing.RoutingManager;
import com.channel2.mobile.ui.splash.MyApplication;
import com.channel2.mobile.ui.webView.controllers.WebViewFragment;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mako.kscore.ksmeasurements.helpers.PlayerState;
import com.mako.kscore.ksplayer.helpers.ActionReport;
import com.mako.kscore.ksplayer.helpers.KillPlayerListener;
import com.mako.kscore.ksplayer.helpers.PlayerCallback;
import com.mako.kscore.ksplayer.helpers.PlayerCastState;
import com.mako.kscore.ksplayer.helpers.ReasonReport;
import com.mako.kscore.ksplayer.helpers.managers.CastManager;
import com.mako.kscore.ksplayer.helpers.managers.KsPlayerManager;
import com.mako.kscore.ksplayer.model.KsPlayItem;
import com.mako.kscore.ksplayer.view.RootControl;
import com.outbrain.OBSDK.Entities.OBRecommendation;
import com.outbrain.OBSDK.Outbrain;
import com.outbrain.OBSDK.SmartFeed.OBSmartFeedAdvancedListener;
import com.outbrain.OBSDK.SmartFeed.OBSmartFeedListener;
import com.permutive.android.EventProperties;
import com.permutive.android.PageTracker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LobbyFragment extends CustomFragment implements OBSmartFeedListener, OBSmartFeedAdvancedListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int NOTCH_PADDING = 22;
    private static RecyclerView.RecycledViewPool viewPool;
    private boolean canChangeHeaderColor;
    private boolean canPlayVideo;
    String channelVcmId;
    private CustomVideoControls controls;
    private int currentVideoSection;
    private CustomHeaderManager customHeaderManager;
    private String deepLink;
    private boolean didReturnedFromInterstitial;
    String friendlyUrl;
    private FrameLayout headerView;
    private FrameLayout header_lobby;
    public boolean isHorzontalViewOnScreen;
    private boolean isLobbyWithAds;
    private ImageButton liveButton;
    private ItemManager lobbyItemManager;
    private ItemsArray lobbyItemsArray;
    public LobbyTeaser lobbyTeaser;
    FrameLayout lobby_fragment_container;
    public LinearLayoutManager mLayoutManager;
    private MainActivity mainActivity;
    private PageTracker pageTracker;
    private CustomRecyclerViewAdapter productAdapter;
    private String pushId;
    private RecyclerView recyclerView;
    public int scrollY;
    private ImageView transparentHeaderAppLogo;
    private ImageButton transparentHeaderLiveButton;
    private String vcmId;
    private FrameLayout videoContainer;
    private ImageView videoCoverView;
    private View view;
    private MutableLiveData<Boolean> didFetchData = new MutableLiveData<>();
    public boolean isInterstitialFinished = false;
    private int returningCount = 1;
    private boolean lobbyRefresh = false;

    static /* synthetic */ void lambda$onLiveButtonClicked$5() {
    }

    static /* synthetic */ void lambda$onPause$0() {
    }

    static /* synthetic */ void lambda$onStop$1() {
    }

    static /* synthetic */ void lambda$play$6() {
    }

    static /* synthetic */ void lambda$refresh$7() {
    }

    @Override // com.outbrain.OBSDK.SmartFeed.OBSmartFeedAdvancedListener
    public boolean isVideoCurrentlyPlaying() {
        return false;
    }

    @Override // com.outbrain.OBSDK.SmartFeed.OBSmartFeedAdvancedListener
    public void onOutbrainRecsReceived(ArrayList<OBRecommendation> arrayList, String str) {
    }

    public void postDataFetching(JSONObject jSONObject, LobbyFirstReport lobbyFirstReport, boolean z, FetchItems.ResponseHandler responseHandler) {
    }

    @Override // com.outbrain.OBSDK.SmartFeed.OBSmartFeedAdvancedListener
    public void smartfeedIsReadyWithRecs() {
    }

    public static LobbyFragment newInstance(boolean z, String str, int i, String str2, String str3, boolean z2, Boolean bool) {
        Log.i("InterstitialManager", "lobbyFragment newInstance isInterstitialFinished = " + bool);
        LobbyFragment lobbyFragment = new LobbyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("vcmId", str);
        bundle.putString(SDKConstants.PARAM_DEEP_LINK, str2);
        bundle.putString("pushId", str3);
        bundle.putInt("id", i);
        bundle.putBoolean("isHomePage", z);
        bundle.putBoolean("isLobbyWithAds", z2);
        bundle.putBoolean("isInterstitialFinished", bool.booleanValue());
        lobbyFragment.setArguments(bundle);
        return lobbyFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        Log.i("LobbyLifeCycle", "onCreate");
        Log.i("InterstitialManager", "lobbyFragment onCreate()");
        super.onCreate(bundle);
        Log.d("maavaron", "lobby fragment created after " + (new Date().getTime() - InterstitialManager.timeTestStart));
        if (getArguments() != null) {
            this.vcmId = getArguments().getString("vcmId");
            this.deepLink = getArguments().getString(SDKConstants.PARAM_DEEP_LINK, "");
            this.pushId = getArguments().getString("pushId");
            setTabId(getArguments().getInt("id"));
            this.isHomePage = getArguments().getBoolean("isHomePage");
            this.isLobbyWithAds = getArguments().getBoolean("isLobbyWithAds");
            this.isInterstitialFinished = getArguments().getBoolean("isInterstitialFinished");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Log.i("LobbyLifeCycle", "onAttach");
        super.onAttach(context);
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            this.mainActivity = mainActivity;
            if (mainActivity.adContainer != null) {
                this.mainActivity.adContainer.setVisibility(8);
            }
        }
        this.didFetchData.setValue(false);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.view == null) {
            this.view = layoutInflater.inflate(R.layout.fragment_lobby, viewGroup, false);
            init();
        }
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        Log.i("LobbyLifeCycle", "onInflate");
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.i("LobbyLifeCycle", "onAttachFragment");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Log.i("LobbyLifeCycle", "onViewCreated");
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Log.i("LobbyLifeCycle", "onActivityCreated");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        Log.i("LobbyLifeCycle", "onViewStateRestored");
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.i("LobbyLifeCycle", "onSaveInstanceState");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        PageTracker pageTracker;
        super.onDestroyView();
        Log.i("LobbyLifeCycle", "onDestroyView");
        if (!MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) || (pageTracker = this.pageTracker) == null) {
            return;
        }
        try {
            pageTracker.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("permutive", "permutive_Off");
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Log.i("LobbyLifeCycle", "onStart");
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        PageTracker pageTracker;
        Log.i("LobbyLifeCycle", "onResume");
        super.onResume();
        if (!MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) || (pageTracker = this.pageTracker) == null) {
            return;
        }
        pageTracker.resume();
        Log.i("permutive", "permutive_resume");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        Log.i("LobbyLifeCycle", "onDetach");
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        Log.i("LobbyLifeCycle", "onDestroy");
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        PageTracker pageTracker;
        Log.i("LobbyLifeCycle", "onPause");
        super.onPause();
        if (MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) && (pageTracker = this.pageTracker) != null) {
            pageTracker.pause();
            Log.i("permutive", "permutive_pause");
        }
        if (((MyApplication) requireActivity().getApplication()).isBackground() || this.videoContainer == null) {
            return;
        }
        KsPlayerManager.INSTANCE.killPlayer(this.videoContainer, ActionReport.pause, getIsLive() ? ReasonReport.user : ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$$ExternalSyntheticLambda10
            @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
            public final void onKilled() {
                LobbyFragment.lambda$onPause$0();
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        Log.i("LobbyLifeCycle", "onStop");
        super.onStop();
        if (getActivity() != null && (getActivity().getApplication() instanceof MyApplication) && ((MyApplication) getActivity().getApplication()).isBackground()) {
            ImageView imageView = this.videoCoverView;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (this.videoContainer != null) {
                KsPlayerManager.INSTANCE.killPlayer(this.videoContainer, ActionReport.pause, getIsLive() ? ReasonReport.appBackground : ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$$ExternalSyntheticLambda2
                    @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                    public final void onKilled() {
                        LobbyFragment.lambda$onStop$1();
                    }
                });
            }
        }
    }

    private void init() {
        setFragmentContainerId(R.id.lobby_fragment_container);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        this.transparentHeaderAppLogo = (ImageView) this.view.findViewById(R.id.transparentHeaderAppLogo);
        this.lobby_fragment_container = (FrameLayout) this.view.findViewById(R.id.lobby_fragment_container);
        this.header_lobby = (FrameLayout) layoutInflaterFrom.inflate(R.layout.header_lobby, (ViewGroup) null, false);
        try {
            this.mainActivity.navigationManager.addHeader(getTabId(), this.header_lobby);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.recyclerView);
        FrameLayout frameLayout = (FrameLayout) this.view.findViewById(R.id.headerView);
        this.headerView = frameLayout;
        this.transparentHeaderLiveButton = (ImageButton) frameLayout.findViewById(R.id.live);
        ((ConstraintLayout.LayoutParams) this.headerView.getLayoutParams()).height = this.mainActivity.getStatusBarHeight() + Utils.convertDipToPixels(getContext(), 40.0f);
        this.headerView.requestLayout();
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        viewPool = recycledViewPool;
        this.recyclerView.setRecycledViewPool(recycledViewPool);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.mLayoutManager = linearLayoutManager;
        linearLayoutManager.setItemPrefetchEnabled(true);
        this.recyclerView.setLayoutManager(this.mLayoutManager);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setItemViewCacheSize(20);
        this.recyclerView.setItemAnimator(null);
        this.lobbyItemManager = new ItemManager();
        this.productAdapter = new CustomRecyclerViewAdapter(this.lobbyItemManager, new AnonymousClass1());
        LobbyTeaser lobbyTeaser = new LobbyTeaser();
        lobbyTeaser.setLobbyItemType(this.isHomePage ? ItemType.placeholderBig : ItemType.placeholderSmall);
        lobbyTeaser.setTeaserVcmId("skeleton");
        Log.d("checkManyKills", "init: submitList:");
        this.productAdapter.submitList(new ArrayList(Collections.singletonList(lobbyTeaser)));
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (i2 > 0 && !LobbyFragment.this.canPlayVideo) {
                    LobbyFragment.this.canPlayVideo = true;
                }
                if (LobbyFragment.this.mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    LobbyFragment.this.scrollY = 0;
                }
                LobbyFragment.this.scrollY += i2;
                if (LobbyFragment.this.canChangeHeaderColor && LobbyFragment.this.mainActivity != null && LobbyFragment.this.customHeaderManager != null) {
                    LobbyFragment.this.customHeaderManager.setHeaderStyle(LobbyFragment.this.scrollY, i2);
                }
                RootControl rootControl = LobbyFragment.this.getRootControl();
                if (rootControl != null && rootControl.getPlayerExists() && i2 > 0) {
                    rootControl.mute();
                }
                if (LobbyFragment.this.mainActivity != null) {
                    LobbyFragment.this.mainActivity.setSwipeToRefresh(Boolean.valueOf(LobbyFragment.this.mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0));
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                Log.i("onScrollStateChanged", "newState = " + i);
                if (i == 0) {
                    LobbyFragment.this.playVideoSectionIfOnScreen();
                    if (LobbyFragment.this.getContext() == null || LobbyFragment.this.lobbyItemsArray == null || LobbyFragment.this.lobbyItemsArray.lobbyItems.getValue() == null || LobbyFragment.this.mLayoutManager.findLastVisibleItemPosition() != LobbyFragment.this.lobbyItemsArray.lobbyItems.getValue().size() - 1) {
                        return;
                    }
                    Log.d("checkOBCall", "call from SCROLL_STATE_IDLE: LastVisibleItemPosition = " + LobbyFragment.this.mLayoutManager.findLastVisibleItemPosition() + " | size = " + LobbyFragment.this.lobbyItemsArray.lobbyItems.getValue().size());
                    LobbyFragment.this.lobbyItemsArray.fetchMoreOutBrainFooterItems(LobbyFragment.this.getContext(), LobbyFragment.this.friendlyUrl, LobbyFragment.this.channelVcmId, LobbyFragment.this.isHomePage);
                }
            }
        });
        this.recyclerView.setAdapter(this.productAdapter);
        Header header = MainConfig.main.getHeader();
        if (this.isHomePage && header.getHeaderPosition() == HeaderVisibility.INVISIBLE) {
            setHeader();
        }
        fetchData(true, null);
        resumeFragment(this.mainActivity);
        LobbyAd.instance.setAdArrivedListener(new AdArrivedListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$$ExternalSyntheticLambda9
            @Override // com.channel2.mobile.ui.lobby.models.ads.AdArrivedListener
            public final void onAdReady(LobbyAd lobbyAd) {
                this.f$0.lambda$init$2(lobbyAd);
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment.3
            @Override // java.lang.Runnable
            public void run() {
                LobbyFragment.this.mainActivity.loadTutorial();
            }
        }, 200L);
    }

    /* renamed from: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$1, reason: invalid class name */
    class AnonymousClass1 implements LobbyFragmentHandler {
        static /* synthetic */ void lambda$onClick$0() {
        }

        static /* synthetic */ void lambda$onPauseVideo$1() {
        }

        static /* synthetic */ void lambda$onViewDetachedFromWindow$2() {
        }

        static /* synthetic */ void lambda$onViewDetachedFromWindow$3() {
        }

        static /* synthetic */ void lambda$onViewDetachedFromWindow$4() {
        }

        static /* synthetic */ void lambda$onViewDetachedFromWindow$5() {
        }

        @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
        public void enableVerticleScroll(boolean z) {
        }

        @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
        public void onListRefreshed() {
        }

        @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
        public void readMore(int i) {
        }

        AnonymousClass1() {
        }

        @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
        public void onClick(Item item, Bundle bundle) {
            LobbyFragment.this.canPlayVideo = false;
            if (LobbyFragment.this.videoContainer != null) {
                Log.d("checkManyKills", "onClick: ");
                KsPlayerManager.INSTANCE.killPlayer(LobbyFragment.this.videoContainer, ActionReport.pause, LobbyFragment.this.getIsLive() ? ReasonReport.user : ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$1$$ExternalSyntheticLambda5
                    @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                    public final void onKilled() {
                        LobbyFragment.AnonymousClass1.lambda$onClick$0();
                    }
                });
            }
            if (item instanceof LobbyTeaser) {
                LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
                if (lobbyTeaser.getLobbyItemType() == ItemType.videoItem || lobbyTeaser.getOpenItemIn().equals("player")) {
                    lobbyTeaser.setClickUrl("app://playVideo?url=not&videoId=" + lobbyTeaser.getVideoVcmId() + "&channelId=" + lobbyTeaser.getVideoChannelId() + "&galleryChannelId=" + lobbyTeaser.getVideoGalleryChannelId());
                    FirebaseAnalytics.getInstance(LobbyFragment.this.mainActivity).logEvent("News_Video", null);
                    RoutingManager.goToNextScreen(R.id.lobby_fragment_container, lobbyTeaser, LobbyFragment.this.getTabId(), LobbyFragment.this.mainActivity, LobbyFragment.this.controls);
                    return;
                }
                if (item.getLobbyItemType().name().startsWith("placeholder")) {
                    return;
                }
                RoutingManager.goToNextScreen(R.id.lobby_fragment_container, item, LobbyFragment.this.getTabId(), LobbyFragment.this.mainActivity, null);
                return;
            }
            if (item instanceof ChatReportItem) {
                ChatReportItem chatReportItem = (ChatReportItem) item;
                chatReportItem.setClickUrl("messageId=" + chatReportItem.getMessageID());
                RoutingManager.goToNextScreen(R.id.lobby_fragment_container, chatReportItem, LobbyFragment.this.getTabId(), bundle, LobbyFragment.this.mainActivity, LobbyFragment.this.controls);
                return;
            }
            if (item.getLobbyItemType().name().startsWith("placeholder")) {
                return;
            }
            RoutingManager.goToNextScreen(R.id.lobby_fragment_container, item, LobbyFragment.this.getTabId(), LobbyFragment.this.mainActivity, null);
        }

        @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
        public void onPlayVideo(LobbyTeaser lobbyTeaser, FrameLayout frameLayout, ImageView imageView) {
            imageView.setVisibility(0);
            LobbyFragment.this.videoContainer = frameLayout;
            LobbyFragment.this.videoCoverView = imageView;
            LobbyFragment.this.lobbyTeaser = lobbyTeaser;
            Log.d("checkHP", "onPlayVideo: ");
            Log.d("checkManyKills", "play - onPlayVideo: | canPlayVideo = " + LobbyFragment.this.canPlayVideo);
            LobbyFragment.this.play(lobbyTeaser.getSectionId());
        }

        @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
        public void onPauseVideo(LobbyTeaser lobbyTeaser, FrameLayout frameLayout, ImageView imageView) {
            imageView.setVisibility(0);
            if (frameLayout != null) {
                Log.d("checkManyKills", "onPauseVideo: ");
                KsPlayerManager.INSTANCE.killPlayer(frameLayout, ActionReport.pause, LobbyFragment.this.getIsLive() ? ReasonReport.scroll : ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$1$$ExternalSyntheticLambda0
                    @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                    public final void onKilled() {
                        LobbyFragment.AnonymousClass1.lambda$onPauseVideo$1();
                    }
                });
            }
        }

        @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
        public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof TeaserMainItemMediumVideo) {
                TeaserMainItemMediumVideo teaserMainItemMediumVideo = (TeaserMainItemMediumVideo) viewHolder;
                LobbyFragment.this.videoCoverView = teaserMainItemMediumVideo.image;
                LobbyFragment.this.videoCoverView.setVisibility(0);
                onPlayVideo((LobbyTeaser) teaserMainItemMediumVideo.lobbyItem, teaserMainItemMediumVideo.videoContainer, teaserMainItemMediumVideo.image);
            } else if (viewHolder instanceof SectionChatReportLobby) {
                ChatManager.getInstance().setChatOnScreen(true, ((SectionChatReportLobby) viewHolder).topicID);
            } else if (viewHolder instanceof SectionHorizontalPaging) {
                LobbyFragment.this.canPlayVideo = true;
                ((SectionHorizontalPaging) viewHolder).initViewPager();
                LobbyFragment.this.isHorzontalViewOnScreen = true;
            } else if (viewHolder instanceof TeaserMainItemRegularVideo) {
                TeaserMainItemRegularVideo teaserMainItemRegularVideo = (TeaserMainItemRegularVideo) viewHolder;
                LobbyFragment.this.videoCoverView = teaserMainItemRegularVideo.image;
                LobbyFragment.this.videoCoverView.setVisibility(0);
                onPlayVideo((LobbyTeaser) teaserMainItemRegularVideo.lobbyItem, teaserMainItemRegularVideo.videoContainer, teaserMainItemRegularVideo.image);
            } else if (viewHolder instanceof TeaserMainItemSpecialVideo) {
                TeaserMainItemSpecialVideo teaserMainItemSpecialVideo = (TeaserMainItemSpecialVideo) viewHolder;
                LobbyFragment.this.videoCoverView = teaserMainItemSpecialVideo.image;
                LobbyFragment.this.videoCoverView.setVisibility(0);
                onPlayVideo((LobbyTeaser) teaserMainItemSpecialVideo.lobbyItem, teaserMainItemSpecialVideo.videoContainer, teaserMainItemSpecialVideo.image);
            } else if (viewHolder instanceof AdSponsored) {
                try {
                    ApiService.getStringAsync(((AdSponsored) viewHolder).getLobbyAd().getImpUrl(), LobbyFragment.this.getContext(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (viewHolder instanceof CustomRecyclerViewHolder) {
                CustomRecyclerViewHolder customRecyclerViewHolder = (CustomRecyclerViewHolder) viewHolder;
                if (!(customRecyclerViewHolder.lobbyItem instanceof LobbyTeaser) || !((LobbyTeaser) customRecyclerViewHolder.lobbyItem).isOutBrain() || LobbyFragment.this.getContext() == null || LobbyFragment.this.lobbyItemsArray == null || LobbyFragment.this.lobbyItemsArray.lobbyItems.getValue() == null || LobbyFragment.this.mLayoutManager.findLastVisibleItemPosition() != LobbyFragment.this.lobbyItemsArray.lobbyItems.getValue().size() - 2) {
                    return;
                }
                Log.d("checkOBCall", "call from onViewAttachedToWindow: LastVisibleItemPosition = " + LobbyFragment.this.mLayoutManager.findLastVisibleItemPosition() + " | size = " + LobbyFragment.this.lobbyItemsArray.lobbyItems.getValue().size());
                LobbyFragment.this.lobbyItemsArray.fetchMoreOutBrainFooterItems(LobbyFragment.this.getContext(), LobbyFragment.this.friendlyUrl, LobbyFragment.this.channelVcmId, LobbyFragment.this.isHomePage);
            }
        }

        @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
        public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
            if (LobbyFragment.this.videoContainer != null) {
                LobbyFragment.this.mainActivity.getWindow().clearFlags(128);
                if (viewHolder instanceof TeaserMainItemMediumVideo) {
                    TeaserMainItemMediumVideo teaserMainItemMediumVideo = (TeaserMainItemMediumVideo) viewHolder;
                    if (LobbyFragment.this.getCurrentVideoSection() == teaserMainItemMediumVideo.lobbyItem.getSectionId()) {
                        teaserMainItemMediumVideo.image.setVisibility(0);
                        if (LobbyFragment.this.videoContainer != null) {
                            KsPlayerManager.INSTANCE.killPlayer(LobbyFragment.this.videoContainer, ActionReport.pause, LobbyFragment.this.getIsLive() ? ReasonReport.scroll : ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$1$$ExternalSyntheticLambda1
                                @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                                public final void onKilled() {
                                    LobbyFragment.AnonymousClass1.lambda$onViewDetachedFromWindow$2();
                                }
                            });
                        }
                        LobbyFragment.this.view.setKeepScreenOn(false);
                        LobbyFragment.this.videoContainer = null;
                        LobbyFragment.this.videoCoverView = null;
                        return;
                    }
                    return;
                }
                if (viewHolder instanceof TeaserMainItemRegularVideo) {
                    TeaserMainItemRegularVideo teaserMainItemRegularVideo = (TeaserMainItemRegularVideo) viewHolder;
                    if (LobbyFragment.this.getCurrentVideoSection() == teaserMainItemRegularVideo.lobbyItem.getSectionId()) {
                        teaserMainItemRegularVideo.image.setVisibility(0);
                        if (LobbyFragment.this.videoContainer != null) {
                            Log.d("checkManyKills", "onViewDetachedFromWindow: TeaserMainItemRegularVideo");
                            KsPlayerManager.INSTANCE.killPlayer(LobbyFragment.this.videoContainer, ActionReport.pause, LobbyFragment.this.getIsLive() ? ReasonReport.scroll : ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$1$$ExternalSyntheticLambda2
                                @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                                public final void onKilled() {
                                    LobbyFragment.AnonymousClass1.lambda$onViewDetachedFromWindow$3();
                                }
                            });
                        }
                        LobbyFragment.this.view.setKeepScreenOn(false);
                        LobbyFragment.this.videoContainer = null;
                        LobbyFragment.this.videoCoverView = null;
                        return;
                    }
                    return;
                }
                if (viewHolder instanceof TeaserMainItemSpecialVideo) {
                    TeaserMainItemSpecialVideo teaserMainItemSpecialVideo = (TeaserMainItemSpecialVideo) viewHolder;
                    if (LobbyFragment.this.getCurrentVideoSection() == teaserMainItemSpecialVideo.lobbyItem.getSectionId()) {
                        teaserMainItemSpecialVideo.image.setVisibility(0);
                        if (LobbyFragment.this.videoContainer != null) {
                            Log.d("checkManyKills", "onViewDetachedFromWindow: TeaserMainItemSpecialVideo");
                            KsPlayerManager.INSTANCE.killPlayer(LobbyFragment.this.videoContainer, ActionReport.pause, LobbyFragment.this.getIsLive() ? ReasonReport.scroll : ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$1$$ExternalSyntheticLambda3
                                @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                                public final void onKilled() {
                                    LobbyFragment.AnonymousClass1.lambda$onViewDetachedFromWindow$4();
                                }
                            });
                        }
                        LobbyFragment.this.view.setKeepScreenOn(false);
                        LobbyFragment.this.videoContainer = null;
                        LobbyFragment.this.videoCoverView = null;
                        return;
                    }
                    return;
                }
                if (viewHolder instanceof SectionHorizontalPaging) {
                    SectionHorizontalPaging sectionHorizontalPaging = (SectionHorizontalPaging) viewHolder;
                    sectionHorizontalPaging.adapter = null;
                    if (LobbyFragment.this.videoContainer != null) {
                        Log.d("checkManyKills", "onViewDetachedFromWindow: SectionHorizontalPaging");
                        KsPlayerManager.INSTANCE.killPlayer(LobbyFragment.this.videoContainer, ActionReport.pause, LobbyFragment.this.getIsLive() ? ReasonReport.scroll : ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$1$$ExternalSyntheticLambda4
                            @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                            public final void onKilled() {
                                LobbyFragment.AnonymousClass1.lambda$onViewDetachedFromWindow$5();
                            }
                        });
                    }
                    LobbyFragment.this.view.setKeepScreenOn(false);
                    sectionHorizontalPaging.isPlaying = false;
                    LobbyFragment.this.isHorzontalViewOnScreen = false;
                    LobbyFragment.this.videoContainer = null;
                    LobbyFragment.this.videoCoverView = null;
                    return;
                }
                return;
            }
            if (viewHolder instanceof SectionChatReportLobby) {
                int topicID = ((SectionChatReportLobby) viewHolder).getTopicID();
                ChatManager.getInstance().setChatOnScreen(false, topicID);
                if (ChatManager.getInstance().chatItemsArray.getChatItems(false, topicID).size() > 0) {
                    ChatManager.getInstance().chatItemsArray.getChatItems(false, topicID).get(0).setTyping(false);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$2(LobbyAd lobbyAd) {
        RecyclerView.ViewHolder viewHolderFindViewHolderForLayoutPosition;
        if (this.productAdapter != null) {
            for (int iFindFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition(); iFindFirstVisibleItemPosition <= this.mLayoutManager.findLastVisibleItemPosition(); iFindFirstVisibleItemPosition++) {
                if (this.mLayoutManager.findViewByPosition(iFindFirstVisibleItemPosition) != null && (viewHolderFindViewHolderForLayoutPosition = this.recyclerView.findViewHolderForLayoutPosition(iFindFirstVisibleItemPosition)) != null && (viewHolderFindViewHolderForLayoutPosition instanceof LobbyAdViewHolder)) {
                    LobbyAd lobbyAd2 = ((LobbyAdViewHolder) viewHolderFindViewHolderForLayoutPosition).getLobbyAd();
                    if (lobbyAd2 != null && lobbyAd2.getIndex() == lobbyAd.getIndex()) {
                        this.productAdapter.notifyItemChanged(iFindFirstVisibleItemPosition);
                    }
                    Log.d("adReady", "adReady" + iFindFirstVisibleItemPosition);
                }
            }
        }
    }

    private void fetchData(final boolean z, final FetchItems.ResponseHandler responseHandler) {
        new FetchItems(getContext(), MainConfig.main.getCurrentSource("channelDataApi").replace("%CHANNEL_ID%", this.vcmId), this.isHomePage, new FetchItems.ResponseHandler() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment.4
            @Override // com.channel2.mobile.ui.lobby.models.items.FetchItems.ResponseHandler
            public /* synthetic */ void onSuccess() {
                FetchItems.ResponseHandler.CC.$default$onSuccess(this);
            }

            @Override // com.channel2.mobile.ui.lobby.models.items.FetchItems.ResponseHandler
            public void onSuccess(JSONObject jSONObject, LobbyFirstReport lobbyFirstReport) throws JSONException {
                LobbyFragment.this.setDataForReportLobbyMetrics(jSONObject);
                LobbyFragment.this.setHeaderTitle(jSONObject);
                LobbyFragment.this.lobbyItemsArray = new ItemsArray(jSONObject, LobbyFragment.this.getContext(), LobbyFragment.this.friendlyUrl, LobbyFragment.this.channelVcmId, LobbyFragment.this.isHomePage);
                if (LobbyFragment.this.isHomePage) {
                    if (MainConfig.main.getHeader().getHeaderPosition() == HeaderVisibility.INVISIBLE) {
                        LobbyFragment lobbyFragment = LobbyFragment.this;
                        lobbyFragment.setLiveButton(lobbyFragment.lobbyItemsArray.isLive(), LobbyFragment.this.transparentHeaderLiveButton);
                    } else {
                        LobbyFragment lobbyFragment2 = LobbyFragment.this;
                        lobbyFragment2.setLiveButton(lobbyFragment2.lobbyItemsArray.isLive(), LobbyFragment.this.liveButton);
                    }
                    if (!z || LobbyFragment.this.isInterstitialFinished) {
                        LobbyFragment.this.canPlayVideo = true;
                        LobbyFragment.this.showData(z);
                        FetchItems.ResponseHandler responseHandler2 = responseHandler;
                        if (responseHandler2 != null) {
                            responseHandler2.onSuccess();
                        }
                    }
                } else if (MainConfig.main.getCurrentBooleanParam("interstitial_lobbies_enable")) {
                    LobbyFragment.this.loadInterstitial(jSONObject, z);
                } else {
                    LobbyFragment.this.canPlayVideo = true;
                    LobbyFragment.this.showData(z);
                    FetchItems.ResponseHandler responseHandler3 = responseHandler;
                    if (responseHandler3 != null) {
                        responseHandler3.onSuccess();
                    }
                    new Handler(Looper.getMainLooper()).post(new LobbyFragment$$ExternalSyntheticLambda8(LobbyFragment.this));
                }
                String currentParam = MainConfig.main.getCurrentParam("firstReportDurationInMin");
                if (currentParam.length() > 0 && lobbyFirstReport != null) {
                    long j = Long.parseLong(currentParam) * 1000 * 60;
                    if (LobbyFragment.this.isHomePage && j + lobbyFirstReport.getCreation_time() > System.currentTimeMillis()) {
                        LobbyFragment.this.lobbyItemsArray.add(0, lobbyFirstReport);
                    }
                }
                if (LobbyFragment.this.mainActivity != null) {
                    LobbyFragment.this.mainActivity.setSwipeToRefresh(Boolean.valueOf(LobbyFragment.this.mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0));
                }
            }

            @Override // com.channel2.mobile.ui.lobby.models.items.FetchItems.ResponseHandler
            public void onFailure() {
                Toast.makeText(LobbyFragment.this.mainActivity, "נראה שאין חיבור לאינטרנט, מומלץ לבדוק את הגדרות הרשת", 1).show();
            }
        });
    }

    public void showData(boolean z) throws JSONException {
        Log.d("maavaron", "showData");
        if (z) {
            String str = this.deepLink;
            if (str != null && str.length() > 0) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", this.deepLink);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RoutingManager.goToNextScreen(R.id.lobby_fragment_container, new LinkItem(jSONObject), getTabId(), this.mainActivity, null);
                this.deepLink = "";
                this.pushId = "";
            }
        } else {
            this.mainActivity.setRefreshEnd();
        }
        setData();
        if (!z) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$showData$3();
                }
            }, 300L);
            new Handler(Looper.getMainLooper()).postDelayed(new LobbyFragment$$ExternalSyntheticLambda7(this), 1500L);
        }
        if (this.mainActivity != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$showData$4();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showData$3() {
        if (this.lobbyTeaser != null) {
            Log.d("checkManyKills", "play - onListRefreshed: | canPlayVideo = " + this.canPlayVideo);
            play(this.lobbyTeaser.getSectionId());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showData$4() {
        this.mainActivity.initChatInnerPush();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLiveButton(boolean z, final ImageView imageView) {
        if (imageView != null) {
            if (z) {
                imageView.setVisibility(0);
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) throws JSONException {
                        if (imageView == LobbyFragment.this.transparentHeaderLiveButton) {
                            if (LobbyFragment.this.headerView == null || LobbyFragment.this.headerView.getAlpha() <= 0.0d) {
                                return;
                            }
                            LobbyFragment.this.onLiveButtonClicked();
                            return;
                        }
                        LobbyFragment.this.onLiveButtonClicked();
                    }
                });
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLiveButtonClicked() throws JSONException {
        if (this.videoContainer != null) {
            Log.d("checkManyKills", "onLiveButtonClicked:");
            KsPlayerManager.INSTANCE.killPlayer(this.videoContainer, ActionReport.pause, getIsLive() ? ReasonReport.user : ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$$ExternalSyntheticLambda3
                @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                public final void onKilled() {
                    LobbyFragment.lambda$onLiveButtonClicked$5();
                }
            });
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", "app://playVideo?url=not&videoId=1e2258089b67f510VgnVCM2000002a0c10acRCRD&channelId=56dda65207e41210VgnVCM100000290c10acRCRD&galleryChannelId=1e2258089b67f510VgnVCM2000002a0c10acRCRD");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FirebaseAnalytics.getInstance(this.mainActivity).logEvent("News_Live_Button", null);
        LinkItem linkItem = new LinkItem(jSONObject);
        linkItem.setMako_ref_comp("header");
        RoutingManager.goToNextScreen(getFragmentContainerId(), linkItem, getTabId(), this.mainActivity, null);
    }

    private void setHeader() {
        FrameLayout frameLayout = this.headerView;
        if (frameLayout == null || frameLayout.getContext() == null) {
            return;
        }
        ViewCompat.setOnApplyWindowInsetsListener((ConstraintLayout) this.headerView.findViewById(R.id.logoContainer), new OnApplyWindowInsetsListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment.6
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                if (windowInsetsCompat.getDisplayCutout() != null) {
                    ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin = Utils.convertDipToPixels(LobbyFragment.this.getContext(), 22.0f);
                    ((ViewGroup.MarginLayoutParams) LobbyFragment.this.headerView.findViewById(R.id.live).getLayoutParams()).topMargin = Utils.convertDipToPixels(LobbyFragment.this.getContext(), 22.0f);
                }
                return windowInsetsCompat.consumeSystemWindowInsets();
            }
        });
        this.canChangeHeaderColor = true;
        this.customHeaderManager = new CustomHeaderManager(this.headerView);
        displayCustomHeader();
    }

    public void hideCustomHeader() {
        FrameLayout frameLayout = this.headerView;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
    }

    public void displayCustomHeader() {
        if (this.headerView != null && CheckHeaderStyle.isTransparentHeader && this.isHomePage) {
            this.headerView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void play(int i) {
        try {
            if (this.videoContainer == null || !this.canPlayVideo || ((MyApplication) this.mainActivity.getApplication()).getIsPiP() || CastManager.INSTANCE.getSharedInstance(requireContext()).getCastState().getValue() != PlayerCastState.none) {
                return;
            }
            this.mainActivity.getWindow().addFlags(128);
            this.view.setKeepScreenOn(true);
            setCurrentVideoSection(i);
            Log.i("Lobby player", "play");
            KsPlayerManager.INSTANCE.killPlayer(this.videoContainer, ActionReport.pause, ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$$ExternalSyntheticLambda4
                @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                public final void onKilled() {
                    LobbyFragment.lambda$play$6();
                }
            });
            KsPlayItem ksPlayItem = new KsPlayItem();
            ksPlayItem.setPlayerState(PlayerState.teaser);
            ksPlayItem.setLoop(true);
            ksPlayItem.setMute(true);
            ksPlayItem.setWithAds(false);
            ksPlayItem.setUseController(false);
            if (this.lobbyTeaser.getImage() != null && this.lobbyTeaser.getImage().length() > 0) {
                ksPlayItem.setImage(this.lobbyTeaser.getImage());
            }
            if (this.lobbyTeaser.getTrailer() != null && this.lobbyTeaser.getTrailer().length() > 0) {
                ksPlayItem.setUrl(this.lobbyTeaser.getTrailer());
            } else if (this.lobbyTeaser.getVideoVcmId() != null && this.lobbyTeaser.getVideoVcmId().length() > 0 && this.lobbyTeaser.getVideoChannelId() != null && this.lobbyTeaser.getVideoChannelId().length() > 0 && this.lobbyTeaser.getVideoGalleryChannelId() != null && this.lobbyTeaser.getVideoGalleryChannelId().length() > 0) {
                ksPlayItem.setVideoVcmId(this.lobbyTeaser.getVideoVcmId());
                ksPlayItem.setChannelId(this.lobbyTeaser.getVideoChannelId());
                ksPlayItem.setGalleryChannelId(this.lobbyTeaser.getVideoGalleryChannelId());
                ksPlayItem.setLoop(true);
                ksPlayItem.setMute(true);
                ksPlayItem.setWithAds(false);
                ksPlayItem.setPlayWhenReady(false);
                ksPlayItem.setPlayerState(PlayerState.teaser);
                ksPlayItem.getPlayerPageParams().setMako_video_state("teaser");
                ksPlayItem.setItemType(com.mako.kscore.ksplayer.helpers.ItemType.TEASER);
            }
            KsPlayerManager.INSTANCE.play(ksPlayItem, this.videoContainer, new AnonymousClass7());
        } catch (Exception unused) {
        }
    }

    /* renamed from: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$7, reason: invalid class name */
    class AnonymousClass7 implements PlayerCallback {
        static /* synthetic */ void lambda$onInitialized$0() {
        }

        static /* synthetic */ void lambda$onStopWatchingTime$2() {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onCancel() {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onContentPlayingChanged(boolean z) {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onControlsVisibilityChange(int i) {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onCreditTime() {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onError(int i, boolean z) {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onPauseForAds() {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onPlayerClick(Bundle bundle) {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onPlayerFinish(boolean z) {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onProgress(int i) {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onProgress(long j) {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onReset() {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onResumeAfterAds() {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void showLoader() {
        }

        AnonymousClass7() {
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onInitialized() {
            Log.i("Lobby player", "onInitialized");
            if (LobbyFragment.this.getContext() != null) {
                LobbyFragment lobbyFragment = LobbyFragment.this;
                lobbyFragment.controls = (CustomVideoControls) lobbyFragment.view.findViewById(R.id.customVideoControls);
                if (LobbyFragment.this.controls != null) {
                    ViewCompat.setTransitionName(LobbyFragment.this.controls, "100_image");
                    LobbyFragment.this.controls.registerCallback(new CustomVideoControlsHandler() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$7$$ExternalSyntheticLambda2
                        @Override // com.channel2.mobile.ui.helpers.CustomVideoControlsHandler
                        public final void onClick(boolean z) throws JSONException {
                            this.f$0.lambda$onInitialized$1(z);
                        }
                    });
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onInitialized$1(boolean z) throws JSONException {
            RootControl rootControl = LobbyFragment.this.getRootControl();
            if (rootControl != null && rootControl.getPlayerExists()) {
                if (LobbyFragment.this.videoCoverView != null) {
                    LobbyFragment.this.videoCoverView.setVisibility(0);
                }
                if (LobbyFragment.this.videoContainer != null) {
                    Log.d("checkManyKills", "onInitialized:");
                    KsPlayerManager.INSTANCE.killPlayer(LobbyFragment.this.videoContainer, ActionReport.pause, (rootControl.isPlaying() && rootControl.isLive()) ? ReasonReport.user : ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$7$$ExternalSyntheticLambda1
                        @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                        public final void onKilled() {
                            LobbyFragment.AnonymousClass7.lambda$onInitialized$0();
                        }
                    });
                }
            }
            if (z) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", "app://playVideo?url=not&videoId=" + LobbyFragment.this.lobbyTeaser.getVideoVcmId() + "&channelId=" + LobbyFragment.this.lobbyTeaser.getVideoChannelId() + "&galleryChannelId=" + LobbyFragment.this.lobbyTeaser.getVideoGalleryChannelId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                LinkItem linkItem = new LinkItem(jSONObject);
                linkItem.setMako_ref_comp("slider_Video");
                RoutingManager.goToNextScreen(R.id.lobby_fragment_container, linkItem, LobbyFragment.this.getTabId(), LobbyFragment.this.mainActivity, LobbyFragment.this.controls);
                return;
            }
            if (LobbyFragment.this.lobbyTeaser.getLobbyItemType().name().startsWith("placeholder")) {
                return;
            }
            RoutingManager.goToNextScreen(R.id.lobby_fragment_container, LobbyFragment.this.lobbyTeaser, LobbyFragment.this.getTabId(), LobbyFragment.this.mainActivity, null);
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onPlayerReady() {
            Log.i("Lobby player", "onPlayerReady");
            RootControl rootControl = LobbyFragment.this.getRootControl();
            if (!LobbyFragment.this.canPlayVideo || rootControl == null || !rootControl.getPlayerExists() || rootControl.isPlaying()) {
                return;
            }
            rootControl.play(ActionReport.none, ReasonReport.none);
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onStopWatchingTime() {
            Log.d("shouldStop", " lobbyfragment onStopWatchingTime");
            if (LobbyFragment.this.videoCoverView != null) {
                LobbyFragment.this.videoCoverView.setVisibility(0);
            }
            if (LobbyFragment.this.videoContainer != null) {
                if (LobbyFragment.this.getRootControl() != null) {
                    Log.d("checkManyKills", "onStopWatchingTime:");
                    KsPlayerManager.INSTANCE.killPlayer(LobbyFragment.this.videoContainer, ActionReport.pause, ReasonReport.userIdle, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$7$$ExternalSyntheticLambda0
                        @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                        public final void onKilled() {
                            LobbyFragment.AnonymousClass7.lambda$onStopWatchingTime$2();
                        }
                    });
                }
                if (LobbyFragment.this.controls == null) {
                    CustomVideoControls customVideoControls = (CustomVideoControls) LobbyFragment.this.view.findViewById(R.id.customVideoControls);
                    if (customVideoControls != null) {
                        customVideoControls.removeControls();
                        return;
                    }
                    return;
                }
                LobbyFragment.this.controls.removeControls();
            }
        }

        @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
        public void onPosterLoaded() {
            if (LobbyFragment.this.videoCoverView != null) {
                LobbyFragment.this.videoCoverView.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHeaderTitle(JSONObject jSONObject) {
        ImageView imageView = (ImageView) this.header_lobby.findViewById(R.id.appLogo);
        this.liveButton = (ImageButton) this.header_lobby.findViewById(R.id.live);
        TextView textView = (TextView) this.header_lobby.findViewById(R.id.channelTitle);
        ConstraintLayout constraintLayout = (ConstraintLayout) this.header_lobby.findViewById(R.id.logoContainer);
        String strOptString = jSONObject.optString("channelName");
        Tab tab = MainConfig.main.getFooter().tabs.get(getTabId());
        if (!this.isHomePage && ((strOptString != null && strOptString.length() > 0) || tab.headerTitle.length() > 0)) {
            this.headerView.setVisibility(8);
            imageView.setImageResource(R.drawable.app_logo_lines);
            this.liveButton.setVisibility(8);
            textView.setVisibility(0);
            try {
                textView.setPadding(0, 0, ((int) getResources().getDisplayMetrics().density) * 2, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tab.headerTitle.length() > 0) {
                textView.setText(tab.headerTitle);
            } else {
                textView.setText(strOptString);
            }
        } else {
            imageView.setVisibility(0);
            if (CheckHeaderStyle.isTransparentHeader && this.isHomePage) {
                this.headerView.setVisibility(0);
            }
            Header header = MainConfig.main.getHeader();
            if (header.mobileLogoImage != null) {
                imageView.setImageBitmap(header.mobileLogoImage);
                this.transparentHeaderAppLogo.setImageBitmap(header.mobileLogoImage);
            } else {
                imageView.setImageResource(R.drawable.app_logo_n12);
                this.transparentHeaderAppLogo.setImageResource(R.drawable.app_logo_n12);
            }
            textView.setVisibility(8);
        }
        constraintLayout.setVisibility(0);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void refresh() {
        super.refresh();
        this.mLayoutManager.setRecycleChildrenOnDetach(true);
        ImageView imageView = this.videoCoverView;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        if (this.videoContainer != null) {
            Log.d("checkManyKills", "refresh:");
            KsPlayerManager.INSTANCE.killPlayer(this.videoContainer, ActionReport.pause, getIsLive() ? ReasonReport.scroll : ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$$ExternalSyntheticLambda0
                @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                public final void onKilled() {
                    LobbyFragment.lambda$refresh$7();
                }
            });
        }
        fetchData(false, null);
        for (int iFindFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition(); iFindFirstVisibleItemPosition <= this.mLayoutManager.findLastVisibleItemPosition(); iFindFirstVisibleItemPosition++) {
            RecyclerView.ViewHolder viewHolderFindViewHolderForLayoutPosition = this.recyclerView.findViewHolderForLayoutPosition(iFindFirstVisibleItemPosition);
            if (viewHolderFindViewHolderForLayoutPosition instanceof SectionChatReportLobby) {
                ((SectionChatReportLobby) viewHolderFindViewHolderForLayoutPosition).notifyAdapter();
            }
            Log.d("Lobby chat position is ", "" + iFindFirstVisibleItemPosition);
            Log.d("position is ", "" + iFindFirstVisibleItemPosition);
        }
    }

    public void setData() {
        Log.d("refreshOB", "setData");
        try {
            if (this.lobbyItemsArray.lobbyItems.hasActiveObservers()) {
                return;
            }
            this.lobbyItemsArray.lobbyItems.observe(this, new Observer() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$$ExternalSyntheticLambda6
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$setData$8((ArrayList) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setData$8(ArrayList arrayList) {
        if (arrayList != null) {
            Log.d("checkManyKills", "setData: submitList:");
            this.productAdapter.submitList(new ArrayList(arrayList));
            this.mLayoutManager.setRecycleChildrenOnDetach(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDataForReportLobbyMetrics(JSONObject jSONObject) {
        this.friendlyUrl = jSONObject.optString("friendlyUrl");
        this.channelVcmId = jSONObject.optString("channelVcmId");
        this.didFetchData.setValue(true);
    }

    private void reportLobbyMetrics(Activity activity) {
        if (this.didReturnedFromInterstitial) {
            this.didReturnedFromInterstitial = false;
            return;
        }
        Log.i("reportMetrics", "Lobby");
        Log.i("LobbyLifeCycle", "reportLobbyMetrics");
        Log.i("LobbyLifeCycle", "" + this.friendlyUrl);
        String currentSource = MainConfig.main.getCurrentSource("reportMetrics");
        if (this.friendlyUrl.length() > 0) {
            FirebaseAnalytics.getInstance(this.mainActivity).setCurrentScreen(this.mainActivity, this.friendlyUrl, null);
            Uri uri = Uri.parse("https://www.mako.co.il" + this.friendlyUrl);
            if (MainConfig.main.getCurrentBooleanParam(activity.getResources().getString(R.string.idx_enable))) {
                this.pageTracker = MainActivity.permutive.trackPage(new EventProperties.Builder().build(), this.friendlyUrl, uri, null);
                Log.i("permutive", "permutive_On");
            }
        }
        if (this.pushId.length() > 0 && this.deepLink.length() == 0) {
            currentSource = currentSource.replace("%PUSH_TRIGGER%", "utm_source=news12_App&utm_campaign=Push_Notification&utm_medium=" + this.pushId).replace("%PUSH_ID%", "utm_source=news12_App&utm_campaign=Push_Notification&utm_medium=" + this.pushId);
        }
        String strReplace = currentSource.replace("%GUID%", this.channelVcmId).replace("%VCM_ID%", this.channelVcmId).replace("%CONTENT_TYPE%", this.isHomePage ? "Lobby" : "Vertical").replace("%FRIENDLY_URL%", this.friendlyUrl).replace("%REFRESH%", String.valueOf(this.lobbyRefresh));
        String str = this.returningCount > 0 ? "BackPV" : "";
        Log.i("reportMetrics", "Lobby mako_ref_comp = ".concat(str));
        TransparentWebView.report(getContext(), strReplace.replace("%MAKO_REF_COMP%", str));
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void fragmentOnResume(Activity activity) {
        Log.i("LobbyLifeCycle", "fragmentOnResume");
        super.fragmentOnResume(activity);
        resumeFragment(activity);
        try {
            Boolean bool = false;
            Boolean bool2 = false;
            if (isAdded() && ((MainActivity) requireActivity()).fromBack && this.isHomePage && ((MainActivity) requireActivity()).lastFragment.getClass() == WebViewFragment.class && ((WebViewFragment) ((MainActivity) requireActivity()).lastFragment).isArticleView()) {
                bool = true;
            }
            boolean currentBooleanParam = MainConfig.main.getCurrentBooleanParam("interstitial_articleBack_enable");
            if (bool.booleanValue() && currentBooleanParam && InterstitialManager.getInstance().hasInterstitialReady()) {
                bool2 = true;
                InterstitialManager.getInstance().createInterstitial(new DFP(MainConfig.main.getCurrentParam("homePageIu") + MainConfig.main.getCurrentParam("secondPreloadUI")), activity, "", true);
            }
            if (isAdded()) {
                ((MainActivity) requireActivity()).fromBack = false;
            }
            CustomRecyclerViewAdapter customRecyclerViewAdapter = this.productAdapter;
            if (customRecyclerViewAdapter == null || customRecyclerViewAdapter.getCurrentList().size() <= 1) {
                return;
            }
            Log.d("listAdapter", "hpReturningCount  = " + this.returningCount + " hp size = " + this.productAdapter.getCurrentList().size());
            boolean zShouldRefreshLobby = shouldRefreshLobby();
            this.lobbyRefresh = zShouldRefreshLobby;
            if (zShouldRefreshLobby && !bool2.booleanValue()) {
                this.returningCount = 1;
                refresh();
            } else {
                this.returningCount++;
                new Handler(Looper.getMainLooper()).postDelayed(new LobbyFragment$$ExternalSyntheticLambda7(this), 500L);
                new Handler(Looper.getMainLooper()).postDelayed(new LobbyFragment$$ExternalSyntheticLambda8(this), 500L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resumeFragment(Activity activity) {
        try {
            if (this.mainActivity == null) {
                this.mainActivity = (MainActivity) activity;
            }
            MainActivity mainActivity = this.mainActivity;
            if (mainActivity != null) {
                mainActivity.exitFullScreen(this);
                Header header = MainConfig.main.getHeader();
                if (this.isHomePage && header.getHeaderPosition() == HeaderVisibility.INVISIBLE) {
                    this.mainActivity.displayHeader(HeaderVisibility.INVISIBLE);
                } else {
                    this.mainActivity.toolbarParams.setScrollFlags(0);
                }
                if (this.mainActivity.adContainer != null) {
                    this.mainActivity.adContainer.setVisibility(8);
                }
                if (this.mainActivity.bottomNavigationView != null) {
                    this.mainActivity.bottomNavigationView.selectItem(getTabId());
                }
                this.mainActivity.setRequestedOrientation(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playTeaserVideo() {
        try {
            if (CastManager.INSTANCE.getSharedInstance(requireContext()).getCastState().getValue() == PlayerCastState.none) {
                RootControl rootControl = getRootControl();
                if (this.videoContainer != null && rootControl != null && this.lobbyTeaser != null) {
                    Rect rect = new Rect();
                    this.videoContainer.getLocalVisibleRect(rect);
                    int i = rect.bottom - rect.top;
                    int height = this.videoContainer.getHeight();
                    if (i != 0 && i > height * 0.5d && this.canPlayVideo) {
                        Log.d("checkHP", "playTeaserVideo: ");
                        if (rootControl.isLive() || rootControl.getPlayerIsNull()) {
                            play(this.lobbyTeaser.getSectionId());
                        } else {
                            rootControl.play(ActionReport.none, ReasonReport.none);
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void reportAnalyticsEvents(final Activity activity) {
        super.reportAnalyticsEvents(activity);
        MutableLiveData<Boolean> mutableLiveData = this.didFetchData;
        if (mutableLiveData != null && mutableLiveData.getValue() != null && this.didFetchData.getValue().booleanValue()) {
            reportLobbyMetrics(activity);
            return;
        }
        MutableLiveData<Boolean> mutableLiveData2 = this.didFetchData;
        if (mutableLiveData2 == null || mutableLiveData2.hasActiveObservers()) {
            return;
        }
        this.didFetchData.observe(this, new Observer() { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$reportAnalyticsEvents$9(activity, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reportAnalyticsEvents$9(Activity activity, Boolean bool) {
        if (bool.booleanValue()) {
            reportLobbyMetrics(activity);
            this.didFetchData.removeObservers(this);
        }
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void scrollTop() {
        super.scrollTop();
        if (this.mLayoutManager == null || this.recyclerView == null) {
            return;
        }
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(this.recyclerView.getContext()) { // from class: com.channel2.mobile.ui.lobby.controllers.LobbyFragment.8
            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 10.0f / displayMetrics.densityDpi;
            }
        };
        linearSmoothScroller.setTargetPosition(0);
        this.mLayoutManager.startSmoothScroll(linearSmoothScroller);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void onInterstitialAd() throws JSONException {
        super.onInterstitialAd();
        Log.i("onInterstitialAd", "Lobby");
        if (this.isHomePage) {
            this.didReturnedFromInterstitial = true;
        }
        showData(true);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void onInterstitialAdClosed() {
        super.onInterstitialAdClosed();
        Log.i("chatLifeCycle", "onInterstitialAdClosed");
        this.isInterstitialFinished = true;
        this.canPlayVideo = true;
        FrameLayout frameLayout = this.videoContainer;
        if ((frameLayout == null || frameLayout.getChildCount() == 0) && this.lobbyTeaser != null) {
            Log.d("checkManyKills", "play - onInterstitialAdClosed");
            play(this.lobbyTeaser.getSectionId());
        }
    }

    private DFP setDFP(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObject3;
        try {
            if (jSONObject.has("url")) {
                jSONObject3 = new JSONObject(jSONObject.getString("url"));
            } else {
                jSONObject3 = new JSONObject(jSONObject.getString("makoCatDFP"));
            }
            return new DFP(jSONObject3.optString("iu"), jSONObject3.optJSONObject("cust_params"), jSONObject2.getString("channelVcmId"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadInterstitial(JSONObject jSONObject, boolean z) throws JSONException {
        DFP dfp = setDFP(jSONObject, jSONObject);
        if (dfp.getIu().length() > 0 && getContext() != null && !DeepLinkManager.getInstance().isOpenedByLink().booleanValue()) {
            Log.i("InterstitialManager", "lobby load interstitial");
            String str = this.friendlyUrl;
            this.mainActivity.loadInterstitial(dfp, (str == null || str.length() <= 0) ? MainConfig.main.getCurrentParam("dfpBaseContentURL") : Utils.getDesktopUrl(this.friendlyUrl), InterstitialManager.ShowPreload.NOT_SET);
        } else {
            this.canPlayVideo = true;
            DeepLinkManager.getInstance().setOpenedByLink(false);
            showData(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCurrentVideoSection() {
        return this.currentVideoSection;
    }

    private void setCurrentVideoSection(int i) {
        this.currentVideoSection = i;
    }

    public static RecyclerView.RecycledViewPool getViewPool() {
        return viewPool;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playVideoSectionIfOnScreen() {
        LinearLayoutManager linearLayoutManager;
        try {
            if (CastManager.INSTANCE.getSharedInstance(requireContext()).getCastState().getValue() != PlayerCastState.none || (linearLayoutManager = this.mLayoutManager) == null) {
                return;
            }
            for (int iFindFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition(); iFindFirstVisibleItemPosition <= this.mLayoutManager.findLastVisibleItemPosition(); iFindFirstVisibleItemPosition++) {
                RecyclerView.ViewHolder viewHolderFindViewHolderForLayoutPosition = this.recyclerView.findViewHolderForLayoutPosition(iFindFirstVisibleItemPosition);
                if (viewHolderFindViewHolderForLayoutPosition instanceof SectionHorizontalPaging) {
                    ((SectionHorizontalPaging) viewHolderFindViewHolderForLayoutPosition).onScrollStateIdle();
                }
                Log.d("Lobby chat position is ", "" + iFindFirstVisibleItemPosition);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.outbrain.OBSDK.OBClickListener
    public void userTappedOnRecommendation(OBRecommendation oBRecommendation) {
        LobbyTeaser lobbyTeaser = new LobbyTeaser();
        lobbyTeaser.setClickUrl(Outbrain.getUrl(oBRecommendation).replace("https://www.mako.co.il/", "https://mobileapp.mako.co.il/"));
        RoutingManager.goToNextScreen(R.id.lobby_fragment_container, lobbyTeaser, getTabId(), this.mainActivity, this.controls);
    }

    @Override // com.outbrain.OBSDK.OBClickListener
    public void userTappedOnAdChoicesIcon(String str) {
        LobbyTeaser lobbyTeaser = new LobbyTeaser();
        lobbyTeaser.setClickUrl(str);
        RoutingManager.goToNextScreen(R.id.lobby_fragment_container, lobbyTeaser, getTabId(), this.mainActivity, this.controls);
    }

    @Override // com.outbrain.OBSDK.OBClickListener
    public void userTappedOnAboutOutbrain() {
        String outbrainAboutURL = Outbrain.getOutbrainAboutURL();
        LobbyTeaser lobbyTeaser = new LobbyTeaser();
        lobbyTeaser.setClickUrl(outbrainAboutURL);
        RoutingManager.goToNextScreen(R.id.lobby_fragment_container, lobbyTeaser, getTabId(), this.mainActivity, this.controls);
    }

    @Override // com.outbrain.OBSDK.OBClickListener
    public void userTappedOnVideo(String str) {
        LobbyTeaser lobbyTeaser = new LobbyTeaser();
        lobbyTeaser.setClickUrl(str);
        RoutingManager.goToNextScreen(R.id.lobby_fragment_container, lobbyTeaser, getTabId(), this.mainActivity, this.controls);
    }

    private boolean shouldRefreshLobby() throws NumberFormatException {
        String stringFromPreferences;
        int i;
        if (isOutBrainOnScreen() || MainConfig.appData == null || MainConfig.main.getCurrentIntParam("returningRate") > this.returningCount) {
            return false;
        }
        try {
            stringFromPreferences = Utils.getStringFromPreferences(requireContext(), requireContext().getResources().getString(R.string.userPercentNumber));
        } catch (Resources.NotFoundException unused) {
            stringFromPreferences = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        Log.d("listAdapter", "user percent string = " + stringFromPreferences);
        if (stringFromPreferences.length() > 0) {
            try {
                i = Integer.parseInt(stringFromPreferences);
            } catch (Exception unused2) {
            }
        } else {
            i = -1;
        }
        Log.d("listAdapter", "user percent number = " + i + " and config percentage = " + (MainConfig.main.getCurrentIntParam("refreshPercent") * 10));
        if (i < 0 || i >= MainConfig.main.getCurrentIntParam("refreshPercent") * 10) {
            return false;
        }
        Log.d("listAdapter", "HP refreshed");
        return true;
    }

    public boolean isOutBrainOnScreen() {
        for (int iFindFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition(); iFindFirstVisibleItemPosition <= this.mLayoutManager.findLastVisibleItemPosition(); iFindFirstVisibleItemPosition++) {
            if (this.mLayoutManager.findViewByPosition(iFindFirstVisibleItemPosition) != null) {
                RecyclerView.ViewHolder viewHolderFindViewHolderForLayoutPosition = this.recyclerView.findViewHolderForLayoutPosition(iFindFirstVisibleItemPosition);
                if (viewHolderFindViewHolderForLayoutPosition instanceof CustomRecyclerViewHolder) {
                    Item item = ((CustomRecyclerViewHolder) viewHolderFindViewHolderForLayoutPosition).lobbyItem;
                    if (item.getLobbyItemType() == ItemType.outbrain || item.getLobbyItemType() == ItemType.outbrainfooter || ((item instanceof LobbyTeaser) && ((LobbyTeaser) item).isOutBrain())) {
                        return true;
                    }
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    public void setCanPlayVideo() {
        this.canPlayVideo = true;
    }

    public RootControl getRootControl() {
        FrameLayout frameLayout = this.videoContainer;
        if (frameLayout != null) {
            return (RootControl) frameLayout.findViewById(R.id.root_control);
        }
        return null;
    }

    public boolean getIsLive() {
        RootControl rootControl = getRootControl();
        return rootControl != null && rootControl.getPlayerExists() && rootControl.isPlaying() && rootControl.isLive();
    }
}
