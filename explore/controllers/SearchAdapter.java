package com.channel2.mobile.ui.explore.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.explore.models.SearchResultsItem;
import com.channel2.mobile.ui.explore.views.SearchPlaceholder;
import com.channel2.mobile.ui.explore.views.SearchRecyclerViewHolder;
import com.channel2.mobile.ui.explore.views.SearchViewHolder;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class SearchAdapter extends RecyclerView.Adapter<SearchRecyclerViewHolder> {
    private ClickHandler handler;
    private ArrayList<SearchResultsItem> items;

    public interface ClickHandler {
        void onClick(SearchResultsItem searchResultsItem);
    }

    public SearchAdapter(ArrayList<SearchResultsItem> arrayList, ClickHandler clickHandler) {
        this.items = arrayList;
        this.handler = clickHandler;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SearchRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new SearchPlaceholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teaser_placeholder_small, viewGroup, false));
        }
        return new SearchViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teaser_regular_small, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SearchRecyclerViewHolder searchRecyclerViewHolder, final int i) {
        searchRecyclerViewHolder.initial(this.items.get(i));
        searchRecyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.explore.controllers.SearchAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchAdapter.this.handler.onClick((SearchResultsItem) SearchAdapter.this.items.get(i));
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (this.items.get(i).getLobbyItemType() == null || !this.items.get(i).getLobbyItemType().name().contains("placeholder")) ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public void setLobbyItems(ArrayList<SearchResultsItem> arrayList) {
        this.items = arrayList;
    }
}
