package com.channel2.mobile.ui.reports;

import android.content.Context;
import com.channel2.mobile.ui.helpers.DictionaryUtils;
import com.channel2.mobile.ui.network.ApiService;
import java.io.UnsupportedEncodingException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ReportAppStart {
    public ReportAppStart(Context context, String str, String str2) throws UnsupportedEncodingException {
        DictionaryUtils.initDictionary(context);
        ApiService.getJSONObjectAsync(DictionaryUtils.replaceDictionaryValues(str.length() > 0 ? str2.replace("%PUSH_TRIGGER%", str) : str2), context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.reports.ReportAppStart.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str3, int i) {
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) {
            }
        });
    }
}
