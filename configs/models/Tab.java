package com.channel2.mobile.ui.configs.models;

import com.facebook.appevents.internal.ViewHierarchyConstants;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Tab {
    public String action;
    public String headerTitle;
    public String icon;
    public String title;
    public String view;

    Tab(JSONObject jSONObject) {
        this.title = "";
        this.action = "";
        this.view = "";
        this.icon = "";
        this.headerTitle = "";
        this.title = jSONObject.optString("title");
        this.action = jSONObject.optString("action");
        this.view = jSONObject.optString(ViewHierarchyConstants.VIEW_KEY);
        this.icon = jSONObject.optString("icon");
        this.headerTitle = jSONObject.optString("headerTitle");
    }
}
