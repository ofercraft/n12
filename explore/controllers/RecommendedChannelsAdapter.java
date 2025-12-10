package com.channel2.mobile.ui.explore.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.explore.models.Channel;
import com.channel2.mobile.ui.explore.views.RecommendedHeaderViewHolder;
import com.channel2.mobile.ui.explore.views.RecommendedRecyclerViewHolder;
import com.channel2.mobile.ui.explore.views.RecommendedViewHolder;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class RecommendedChannelsAdapter extends RecyclerView.Adapter<RecommendedRecyclerViewHolder> {
    private ArrayList<Channel> channels;
    private ClickHandler handler;

    public interface ClickHandler {
        void onClick(Channel channel);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    public RecommendedChannelsAdapter(ArrayList<Channel> arrayList, ClickHandler clickHandler) {
        this.channels = arrayList;
        this.handler = clickHandler;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecommendedRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new RecommendedHeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recomended_channel_header, viewGroup, false));
        }
        return new RecommendedViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recomended_channel, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecommendedRecyclerViewHolder recommendedRecyclerViewHolder, final int i) {
        recommendedRecyclerViewHolder.initial(this.channels.get(i));
        recommendedRecyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.explore.controllers.RecommendedChannelsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendedChannelsAdapter.this.handler.onClick((Channel) RecommendedChannelsAdapter.this.channels.get(i));
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.channels.size();
    }
}
