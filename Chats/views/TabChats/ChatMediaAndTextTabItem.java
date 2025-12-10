package com.channel2.mobile.ui.Chats.views.TabChats;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class ChatMediaAndTextTabItem extends ChatTabViewModel {
    private ChatTabViewModel chatTabViewModel;
    public TextView content;
    private ImageView media;
    private ImageView play;

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public ChatMediaAndTextTabItem(View view) {
        super(view);
        this.content = (TextView) view.findViewById(R.id.content);
        this.media = (ImageView) view.findViewById(R.id.media);
        this.play = (ImageView) view.findViewById(R.id.play);
    }

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        this.content.setText(chatReportItem.getChatMedia(0).getMediaContent());
        setVideoImage(this.media, this.play, chatReportItem.getChatMedia(0));
    }
}
