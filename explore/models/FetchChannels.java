package com.channel2.mobile.ui.explore.models;

import android.content.Context;
import com.channel2.mobile.ui.network.ApiService;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FetchChannels {

    public interface ResponseHandler {
        void onFailure();

        void onSuccess(ArrayList<Channel> arrayList, String str);
    }

    public FetchChannels(Context context, String str, final ResponseHandler responseHandler) {
        ApiService.getJSONObjectAsync(str, context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.explore.models.FetchChannels.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) throws JSONException {
                ArrayList<Channel> arrayList = new ArrayList<>();
                String strOptString = jSONObject.optString("headerTitle");
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("channels");
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("displayName", strOptString);
                    jSONObject2.put("link", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                arrayList.add(new Channel(jSONObject2));
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(new Channel(jSONArrayOptJSONArray.optJSONObject(i)));
                }
                responseHandler.onSuccess(arrayList, strOptString);
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str2, int i) {
                responseHandler.onFailure();
            }
        });
    }
}
