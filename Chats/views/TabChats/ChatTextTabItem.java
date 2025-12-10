package com.channel2.mobile.ui.Chats.views.TabChats;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class ChatTextTabItem extends ChatTabViewModel {
    private ChatTabViewModel chatTabViewModel;
    private TextView content;
    public Context context;
    private Button readMore;
    private TextView reporter;

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public ChatTextTabItem(View view) {
        super(view);
        this.reporter = (TextView) view.findViewById(R.id.reporter);
        this.content = (TextView) view.findViewById(R.id.content);
        this.context = view.getContext();
        this.readMore = (Button) view.findViewById(R.id.readMore);
        this.context = view.getContext();
    }

    @Override // com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        handleContent(this.content, ((ChatReportItem) item).getMessageContent(), this.readMore);
    }
}
