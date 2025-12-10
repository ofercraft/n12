package com.channel2.mobile.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.channel2.mobile.ui.Chats.controllers.ChatManager;
import com.channel2.mobile.ui.Chats.controllers.ChatsFragment;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.Chats.models.ChatTopic;
import com.channel2.mobile.ui.Chats.views.InnerPushController;
import com.channel2.mobile.ui.advertising.InterstitialManager;
import com.channel2.mobile.ui.advertising.InterstitialStatus;
import com.channel2.mobile.ui.alerts.HFC.HfcAlertHelper;
import com.channel2.mobile.ui.alerts.HFC.HfcAlertHelpersHandler;
import com.channel2.mobile.ui.alerts.VersionControlAlert;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.configs.models.Footer;
import com.channel2.mobile.ui.configs.models.Tab;
import com.channel2.mobile.ui.customViews.CustomBottomNavigationView;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.customViews.TutorialDialog;
import com.channel2.mobile.ui.header.CheckHeaderStyle;
import com.channel2.mobile.ui.header.Header;
import com.channel2.mobile.ui.header.HeaderVisibility;
import com.channel2.mobile.ui.helpers.NavigationManager;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragment;
import com.channel2.mobile.ui.lobby.models.ads.DFP;
import com.channel2.mobile.ui.location.PermissionRequestsManager;
import com.channel2.mobile.ui.network.ApiService;
import com.channel2.mobile.ui.programs.controllers.ProgramsFragment;
import com.channel2.mobile.ui.splash.MyApplication;
import com.channel2.mobile.ui.splash.SplashActivity;
import com.channel2.mobile.ui.webView.controllers.GalleryFragment;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.appbar.AppBarLayout;
import com.outbrain.OBSDK.Outbrain;
import com.outbrain.OBSDK.OutbrainException;
import com.permutive.android.Permutive;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, NavigationManager.Handler {
    public static final int RESULT_BACK_FROM_GALLERY = 2;
    public static final int RESULT_BACK_FROM_PLAYER = 3;
    public static final int RESULT_RESTART = 100;
    public static boolean isBackFromGallery = false;
    public static boolean isInterstitialShowed = false;
    public static Permutive permutive;
    public FrameLayout adContainer;
    public AppBarLayout appBarLayout;
    public ConstraintLayout bottomNavigationContainer;
    public CustomBottomNavigationView bottomNavigationView;
    public HeaderVisibility bottomNavigationVisibility;
    private CustomFragment currentFragment;
    public int currentTab;
    private String deepLink;
    private Footer footer;
    public ArrayList<CustomFragment> fragments;
    public FrameLayout headerContainer;
    public ConstraintLayout hfcLayout;
    private Handler hfc_handler;
    private Timer hfc_timer;
    private InnerPushController innerPushController;
    private boolean innerPushInitialized;
    public boolean isCurrentFragment;
    public CustomFragment lastFragment;
    public ArrayList<Integer> lastTab;
    public NavigationManager navigationManager;
    private PermissionRequestsManager permissionRequestsManager;
    private String pushId;
    public SwipeRefreshLayout swipeRefreshLayout;
    private int tabsCount;
    public Toolbar toolbar;
    private int toolbarHeight;
    public AppBarLayout.LayoutParams toolbarParams;
    private boolean restartAppEnabled = true;
    public boolean fromBack = false;
    boolean isInterstitialFinished = false;
    public final ActivityResultLauncher<Intent> resultActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.channel2.mobile.ui.MainActivity$$ExternalSyntheticLambda5
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            this.f$0.lambda$new$0((ActivityResult) obj);
        }
    });

    @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
    public /* synthetic */ void onBackStackChangeCommitted(Fragment fragment, boolean z) {
        FragmentManager.OnBackStackChangedListener.CC.$default$onBackStackChangeCommitted(this, fragment, z);
    }

    @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
    public /* synthetic */ void onBackStackChangeStarted(Fragment fragment, boolean z) {
        FragmentManager.OnBackStackChangedListener.CC.$default$onBackStackChangeStarted(this, fragment, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ActivityResult activityResult) {
        Log.d("PBR", "MainActivity onResult:  result = " + activityResult.getResultCode());
        int resultCode = activityResult.getResultCode();
        if (resultCode == 2) {
            isBackFromGallery = true;
            CustomFragment customFragment = this.currentFragment;
            if (customFragment instanceof ChatsFragment) {
                ((ChatsFragment) customFragment).setExitToGallery(false);
                return;
            }
            return;
        }
        if (resultCode != 3) {
            if (resultCode != 100) {
                return;
            }
            restartApp();
        } else {
            CustomFragment customFragment2 = this.currentFragment;
            if (customFragment2 instanceof LobbyFragment) {
                ((LobbyFragment) customFragment2).setCanPlayVideo();
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) throws PackageManager.NameNotFoundException {
        super.onCreate(bundle);
        Log.i("mainlifecycle", "onCreate()");
        Log.i("InterstitialManager", "mainActivity onCreate()");
        Log.d("maavaron", "main activity created after " + (new Date().getTime() - InterstitialManager.timeTestStart));
        setContentView(R.layout.activity_main);
        InterstitialStatus value = InterstitialManager.getInstance().interstitialStateLiveData.getValue();
        if (value == null) {
            value = InterstitialStatus.FAILED;
        }
        interstitialStatus(value);
        registerObserver();
        permutive = MyApplication.getInstance().getPermutive();
        if (MainConfig.appData != null && MainConfig.main != null) {
            MainConfig.appData.setMainActivity(this);
            try {
                PermissionRequestsManager permissionRequestsManager = new PermissionRequestsManager(this, getApplicationContext());
                this.permissionRequestsManager = permissionRequestsManager;
                permissionRequestsManager.requestPermissions();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Outbrain.register(getApplicationContext(), "MAKOM1PIFCF10AA0295M03NM9");
            } catch (OutbrainException unused) {
            }
            Intent intent = getIntent();
            this.deepLink = intent.getStringExtra(SDKConstants.PARAM_DEEP_LINK);
            this.pushId = intent.getStringExtra("pushId");
            getSupportFragmentManager().addOnBackStackChangedListener(this);
            this.navigationManager = new NavigationManager(this);
            this.bottomNavigationView = (CustomBottomNavigationView) findViewById(R.id.navigation);
            this.adContainer = (FrameLayout) findViewById(R.id.adContainer);
            this.bottomNavigationContainer = (ConstraintLayout) findViewById(R.id.bottomNavigationContainer);
            this.bottomNavigationVisibility = HeaderVisibility.VISIBLE;
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            this.toolbar = toolbar;
            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
            this.toolbarParams = layoutParams;
            layoutParams.height = getStatusBarHeight() + Utils.convertDipToPixels(getApplicationContext(), 40.0f);
            this.toolbarHeight = this.toolbarParams.height;
            this.toolbar.requestLayout();
            this.headerContainer = (FrameLayout) findViewById(R.id.headerContainer);
            setSupportActionBar(this.toolbar);
            this.appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
            this.hfcLayout = (ConstraintLayout) findViewById(R.id.hfc_view);
            this.appBarLayout.setOutlineProvider(null);
            getWindow().clearFlags(1024);
            if (Build.VERSION.SDK_INT >= 26) {
                getWindow().getDecorView().setSystemUiVisibility(1296);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(1280);
            }
            Window window = getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags &= -67108865;
            window.setAttributes(attributes);
            SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
            this.swipeRefreshLayout = swipeRefreshLayout;
            swipeRefreshLayout.setEnabled(false);
            this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.channel2.mobile.ui.MainActivity$$ExternalSyntheticLambda0
                @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
                public final void onRefresh() {
                    this.f$0.lambda$onCreate$1();
                }
            });
            initial();
        } else {
            restartApp();
        }
        com.mako.kscore.helpers.Utils.INSTANCE.killPiP(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1() {
        this.currentFragment.refresh();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        backPressed();
    }

    @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
    public void onBackStackChanged() {
        CustomFragment lastFragment = this.navigationManager.getLastFragment(this.currentTab);
        if (lastFragment == null) {
            lastFragment = this.fragments.get(this.currentTab);
        }
        Header header = MainConfig.main.getHeader();
        if (lastFragment != null && lastFragment.isHomePage && header.getHeaderPosition() == HeaderVisibility.INVISIBLE) {
            displayHeader(HeaderVisibility.INVISIBLE);
        } else {
            displayHeader(HeaderVisibility.VISIBLE);
        }
    }

    @Override // com.channel2.mobile.ui.helpers.NavigationManager.Handler
    public void onHeaderBackPressed() {
        backPressed();
    }

    public int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public int getNotchHeight() {
        if (Build.VERSION.SDK_INT < 28 || getWindow().getDecorView().getRootWindowInsets() == null || getWindow().getDecorView().getRootWindowInsets().getDisplayCutout() == null) {
            return 0;
        }
        return getWindow().getDecorView().getRootWindowInsets().getDisplayCutout().getSafeInsetTop();
    }

    public int getScreenWidth() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setRefreshEnd() {
        this.swipeRefreshLayout.setRefreshing(false);
    }

    private void initial() {
        boolean z;
        this.lastTab = new ArrayList<>();
        this.footer = MainConfig.main.getFooter();
        getWindow().setNavigationBarColor(Color.parseColor("#E4E4E5"));
        new VersionControlAlert((ConstraintLayout) findViewById(R.id.versionControlAlert), new VersionControlAlert.Handler() { // from class: com.channel2.mobile.ui.MainActivity.1
            @Override // com.channel2.mobile.ui.alerts.VersionControlAlert.Handler
            public void onClick() {
                MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName())));
            }
        });
        this.fragments = new ArrayList<>();
        for (int i = 0; i < this.footer.tabs.size(); i++) {
            this.fragments.add(null);
        }
        displayHeader(MainConfig.main.getHeader().getHeaderPosition());
        this.toolbar.setTitleTextColor(getResources().getColor(R.color.transparent));
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new CustomBottomNavigationView.OnNavigationItemSelectedListener() { // from class: com.channel2.mobile.ui.MainActivity.2
            @Override // com.channel2.mobile.ui.customViews.CustomBottomNavigationView.OnNavigationItemSelectedListener
            public void onNavigationItemSelected(int i2) {
                if (MainActivity.this.currentTab != i2) {
                    MainActivity.isInterstitialShowed = false;
                }
                MainActivity.this.displayFragment(i2, null);
            }
        });
        String str = this.deepLink;
        if (str != null && str.length() > 0) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.footer.tabs.size()) {
                    z = false;
                    break;
                }
                if (this.deepLink.equals(this.footer.tabs.get(i2).action)) {
                    displayFragment(i2, null);
                    z = true;
                    break;
                }
                i2++;
            }
            if (!z) {
                displayFragment(0, getIntent().getExtras());
            }
            this.deepLink = "";
            return;
        }
        displayFragment(0, getIntent().getExtras());
    }

    public void displayHeader(HeaderVisibility headerVisibility) {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) this.swipeRefreshLayout.getLayoutParams();
        CustomFragment customFragment = this.fragments.get(this.currentTab);
        LobbyFragment lobbyFragment = customFragment instanceof LobbyFragment ? (LobbyFragment) customFragment : null;
        if (headerVisibility == HeaderVisibility.VISIBLE) {
            layoutParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());
            this.headerContainer.setVisibility(0);
            Header header = MainConfig.main.getHeader();
            if (header.backgroundColorFromService != null && header.backgroundColorFromService.length() > 0) {
                this.toolbar.setBackgroundColor(Color.parseColor(header.backgroundColorFromService));
            } else {
                this.toolbar.setBackgroundColor(Color.parseColor(header.backgroundColorFromConfig));
            }
            ((AppBarLayout.LayoutParams) this.toolbar.getLayoutParams()).height = getStatusBarHeight() + Utils.convertDipToPixels(getApplicationContext(), 40.0f);
            this.toolbar.requestLayout();
            if (header.backgroundColorFromService != null && header.backgroundColorFromService.length() > 0) {
                getWindow().setStatusBarColor(Color.parseColor(header.backgroundColorFromService));
            } else {
                getWindow().setStatusBarColor(Color.parseColor(header.backgroundColorFromConfig));
            }
            if (lobbyFragment != null) {
                lobbyFragment.hideCustomHeader();
            }
        } else {
            layoutParams.setBehavior(null);
            this.headerContainer.setVisibility(8);
            this.toolbar.setBackgroundColor(Color.parseColor("#00000000"));
            getWindow().setStatusBarColor(0);
            ((AppBarLayout.LayoutParams) this.toolbar.getLayoutParams()).height = 0;
            this.toolbar.requestLayout();
            if (lobbyFragment != null) {
                lobbyFragment.displayCustomHeader();
            }
        }
        this.swipeRefreshLayout.requestLayout();
    }

    public void displayBottomNavigation(HeaderVisibility headerVisibility) {
        this.bottomNavigationView.clearAnimation();
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) this.swipeRefreshLayout.getLayoutParams();
        if (headerVisibility == HeaderVisibility.VISIBLE) {
            this.bottomNavigationContainer.animate().translationY(0.0f).setDuration(300L);
        } else {
            layoutParams.bottomMargin = 0;
            this.bottomNavigationContainer.animate().translationY(this.bottomNavigationView.getHeight()).setDuration(300L);
        }
        this.swipeRefreshLayout.requestLayout();
    }

    public void displayFragment(int i, Bundle bundle) {
        Tab tab = MainConfig.main.getFooter().tabs.get(i);
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        if (bundle != null) {
            boolean z = bundle.getBoolean("isFromRouting");
            if ((this.currentFragment instanceof LobbyFragment) && i == this.currentTab && z) {
                return;
            }
        }
        CustomFragment customFragmentSelectFragment = this.fragments.get(i);
        if (customFragmentSelectFragment == null) {
            customFragmentSelectFragment = selectFragment(tab.view, tab.action, i, bundle);
            this.navigationManager.addTab(i, getApplicationContext());
            this.fragments.set(i, customFragmentSelectFragment);
        }
        if (customFragmentSelectFragment instanceof ProgramsFragment) {
            if (bundle != null && bundle.containsKey("programCode")) {
                ((ProgramsFragment) customFragmentSelectFragment).setProgramCode(bundle.getString("programCode"));
            }
        } else if (customFragmentSelectFragment instanceof ChatsFragment) {
            Log.i("chatLifeCycle", "displayFragment");
            if (bundle != null) {
                ((ChatsFragment) customFragmentSelectFragment).setArgs(bundle);
            }
        }
        FrameLayout tabHeaders = this.navigationManager.getTabHeaders(i);
        this.headerContainer.removeAllViews();
        this.headerContainer.addView(tabHeaders);
        fragmentTransactionBeginTransaction.replace(R.id.fragment_container, customFragmentSelectFragment);
        if (getSupportFragmentManager().getFragments().size() > 1) {
            int i2 = this.currentTab;
            if (i2 == i) {
                ArrayList<CustomFragment> tabFragments = this.navigationManager.getTabFragments(i2);
                if (tabFragments != null && tabFragments.size() > 0) {
                    this.navigationManager.removeAll(i, getSupportFragmentManager());
                } else {
                    Fragment fragment = getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size() - 1);
                    if (fragment instanceof CustomFragment) {
                        ((CustomFragment) fragment).scrollTop();
                        this.isCurrentFragment = true;
                    }
                }
            } else {
                fragmentTransactionBeginTransaction.addToBackStack(String.valueOf(i));
                this.lastTab.add(Integer.valueOf(i));
            }
        } else {
            this.lastTab.add(Integer.valueOf(i));
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        this.currentTab = i;
        this.currentFragment = customFragmentSelectFragment;
        performOnResume();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.channel2.mobile.ui.customViews.CustomFragment selectFragment(java.lang.String r10, java.lang.String r11, int r12, android.os.Bundle r13) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.channel2.mobile.ui.MainActivity.selectFragment(java.lang.String, java.lang.String, int, android.os.Bundle):com.channel2.mobile.ui.customViews.CustomFragment");
    }

    public void backPressed() {
        getWindow().clearFlags(128);
        this.lastFragment = this.currentFragment;
        this.fromBack = true;
        this.navigationManager.removeLast(this.currentTab, getSupportFragmentManager(), new NavigationManager.RemoveLastHandler() { // from class: com.channel2.mobile.ui.MainActivity.3
            @Override // com.channel2.mobile.ui.helpers.NavigationManager.RemoveLastHandler
            public void onBackPressed() {
                MainActivity.super.onBackPressed();
                MainActivity.isInterstitialShowed = false;
                if (MainActivity.this.lastTab.size() > 1) {
                    MainActivity.this.lastTab.remove(MainActivity.this.lastTab.size() - 1);
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.currentTab = mainActivity.lastTab.get(MainActivity.this.lastTab.size() - 1).intValue();
                    FrameLayout tabHeaders = MainActivity.this.navigationManager.getTabHeaders(MainActivity.this.currentTab);
                    MainActivity.this.headerContainer.removeAllViews();
                    MainActivity.this.headerContainer.addView(tabHeaders);
                    return;
                }
                MainActivity.this.isCurrentFragment = true;
            }

            @Override // com.channel2.mobile.ui.helpers.NavigationManager.RemoveLastHandler
            public void setVisibleHeader() {
                MainActivity.this.displayHeader(HeaderVisibility.INVISIBLE);
            }
        });
        performOnResume();
    }

    public void addFragment(int i, CustomFragment customFragment, String str) {
        if (customFragment.isAdded()) {
            return;
        }
        getSupportFragmentManager().beginTransaction().add(i, customFragment, str).commitAllowingStateLoss();
        customFragment.reportAnalyticsEvents(this);
        Log.i("tabreported", "" + this.currentTab);
    }

    public void replaceFragment(int i, CustomFragment customFragment, String str) {
        if (customFragment.isAdded()) {
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(i, customFragment, str).commitAllowingStateLoss();
        customFragment.reportAnalyticsEvents(this);
        Log.i("tabreported", "" + this.currentTab);
    }

    public void enterFullScreen(CustomFragment customFragment) {
        if (this.navigationManager.getLastFragment(this.currentTab) == null || this.navigationManager.getLastFragment(this.currentTab) == customFragment) {
            ((CoordinatorLayout.LayoutParams) this.swipeRefreshLayout.getLayoutParams()).bottomMargin = 0;
            this.swipeRefreshLayout.requestLayout();
            displayBottomNavigation(HeaderVisibility.INVISIBLE);
            displayHeader(HeaderVisibility.INVISIBLE);
            getWindow().setFlags(1024, 1024);
        }
    }

    public void exitFullScreen(CustomFragment customFragment) {
        try {
            if (this.navigationManager.getLastFragment(this.currentTab) == null || this.navigationManager.getLastFragment(this.currentTab) == customFragment) {
                ((CoordinatorLayout.LayoutParams) this.swipeRefreshLayout.getLayoutParams()).bottomMargin = Utils.convertDipToPixels(getApplicationContext(), 61.0f);
                this.swipeRefreshLayout.requestLayout();
                displayBottomNavigation(HeaderVisibility.VISIBLE);
                displayHeader(HeaderVisibility.VISIBLE);
                getWindow().clearFlags(1024);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i("mainlifecycle", "onResume()");
        performOnResume();
    }

    private void restartApp() {
        Log.d("checkAppStart", "MainActivity: chatTopics.restartApp. restartAppEnabled = " + this.restartAppEnabled);
        if (this.restartAppEnabled) {
            stopHFCTimer();
            InterstitialManager.getInstance().resetApp();
            ChatManager.getInstance().resetApp();
            Intent intent = new Intent(this, (Class<?>) SplashActivity.class);
            intent.addFlags(32768);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            finish();
            return;
        }
        this.restartAppEnabled = true;
    }

    public void performOnResume() {
        this.toolbarParams.setScrollFlags(0);
        if (this.navigationManager.getLastFragment(this.currentTab) != null) {
            this.navigationManager.getLastFragment(this.currentTab).fragmentOnResume(this);
            this.navigationManager.getLastFragment(this.currentTab).reportAnalyticsEvents(this);
            Log.i("tabreported", "" + this.currentTab);
            return;
        }
        if (this.fragments.get(this.currentTab) != null) {
            this.fragments.get(this.currentTab).fragmentOnResume(this);
            if (!this.isCurrentFragment) {
                this.fragments.get(this.currentTab).reportAnalyticsEvents(this);
                Log.i("tabreported", "" + this.currentTab);
            }
            this.isCurrentFragment = false;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.i("mainlifecycle", "onStop()");
        if (MainConfig.appData != null) {
            MainConfig.appData.setAppStartTime(System.currentTimeMillis());
        }
        stopHFCTimer();
        ChatManager.getInstance().appEnterBackground();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        Log.d("checkResult", "MainActivity onStart. is foreground : " + ((MyApplication) getApplication()).isForeground());
        Log.i("mainlifecycle", "onStart()");
        if (MainConfig.appData == null || MainConfig.main == null) {
            restartApp();
        } else {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = Long.parseLong(MainConfig.main.getCurrentParam("appLifeCycleInMin")) * 1000 * 60;
            if ((!((MyApplication) getApplication()).isForeground() || ((MyApplication) getApplication()).getIsPiP()) && !isBackFromGallery && MainConfig.appData.getAppStartTime() > 0 && MainConfig.appData.getAppStartTime() + j < jCurrentTimeMillis) {
                if (((MyApplication) getApplication()).getIsPiP()) {
                    com.mako.kscore.helpers.Utils.INSTANCE.killPiP(this);
                }
                restartApp();
            } else {
                startHFCTimer();
                ChatManager.getInstance().appEnterForeground(new ChatManager.ResponseHandler() { // from class: com.channel2.mobile.ui.MainActivity.4
                    @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
                    public void onFailure() {
                    }

                    @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
                    public void onSuccess(JSONObject jSONObject) {
                    }

                    @Override // com.channel2.mobile.ui.Chats.controllers.ChatManager.ResponseHandler
                    public void onSuccess(int i) {
                        CustomFragment customFragment = MainActivity.this.fragments.get(MainActivity.this.currentTab);
                        if (customFragment instanceof ChatsFragment) {
                            customFragment.fragmentOnResume(null);
                        }
                    }
                });
            }
        }
        super.onStart();
    }

    public void setRestartAppEnabled(boolean z) {
        Log.d("checkAppStart", "MainActivity: setRestartAppEnabled = " + z);
        this.restartAppEnabled = z;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PermissionRequestsManager permissionRequestsManager = this.permissionRequestsManager;
        if (permissionRequestsManager != null) {
            permissionRequestsManager.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void setSwipeToRefresh(Boolean bool) {
        NavigationManager navigationManager = this.navigationManager;
        if (navigationManager == null || navigationManager.getTabFragments(this.currentTab) == null || this.swipeRefreshLayout == null) {
            return;
        }
        CustomFragment lastFragment = this.navigationManager.getLastFragment(this.currentTab);
        if (lastFragment == null) {
            lastFragment = this.fragments.get(this.currentTab);
        }
        if (lastFragment instanceof LobbyFragment) {
            this.swipeRefreshLayout.setEnabled(bool.booleanValue());
        }
    }

    public void loadTutorial() {
        try {
            JSONObject tutorial = MainConfig.main.getTutorial();
            if (tutorial.optBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE) && Utils.isFirstTime(this).booleanValue()) {
                TutorialDialog tutorialDialog = new TutorialDialog(this, tutorial);
                tutorialDialog.show();
                ((Window) Objects.requireNonNull(tutorialDialog.getWindow())).setLayout(-1, -1);
                tutorialDialog.getWindow().setBackgroundDrawableResource(R.color.tutorial);
                ((ViewGroup) tutorialDialog.getWindow().getDecorView()).getChildAt(0).startAnimation(AnimationUtils.loadAnimation(this, R.anim.interstitial_in));
                Utils.setNotFirstTime(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChatInnerPush() {
        if (this.innerPushInitialized || ChatManager.getInstance().chatItemsArray == null) {
            return;
        }
        Log.i("pushim", "init");
        this.innerPushInitialized = true;
        this.innerPushController = new InnerPushController(this);
        ChatManager.getInstance().chatItemsArray.getNewItemObservation().observe(this, new Observer() { // from class: com.channel2.mobile.ui.MainActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$initChatInnerPush$2((ChatReportItem) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initChatInnerPush$2(ChatReportItem chatReportItem) {
        try {
            ChatReportItem originalChatItem = ChatManager.getInstance().chatItemsArray.getChatItems(false, chatReportItem.getTopicID()).get(0);
            if (shouldShowRedDot(originalChatItem)) {
                this.bottomNavigationView.addRedDot();
            }
            if (shouldInflateInnerPush(originalChatItem)) {
                Log.i("pushim", "inflated " + originalChatItem.getMessageID());
                if (originalChatItem.isSplitted()) {
                    originalChatItem = ChatManager.getInstance().chatItemsArray.getOriginalChatItem(originalChatItem);
                }
                this.innerPushController.initPush(originalChatItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean shouldInflateInnerPush(ChatReportItem chatReportItem) {
        Fragment fragment = getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size() - 1);
        Footer footer = this.footer;
        if (footer != null && !footer.tabs.get(this.currentTab).view.equals("Chat") && !(fragment instanceof GalleryFragment) && Utils.getBoolFromPreferences(this, getResources().getString(R.string.insidePush)) && chatReportItem.isFromFirebase()) {
            int topicID = chatReportItem.getTopicID();
            Iterator<ChatTopic> it = ChatManager.getInstance().chatTopics.iterator();
            while (it.hasNext()) {
                ChatTopic next = it.next();
                if (next.getTopicId() == topicID && !next.getTopicName().equals("news")) {
                    return false;
                }
            }
            if ((chatReportItem.isCurrent() && Utils.getBoolFromPreferences(this, getResources().getString(R.string.current))) || chatReportItem.isImportant()) {
                if (chatReportItem.isSplitted()) {
                    return chatReportItem.getChatMedia(0).getAutoId() == ChatManager.getInstance().chatItemsArray.getOriginalChatItem(chatReportItem).getChatMedia(0).getAutoId();
                }
                return true;
            }
        }
        return false;
    }

    public boolean shouldShowRedDot(ChatReportItem chatReportItem) {
        Footer footer = this.footer;
        if (footer == null || footer.tabs.get(this.currentTab).view.equals("Chat") || !chatReportItem.isFromFirebase()) {
            return false;
        }
        int topicID = chatReportItem.getTopicID();
        Iterator<ChatTopic> it = ChatManager.getInstance().chatTopics.iterator();
        while (it.hasNext()) {
            ChatTopic next = it.next();
            if (next.getTopicId() == topicID && !next.getTopicName().equals("news")) {
                return false;
            }
        }
        return true;
    }

    public void loadInterstitial(final DFP dfp, final String str, InterstitialManager.ShowPreload showPreload) {
        if (showPreload != InterstitialManager.ShowPreload.NOT_SET) {
            InterstitialManager.getInstance().createInterstitial(dfp, this, str, Boolean.valueOf(showPreload == InterstitialManager.ShowPreload.TRUE));
        } else {
            ApiService.getJSONObjectAsync(MainConfig.main.getCurrentSource("PreloadByUrlApi").replace("%URL%", str), this, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.MainActivity.5
                @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
                public void onSuccess(JSONObject jSONObject) {
                    InterstitialManager.getInstance().createInterstitial(dfp, MainActivity.this, str, Boolean.valueOf(jSONObject.optBoolean("preload", true)));
                }

                @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
                public void onFailure(String str2, int i) {
                    InterstitialManager.getInstance().createInterstitial(dfp, MainActivity.this, str, true);
                }
            });
        }
    }

    public void registerObserver() {
        Log.i("InterstitialManager", "registerObserver()");
        InterstitialManager.getInstance().interstitialStateLiveData.observe(this, new Observer() { // from class: com.channel2.mobile.ui.MainActivity$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.interstitialStatus((InterstitialStatus) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void interstitialStatus(InterstitialStatus interstitialStatus) {
        Log.i("InterstitialManager", "mainActivity status = " + interstitialStatus);
        if (interstitialStatus != null) {
            switch (AnonymousClass11.$SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[interstitialStatus.ordinal()]) {
                case 1:
                    InterstitialManager.getInstance().showInterstitial(this);
                    break;
                case 2:
                    this.isInterstitialFinished = false;
                    break;
                case 3:
                case 4:
                    this.isInterstitialFinished = true;
                    break;
                case 5:
                    this.isInterstitialFinished = true;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.MainActivity$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$interstitialStatus$3();
                        }
                    }, 500L);
                    break;
                case 6:
                case 7:
                    this.isInterstitialFinished = true;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.MainActivity$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$interstitialStatus$4();
                        }
                    }, 500L);
                    break;
                case 8:
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.MainActivity$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$interstitialStatus$5();
                        }
                    }, 500L);
                    break;
            }
        }
    }

    /* renamed from: com.channel2.mobile.ui.MainActivity$11, reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus;

        static {
            int[] iArr = new int[InterstitialStatus.values().length];
            $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus = iArr;
            try {
                iArr[InterstitialStatus.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[InterstitialStatus.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[InterstitialStatus.LOADED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[InterstitialStatus.PRELOAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[InterstitialStatus.SHOW.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[InterstitialStatus.FAILED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[InterstitialStatus.TIMEOUT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[InterstitialStatus.CLOSED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$interstitialStatus$3() {
        try {
            Fragment fragment = getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size() - 1);
            if (fragment instanceof CustomFragment) {
                CustomFragment customFragment = (CustomFragment) fragment;
                this.currentFragment = customFragment;
                customFragment.onInterstitialAd();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$interstitialStatus$4() {
        Fragment fragment = getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size() - 1);
        if (fragment instanceof CustomFragment) {
            CustomFragment customFragment = (CustomFragment) fragment;
            this.currentFragment = customFragment;
            customFragment.onInterstitialAd();
            this.currentFragment.onInterstitialAdClosed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$interstitialStatus$5() {
        Fragment fragment = getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size() - 1);
        if (fragment instanceof CustomFragment) {
            CustomFragment customFragment = (CustomFragment) fragment;
            this.currentFragment = customFragment;
            customFragment.onInterstitialAdClosed();
        }
    }

    public int getContainerByTabId(int i) {
        String tabNameById = this.bottomNavigationView.getTabNameById(i);
        tabNameById.hashCode();
        if (tabNameById.equals("Search")) {
            return R.id.recommended_fragments_container;
        }
        if (tabNameById.equals("Lobby")) {
            return R.id.lobby_fragment_container;
        }
        return 0;
    }

    public void startHFCTimer() {
        if ((Utils.getBoolFromPreferences(this, getResources().getString(R.string.hfcAlert)) && MainConfig.main.getCurrentBooleanParam("hfcEnable")) && this.hfc_timer == null) {
            int currentIntParam = MainConfig.main.getCurrentIntParam("hfcIntervals");
            this.hfc_timer = new Timer();
            this.hfc_timer.scheduleAtFixedRate(new TimerTask() { // from class: com.channel2.mobile.ui.MainActivity.6
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    MainActivity.this.handleHFCAlert();
                }
            }, 1500L, currentIntParam);
        }
    }

    public void stopHFCTimer() {
        hideHFCAlert(false);
        Timer timer = this.hfc_timer;
        if (timer != null) {
            timer.cancel();
            this.hfc_timer = null;
        }
    }

    public void handleHFCAlert() {
        HfcAlertHelper.getInstance().getData(this, new HfcAlertHelpersHandler() { // from class: com.channel2.mobile.ui.MainActivity.7
            @Override // com.channel2.mobile.ui.alerts.HFC.HfcAlertHelpersHandler
            public void onSuccessWithoutChange() {
            }

            @Override // com.channel2.mobile.ui.alerts.HFC.HfcAlertHelpersHandler
            public void onSuccess() {
                MainActivity.this.showHFCAlert();
            }

            @Override // com.channel2.mobile.ui.alerts.HFC.HfcAlertHelpersHandler
            public void onFailure() {
                MainActivity.this.hideHFCAlert(true);
            }
        });
    }

    public void hideHFCAlert(final Boolean bool) {
        ConstraintLayout constraintLayout = this.hfcLayout;
        if (constraintLayout == null || constraintLayout.getVisibility() == 8) {
            return;
        }
        try {
            this.hfc_handler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread() { // from class: com.channel2.mobile.ui.MainActivity.8
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws InterruptedException {
                if (bool.booleanValue()) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused) {
                    }
                }
                MainActivity.this.runOnUiThread(new Runnable() { // from class: com.channel2.mobile.ui.MainActivity.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HfcAlertHelper.getInstance().resetAlert();
                        MainActivity.this.hfcLayout.setVisibility(8);
                        TextView textView = (TextView) MainActivity.this.hfcLayout.findViewById(R.id.hfc_title);
                        TextView textView2 = (TextView) MainActivity.this.hfcLayout.findViewById(R.id.htc_cities);
                        textView.setText("");
                        textView2.setText("");
                    }
                });
            }
        }.start();
    }

    public void showHFCAlert() {
        try {
            this.hfc_handler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateHFCLayout();
        TextView textView = (TextView) this.hfcLayout.findViewById(R.id.hfc_title);
        final TextView textView2 = (TextView) this.hfcLayout.findViewById(R.id.htc_cities);
        ImageButton imageButton = (ImageButton) this.hfcLayout.findViewById(R.id.btn_close);
        textView.setText(HfcAlertHelper.getInstance().getTitle());
        Handler handler = new Handler(Looper.getMainLooper());
        this.hfc_handler = handler;
        handler.postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.MainActivity.9
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.hfcLayout.setVisibility(0);
                textView2.setText(HfcAlertHelper.getInstance().generateCities());
                MainActivity.this.showHFCAlert();
            }
        }, 1500L);
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.MainActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.hideHFCAlert(false);
            }
        });
    }

    private void updateHFCLayout() {
        int toolBarHeight = (CheckHeaderStyle.isTransparentHeader && (this.currentFragment instanceof LobbyFragment)) ? 30 : Utils.getToolBarHeight(this);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) this.hfcLayout.getLayoutParams();
        layoutParams.setMargins(0, toolBarHeight, 0, 0);
        this.hfcLayout.setLayoutParams(layoutParams);
    }
}
