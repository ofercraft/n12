package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class TeaserMainItemMedium extends CustomRecyclerViewHolder {
    protected TextView author;
    protected TextView caption;
    protected TextView date;
    public ImageView image;
    protected FrameLayout imageContainer;
    protected TextView subtitle;
    protected ViewGroup teaser_main_item_medium_text_box;
    protected ImageView textBackground;
    protected TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserMainItemMedium(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.subtitle = (TextView) view.findViewById(R.id.subtitle);
        this.caption = (TextView) view.findViewById(R.id.caption);
        this.date = (TextView) view.findViewById(R.id.date);
        this.author = (TextView) view.findViewById(R.id.author);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.teaser_main_item_medium_text_box = (ViewGroup) view.findViewById(R.id.teaser_main_item_medium_text_box);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        if (lobbyTeaser.getNTheme() != null) {
            Glide.with(this.itemView).load(lobbyTeaser.getNTheme().getGradientBackground()).dontAnimate().into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemMedium.1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                }

                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    TeaserMainItemMedium.this.teaser_main_item_medium_text_box.setBackground(drawable);
                }
            });
            TextView textView = this.caption;
            if (textView != null) {
                textView.setBackgroundColor(Color.parseColor(lobbyTeaser.getNTheme().getCaptionColor()));
            }
        } else {
            ViewGroup viewGroup = this.teaser_main_item_medium_text_box;
            viewGroup.setBackground(ContextCompat.getDrawable(viewGroup.getContext(), R.drawable.n12_bg_gradient_blue_reverse));
        }
        Glide.with(this.image).load(lobbyTeaser.getImage()).into(this.image);
        this.title.setText(lobbyTeaser.getTitle());
        setFontSize(this.title, 27.0f);
        this.subtitle.setText(lobbyTeaser.getSubTitle());
        setFontSize(this.subtitle, 15.0f);
        if (this.caption != null) {
            if (lobbyTeaser.getCaption() != null && lobbyTeaser.getCaption().length() > 1) {
                this.caption.setText(lobbyTeaser.getCaption());
                setFontSize(this.caption, 15.0f);
            } else {
                this.caption.setVisibility(8);
            }
        }
        this.author.setText(lobbyTeaser.getAuthor());
        setFontSize(this.author, 12.0f);
        this.date.setText(lobbyTeaser.getDate());
        setFontSize(this.date, 13.0f);
    }
}
