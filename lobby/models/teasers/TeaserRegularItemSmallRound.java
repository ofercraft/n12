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
public class TeaserRegularItemSmallRound extends CustomRecyclerViewHolder {
    private TextView author;
    private TextView caption;
    private View divider;
    private ImageView image;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserRegularItemSmallRound(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.caption = (TextView) view.findViewById(R.id.caption);
        this.author = (TextView) view.findViewById(R.id.author);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.divider = view.findViewById(R.id.divider);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        this.title.setText(lobbyTeaser.getTitle());
        setFontSize(this.title, 20.0f);
        this.author.setText(lobbyTeaser.getAuthor());
        setFontSize(this.author, 14.0f);
        if (lobbyTeaser.getImage().length() > 0) {
            Picasso.get().load(lobbyTeaser.getOriginalimage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_dound))).transform(new CircleTransform()).into(this.image);
        }
        if (lobbyTeaser.getCaption() != null && lobbyTeaser.getCaption().length() > 0) {
            this.caption.setText(lobbyTeaser.getCaption());
        } else {
            this.caption.setVisibility(8);
        }
        setFontSize(this.caption, 14.0f);
        if (lobbyTeaser.isDisplayDivider()) {
            this.divider.setVisibility(0);
        } else {
            this.divider.setVisibility(8);
        }
    }
}
