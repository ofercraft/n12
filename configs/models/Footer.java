package com.channel2.mobile.ui.configs.models;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Footer {
    public String backgroundColor;
    public String itemColor;
    public String selectedColor;
    public ArrayList<Tab> tabs;
    public String textColor;

    Footer(JSONObject jSONObject) {
        this.backgroundColor = "";
        this.selectedColor = "";
        this.itemColor = "";
        this.textColor = "";
        this.backgroundColor = jSONObject.optString(TtmlNode.ATTR_TTS_BACKGROUND_COLOR);
        this.selectedColor = jSONObject.optString("selectedColor");
        this.itemColor = jSONObject.optString("itemColor");
        this.textColor = jSONObject.optString("textColor");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("tabs");
        this.tabs = new ArrayList<>();
        int length = jSONArrayOptJSONArray.length() <= 5 ? jSONArrayOptJSONArray.length() : 5;
        for (int i = 0; i < length; i++) {
            this.tabs.add(new Tab(jSONArrayOptJSONArray.optJSONObject(i)));
        }
    }
}
