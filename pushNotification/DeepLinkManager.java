package com.channel2.mobile.ui.pushNotification;

/* loaded from: classes2.dex */
public class DeepLinkManager {
    private static DeepLinkManager instance;
    private Boolean openedByLink = false;
    private String pushId;
    private String url;

    private DeepLinkManager() {
    }

    public static DeepLinkManager getInstance() {
        if (instance == null) {
            instance = new DeepLinkManager();
        }
        return instance;
    }

    public String getPushId() {
        return this.pushId;
    }

    public void setPushId(String str) {
        this.pushId = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Boolean isOpenedByLink() {
        return this.openedByLink;
    }

    public void setOpenedByLink(Boolean bool) {
        this.openedByLink = bool;
    }

    public void killInstance() {
        this.url = null;
        this.pushId = null;
        instance = null;
    }
}
