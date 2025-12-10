package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.facebook.internal.security.CertificateUtil;
import java.util.Objects;

/* loaded from: classes2.dex */
public class TeaserImage extends CustomRecyclerViewHolder {
    private ImageView image;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserImage(View view) {
        super(view);
        this.image = (ImageView) view.findViewById(R.id.image);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        Glide.with(this.image).load(lobbyTeaser.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_g))).into(this.image);
        if (lobbyTeaser.getItemHeight() > 0 && lobbyTeaser.getItemWidth() > 0) {
            String str = lobbyTeaser.getItemWidth() + CertificateUtil.DELIMITER + lobbyTeaser.getItemHeight();
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone((ConstraintLayout) this.itemView);
            constraintSet.setDimensionRatio(this.image.getId(), str);
            constraintSet.applyTo((ConstraintLayout) this.itemView);
        }
        this.image.requestLayout();
    }
}
