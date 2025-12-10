package com.channel2.mobile.ui.lobby.models.teasers;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class TeaserRegularItemBigOverText extends CustomRecyclerViewHolder {
    private TextView author;
    private TextView caption;
    private TextView date;
    private TextView flach;
    private ImageView image;
    private ImageView outbrain_rec_disclosure_image_view;
    private TextView textDevider;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserRegularItemBigOverText(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.date = (TextView) view.findViewById(R.id.date);
        this.author = (TextView) view.findViewById(R.id.author);
        this.textDevider = (TextView) view.findViewById(R.id.text_devider);
        this.caption = (TextView) view.findViewById(R.id.caption);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.outbrain_rec_disclosure_image_view = (ImageView) view.findViewById(R.id.outbrain_rec_disclosure_image_view);
        this.flach = (TextView) view.findViewById(R.id.flach);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        this.title.setText(lobbyTeaser.getTitle());
        setFontSize(this.title, 22.0f);
        if (lobbyTeaser.getDate().length() == 0 || lobbyTeaser.getAuthor().length() == 0) {
            this.textDevider.setVisibility(8);
        } else {
            this.textDevider.setVisibility(0);
        }
        this.date.setText(lobbyTeaser.getDate());
        setFontSize(this.date, 13.0f);
        if (lobbyTeaser.getFlachText().length() > 0) {
            this.flach.setText(lobbyTeaser.getFlachText());
            setFontSize(this.flach, 12.0f);
            this.flach.setVisibility(0);
            this.author.setVisibility(8);
            this.textDevider.setVisibility(8);
            this.date.setVisibility(8);
        } else {
            this.flach.setVisibility(8);
        }
        this.author.setText(lobbyTeaser.getAuthor());
        setFontSize(this.author, 13.0f);
        Glide.with(this.image).load(lobbyTeaser.getImage()).into(this.image);
        if (lobbyTeaser.getOutbrainRec() != null && lobbyTeaser.getOutbrainRec().isPaid() && lobbyTeaser.getOutbrainRec().shouldDisplayDisclosureIcon()) {
            Glide.with(this.outbrain_rec_disclosure_image_view).load(lobbyTeaser.getOutbrainRec().getDisclosure().getIconUrl()).into(this.outbrain_rec_disclosure_image_view);
        }
    }
}
