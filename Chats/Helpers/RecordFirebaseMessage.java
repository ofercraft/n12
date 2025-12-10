package com.channel2.mobile.ui.Chats.Helpers;

import android.content.Context;
import com.channel2.mobile.ui.helpers.DictionaryUtils;
import com.channel2.mobile.ui.network.ApiService;

/* loaded from: classes2.dex */
public class RecordFirebaseMessage {

    public interface ResponseHandler {
        void onFailure();

        void onSuccess(String str);
    }

    public RecordFirebaseMessage(Context context, String str, ResponseHandler responseHandler) {
        ApiService.getStringAsync(DictionaryUtils.replaceDictionaryValues(str), context, new ApiService.AsyncHTTPStringResponseHandler() { // from class: com.channel2.mobile.ui.Chats.Helpers.RecordFirebaseMessage.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPStringResponseHandler
            public void onFailure(String str2, int i) {
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPStringResponseHandler
            public void onSuccess(String str2) {
            }
        });
    }
}
