package com.channel2.mobile.ui.Chats.views.lobbyChats;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class LobbyChatViewModel extends CustomRecyclerViewHolder {
    private View bg;
    private Group groupChat;
    private int[] groupChatIds;
    private Group groupTyping;
    private TextView reporter;
    private ImageView reporterImage;
    private Long start;
    private TextView time;
    private int topicColor;
    private ImageView typing;
    private View typingBG;

    public void onScrollStateIdle() {
    }

    public LobbyChatViewModel(View view) {
        super(view);
        this.topicColor = Color.parseColor("#AE0000");
        this.reporterImage = (ImageView) view.findViewById(R.id.reporterImage);
        this.reporter = (TextView) view.findViewById(R.id.reporter);
        this.time = (TextView) view.findViewById(R.id.time);
        this.bg = view.findViewById(R.id.bg);
        this.typingBG = view.findViewById(R.id.typingBG);
        this.typing = (ImageView) view.findViewById(R.id.chat_typing);
        this.groupChat = (Group) view.findViewById(R.id.groupChat);
        this.groupTyping = (Group) view.findViewById(R.id.groupTyping);
        this.groupChatIds = this.groupChat.getReferencedIds();
    }

    public void setTopicColor(int i) {
        this.topicColor = i;
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        ChatReportItem chatReportItem = (ChatReportItem) item;
        hideAll();
        Glide.with(this.reporterImage).load(chatReportItem.getReporterImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(this.reporterImage);
        this.reporter.setText(chatReportItem.getName());
        this.reporter.setTextColor(this.topicColor);
        this.time.setText(chatReportItem.getTimeString());
        this.time.setTextColor(this.topicColor);
        if (chatReportItem.isFirst()) {
            float f = this.itemView.getContext().getApplicationContext().getResources().getDisplayMetrics().density;
            ((ViewGroup.MarginLayoutParams) this.bg.getLayoutParams()).setMargins(0, (int) (10 * f), 0, (int) (2 * f));
        } else {
            this.reporter.setVisibility(8);
            int i = (int) (2 * this.itemView.getContext().getApplicationContext().getResources().getDisplayMetrics().density);
            ((ViewGroup.MarginLayoutParams) this.bg.getLayoutParams()).setMargins(0, i, 0, i);
        }
        if (chatReportItem.isTyping()) {
            initTyping();
        } else {
            showChat();
        }
    }

    public void initTyping() {
        this.reporterImage.setVisibility(0);
        this.reporterImage.setAlpha(1.0f);
        this.groupTyping.setVisibility(0);
        this.typing.post(new Runnable() { // from class: com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel.1
            @Override // java.lang.Runnable
            public void run() {
                AnimationDrawable animationDrawable = (AnimationDrawable) LobbyChatViewModel.this.typing.getBackground();
                if (animationDrawable.isRunning()) {
                    return;
                }
                animationDrawable.start();
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel.2
            @Override // java.lang.Runnable
            public void run() {
                LobbyChatViewModel.this.groupTyping.setVisibility(4);
                LobbyChatViewModel.this.showChat();
            }
        }, 1500L);
    }

    public void showChat() {
        this.groupTyping.setVisibility(4);
        for (int i = 0; i < this.groupChatIds.length; i++) {
            this.itemView.findViewById(this.groupChatIds[i]).setAlpha(1.0f);
        }
        ChatReportItem chatReportItem = (ChatReportItem) this.lobbyItem;
        if (!chatReportItem.isFirst()) {
            this.reporterImage.setVisibility(8);
        } else {
            this.reporterImage.setVisibility(0);
            this.reporter.setVisibility(0);
            this.reporterImage.setAlpha(1.0f);
            this.reporter.setAlpha(1.0f);
        }
        if (this.itemView.getTag() == "LobbyChatVHMedia" || this.itemView.getTag() == "LobbyChatVHMediaAndText") {
            if (chatReportItem.getNumberOfMedias() > 0 && chatReportItem.getChatMedia(0).getMediaTypeId() == 2) {
                this.itemView.findViewById(R.id.play).setVisibility(0);
            }
            if (chatReportItem.getNumberOfMedias() > 1 && this.itemView.findViewById(R.id.media2) != null) {
                this.itemView.findViewById(R.id.media2).setVisibility(0);
            }
            if (chatReportItem.getNumberOfMedias() <= 2 || this.itemView.findViewById(R.id.media3) == null) {
                return;
            }
            this.itemView.findViewById(R.id.media3).setVisibility(0);
            this.itemView.findViewById(R.id.more_media).setVisibility(0);
        }
    }

    public void hideAll() {
        this.groupTyping.setVisibility(4);
        for (int i = 0; i < this.groupChatIds.length; i++) {
            this.itemView.findViewById(this.groupChatIds[i]).setAlpha(0.0f);
        }
        this.reporterImage.setVisibility(0);
        this.reporter.setVisibility(0);
        this.reporterImage.setAlpha(0.0f);
        this.reporter.setAlpha(0.0f);
        if (this.itemView.getTag() == "LobbyChatVHMedia" || this.itemView.getTag() == "LobbyChatVHMediaReply") {
            this.itemView.findViewById(R.id.play).setVisibility(4);
        }
        if (this.itemView.getTag() == "LobbyChatVHMedia") {
            this.itemView.findViewById(R.id.media2).setVisibility(8);
            this.itemView.findViewById(R.id.media2).setVisibility(8);
            this.itemView.findViewById(R.id.media3).setVisibility(8);
            this.itemView.findViewById(R.id.more_media).setVisibility(8);
        }
    }
}
