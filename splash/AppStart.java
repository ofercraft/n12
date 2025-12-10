package com.channel2.mobile.ui.splash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.channel2.mobile.ui.Chats.controllers.ChatManager;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.advertising.InterstitialManager;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.header.CheckHeaderStyle;
import com.channel2.mobile.ui.header.Header;
import com.channel2.mobile.ui.helpers.DictionaryUtils;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.models.ads.DFP;
import com.channel2.mobile.ui.network.ApiService;
import com.channel2.mobile.ui.pushNotification.PushTagsManager;
import com.channel2.mobile.ui.reports.ReportAppStart;
import com.channel2.mobile.ui.reports.TransparentWebView;
import com.channel2.mobile.ui.routing.RoutingManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mako.kscore.helpers.InitCallback;
import com.mako.kscore.helpers.Platform;
import com.mako.kscore.ksmeasurements.KsMeasurementsManager;
import com.mako.kscore.ksplayer.helpers.ItemType;
import com.mako.kscore.ksplayer.helpers.managers.KsPlayerManager;
import com.mako.kscore.ksplayer.model.CustomLoader;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;

/* loaded from: classes2.dex */
public class AppStart {
    private Context context;
    private int counter;
    private Handler handler;
    private boolean isHeaderStyleLoaded;
    private boolean isKsMeasurementsLoaded;
    private boolean isMessagesReceived;
    private boolean isPlayerLoaded;
    private boolean isPushTagsLoaded;
    private String pushId;

    public interface Handler {
        void onFailure();

        void onFinished();

        void onMainConfigLoaded();
    }

    AppStart(Context context, String str, Handler handler) {
        this.handler = handler;
        this.pushId = str;
        this.context = context;
        loadMainConfig();
    }

