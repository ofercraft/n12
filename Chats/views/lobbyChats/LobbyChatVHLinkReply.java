package com.channel2.mobile.ui.Chats.views.lobbyChats;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class LobbyChatVHLinkReply extends LobbyChatViewModel {
    private TextView ContentReply;
    public TextView content;
    private ImageView media;
    private ImageView play;
    private TextView titleReply;

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public LobbyChatVHLinkReply(View view) {
        super(view);
        this.content = (TextView) view.findViewById(R.id.content);
        this.titleReply = (TextView) view.findViewById(R.id.titleReply);
        this.ContentReply = (TextView) view.findViewById(R.id.ContentReply);
        this.media = (ImageView) view.findViewById(R.id.media1);
        this.play = (ImageView) view.findViewById(R.id.play);
    }

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        this.content.setText(chatReportItem.getMessageContent());
        String name = "";
        if (chatReportItem.getReply().getName() != null && chatReportItem.getReply().getName() != "") {
            name = chatReportItem.getReply().getName();
        }
        this.titleReply.setText(name);
        if (chatReportItem.getReply().getNumberOfMedias() > 0) {
            this.ContentReply.setText(chatReportItem.getReply().getChatMedia(0).getLink1());
            Glide.with(this.media).load(chatReportItem.getReply().getChatMedia(0).getThumbnail()).into(this.media);
        }
        this.play.setAlpha(0.0f);
    }

    public ChatReportItem getChatReportItem() {
        return (ChatReportItem) this.lobbyItem;
    }
}
