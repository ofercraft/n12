package com.channel2.mobile.ui.webView.models;

import android.content.Context;
import android.content.res.Resources;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class GalleryItem {
    private String caption;
    private int index;
    private String photoCredit;
    private String portraitImageUrl;

    public GalleryItem(JSONObject jSONObject, Context context, int i) {
        setCaption(jSONObject.optString("caption"));
        setPhotoCredit(jSONObject.optString("photo_credit"));
        setPortraitImageUrl(findImageUrl(jSONObject, context));
        setIndex(i);
    }

    private void setPhotoCredit(String str) {
        this.photoCredit = str;
    }

    private void setCaption(String str) {
        this.caption = str;
    }

    private void setIndex(int i) {
        this.index = i;
    }

    private void setPortraitImageUrl(String str) {
        this.portraitImageUrl = str;
    }

    public String getCaption() {
        return this.caption;
    }

    public String getPhotoCredit() {
        return this.photoCredit;
    }

    public int getIndex() {
        return this.index;
    }

    public String getPortraitImageUrl() {
        return this.portraitImageUrl;
    }

    private String findImageUrl(JSONObject jSONObject, Context context) throws Resources.NotFoundException {
        String string;
        try {
            for (String str : context.getResources().getStringArray(R.array.imageTypes)) {
                if (!jSONObject.isNull(str) && (string = jSONObject.getString(str)) != null && string.length() > 5) {
                    if (string.startsWith("http")) {
                        return string;
                    }
                    return MainConfig.main.getCurrentParam("galleryImageDomain") + string;
                }
            }
            return "";
        } catch (JSONException unused) {
            return "";
        }
    }
}
