package com.channel2.mobile.ui.lobby.models.ads;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.ads.LobbyAd;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class AdBanner extends LobbyAdViewHolder {
    private FrameLayout adContainer;
    private ConstraintLayout body;
    private ConstraintLayout container;
    private TextView title;

    public AdBanner(View view) {
        super(view);
        this.body = (ConstraintLayout) view.findViewById(R.id.body);
        this.adContainer = (FrameLayout) view.findViewById(R.id.adContainer);
        this.title = (TextView) view.findViewById(R.id.title);
        this.container = (ConstraintLayout) view.findViewById(R.id.container);
    }

    @Override // com.channel2.mobile.ui.lobby.models.ads.LobbyAdViewHolder, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyAd lobbyAd = (LobbyAd) item;
        FrameLayout frameLayout = (FrameLayout) lobbyAd.getAdView().getParent();
        if (frameLayout != null) {
            frameLayout.removeView(lobbyAd.getAdView());
        }
        this.adContainer.removeAllViews();
        this.container.getLayoutParams();
        this.adContainer.getLayoutParams();
        if (lobbyAd.getAdStatus() == LobbyAd.AdStatus.AdFailed) {
            this.container.getLayoutParams().height = 0;
            this.body.setVisibility(8);
            this.title.setVisibility(8);
        } else {
            this.container.getLayoutParams().height = -2;
            this.body.setVisibility(0);
            this.title.setVisibility(0);
            this.adContainer.addView(lobbyAd.getAdView());
        }
        this.title.setText("פרסומת");
        setFontSize(this.title, 12.0f);
        lobbyAd.getAdView().setAlpha(1.0f);
    }
}
