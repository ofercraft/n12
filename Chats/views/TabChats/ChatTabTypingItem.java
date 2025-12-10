package com.channel2.mobile.ui.Chats.views.TabChats;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class ChatTabTypingItem extends CustomRecyclerViewHolder {
    private ImageView reporterImage;
    private ImageView typing;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public ChatTabTypingItem(View view) {
        super(view);
        this.reporterImage = (ImageView) view.findViewById(R.id.reporterImage);
        this.typing = (ImageView) view.findViewById(R.id.chat_typing);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        Glide.with(this.reporterImage).load(((ChatReportItem) item).getReporterImage()).placeholder(R.drawable.reporter_place_holder).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(this.reporterImage);
        initTyping();
    }

    public void initTyping() {
        this.typing.post(new Runnable() { // from class: com.channel2.mobile.ui.Chats.views.TabChats.ChatTabTypingItem.1
            @Override // java.lang.Runnable
            public void run() {
                AnimationDrawable animationDrawable = (AnimationDrawable) ChatTabTypingItem.this.typing.getBackground();
                if (animationDrawable.isRunning()) {
                    return;
                }
                animationDrawable.start();
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.views.TabChats.ChatTabTypingItem.2
            @Override // java.lang.Runnable
            public void run() {
                ((AnimationDrawable) ChatTabTypingItem.this.typing.getBackground()).stop();
            }
        }, 1500L);
    }
}
