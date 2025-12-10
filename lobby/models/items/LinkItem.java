package com.channel2.mobile.ui.lobby.models.items;

import com.channel2.mobile.ui.helpers.Utils;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LinkItem extends Item {
    private String url;

    public LinkItem(JSONObject jSONObject) {
        super(jSONObject);
        setClickUrl(Utils.getAbsoluteUrl(jSONObject.optString("url")));
    }
}
