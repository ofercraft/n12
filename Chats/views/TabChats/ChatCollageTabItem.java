package com.channel2.mobile.ui.Chats.views.TabChats;

import android.view.View;
import android.widget.ImageView;
import com.channel2.mobile.ui.Chats.models.ChatMediaItem;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class ChatCollageTabItem extends ChatTabViewModel {
    private ImageView first_media;
    private ImageView play_first;
    private ImageView play_second;
    private ImageView play_third;
    private ImageView second_media;
    private ImageView third_media;

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public ChatCollageTabItem(View view) {
        super(view);
        this.first_media = (ImageView) view.findViewById(R.id.first_media);
        this.second_media = (ImageView) view.findViewById(R.id.second_media);
        this.third_media = (ImageView) view.findViewById(R.id.third_media);
        this.play_first = (ImageView) view.findViewById(R.id.play_first);
        this.play_second = (ImageView) view.findViewById(R.id.play_second);
        this.play_third = (ImageView) view.findViewById(R.id.play_third);
    }

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        for (int i = 0; i < chatReportItem.getChatMedias().size(); i++) {
            ChatMediaItem chatMedia = chatReportItem.getChatMedia(i);
            if (i == 0) {
                setVideoImage(this.first_media, this.play_first, chatMedia);
            } else if (i == 1) {
                setVideoImage(this.second_media, this.play_second, chatMedia);
            } else if (i == 2) {
                setVideoImage(this.third_media, this.play_third, chatMedia);
            }
        }
    }

    public ChatReportItem getChatReportItem() {
        return (ChatReportItem) this.lobbyItem;
    }
}
