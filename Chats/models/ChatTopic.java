package com.channel2.mobile.ui.Chats.models;

import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ChatTopic {
    private String background;
    JSONObject chatTopicJson;
    private int chat_lobby_teaser_count;
    private String color;
    private String iconSelected;
    private String iconUnselected;
    private int topicId;
    private String topicName;
    private String topicTitle;

    public ChatTopic(JSONObject jSONObject) {
        this.chatTopicJson = jSONObject;
        setTopicId(jSONObject.optInt("topic", 1));
        setTopicName(jSONObject.optString("topicName"));
        setTopicTitle(jSONObject.optString("topicTitle"));
        setColor(jSONObject.optString("color"));
        setIconSelected(jSONObject.optString("iconSelected"));
        setIconUnselected(jSONObject.optString("iconUnselected"));
        setBackground(jSONObject.optString("background"));
        setChat_lobby_teaser_count(jSONObject.optInt("chat_lobby_teaser_count", 0));
    }

    public int getTopicId() {
        return this.topicId;
    }

    public void setTopicId(int i) {
        this.topicId = i;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public void setTopicName(String str) {
        this.topicName = str;
    }

    public String getTopicTitle() {
        return this.topicTitle;
    }

    public void setTopicTitle(String str) {
        this.topicTitle = str;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public String getIconSelected() {
        return this.iconSelected;
    }

    public void setIconSelected(String str) {
        this.iconSelected = str;
    }

    public String getIconUnselected() {
        return this.iconUnselected;
    }

    public void setIconUnselected(String str) {
        this.iconUnselected = str;
    }

    public int getChat_lobby_teaser_count() {
        return this.chat_lobby_teaser_count;
    }

    public void setChat_lobby_teaser_count(int i) {
        this.chat_lobby_teaser_count = i;
    }

    public String getBackground() {
        return this.background;
    }

    public void setBackground(String str) {
        this.background = str;
    }
}
