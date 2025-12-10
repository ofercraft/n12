package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import java.util.Objects;

/* loaded from: classes2.dex */
public class TeaserRegularItemSmall extends CustomRecyclerViewHolder {
    private TextView authorAndDate;
    private TextView caption;
    private View divider;
    private ImageView image;
    private ImageView outbrain_rec_disclosure_image_view;
    private ConstraintLayout rootView;
    private TextView subtitle;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserRegularItemSmall(View view) {
        super(view);
        this.rootView = (ConstraintLayout) view.findViewById(R.id.rootView);
        this.title = (TextView) view.findViewById(R.id.title);
        this.subtitle = (TextView) view.findViewById(R.id.subtitle);
        this.caption = (TextView) view.findViewById(R.id.caption);
        this.authorAndDate = (TextView) view.findViewById(R.id.authorAndDate);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.divider = view.findViewById(R.id.divider);
        this.outbrain_rec_disclosure_image_view = (ImageView) view.findViewById(R.id.outbrain_rec_disclosure_image_view);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        this.title.setText(lobbyTeaser.getTitle());
        setFontSize(this.title, 20.0f);
        if (lobbyTeaser.getDate().length() == 0 && lobbyTeaser.getAuthor().length() == 0) {
            this.authorAndDate.setVisibility(8);
        } else {
            String str = (lobbyTeaser.getDate().length() == 0 || lobbyTeaser.getAuthor().length() == 0) ? "" : " | ";
            String[] strArrSplit = lobbyTeaser.getAuthor().split(", ");
            if (strArrSplit.length == 0) {
                strArrSplit = lobbyTeaser.getAuthor().split(",");
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strArrSplit.length; i++) {
                sb.append("\u200f");
                sb.append(strArrSplit[i]);
                if (i < strArrSplit.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("\u200f");
            sb.append(str);
            sb.append("\u200f");
            sb.append(lobbyTeaser.getDate());
            this.authorAndDate.setText(sb);
            this.authorAndDate.setTextDirection(4);
            setFontSize(this.authorAndDate, 12.0f);
            this.authorAndDate.setVisibility(0);
        }
        if (lobbyTeaser.getImage().length() > 0) {
            this.image.setVisibility(0);
            Glide.with(this.image).load(lobbyTeaser.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_ir))).into(this.image);
        } else {
            this.image.setVisibility(8);
        }
        this.image.requestLayout();
        if (lobbyTeaser.getSubTitle().length() > 0) {
            this.subtitle.setText(lobbyTeaser.getSubTitle());
            setFontSize(this.subtitle, 15.0f);
            this.subtitle.setVisibility(0);
        } else {
            this.subtitle.setVisibility(8);
        }
        if (lobbyTeaser.getCaption().length() > 0) {
            this.caption.setText(lobbyTeaser.getCaption());
            setFontSize(this.caption, 14.0f);
            this.caption.setVisibility(0);
        } else {
            this.caption.setVisibility(8);
        }
        if (lobbyTeaser.isDisplayDivider()) {
            this.divider.setVisibility(0);
        } else {
            this.divider.setVisibility(8);
        }
        customizeItemColors(lobbyTeaser);
        if (lobbyTeaser.getOutbrainRec() != null && lobbyTeaser.getOutbrainRec().isPaid() && lobbyTeaser.getOutbrainRec().shouldDisplayDisclosureIcon()) {
            Glide.with(this.outbrain_rec_disclosure_image_view).load(lobbyTeaser.getOutbrainRec().getDisclosure().getIconUrl()).into(this.outbrain_rec_disclosure_image_view);
        }
    }

    public void clearImageView() {
        try {
            Glide.with(this.image.getContext()).clear(this.image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customizeItemColors(LobbyTeaser lobbyTeaser) {
        if (!lobbyTeaser.getCaptionTextColor().isEmpty()) {
            this.caption.setTextColor(Color.parseColor(lobbyTeaser.getCaptionTextColor()));
        } else {
            TextView textView = this.caption;
            textView.setTextColor(ContextCompat.getColor(textView.getContext(), R.color.colorPrimaryDark));
        }
        if (!lobbyTeaser.getCaptionBackgroundColor().isEmpty()) {
            int i = (int) (4 * this.caption.getContext().getResources().getDisplayMetrics().density);
            this.caption.setPadding(i, i, i, i);
            this.caption.setBackgroundColor(Color.parseColor(lobbyTeaser.getCaptionBackgroundColor()));
        } else {
            this.caption.setBackgroundColor(0);
        }
        if (!lobbyTeaser.getTeaserTextColor().isEmpty()) {
            this.title.setTextColor(Color.parseColor(lobbyTeaser.getTeaserTextColor()));
            this.subtitle.setTextColor(Color.parseColor(lobbyTeaser.getTeaserTextColor()));
            this.authorAndDate.setTextColor(Color.parseColor(lobbyTeaser.getTeaserTextColor()));
        } else {
            this.title.setTextColor(ContextCompat.getColor(this.caption.getContext(), R.color.black0C0C0C));
            this.subtitle.setTextColor(ContextCompat.getColor(this.caption.getContext(), R.color.black0C0C0C));
            this.authorAndDate.setTextColor(ContextCompat.getColor(this.caption.getContext(), R.color.grey6C6E70));
        }
        if (!lobbyTeaser.getTeaserBackgroundColor().isEmpty()) {
            setGradientBackground(lobbyTeaser, this.rootView);
        } else {
            this.rootView.setBackgroundColor(-1);
        }
    }
}
