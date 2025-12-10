package com.channel2.mobile.ui.settings.models;

import android.content.Context;
import com.channel2.mobile.ui.network.ApiService;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FetchSettings {

    public interface ResponseHandler {
        void onFailure();

        void onSuccess(ArrayList<SettingsItem> arrayList);
    }

    public FetchSettings(final Context context, String str, final ResponseHandler responseHandler) {
        ApiService.getJSONObjectAsync(str, context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.settings.models.FetchSettings.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) throws JSONException {
                ArrayList<SettingsItem> arrayList = new ArrayList<>();
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("settings");
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(new SettingsItem(jSONArrayOptJSONArray.optJSONObject(i)));
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                    jSONObject2.put("type", "version");
                    jSONObject2.put("displayName", "גרסה " + str2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                arrayList.add(new SettingsItem(jSONObject2));
                responseHandler.onSuccess(arrayList);
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str2, int i) {
                responseHandler.onFailure();
            }
        });
    }
}
