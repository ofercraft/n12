package com.channel2.mobile.ui.lobby.models.teasers;

import android.content.Context;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.advertising.InterstitialManager;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.models.ads.DFP;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.NTheme;
import com.google.android.gms.cast.MediaTrack;
import com.outbrain.OBSDK.Entities.OBRecommendation;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LobbyTeaser extends Item {
    private NTheme NTheme;
    private boolean addControls;
    private String author;
    private String caption;
    private String captionBackgroundColor;
    private String captionTextColor;
    private String componentIcon;
    private String date;
    private DFP dfp;
    private boolean displayDivider;
    private String flachText;
    private String guid;
    private String image;
    private boolean isLive;
    private boolean isOutBrain;
    private boolean isVideo;
    private String itemChannelId;
    private String itemChannelName;
    private int itemHeight;
    private String itemMakoCatDFP;
    private String itemUrl;
    private int itemWidth;
    private JSONArray links;
    private String openItemIn;
    private String originalimage;
    private OBRecommendation outbrainRec;
    private boolean playVideoInTeaser;
    private int readingTime;
    private String screenName;
    private InterstitialManager.ShowPreload showPreload;
    private String subTitle;
    private String teaserBackgroundColor;
    private String teaserTextColor;
    private String teaserVcmId;
    private String title;
    private String trailer;
    private JSONArray variants;
    private String videoChannelId;
    private String videoFlachBackgroundColor;
    private String videoFlachText;
    private String videoFlachTextColor;
    private String videoGalleryChannelId;
    private String videoVcmId;

    public LobbyTeaser() {
        this.teaserVcmId = "";
        this.title = "";
        this.subTitle = "";
        this.image = "";
        this.originalimage = "";
        this.guid = "";
        this.screenName = "";
        this.itemUrl = "";
        this.flachText = "";
        this.itemMakoCatDFP = "";
        this.itemChannelId = "";
        this.date = "";
        this.author = "";
        this.itemChannelName = "";
        this.caption = "";
        this.componentIcon = "";
        this.isOutBrain = false;
        this.openItemIn = "";
        this.readingTime = -1;
        this.itemHeight = -1;
        this.itemWidth = -1;
        this.isVideo = false;
        this.showPreload = InterstitialManager.ShowPreload.NOT_SET;
        this.playVideoInTeaser = false;
        this.videoVcmId = "";
        this.videoChannelId = "";
        this.videoGalleryChannelId = "";
        this.trailer = "";
        this.isLive = false;
        this.videoFlachText = "";
        this.videoFlachTextColor = "";
        this.videoFlachBackgroundColor = "";
        this.links = new JSONArray();
        this.variants = new JSONArray();
        this.NTheme = new NTheme(new JSONObject());
        this.dfp = new DFP();
        this.displayDivider = true;
        this.captionBackgroundColor = "";
        this.captionTextColor = "";
        this.teaserBackgroundColor = "";
        this.teaserTextColor = "";
    }

    public LobbyTeaser(JSONObject jSONObject, Context context) throws NumberFormatException {
        super(jSONObject);
        this.teaserVcmId = "";
        this.title = "";
        this.subTitle = "";
        this.image = "";
        this.originalimage = "";
        this.guid = "";
        this.screenName = "";
        this.itemUrl = "";
        this.flachText = "";
        this.itemMakoCatDFP = "";
        this.itemChannelId = "";
        this.date = "";
        this.author = "";
        this.itemChannelName = "";
        this.caption = "";
        this.componentIcon = "";
        this.isOutBrain = false;
        this.openItemIn = "";
        this.readingTime = -1;
        this.itemHeight = -1;
        this.itemWidth = -1;
        this.isVideo = false;
        this.showPreload = InterstitialManager.ShowPreload.NOT_SET;
        this.playVideoInTeaser = false;
        this.videoVcmId = "";
        this.videoChannelId = "";
        this.videoGalleryChannelId = "";
        this.trailer = "";
        this.isLive = false;
        this.videoFlachText = "";
        this.videoFlachTextColor = "";
        this.videoFlachBackgroundColor = "";
        this.links = new JSONArray();
        this.variants = new JSONArray();
        this.NTheme = new NTheme(new JSONObject());
        this.dfp = new DFP();
        this.displayDivider = true;
        this.captionBackgroundColor = "";
        this.captionTextColor = "";
        this.teaserBackgroundColor = "";
        this.teaserTextColor = "";
        setTeaserVcmId(jSONObject.optString("teaserVcmId"));
        setItemId(System.currentTimeMillis() / 1000);
        setLobbyItemType(jSONObject);
        setTitle(jSONObject.optString("title"));
        setSubTitle(jSONObject.optString(MediaTrack.ROLE_SUBTITLE));
        setImage(jSONObject.optString("image"));
        setOriginalimage(jSONObject.optString("image"));
        setGuid(jSONObject.optString("guid"));
        setScreenName(jSONObject.optString("screenName"));
        String strOptString = jSONObject.optString("itemUrl");
        setItemUrl(Utils.getAbsoluteUrl(strOptString));
        setClickUrl(Utils.getAbsoluteUrl(strOptString));
        setFlachText(jSONObject.optString("flachText"));
        setItemMakoCatDFP(jSONObject.optString("itemMakoCatDFP"));
        setItemChannelId(jSONObject.optString("itemChannelId"));
        setItemChannelName(jSONObject.optString("itemChannelName"));
        setReadingTime(jSONObject.optInt("readingTime"));
        setItemHeight(jSONObject.optInt("itemHeight"));
        setItemWidth(jSONObject.optInt("itemWidth"));
        setVideo(jSONObject.optBoolean("isVideo"));
        setDate(jSONObject.optString(StringLookupFactory.KEY_DATE));
        setCaption(jSONObject.optString("caption"));
        setComponentIcon(jSONObject.optString("componentIcon"));
        setOpenItemIn(jSONObject.optString("openItemIn"));
        setDFP(jSONObject.optString("itemMakoCatDFP"));
        if (jSONObject.has("preload") && !jSONObject.isNull("preload")) {
            setShowPreload(Boolean.valueOf(jSONObject.optBoolean("preload")));
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("authors");
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < jSONArrayOptJSONArray.length()) {
                sb.append(jSONArrayOptJSONArray.optString(i));
                i++;
                if (i < jSONArrayOptJSONArray.length()) {
                    sb.append(",");
                }
            }
            setAuthor(sb.toString());
        } else {
            setAuthor("");
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("playVideoInTeaser");
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("linkedItems");
        if (jSONArrayOptJSONArray2 != null) {
            setLinks(jSONArrayOptJSONArray2);
        }
        JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("variants");
        if (jSONArrayOptJSONArray3 != null && context != null) {
            setVariants(jSONArrayOptJSONArray3, context);
        }
        if (jSONObjectOptJSONObject != null) {
            setPlayVideoInTeaser(true);
            setVideoVcmId(jSONObjectOptJSONObject.optString("videoVcmId"));
            setVideoChannelId(jSONObjectOptJSONObject.optString("videoChannelId"));
            setVideoGalleryChannelId(jSONObjectOptJSONObject.optString("videoGalleryChannelId"));
            setVideoFlachText(jSONObjectOptJSONObject.optString("videoFlachText"));
            setVideoFlachTextColor(jSONObjectOptJSONObject.optString("videoFlachTextColor"));
            setVideoFlachBackgroundColor(jSONObjectOptJSONObject.optString("videoFlachBackgroundColor"));
            setTrailer(jSONObjectOptJSONObject.optString("trailer"));
            setLive(jSONObjectOptJSONObject.optBoolean("isLive"));
        }
        if (jSONObject.optJSONObject("themes") != null) {
            this.NTheme = new NTheme(jSONObject.optJSONObject("themes"));
        }
        setCaptionBackgroundColor(jSONObject.optString("captionBackgroundColor"));
        setCaptionTextColor(jSONObject.optString("captionTextColor"));
        setTeaserBackgroundColor(jSONObject.optString("teaserBackgroundColor"));
        setTeaserTextColor(jSONObject.optString("teaserTextColor"));
        setOutBrain(false);
    }

    protected void setLobbyItemType(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("itemType");
        if (jSONObject.optJSONObject("playVideoInTeaser") != null && (strOptString.equalsIgnoreCase("mainItemRegular") || strOptString.equalsIgnoreCase("mainItemMedium") || strOptString.equalsIgnoreCase("mainItemSpecialEvents"))) {
            strOptString = strOptString + "Video";
        } else if ((strOptString.equalsIgnoreCase("regularitemsmall") || strOptString.equalsIgnoreCase("regularitemsmallc") || strOptString.equalsIgnoreCase("textitemregular")) && jSONObject.optString("flachText") != null && jSONObject.optString("flachText").length() > 0) {
            strOptString = strOptString + "Ad";
        }
        setLobbyItemType(strOptString);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getOriginalimage() {
        return this.originalimage;
    }

    public void setOriginalimage(String str) {
        this.originalimage = str;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String str) {
        this.guid = str;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public void setScreenName(String str) {
        this.screenName = str;
    }

    public String getItemUrl() {
        return this.itemUrl;
    }

    public void setItemUrl(String str) {
        this.itemUrl = str;
    }

    public String getFlachText() {
        return this.flachText;
    }

    public void setFlachText(String str) {
        this.flachText = str;
    }

    public String getItemMakoCatDFP() {
        return this.itemMakoCatDFP;
    }

    public void setItemMakoCatDFP(String str) {
        this.itemMakoCatDFP = str;
    }

    public String getItemChannelId() {
        return this.itemChannelId;
    }

    public void setItemChannelId(String str) {
        this.itemChannelId = str;
    }

    public String getItemChannelName() {
        return this.itemChannelName;
    }

    public void setItemChannelName(String str) {
        this.itemChannelName = str;
    }

    public int getReadingTime() {
        return this.readingTime;
    }

    public void setReadingTime(int i) {
        this.readingTime = i;
    }

    public boolean isVideo() {
        return this.isVideo;
    }

    public void setVideo(boolean z) {
        this.isVideo = z;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String str) {
        this.caption = str;
    }

    public boolean isPlayVideoInTeaser() {
        return this.playVideoInTeaser;
    }

    public void setPlayVideoInTeaser(boolean z) {
        this.playVideoInTeaser = z;
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

    public int getItemHeight() {
        return this.itemHeight;
    }

    public void setItemHeight(int i) {
        this.itemHeight = i;
    }

    public int getItemWidth() {
        return this.itemWidth;
    }

    public void setItemWidth(int i) {
        this.itemWidth = i;
    }

    public boolean isDisplayDivider() {
        return this.displayDivider;
    }

    public void setDisplayDivider(boolean z) {
        this.displayDivider = z;
    }

    public JSONArray getLinks() {
        return this.links;
    }

    public void setLinks(JSONArray jSONArray) {
        this.links = jSONArray;
    }

    public JSONArray getVariants() {
        return this.variants;
    }

    public void setVariants(JSONArray jSONArray, Context context) throws NumberFormatException {
        String stringFromPreferences = Utils.getStringFromPreferences(context, context.getResources().getString(R.string.userPercentNumber));
        if (stringFromPreferences.length() > 0) {
            int i = Integer.parseInt(stringFromPreferences);
            int i2 = 1;
            while (true) {
                if (i2 > jSONArray.length()) {
                    break;
                }
                if ((1000 / jSONArray.length()) * i2 > i) {
                    JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i2 - 1);
                    if (jSONObjectOptJSONObject.optString("imageUrl").length() > 0) {
                        setImage(jSONObjectOptJSONObject.optString("imageUrl"));
                    }
                    setTitle(jSONObjectOptJSONObject.optString("title"));
                    setSubTitle(jSONObjectOptJSONObject.optString(MediaTrack.ROLE_SUBTITLE));
                    setItemUrl(Utils.getAbsoluteUrl(jSONObjectOptJSONObject.optString("url")));
                    setClickUrl(Utils.getAbsoluteUrl(jSONObjectOptJSONObject.optString("url")));
                } else {
                    i2++;
                }
            }
        }
        this.variants = jSONArray;
    }

    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String str) {
        this.trailer = str;
    }

    public boolean isLive() {
        return this.isLive;
    }

    public void setLive(boolean z) {
        this.isLive = z;
    }

    public String getComponentIcon() {
        return this.componentIcon;
    }

    public void setComponentIcon(String str) {
        this.componentIcon = str;
    }

    public String getOpenItemIn() {
        return this.openItemIn;
    }

    public void setOpenItemIn(String str) {
        this.openItemIn = str;
    }

    public NTheme getNTheme() {
        return this.NTheme;
    }

    public DFP getDfp() {
        return this.dfp;
    }

    private void setDFP(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.dfp = new DFP(jSONObject.optString("iu"), jSONObject.optJSONObject("cust_params"), this.guid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InterstitialManager.ShowPreload getShowPreload() {
        return this.showPreload;
    }

    public void setShowPreload(Boolean bool) {
        this.showPreload = bool.booleanValue() ? InterstitialManager.ShowPreload.TRUE : InterstitialManager.ShowPreload.FALSE;
    }

    public String getCaptionBackgroundColor() {
        return this.captionBackgroundColor;
    }

    public void setCaptionBackgroundColor(String str) {
        this.captionBackgroundColor = str;
    }

    public String getCaptionTextColor() {
        return this.captionTextColor;
    }

    public void setCaptionTextColor(String str) {
        this.captionTextColor = str;
    }

    public String getTeaserBackgroundColor() {
        return this.teaserBackgroundColor;
    }

    public void setTeaserBackgroundColor(String str) {
        this.teaserBackgroundColor = str;
    }

    public String getTeaserTextColor() {
        return this.teaserTextColor;
    }

    public void setTeaserTextColor(String str) {
        this.teaserTextColor = str;
    }

    public String getTeaserVcmId() {
        return this.teaserVcmId;
    }

    public void setTeaserVcmId(String str) {
        this.teaserVcmId = str;
    }

    public boolean isOutBrain() {
        return this.isOutBrain;
    }

    public void setOutBrain(boolean z) {
        this.isOutBrain = z;
    }

    public OBRecommendation getOutbrainRec() {
        return this.outbrainRec;
    }

    public void setOutbrainRec(OBRecommendation oBRecommendation) {
        this.outbrainRec = oBRecommendation;
    }
}
