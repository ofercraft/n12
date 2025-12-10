package com.channel2.mobile.ui.lobby.models.items;

/* loaded from: classes2.dex */
public class ObituaryItem {
    String ImgUrl;
    int ItemOrder;
    String link;
    String mobileImgUrl;

    public ObituaryItem(int i, String str, String str2, String str3) {
        this.ItemOrder = i;
        this.ImgUrl = str;
        this.mobileImgUrl = str2;
        this.link = str3;
    }

    public int getItemOrder() {
        return this.ItemOrder;
    }

    public void setItemOrder(int i) {
        this.ItemOrder = i;
    }

    public String getImgUrl() {
        return this.ImgUrl;
    }

    public void setImgUrl(String str) {
        this.ImgUrl = str;
    }

    public String getMobileImgUrl() {
        return this.mobileImgUrl;
    }

    public void setMobileImgUrl(String str) {
        this.mobileImgUrl = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }
}
