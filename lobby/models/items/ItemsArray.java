package com.channel2.mobile.ui.lobby.models.items;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.helpers.ObituariesHandler;
import com.channel2.mobile.ui.helpers.ObituariesHelper;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.models.sections.LobbySection;
import com.channel2.mobile.ui.lobby.models.teasers.LobbyTeaser;
import com.channel2.mobile.ui.network.ApiService;
import com.channel2.mobile.ui.outbrain.OBArrivedListener;
import com.channel2.mobile.ui.outbrain.OutBrainData;
import com.cooladata.android.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mako.kscore.ksmeasurements.model.item.ErrorItem$$ExternalSyntheticBackport0;
import com.outbrain.OBSDK.FetchRecommendations.OBRequest;
import com.outbrain.OBSDK.Outbrain;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ItemsArray {
    private final MutableLiveData<ArrayList<Item>> _lobbyItems;
    private boolean isFooterLocked;
    private boolean isLive;
    public final LiveData<ArrayList<Item>> lobbyItems;
    private LobbySection outBrainFooterSection;
    private int outBrainIndex;

    /* JADX WARN: Removed duplicated region for block: B:81:0x0178  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ItemsArray(org.json.JSONObject r17, android.content.Context r18, java.lang.String r19, java.lang.String r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 542
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.channel2.mobile.ui.lobby.models.items.ItemsArray.<init>(org.json.JSONObject, android.content.Context, java.lang.String, java.lang.String, boolean):void");
    }

    private void getOutBrain(final Context context, LobbySection lobbySection, ArrayList<LobbyTeaser> arrayList, final String str, final String str2, final boolean z, final boolean z2) {
        String str3;
        StringBuilder sb = new StringBuilder("size = ");
        sb.append(arrayList == null ? AbstractJsonLexerKt.NULL : Integer.valueOf(arrayList.size()));
        sb.append(" | isFooterLocked = ");
        sb.append(this.isFooterLocked);
        Log.d("checkOBCall", sb.toString());
        if (arrayList == null || !this.isFooterLocked) {
            this.isFooterLocked = arrayList == null;
            String outbrainAboutURL = Outbrain.getOutbrainAboutURL();
            if (outbrainAboutURL.indexOf("?") > 0) {
                String strSubstring = outbrainAboutURL.substring(0, outbrainAboutURL.indexOf("?"));
                if (MainConfig.appData.getAdvertisingId(context).length() > 6) {
                    outbrainAboutURL = strSubstring + "?doo=false&advertiser_id=" + MainConfig.appData.getAdvertisingId(context);
                } else {
                    outbrainAboutURL = strSubstring + "?doo=true&advertiser_id=" + MainConfig.appData.getAdvertisingId(context);
                }
            }
            lobbySection.setIconClickUrl(outbrainAboutURL);
            String str4 = MainConfig.main.getCurrentParam("outbrainUrl") + str;
            OBRequest oBRequest = new OBRequest();
            oBRequest.setUrl(str4);
            int i = this.outBrainIndex;
            this.outBrainIndex = i + 1;
            oBRequest.setWidgetIndex(i);
            try {
                str3 = lobbySection.getChannelGuid().split("\\|")[0];
            } catch (Exception unused) {
                str3 = "";
            }
            final String str5 = str3;
            if (!ErrorItem$$ExternalSyntheticBackport0.m(str5)) {
                oBRequest.setWidgetId(str5);
            }
            final String str6 = str5 + "," + MainConfig.appData.getUserId() + "," + str2 + ",mobileapp," + System.currentTimeMillis();
            Log.d("ob request", StringUtils.SPACE + oBRequest.toString());
            OutBrainData.instance.fetchOutBrainData(oBRequest, arrayList, new OBArrivedListener() { // from class: com.channel2.mobile.ui.lobby.models.items.ItemsArray.1
                @Override // com.channel2.mobile.ui.outbrain.OBArrivedListener
                public void onFailed() {
                }

                @Override // com.channel2.mobile.ui.outbrain.OBArrivedListener
                public void onOBReady(String str7, ArrayList<Item> arrayList2) throws JSONException {
                    ArrayList<Item> value = ItemsArray.this.lobbyItems.getValue();
                    if (value != null) {
                        if (arrayList2 != null && arrayList2.size() > 0) {
                            value.addAll(arrayList2);
                        }
                        ItemsArray.this._lobbyItems.setValue(value);
                        ItemsArray.this.isFooterLocked = false;
                    }
                    if (z) {
                        ItemsArray.this.reportDomo(context, str, str2, str5, str6, str7, z2);
                    }
                }
            });
        }
    }

    public void fetchMoreOutBrainFooterItems(Context context, String str, String str2, boolean z) {
        LobbySection lobbySection = this.outBrainFooterSection;
        if (lobbySection != null) {
            getOutBrain(context, lobbySection, null, str, str2, false, z);
        }
    }

    private void getObituariesData(Context context) {
        ObituariesHelper.getInstance().getData(context, new ObituariesHandler() { // from class: com.channel2.mobile.ui.lobby.models.items.ItemsArray.2
            @Override // com.channel2.mobile.ui.helpers.ObituariesHandler
            public void onSuccess() {
                Log.i("getObituariesData", "onSuccess items count " + ObituariesHelper.getInstance().getItems().size());
            }

            @Override // com.channel2.mobile.ui.helpers.ObituariesHandler
            public void onFailure() {
                Log.i("getObituariesData", "onFailure items count " + ObituariesHelper.getInstance().getItems().size());
            }
        });
    }

    public void add(int i, Item item) {
        ArrayList<Item> value = this._lobbyItems.getValue();
        if (value != null) {
            value.add(i, item);
            this._lobbyItems.postValue(value);
        }
    }

    public boolean isLive() {
        return this.isLive;
    }

    public void setLive(boolean z) {
        this.isLive = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportDomo(Context context, String str, String str2, String str3, String str4, String str5, boolean z) throws JSONException {
        String str6 = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        JSONObject jSONObject = new JSONObject();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String str7 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(Long.valueOf(jCurrentTimeMillis));
            String strReplace = new SimpleDateFormat("Z", Locale.getDefault()).format(Long.valueOf(jCurrentTimeMillis)).replace(AppEventsConstants.EVENT_PARAM_VALUE_NO, "").replace("+", "");
            if (!ErrorItem$$ExternalSyntheticBackport0.m(strReplace) && !strReplace.equals("-")) {
                str6 = strReplace;
            }
            jSONObject.put(Constants.EVENT_NAME_FIELD_NAME, "outbrain_widgets");
            jSONObject.put("event_time_epoch", jCurrentTimeMillis);
            jSONObject.put("event_time_offfset", str6);
            jSONObject.put("event_time_ts", str7);
            jSONObject.put("user_id", MainConfig.appData.getUserId());
            jSONObject.put("platform", "mobileapp");
            jSONObject.put("os_type", Constants.TRACKER_TYPE);
            jSONObject.put("app_id", MainConfig.appData.getAppId());
            jSONObject.put(Constants.SESSION_APP_VERSION_FIELD_NAME, Utils.getAppVersionName(context));
            jSONObject.put("page_url", "https://www.mako.co.il" + str);
            jSONObject.put("channel_id", str2);
            jSONObject.put("ob_key", str4);
            jSONObject.put("ob_widget_name", str3);
            jSONObject.put(FirebaseAnalytics.Param.ITEM_ID, str2);
            jSONObject.put("ob_widget_id", str5);
            jSONObject.put(FirebaseAnalytics.Param.CONTENT_TYPE, z ? "Lobby" : "Vertical");
            Log.d("checkOutBrain", "ob params:: " + jSONObject);
            ApiService.postJsonRequest("https://biprx.n12.co.il\\outbrain_dev", context, jSONObject, null);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
