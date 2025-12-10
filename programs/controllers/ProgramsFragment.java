package com.channel2.mobile.ui.programs.controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.configs.models.Tab;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.models.items.LinkItem;
import com.channel2.mobile.ui.programs.controllers.ListAdapter;
import com.channel2.mobile.ui.programs.models.FetchProgramsData;
import com.channel2.mobile.ui.programs.models.ProgramsComponent;
import com.channel2.mobile.ui.programs.models.ProgramsItem;
import com.channel2.mobile.ui.programs.views.ProgramsViewHolder;
import com.channel2.mobile.ui.reports.TransparentWebView;
import com.channel2.mobile.ui.routing.RoutingManager;
import com.channel2.mobile.ui.splash.MyApplication;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mako.kscore.ksmeasurements.helpers.PlayerState;
import com.mako.kscore.ksmeasurements.model.item.ErrorItem$$ExternalSyntheticBackport0;
import com.mako.kscore.ksplayer.helpers.ActionReport;
import com.mako.kscore.ksplayer.helpers.KillPlayerListener;
import com.mako.kscore.ksplayer.helpers.PlayerCallback;
import com.mako.kscore.ksplayer.helpers.PlayerCastState;
import com.mako.kscore.ksplayer.helpers.ReasonReport;
import com.mako.kscore.ksplayer.helpers.managers.CastManager;
import com.mako.kscore.ksplayer.helpers.managers.KsPlayerManager;
import com.mako.kscore.ksplayer.model.KsPlayItem;
import com.mako.kscore.ksplayer.view.RootControl;
import com.permutive.android.EventProperties;
import com.permutive.android.PageTracker;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ProgramsFragment extends CustomFragment implements PlayerCallback {
    private String ChannelVcmId;
    private String FriendlyUrl;
    private String action;
    private int bodyPadding;
    private ProgramsItem finalItem;
    private ProgramsViewHolder finalTmp;
    private int headerPadding;
    private int id;
    private boolean isPlaceholderVisible;
    private ArrayList<ProgramsComponent> items;
    private int lastSelectedComponent;
    private Handler liveDelayHandler;
    private Runnable liveDelayRunnable;
    private MainActivity mainActivity;
    private PageTracker pageTracker;
    private FrameLayout placeholder;
    private ValueAnimator placeholderAnimation;
    private String programCode;
    private RootControl rootControl;
    private int screenWidth;
    private int selectedComponent;
    private HashMap<Integer, Integer> selectedItemMap;
    private ProgramsViewHolder selectedVideoItem;
    private ViewGroup videoContainer;
    private Handler videoDelayHandler;
    private Runnable videoDelayRunnable;
    private View view;
    private ViewPager viewPagerBody;
    private ViewPager viewPagerHeader;
    private boolean isFirst = true;
    private boolean canSelect = true;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Subject<Integer> observable = PublishSubject.create();
    private MutableLiveData<Boolean> didFetchData = new MutableLiveData<>();

    static /* synthetic */ void lambda$onPause$0() {
    }

    static /* synthetic */ void lambda$onStop$1() {
    }

    static /* synthetic */ void lambda$onVideoItemClicked$9() {
    }

    static /* synthetic */ void lambda$play$8() {
    }

    static /* synthetic */ void lambda$setVideoItem$7() {
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
    public void onPlayerReady() {
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
    public void onStopWatchingTime() {
    }

    @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
    public void showLoader() {
    }

    public static ProgramsFragment newInstance(String str, int i, String str2) {
        ProgramsFragment programsFragment = new ProgramsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        bundle.putString("programCode", str2);
        bundle.putString("action", str);
        programsFragment.setArguments(bundle);
        return programsFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
            this.action = getArguments().getString("action");
            this.programCode = getArguments().getString("programCode");
            setTabId(this.id);
        }
        try {
            setArguments(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
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
            try {
                View viewInflate = layoutInflater.inflate(R.layout.fragment_programs, viewGroup, false);
                this.view = viewInflate;
                if (viewInflate != null) {
                    init();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        PageTracker pageTracker;
        ViewPager viewPager;
        super.onResume();
        ViewPager viewPager2 = this.viewPagerBody;
        if (viewPager2 != null && viewPager2.getAdapter() != null && (viewPager = this.viewPagerHeader) != null && viewPager.getAdapter() != null) {
            this.viewPagerBody.getAdapter().notifyDataSetChanged();
            this.viewPagerHeader.getAdapter().notifyDataSetChanged();
        }
        if (!MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) || (pageTracker = this.pageTracker) == null) {
            return;
        }
        pageTracker.resume();
        Log.i("permutive", "permutive_resume");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        PageTracker pageTracker;
        if (MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) && (pageTracker = this.pageTracker) != null) {
            try {
                pageTracker.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("permutive", "permutive_Off");
        }
        super.onDestroyView();
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        ViewGroup viewGroup;
        PageTracker pageTracker;
        super.onPause();
        lambda$init$6();
        if (MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) && (pageTracker = this.pageTracker) != null) {
            pageTracker.pause();
            Log.i("permutive", "permutive_pause");
        }
        if (((MyApplication) requireActivity().getApplication()).isBackground() || (viewGroup = this.videoContainer) == null) {
            return;
        }
        KsPlayerManager.INSTANCE.killPlayer(viewGroup, ActionReport.pause, ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda4
            @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
            public final void onKilled() {
                ProgramsFragment.lambda$onPause$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: pause, reason: merged with bridge method [inline-methods] */
    public void lambda$init$6() {
        ProgramsViewHolder programsViewHolder = this.selectedVideoItem;
        if (programsViewHolder != null) {
            ((FrameLayout) programsViewHolder.itemView.findViewById(R.id.videoContainer)).removeAllViews();
            this.selectedVideoItem.imageView.setImageAlpha(255);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (getActivity() != null && (getActivity().getApplication() instanceof MyApplication) && ((MyApplication) getActivity().getApplication()).isBackground()) {
            ReasonReport reasonReport = ReasonReport.none;
            RootControl rootControl = this.rootControl;
            if (rootControl != null && rootControl.getPlayerExists() && this.rootControl.isPlaying() && this.rootControl.isLive()) {
                reasonReport = ReasonReport.appBackground;
            }
            ViewGroup viewGroup = this.videoContainer;
            if (viewGroup != null) {
                KsPlayerManager.INSTANCE.killPlayer(viewGroup, ActionReport.pause, reasonReport, new KillPlayerListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda12
                    @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                    public final void onKilled() {
                        ProgramsFragment.lambda$onStop$1();
                    }
                });
            }
        }
    }

    private void init() throws Resources.NotFoundException {
        if (getContext() != null) {
            setHeader();
        }
        this.screenWidth = Utils.getScreenWidth(getContext());
        setFragmentContainerId(R.id.programs_fragments_container);
        this.bodyPadding = Utils.convertDipToPixels(this.view.getContext(), 40.0f);
        FrameLayout frameLayout = (FrameLayout) this.view.findViewById(R.id.leftClick);
        ((CoordinatorLayout.LayoutParams) frameLayout.getLayoutParams()).width = this.bodyPadding;
        frameLayout.requestLayout();
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Resources.NotFoundException {
                this.f$0.lambda$init$2(view);
            }
        });
        FrameLayout frameLayout2 = (FrameLayout) this.view.findViewById(R.id.rightClick);
        ((CoordinatorLayout.LayoutParams) frameLayout2.getLayoutParams()).width = this.bodyPadding;
        frameLayout2.requestLayout();
        frameLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Resources.NotFoundException {
                this.f$0.lambda$init$3(view);
            }
        });
        initViewPagerBody();
        initViewPagerHeader();
        this.selectedItemMap = new HashMap<>();
        this.items = new ArrayList<>();
        this.observable.map(new Function() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda8
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$init$4((Integer) obj);
            }
        }).subscribe();
        double screenHeight = (Utils.getScreenHeight(getContext()) * 1.0d) / Utils.getScreenWidth(getContext());
        int iConvertDipToPixels = Utils.convertDipToPixels(getContext(), 85.0f);
        if (screenHeight > 1.77777777778d && Utils.getScreenHeight(getContext()) < 2500) {
            iConvertDipToPixels = Utils.convertDipToPixels(getContext(), 133.0f);
        }
        fragmentOnResume(this.mainActivity);
        new FetchProgramsData(MainConfig.main.getCurrentSource("channelDataApi").replace("%CHANNEL_ID%", this.action), this.mainActivity, this.viewPagerBody, this.viewPagerHeader, this.items, this.observable, this.selectedItemMap, iConvertDipToPixels, this.programCode, new ListAdapter.ClickHandler() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment.1
            @Override // com.channel2.mobile.ui.programs.controllers.ListAdapter.ClickHandler
            public void onClicked(ProgramsItem programsItem) throws JSONException {
                ProgramsFragment.this.onVideoItemClicked(programsItem.getVideoVcmId(), programsItem.getVideoChannelId(), programsItem.getVideoGalleryChannelId(), programsItem.getStartTimeUTC());
            }
        }, new FetchProgramsData.Handler() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda9
            @Override // com.channel2.mobile.ui.programs.models.FetchProgramsData.Handler
            public final void onSuccess(JSONObject jSONObject) {
                this.f$0.setDataForReportLobbyMetrics(jSONObject);
            }
        });
        this.videoDelayHandler = new Handler(Looper.getMainLooper());
        this.videoDelayRunnable = new Runnable() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$init$5();
            }
        };
        this.liveDelayHandler = new Handler(Looper.getMainLooper());
        this.liveDelayRunnable = new Runnable() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$init$6();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$2(View view) throws Resources.NotFoundException {
        selectItemOnClick(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$3(View view) throws Resources.NotFoundException {
        selectItemOnClick(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$init$4(Integer num) throws Exception {
        setVideoItem(false);
        return num;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$5() {
        play(this.finalItem, this.finalTmp);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectItemOnClick(int i) throws Resources.NotFoundException {
        deselectComponent();
        this.canSelect = true;
        ViewPager viewPager = this.viewPagerBody;
        viewPager.setCurrentItem(viewPager.getCurrentItem() + i);
        setVideoItem(false);
    }

    private void initViewPagerBody() throws Resources.NotFoundException {
        this.viewPagerBody = (ViewPager) this.view.findViewById(R.id.view_pager_body);
        startPlaceholderAnimation();
        ((CoordinatorLayout.LayoutParams) this.viewPagerBody.getLayoutParams()).topMargin = (int) (Utils.getScreenHeight(this.view.getContext()) / 5.5f);
        this.viewPagerBody.requestLayout();
        this.viewPagerBody.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment.2
            private int mScrollState = 0;

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                if (this.mScrollState == 0) {
                    return;
                }
                ProgramsFragment.this.viewPagerHeader.scrollTo((int) (ProgramsFragment.this.viewPagerBody.getScrollX() * ((ProgramsFragment.this.screenWidth - (ProgramsFragment.this.headerPadding * 2)) / (ProgramsFragment.this.screenWidth - (ProgramsFragment.this.bodyPadding * 2)))), 0);
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                ProgramsFragment programsFragment = ProgramsFragment.this;
                programsFragment.selectedComponent = i % programsFragment.items.size();
                if (ProgramsFragment.this.canSelect) {
                    ProgramsFragment.this.selectComponent();
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) throws Resources.NotFoundException {
                this.mScrollState = i;
                if (i == 0) {
                    ProgramsFragment.this.viewPagerHeader.setCurrentItem(ProgramsFragment.this.viewPagerBody.getCurrentItem(), false);
                    ProgramsFragment.this.setVideoItem(false);
                }
                if (i == 1) {
                    ProgramsFragment.this.canSelect = true;
                    ProgramsFragment programsFragment = ProgramsFragment.this;
                    programsFragment.lastSelectedComponent = programsFragment.selectedComponent;
                    ProgramsFragment.this.deselectComponent();
                    return;
                }
                if (i == 0 && ProgramsFragment.this.lastSelectedComponent == ProgramsFragment.this.selectedComponent) {
                    ProgramsFragment.this.selectComponent();
                }
            }
        });
        this.viewPagerBody.setPadding(this.bodyPadding, Utils.convertDipToPixels(this.view.getContext(), 10.0f), this.bodyPadding, 0);
        this.viewPagerBody.setClipToPadding(false);
        this.viewPagerBody.setClipChildren(false);
        this.viewPagerBody.setOffscreenPageLimit(3);
    }

    private void stopPlaceholderAnimation() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.setDuration(1000L);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ProgramsFragment.this.placeholder.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        valueAnimatorOfFloat.start();
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                ProgramsFragment.this.placeholder.setVisibility(8);
            }
        });
        this.placeholderAnimation.cancel();
    }

    private void startPlaceholderAnimation() {
        this.isPlaceholderVisible = true;
        this.placeholder = (FrameLayout) this.view.findViewById(R.id.placeholder);
        final ImageView imageView = (ImageView) this.view.findViewById(R.id.placeholderImage);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) this.placeholder.getLayoutParams();
        layoutParams.width = Utils.getScreenWidth(getContext());
        layoutParams.height = (int) (Utils.getScreenWidth(getContext()) * 1.940625d);
        this.placeholder.requestLayout();
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(255, 150);
        this.placeholderAnimation = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration(1000L);
        this.placeholderAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                imageView.setImageAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        this.placeholderAnimation.setRepeatMode(2);
        this.placeholderAnimation.setRepeatCount(-1);
        this.placeholderAnimation.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deselectComponent() {
        final FrameLayout view;
        try {
            ProgramsComponent programsComponent = this.items.get(this.selectedComponent);
            if (programsComponent == null || (view = programsComponent.getView()) == null) {
                return;
            }
            FrameLayout headerView = programsComponent.getHeaderView();
            if (view.getChildCount() > 1) {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.05f, 1.0f);
                valueAnimatorOfFloat.setDuration(500L);
                valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment.6
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        view.setScaleX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        view.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                });
                valueAnimatorOfFloat.start();
                CardView cardView = (CardView) view.getChildAt(0);
                CardView cardView2 = (CardView) view.getChildAt(1);
                cardView.setCardElevation(0.0f);
                cardView2.setCardElevation(0.0f);
            }
            if (headerView != null) {
                ((CustomTextView) headerView.findViewById(R.id.title)).setTextColor(Color.parseColor("#B36C6E70"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectComponent() {
        try {
            this.canSelect = false;
            ProgramsComponent programsComponent = this.items.get(this.selectedComponent);
            if (programsComponent != null) {
                final FrameLayout view = programsComponent.getView();
                FrameLayout headerView = programsComponent.getHeaderView();
                if (view != null) {
                    CardView cardView = (CardView) view.getChildAt(0);
                    CardView cardView2 = (CardView) view.getChildAt(1);
                    cardView.setCardElevation(5.0f);
                    cardView2.setCardElevation(5.0f);
                    ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 1.05f);
                    valueAnimatorOfFloat.setDuration(500L);
                    valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment.7
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            view.setScaleX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            view.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        }
                    });
                    valueAnimatorOfFloat.start();
                }
                if (headerView != null) {
                    ((CustomTextView) headerView.findViewById(R.id.title)).setTextColor(Color.parseColor("#0C0C0C"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViewPagerHeader() throws Resources.NotFoundException {
        this.headerPadding = Utils.getScreenWidth(this.view.getContext()) / 4;
        ViewPager viewPager = (ViewPager) this.view.findViewById(R.id.view_pager_header);
        this.viewPagerHeader = viewPager;
        ((CoordinatorLayout.LayoutParams) viewPager.getLayoutParams()).height = (int) (Utils.getScreenHeight(this.view.getContext()) / 5.5f);
        this.viewPagerHeader.requestLayout();
        this.viewPagerHeader.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment.8
            private int mScrollState = 0;

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                if (this.mScrollState == 0) {
                    return;
                }
                ProgramsFragment.this.viewPagerBody.scrollTo((int) (ProgramsFragment.this.viewPagerHeader.getScrollX() * ((ProgramsFragment.this.screenWidth - (ProgramsFragment.this.bodyPadding * 2)) / (ProgramsFragment.this.screenWidth - (ProgramsFragment.this.headerPadding * 2)))), 0);
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                ProgramsFragment programsFragment = ProgramsFragment.this;
                programsFragment.selectedComponent = i % programsFragment.items.size();
                if (ProgramsFragment.this.canSelect) {
                    ProgramsFragment.this.selectComponent();
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) throws Resources.NotFoundException {
                this.mScrollState = i;
                if (i == 0) {
                    ProgramsFragment.this.viewPagerBody.setCurrentItem(ProgramsFragment.this.viewPagerHeader.getCurrentItem(), false);
                    ProgramsFragment.this.setVideoItem(false);
                }
                if (i == 1) {
                    ProgramsFragment.this.canSelect = true;
                    ProgramsFragment programsFragment = ProgramsFragment.this;
                    programsFragment.lastSelectedComponent = programsFragment.selectedComponent;
                    ProgramsFragment.this.deselectComponent();
                    return;
                }
                if (i == 0 && ProgramsFragment.this.lastSelectedComponent == ProgramsFragment.this.selectedComponent) {
                    ProgramsFragment.this.selectComponent();
                }
            }
        });
        ViewPager viewPager2 = this.viewPagerHeader;
        int i = this.headerPadding;
        viewPager2.setPadding(i, 0, i, 0);
        this.viewPagerHeader.setClipToPadding(false);
        this.viewPagerHeader.setClipChildren(false);
        this.viewPagerHeader.setOffscreenPageLimit(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoItem(boolean z) {
        ProgramsItem programsItem;
        if (this.isFirst) {
            this.isFirst = false;
            selectComponent();
        }
        try {
            programsItem = this.items.get(this.selectedComponent).getItems().get(((Integer) ErrorItem$$ExternalSyntheticBackport0.m(this.selectedItemMap.get(Integer.valueOf(this.selectedComponent)), 0)).intValue());
        } catch (Exception e) {
            e.printStackTrace();
            programsItem = null;
        }
        if (programsItem == null || programsItem.getViewHolder() == null) {
            return;
        }
        ProgramsViewHolder viewHolder = programsItem.getViewHolder();
        ProgramsViewHolder programsViewHolder = this.selectedVideoItem;
        if (programsViewHolder != viewHolder || z) {
            if (programsViewHolder != null) {
                programsViewHolder.imageView.setVisibility(0);
                ReasonReport reasonReport = ReasonReport.none;
                RootControl rootControl = this.rootControl;
                if (rootControl != null && rootControl.getPlayerExists() && this.rootControl.isPlaying() && this.rootControl.isLive()) {
                    reasonReport = ReasonReport.scroll;
                }
                ViewGroup viewGroup = this.videoContainer;
                if (viewGroup != null) {
                    KsPlayerManager.INSTANCE.killPlayer(viewGroup, ActionReport.pause, reasonReport, new KillPlayerListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda2
                        @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                        public final void onKilled() {
                            ProgramsFragment.lambda$setVideoItem$7();
                        }
                    });
                }
            }
            this.finalItem = programsItem;
            this.finalTmp = viewHolder;
            this.videoDelayHandler.removeCallbacks(this.videoDelayRunnable);
            this.videoDelayHandler.postDelayed(this.videoDelayRunnable, 500L);
            this.selectedVideoItem = viewHolder;
        }
    }

    private void play(ProgramsItem programsItem, ProgramsViewHolder programsViewHolder) {
        ViewGroup viewGroup = this.videoContainer;
        if (viewGroup != null) {
            KsPlayerManager.INSTANCE.killPlayer(viewGroup, ActionReport.pause, ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda5
                @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                public final void onKilled() {
                    ProgramsFragment.lambda$play$8();
                }
            });
        }
        this.liveDelayHandler.removeCallbacks(this.liveDelayRunnable);
        if (getContext() != null && CastManager.INSTANCE.getSharedInstance(getContext()).getCastState().getValue() == PlayerCastState.none && !((MyApplication) this.mainActivity.getApplication()).getIsPiP()) {
            this.view.setKeepScreenOn(true);
            KsPlayItem ksPlayItem = new KsPlayItem();
            ksPlayItem.setLoop(true);
            ksPlayItem.setMute(true);
            ksPlayItem.setWithAds(false);
            ksPlayItem.setUseController(false);
            ksPlayItem.setPlayerState(PlayerState.teaser);
            ksPlayItem.setImage(programsItem.getImage());
            if (programsItem.getTrailer() != null && programsItem.getTrailer().length() > 0) {
                ksPlayItem.setUrl(programsItem.getTrailer());
            } else if (programsItem.getVideoVcmId() != null && programsItem.getVideoVcmId().length() > 0 && programsItem.getVideoChannelId() != null && programsItem.getVideoChannelId().length() > 0 && programsItem.getVideoGalleryChannelId() != null && programsItem.getVideoGalleryChannelId().length() > 0) {
                ksPlayItem.setVideoVcmId(programsItem.getVideoVcmId());
                ksPlayItem.setChannelId(programsItem.getVideoChannelId());
                ksPlayItem.setGalleryChannelId(programsItem.getVideoGalleryChannelId());
                ksPlayItem.setSeekTo(programsItem.getStartTimeUTC());
                ksPlayItem.setLoop(true);
                ksPlayItem.setMute(true);
                ksPlayItem.setWithAds(false);
                ksPlayItem.setPlayerState(PlayerState.teaser);
                ksPlayItem.getPlayerPageParams().setMako_video_state("teaser");
                this.liveDelayHandler.postDelayed(this.liveDelayRunnable, 180000L);
            }
            this.videoContainer = (ViewGroup) programsViewHolder.itemView.findViewById(R.id.videoContainer);
            KsPlayerManager.INSTANCE.play(ksPlayItem, this.videoContainer, this);
        }
        if (this.isPlaceholderVisible) {
            this.isPlaceholderVisible = false;
            stopPlaceholderAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onVideoItemClicked(String str, String str2, String str3, long j) throws JSONException {
        Runnable runnable;
        Handler handler = this.videoDelayHandler;
        if (handler != null && (runnable = this.videoDelayRunnable) != null) {
            handler.removeCallbacks(runnable);
        }
        ViewGroup viewGroup = this.videoContainer;
        if (viewGroup != null) {
            KsPlayerManager.INSTANCE.killPlayer(viewGroup, ActionReport.pause, ReasonReport.none, new KillPlayerListener() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda3
                @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                public final void onKilled() {
                    ProgramsFragment.lambda$onVideoItemClicked$9();
                }
            });
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", "app://playVideo?url=not&videoId=" + str + "&channelId=" + str2 + "&galleryChannelId=" + str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LinkItem linkItem = new LinkItem(jSONObject);
        linkItem.setStartPosition(j);
        lambda$init$6();
        linkItem.setMako_ref_comp("programs_page");
        RoutingManager.goToNextScreen(getFragmentContainerId(), linkItem, getTabId(), this.mainActivity, null);
    }

    private void setHeader() {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.header_lobby, (ViewGroup) null, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) frameLayout.findViewById(R.id.logoContainer);
        Tab tab = MainConfig.main.getFooter().tabs.get(getTabId());
        if (tab.headerTitle.length() > 0) {
            TextView textView = (TextView) frameLayout.findViewById(R.id.channelTitle);
            ImageView imageView = (ImageView) frameLayout.findViewById(R.id.appLogo);
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.app_logo_lines);
            textView.setVisibility(0);
            textView.setText(tab.headerTitle);
        }
        constraintLayout.setVisibility(0);
        this.mainActivity.navigationManager.addHeader(getTabId(), frameLayout);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void fragmentOnResume(Activity activity) {
        ViewPager viewPager;
        super.fragmentOnResume(activity);
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
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) this.mainActivity.swipeRefreshLayout.getLayoutParams();
                if (getContext() != null) {
                    layoutParams.bottomMargin = Utils.convertDipToPixels(getContext(), 0.0f);
                }
                if (this.mainActivity.adContainer != null) {
                    this.mainActivity.adContainer.setVisibility(8);
                }
                if (this.mainActivity.bottomNavigationView != null) {
                    this.mainActivity.bottomNavigationView.selectItem(getTabId());
                }
                this.mainActivity.getWindow().addFlags(128);
                this.mainActivity.setRequestedOrientation(1);
                if (this.items.size() > 0) {
                    setVideoItem(true);
                }
            }
            ViewPager viewPager2 = this.viewPagerBody;
            if (viewPager2 == null || viewPager2.getAdapter() == null || (viewPager = this.viewPagerHeader) == null || viewPager.getAdapter() == null) {
                return;
            }
            this.viewPagerBody.getAdapter().notifyDataSetChanged();
            this.viewPagerHeader.getAdapter().notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setProgramCode(String str) {
        this.programCode = str;
        if (this.items == null || str.length() <= 0) {
            return;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment.9
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                int size = ProgramsFragment.this.items.size() - 1;
                if (ProgramsFragment.this.programCode.length() > 0) {
                    int i = 0;
                    while (true) {
                        if (i >= ProgramsFragment.this.items.size()) {
                            break;
                        }
                        if (((ProgramsComponent) ProgramsFragment.this.items.get(i)).getProgramCodeDisplay().equals(ProgramsFragment.this.programCode)) {
                            size = i;
                            break;
                        }
                        i++;
                    }
                }
                int i2 = 500;
                for (int i3 = 0; i3 < ProgramsFragment.this.items.size(); i3++) {
                    i2 = i3 + 500;
                    if (i2 % ProgramsFragment.this.items.size() == size) {
                        break;
                    }
                }
                ProgramsFragment programsFragment = ProgramsFragment.this;
                programsFragment.selectItemOnClick(i2 - programsFragment.viewPagerBody.getCurrentItem());
            }
        }, 300L);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void scrollTop() {
        super.scrollTop();
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
        this.didFetchData.observe(this, new Observer() { // from class: com.channel2.mobile.ui.programs.controllers.ProgramsFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$reportAnalyticsEvents$10(activity, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reportAnalyticsEvents$10(Activity activity, Boolean bool) {
        if (bool.booleanValue()) {
            reportLobbyMetrics(activity);
            this.didFetchData.removeObservers(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDataForReportLobbyMetrics(JSONObject jSONObject) {
        this.FriendlyUrl = jSONObject.optString("friendlyUrl");
        this.ChannelVcmId = jSONObject.optString("channelVcmId");
        this.didFetchData.setValue(true);
    }

    private void reportLobbyMetrics(Activity activity) {
        Log.i("reportMetrics", "Programs");
        String currentSource = MainConfig.main.getCurrentSource("reportMetrics");
        if (this.FriendlyUrl.length() > 0) {
            FirebaseAnalytics.getInstance(this.mainActivity).setCurrentScreen(this.mainActivity, this.FriendlyUrl, null);
            Uri uri = Uri.parse("https://www.mako.co.il" + this.FriendlyUrl);
            if (MainConfig.main.getCurrentBooleanParam(activity.getResources().getString(R.string.idx_enable))) {
                this.pageTracker = MainActivity.permutive.trackPage(new EventProperties.Builder().build(), this.FriendlyUrl, uri, null);
                Log.i("permutive", "permutive_On");
            }
        }
        TransparentWebView.report(this.mainActivity.getApplicationContext(), currentSource.replace("%GUID%", this.ChannelVcmId).replace("%VCM_ID%", this.ChannelVcmId).replace("%CONTENT_TYPE%", "Vertical").replace("%FRIENDLY_URL%", this.FriendlyUrl));
    }

    @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
    public void onInitialized() {
        this.rootControl = (RootControl) this.view.findViewById(R.id.root_control);
    }

    @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
    public void onPosterLoaded() {
        ProgramsViewHolder programsViewHolder = this.finalTmp;
        if (programsViewHolder == null || programsViewHolder.imageView == null) {
            return;
        }
        this.finalTmp.imageView.setVisibility(8);
    }
}
