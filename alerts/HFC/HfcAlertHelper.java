package com.channel2.mobile.ui.alerts.HFC;

import android.content.Context;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.network.ApiService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HfcAlertHelper {
    private static final HfcAlertHelper instance = new HfcAlertHelper();
    private String title;
    public HFCAlert currentAlert = null;
    private Map<Long, HFCAlert> alerts = new HashMap();
    private ArrayList<String> cities = new ArrayList<>();
    private String citiesStr = "";
    int currentIndex = 0;
    int maxCitiesChar = 33;

    public static HfcAlertHelper getInstance() {
        return instance;
    }

    public void getData(Context context, final HfcAlertHelpersHandler hfcAlertHelpersHandler) {
        ApiService.getJSONObjectAsync(MainConfig.main.getCurrentSource("getHFCAlert"), context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.alerts.HFC.HfcAlertHelper.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) throws JSONException {
                try {
                    HFCAlert hFCAlert = new HFCAlert(jSONObject.getLong("id"), false);
                    JSONArray jSONArray = jSONObject.getJSONArray("data");
                    if (jSONArray.length() == 0) {
                        HfcAlertHelper.this.handleResponse(hfcAlertHelpersHandler);
                        return;
                    }
                    if (HfcAlertHelper.this.hasAlert(hFCAlert.getId())) {
                        hfcAlertHelpersHandler.onFailure();
                        return;
                    }
                    if (HfcAlertHelper.this.currentAlert != null && hFCAlert.getId() == HfcAlertHelper.this.currentAlert.getId()) {
                        HfcAlertHelper.this.handleResponse(hfcAlertHelpersHandler);
                        return;
                    }
                    HfcAlertHelper.this.currentAlert = hFCAlert;
                    HfcAlertHelper.this.title = jSONObject.getString("title");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        HfcAlertHelper.this.cities.add(jSONArray.getString(i));
                    }
                    hfcAlertHelpersHandler.onSuccess();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str, int i) {
                hfcAlertHelpersHandler.onFailure();
            }
        });
    }

    public boolean hasAlert(long j) {
        return this.alerts.get(Long.valueOf(j)) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResponse(HfcAlertHelpersHandler hfcAlertHelpersHandler) {
        HFCAlert hFCAlert = this.currentAlert;
        if (hFCAlert != null && !hFCAlert.isFinished()) {
            hfcAlertHelpersHandler.onSuccessWithoutChange();
        } else {
            hfcAlertHelpersHandler.onFailure();
        }
    }

    public void resetAlert() {
        try {
            HFCAlert hFCAlert = this.currentAlert;
            if (hFCAlert != null) {
                hFCAlert.setFinished(true);
                this.alerts.put(Long.valueOf(this.currentAlert.getId()), this.currentAlert);
                this.currentAlert = null;
                this.cities.clear();
                this.cities = new ArrayList<>();
                this.currentIndex = 0;
                this.citiesStr = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCities() {
        return this.cities;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public String generateCities() {
        if (this.cities.size() == 0) {
            this.currentAlert.setFinished(true);
            return this.citiesStr;
        }
        StringBuilder sb = new StringBuilder();
        int size = this.cities.size();
        ArrayList<String> arrayList = new ArrayList<>(this.cities);
        int i = 0;
        while (true) {
            if (i < size && sb.length() < this.maxCitiesChar) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(sb.toString());
                String str = this.cities.get(i);
                sb2.append(str);
                if (str.length() >= this.maxCitiesChar && i == 0) {
                    sb.append(str);
                    sb.append(",");
                    sb.append(StringUtils.SPACE);
                    arrayList.remove(0);
                    break;
                }
                if (sb2.length() < this.maxCitiesChar) {
                    sb.append(str);
                    sb.append(",");
                    sb.append(StringUtils.SPACE);
                    arrayList.remove(0);
                    i++;
                }
            }
        }
        try {
            sb.setLength(sb.length() - 2);
        } catch (Exception unused) {
        }
        this.cities = arrayList;
        String string = sb.toString();
        this.citiesStr = string;
        return string;
    }
}
