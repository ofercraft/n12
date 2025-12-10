package com.channel2.mobile.ui.header;

import android.graphics.Bitmap;
import androidx.media3.extractor.text.ttml.TtmlNode;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Header {
    public String appLogo;
    public String appLogoLines;
    public String backgroundColorFromConfig;
    public Bitmap mobileLogoImage = null;
    public String mobileLogoImageUrl = "";
    public String backgroundColorFromService = "";
    private HeaderVisibility headerPosition = HeaderVisibility.VISIBLE;

    public Header(JSONObject jSONObject) {
        this.backgroundColorFromConfig = "";
        this.appLogo = "";
        this.appLogoLines = "";
        this.backgroundColorFromConfig = jSONObject.optString(TtmlNode.ATTR_TTS_BACKGROUND_COLOR);
        this.appLogo = jSONObject.optString("appLogo");
        this.appLogoLines = jSONObject.optString("appLogoLines");
    }

    public HeaderVisibility getHeaderPosition() {
        return this.headerPosition;
    }

    public void setHeaderPosition(HeaderVisibility headerVisibility) {
        this.headerPosition = headerVisibility;
    }
}
