package com.channel2.mobile.ui.customViews;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.teasers.LobbyTeaser;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public abstract class CustomRecyclerViewHolder extends RecyclerView.ViewHolder implements CustomRecyclerViewInterface {
    public LobbyFragmentHandler handler;
    public Item lobbyItem;

    public CustomRecyclerViewHolder(View view) {
        super(view);
    }

    protected void setFontSize(TextView textView, float f) {
        textView.setTextSize(2, f * Utils.getFloatFromPreferences(textView.getContext(), "zoom").floatValue());
    }

    protected void setGradientBackground(LobbyTeaser lobbyTeaser, View view) {
        String[] strArrSplit = lobbyTeaser.getTeaserBackgroundColor().split(",");
        if (strArrSplit.length == 1) {
            view.setBackgroundColor(Color.parseColor(strArrSplit[0]));
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArrSplit) {
            String strReplace = str.replace(StringUtils.SPACE, "");
            if (strReplace.startsWith("#")) {
                arrayList.add(strReplace);
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = Color.parseColor((String) arrayList.get(i));
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BR_TL, iArr);
        gradientDrawable.setCornerRadius(0.0f);
        view.setBackground(gradientDrawable);
    }

    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        this.lobbyItem = item;
        this.handler = lobbyFragmentHandler;
    }
}
