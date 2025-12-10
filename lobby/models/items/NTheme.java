package com.channel2.mobile.ui.lobby.models.items;

import org.json.JSONObject;

/* loaded from: classes2.dex */
public class NTheme extends Item {
    private String captionColor;
    private String gradientBackground;
    private String lineColor;
    private String linkedImage;
    private String name;

    public NTheme(JSONObject jSONObject) {
        super(jSONObject);
        this.name = jSONObject.optString("name");
        this.lineColor = jSONObject.optString("lineColor");
        this.captionColor = jSONObject.optString("captionColor");
        this.gradientBackground = jSONObject.optString("gradientBackground");
        this.linkedImage = jSONObject.optString("linkedImage");
    }

    public String getName() {
        return this.name;
    }

    public String getLineColor() {
        return this.lineColor;
    }

    public String getCaptionColor() {
        return this.captionColor;
    }

    public String getGradientBackground() {
        return this.gradientBackground;
    }

    public String getLinkedImage() {
        return this.linkedImage;
    }
}
