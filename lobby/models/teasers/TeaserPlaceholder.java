package com.channel2.mobile.ui.lobby.models.teasers;

import android.content.res.Resources;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class TeaserPlaceholder extends CustomRecyclerViewHolder {
    private LinearLayout rootView;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserPlaceholder(View view) {
        super(view);
        this.rootView = (LinearLayout) view.findViewById(R.id.rootView);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) throws Resources.NotFoundException {
        super.setViewResources(item, lobbyFragmentHandler);
        if (this.rootView != null) {
            for (int i = 0; i < this.rootView.getChildCount(); i++) {
                this.rootView.getChildAt(i).startAnimation(AnimationUtils.loadAnimation(this.rootView.getContext(), R.anim.alpha));
            }
        }
    }
}
