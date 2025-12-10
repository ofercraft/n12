package com.channel2.mobile.ui.lobby.controllers;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.Chats.controllers.ChatManager;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.models.ads.LobbyAdViewHolder;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.ItemManager;
import com.channel2.mobile.ui.lobby.models.sections.SectionChatReportLobby;
import com.channel2.mobile.ui.lobby.models.teasers.LobbyTeaser;
import com.channel2.mobile.ui.lobby.models.teasers.ObituariesTeaserItem;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserIframe;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemMediumVideo;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemRegularVideo;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemSpecialVideo;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class CustomRecyclerViewAdapter extends ListAdapter<Item, RecyclerView.ViewHolder> {
    private static final DiffCallBack diff = new DiffCallBack();
    private String TAG;
    private LobbyFragmentHandler handler;
    private ItemManager itemManager;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder instanceof ObituariesTeaserItem) {
            ((ObituariesTeaserItem) viewHolder).setTimer();
        }
        this.handler.onViewAttachedToWindow(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        if (viewHolder instanceof ObituariesTeaserItem) {
            ((ObituariesTeaserItem) viewHolder).stopTimer();
        }
        this.handler.onViewDetachedFromWindow(viewHolder);
    }

    public CustomRecyclerViewAdapter(ItemManager itemManager, LobbyFragmentHandler lobbyFragmentHandler) {
        super(diff);
        this.TAG = "CustomRecyclerViewAdapter";
        this.itemManager = itemManager;
        this.handler = lobbyFragmentHandler;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return getCurrentList().get(i).getItemId();
    }

    @Override // androidx.recyclerview.widget.ListAdapter
    public void onCurrentListChanged(List<Item> list, List<Item> list2) {
        super.onCurrentListChanged(list, list2);
        Log.d("refreshOB", "onCurrentListChanged - previousList = " + list.size() + " | currentList = " + list2.size());
        if (list.size() > 1) {
            this.handler.onListRefreshed();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.i("viewType", "" + i);
        CustomRecyclerViewHolder lobbyItemView = this.itemManager.getLobbyItemView(i, viewGroup);
        if ((lobbyItemView instanceof TeaserIframe) || (lobbyItemView instanceof LobbyAdViewHolder) || (lobbyItemView instanceof SectionChatReportLobby)) {
            lobbyItemView.setIsRecyclable(false);
        }
        return lobbyItemView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        final CustomRecyclerViewHolder customRecyclerViewHolder = (CustomRecyclerViewHolder) viewHolder;
        customRecyclerViewHolder.setViewResources(getCurrentList().get(i), this.handler);
        if (customRecyclerViewHolder instanceof SectionChatReportLobby) {
            ((SectionChatReportLobby) customRecyclerViewHolder).init(ChatManager.getInstance().chatItemsArray);
        }
        customRecyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0(i, customRecyclerViewHolder, view);
            }
        });
        Log.i(this.TAG, "bindView:" + getCurrentList().get(i).getLobbyItemType().toString() + StringUtils.SPACE + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(int i, CustomRecyclerViewHolder customRecyclerViewHolder, View view) {
        Item item = getCurrentList().get(i);
        boolean z = customRecyclerViewHolder instanceof TeaserMainItemMediumVideo;
        if (z || (customRecyclerViewHolder instanceof TeaserMainItemRegularVideo) || (customRecyclerViewHolder instanceof TeaserMainItemSpecialVideo)) {
            if (z) {
                ((TeaserMainItemMediumVideo) customRecyclerViewHolder).image.setVisibility(0);
            }
            if (customRecyclerViewHolder instanceof TeaserMainItemRegularVideo) {
                ((TeaserMainItemRegularVideo) customRecyclerViewHolder).image.setVisibility(0);
            }
            if (customRecyclerViewHolder instanceof TeaserMainItemSpecialVideo) {
                ((TeaserMainItemSpecialVideo) customRecyclerViewHolder).image.setVisibility(0);
            }
            item.setMako_ref_comp("slider_Video");
        } else {
            item.setMako_ref_comp("hp_teaser");
        }
        this.handler.onClick(item, null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.itemManager.getItemViewType(getCurrentList().get(i).getLobbyItemType());
    }

    private static class DiffCallBack extends DiffUtil.ItemCallback<Item> {
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(Item item, Item item2) {
            if ((item instanceof LobbyTeaser) && (item2 instanceof LobbyTeaser)) {
                return ((LobbyTeaser) item).getTeaserVcmId().equals(((LobbyTeaser) item2).getTeaserVcmId());
            }
            return false;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(Item item, Item item2) {
            if (!(item instanceof LobbyTeaser) || !(item2 instanceof LobbyTeaser)) {
                return item.isAd() && item2.isAd();
            }
            LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
            if (lobbyTeaser.isOutBrain() && ((LobbyTeaser) item2).isOutBrain()) {
                return true;
            }
            LobbyTeaser lobbyTeaser2 = (LobbyTeaser) item2;
            return lobbyTeaser.getTitle().equals(lobbyTeaser2.getTitle()) && lobbyTeaser.getSubTitle().equals(lobbyTeaser2.getSubTitle());
        }
    }
}
