package com.channel2.mobile.ui.lobby.models.sections;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.helpers.ChromeCustomTabsApi;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.squareup.picasso.Picasso;
import java.util.Objects;

/* loaded from: classes2.dex */
public class SectionVerticalParshanim extends CustomRecyclerViewHolder {
    private View backgroundImageView;
    private ImageView componentIcon;
    private Group container;
    private View itemView;
    private TextView outbrainViewability;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public SectionVerticalParshanim(View view) {
        super(view);
        this.itemView = view;
        this.title = (TextView) view.findViewById(R.id.title);
        this.container = (Group) view.findViewById(R.id.container);
        this.componentIcon = (ImageView) view.findViewById(R.id.componentIcon);
        this.outbrainViewability = (TextView) view.findViewById(R.id.outbrainViewability);
        this.backgroundImageView = view.findViewById(R.id.backgroundImageView);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        final LobbySection lobbySection = (LobbySection) item;
        if (lobbySection.getName().length() > 0) {
            this.title.setText(lobbySection.getName());
            setFontSize(this.title, 27.0f);
            if (lobbySection.getBackgroundColor().length() > 0) {
                this.title.setTextColor(Color.parseColor(lobbySection.getBackgroundColor()));
            }
            this.container.setVisibility(0);
        } else {
            this.container.getLayoutParams();
            this.container.setVisibility(8);
        }
        if (lobbySection.getComponentIcon().length() > 0) {
            Picasso.get().load(lobbySection.getComponentIcon()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.componentIcon.getContext(), R.drawable.placeholder_dound))).into(this.componentIcon);
            this.componentIcon.setAlpha(1.0f);
            this.componentIcon.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.models.sections.SectionVerticalParshanim.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    new ChromeCustomTabsApi().openLinkInChromeCustomTabs(SectionVerticalParshanim.this.componentIcon.getContext(), lobbySection.getIconClickUrl());
                }
            });
            return;
        }
        this.componentIcon.setAlpha(0.0f);
    }
}
