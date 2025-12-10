package com.channel2.mobile.ui.explore.models;

import android.content.Context;
import com.channel2.mobile.ui.network.ApiService;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FetchSearchResults {

    public interface ResponseHandler {
        void onFailure();

        void onSuccess(ArrayList<SearchResultsItem> arrayList);
    }

    public FetchSearchResults(Context context, String str, final ResponseHandler responseHandler) {
        ApiService.getJSONObjectAsync(str, context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.explore.models.FetchSearchResults.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) {
                ArrayList<SearchResultsItem> arrayList = new ArrayList<>();
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("searchResults");
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(new SearchResultsItem(jSONArrayOptJSONArray.optJSONObject(i)));
                }
                responseHandler.onSuccess(arrayList);
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str2, int i) {
                responseHandler.onFailure();
            }
        });
    }
}
