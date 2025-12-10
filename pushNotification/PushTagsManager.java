package com.channel2.mobile.ui.pushNotification;

import android.content.Context;
import android.util.Log;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.helpers.DictionaryUtils;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.network.ApiService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PushTagsManager {
    private String current;
    private String important;
    private String removeAll;

    public interface Handler {
        void onFailure();

        void onSuccess(String str);
    }

    public PushTagsManager(Context context) {
        this.important = "";
        this.current = "";
        this.removeAll = "";
        JSONObject currentJsonParam = MainConfig.main.getCurrentJsonParam("pushTags");
        if (currentJsonParam != null) {
            this.important = currentJsonParam.optString(context.getResources().getString(R.string.important));
            this.current = currentJsonParam.optString(context.getResources().getString(R.string.current));
            this.removeAll = currentJsonParam.optString(context.getResources().getString(R.string.removeAll));
        }
    }

    public void getTag(Context context, boolean z, Handler handler) {
        try {
            String stringFromPreferences = Utils.getStringFromPreferences(context, context.getResources().getString(R.string.pushTags));
            if (!Utils.isExistInPreferences(context, "subscribedToInternalPush")) {
                Utils.saveBoolToPreferences(context, "subscribedToInternalPush", true);
                Utils.saveBoolToPreferences(context, context.getResources().getString(R.string.insidePush), true);
                Utils.saveBoolToPreferences(context, context.getResources().getString(R.string.important), true);
                Utils.saveBoolToPreferences(context, context.getResources().getString(R.string.current), true);
                Utils.saveBoolToPreferences(context, context.getResources().getString(R.string.redDot), true);
                if (stringFromPreferences != null && stringFromPreferences.equals(this.removeAll)) {
                    Utils.saveBoolToPreferences(context, context.getResources().getString(R.string.outsidePush), false);
                } else {
                    Utils.saveBoolToPreferences(context, context.getResources().getString(R.string.outsidePush), true);
                }
            }
            if (stringFromPreferences != null && stringFromPreferences.length() > 0 && !stringFromPreferences.equals(this.removeAll)) {
                String str = stringFromPreferences.split(",")[0];
                subscribeToTopic(str, new Handler() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.1
                    @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                    public void onFailure() {
                    }

                    @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                    public void onSuccess(String str2) {
                    }
                });
                handler.onSuccess(str);
                return;
            }
            getTagFromServer(context, z, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTagFromServer(final Context context, final boolean z, final Handler handler) {
        try {
            ApiService.getStringAsync(DictionaryUtils.replaceDictionaryValues(MainConfig.main.getCurrentSource("getPushTags")), context, new ApiService.AsyncHTTPStringResponseHandler() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.2
                @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPStringResponseHandler
                public void onSuccess(String str) {
                    if (str != null && str.length() > 0) {
                        String str2 = str.split(",")[0];
                        Context context2 = context;
                        Utils.saveStringToPreferences(context2, context2.getResources().getString(R.string.pushTags), str2);
                        PushTagsManager.this.subscribeToTopic(str2, new Handler() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.2.1
                            @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                            public void onFailure() {
                            }

                            @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                            public void onSuccess(String str3) {
                            }
                        });
                        handler.onSuccess(str2);
                        return;
                    }
                    Context context3 = context;
                    String stringFromPreferences = Utils.getStringFromPreferences(context3, context3.getResources().getString(R.string.pushTags));
                    if (z || !stringFromPreferences.equals(PushTagsManager.this.removeAll)) {
                        PushTagsManager pushTagsManager = PushTagsManager.this;
                        pushTagsManager.subscribeToTopic(pushTagsManager.important, new Handler() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.2.2
                            @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                            public void onFailure() {
                            }

                            @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                            public void onSuccess(String str3) {
                            }
                        });
                        PushTagsManager pushTagsManager2 = PushTagsManager.this;
                        pushTagsManager2.updateApi(context, pushTagsManager2.important);
                        handler.onSuccess(PushTagsManager.this.important);
                        return;
                    }
                    handler.onSuccess("");
                }

                @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPStringResponseHandler
                public void onFailure(String str, int i) {
                    handler.onFailure();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTag(final Context context, final String str, final Handler handler) {
        try {
            ApiService.getStringAsync(DictionaryUtils.replaceDictionaryValues(MainConfig.main.getCurrentSource("setPushTags").replace("%PUSH_TAGS%", str)), context, new ApiService.AsyncHTTPStringResponseHandler() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.3
                @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPStringResponseHandler
                public void onSuccess(String str2) {
                    Context context2 = context;
                    Utils.saveStringToPreferences(context2, context2.getResources().getString(R.string.pushTags), str);
                    handler.onSuccess(str);
                }

                @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPStringResponseHandler
                public void onFailure(String str2, int i) {
                    handler.onFailure();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void subscribeToTopic(String str, final Handler handler) {
        try {
            if (!str.equals(this.removeAll)) {
                if (str.equals(this.important)) {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(this.current).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.4
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public void onComplete(Task<Void> task) {
                            Log.i("PUSH_TAG", "unsubscribeFromTopic " + PushTagsManager.this.current);
                            FirebaseMessaging.getInstance().subscribeToTopic(PushTagsManager.this.important).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.4.1
                                @Override // com.google.android.gms.tasks.OnCompleteListener
                                public void onComplete(Task<Void> task2) {
                                    Log.i("PUSH_TAG", "subscribeToTopic " + PushTagsManager.this.important);
                                    handler.onSuccess("");
                                }
                            });
                        }
                    });
                } else {
                    FirebaseMessaging.getInstance().subscribeToTopic(this.important).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.5
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public void onComplete(Task<Void> task) {
                            Log.i("PUSH_TAG", "subscribeToTopic " + PushTagsManager.this.important);
                            FirebaseMessaging.getInstance().subscribeToTopic(PushTagsManager.this.current).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.5.1
                                @Override // com.google.android.gms.tasks.OnCompleteListener
                                public void onComplete(Task<Void> task2) {
                                    Log.i("PUSH_TAG", "subscribeToTopic " + PushTagsManager.this.current);
                                    handler.onSuccess("");
                                }
                            });
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unsubscribeFromAllTopics(final Handler handler) {
        try {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(this.current).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.6
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<Void> task) {
                    Log.i("PUSH_TAG", "unsubscribeFromTopic " + PushTagsManager.this.current);
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(PushTagsManager.this.important).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.6.1
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public void onComplete(Task<Void> task2) {
                            Log.i("PUSH_TAG", "unsubscribeFromTopic " + PushTagsManager.this.important);
                            handler.onSuccess("");
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateApi(Context context, final String str) {
        try {
            new PushTagsManager(context).addTag(context, str, new Handler() { // from class: com.channel2.mobile.ui.pushNotification.PushTagsManager.7
                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onSuccess(String str2) {
                    Log.i("PUSH_TAG", "updateApi " + str + " finished");
                }

                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onFailure() {
                    Log.i("PUSH_TAG", "ERROR updateApi " + str);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
