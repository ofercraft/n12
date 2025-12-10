package com.channel2.mobile.ui.helpers;

import android.content.Context;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.lobby.models.items.ObituaryItem;
import com.channel2.mobile.ui.network.ApiService;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ObituariesHelper {
    private static final ObituariesHelper instance = new ObituariesHelper();
    private ArrayList<ObituaryItem> items = new ArrayList<>();
    private int animationtime = 0;
    private int lastPositionDisplay = 0;
    private boolean componentOnScreen = false;

    public static ObituariesHelper getInstance() {
        return instance;
    }

    private ObituariesHelper() {
    }

    public void getData(Context context, final ObituariesHandler obituariesHandler) {
        ApiService.getJSONObjectAsync(MainConfig.main.getCurrentSource("getObituaries"), context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.helpers.ObituariesHelper.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) throws JSONException {
                try {
                    ObituariesHelper.this.animationtime = jSONObject.getInt("animationtime");
                    JSONArray jSONArray = jSONObject.getJSONArray(FirebaseAnalytics.Param.ITEMS);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        ObituariesHelper.this.items.add(new ObituaryItem(jSONObject2.getInt("ItemOrder"), jSONObject2.getString("ImgUrl"), jSONObject2.getString("mobileImgUrl"), jSONObject2.getString("link")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                obituariesHandler.onSuccess();
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str, int i) {
                obituariesHandler.onFailure();
            }
        });
    }

    public ArrayList<ObituaryItem> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<ObituaryItem> arrayList) {
        this.items = arrayList;
    }

    public int getAnimationtime() {
        return this.animationtime;
    }

    public void setAnimationtime(int i) {
        this.animationtime = i;
    }

    public int getLastPositionDisplay() {
        return this.lastPositionDisplay;
    }

    public void setLastPositionDisplay(int i) {
        this.lastPositionDisplay = i;
    }

    public boolean isComponentOnScreen() {
        return this.componentOnScreen;
    }

    public void setComponentOnScreen(boolean z) {
        this.componentOnScreen = z;
    }
}
