package com.channel2.mobile.ui.lobby.models.sections;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.ItemManager;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserSpecialItem;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserVideoItem;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class CustomViewPagerAdapter extends RecyclerView.Adapter<CustomRecyclerViewHolder> {
    private Context context;
    private LobbyFragmentHandler handler;
    private ItemManager itemManager;
    private int itemWidth;
    private ArrayList<Item> items;
    private int playPosition;
    public ValueAnimator va;

    public CustomViewPagerAdapter(Context context, ArrayList<Item> arrayList, LobbyFragmentHandler lobbyFragmentHandler) {
        this.playPosition = 0;
        this.context = context;
        this.items = arrayList;
        this.handler = lobbyFragmentHandler;
        this.itemManager = new ItemManager();
    }

    public CustomViewPagerAdapter() {
        this.playPosition = 0;
    }

    public void setCurrentPostion(int i) {
        this.playPosition = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CustomRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.itemManager.getLobbyItemView(i, viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(CustomRecyclerViewHolder customRecyclerViewHolder, int i) {
        ViewGroup.LayoutParams layoutParams = customRecyclerViewHolder.itemView.getLayoutParams();
        if (customRecyclerViewHolder instanceof TeaserVideoItem) {
            layoutParams.width = (int) (Utils.getScreenWidth(customRecyclerViewHolder.itemView.getContext()) * 0.8d);
            layoutParams.height = -2;
        } else if (customRecyclerViewHolder instanceof TeaserSpecialItem) {
            layoutParams.width = (int) (Utils.getScreenWidth(customRecyclerViewHolder.itemView.getContext()) * 0.9d);
        }
        customRecyclerViewHolder.itemView.setLayoutParams(layoutParams);
        customRecyclerViewHolder.setViewResources(this.items.get(i), this.handler);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.itemManager.getItemViewType(this.items.get(i).getLobbyItemType());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    private void setPlayPosition(int i) {
        this.playPosition = i;
    }

    public int getPlayPosition() {
        return this.playPosition;
    }
}
