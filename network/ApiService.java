package com.channel2.mobile.ui.network;

import android.content.Context;
import android.util.Log;
import android.webkit.WebSettings;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.channel2.mobile.ui.Chats.controllers.ChatManager;
import com.channel2.mobile.ui.configs.MainConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ApiService {
    private static RequestQueue queue;

    public interface AsyncHTTPJSONResponseHandler {
        void onFailure(String str, int i);

        void onSuccess(JSONObject jSONObject) throws JSONException;
    }

    public interface AsyncHTTPStringResponseHandler {
        void onFailure(String str, int i);

        void onSuccess(String str);
    }

    public interface ResponseHandler {
        void onSuccess();
    }

    public static void getJSONObjectAsync(final String str, final Context context, final AsyncHTTPJSONResponseHandler asyncHTTPJSONResponseHandler) {
        if (queue == null) {
            createRequestQueue(context);
        }
        Log.i("OMRI get", str);
        GetHebrewStringRequest getHebrewStringRequest = new GetHebrewStringRequest(0, str, null, new Response.Listener<String>() { // from class: com.channel2.mobile.ui.network.ApiService.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(String str2) throws JSONException {
                Log.i("OMRI onResponse", str);
                if (str2 != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        AsyncHTTPJSONResponseHandler asyncHTTPJSONResponseHandler2 = asyncHTTPJSONResponseHandler;
                        if (asyncHTTPJSONResponseHandler2 != null) {
                            asyncHTTPJSONResponseHandler2.onSuccess(jSONObject);
                            return;
                        } else {
                            asyncHTTPJSONResponseHandler2.onFailure("", 0);
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            JSONArray jSONArray = new JSONArray(str2);
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.putOpt("root", jSONArray);
                            AsyncHTTPJSONResponseHandler asyncHTTPJSONResponseHandler3 = asyncHTTPJSONResponseHandler;
                            if (asyncHTTPJSONResponseHandler3 != null) {
                                asyncHTTPJSONResponseHandler3.onSuccess(jSONObject2);
                                return;
                            }
                            return;
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                            AsyncHTTPJSONResponseHandler asyncHTTPJSONResponseHandler4 = asyncHTTPJSONResponseHandler;
                            if (asyncHTTPJSONResponseHandler4 != null) {
                                asyncHTTPJSONResponseHandler4.onFailure(e2.getMessage(), 0);
                                return;
                            }
                            return;
                        }
                    }
                }
                AsyncHTTPJSONResponseHandler asyncHTTPJSONResponseHandler5 = asyncHTTPJSONResponseHandler;
                if (asyncHTTPJSONResponseHandler5 != null) {
                    asyncHTTPJSONResponseHandler5.onFailure("Null response", 0);
                }
            }
        }, new Response.ErrorListener() { // from class: com.channel2.mobile.ui.network.ApiService.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                if (asyncHTTPJSONResponseHandler != null) {
                    Log.i("OMRI onErrorResponse", str);
                    asyncHTTPJSONResponseHandler.onFailure(volleyError.getMessage(), volleyError.networkResponse == null ? 0 : volleyError.networkResponse.statusCode);
                }
            }
        }) { // from class: com.channel2.mobile.ui.network.ApiService.3
            @Override // com.android.volley.Request
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap map = new HashMap();
                map.put("User-agent", ApiService.getDefaultUserAgentString(context));
                return map;
            }
        };
        getHebrewStringRequest.setShouldCache(false);
        queue.add(getHebrewStringRequest);
    }

    public static void getStringAsync(String str, final Context context, final AsyncHTTPStringResponseHandler asyncHTTPStringResponseHandler) {
        if (queue == null) {
            createRequestQueue(context);
        }
        GetHebrewStringRequest getHebrewStringRequest = new GetHebrewStringRequest(0, str, null, new Response.Listener<String>() { // from class: com.channel2.mobile.ui.network.ApiService.4
            @Override // com.android.volley.Response.Listener
            public void onResponse(String str2) {
                if (str2 != null && asyncHTTPStringResponseHandler != null) {
                    asyncHTTPStringResponseHandler.onSuccess(str2.trim());
                } else {
                    AsyncHTTPStringResponseHandler asyncHTTPStringResponseHandler2 = asyncHTTPStringResponseHandler;
                    if (asyncHTTPStringResponseHandler2 != null) {
                        asyncHTTPStringResponseHandler2.onFailure("Null response", 0);
                    }
                }
            }
        }, new Response.ErrorListener() { // from class: com.channel2.mobile.ui.network.ApiService.5
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                AsyncHTTPStringResponseHandler asyncHTTPStringResponseHandler2 = asyncHTTPStringResponseHandler;
                if (asyncHTTPStringResponseHandler2 != null) {
                    asyncHTTPStringResponseHandler2.onFailure(volleyError.getMessage(), volleyError.networkResponse == null ? 0 : volleyError.networkResponse.statusCode);
                }
            }
        }) { // from class: com.channel2.mobile.ui.network.ApiService.6
            @Override // com.android.volley.Request
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap map = new HashMap();
                map.put("User-agent", ApiService.getDefaultUserAgentString(context));
                return map;
            }
        };
        getHebrewStringRequest.setRetryPolicy(new DefaultRetryPolicy(2500, 3, 1.0f));
        getHebrewStringRequest.setShouldCache(false);
        queue.add(getHebrewStringRequest);
    }

    public static JSONObject getRouteData(String str, Context context) throws ExecutionException, InterruptedException, RuntimeException {
        RequestFuture requestFutureNewFuture = RequestFuture.newFuture();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(0, str, null, requestFutureNewFuture, requestFutureNewFuture);
        if (queue == null) {
            createRequestQueue(context);
        }
        queue.add(jsonObjectRequest);
        return (JSONObject) requestFutureNewFuture.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getDefaultUserAgentString(Context context) {
        try {
            return WebSettings.getDefaultUserAgent(context);
        } catch (Exception unused) {
            return "";
        }
    }

    private static void createRequestQueue(Context context) {
        RequestQueue requestQueueNewRequestQueue = Volley.newRequestQueue(context);
        queue = requestQueueNewRequestQueue;
        requestQueueNewRequestQueue.start();
    }

    public static void fetchChatItems(Context context, int i, int i2, final ChatManager.ResponseHandler responseHandler) {
        getJSONObjectAsync(MainConfig.main.getCurrentSource("desk12MessagesApi").replace("%PAGE%", String.valueOf(i)).replace("%TOPICID%", String.valueOf(i2)), context, new AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.network.ApiService.7
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) throws JSONException {
                if (jSONObject.length() == 0 || (jSONObject.length() == 1 && jSONObject.has("tempAjaxError"))) {
                    responseHandler.onFailure();
                } else {
                    responseHandler.onSuccess(jSONObject);
                }
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str, int i3) {
                responseHandler.onFailure();
            }
        });
    }

    public static void getChatIndexes(Context context, int i, final ChatManager.ResponseHandler responseHandler) {
        getJSONObjectAsync(MainConfig.main.getCurrentSource("desk12MessagesIndexApi").replace("%TOPICID%", String.valueOf(i)), context, new AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.network.ApiService.8
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) throws JSONException {
                responseHandler.onSuccess(jSONObject);
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str, int i2) {
                responseHandler.onFailure();
            }
        });
    }

    public static void getChatItemById(Context context, long j, int i, final ChatManager.ResponseHandler responseHandler) {
        getJSONObjectAsync(MainConfig.main.getCurrentSource("desk12MessageByIdApi").replace("%MESSAGEID%", String.valueOf(j)).replace("%TOPICID%", String.valueOf(i)), context, new AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.network.ApiService.9
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) throws JSONException {
                responseHandler.onSuccess(jSONObject);
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str, int i2) {
                responseHandler.onFailure();
            }
        });
    }

    public static void getChannelIdByUrl(Context context, String str, final AsyncHTTPJSONResponseHandler asyncHTTPJSONResponseHandler) {
        getJSONObjectAsync(MainConfig.main.getCurrentSource("ChannelIdByUrlApi").replace("%URL%", str), context, new AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.network.ApiService.10
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) throws JSONException {
                asyncHTTPJSONResponseHandler.onSuccess(jSONObject);
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str2, int i) {
                asyncHTTPJSONResponseHandler.onFailure(str2, i);
            }
        });
    }

    public static void post(String str, Context context, HashMap<String, String> map, final AsyncHTTPStringResponseHandler asyncHTTPStringResponseHandler) {
        if (queue == null) {
            createRequestQueue(context);
        }
        GetHebrewStringRequest getHebrewStringRequest = new GetHebrewStringRequest(1, str, map, new Response.Listener<String>() { // from class: com.channel2.mobile.ui.network.ApiService.11
            @Override // com.android.volley.Response.Listener
            public void onResponse(String str2) {
                AsyncHTTPStringResponseHandler asyncHTTPStringResponseHandler2 = asyncHTTPStringResponseHandler;
                if (asyncHTTPStringResponseHandler2 != null) {
                    asyncHTTPStringResponseHandler2.onSuccess(str2);
                }
            }
        }, new Response.ErrorListener() { // from class: com.channel2.mobile.ui.network.ApiService.12
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                AsyncHTTPStringResponseHandler asyncHTTPStringResponseHandler2 = asyncHTTPStringResponseHandler;
                if (asyncHTTPStringResponseHandler2 != null) {
                    asyncHTTPStringResponseHandler2.onFailure(volleyError.getMessage(), volleyError.networkResponse == null ? 0 : volleyError.networkResponse.statusCode);
                }
            }
        });
        getHebrewStringRequest.setRetryPolicy(new DefaultRetryPolicy(2500, 3, 1.0f));
        getHebrewStringRequest.setShouldCache(false);
        queue.add(getHebrewStringRequest);
    }

    public static void postJsonRequest(String str, Context context, JSONObject jSONObject, final AsyncHTTPStringResponseHandler asyncHTTPStringResponseHandler) {
        try {
            Volley.newRequestQueue(context).add(new JsonObjectRequest(1, str, jSONObject, new Response.Listener() { // from class: com.channel2.mobile.ui.network.ApiService$$ExternalSyntheticLambda0
                @Override // com.android.volley.Response.Listener
                public final void onResponse(Object obj) {
                    ApiService.lambda$postJsonRequest$0(asyncHTTPStringResponseHandler, (JSONObject) obj);
                }
            }, new Response.ErrorListener() { // from class: com.channel2.mobile.ui.network.ApiService$$ExternalSyntheticLambda1
                @Override // com.android.volley.Response.ErrorListener
                public final void onErrorResponse(VolleyError volleyError) {
                    ApiService.lambda$postJsonRequest$1(asyncHTTPStringResponseHandler, volleyError);
                }
            }));
        } catch (Exception unused) {
        }
    }

    static /* synthetic */ void lambda$postJsonRequest$0(AsyncHTTPStringResponseHandler asyncHTTPStringResponseHandler, JSONObject jSONObject) {
        Log.d("checkDomo", "onSuccess: " + jSONObject);
        if (asyncHTTPStringResponseHandler != null) {
            asyncHTTPStringResponseHandler.onSuccess(jSONObject.toString());
        }
    }

    static /* synthetic */ void lambda$postJsonRequest$1(AsyncHTTPStringResponseHandler asyncHTTPStringResponseHandler, VolleyError volleyError) {
        Log.d("checkDomo", "onFailure: " + volleyError);
        if (asyncHTTPStringResponseHandler != null) {
            asyncHTTPStringResponseHandler.onFailure(volleyError.getMessage(), volleyError.networkResponse == null ? 0 : volleyError.networkResponse.statusCode);
        }
    }
}
