package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.squareup.picasso.Picasso;
import java.util.Objects;

/* loaded from: classes2.dex */
public class TeaserRegularItemSmallTagit extends CustomRecyclerViewHolder {
    private CustomTextView author;
    private CustomTextView caption;
    private CustomTextView collaboration;
    private CustomTextView date;
    private ImageView image;
    private CustomTextView subtitle;
    private CustomTextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserRegularItemSmallTagit(View view) {
        super(view);
        this.title = (CustomTextView) view.findViewById(R.id.title);
        this.subtitle = (CustomTextView) view.findViewById(R.id.subtitle);
        this.caption = (CustomTextView) view.findViewById(R.id.caption);
        this.collaboration = (CustomTextView) view.findViewById(R.id.collaboration);
        this.date = (CustomTextView) view.findViewById(R.id.date);
        this.author = (CustomTextView) view.findViewById(R.id.author);
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
        setFontSize(this.title, 17.0f);
        String str = (lobbyTeaser.getDate().length() == 0 || lobbyTeaser.getAuthor().length() == 0) ? "" : " | ";
        CustomTextView customTextView2 = this.date;
        String date = lobbyTeaser.getDate();
        Objects.requireNonNull(this.title);
        customTextView2.setHebText(date, "fonts/OpenSansHebrew-Regular.ttf");
        setFontSize(this.date, 12.0f);
        CustomTextView customTextView3 = this.author;
        String str2 = lobbyTeaser.getAuthor() + str;
        Objects.requireNonNull(this.title);
        customTextView3.setHebText(str2, "fonts/OpenSansHebrew-Regular.ttf");
        setFontSize(this.author, 12.0f);
        if (lobbyTeaser.getImage().length() > 0) {
            Picasso.get().load(lobbyTeaser.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_ir))).into(this.image);
        }
        if (lobbyTeaser.getSubTitle().length() > 0) {
            CustomTextView customTextView4 = this.subtitle;
            String subTitle = lobbyTeaser.getSubTitle();
            Objects.requireNonNull(this.title);
            customTextView4.setHebText(subTitle, "fonts/OpenSansHebrew-Regular.ttf");
            setFontSize(this.subtitle, 15.0f);
            this.subtitle.setVisibility(0);
        } else {
            this.subtitle.setVisibility(8);
        }
        if (lobbyTeaser.getCaption().length() > 0) {
            CustomTextView customTextView5 = this.caption;
            String caption = lobbyTeaser.getCaption();
            Objects.requireNonNull(this.title);
            customTextView5.setHebText(caption, "fonts/YonitMedium_v2.ttf");
            setFontSize(this.caption, 14.0f);
            this.caption.setVisibility(0);
            return;
        }
        if (lobbyTeaser.getFlachText().length() > 0) {
            CustomTextView customTextView6 = this.collaboration;
            String flachText = lobbyTeaser.getFlachText();
            Objects.requireNonNull(this.title);
            customTextView6.setHebText(flachText, "fonts/YonitMedium_v2.ttf");
            setFontSize(this.collaboration, 14.0f);
            this.collaboration.setVisibility(0);
            this.caption.setVisibility(8);
            return;
        }
        this.collaboration.setVisibility(8);
        this.caption.setVisibility(8);
    }
}
