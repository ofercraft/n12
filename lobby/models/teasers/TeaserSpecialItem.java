package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.squareup.picasso.Picasso;
import java.util.Objects;

/* loaded from: classes2.dex */
public class TeaserSpecialItem extends CustomRecyclerViewHolder {
    private TextView caption;
    public ImageView image;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserSpecialItem(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.caption = (TextView) view.findViewById(R.id.caption);
        this.image = (ImageView) view.findViewById(R.id.image);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(final Item item, final LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        this.caption.setText(lobbyTeaser.getCaption());
        this.title.setText(lobbyTeaser.getTitle());
        if (lobbyTeaser.getImage().length() > 0) {
            Picasso.get().load(lobbyTeaser.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_g))).into(this.image);
        }
        this.image.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.models.teasers.TeaserSpecialItem.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                lobbyFragmentHandler.onClick(item, null);
            }
        });
    }
}
