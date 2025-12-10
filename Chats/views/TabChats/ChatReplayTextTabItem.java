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
public class ChatReplayTextTabItem extends ChatTabViewModel {
    private ChatTabViewModel chatTabViewModel;
    ConstraintLayout constraintLayout;
    public TextView content;
    public TextView contentReply;
    private Button readMore;
    private TextView reporter;
    private ImageView reporterImage;
    private TextView reporterReply;

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public ChatReplayTextTabItem(View view) {
        super(view);
        view.setOnClickListener(this);
        this.content = (TextView) view.findViewById(R.id.content);
        this.contentReply = (TextView) view.findViewById(R.id.contentReply);
        this.reporterReply = (TextView) view.findViewById(R.id.reporterReply);
        this.reporter = (TextView) view.findViewById(R.id.reporter);
        this.reporterImage = (ImageView) view.findViewById(R.id.reporterImage);
        this.readMore = (Button) view.findViewById(R.id.readMore);
        this.constraintLayout = (ConstraintLayout) view.findViewById(R.id.reply_constraint_view);
    }

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        String name = "";
        if (chatReportItem.getName() != null && chatReportItem.getName() != "") {
            name = chatReportItem.getName();
        }
        this.reporter.setText(name);
        this.reporterReply.setText(chatReportItem.getReply().getName());
        handleContent(this.content, chatReportItem.getMessageContent(), this.readMore);
        this.contentReply.setText(chatReportItem.getReply().getMessageContent());
        Glide.with(this.reporterImage).load(chatReportItem.getReporterImage()).placeholder(R.drawable.reporter_place_holder).into(this.reporterImage);
    }

    private void fixBubbleView() {
        this.contentReply.measure(0, 0);
        this.contentReply.getMeasuredHeight();
        int measuredWidth = this.contentReply.getMeasuredWidth();
        this.reporterReply.measure(0, 0);
        this.reporterReply.getMeasuredHeight();
        int measuredWidth2 = this.reporterReply.getMeasuredWidth();
        this.content.measure(0, 0);
        this.content.getMeasuredHeight();
        int measuredWidth3 = this.content.getMeasuredWidth();
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.constraintLayout);
        if (measuredWidth3 <= measuredWidth || measuredWidth3 <= measuredWidth2) {
            if (measuredWidth > measuredWidth2) {
                constraintSet.connect(R.id.bg, 7, R.id.contentReply, 7, 0);
            } else {
                constraintSet.connect(R.id.bg, 7, R.id.reporterReply, 7, 0);
            }
        }
        constraintSet.applyTo(this.constraintLayout);
    }

    public ChatReportItem getChatReportItem() {
        return (ChatReportItem) this.lobbyItem;
    }
}
