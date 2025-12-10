package com.channel2.mobile.ui.Chats.views.lobbyChats;

import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class LobbyChatVHText extends LobbyChatViewModel {
    public TextView content;

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public LobbyChatVHText(View view) {
        super(view);
        this.content = (TextView) view.findViewById(R.id.content);
    }

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        this.content.setText(((ChatReportItem) item).getMessageContent());
    }

    public ChatReportItem getChatReportItem() {
        return (ChatReportItem) this.lobbyItem;
    }

    public String stripHtml(String str) {
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(str, 0).toString();
        }
        return Html.fromHtml(str).toString();
    }
}
