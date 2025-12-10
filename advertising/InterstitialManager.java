package com.channel2.mobile.ui.advertising;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.configs.models.Main;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.models.ads.DFP;
import com.channel2.mobile.ui.pushNotification.DeepLinkManager;
import com.channel2.mobile.ui.splash.MyApplication;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.mako.kscore.ksmeasurements.model.item.ErrorItem$$ExternalSyntheticBackport0;
import com.permutive.android.ads.AdManagerAdRequestUtils;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class InterstitialManager extends FullScreenContentCallback {
    private static InterstitialManager instance;
    public static long timeTestStart;
    private boolean isPreloadEnable;
    private AdManagerInterstitialAd preloadPublisherInterstitialAd;
    private AdManagerInterstitialAd publisherInterstitialAd;
    private Handler timerHandler;
    private Runnable timerRunnable;
    private String TAG = "InterstitialManager";
    private boolean isTimerFinished = false;
    private boolean itemPreload = true;
    private DFP nextDFP = null;
    private boolean isPreloadRequest = false;
    public MutableLiveData<InterstitialStatus> interstitialStateLiveData = new MutableLiveData<>();

    public enum ShowPreload {
        NOT_SET,
        TRUE,
        FALSE
    }

    private InterstitialManager() {
        this.isPreloadEnable = false;
        try {
            this.isPreloadEnable = MainConfig.main.getCurrentBooleanParam("interstitial_preload_enable");
            this.interstitialStateLiveData.setValue(InterstitialStatus.NO_AD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InterstitialManager getInstance() {
        if (instance == null) {
            instance = new InterstitialManager();
        }
        return instance;
    }

    public void createInterstitial(DFP dfp, Context context, String str, Boolean bool) {
        Main main;
        String str2;
        this.interstitialStateLiveData.setValue(InterstitialStatus.LOADING);
        if (DeepLinkManager.getInstance().getUrl() != null && DeepLinkManager.getInstance().getUrl().contains("firstinter=false&secondinter=false")) {
            this.interstitialStateLiveData.setValue(InterstitialStatus.FAILED);
            DeepLinkManager.getInstance().killInstance();
            return;
        }
        this.isPreloadRequest = false;
        this.itemPreload = bool.booleanValue();
        if (bool.booleanValue()) {
            this.nextDFP = new DFP(dfp.getIu(), dfp.getCustParams(), dfp.getMakoPage());
            AdManagerInterstitialAd adManagerInterstitialAd = this.preloadPublisherInterstitialAd;
            if (adManagerInterstitialAd != null) {
                this.publisherInterstitialAd = adManagerInterstitialAd;
                adManagerInterstitialAd.setFullScreenContentCallback(this);
                if (!ErrorItem$$ExternalSyntheticBackport0.m(this.preloadPublisherInterstitialAd.getResponseInfo().toString())) {
                    Log.i(this.TAG, "preload is loaded , display from preload");
                    this.interstitialStateLiveData.setValue(InterstitialStatus.READY);
                } else {
                    Log.i(this.TAG, "preload is NOT loaded , start timer and show after receive ad");
                    this.isPreloadRequest = false;
                    startTimer(MainConfig.main.getCurrentIntParam("interstitalTimeout"));
                }
                this.preloadPublisherInterstitialAd = null;
                return;
            }
            Log.i(this.TAG, "preload is null , iu = " + this.nextDFP.getIu());
            bool = false;
        }
        if (dfp.getIu().isEmpty()) {
            main = MainConfig.main;
            str2 = "mainInterstitialTimeout";
        } else {
            main = MainConfig.main;
            str2 = "interstitialTimeout";
        }
        startTimer(main.getCurrentIntParam(str2));
        dfp.setIu(dfp.getIu().isEmpty() ? MainConfig.main.getCurrentParam("homePageIu") : dfp.getIu());
        createRequest(dfp, str, context, bool.booleanValue());
    }

    public boolean hasInterstitialReady() {
        AdManagerInterstitialAd adManagerInterstitialAd = this.preloadPublisherInterstitialAd;
        return (adManagerInterstitialAd == null || ErrorItem$$ExternalSyntheticBackport0.m(adManagerInterstitialAd.getResponseInfo().toString())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void preparePreloadedInterstitial() {
        try {
            if (this.isPreloadEnable) {
                this.interstitialStateLiveData.setValue(InterstitialStatus.PRELOAD);
                this.publisherInterstitialAd = null;
                this.preloadPublisherInterstitialAd = null;
                if (this.nextDFP == null) {
                    this.nextDFP = new DFP();
                }
                if (this.nextDFP.getIu().isEmpty()) {
                    this.nextDFP.setMakoPage("homepage");
                    this.nextDFP.setIu(MainConfig.main.getCurrentParam("homePageIu") + MainConfig.main.getCurrentParam("secondPreloadUI"));
                }
                Log.i(this.TAG, "prepare preloaded , iu = " + this.nextDFP.getIu());
                this.isPreloadRequest = true;
                createRequest(this.nextDFP, MainConfig.main.getCurrentParam("dfpBaseContentURL"), null, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createRequest(DFP dfp, String str, Context context, final boolean z) throws JSONException {
        AdManagerAdRequest adManagerAdRequestBuild;
        if (context == null) {
            context = MyApplication.getInstance().getApplicationContext();
        }
        DFP dfp2 = new DFP(dfp.getIu() + MainConfig.main.getCurrentParam("interstitialAdUnit"), dfp.getCustParams(), dfp.getMakoPage());
        AdManagerAdRequest.Builder builder = new AdManagerAdRequest.Builder();
        Utils.setDfpCustParams(builder, dfp2);
        try {
            builder.setContentUrl(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (MainConfig.main.getCurrentBooleanParam(context.getResources().getString(R.string.idx_enable))) {
            adManagerAdRequestBuild = AdManagerAdRequestUtils.buildWithPermutiveTargeting(builder, MyApplication.getInstance().getPermutive());
        } else {
            adManagerAdRequestBuild = builder.build();
        }
        AdManagerInterstitialAd.load(context, dfp2.getIu(), adManagerAdRequestBuild, new AdManagerInterstitialAdLoadCallback() { // from class: com.channel2.mobile.ui.advertising.InterstitialManager.1
            @Override // com.google.android.gms.ads.AdLoadCallback
            public void onAdLoaded(AdManagerInterstitialAd adManagerInterstitialAd) {
                super.onAdLoaded((AnonymousClass1) adManagerInterstitialAd);
                adManagerInterstitialAd.setFullScreenContentCallback(InterstitialManager.this);
                if (z) {
                    InterstitialManager.this.preloadPublisherInterstitialAd = adManagerInterstitialAd;
                } else {
                    InterstitialManager.this.publisherInterstitialAd = adManagerInterstitialAd;
                }
                Log.i(InterstitialManager.this.TAG, "onAdLoaded , timer finished = " + InterstitialManager.this.isTimerFinished + " isPreloadRequest = " + InterstitialManager.this.isPreloadRequest);
                if (InterstitialManager.this.isPreloadRequest) {
                    return;
                }
                try {
                    if (!InterstitialManager.this.isTimerFinished) {
                        InterstitialManager.this.interstitialStateLiveData.setValue(InterstitialStatus.READY);
                        if (InterstitialManager.this.timerHandler != null) {
                            InterstitialManager.this.timerHandler.removeCallbacks(InterstitialManager.this.timerRunnable);
                            InterstitialManager.this.timerHandler = null;
                        }
                    } else {
                        InterstitialManager.this.preparePreloadedInterstitial();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.google.android.gms.ads.AdLoadCallback
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                if (z) {
                    InterstitialManager.this.preloadPublisherInterstitialAd = null;
                }
                InterstitialManager.this.publisherInterstitialAd = null;
                if (InterstitialManager.this.isPreloadRequest) {
                    return;
                }
                InterstitialManager.this.interstitialStateLiveData.setValue(InterstitialStatus.FAILED);
                if (InterstitialManager.this.timerHandler != null) {
                    InterstitialManager.this.timerHandler.removeCallbacks(InterstitialManager.this.timerRunnable);
                    InterstitialManager.this.timerHandler = null;
                }
                InterstitialManager.this.preparePreloadedInterstitial();
            }
        });
        Log.i(this.TAG, "createInterstitial , iu = " + dfp2.getIu() + " contentUrl = " + str);
        Log.i("custparams ", "interstitial : " + adManagerAdRequestBuild.getCustomTargeting().toString() + "iu = " + dfp2.getIu());
    }

    public void showInterstitial(Activity activity) {
        if (this.publisherInterstitialAd != null) {
            Log.i(this.TAG, "show interstitial");
            if (!ErrorItem$$ExternalSyntheticBackport0.m(this.publisherInterstitialAd.getResponseInfo().toString())) {
                this.publisherInterstitialAd.show(activity);
            } else {
                this.interstitialStateLiveData.setValue(InterstitialStatus.FAILED);
            }
        }
    }

    private void startTimer(long j) {
        if (j == 0) {
            j = 5000;
        }
        this.isTimerFinished = false;
        this.timerHandler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() { // from class: com.channel2.mobile.ui.advertising.InterstitialManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$startTimer$0();
            }
        };
        this.timerRunnable = runnable;
        this.timerHandler.postDelayed(runnable, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startTimer$0() {
        Log.i(this.TAG, "timeout");
        this.isTimerFinished = true;
        this.interstitialStateLiveData.setValue(InterstitialStatus.TIMEOUT);
        AdManagerInterstitialAd adManagerInterstitialAd = this.publisherInterstitialAd;
        if (adManagerInterstitialAd != null) {
            adManagerInterstitialAd.setFullScreenContentCallback(null);
        }
        Handler handler = this.timerHandler;
        if (handler != null) {
            handler.removeCallbacks(this.timerRunnable);
            this.timerHandler = null;
        }
        preparePreloadedInterstitial();
    }

    public void resetApp() {
        Log.i(this.TAG, "reset app");
        this.publisherInterstitialAd = null;
        this.preloadPublisherInterstitialAd = null;
    }

    @Override // com.google.android.gms.ads.FullScreenContentCallback
    public void onAdShowedFullScreenContent() {
        this.interstitialStateLiveData.setValue(InterstitialStatus.SHOW);
    }

    @Override // com.google.android.gms.ads.FullScreenContentCallback
    public void onAdDismissedFullScreenContent() {
        super.onAdDismissedFullScreenContent();
        if (this.isPreloadRequest) {
            return;
        }
        this.interstitialStateLiveData.setValue(InterstitialStatus.CLOSED);
        Log.i(this.TAG, "onAdClosed");
        this.publisherInterstitialAd = null;
        preparePreloadedInterstitial();
    }

    @Override // com.google.android.gms.ads.FullScreenContentCallback
    public void onAdFailedToShowFullScreenContent(AdError adError) {
        super.onAdFailedToShowFullScreenContent(adError);
        if (!this.isPreloadRequest) {
            this.interstitialStateLiveData.setValue(InterstitialStatus.FAILED);
            Handler handler = this.timerHandler;
            if (handler != null) {
                handler.removeCallbacks(this.timerRunnable);
                this.timerHandler = null;
            }
            preparePreloadedInterstitial();
        }
        this.publisherInterstitialAd = null;
    }
}
