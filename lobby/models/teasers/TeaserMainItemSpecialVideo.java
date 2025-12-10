package com.channel2.mobile.ui.lobby.models.teasers;

import android.view.View;
import android.widget.FrameLayout;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class TeaserMainItemSpecialVideo extends TeaserMainItemSpecial {
    public FrameLayout videoContainer;

    public TeaserMainItemSpecialVideo(View view) {
        super(view);
        this.videoContainer = (FrameLayout) view.findViewById(R.id.videoContainer);
    }

    @Override // com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemSpecial, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        this.image.setVisibility(0);
    }
}
