package com.channel2.mobile.ui.lobby.models.sections;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewAdapter;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.ItemManager;
import com.channel2.mobile.ui.lobby.models.teasers.LobbyTeaser;

/* loaded from: classes2.dex */
public class SectionHorizontal extends CustomRecyclerViewHolder {
    private ImageView backgroundImage;
    private View itemView;
    private RecyclerView mRecyclerView;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public SectionHorizontal(View view) {
        super(view);
        this.itemView = view;
        this.title = (TextView) view.findViewById(R.id.title);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, final LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbySection lobbySection = (LobbySection) item;
        this.title.setText(lobbySection.getName());
        setFontSize(this.title, 25.0f);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mRecyclerView.requestDisallowInterceptTouchEvent(true);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mRecyclerView.getContext(), 0, true));
        CustomRecyclerViewAdapter customRecyclerViewAdapter = new CustomRecyclerViewAdapter(new ItemManager(), new LobbyFragmentHandler() { // from class: com.channel2.mobile.ui.lobby.models.sections.SectionHorizontal.1
            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void enableVerticleScroll(boolean z) {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onListRefreshed() {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onPauseVideo(LobbyTeaser lobbyTeaser, FrameLayout frameLayout, ImageView imageView) {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onPlayVideo(LobbyTeaser lobbyTeaser, FrameLayout frameLayout, ImageView imageView) {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void readMore(int i) {
            }

            @Override // com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler
            public void onClick(Item item2, Bundle bundle) {
                item2.setMako_ref_comp("hp_teaser");
                lobbyFragmentHandler.onClick(item2, null);
            }
        });
        customRecyclerViewAdapter.submitList(lobbySection.getHorizontalItems());
        this.mRecyclerView.setAdapter(customRecyclerViewAdapter);
    }
}
