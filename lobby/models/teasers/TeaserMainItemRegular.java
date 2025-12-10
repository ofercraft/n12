package com.channel2.mobile.ui.lobby.models.teasers;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class TeaserMainItemRegular extends CustomRecyclerViewHolder {
    protected TextView author;
    protected LinearLayout authorAndDate;
    protected TextView caption;
    protected TextView date;
    public ImageView image;
    protected TextView subtitle;
    protected TextView textDevider;
    protected TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserMainItemRegular(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.subtitle = (TextView) view.findViewById(R.id.subtitle);
        this.caption = (TextView) view.findViewById(R.id.caption);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.date = (TextView) view.findViewById(R.id.date);
        this.author = (TextView) view.findViewById(R.id.author);
        this.textDevider = (TextView) view.findViewById(R.id.text_devider);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        if (this.caption != null) {
            if (lobbyTeaser.getCaption().length() > 0) {
                this.caption.setText(lobbyTeaser.getCaption());
                setFontSize(this.caption, 15.0f);
                this.caption.setVisibility(0);
            } else {
                this.caption.setVisibility(8);
            }
        }
        this.title.setText(lobbyTeaser.getTitle());
        setFontSize(this.title, 27.0f);
        this.subtitle.setText(lobbyTeaser.getSubTitle());
        setFontSize(this.subtitle, 15.0f);
        if (lobbyTeaser.getDate().length() == 0 || lobbyTeaser.getAuthor().length() == 0) {
            this.textDevider.setVisibility(8);
        }
        this.date.setText(lobbyTeaser.getDate());
        setFontSize(this.date, 12.0f);
        this.author.setText(lobbyTeaser.getAuthor());
        setFontSize(this.author, 12.0f);
        Glide.with(this.image).load(lobbyTeaser.getImage()).into(this.image);
    }
}
