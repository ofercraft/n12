package com.channel2.mobile.ui.lobby.models.sections;

import com.channel2.mobile.ui.lobby.models.items.Item;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LobbyFooter extends Item {
    private String FooterText;

    public LobbyFooter(JSONObject jSONObject) {
        super(jSONObject);
        setFooterText(jSONObject.optString("footerText"));
        if (jSONObject.optString("componentType").equals("verticalparshanim")) {
            setLobbyItemType("footerparshanim");
        } else if (jSONObject.optString("componentType").equals("verticalpodcast")) {
            setLobbyItemType("footerpodcast");
        } else {
            setLobbyItemType("footer");
        }
    }

    public String getFooterText() {
        return this.FooterText;
    }

    public void setFooterText(String str) {
        this.FooterText = str;
    }
}
