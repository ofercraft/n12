package com.channel2.mobile.ui.lobby.models.sections;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.channel2.mobile.ui.lobby.models.items.DividerType;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.SectionType;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LobbySection extends Item {
    private String background;
    private String backgroundColor;
    private String backgroundImage;
    private String channelGuid;
    private String componentIcon;
    private String componentLeftIcon;
    private String componentRightIcon;
    private String footerText;
    private String headerBackground;
    private String headerLogoImage;
    private String headerText;
    private ArrayList<Item> horizontalItems;
    private String iconClickUrl;
    private String name;
    private String sectionName;
    private SectionType sectionType;
    private String textColor;
    private String titleColor;
    private DividerType topDivider;
    private int topicID;

    public LobbySection(JSONObject jSONObject) {
        super(jSONObject);
        this.topicID = 1;
        setLobbyItemType(jSONObject.optString("componentType"));
        setName(jSONObject.optString("name"));
        setSectionName(jSONObject.optString("sectionName"));
        setBackgroundColor(jSONObject.optString(TtmlNode.ATTR_TTS_BACKGROUND_COLOR));
        setBackgroundImage(jSONObject.optString("backgroundImage"));
        setTitleColor(jSONObject.optString("titleColor"));
        setFooterText(jSONObject.optString("footerText"));
        setTopDivider(jSONObject.optString("topDivider"));
        setComponentIcon(jSONObject.optString("componentIcon"));
        setChannelGuid(jSONObject.optString("channelVcmId"));
        setClickUrl(jSONObject.optString("channelVcmId"));
        setSectionType(jSONObject.optString("sectionType"));
        setTopicID(jSONObject.optInt("topicId", 1));
        setTextColor(jSONObject.optString("textColor"));
        setHeaderText(jSONObject.optString("headerText"));
        setHeaderBackground(jSONObject.optString("headerBackground"));
        setHeaderLogoImage(jSONObject.optString("headerLogoImage"));
        setBackground(jSONObject.optString("background"));
        setComponentLeftIcon(jSONObject.optString("componentLeftIcon"));
        setComponentRightIcon(jSONObject.optString("componentRightIcon"));
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSectionName() {
        return this.sectionName;
    }

    public void setSectionName(String str) {
        this.sectionName = str;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String str) {
        this.backgroundColor = str;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public String getTitleColor() {
        return this.titleColor;
    }

    public void setTitleColor(String str) {
        this.titleColor = str;
    }

    public String getFooterText() {
        return this.footerText;
    }

    public void setFooterText(String str) {
        this.footerText = str;
    }

    public String getComponentIcon() {
        return this.componentIcon;
    }

    public void setComponentIcon(String str) {
        this.componentIcon = str;
    }

    public String getChannelGuid() {
        return this.channelGuid;
    }

    public void setChannelGuid(String str) {
        this.channelGuid = str;
    }

    public ArrayList<Item> getHorizontalItems() {
        return this.horizontalItems;
    }

    public void setHorizontalItems(ArrayList<Item> arrayList) {
        this.horizontalItems = arrayList;
    }

    public DividerType getTopDivider() {
        return this.topDivider;
    }

    public void setTopDivider(String str) {
        DividerType dividerTypeValueOf;
        if (str.length() > 0) {
            try {
                dividerTypeValueOf = DividerType.valueOf(str);
            } catch (IllegalArgumentException unused) {
                dividerTypeValueOf = null;
            }
            this.topDivider = dividerTypeValueOf;
        }
    }

    public String getIconClickUrl() {
        return this.iconClickUrl;
    }

    public void setIconClickUrl(String str) {
        this.iconClickUrl = str;
    }

    public SectionType getSectionType() {
        return this.sectionType;
    }

    public void setSectionType(String str) {
        SectionType sectionTypeValueOf;
        if (str.length() > 0) {
            try {
                sectionTypeValueOf = SectionType.valueOf(str);
            } catch (IllegalArgumentException unused) {
                sectionTypeValueOf = SectionType.red;
            }
            this.sectionType = sectionTypeValueOf;
            return;
        }
        this.sectionType = SectionType.none;
    }

    public int getTopicID() {
        return this.topicID;
    }

    public void setTopicID(int i) {
        this.topicID = i;
    }

    public String getTextColor() {
        return this.textColor;
    }

    public void setTextColor(String str) {
        this.textColor = str;
    }

    public String getHeaderText() {
        return this.headerText;
    }

    public void setHeaderText(String str) {
        this.headerText = str;
    }

    public String getHeaderBackground() {
        return this.headerBackground;
    }

    public void setHeaderBackground(String str) {
        this.headerBackground = str;
    }

    public String getHeaderLogoImage() {
        return this.headerLogoImage;
    }

    public void setHeaderLogoImage(String str) {
        this.headerLogoImage = str;
    }

    public String getBackground() {
        return this.background;
    }

    public void setBackground(String str) {
        this.background = str;
    }

    public String getComponentRightIcon() {
        return this.componentRightIcon;
    }

    public void setComponentRightIcon(String str) {
        this.componentRightIcon = str;
    }

    public String getComponentLeftIcon() {
        return this.componentLeftIcon;
    }

    public void setComponentLeftIcon(String str) {
        this.componentLeftIcon = str;
    }
}
