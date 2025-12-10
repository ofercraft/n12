package com.channel2.mobile.ui.Chats.views.TabChats;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.Chats.models.ChatItemType;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class ChatReplayMediaTabItem extends ChatTabViewModel {
    private ChatTabViewModel chatTabViewModel;
    public TextView content;
    public TextView contentReply;
    private ImageView imgVideo;
    private ImageView media;
    private Button readMore;
    private TextView reporterReply;

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public ChatReplayMediaTabItem(View view) {
        super(view);
        view.setOnClickListener(this);
        this.content = (TextView) view.findViewById(R.id.content);
        this.contentReply = (TextView) view.findViewById(R.id.contentReply);
        this.reporterReply = (TextView) view.findViewById(R.id.reporterReply);
        this.media = (ImageView) view.findViewById(R.id.media);
        this.imgVideo = (ImageView) view.findViewById(R.id.imgVideo);
        this.readMore = (Button) view.findViewById(R.id.readMore);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.channel2.mobile.ui.Chats.models.ChatItemType] */
    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        ?? chatItemType;
        String str;
        String str2;
        String thumbnail;
        String link1;
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        String str3 = "";
        String name = chatReportItem.getReply().getName();
        String messageContent = chatReportItem.getMessageContent();
        try {
            chatItemType = chatReportItem.getChatItemType();
            try {
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                str = chatItemType;
                this.reporterReply.setText(name);
                this.contentReply.setText(str3);
                handleContent(this.content, messageContent, this.readMore);
                Glide.with(this.media).load(str).into(this.media);
            }
        } catch (Exception e2) {
            e = e2;
            chatItemType = "";
        }
        if (chatItemType == ChatItemType.mediaReply) {
            if (chatReportItem.getReply().getChatMedias().get(0).getMediaTypeId() == 2) {
                thumbnail = chatReportItem.getReply().getChatMedias().get(0).getThumbnail();
                this.imgVideo.setVisibility(0);
            } else {
                thumbnail = chatReportItem.getReply().getChatMedias().get(0).getLink1();
                this.imgVideo.setVisibility(8);
            }
            if (chatReportItem.getReply().getChatMedias().get(0).getMediaContent().equals("")) {
                link1 = chatReportItem.getReply().getChatMedias().get(0).getMediaTypeId() == 2 ? "וידאו" : "תמונה";
            } else {
                link1 = chatReportItem.getReply().getChatMedias().get(0).getMediaContent();
            }
        } else if (chatReportItem.getChatItemType() == ChatItemType.mediaAndTextReply) {
            if (chatReportItem.getReply().getChatMedias().get(0).getMediaTypeId() == 2) {
                thumbnail = chatReportItem.getReply().getChatMedias().get(0).getThumbnail();
                this.imgVideo.setVisibility(0);
            } else {
                thumbnail = chatReportItem.getReply().getChatMedias().get(0).getLink1();
                this.imgVideo.setVisibility(8);
            }
            link1 = chatReportItem.getReply().getChatMedias().get(0).getMediaContent();
        } else if (chatReportItem.getChatItemType() == ChatItemType.linkReply) {
            this.imgVideo.setVisibility(8);
            thumbnail = chatReportItem.getReply().getChatMedias().get(0).getThumbnail();
            link1 = chatReportItem.getReply().getChatMedias().get(0).getLink1();
        } else {
            str2 = "";
            str = str3;
            str3 = str2;
            this.reporterReply.setText(name);
            this.contentReply.setText(str3);
            handleContent(this.content, messageContent, this.readMore);
            Glide.with(this.media).load(str).into(this.media);
        }
        str2 = link1;
        str3 = thumbnail;
        str = str3;
        str3 = str2;
        this.reporterReply.setText(name);
        this.contentReply.setText(str3);
        handleContent(this.content, messageContent, this.readMore);
        Glide.with(this.media).load(str).into(this.media);
    }

    public ChatReportItem getChatReportItem() {
        return (ChatReportItem) this.lobbyItem;
    }
}
