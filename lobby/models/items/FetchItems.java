package com.channel2.mobile.ui.lobby.models.items;

import android.content.Context;
import android.util.Log;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.header.Header;
import com.channel2.mobile.ui.header.HeaderVisibility;
import com.channel2.mobile.ui.lobby.models.firstReport.FetchFirstReport;
import com.channel2.mobile.ui.lobby.models.firstReport.LobbyFirstReport;
import com.channel2.mobile.ui.network.ApiService;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FetchItems {

    public interface ResponseHandler {

        /* renamed from: com.channel2.mobile.ui.lobby.models.items.FetchItems$ResponseHandler$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onFailure(ResponseHandler _this) {
            }

            public static void $default$onSuccess(ResponseHandler _this) {
            }

            public static void $default$onSuccess(ResponseHandler _this, JSONObject jSONObject, LobbyFirstReport lobbyFirstReport) {
            }
        }

        void onFailure();

        void onSuccess();

        void onSuccess(JSONObject jSONObject, LobbyFirstReport lobbyFirstReport);
    }

    public FetchItems(final Context context, String str, final boolean z, final ResponseHandler responseHandler) {
        Log.d("FetchItems", "url: " + str);
        ApiService.getJSONObjectAsync(str, context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.lobby.models.items.FetchItems.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(final JSONObject jSONObject) {
                Header header = MainConfig.main.getHeader();
                if (z && header.getHeaderPosition() == HeaderVisibility.VISIBLE) {
                    new FetchFirstReport(context, MainConfig.main.getCurrentSource("firstReportApi"), new FetchFirstReport.ResponseHandler() { // from class: com.channel2.mobile.ui.lobby.models.items.FetchItems.1.1
                        @Override // com.channel2.mobile.ui.lobby.models.firstReport.FetchFirstReport.ResponseHandler
                        public void onSuccess(LobbyFirstReport lobbyFirstReport) {
                            responseHandler.onSuccess(jSONObject, lobbyFirstReport);
                        }

                        @Override // com.channel2.mobile.ui.lobby.models.firstReport.FetchFirstReport.ResponseHandler
                        public void onFailure() {
                            responseHandler.onSuccess(jSONObject, null);
                        }
                    });
                } else {
                    responseHandler.onSuccess(jSONObject, null);
                }
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str2, int i) {
                responseHandler.onFailure();
            }
        });
    }
}
