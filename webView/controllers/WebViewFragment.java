package com.channel2.mobile.ui.webView.controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.advertising.InterstitialManager;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.configs.models.Tab;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.header.HeaderVisibility;
import com.channel2.mobile.ui.helpers.CustomWebViewClient;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.models.ads.DFP;
import com.channel2.mobile.ui.lobby.models.items.LinkItem;
import com.channel2.mobile.ui.pushNotification.DeepLinkManager;
import com.channel2.mobile.ui.routing.RoutingManager;
import com.channel2.mobile.ui.webView.views.CustomWebView;
import com.channel2.mobile.ui.webView.views.NestedWebView;
import com.cooladata.android.Constants;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.permutive.android.ads.AdManagerAdRequestUtils;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class WebViewFragment extends CustomFragment implements CustomEvent {
    private AdManagerAdView adView;
    private ImageView appLogo;
    private String canonical;
    private TextView channelTitle;
    private CustomWebViewClient customWebViewClient;
    private DFP dfp;
    private int[] dotsArray;
    private int fontSize;
    private FrameLayout fontSizeView;
    private String headerTitle;
    private int id;
    private boolean isArticleView;
    private boolean isFontSizeViewVisible;
    private boolean isNoHeader;
    private boolean isTabView;
    private boolean isWebViewMarginTop;
    private MainActivity mainActivity;
    private String ogTitle;
    private String ogUrl;
    private LinearLayout rootView;
    private AppCompatSeekBar seekBar;
    private String url;
    private String vcmID;
    private View view;
    private CustomWebView webView;
    private FrameLayout web_view_container;
    private boolean isInterstitial = false;
    private boolean bannerReady = false;
    private boolean didInterstitialFinised = false;
    private boolean isPageReady = false;
    private boolean reloaded = false;
    private InterstitialManager.ShowPreload showPreload = InterstitialManager.ShowPreload.NOT_SET;
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.12
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            try {
                int progress = seekBar.getProgress();
                int i2 = 0;
                int iAbs = Math.abs(WebViewFragment.this.dotsArray[0] - progress);
                int i3 = 0;
                for (int i4 = 1; i4 < WebViewFragment.this.dotsArray.length; i4++) {
                    int iAbs2 = Math.abs(WebViewFragment.this.dotsArray[i4] - progress);
                    if (iAbs2 < iAbs) {
                        i3 = i4;
                        iAbs = iAbs2;
                    }
                }
                int i5 = WebViewFragment.this.dotsArray[i3];
                seekBar.setProgress(i5);
                if (i5 == 0) {
                    Utils.saveFloatToPreferences(WebViewFragment.this.mainActivity, "zoom", Float.valueOf(1.5f));
                    i2 = 150;
                } else if (i5 == 25) {
                    Utils.saveFloatToPreferences(WebViewFragment.this.mainActivity, "zoom", Float.valueOf(1.4f));
                    i2 = 140;
                } else if (i5 == 50) {
                    Utils.saveFloatToPreferences(WebViewFragment.this.mainActivity, "zoom", Float.valueOf(1.3f));
                    i2 = 130;
                } else if (i5 == 75) {
                    Utils.saveFloatToPreferences(WebViewFragment.this.mainActivity, "zoom", Float.valueOf(1.2f));
                    i2 = 120;
                } else if (i5 == 100) {
                    Utils.saveFloatToPreferences(WebViewFragment.this.mainActivity, "zoom", Float.valueOf(1.0f));
                    i2 = 100;
                }
                if (WebViewFragment.this.customWebViewClient != null) {
                    WebViewFragment.this.customWebViewClient.setFontSize(WebViewFragment.this.webView, i2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.i("fontZoom", "onStopTrackingTouch " + seekBar.getProgress());
            int progress = seekBar.getProgress();
            int i = progress != 0 ? progress != 25 ? progress != 50 ? progress != 75 ? 1 : 2 : 3 : 4 : 5;
            Bundle bundle = new Bundle();
            bundle.putString("Size", String.valueOf(i));
            FirebaseAnalytics.getInstance(WebViewFragment.this.mainActivity).logEvent("Change_Font_Size", bundle);
            Log.i("fontZoom", "onStopTrackingTouch reprot - " + i);
        }
    };

    public DFP getDfp() {
        return this.dfp;
    }

    public boolean isArticleView() {
        return this.isArticleView;
    }

    public String getUrl() {
        return this.url;
    }

    public static WebViewFragment newInstance(int i, String str, DFP dfp, boolean z, boolean z2, boolean z3, InterstitialManager.ShowPreload showPreload) {
        WebViewFragment webViewFragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        try {
            bundle.putInt("id", i);
            bundle.putString("url", str);
            bundle.putSerializable("dfp", dfp);
            bundle.putBoolean("isNoHeader", z);
            bundle.putBoolean("isArticleView", z3);
            bundle.putBoolean("isTabView", z2);
            bundle.putSerializable("showPreload", showPreload);
            webViewFragment.setArguments(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webViewFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            try {
                int i = getArguments().getInt("id");
                this.id = i;
                setTabId(i);
                this.url = getArguments().getString("url");
                this.dfp = (DFP) getArguments().getSerializable("dfp");
                this.isNoHeader = getArguments().getBoolean("isNoHeader");
                this.isTabView = getArguments().getBoolean("isTabView");
                this.isArticleView = getArguments().getBoolean("isArticleView");
                this.showPreload = (InterstitialManager.ShowPreload) getArguments().get("showPreload");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) throws JSONException, Resources.NotFoundException {
        if (this.view == null) {
            this.view = layoutInflater.inflate(R.layout.fragment_web_view, viewGroup, false);
            init();
        }
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        AdManagerAdView adManagerAdView = this.adView;
        if (adManagerAdView != null) {
            adManagerAdView.removeAllViews();
            this.adView.setAdListener(null);
            this.adView.destroy();
        }
        CustomWebView customWebView = this.webView;
        if (customWebView != null) {
            if (customWebView.getContext() != null) {
                this.webView.destroy();
            }
            this.webView = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    private void init() throws JSONException, Resources.NotFoundException {
        setFragmentContainerId(R.id.web_view_fragment_container);
        setHeader();
        this.rootView = (LinearLayout) this.view.findViewById(R.id.rootView);
        this.web_view_container = (FrameLayout) this.view.findViewById(R.id.web_view_container);
        this.mainActivity.displayBottomNavigation(HeaderVisibility.VISIBLE);
        CustomWebView customWebView = (CustomWebView) this.view.findViewById(R.id.webView);
        this.webView = customWebView;
        MobileAds.registerWebView(customWebView);
        this.webView.setWebChromeClient(new WebChromeClient() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.1
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                Log.i("onProgressChanged", "" + i);
                if (i >= 70) {
                    WebViewFragment.this.isPageReady = true;
                    WebViewFragment.this.canReload();
                }
            }
        });
        if (this.url.startsWith("https://traffic.outbrain.com/network/redir")) {
            CustomWebView customWebView2 = new CustomWebView(getContext());
            MobileAds.registerWebView(customWebView2);
            customWebView2.setWebViewClient(new WebViewClient() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.2
                @Override // android.webkit.WebViewClient
                public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                    if (str.contains("mako.co.il")) {
                        try {
                            WebViewFragment.this.url = URLDecoder.decode(Utils.getAbsoluteUrl(str), "UTF-8");
                            WebViewFragment.this.webView.loadUrl(WebViewFragment.this.url);
                            webView.destroy();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            customWebView2.loadUrl(this.url);
        } else {
            this.webView.loadUrl(this.url);
        }
        this.webView.addJavascriptInterface(new JavaScriptInterface(getContext()), Constants.TRACKER_TYPE);
        this.webView.setOnTouchListener(new View.OnTouchListener() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!WebViewFragment.this.isFontSizeViewVisible) {
                    return false;
                }
                WebViewFragment.this.hideFontSizeView();
                return false;
            }
        });
        this.webView.setOnScrollListener(new NestedWebView.OnScrollUpAdnDownHandler() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.4
            @Override // com.channel2.mobile.ui.webView.views.NestedWebView.OnScrollUpAdnDownHandler
            public void onScrollUp() {
                if (WebViewFragment.this.isFontSizeViewVisible) {
                    WebViewFragment.this.hideFontSizeView();
                }
                if (WebViewFragment.this.isNoHeader || WebViewFragment.this.isTabView) {
                    return;
                }
                WebViewFragment.this.mainActivity.displayBottomNavigation(HeaderVisibility.VISIBLE);
            }

            @Override // com.channel2.mobile.ui.webView.views.NestedWebView.OnScrollUpAdnDownHandler
            public void onScrollDown() {
                if (WebViewFragment.this.isFontSizeViewVisible) {
                    WebViewFragment.this.hideFontSizeView();
                }
                if (WebViewFragment.this.isNoHeader || WebViewFragment.this.isTabView) {
                    return;
                }
                WebViewFragment.this.mainActivity.displayBottomNavigation(HeaderVisibility.INVISIBLE);
            }
        });
        AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) this.view.findViewById(R.id.seekBar);
        this.seekBar = appCompatSeekBar;
        this.dotsArray = new int[]{100, 75, 50, 25, 0};
        appCompatSeekBar.setOnSeekBarChangeListener(this.seekBarChangeListener);
        this.seekBar.setProgress(100);
        FrameLayout frameLayout = (FrameLayout) this.view.findViewById(R.id.fontSize);
        this.fontSizeView = frameLayout;
        frameLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        DFP dfp = this.dfp;
        if (dfp != null && dfp.getIu().length() > 0 && getContext() != null && !this.url.contains("firstinter=false&secondinter=false") && !this.url.contains("utm_source=news12_App&utm_campaign=Push_Notification&utm_medium=")) {
            Log.i("InterstitialManager", "webView load interstitial");
            loadInterstitial(this.dfp.getIu());
        } else if (this.url.contains("firstinter=false&secondinter=false") || this.url.contains("utm_source=news12_App&utm_medium=Push_Notification")) {
            onInterstitialFinished();
        }
        CustomWebViewClient customWebViewClient = new CustomWebViewClient(this, this.mainActivity, getTabId(), new CustomWebViewClient.CustomWebViewHandler() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.6
            @Override // com.channel2.mobile.ui.helpers.CustomWebViewClient.CustomWebViewHandler
            public void onUserClicked() {
                if (WebViewFragment.this.isWebViewMarginTop) {
                    return;
                }
                WebViewFragment.this.isWebViewMarginTop = true;
                ((FrameLayout.LayoutParams) WebViewFragment.this.web_view_container.getLayoutParams()).topMargin = -(WebViewFragment.this.mainActivity.getStatusBarHeight() - WebViewFragment.this.mainActivity.getNotchHeight());
                WebViewFragment.this.web_view_container.requestLayout();
            }

            @Override // com.channel2.mobile.ui.helpers.CustomWebViewClient.CustomWebViewHandler
            public void onLoaded() {
                if (WebViewFragment.this.didInterstitialFinised || WebViewFragment.this.webView.getAlpha() >= 1.0f) {
                    return;
                }
                if (WebViewFragment.this.dfp == null || WebViewFragment.this.dfp.getIu().length() == 0) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (WebViewFragment.this.didInterstitialFinised) {
                                return;
                            }
                            WebViewFragment.this.didInterstitialFinised = true;
                            WebViewFragment.this.canReload();
                        }
                    }, 5000L);
                }
            }

            @Override // com.channel2.mobile.ui.helpers.CustomWebViewClient.CustomWebViewHandler
            public void onOpenUrl(String str) throws JSONException {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                LinkItem linkItem = new LinkItem(jSONObject);
                linkItem.setMako_ref_comp("article");
                RoutingManager.goToNextScreen(WebViewFragment.this.getFragmentContainerId(), linkItem, WebViewFragment.this.getTabId(), WebViewFragment.this.mainActivity, null);
            }
        });
        this.customWebViewClient = customWebViewClient;
        this.webView.setWebViewClient(customWebViewClient);
        fragmentOnResume(this.mainActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void canReload() {
        Log.i("canReload", "isPageReady = " + this.isPageReady + " didInterstitialFinised = " + this.didInterstitialFinised + " reloaded = " + this.reloaded);
        if (this.isPageReady && this.didInterstitialFinised && !this.reloaded) {
            Log.i("canReload", "enter");
            this.reloaded = true;
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.setDuration(100);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.7
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (WebViewFragment.this.webView != null) {
                        WebViewFragment.this.webView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                    if (WebViewFragment.this.adView != null) {
                        WebViewFragment.this.adView.setAlpha(1.0f);
                    }
                }
            });
            valueAnimatorOfFloat.start();
            ((FrameLayout.LayoutParams) this.web_view_container.getLayoutParams()).topMargin = -(this.mainActivity.getStatusBarHeight() - this.mainActivity.getNotchHeight());
            this.web_view_container.requestLayout();
        }
    }

    private void loadBanner() throws JSONException {
        AdManagerAdRequest adManagerAdRequestBuild;
        AdManagerAdView adManagerAdView = new AdManagerAdView(requireContext());
        this.adView = adManagerAdView;
        adManagerAdView.setFocusable(false);
        this.adView.clearFocus();
        String str = this.dfp.getIu() + MainConfig.main.getCurrentParam("appBannerAdUnit");
        DFP dfp = new DFP(str, this.dfp.getCustParams(), this.dfp.getMakoPage());
        Log.i("ADDEUBG:", "bottom banner  : " + str);
        this.adView.setAdUnitId(str);
        this.adView.setAdSizes(AdSize.BANNER, AdSize.FLUID);
        this.adView.setAlpha(0.0f);
        AdManagerAdRequest.Builder builder = new AdManagerAdRequest.Builder();
        Utils.setDfpCustParams(builder, dfp);
        try {
            builder.setContentUrl(Utils.getDesktopUrl(this.url));
            Log.i("ADDEUBG:", "bottom banner : " + Utils.getDesktopUrl(this.url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (MainConfig.main.getCurrentBooleanParam(getContext().getResources().getString(R.string.idx_enable))) {
            adManagerAdRequestBuild = AdManagerAdRequestUtils.buildWithPermutiveTargeting(builder, MainActivity.permutive);
            Log.i("permutive", "permutive_On");
        } else {
            adManagerAdRequestBuild = builder.build();
        }
        Log.i("custparams ", "APP BANNER : " + adManagerAdRequestBuild.getCustomTargeting() + "iu = " + dfp.getIu());
        this.adView.loadAd(adManagerAdRequestBuild);
        this.adView.setAdListener(new AdListener() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.8
            @Override // com.google.android.gms.ads.AdListener
            public void onAdClicked() {
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdOpened() {
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                WebViewFragment.this.bannerReady = true;
                if (WebViewFragment.this.mainActivity.getSupportFragmentManager().getFragments().get(WebViewFragment.this.mainActivity.getSupportFragmentManager().getFragments().size() - 1) instanceof WebViewFragment) {
                    WebViewFragment.this.mainActivity.adContainer.setVisibility(0);
                } else {
                    WebViewFragment.this.mainActivity.adContainer.setVisibility(8);
                }
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                if (WebViewFragment.this.mainActivity == null || WebViewFragment.this.mainActivity.adContainer == null) {
                    return;
                }
                WebViewFragment.this.mainActivity.adContainer.setVisibility(8);
            }
        });
        this.mainActivity.adContainer.addView(this.adView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadInterstitial(String str) throws JSONException, Resources.NotFoundException {
        if (this.url.contains("firstinter=false&secondinter=false") || this.url.contains("utm_source=news12_App&utm_medium=Push_Notification")) {
            return;
        }
        this.isInterstitial = true;
        if (this.dfp.getIu() != null && this.dfp.getIu().length() > 0) {
            if (this.isArticleView && getContext() != null) {
                loadBanner();
            }
            if (this.rootView != null) {
                for (int i = 0; i < this.rootView.getChildCount(); i++) {
                    this.rootView.getChildAt(i).startAnimation(AnimationUtils.loadAnimation(this.rootView.getContext(), R.anim.alpha));
                }
            }
            if (!DeepLinkManager.getInstance().isOpenedByLink().booleanValue()) {
                this.mainActivity.loadInterstitial(this.dfp, Utils.getDesktopUrl(this.url), this.showPreload);
                return;
            } else {
                onInterstitialFinished();
                return;
            }
        }
        Log.i("weview", "1");
        onInterstitialFinished();
    }

    private void onInterstitialFinished() {
        this.didInterstitialFinised = true;
        this.isInterstitial = true;
        DeepLinkManager.getInstance().setOpenedByLink(false);
        canReload();
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void onInterstitialAd() {
        super.onInterstitialAd();
        onInterstitialFinished();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayFontSizeView() {
        this.isFontSizeViewVisible = true;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.fontSizeView.setVisibility(0);
        valueAnimatorOfFloat.setDuration(300);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.9
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                WebViewFragment.this.fontSizeView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        valueAnimatorOfFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideFontSizeView() {
        this.isFontSizeViewVisible = false;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.setDuration(100);
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.10
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                WebViewFragment.this.fontSizeView.setVisibility(8);
            }
        });
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.11
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                WebViewFragment.this.fontSizeView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        valueAnimatorOfFloat.start();
    }

    private void setHeader() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        if (this.isArticleView) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(R.layout.header_article, (ViewGroup) null, false);
            FrameLayout frameLayout2 = (FrameLayout) frameLayout.findViewById(R.id.aa);
            ((FrameLayout) frameLayout.findViewById(R.id.share)).setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("text/plain");
                        intent.putExtra("android.intent.extra.SUBJECT", WebViewFragment.this.ogTitle);
                        if (WebViewFragment.this.ogUrl != null && WebViewFragment.this.ogUrl.length() > 0) {
                            intent.putExtra("android.intent.extra.TEXT", WebViewFragment.this.ogUrl);
                        } else {
                            intent.putExtra("android.intent.extra.TEXT", WebViewFragment.this.canonical);
                        }
                        WebViewFragment.this.startActivity(Intent.createChooser(intent, "שתף"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            frameLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        if (WebViewFragment.this.isFontSizeViewVisible) {
                            WebViewFragment.this.hideFontSizeView();
                        } else {
                            WebViewFragment.this.displayFontSizeView();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            this.mainActivity.navigationManager.addHeader(getTabId(), frameLayout);
            return;
        }
        FrameLayout frameLayout3 = (FrameLayout) layoutInflaterFrom.inflate(R.layout.header_lobby, (ViewGroup) null, false);
        ((ConstraintLayout) frameLayout3.findViewById(R.id.logoContainer)).setVisibility(0);
        this.channelTitle = (TextView) frameLayout3.findViewById(R.id.channelTitle);
        this.appLogo = (ImageView) frameLayout3.findViewById(R.id.appLogo);
        this.mainActivity.navigationManager.addHeader(getTabId(), frameLayout3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHeaderTitle(String str) {
        Tab tab = MainConfig.main.getFooter().tabs.get(this.id);
        this.appLogo.setVisibility(8);
        this.channelTitle.setVisibility(0);
        if (this.isTabView && tab.headerTitle.length() > 0) {
            this.channelTitle.setText(tab.headerTitle);
        } else {
            this.channelTitle.setText(str);
        }
    }

    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void setMakoCatDFP(final String str) {
            WebViewFragment.this.mainActivity.runOnUiThread(new Runnable() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.JavaScriptInterface.1
                @Override // java.lang.Runnable
                public void run() throws JSONException, Resources.NotFoundException {
                    String str2 = str;
                    if (str2 == null || str2.equals(AdError.UNDEFINED_DOMAIN)) {
                        return;
                    }
                    if ((WebViewFragment.this.getDfp() == null || WebViewFragment.this.getDfp().getIu().length() == 0) && WebViewFragment.this.getContext() != null) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            String string = jSONObject.getString("iu");
                            JSONObject jSONObject2 = jSONObject.getJSONObject("cust_params");
                            WebViewFragment.this.dfp = new DFP(string, jSONObject2, WebViewFragment.this.vcmID);
                            Log.i("InterstitialManager", "webView JS load interstitial");
                            WebViewFragment.this.loadInterstitial(str);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        @JavascriptInterface
        public void setCanonical(String str) {
            WebViewFragment.this.canonical = str;
        }

        @JavascriptInterface
        public void setOgUrl(String str) {
            Log.i("", str);
        }

        @JavascriptInterface
        public void setChannelName(final String str) {
            if (str == null || str.length() <= 0) {
                return;
            }
            WebViewFragment.this.mainActivity.runOnUiThread(new Runnable() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.JavaScriptInterface.2
                @Override // java.lang.Runnable
                public void run() {
                    if (WebViewFragment.this.headerTitle == null) {
                        WebViewFragment.this.headerTitle = str;
                        WebViewFragment.this.setHeaderTitle(str);
                    }
                }
            });
        }

        @JavascriptInterface
        public void setVcmID(final String str) {
            if (str == null || str.length() <= 0) {
                return;
            }
            WebViewFragment.this.mainActivity.runOnUiThread(new Runnable() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.JavaScriptInterface.3
                @Override // java.lang.Runnable
                public void run() {
                    if (WebViewFragment.this.vcmID == null) {
                        WebViewFragment.this.vcmID = str;
                    }
                }
            });
        }

        @JavascriptInterface
        public void setShareFields(String str, String str2, String str3, String str4) {
            if (WebViewFragment.this.ogTitle == null || WebViewFragment.this.ogTitle.length() == 0) {
                WebViewFragment.this.ogTitle = str;
                if (str2 == null || str2.length() <= 0) {
                    return;
                }
                WebViewFragment.this.ogUrl = str2 + "?utm_source=AndroidNews12&utm_medium=Share";
            }
        }

        @JavascriptInterface
        public void setPresentationArticle(String str) {
            if (str == null || str.length() <= 0) {
                return;
            }
            WebViewFragment.this.mainActivity.runOnUiThread(new Runnable() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.JavaScriptInterface.4
                @Override // java.lang.Runnable
                public void run() {
                    if (WebViewFragment.this.adView != null) {
                        WebViewFragment.this.adView.destroy();
                        WebViewFragment.this.adView.setAlpha(0.0f);
                        WebViewFragment.this.adView = null;
                        if (WebViewFragment.this.mainActivity.adContainer != null) {
                            WebViewFragment.this.mainActivity.adContainer.setVisibility(8);
                        }
                    }
                }
            });
        }

        @JavascriptInterface
        public void setFontSize(final String str) {
            WebViewFragment.this.mainActivity.runOnUiThread(new Runnable() { // from class: com.channel2.mobile.ui.webView.controllers.WebViewFragment.JavaScriptInterface.5
                @Override // java.lang.Runnable
                public void run() throws NumberFormatException {
                    String str2 = str;
                    if (str2 == null || str2.equals(AdError.UNDEFINED_DOMAIN) || WebViewFragment.this.getContext() == null) {
                        return;
                    }
                    try {
                        int i = Integer.parseInt(str);
                        if (i == 0 || i == WebViewFragment.this.fontSize) {
                            return;
                        }
                        WebViewFragment.this.fontSize = i;
                        int i2 = 100;
                        if (i != 100) {
                            if (i == 120) {
                                i2 = 75;
                            } else if (i == 130) {
                                i2 = 50;
                            } else if (i == 140) {
                                i2 = 25;
                            } else if (i == 150) {
                                i2 = 0;
                            }
                        }
                        WebViewFragment.this.seekBar.setProgress(i2);
                    } catch (Exception unused) {
                    }
                }
            });
        }
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void fragmentOnResume(Activity activity) {
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
                if (this.isNoHeader) {
                    this.mainActivity.enterFullScreen(this);
                    if (this.mainActivity.adContainer != null) {
                        this.mainActivity.adContainer.setVisibility(8);
                    }
                } else {
                    if (this.isArticleView && this.adView != null && this.bannerReady) {
                        this.mainActivity.adContainer.setVisibility(0);
                    } else {
                        this.view.setKeepScreenOn(false);
                        if (this.mainActivity.adContainer != null) {
                            this.mainActivity.adContainer.setVisibility(8);
                        }
                    }
                    this.mainActivity.exitFullScreen(this);
                }
                if (this.mainActivity.bottomNavigationView != null) {
                    this.mainActivity.bottomNavigationView.selectItem(getTabId());
                }
                this.mainActivity.setRequestedOrientation(1);
                if (!this.bannerReady || this.mainActivity.adContainer == null) {
                    return;
                }
                this.mainActivity.adContainer.setVisibility(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void scrollTop() {
        super.scrollTop();
        CustomWebView customWebView = this.webView;
        if (customWebView != null) {
            customWebView.scrollTo(0, 0);
        }
    }
}