    private void loadMainConfig() {
        Log.v(this.context.getResources().getString(R.string.log_tag), "start loadMainConfig");
        new MainConfig().load(this.context, new MainConfig.ResponseHandler() { // from class: com.channel2.mobile.ui.splash.AppStart.1
            @Override // com.channel2.mobile.ui.configs.MainConfig.ResponseHandler
            public void onSuccess() throws UnsupportedEncodingException {
                Log.v(AppStart.this.context.getResources().getString(R.string.log_tag), "loadMainConfig onSuccess");
                DictionaryUtils.initDictionary(AppStart.this.context);
                AppStart.this.handler.onMainConfigLoaded();
                AppStart.this.initCoolaData();
                AppStart.this.handleFCM();
                AppStart.this.loadInterstitial();
                AppStart.this.loadRoutingConfig();
                AppStart.this.initKsPlayer();
                AppStart.this.checkHeaderStyle();
                AppStart.this.loadChatItems();
                AppStart.this.getPushTags();
                AppStart.this.initKsMeasurements();
            }

            @Override // com.channel2.mobile.ui.configs.MainConfig.ResponseHandler
            public void onFailure() {
                AppStart.this.handler.onFailure();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleToken(String str) {
        Context context = this.context;
        String stringFromPreferences = Utils.getStringFromPreferences(context, context.getResources().getString(R.string.regId));
        if (stringFromPreferences != null && stringFromPreferences.length() > 0) {
            if (!stringFromPreferences.equals(str)) {
                Context context2 = this.context;
                final String stringFromPreferences2 = Utils.getStringFromPreferences(context2, context2.getResources().getString(R.string.pushTags));
                if (stringFromPreferences2 != null && stringFromPreferences2.length() > 0 && !stringFromPreferences2.equals(this.context.getResources().getString(R.string.removeAll))) {
                    FirebaseMessaging.getInstance().subscribeToTopic(stringFromPreferences2).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.channel2.mobile.ui.splash.AppStart.2
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public void onComplete(Task<Void> task) {
                            Log.i("Firebase", "subscribeToTopic " + stringFromPreferences2);
                        }
                    });
                } else {
                    Log.i("Firebase", "user is not register to tags");
                }
            } else {
                Log.i("Firebase", "firebase token isn't changed");
            }
        }
        Log.d("FCM-splash", str);
        Context context3 = this.context;
        Utils.saveStringToPreferences(context3, context3.getResources().getString(R.string.regId), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFCM() {
        try {
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() { // from class: com.channel2.mobile.ui.splash.AppStart.3
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<String> task) {
                    if (!task.isSuccessful()) {
                        Log.w(FirebaseMessaging.INSTANCE_ID_SCOPE, "Fetching FCM registration token failed", task.getException());
                    } else {
                        AppStart.this.handleToken(task.getResult());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadRoutingConfig() {
        Log.v(this.context.getResources().getString(R.string.log_tag), "start loadRoutingConfig");
        new RoutingManager().load(this.context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadChatItems() {
        Log.d("checkAppStart", "loadChatItems: chatTopics.size = " + ChatManager.getInstance().chatTopics.size());
        Log.v(this.context.getResources().getString(R.string.log_tag), "start loadChatMessages");
        ChatManager.getInstance().resetApp();
        for (int i = 0; i < ChatManager.getInstance().chatTopics.size(); i++) {
            ChatManager.getInstance().fetchItems(1, ChatManager.getInstance().chatTopics.get(i).getTopicId(), this.context, new ApiService.ResponseHandler() { // from class: com.channel2.mobile.ui.splash.AppStart$$ExternalSyntheticLambda0
                @Override // com.channel2.mobile.ui.network.ApiService.ResponseHandler
                public final void onSuccess() {
                    this.f$0.lambda$loadChatItems$0();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadChatItems$0() {
        int i = this.counter + 1;
        this.counter = i;
        if (i == ChatManager.getInstance().chatTopics.size()) {
            Log.v(this.context.getResources().getString(R.string.log_tag), "loadChatMessages onSuccess");
            this.isMessagesReceived = true;
            checkIfFinished();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkHeaderStyle() {
        Log.v(this.context.getResources().getString(R.string.log_tag), "start loadHeaderStyle");
        new CheckHeaderStyle(this.context, new CheckHeaderStyle.ResponseHandler() { // from class: com.channel2.mobile.ui.splash.AppStart.4
            @Override // com.channel2.mobile.ui.header.CheckHeaderStyle.ResponseHandler
            public void onSuccess() {
                Log.v(AppStart.this.context.getResources().getString(R.string.log_tag), "loadHeaderStyle onSuccess");
                final Header header = MainConfig.main.getHeader();
                if (!header.mobileLogoImageUrl.isEmpty()) {
                    Glide.with(AppStart.this.context).asBitmap().load(header.mobileLogoImageUrl).into((RequestBuilder<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.channel2.mobile.ui.splash.AppStart.4.1
                        @Override // com.bumptech.glide.request.target.Target
                        public void onLoadCleared(Drawable drawable) {
                        }

                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                        }

                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                            header.mobileLogoImage = bitmap;
                            AppStart.this.isHeaderStyleLoaded = true;
                            AppStart.this.checkIfFinished();
                        }
                    });
                } else {
                    AppStart.this.isHeaderStyleLoaded = true;
                    AppStart.this.checkIfFinished();
                }
            }

            @Override // com.channel2.mobile.ui.header.CheckHeaderStyle.ResponseHandler
            public void onFailure() {
                AppStart.this.isHeaderStyleLoaded = true;
                AppStart.this.checkIfFinished();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getPushTags() {
        Context context = this.context;
        if (Utils.getStringFromPreferences(context, context.getResources().getString(R.string.pushTags)).equals("")) {
            new PushTagsManager(this.context).getTag(this.context, true, new PushTagsManager.Handler() { // from class: com.channel2.mobile.ui.splash.AppStart.5
                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onSuccess(String str) {
                    AppStart.this.isPushTagsLoaded = true;
                    AppStart.this.checkIfFinished();
                }

                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onFailure() {
                    AppStart.this.isPushTagsLoaded = true;
                    AppStart.this.checkIfFinished();
                }
            });
        } else {
            this.isPushTagsLoaded = true;
            checkIfFinished();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIfFinished() {
        Log.d("checkAppStart", "checkIfFinished: isHeaderStyleFinish = " + this.isHeaderStyleLoaded + " | isMessagesReceived = " + this.isMessagesReceived + " | isPlayerLoaded = " + this.isPlayerLoaded + " | isKsMeasurementsLoaded = " + this.isKsMeasurementsLoaded + " | isPushTagsLoaded = " + this.isPushTagsLoaded);
        if (this.isHeaderStyleLoaded && this.isMessagesReceived && this.isPlayerLoaded && this.isPushTagsLoaded && this.isKsMeasurementsLoaded) {
            reportAppStart();
            this.handler.onFinished();
        }
    }

    private void reportAppStart() {
        new ReportAppStart(this.context, this.pushId, MainConfig.main.getCurrentSource("reportAppUsage"));
        TransparentWebView.report(this.context, MainConfig.main.getCurrentSource("localStorageVariables"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadInterstitial() {
        InterstitialManager.getInstance().createInterstitial(new DFP("", "homepage"), this.context, MainConfig.main.getCurrentParam("dfpBaseContentURL"), false);
    }

    public void initKsMeasurements() {
        try {
            KsMeasurementsManager.INSTANCE.init(this.context, MainConfig.main.getDomoEnabled().booleanValue(), Platform.INSTANCE.getMOBILE(), MainConfig.appData.getAppId(), MainConfig.appData.getUserId(), false, MainConfig.main.getTrackerVersion(), MainConfig.main.getEventSchemas(), MainConfig.main.getInitSession(), MainConfig.main.getEventSchemasFallback(), MainConfig.main.getInitSessionFallback(), MainConfig.main.getInitSessionFallback(), MainConfig.main.getRequestTimeout(), new InitCallback() { // from class: com.channel2.mobile.ui.splash.AppStart.6
                @Override // com.mako.kscore.helpers.InitCallback
                public void onSuccess() {
                    AppStart.this.isKsMeasurementsLoaded = true;
                    AppStart.this.checkIfFinished();
                }

                @Override // com.mako.kscore.helpers.InitCallback
                public void onError(int i, boolean z) {
                    AppStart.this.isKsMeasurementsLoaded = true;
                    AppStart.this.checkIfFinished();
                }
            });
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initKsPlayer() {
        EnumMap<ItemType, Integer> enumMap = new EnumMap<>(ItemType.class);
        enumMap.put((EnumMap<ItemType, Integer>) ItemType.VOD, (ItemType) Integer.valueOf(R.layout.player_vod));
        enumMap.put((EnumMap<ItemType, Integer>) ItemType.LIVE, (ItemType) Integer.valueOf(R.layout.player_live));
        KsPlayerManager.INSTANCE.init(this.context, Platform.INSTANCE.getMOBILE(), MainConfig.main.getCurrentParam("player_consumer"), MainConfig.main.getCurrentParam("cast_consumer"), MainConfig.appData.getUserId(), MainConfig.appData.getAppId(), MainConfig.main.getCurrentSource("player"), enumMap, new CustomLoader(this.context, R.drawable.loader, 110, 110), MainConfig.main.getCooladataEnable().booleanValue(), new InitCallback() { // from class: com.channel2.mobile.ui.splash.AppStart.7
            @Override // com.mako.kscore.helpers.InitCallback
            public void onSuccess() {
                AppStart.this.isPlayerLoaded = true;
                AppStart.this.checkIfFinished();
            }

            @Override // com.mako.kscore.helpers.InitCallback
            public void onError(int i, boolean z) {
                AppStart.this.isPlayerLoaded = true;
                AppStart.this.checkIfFinished();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCoolaData() {
        String currentParam;
        try {
            currentParam = MainConfig.main.getCurrentParam("cooldataKey");
        } catch (Exception unused) {
            currentParam = "";
        }
        if (MainConfig.main.getCooladataEnable().booleanValue()) {
            try {
                ((MyApplication) this.context.getApplicationContext()).initCoolaData(currentParam);
            } catch (Exception unused2) {
            }
        }
    }
}
