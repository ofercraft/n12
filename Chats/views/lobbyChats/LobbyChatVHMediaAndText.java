package com.channel2.mobile.ui.Chats.views.lobbyChats;

import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class LobbyChatVHMediaAndText extends LobbyChatViewModel {
    public TextView content;
    private ImageView media;
    private ImageView play;

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public LobbyChatVHMediaAndText(View view) {
        super(view);
        this.content = (TextView) view.findViewById(R.id.content);
        this.media = (ImageView) view.findViewById(R.id.media1);
        this.play = (ImageView) view.findViewById(R.id.play);
    }

    @Override // com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        if (chatReportItem.getNumberOfMedias() > 0) {
            if (chatReportItem.getChatMedia(0).getMediaTypeId() == 2) {
                Glide.with(this.media).load(chatReportItem.getChatMedia(0).getThumbnail()).into(this.media);
            } else {
                Glide.with(this.media).load(chatReportItem.getChatMedia(0).getLink1()).into(this.media);
            }
            this.content.setText(chatReportItem.getChatMedia(0).getMediaContent());
        }
    }

    public ChatReportItem getChatReportItem() {
        return (ChatReportItem) this.lobbyItem;
    }

    public String stripHtml(String str) {
        if (str.length() <= 0) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(str, 0).toString();
        }
        return Html.fromHtml(str).toString();
    }
}
