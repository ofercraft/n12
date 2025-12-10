package com.channel2.mobile.ui.lobby.models.teasers;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class TeaserRegularItemBigLobby extends CustomRecyclerViewHolder {
    private TextView author;
    private TextView date;
    private ImageView image;
    private TextView subtitle;
    private TextView textDevider;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserRegularItemBigLobby(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        TextView textView = (TextView) view.findViewById(R.id.subtitle);
        this.subtitle = textView;
        textView.setMaxLines(3);
        this.date = (TextView) view.findViewById(R.id.date);
        this.author = (TextView) view.findViewById(R.id.author);
        this.image = (ImageView) view.findViewById(R.id.image);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        this.title.setText(lobbyTeaser.getTitle());
        setFontSize(this.title, 27.0f);
        this.subtitle.setText(lobbyTeaser.getSubTitle());
        setFontSize(this.subtitle, 15.0f);
        this.date.setText(lobbyTeaser.getDate());
        setFontSize(this.date, 12.0f);
        this.author.setText(lobbyTeaser.getAuthor());
        setFontSize(this.author, 12.0f);
        this.textDevider = (TextView) this.itemView.findViewById(R.id.text_devider);
        if (lobbyTeaser.getDate().length() == 0 || lobbyTeaser.getAuthor().length() == 0) {
            this.textDevider.setVisibility(8);
        }
        if (lobbyTeaser.getImage().length() > 0) {
            Glide.with(this.image).load(lobbyTeaser.getImage()).into(this.image);
        } else {
            ImageView imageView = this.image;
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.placeholder_ir));
        }
    }
}
