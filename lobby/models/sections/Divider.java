package com.channel2.mobile.ui.lobby.models.sections;

import com.channel2.mobile.ui.lobby.models.items.DividerType;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class Divider extends Item {
    private DividerType dividerType;
    private String name;

    private String setItemType() {
        return "";
    }

    public Divider(String str, DividerType dividerType) {
        setName(str);
        setDividerType(dividerType);
        setLobbyItemType(str + dividerType);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public DividerType getDividerType() {
        return this.dividerType;
    }

    public void setDividerType(DividerType dividerType) {
        this.dividerType = dividerType;
    }
}
