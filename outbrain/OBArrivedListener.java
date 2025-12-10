package com.channel2.mobile.ui.outbrain;

import com.channel2.mobile.ui.lobby.models.items.Item;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public interface OBArrivedListener {
    void onFailed();

    void onOBReady(String str, ArrayList<Item> arrayList);
}
