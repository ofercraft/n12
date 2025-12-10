package com.channel2.mobile.ui.Chats.models;

import android.os.Build;
import android.text.Html;
import java.io.Serializable;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ChatMediaItem implements Serializable {
    private long autoId;
    private String link1;
    private String link2;
    private String link3;
    private String linkDescription;
    private String linkTitle;
    private String mediaContent;
    private long messageID;
    private String thumbnail;
    private long typeId;

    public ChatMediaItem(JSONObject jSONObject) {
        setTypeId(jSONObject.optInt("typeId"));
        setMessageID(jSONObject.optInt("messageId"));
        setAutoId(jSONObject.optInt("autoId"));
        setLink1(jSONObject.optString("link1"));
        setLink2(jSONObject.optString("link2"));
        setLink3(jSONObject.optString("link3"));
        setLinkTitle(jSONObject.optString("linkTitle"));
        setLinkDescription(jSONObject.optString("linkDescription"));
        setThumbnail(jSONObject.optString("thumbnail"));
        setMediaContent(jSONObject.optString("mediaContent"));
    }

    public ChatMediaItem(HashMap map) {
        setTypeId(((Long) map.get("typeId")).longValue());
        setAutoId(((Long) map.get("autoId")).longValue());
        setMessageID(((Long) map.get("messageId")).longValue());
        setLink1((String) map.get("link1"));
        setLink2((String) map.get("link2"));
        setLink3((String) map.get("link3"));
        setLinkTitle((String) map.get("linkTitle"));
        setLinkDescription((String) map.get("linkDescription"));
        setThumbnail((String) map.get("thumbnail"));
        setMediaContent((String) map.get("mediaContent"));
    }

    public long getMediaTypeId() {
        return this.typeId;
    }

    public void setTypeId(long j) {
        this.typeId = j;
    }

    public long getAutoId() {
        return this.autoId;
    }

    public void setAutoId(long j) {
        this.autoId = j;
    }

    public long getMessageID() {
        return this.messageID;
    }

    public void setMessageID(long j) {
        this.messageID = j;
    }

    public String getLink1() {
        return this.link1;
    }

    public String getLink2() {
        return this.link2;
    }

    public String getLink3() {
        return this.link3;
    }

    public void setLink1(String str) {
        this.link1 = str;
    }

    public void setLink2(String str) {
        this.link2 = str;
    }

    public void setLink3(String str) {
        this.link3 = str;
    }

    public String getLinkTitle() {
        return this.linkTitle;
    }

    public void setLinkTitle(String str) {
        this.linkTitle = stripHtml(str);
    }

    public String getLinkDescription() {
        return this.linkDescription;
    }

    public void setLinkDescription(String str) {
        this.linkDescription = stripHtml(str);
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String str) {
        this.thumbnail = str;
    }

    public String getMediaContent() {
        return this.mediaContent;
    }

    public void setMediaContent(String str) {
        this.mediaContent = stripHtml(str);
    }

    public String stripHtml(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        str.replace(StringUtils.LF, "|*|");
        str.replace("<br>", "|*|");
        if (Build.VERSION.SDK_INT >= 24) {
            String string = Html.fromHtml(str, 0).toString();
            string.replace("|*|", "<br>");
            return string;
        }
        String string2 = Html.fromHtml(str).toString();
        string2.replace("|*|", "<br>");
        return string2;
    }
}
