package com.channel2.mobile.ui.lobby.models.firstReport;

import com.channel2.mobile.ui.lobby.models.items.Item;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LobbyFirstReport extends Item {
    private long creation_time;
    private String more_details_text;
    private String text;
    private String title;

    public LobbyFirstReport(JSONObject jSONObject) {
        super(jSONObject);
        setLobbyItemType("firstReport");
        setCreation_time(jSONObject.optLong("creation_time"));
        setText(jSONObject.optString("text"));
        setMore_details_text(jSONObject.optString("more_details_text"));
        setTitle(jSONObject.optString("title"));
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getMore_details_text() {
        return this.more_details_text;
    }

    public void setMore_details_text(String str) {
        this.more_details_text = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public long getCreation_time() {
        return this.creation_time;
    }

    public void setCreation_time(long j) {
        this.creation_time = j;
    }
}
