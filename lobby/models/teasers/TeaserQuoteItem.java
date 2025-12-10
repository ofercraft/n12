package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class TeaserQuoteItem extends CustomRecyclerViewHolder {
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserQuoteItem(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        if (lobbyTeaser.getCaption() != null && lobbyTeaser.getCaption().length() > 0) {
            String str = lobbyTeaser.getCaption() + " // ";
            SpannableString spannableString = new SpannableString(str + lobbyTeaser.getTitle());
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#AE0000")), 0, str.length(), 33);
            this.title.setText(spannableString);
        } else {
            this.title.setText(lobbyTeaser.getTitle());
        }
        setFontSize(this.title, 24.0f);
    }
}
