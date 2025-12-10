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
public class LobbyChatVHLink extends LobbyChatViewModel {
    public TextView content;
    private TextView linkContent;
    private TextView linkTitle;
    private ImageView media;
    private ImageView play;

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public LobbyChatVHLink(View view) {
        super(view);
        this.content = (TextView) view.findViewById(R.id.content);
        this.linkTitle = (TextView) view.findViewById(R.id.linkTitle);
        this.linkContent = (TextView) view.findViewById(R.id.linkContent);
        this.media = (ImageView) view.findViewById(R.id.media1);
        this.play = (ImageView) view.findViewById(R.id.play);
    }

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        if (chatReportItem.getNumberOfMedias() > 0) {
            this.content.setText(chatReportItem.getChatMedia(0).getLink1());
            this.linkTitle.setText(chatReportItem.getChatMedia(0).getLinkTitle());
            this.linkContent.setText(chatReportItem.getChatMedia(0).getLinkDescription());
            Glide.with(this.media).load(chatReportItem.getChatMedia(0).getThumbnail()).into(this.media);
        }
        this.play.setAlpha(0.0f);
    }

    public ChatReportItem getChatReportItem() {
        return (ChatReportItem) this.lobbyItem;
    }
}
