package com.channel2.mobile.ui.programs.models;

import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.programs.views.ProgramsViewHolder;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ProgramsItem extends Item {
    private String date;
    private boolean fullEpisode;
    private String image;
    private long publishedDate;
    private long startTimeUTC;
    private String title;
    private String trailer;
    private String videoChannelId;
    private String videoFlachBackgroundColor;
    private String videoFlachText;
    private String videoFlachTextColor;
    private String videoGalleryChannelId;
    private String videoVcmId;
    private ProgramsViewHolder viewHolder;

    public ProgramsItem(JSONObject jSONObject) {
        super(jSONObject);
        setImage(jSONObject.optString("image"));
        setTitle(jSONObject.optString("title"));
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("playVideoInTeaser");
        if (jSONObjectOptJSONObject != null) {
            setTrailer(jSONObjectOptJSONObject.optString("trailer"));
            setDate(jSONObjectOptJSONObject.optString(StringLookupFactory.KEY_DATE));
            setVideoFlachText(jSONObjectOptJSONObject.optString("videoFlachText"));
            setVideoFlachTextColor(jSONObjectOptJSONObject.optString("videoFlachTextColor"));
            setVideoFlachBackgroundColor(jSONObjectOptJSONObject.optString("videoFlachBackgroundColor"));
            setVideoVcmId(jSONObjectOptJSONObject.optString("videoVcmId"));
            setVideoChannelId(jSONObjectOptJSONObject.optString("videoChannelId"));
            setVideoGalleryChannelId(jSONObjectOptJSONObject.optString("videoGalleryChannelId"));
            setFullEpisode(jSONObjectOptJSONObject.optBoolean("fullEpisode"));
            setPublishedDate(jSONObjectOptJSONObject.optLong("publishDate"));
        }
    }

    public String getVideoFlachText() {
        return this.videoFlachText;
    }

    public void setVideoFlachText(String str) {
        this.videoFlachText = str;
    }

    public String getVideoFlachTextColor() {
        return this.videoFlachTextColor;
    }

    public void setVideoFlachTextColor(String str) {
        this.videoFlachTextColor = str;
    }

    public String getVideoFlachBackgroundColor() {
        return this.videoFlachBackgroundColor;
    }

    public void setVideoFlachBackgroundColor(String str) {
        this.videoFlachBackgroundColor = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String str) {
        this.trailer = str;
    }

    public String getVideoVcmId() {
        return this.videoVcmId;
    }

    public void setVideoVcmId(String str) {
        this.videoVcmId = str;
    }

    public String getVideoChannelId() {
        return this.videoChannelId;
    }

    public void setVideoChannelId(String str) {
        this.videoChannelId = str;
    }

    public String getVideoGalleryChannelId() {
        return this.videoGalleryChannelId;
    }

    public void setVideoGalleryChannelId(String str) {
        this.videoGalleryChannelId = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public ProgramsViewHolder getViewHolder() {
        return this.viewHolder;
    }

    public void setViewHolder(ProgramsViewHolder programsViewHolder) {
        this.viewHolder = programsViewHolder;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public boolean isFullEpisode() {
        return this.fullEpisode;
    }

    public void setFullEpisode(boolean z) {
        this.fullEpisode = z;
    }

    public long getPublishedDate() {
        return this.publishedDate;
    }

    public void setPublishedDate(long j) {
        this.publishedDate = j;
    }

    public long getStartTimeUTC() {
        return this.startTimeUTC;
    }

    public void setStartTimeUTC(long j) {
        this.startTimeUTC = j;
    }
}
