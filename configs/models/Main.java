package com.channel2.mobile.ui.configs.models;

import android.content.Context;
import com.channel2.mobile.ui.header.Header;
import com.cooladata.android.Constants;
import com.facebook.internal.NativeProtocol;
import com.mako.kscore.ksmeasurements.model.item.ErrorItem$$ExternalSyntheticBackport0;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Main {
    private JSONObject chatTopics;
    private Footer footer;
    private Header header;
    private JSONObject params;
    private JSONObject sources;
    private JSONObject tutorial;
    private JSONObject versionControlAlert;

    public static Main fromJsonObject(JSONObject jSONObject, Context context) {
        Main main = new Main();
        main.header = jSONObject.isNull("header") ? new Header(new JSONObject()) : new Header(jSONObject.optJSONObject("header"));
        main.footer = jSONObject.isNull("footer") ? new Footer(new JSONObject()) : new Footer(jSONObject.optJSONObject("footer"));
        main.params = jSONObject.isNull(NativeProtocol.WEB_DIALOG_PARAMS) ? new JSONObject() : jSONObject.optJSONObject(NativeProtocol.WEB_DIALOG_PARAMS);
        main.tutorial = jSONObject.isNull("tutorial") ? new JSONObject() : jSONObject.optJSONObject("tutorial");
        main.chatTopics = jSONObject.isNull("chatTopics") ? new JSONObject() : jSONObject.optJSONObject("chatTopics");
        main.sources = jSONObject.isNull("sources") ? new JSONObject() : jSONObject.optJSONObject("sources");
        main.versionControlAlert = jSONObject.isNull("versionControlAlert") ? new JSONObject() : jSONObject.optJSONObject("versionControlAlert");
        return main;
    }

    public JSONObject getParams() {
        return this.params;
    }

    public Header getHeader() {
        return this.header;
    }

    public Footer getFooter() {
        return this.footer;
    }

    public String getCurrentSource(String str) {
        return this.sources.optString(str, "");
    }

    public String getCurrentParam(String str) {
        return this.params.optString(str, "");
    }

    public JSONObject getCurrentJsonParam(String str) {
        return this.params.optJSONObject(str);
    }

    public int getCurrentIntParam(String str) {
        return this.params.optInt(str);
    }

    public boolean getCurrentBooleanParam(String str) {
        return this.params.optBoolean(str);
    }

    public JSONObject getVersionControlAlert() {
        return this.versionControlAlert;
    }

    public JSONObject getTutorial() {
        return this.tutorial;
    }

    public JSONObject getChatTopics() {
        return this.chatTopics;
    }

    public String getTrackerVersion() {
        return this.params.optString(Constants.TRACKER_VERSION_FIELD_NAME);
    }

    public int getRequestTimeout() {
        return this.params.optInt("requestTimeout", 2000);
    }

    public String getEventSchemas() {
        return this.sources.optString("event_schemas");
    }

    public String getEventSchemasFallback() {
        return this.sources.optString("eventSchemasFallback");
    }

    public String getInitSession() {
        return this.sources.optString("init_session");
    }

    public String getInitSessionFallback() {
        return this.sources.optString("initSessionFallback");
    }

    public String getErrorEventEndpoint() {
        return this.sources.optString("errorEventEndpoint");
    }

    public Boolean getDomoEnabled() {
        return Boolean.valueOf(this.params.optBoolean("domoEnable") && !ErrorItem$$ExternalSyntheticBackport0.m(getEventSchemas()));
    }

    public Boolean getCooladataEnable() {
        return Boolean.valueOf(this.params.optBoolean("cooladataEnable") && !ErrorItem$$ExternalSyntheticBackport0.m(this.params.optString("cooldataKey")));
    }
}
