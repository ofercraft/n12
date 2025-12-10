package com.channel2.mobile.ui.lobby.models.teasers;

import android.view.View;
import android.widget.TextView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class TeaserTextItemFlach extends CustomRecyclerViewHolder {
    private View divider;
    private TextView flach;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserTextItemFlach(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.flach = (TextView) view.findViewById(R.id.flach);
        this.divider = view.findViewById(R.id.divider);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        if (lobbyTeaser.getFlachText().length() > 0) {
            this.flach.setText(lobbyTeaser.getFlachText());
            setFontSize(this.flach, 14.0f);
            this.flach.setVisibility(0);
        } else {
            this.flach.setVisibility(8);
        }
        this.title.setText(lobbyTeaser.getTitle());
        setFontSize(this.title, 20.0f);
        if (lobbyTeaser.isDisplayDivider()) {
            this.divider.setVisibility(0);
        } else {
            this.divider.setVisibility(8);
        }
    }
}
