package com.channel2.mobile.ui.configs;

import android.content.Context;
import com.channel2.mobile.ui.configs.models.Main;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.network.ApiService;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MainConfig {
    public static AppData appData;
    public static Main main;

    public interface ResponseHandler {
        void onFailure();

        void onSuccess();
    }

    public void load(final Context context, final ResponseHandler responseHandler) {
        String appVersionName = Utils.getAppVersionName(context);
        String str = "http://apps.mako.co.il/mobile/config/android/news12/" + appVersionName + "/mainConfig.json";
        String str2 = "http://apps.mako.co.il/mobile/_test/android/news12/" + appVersionName + "/mainConfig.json";
        if (!Utils.getBoolFromPreferences(context, "isProdEnvironment")) {
            str = str2;
        }
        ApiService.getJSONObjectAsync(str, context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.configs.MainConfig.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) {
                MainConfig.main = Main.fromJsonObject(jSONObject, context);
                MainConfig.appData = new AppData(context);
                responseHandler.onSuccess();
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str3, int i) {
                responseHandler.onFailure();
            }
        });
    }
}
