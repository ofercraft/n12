package com.channel2.mobile.ui.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class GetHebrewStringRequest extends StringRequest {
    private static int mStatusCode;
    private HashMap<String, String> requestParams;

    public static int getStatusCode() {
        return mStatusCode;
    }

    GetHebrewStringRequest(int i, String str, HashMap<String, String> map, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(i, str, listener, errorListener);
        this.requestParams = map;
    }

    @Override // com.android.volley.Request
    public byte[] getBody() throws AuthFailureError {
        HashMap<String, String> map = this.requestParams;
        if (map != null && map.containsKey("data")) {
            return this.requestParams.get("data").getBytes();
        }
        return super.getBody();
    }

    @Override // com.android.volley.Request
    protected Map<String, String> getParams() throws AuthFailureError {
        HashMap<String, String> map = this.requestParams;
        if (map != null && (map.size() != 1 || !this.requestParams.containsKey("data"))) {
            return this.requestParams;
        }
        return super.getParams();
    }

    @Override // com.android.volley.toolbox.StringRequest, com.android.volley.Request
    protected Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            int i = networkResponse.statusCode;
            mStatusCode = i;
            if (i >= 400) {
                return Response.error(new VolleyError(networkResponse));
            }
            return Response.success(new String(networkResponse.data, "UTF-8"), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }
}
