package com.channel2.mobile.ui.lobby.models.items;

import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class Item {
    private String clickUrl;
    private boolean isEmpty = true;
    private long itemId;
    private ItemType lobbyItemType;
    private String mako_ref_comp;
    private int sectionId;
    private long startPosition;

    public static boolean contains(String str) {
        for (ItemType itemType : ItemType.values()) {
            if (itemType.name().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    protected Item(JSONObject jSONObject) {
    }

    public Item() {
    }

    public ItemType getLobbyItemType() {
        return this.lobbyItemType;
    }

    public void setLobbyItemType(ItemType itemType) {
        this.isEmpty = false;
        this.lobbyItemType = itemType;
    }

    protected void setLobbyItemType(String str) {
        this.isEmpty = false;
        this.lobbyItemType = ItemType.vertical;
        if (!contains(str) && !str.equals("")) {
            this.lobbyItemType = ItemType.none;
            return;
        }
        for (int i = 0; i < ItemType.values().length; i++) {
            ItemType itemType = ItemType.values()[i];
            if (itemType.name().equalsIgnoreCase(str)) {
                this.lobbyItemType = itemType;
                return;
            }
        }
    }

    public long getItemId() {
        return this.itemId;
    }

    public void setItemId(long j) {
        this.isEmpty = false;
        this.itemId = j;
    }

    public String getClickUrl() {
        return this.clickUrl;
    }

    public void setClickUrl(String str) {
        this.isEmpty = false;
        this.clickUrl = str;
    }

    public int getSectionId() {
        return this.sectionId;
    }

    public void setSectionId(int i) {
        this.isEmpty = false;
        this.sectionId = i;
    }

    public long getStartPosition() {
        return this.startPosition;
    }

    public void setStartPosition(long j) {
        this.isEmpty = false;
        this.startPosition = j;
    }

    public boolean isAd() {
        return getLobbyItemType() == ItemType.regularItemSmallAd || getLobbyItemType() == ItemType.regularitemsmallcAd || getLobbyItemType() == ItemType.fluid || getLobbyItemType() == ItemType.banner || getLobbyItemType() == ItemType.sponsored || getLobbyItemType() == ItemType.feedroll || getLobbyItemType() == ItemType.advertisingitem || getLobbyItemType() == ItemType.textitemregularAd;
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public boolean isNotEmpty() {
        return !this.isEmpty;
    }

    public String getMako_ref_comp() {
        return this.mako_ref_comp;
    }

    public void setMako_ref_comp(String str) {
        this.mako_ref_comp = str;
    }
}
