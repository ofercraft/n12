package com.channel2.mobile.ui.Chats.views.TabChats;

import android.view.View;
import android.widget.TextView;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class ChatSectionHeader extends CustomRecyclerViewHolder {
    public TextView headerSectionText;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public ChatSectionHeader(View view) {
        super(view);
        this.headerSectionText = (TextView) view.findViewById(R.id.headerSectionText);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        this.headerSectionText.setText(((ChatReportItem) item).getMessageContent());
    }
}
