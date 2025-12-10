package com.channel2.mobile.ui.Chats.views.TabChats;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class ChatLinkTabItem extends ChatTabViewModel {
    ConstraintLayout constraintLayout;
    public TextView content;
    private TextView linkContent;
    private TextView linkTitle;
    private ImageView media;
    private ImageView play;
    private Button readMore;

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public ChatLinkTabItem(View view) {
        super(view);
        this.content = (TextView) view.findViewById(R.id.content);
        this.linkTitle = (TextView) view.findViewById(R.id.linkTitle);
        this.linkContent = (TextView) view.findViewById(R.id.linkContent);
        this.media = (ImageView) view.findViewById(R.id.media);
        this.play = (ImageView) view.findViewById(R.id.play);
        this.readMore = (Button) view.findViewById(R.id.readMore);
        this.constraintLayout = (ConstraintLayout) view.findViewById(R.id.link_constraint_view);
    }

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        String messageContent = chatReportItem.getMessageContent();
        String link1 = chatReportItem.getChatMedia(0).getLink1();
        if (messageContent.length() <= 0) {
            messageContent = link1;
        }
        handleContent(this.content, messageContent, this.readMore);
        this.linkTitle.setText(chatReportItem.getChatMedia(0).getLinkTitle());
        if (chatReportItem.getChatMedia(0).getLinkDescription().equals("")) {
            this.linkContent.setText("");
            this.linkContent.setVisibility(8);
        } else {
            this.linkContent.setVisibility(0);
            this.linkContent.setText(chatReportItem.getChatMedia(0).getLinkDescription());
        }
        Glide.with(this.media).load(chatReportItem.getChatMedia(0).getThumbnail()).into(this.media);
        if (chatReportItem.getChatMedia(0).getMediaTypeId() == 2) {
            this.play.setVisibility(0);
        }
    }

    private void fixBubbleView() {
        this.linkContent.measure(0, 0);
        this.linkContent.getMeasuredHeight();
        int measuredWidth = this.linkContent.getMeasuredWidth();
        this.content.measure(0, 0);
        this.content.getMeasuredHeight();
        int measuredWidth2 = this.content.getMeasuredWidth();
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.constraintLayout);
        if (measuredWidth2 < measuredWidth) {
            constraintSet.connect(R.id.bg, 7, R.id.linkContent, 7, 0);
        } else {
            constraintSet.connect(R.id.bg, 7, R.id.content, 7, 0);
        }
        constraintSet.applyTo(this.constraintLayout);
    }
}
