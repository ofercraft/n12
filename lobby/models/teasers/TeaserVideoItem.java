package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class TeaserVideoItem extends CustomRecyclerViewHolder {
    public ConstraintLayout container;
    public ImageView image;
    private boolean playingVideo;
    public TextView title;
    public FrameLayout videoContainer;
    private TextView videoFlach;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserVideoItem(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.videoFlach = (TextView) view.findViewById(R.id.videoFlach);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.videoContainer = (FrameLayout) view.findViewById(R.id.videoContainer);
        this.playingVideo = false;
        this.container = (ConstraintLayout) view.findViewById(R.id.teaserVideoNew);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        this.playingVideo = false;
        this.title.setText(lobbyTeaser.getTitle());
        setFontSize(this.title, 17.0f);
        if (lobbyTeaser.getVideoFlachText() != null && lobbyTeaser.getVideoFlachText().length() > 0) {
            this.videoFlach.setText(lobbyTeaser.getVideoFlachText());
            setFontSize(this.videoFlach, 17.0f);
            if (lobbyTeaser.getVideoFlachTextColor().length() > 0) {
                this.videoFlach.setTextColor(Color.parseColor(lobbyTeaser.getVideoFlachTextColor()));
                this.videoFlach.setBackgroundColor(Color.parseColor(lobbyTeaser.getVideoFlachBackgroundColor()));
            } else {
                this.videoFlach.setTextColor(Color.parseColor("#242A32"));
                this.videoFlach.setBackgroundColor(Color.parseColor("#979797"));
            }
            this.videoFlach.setVisibility(0);
        } else {
            this.videoFlach.setVisibility(8);
        }
        Glide.with(this.image).load(lobbyTeaser.getImage()).into(this.image);
    }

    public LobbyTeaser getLobbyTeaser() {
        return (LobbyTeaser) this.lobbyItem;
    }
}
