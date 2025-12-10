package com.channel2.mobile.ui.splash;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.advertising.InterstitialManager;
import com.channel2.mobile.ui.advertising.InterstitialStatus;
import com.channel2.mobile.ui.alerts.DisplayAlert;
import com.channel2.mobile.ui.alerts.VersionControlAlert;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.header.CheckHeaderStyle;
import com.channel2.mobile.ui.header.HeaderVisibility;
import com.channel2.mobile.ui.helpers.Orientation;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.liveStreaming.Live;
import com.channel2.mobile.ui.lobby.models.items.LinkItem;
import com.channel2.mobile.ui.pushNotification.DeepLinkManager;
import com.channel2.mobile.ui.pushNotification.FetchPushUrl;
import com.channel2.mobile.ui.routing.RoutingManager;
import com.channel2.mobile.ui.splash.AppStart;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.cast.MediaTrack;
import com.mako.kscore.ksmeasurements.model.item.ErrorItem$$ExternalSyntheticBackport0;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class SplashActivity extends AppCompatActivity {
    private static final String AF_DEV_KEY = "dvWNs9CHpSLsMZtUEgExyJ";
    private boolean mainActivityLoaded;
    private String deepLink = "";
    private String pushId = "";
    private boolean interstitialIsOpen = false;
    private boolean shouldInflateMainActivity = false;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) throws JSONException {
        String str;
        String str2;
        super.onCreate(bundle);
        try {
            MobileAds.initialize(this, new OnInitializationCompleteListener() { // from class: com.channel2.mobile.ui.splash.SplashActivity$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.ads.initialization.OnInitializationCompleteListener
                public final void onInitializationComplete(InitializationStatus initializationStatus) {
                    Log.i("OMRI", "onInitializationComplete");
                }
            });
        } catch (Exception unused) {
        }
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, new AppsFlyerConversionListener() { // from class: com.channel2.mobile.ui.splash.SplashActivity.1
            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onConversionDataSuccess(Map<String, Object> map) {
                for (String str3 : map.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + str3 + " = " + map.get(str3));
                }
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onConversionDataFail(String str3) {
                Log.d("LOG_TAG", "error getting conversion data: " + str3);
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onAppOpenAttribution(Map<String, String> map) {
                for (String str3 : map.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + str3 + " = " + map.get(str3));
                }
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onAttributionFailure(String str3) {
                Log.d("LOG_TAG", "error onAttributionFailure : " + str3);
            }
        }, getApplicationContext());
        AppsFlyerLib.getInstance().start(getApplication());
        setContentView(R.layout.activity_splash);
        Orientation.setOrientationState(Orientation.OrientationState.ORIENTATION_PORTRAIT);
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
        Uri data = getIntent().getData();
        final Bundle extras = getIntent().getExtras();
        setNotificationChannels();
        Live.setIsLive(false);
        if (data != null || extras != null) {
            if (data != null) {
                String string = data.toString();
                this.deepLink = string;
                this.deepLink = Utils.getLinkFromDeepLink(string);
                DeepLinkManager.getInstance().setPushId(this.pushId);
                DeepLinkManager.getInstance().setUrl(this.deepLink);
            }
            if (extras != null && extras.getString("url") != null) {
                this.deepLink = extras.getString("url");
                this.pushId = extras.getString("pushId");
                DeepLinkManager.getInstance().setPushId(this.pushId);
                DeepLinkManager.getInstance().setUrl(this.deepLink);
            }
        } else {
            this.deepLink = "";
        }
        Log.i("deepLink url=", this.deepLink);
        String str3 = this.deepLink;
        if (str3 != null && !ErrorItem$$ExternalSyntheticBackport0.m(str3)) {
            DeepLinkManager.getInstance().setOpenedByLink(Boolean.valueOf(isTaskRoot()));
        }
        if (isTaskRoot()) {
            new AppStart(getApplicationContext(), this.pushId, new AppStart.Handler() { // from class: com.channel2.mobile.ui.splash.SplashActivity.2
                @Override // com.channel2.mobile.ui.splash.AppStart.Handler
                public void onMainConfigLoaded() {
                    SplashActivity.this.registerObserver();
                }

                @Override // com.channel2.mobile.ui.splash.AppStart.Handler
                public void onFinished() {
                    if (CheckHeaderStyle.isTransparentHeader) {
                        MainConfig.main.getHeader().setHeaderPosition(HeaderVisibility.INVISIBLE);
                    }
                    if (extras != null && SplashActivity.this.deepLink != null && SplashActivity.this.deepLink.length() == 0 && SplashActivity.this.pushId != null && SplashActivity.this.pushId.length() > 0) {
                        new FetchPushUrl(SplashActivity.this.getApplicationContext(), SplashActivity.this.pushId, new FetchPushUrl.ResponseHandler() { // from class: com.channel2.mobile.ui.splash.SplashActivity.2.1
                            @Override // com.channel2.mobile.ui.pushNotification.FetchPushUrl.ResponseHandler
                            public void onSuccess(String str4) {
                                SplashActivity.this.deepLink = str4;
                                SplashActivity.this.shouldInflateMainActivity = true;
                                SplashActivity.this.openMainActivity();
                            }

                            @Override // com.channel2.mobile.ui.pushNotification.FetchPushUrl.ResponseHandler
                            public void onFailure() {
                                SplashActivity.this.shouldInflateMainActivity = true;
                                SplashActivity.this.openMainActivity();
                            }
                        });
                    } else {
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.splash.SplashActivity.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                Log.d("maavaron", "pushNotificationObject " + extras);
                                Log.d("maavaron", "deepLink " + SplashActivity.this.deepLink.length());
                                Log.d("maavaron", "pushId " + SplashActivity.this.pushId.length());
                                SplashActivity.this.shouldInflateMainActivity = true;
                                SplashActivity.this.openMainActivity();
                            }
                        }, 100L);
                    }
                }

                @Override // com.channel2.mobile.ui.splash.AppStart.Handler
                public void onFailure() throws JSONException {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("title", "נראה שאין חיבור לאינטרנט");
                        jSONObject.put(MediaTrack.ROLE_SUBTITLE, "מומלץ לבדוק את הגדרות הרשת");
                        jSONObject.put("updateButton", "נסה שוב");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new DisplayAlert(jSONObject, (ConstraintLayout) SplashActivity.this.findViewById(R.id.versionControlAlert), new VersionControlAlert.Handler() { // from class: com.channel2.mobile.ui.splash.SplashActivity.2.3
                        @Override // com.channel2.mobile.ui.alerts.VersionControlAlert.Handler
                        public void onClick() {
                            SplashActivity.this.finish();
                            SplashActivity.this.startActivity(SplashActivity.this.getIntent());
                        }
                    });
                }
            });
            return;
        }
        if (extras != null && (str = this.deepLink) != null && str.length() == 0 && (str2 = this.pushId) != null && str2.length() > 0) {
            new FetchPushUrl(getApplicationContext(), this.pushId, new FetchPushUrl.ResponseHandler() { // from class: com.channel2.mobile.ui.splash.SplashActivity.3
                @Override // com.channel2.mobile.ui.pushNotification.FetchPushUrl.ResponseHandler
                public void onSuccess(String str4) throws JSONException {
                    SplashActivity.this.deepLink = str4;
                    SplashActivity.this.proceed();
                }

                @Override // com.channel2.mobile.ui.pushNotification.FetchPushUrl.ResponseHandler
                public void onFailure() throws JSONException {
                    SplashActivity.this.proceed();
                }
            });
        } else {
            proceed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void proceed() throws JSONException {
        CustomFragment customFragment;
        if (MainConfig.appData != null && MainConfig.appData.getMainActivity() != null) {
            String str = this.deepLink;
            if (str != null && str.length() > 0) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", this.deepLink);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                LinkItem linkItem = new LinkItem(jSONObject);
                MainActivity mainActivity = MainConfig.appData.getMainActivity();
                int i = MainConfig.appData.getMainActivity().currentTab;
                if (mainActivity.fragments == null || (customFragment = mainActivity.fragments.get(i)) == null) {
                    return;
                }
                mainActivity.setRestartAppEnabled(false);
                RoutingManager.goToNextScreen(customFragment.getFragmentContainerId(), linkItem, i, mainActivity, null);
                finish();
                return;
            }
            finish();
            return;
        }
        restartApp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openMainActivity() {
        if (!this.shouldInflateMainActivity || this.interstitialIsOpen) {
            return;
        }
        this.shouldInflateMainActivity = false;
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.putExtra(SDKConstants.PARAM_DEEP_LINK, this.deepLink);
        intent.putExtra("pushId", this.pushId);
        Log.i("InterstitialManager", "openMainActivity()");
        startActivity(intent);
        InterstitialManager.getInstance().interstitialStateLiveData.removeObservers(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        this.mainActivityLoaded = true;
        killSplash();
    }

    private void restartApp() {
        Intent launchIntentForPackage = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        if (launchIntentForPackage != null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                launchIntentForPackage.putExtras(extras);
            }
            launchIntentForPackage.addFlags(32768);
            startActivity(launchIntentForPackage);
            finish();
        }
    }

    private void setNotificationChannels() {
        if (Build.VERSION.SDK_INT >= 26) {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/2131951621");
            NotificationChannel notificationChannel = new NotificationChannel("news", "news", 4);
            notificationChannel.setDescription("news");
            notificationChannel.setShowBadge(false);
            notificationChannel.enableLights(true);
            if (uri != null) {
                notificationChannel.setSound(uri, new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(5).setUsage(10).build());
            }
            NotificationChannel notificationChannel2 = new NotificationChannel("news_silent", "news_silent", 2);
            notificationChannel2.setShowBadge(false);
            notificationChannel2.enableLights(true);
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
                notificationManager.createNotificationChannel(notificationChannel2);
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    private void killSplash() {
        if (this.mainActivityLoaded) {
            finish();
        }
    }

    public void registerObserver() {
        InterstitialManager.getInstance().interstitialStateLiveData.observe(this, new Observer() { // from class: com.channel2.mobile.ui.splash.SplashActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$registerObserver$1((InterstitialStatus) obj);
            }
        });
    }

    /* renamed from: com.channel2.mobile.ui.splash.SplashActivity$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus;

        static {
            int[] iArr = new int[InterstitialStatus.values().length];
            $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus = iArr;
            try {
                iArr[InterstitialStatus.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[InterstitialStatus.SHOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[InterstitialStatus.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[InterstitialStatus.TIMEOUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[InterstitialStatus.CLOSED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$registerObserver$1(InterstitialStatus interstitialStatus) {
        int i = AnonymousClass4.$SwitchMap$com$channel2$mobile$ui$advertising$InterstitialStatus[interstitialStatus.ordinal()];
        if (i == 1) {
            InterstitialManager.getInstance().showInterstitial(this);
            return;
        }
        if (i == 2) {
            this.interstitialIsOpen = true;
            Log.i("InterstitialManager", "SplashActivity___isInterstitialFinished " + interstitialStatus);
            return;
        }
        if (i == 3 || i == 4 || i == 5) {
            this.interstitialIsOpen = false;
            openMainActivity();
            Log.i("InterstitialManager", "SplashActivity___isInterstitialFinished " + interstitialStatus);
        }
    }
}
