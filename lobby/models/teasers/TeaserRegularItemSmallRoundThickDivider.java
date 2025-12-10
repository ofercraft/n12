package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.helpers.CircleTransform;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.squareup.picasso.Picasso;
import java.util.Objects;

/* loaded from: classes2.dex */
public class TeaserRegularItemSmallRoundThickDivider extends CustomRecyclerViewHolder {
    private TextView author;
    private ImageView image;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserRegularItemSmallRoundThickDivider(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.author = (TextView) view.findViewById(R.id.author);
        this.image = (ImageView) view.findViewById(R.id.image);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        this.title.setText(lobbyTeaser.getTitle());
        setFontSize(this.title, 17.0f);
        this.author.setText(lobbyTeaser.getAuthor());
        setFontSize(this.author, 14.0f);
        if (lobbyTeaser.getImage().length() > 0) {
            Picasso.get().load(lobbyTeaser.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_dound))).transform(new CircleTransform()).into(this.image);
        }
    }
}
