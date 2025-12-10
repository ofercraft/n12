package com.channel2.mobile.ui.Chats.views.lobbyChats;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class LobbyChatVHMedia extends LobbyChatViewModel {
    private Group groupChat;
    private int[] groupIds;
    private ImageView media1;
    private ImageView media2;
    private ImageView media3;
    private TextView moreMedias;
    private ImageView play;

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public LobbyChatVHMedia(View view) {
        super(view);
        this.media1 = (ImageView) view.findViewById(R.id.media1);
        this.media2 = (ImageView) view.findViewById(R.id.media2);
        this.media3 = (ImageView) view.findViewById(R.id.media3);
        this.moreMedias = (TextView) view.findViewById(R.id.more_media);
        this.play = (ImageView) view.findViewById(R.id.play);
        Group group = (Group) view.findViewById(R.id.groupChat);
        this.groupChat = group;
        this.groupIds = group.getReferencedIds();
    }

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        if (chatReportItem.getNumberOfMedias() > 0) {
            if (chatReportItem.getChatMedia(0).getMediaTypeId() == 2) {
                Glide.with(this.media1).load(chatReportItem.getChatMedia(0).getThumbnail()).into(this.media1);
            } else {
                Glide.with(this.media1).load(chatReportItem.getChatMedia(0).getLink1()).into(this.media1);
            }
        }
        if (chatReportItem.getNumberOfMedias() > 1) {
            if (chatReportItem.getChatMedia(1).getMediaTypeId() == 2) {
                Glide.with(this.media2).load(chatReportItem.getChatMedia(1).getThumbnail()).into(this.media2);
            } else {
                Glide.with(this.media2).load(chatReportItem.getChatMedia(1).getLink1()).into(this.media2);
            }
        }
        if (chatReportItem.getNumberOfMedias() > 2) {
            if (chatReportItem.getChatMedia(2).getMediaTypeId() == 2) {
                Glide.with(this.media3).load(chatReportItem.getChatMedia(2).getThumbnail()).into(this.media3);
            } else {
                Glide.with(this.media3).load(chatReportItem.getChatMedia(2).getLink1()).into(this.media3);
            }
            this.moreMedias.setVisibility(8);
        }
        if (chatReportItem.getNumberOfMedias() > 3) {
            this.moreMedias.setVisibility(0);
            this.moreMedias.setText("+" + (chatReportItem.getNumberOfMedias() - 2));
        }
    }

    public ChatReportItem getChatReportItem() {
        return (ChatReportItem) this.lobbyItem;
    }
}
