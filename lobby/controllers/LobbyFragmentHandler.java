package com.channel2.mobile.ui.lobby.controllers;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.teasers.LobbyTeaser;

/* loaded from: classes2.dex */
public interface LobbyFragmentHandler {
    void enableVerticleScroll(boolean z);

    void onClick(Item item, Bundle bundle);

    void onListRefreshed();

    void onPauseVideo(LobbyTeaser lobbyTeaser, FrameLayout frameLayout, ImageView imageView);

    void onPlayVideo(LobbyTeaser lobbyTeaser, FrameLayout frameLayout, ImageView imageView);

    void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder);

    void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder);

    void readMore(int i);
}
