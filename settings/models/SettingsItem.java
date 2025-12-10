package com.channel2.mobile.ui.settings.models;

import com.channel2.mobile.ui.lobby.models.items.Item;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class SettingsItem extends Item {
    private boolean defaultDivider;
    private String displayName;
    private String link;
    private String shareSubtitle;
    private String shareTitle;
    private String shareUrl;
    private String subject;
    private String to;
    private String type;

    SettingsItem(JSONObject jSONObject) {
        super(jSONObject);
        setDisplayName(jSONObject.optString("displayName"));
        setLink(jSONObject.optString("link"));
        setType(jSONObject.optString("type"));
        setSubject(jSONObject.optString("subject"));
        setTo(jSONObject.optString("to"));
        setShareTitle(jSONObject.optString("shareTitle"));
        setShareSubtitle(jSONObject.optString("shareSubtitle"));
        setShareUrl(jSONObject.optString("shareUrl"));
        setDefaultDivider(jSONObject.optBoolean("defaultDivider"));
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

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public boolean isDefaultDivider() {
        return this.defaultDivider;
    }

    public void setDefaultDivider(boolean z) {
        this.defaultDivider = z;
    }

    public String getShareTitle() {
        return this.shareTitle;
    }

    public void setShareTitle(String str) {
        this.shareTitle = str;
    }

    public String getShareSubtitle() {
        return this.shareSubtitle;
    }

    public void setShareSubtitle(String str) {
        this.shareSubtitle = str;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String str) {
        this.shareUrl = str;
    }
}
