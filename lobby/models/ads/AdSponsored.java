package com.channel2.mobile.ui.lobby.models.ads;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import java.util.Objects;

/* loaded from: classes2.dex */
public class AdSponsored extends LobbyAdViewHolder {
    private View background;
    private View divider;
    private TextView flach;
    private ImageView image;
    private TextView title;

    public AdSponsored(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.flach = (TextView) view.findViewById(R.id.flach);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.divider = view.findViewById(R.id.divider);
        this.background = view.findViewById(R.id.background);
    }

    @Override // com.channel2.mobile.ui.lobby.models.ads.LobbyAdViewHolder, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyAd lobbyAd = (LobbyAd) item;
        if (lobbyAd.isSponsoredAdReady()) {
            this.divider.setVisibility(0);
            this.background.setVisibility(0);
            if (lobbyAd.getTitle() != null && lobbyAd.getTitle().length() > 0) {
                this.title.setText(lobbyAd.getTitle());
                setFontSize(this.title, 20.0f);
                this.title.setVisibility(0);
            } else {
                this.title.setVisibility(8);
            }
            if (lobbyAd.getFlachText() != null && lobbyAd.getFlachText().length() > 0) {
                this.flach.setText(lobbyAd.getFlachText());
                setFontSize(this.flach, 14.0f);
                this.flach.setVisibility(0);
            } else {
                this.flach.setVisibility(8);
            }
            if (lobbyAd.getImage().length() > 0) {
                this.image.setVisibility(0);
                Glide.with(this.image).load(lobbyAd.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_ir))).into(this.image);
            } else {
                this.image.setVisibility(8);
            }
            this.itemView.setVisibility(0);
        }
    }
}
