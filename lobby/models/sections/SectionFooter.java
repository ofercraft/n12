package com.channel2.mobile.ui.lobby.models.sections;

import android.view.View;
import android.widget.TextView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class SectionFooter extends CustomRecyclerViewHolder {
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public SectionFooter(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        this.title.setText(((LobbyFooter) item).getFooterText());
        setFontSize(this.title, 14.0f);
    }
}
