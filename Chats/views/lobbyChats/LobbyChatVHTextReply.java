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
public class LobbyChatVHTextReply extends LobbyChatViewModel {
    public TextView content;
    public TextView contentReply;
    private TextView reporterReply;

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public LobbyChatVHTextReply(View view) {
        super(view);
        this.content = (TextView) view.findViewById(R.id.content);
        this.contentReply = (TextView) view.findViewById(R.id.contentReply);
        this.reporterReply = (TextView) view.findViewById(R.id.reporterReply);
    }

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        this.reporterReply.setText(chatReportItem.getReply().getName());
        this.content.setText(chatReportItem.getMessageContent());
        this.contentReply.setText(chatReportItem.getReply().getMessageContent());
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
