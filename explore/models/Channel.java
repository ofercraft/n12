package com.channel2.mobile.ui.explore.models;

import com.channel2.mobile.ui.lobby.models.items.Item;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Channel extends Item {
    private String displayName;
    private String link;

    public Channel(JSONObject jSONObject) {
        super(jSONObject);
        setDisplayName(jSONObject.optString("displayName"));
        setLink(jSONObject.optString("link"));
        setClickUrl(jSONObject.optString("link"));
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }
}
