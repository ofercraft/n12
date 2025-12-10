package com.channel2.mobile.ui.lobby.models.firstReport;

import android.content.Context;
import com.channel2.mobile.ui.network.ApiService;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FetchFirstReport {

    public interface ResponseHandler {
        void onFailure();

        void onSuccess(LobbyFirstReport lobbyFirstReport);
    }

    public FetchFirstReport(Context context, String str, final ResponseHandler responseHandler) {
        ApiService.getJSONObjectAsync(str, context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.lobby.models.firstReport.FetchFirstReport.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) {
                responseHandler.onSuccess(new LobbyFirstReport(jSONObject.optJSONObject("PrimaryReport")));
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str2, int i) {
                responseHandler.onFailure();
            }
        });
    }
}
