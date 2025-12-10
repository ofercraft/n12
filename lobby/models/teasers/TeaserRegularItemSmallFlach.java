package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import java.util.Objects;

/* loaded from: classes2.dex */
public class TeaserRegularItemSmallFlach extends CustomRecyclerViewHolder {
    private View divider;
    private TextView flach;
    private ImageView image;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserRegularItemSmallFlach(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.flach = (TextView) view.findViewById(R.id.flach);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.divider = view.findViewById(R.id.divider);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        this.title.setText(lobbyTeaser.getTitle());
        setFontSize(this.title, 20.0f);
        if (lobbyTeaser.getImage().length() > 0) {
            this.image.setVisibility(0);
            Glide.with(this.image).load(lobbyTeaser.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_ir))).into(this.image);
        } else {
            this.image.setVisibility(8);
        }
        this.image.requestLayout();
        if (lobbyTeaser.getFlachText().length() > 0) {
            this.flach.setText(lobbyTeaser.getFlachText());
            setFontSize(this.flach, 14.0f);
            this.flach.setVisibility(0);
        } else {
            this.flach.setVisibility(8);
        }
        if (lobbyTeaser.isDisplayDivider()) {
            this.divider.setVisibility(0);
        } else {
            this.divider.setVisibility(8);
        }
    }
}
