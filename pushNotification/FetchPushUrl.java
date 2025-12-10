package com.channel2.mobile.ui.pushNotification;

import android.content.Context;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.network.ApiService;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FetchPushUrl {

    public interface ResponseHandler {
        void onFailure();

        void onSuccess(String str);
    }

    public FetchPushUrl(Context context, String str, final ResponseHandler responseHandler) {
        try {
            if (MainConfig.main != null) {
                ApiService.getJSONObjectAsync(MainConfig.main.getCurrentSource("pushUrlApi").replace("%PUSH_ID%", str), context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.pushNotification.FetchPushUrl.1
                    @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
                    public void onSuccess(JSONObject jSONObject) {
                        String strOptString = jSONObject.optString("url");
                        if (strOptString.length() < 6) {
                            strOptString = "";
                        }
                        responseHandler.onSuccess(strOptString);
                    }

                    @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
                    public void onFailure(String str2, int i) {
                        responseHandler.onFailure();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
