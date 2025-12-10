package com.channel2.mobile.ui.lobby.models.sections;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatViewModel;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.ItemManager;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class CustomLobbyChatAdapter extends RecyclerView.Adapter<LobbyChatViewModel> {
    private Context context;
    private LobbyFragmentHandler handler;
    private ItemManager itemManager = new ItemManager();
    private ArrayList<ChatReportItem> items;
    private int topicColor;
    private int topicId;

    public CustomLobbyChatAdapter(Context context, ArrayList<ChatReportItem> arrayList, int i, int i2, LobbyFragmentHandler lobbyFragmentHandler) {
        this.context = context;
        this.items = arrayList;
        this.handler = lobbyFragmentHandler;
        this.topicId = i;
        this.topicColor = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public LobbyChatViewModel onCreateViewHolder(ViewGroup viewGroup, int i) {
        return (LobbyChatViewModel) this.itemManager.getLobbyItemView(i, viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(LobbyChatViewModel lobbyChatViewModel, final int i) {
        lobbyChatViewModel.setIsRecyclable(false);
        lobbyChatViewModel.setTopicColor(this.topicColor);
        lobbyChatViewModel.setViewResources(this.items.get(i), this.handler);
        lobbyChatViewModel.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.models.sections.CustomLobbyChatAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatReportItem chatReportItem = (ChatReportItem) CustomLobbyChatAdapter.this.items.get(i);
                Bundle bundle = new Bundle();
                bundle.putString("partner", "Message");
                bundle.putLong("messageId", chatReportItem.getMessageID());
                CustomLobbyChatAdapter.this.handler.onClick((Item) CustomLobbyChatAdapter.this.items.get(i), bundle);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.itemManager.getChatItemViewType(this.items.get(i).getChatItemType(), false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public void customNotifyDataSetChanged(ArrayList<ChatReportItem> arrayList) {
        this.items = arrayList;
        super.notifyDataSetChanged();
    }
}
