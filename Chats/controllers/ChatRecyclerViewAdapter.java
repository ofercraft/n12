package com.channel2.mobile.ui.Chats.controllers;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.Chats.Helpers.StickHeaderItemDecoration;
import com.channel2.mobile.ui.Chats.models.ChatItemType;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.Chats.models.ChatTopic;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.ItemManager;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewHolder> implements StickHeaderItemDecoration.StickyHeaderInterface {
    private ArrayList<ChatReportItem> chatItems;
    private LobbyFragmentHandler handler;
    private ItemManager itemManager;
    ChatTopic topic;

    @Override // com.channel2.mobile.ui.Chats.Helpers.StickHeaderItemDecoration.StickyHeaderInterface
    public int getHeaderLayout(int i) {
        return R.layout.chat_section_header;
    }

    public ChatRecyclerViewAdapter(ArrayList<ChatReportItem> arrayList, ItemManager itemManager, ChatTopic chatTopic, LobbyFragmentHandler lobbyFragmentHandler) {
        this.chatItems = arrayList;
        this.itemManager = itemManager;
        this.handler = lobbyFragmentHandler;
        this.topic = chatTopic;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CustomRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.itemManager.getLobbyItemView(i, viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(CustomRecyclerViewHolder customRecyclerViewHolder, final int i) {
        customRecyclerViewHolder.setViewResources(this.chatItems.get(i), this.handler);
        if (customRecyclerViewHolder instanceof ChatTabViewModel) {
            final ChatTabViewModel chatTabViewModel = (ChatTabViewModel) customRecyclerViewHolder;
            chatTabViewModel.init(this.topic);
            if (chatTabViewModel.readMore != null) {
                chatTabViewModel.readMore.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.Chats.controllers.ChatRecyclerViewAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        chatTabViewModel.readMore();
                        ChatRecyclerViewAdapter.this.handler.readMore(i);
                    }
                });
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.chatItems.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        ChatReportItem chatReportItem = this.chatItems.get(i);
        return chatReportItem.getChatItemTempType() == ChatItemType.typing ? this.itemManager.getChatItemViewType(ChatItemType.typing, true) : this.itemManager.getChatItemViewType(chatReportItem.getChatItemType(), true);
    }

    public void setChatItems(ArrayList<ChatReportItem> arrayList) {
        this.chatItems = arrayList;
    }

    @Override // com.channel2.mobile.ui.Chats.Helpers.StickHeaderItemDecoration.StickyHeaderInterface
    public int getHeaderPositionForItem(int i) {
        while (!isHeader(i)) {
            i--;
            if (i < 0) {
                return 0;
            }
        }
        return i;
    }

    @Override // com.channel2.mobile.ui.Chats.Helpers.StickHeaderItemDecoration.StickyHeaderInterface
    public void bindHeaderData(View view, int i) {
        String dateString;
        Log.d("ChatRecyclerViewAdapter", "headerPosition" + i);
        if (this.chatItems.get(i).getChatItemType() == ChatItemType.sectionHeader) {
            dateString = this.chatItems.get(i).getMessageContent();
        } else {
            dateString = ChatManager.getInstance().getDateString(this.chatItems.get(i).getUpdatedDate());
        }
        ((TextView) view.findViewById(R.id.headerSectionText)).setText(dateString);
    }

    @Override // com.channel2.mobile.ui.Chats.Helpers.StickHeaderItemDecoration.StickyHeaderInterface
    public boolean isHeader(int i) {
        return this.chatItems.get(i).getChatItemType() == ChatItemType.sectionHeader;
    }
}
