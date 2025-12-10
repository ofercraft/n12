package com.channel2.mobile.ui.liveStreaming;

import android.content.Context;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.network.ApiService;
import com.channel2.mobile.ui.programs.models.ProgramsItem;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CheckIsLive {
    public CheckIsLive(Context context, final JSONObject jSONObject) {
        ApiService.getJSONObjectAsync(MainConfig.main.getCurrentSource("liveStreamingDataApi"), context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.liveStreaming.CheckIsLive.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str, int i) {
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject2) {
                long jOptLong;
                JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("LivePrograms");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                    return;
                }
                HashMap map = new HashMap();
                long jCurrentTimeMillis = System.currentTimeMillis();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                    long jOptLong2 = jSONObjectOptJSONObject.optLong("EndTimeUTC");
                    String strValueOf = String.valueOf(jSONObjectOptJSONObject.optInt("ProgramCode"));
                    if (jOptLong2 > jCurrentTimeMillis) {
                        Live.setCurrentProgram(strValueOf);
                        Live.setIsLive(true);
                    }
                    if (jSONObject != null) {
                        ProgramsItem programsItem = new ProgramsItem(jSONObject);
                        if (Live.getCurrentProgram() != null && Live.getCurrentProgram().equals(strValueOf)) {
                            programsItem.setDate("משודר כעת");
                            jOptLong = 0;
                        } else {
                            programsItem.setDate("שודר לאחרונה");
                            jOptLong = jSONObjectOptJSONObject.optLong("StartTimeUTC");
                            programsItem.setVideoFlachText("");
                        }
                        programsItem.setTitle(jSONObjectOptJSONObject.optString("ProgramName"));
                        programsItem.setImage(jSONObjectOptJSONObject.optString("Picture"));
                        programsItem.setStartTimeUTC(jOptLong);
                        map.put(strValueOf, programsItem);
                    }
                }
                Live.setLivePrograms(map);
            }
        });
    }
}
