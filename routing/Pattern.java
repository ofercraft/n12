package com.channel2.mobile.ui.routing;

import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Pattern {
    private JSONArray findReplace;
    private String iu;
    private String pattern;
    private String screenName;
    private String videoChannelId;
    private String videoGalleryId;
    private String videoVcmId;

    public Pattern(JSONObject jSONObject, String str) {
        setPattern(jSONObject.optString("pattern"));
        setVideoVcmId(jSONObject.optString("videoVcmId"));
        setVideoChannelId(jSONObject.optString("videoChannelId"));
        setVideoGalleryId(jSONObject.optString("videoGalleryId"));
        setIu(jSONObject.optString("iu"));
        setFindReplace(jSONObject.optJSONArray("findReplace"));
        setScreenName(str);
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String str) {
        this.pattern = str;
    }

    public String getVideoVcmId() {
        return this.videoVcmId;
    }

    public void setVideoVcmId(String str) {
        this.videoVcmId = str;
    }

    public String getVideoChannelId() {
        return this.videoChannelId;
    }

    public void setVideoChannelId(String str) {
        this.videoChannelId = str;
    }

    public String getVideoGalleryId() {
        return this.videoGalleryId;
    }

    public void setVideoGalleryId(String str) {
        this.videoGalleryId = str;
    }

    public String getIu() {
        return this.iu;
    }

    public void setIu(String str) {
        this.iu = str;
    }

    public JSONArray getFindReplace() {
        return this.findReplace;
    }

    public void setFindReplace(JSONArray jSONArray) {
        this.findReplace = jSONArray;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public void setScreenName(String str) {
        this.screenName = str;
    }
}
