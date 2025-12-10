package com.channel2.mobile.ui.header;

import android.content.Context;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.liveStreaming.CheckIsLive;
import com.channel2.mobile.ui.network.ApiService;
import com.facebook.internal.ServerProtocol;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CheckHeaderStyle {
    public static boolean isTransparentHeader;

    public interface ResponseHandler {
        void onFailure();

        void onSuccess();
    }

    public CheckHeaderStyle(final Context context, final ResponseHandler responseHandler) {
        ApiService.getJSONObjectAsync(MainConfig.main.getCurrentSource("headerStateApi"), context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.header.CheckHeaderStyle.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) {
                try {
                    new CheckIsLive(context, jSONObject.optJSONObject("itemLive"));
                    CheckHeaderStyle.isTransparentHeader = jSONObject.optBoolean(ServerProtocol.DIALOG_PARAM_STATE);
                    Header header = MainConfig.main.getHeader();
                    if (header != null && jSONObject.has("headerSkin") && ((JSONObject) Objects.requireNonNull(jSONObject.optJSONObject("headerSkin"))).has("mobileBackgroundColor")) {
                        header.mobileLogoImageUrl = ((JSONObject) Objects.requireNonNull(jSONObject.optJSONObject("headerSkin"))).optString("mobileLogoImage");
                        header.backgroundColorFromService = ((JSONObject) Objects.requireNonNull(jSONObject.optJSONObject("headerSkin"))).optString("mobileBackgroundColor");
                    }
                    responseHandler.onSuccess();
                } catch (Exception e) {
                    e.printStackTrace();
                    responseHandler.onFailure();
                }
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str, int i) {
                responseHandler.onFailure();
            }
        });
    }
}
