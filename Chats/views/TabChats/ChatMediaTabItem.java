package com.channel2.mobile.ui.Chats.views.TabChats;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class ChatMediaTabItem extends ChatTabViewModel {
    public static final int MAX_LINES = 11;
    private TextView content;
    public Context context;
    private ImageView media;
    private Button readMore;

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public ChatMediaTabItem(View view) {
        super(view);
        this.media = (ImageView) view.findViewById(R.id.media);
        this.content = (TextView) view.findViewById(R.id.content);
        this.context = view.getContext();
        this.readMore = (Button) view.findViewById(R.id.readMore);
    }

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        setVideoImage(this.media, null, chatReportItem.getChatMedia(0));
        handleContent(this.content, chatReportItem.getChatMedia(0).getMediaContent(), this.readMore);
    }

    public ChatReportItem getChatReportItem() {
        return (ChatReportItem) this.lobbyItem;
    }
}
