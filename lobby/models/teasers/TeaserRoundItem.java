package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import java.util.Objects;

/* loaded from: classes2.dex */
public class TeaserRoundItem extends CustomRecyclerViewHolder {
    private ImageView image;
    private CustomTextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserRoundItem(View view) {
        super(view);
        this.title = (CustomTextView) view.findViewById(R.id.title);
        this.image = (ImageView) view.findViewById(R.id.image);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        CustomTextView customTextView = this.title;
        String title = lobbyTeaser.getTitle();
        Objects.requireNonNull(this.title);
        customTextView.setHebText(title, "fonts/YonitMedium_v2.ttf");
        Glide.with(this.image).load(lobbyTeaser.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_tuhnit))).into(this.image);
    }
}
