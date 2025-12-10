package com.channel2.mobile.ui.explore.models;

import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.models.items.Item;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class SearchResultsItem extends Item {
    private String Author;
    private String ChannelName;
    private String GUID;
    private String Image;
    private String PubDate;
    private String SubTitle;
    private String Title;
    private String URL;
    private String openItemIn;

    public SearchResultsItem(JSONObject jSONObject) {
        super(jSONObject);
        setTitle(jSONObject.optString("title"));
        setSubTitle(jSONObject.optString("subTitle"));
        setImage(jSONObject.optString("image"));
        setGUID(jSONObject.optString("guid"));
        setURL(jSONObject.optString("url"));
        setAuthor(jSONObject.optString("author"));
        setPubDate(jSONObject.optString("pubDate"));
        setChannelName(jSONObject.optString("channelName"));
        setOpenItemIn(jSONObject.optString("openItemIn"));
        setClickUrl(Utils.getAbsoluteUrl(jSONObject.optString("url")));
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String str) {
        this.Title = str;
    }

    public String getSubTitle() {
        return this.SubTitle;
    }

    public void setSubTitle(String str) {
        this.SubTitle = str;
    }

    public String getImage() {
        return this.Image;
    }

    public void setImage(String str) {
        this.Image = str;
    }

    public String getGUID() {
        return this.GUID;
    }

    public void setGUID(String str) {
        this.GUID = str;
    }

    public String getURL() {
        return this.URL;
    }

    public void setURL(String str) {
        this.URL = str;
    }

    public String getAuthor() {
        return this.Author;
    }

    public void setAuthor(String str) {
        this.Author = str;
    }

    public String getPubDate() {
        return this.PubDate;
    }

    public void setPubDate(String str) {
        this.PubDate = str;
    }

    public String getChannelName() {
        return this.ChannelName;
    }

    public void setChannelName(String str) {
        this.ChannelName = str;
    }

    public String getOpenItemIn() {
        return this.openItemIn;
    }

    public void setOpenItemIn(String str) {
        this.openItemIn = str;
    }
}
