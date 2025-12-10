package com.channel2.mobile.ui.lobby.models.ads;

import android.content.Context;
import android.util.Log;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.models.teasers.LobbyTeaser;
import com.channel2.mobile.ui.network.ApiService;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.OnAdManagerAdViewLoadedListener;
import com.google.android.gms.ads.nativead.NativeCustomFormatAd;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.permutive.android.ads.AdManagerAdRequestUtils;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LobbyAd extends LobbyTeaser {
    private static AdArrivedListener adArrivedListener;
    public static final LobbyAd instance = new LobbyAd();
    private NativeCustomFormatAd ad;
    public AdStatus adStatus;
    private AdManagerAdView adView;
    private String caption;
    public DFP dfp;
    private String friendlyUrl;
    private String impUrl;
    private int index;
    private boolean isSponsoredAdReady;
    private String url;
    public NativeAdFeedroll view;

    enum AdStatus {
        AdLoaded,
        AdFailed
    }

    static /* synthetic */ void lambda$loadNNativeAds$1(NativeCustomFormatAd nativeCustomFormatAd, String str) {
    }

    public LobbyAd(JSONObject jSONObject, JSONObject jSONObject2, Context context) throws JSONException {
        super(jSONObject, context);
        this.view = null;
        this.friendlyUrl = "";
        this.adStatus = null;
        this.isSponsoredAdReady = false;
        this.friendlyUrl = jSONObject2.optString("friendlyUrl");
        setLobbyItemType(jSONObject.optString("adType"));
        setUrl(jSONObject.optString("url"));
        setDFP(jSONObject, jSONObject2);
        setCaption(jSONObject.optString("caption"));
        setIndex(jSONObject.optInt(FirebaseAnalytics.Param.INDEX));
        if (context != null) {
            if (jSONObject.optString("adType").equals("banner") || jSONObject.optString("adType").equals("fluid")) {
                setAdView(context, jSONObject.optString("adType"));
            } else if (jSONObject.optString("adType").equals("sponsored")) {
                loadSponsoredAd(context);
            } else {
                loadNNativeAds(context);
            }
        }
    }

    private void setDFP(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONObject jSONObject3 = new JSONObject(jSONObject.getString("url"));
            this.dfp = new DFP(jSONObject3.optString("iu"), jSONObject3.optJSONObject("cust_params"), jSONObject2.getString("channelVcmId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LobbyAd() {
        this.view = null;
        this.friendlyUrl = "";
        this.adStatus = null;
        this.isSponsoredAdReady = false;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        String strOptString;
        try {
            strOptString = new JSONObject(str).optString("iu");
        } catch (JSONException e) {
            e.printStackTrace();
            strOptString = "";
        }
        this.url = strOptString;
    }

    public String getImpUrl() {
        return this.impUrl;
    }

    public void setImpUrl(String str) {
        this.impUrl = str;
    }

    @Override // com.channel2.mobile.ui.lobby.models.teasers.LobbyTeaser
    public String getCaption() {
        return this.caption;
    }

    @Override // com.channel2.mobile.ui.lobby.models.teasers.LobbyTeaser
    public void setCaption(String str) {
        this.caption = str;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public AdManagerAdView getAdView() {
        return this.adView;
    }

    private void setAdView(Context context, String str) throws JSONException {
        AdManagerAdRequest adManagerAdRequestBuild;
        AdManagerAdView adManagerAdView = new AdManagerAdView(context);
        this.adView = adManagerAdView;
        adManagerAdView.setFocusable(false);
        this.adView.clearFocus();
        if (str.equals("banner")) {
            this.adView.setAdSizes(AdSize.MEDIUM_RECTANGLE, new AdSize(336, 280), new AdSize(300, 250), AdSize.FLUID, AdSize.BANNER);
        } else {
            this.adView.setAdSizes(AdSize.FLUID, new AdSize(340, 191));
        }
        this.adView.setAdUnitId(this.dfp.iu);
        AdManagerAdRequest.Builder builder = new AdManagerAdRequest.Builder();
        Utils.setDfpCustParams(builder, this.dfp);
        try {
            builder.setContentUrl(Utils.getDesktopUrl(this.friendlyUrl));
            Log.i("ADDEUBG:", "lobby banner : " + Utils.getDesktopUrl(this.friendlyUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (MainConfig.main.getCurrentBooleanParam(context.getResources().getString(R.string.idx_enable))) {
            adManagerAdRequestBuild = AdManagerAdRequestUtils.buildWithPermutiveTargeting(builder, MainActivity.permutive);
            Log.i("permutive", "permutive_On");
        } else {
            adManagerAdRequestBuild = builder.build();
        }
        this.adView.loadAd(adManagerAdRequestBuild);
        this.adView.setAlpha(0.0f);
        this.adView.setAdListener(new AdListener() { // from class: com.channel2.mobile.ui.lobby.models.ads.LobbyAd.1
            @Override // com.google.android.gms.ads.AdListener
            public void onAdClicked() {
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdOpened() {
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                Log.d("banner", "onAdLoaded" + LobbyAd.this.getUrl());
                LobbyAd.this.adStatus = AdStatus.AdLoaded;
                if (LobbyAd.adArrivedListener != null) {
                    LobbyAd.adArrivedListener.onAdReady(LobbyAd.this);
                }
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.d("banner", "onAdFailedToLoad " + loadAdError);
                LobbyAd.this.adStatus = AdStatus.AdFailed;
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
                Log.d("banner", "onAdClosed");
            }
        });
    }

    public NativeCustomFormatAd getAd() {
        return this.ad;
    }

    private void loadNNativeAds(Context context) throws JSONException {
        AdManagerAdRequest adManagerAdRequestBuild;
        AdLoader adLoaderBuild = new AdLoader.Builder(context, getUrl()).forCustomFormatAd("11835143", new NativeCustomFormatAd.OnCustomFormatAdLoadedListener() { // from class: com.channel2.mobile.ui.lobby.models.ads.LobbyAd$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.ads.nativead.NativeCustomFormatAd.OnCustomFormatAdLoadedListener
            public final void onCustomFormatAdLoaded(NativeCustomFormatAd nativeCustomFormatAd) {
                this.f$0.lambda$loadNNativeAds$0(nativeCustomFormatAd);
            }
        }, new NativeCustomFormatAd.OnCustomClickListener() { // from class: com.channel2.mobile.ui.lobby.models.ads.LobbyAd$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.ads.nativead.NativeCustomFormatAd.OnCustomClickListener
            public final void onCustomClick(NativeCustomFormatAd nativeCustomFormatAd, String str) {
                LobbyAd.lambda$loadNNativeAds$1(nativeCustomFormatAd, str);
            }
        }).forAdManagerAdView(new OnAdManagerAdViewLoadedListener() { // from class: com.channel2.mobile.ui.lobby.models.ads.LobbyAd$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.ads.formats.OnAdManagerAdViewLoadedListener
            public final void onAdManagerAdViewLoaded(AdManagerAdView adManagerAdView) {
                this.f$0.lambda$loadNNativeAds$2(adManagerAdView);
            }
        }, new AdSize(340, 191)).withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).setCustomControlsRequested(true).build()).build()).build();
        AdManagerAdRequest.Builder builder = new AdManagerAdRequest.Builder();
        Utils.setDfpCustParams(builder, this.dfp);
        try {
            builder.setContentUrl(Utils.getDesktopUrl(this.friendlyUrl));
            Log.i("ADDEUBG:", "native banner : " + Utils.getDesktopUrl(this.friendlyUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (MainConfig.main.getCurrentBooleanParam(context.getResources().getString(R.string.idx_enable))) {
            adManagerAdRequestBuild = AdManagerAdRequestUtils.buildWithPermutiveTargeting(builder, MainActivity.permutive);
            Log.i("permutive", "permutive_On");
        } else {
            adManagerAdRequestBuild = builder.build();
        }
        adLoaderBuild.loadAd(adManagerAdRequestBuild);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadNNativeAds$0(NativeCustomFormatAd nativeCustomFormatAd) {
        Log.d("NativeAdFeedrollNew", "onAdLoaded" + getUrl());
        this.ad = nativeCustomFormatAd;
        NativeAdFeedroll nativeAdFeedroll = this.view;
        if (nativeAdFeedroll != null) {
            nativeAdFeedroll.updateAd(null, nativeCustomFormatAd);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadNNativeAds$2(AdManagerAdView adManagerAdView) {
        Log.d("NativeAdFeedrollNew", "onAdLoaded" + getUrl());
        this.adView = adManagerAdView;
        AdArrivedListener adArrivedListener2 = adArrivedListener;
        if (adArrivedListener2 != null) {
            adArrivedListener2.onAdReady(this);
        }
        NativeAdFeedroll nativeAdFeedroll = this.view;
        if (nativeAdFeedroll != null) {
            nativeAdFeedroll.updateAd(this.adView, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSponsoredAd(final Context context) {
        ApiService.getJSONObjectAsync(getUrl(), context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.lobby.models.ads.LobbyAd.2
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str, int i) {
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("sponsored-ad");
                if (jSONObjectOptJSONObject != null) {
                    LobbyAd.this.setTitle(jSONObjectOptJSONObject.optString("title"));
                    LobbyAd.this.setFlachText(jSONObjectOptJSONObject.optString("flachText"));
                    LobbyAd.this.setImage(jSONObjectOptJSONObject.optString("image"));
                    LobbyAd.this.setClickUrl(jSONObjectOptJSONObject.optString("click-url"));
                    String strOptString = jSONObjectOptJSONObject.optString("imp-url");
                    if (strOptString.length() > 0 && !strOptString.equalsIgnoreCase(AbstractJsonLexerKt.NULL)) {
                        LobbyAd.this.setImpUrl(strOptString);
                    }
                    String strOptString2 = jSONObjectOptJSONObject.optString("wrapper");
                    if (strOptString2.length() > 0) {
                        LobbyAd.this.url = strOptString2;
                        LobbyAd.this.loadSponsoredAd(context);
                    } else {
                        LobbyAd.this.isSponsoredAdReady = true;
                    }
                    LobbyAd.adArrivedListener.onAdReady(LobbyAd.this);
                }
            }
        });
    }

    public void setAdArrivedListener(AdArrivedListener adArrivedListener2) {
        adArrivedListener = adArrivedListener2;
    }

    public boolean isSponsoredAdReady() {
        return this.isSponsoredAdReady;
    }

    public AdStatus getAdStatus() {
        return this.adStatus;
    }

    public void setAdStatus(AdStatus adStatus) {
        this.adStatus = adStatus;
    }
}
