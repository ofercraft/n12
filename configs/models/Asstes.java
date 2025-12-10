package com.channel2.mobile.ui.configs.models;

import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Asstes {
    private JSONObject images;
    private JSONObject texts;

    public static Asstes fromJsonObject(JSONObject jSONObject) {
        Asstes asstes = new Asstes();
        asstes.texts = jSONObject.isNull("texts") ? new JSONObject() : jSONObject.optJSONObject("texts");
        asstes.images = jSONObject.isNull("images") ? new JSONObject() : jSONObject.optJSONObject("images");
        return asstes;
    }

    public String getCurrentText(String str, String str2) {
        JSONObject jSONObjectOptJSONObject = this.texts.optJSONObject(str);
        return jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString(str2, "") : "";
    }

    public String getCurrentImage(String str, String str2) {
        JSONObject jSONObjectOptJSONObject = this.images.optJSONObject(str);
        return jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString(str2, "") : "";
    }
}
