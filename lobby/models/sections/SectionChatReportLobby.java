package com.channel2.mobile.ui.lobby.models.sections;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.channel2.mobile.ui.Chats.controllers.ChatManager;
import com.channel2.mobile.ui.Chats.models.ChatItemsArray;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.Chats.models.ChatTopic;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragment;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class SectionChatReportLobby extends CustomRecyclerViewHolder implements View.OnClickListener {
    public CustomLobbyChatAdapter adapter;
    private ImageView chatBG;
    private ImageView chatButton;
    private ImageView chatHeaderBG;
    private ImageView chatHeaderLogo;
    private TextView chatHeaderText;
    private Group chat_header;
    private ArrayList<ChatReportItem> items;
    public RecyclerView mRecyclerView;
    private Observer<ChatReportItem> observer;
    private int topicColor;
    public int topicID;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public SectionChatReportLobby(View view) {
        super(view);
        this.topicColor = Color.parseColor("#AE0000");
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.chat_lobby_recycler);
        this.chatButton = (ImageView) view.findViewById(R.id.chat_button);
        this.chat_header = (Group) view.findViewById(R.id.chat_header);
        this.chatHeaderText = (TextView) view.findViewById(R.id.chatHeaderText);
        this.chatHeaderBG = (ImageView) view.findViewById(R.id.chatHeaderBG);
        this.chatHeaderLogo = (ImageView) view.findViewById(R.id.chatHeaderLogo);
        this.chatBG = (ImageView) view.findViewById(R.id.chatBG);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbySection lobbySection = (LobbySection) item;
        this.topicID = lobbySection.getTopicID();
        Iterator<ChatTopic> it = ChatManager.getInstance().chatTopics.iterator();
        while (it.hasNext()) {
            ChatTopic next = it.next();
            if (next.getTopicId() == this.topicID && next.getColor() != null && next.getColor().length() > 0) {
                try {
                    this.topicColor = Color.parseColor("#" + next.getColor());
                } catch (Exception e) {
                    e.printStackTrace();
                    this.topicColor = Color.parseColor("#AE0000");
                }
            }
        }
        if (lobbySection.getHeaderBackground() != null && lobbySection.getHeaderBackground().length() > 0) {
            Glide.with(this.itemView).load(lobbySection.getHeaderBackground()).into(this.chatHeaderBG);
            Glide.with(this.itemView).load(lobbySection.getHeaderBackground()).transform(new CenterCrop(), new RoundedCorners(Utils.convertDipToPixels(this.itemView.getContext(), 8.0f))).into(this.chatButton);
        }
        this.chatHeaderText.setText(lobbySection.getHeaderText());
        if (lobbySection.getBackground() != null && lobbySection.getBackground().length() > 0) {
            Glide.with(this.itemView).load(lobbySection.getBackground()).into(this.chatBG);
        }
        if (lobbySection.getHeaderLogoImage() != null && lobbySection.getHeaderLogoImage().length() > 0) {
            this.chatHeaderLogo.setVisibility(0);
            Glide.with(this.itemView).load(lobbySection.getHeaderLogoImage()).into(this.chatHeaderLogo);
        } else {
            this.chatHeaderLogo.setVisibility(8);
        }
        this.chatButton.setOnClickListener(this);
        for (int i : this.chat_header.getReferencedIds()) {
            this.itemView.findViewById(i).setOnClickListener(this);
        }
    }

    public void init(ChatItemsArray chatItemsArray) {
        if (chatItemsArray != null) {
            try {
                ArrayList<ChatReportItem> chatItems = chatItemsArray.getChatItems(false, this.topicID);
                this.items = chatItems;
                if (chatItems == null || chatItems.size() <= 0) {
                    return;
                }
                this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mRecyclerView.getContext(), 1, true));
                this.mRecyclerView.setRecycledViewPool(LobbyFragment.getViewPool());
                if (this.adapter == null) {
                    CustomLobbyChatAdapter customLobbyChatAdapter = new CustomLobbyChatAdapter(this.mRecyclerView.getContext(), this.items, this.topicID, this.topicColor, this.handler);
                    this.adapter = customLobbyChatAdapter;
                    this.mRecyclerView.setAdapter(customLobbyChatAdapter);
                }
                this.adapter.notifyDataSetChanged();
                if (this.observer == null) {
                    this.observer = new Observer() { // from class: com.channel2.mobile.ui.lobby.models.sections.SectionChatReportLobby$$ExternalSyntheticLambda0
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            this.f$0.lambda$init$0((ChatReportItem) obj);
                        }
                    };
                }
                if (ChatManager.getInstance().chatItemsArray != null) {
                    ChatManager.getInstance().chatItemsArray.getNewItemObservation().observe((LifecycleOwner) this.itemView.getContext(), this.observer);
                }
                this.mRecyclerView.setHasFixedSize(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(ChatReportItem chatReportItem) {
        CustomLobbyChatAdapter customLobbyChatAdapter = this.adapter;
        if (customLobbyChatAdapter != null) {
            customLobbyChatAdapter.notifyDataSetChanged();
        }
    }

    public void notifyAdapter() {
        this.adapter.notifyDataSetChanged();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str = view == this.chatButton ? "All_Messages" : "Header";
        Bundle bundle = new Bundle();
        bundle.putString("partner", str);
        bundle.putInt("goToTopicId", this.topicID);
        this.handler.onClick(new ChatReportItem(), bundle);
    }

    public int getTopicID() {
        return this.topicID;
    }
}
