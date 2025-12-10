package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import java.util.Objects;

/* loaded from: classes2.dex */
public class TeaserRegularItemBig extends CustomRecyclerViewHolder {
    private CustomTextView caption;
    private View divider;
    private CustomTextView flach;
    private ImageView image;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserRegularItemBig(View view) {
        super(view);
        this.caption = (CustomTextView) view.findViewById(R.id.caption);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.divider = view.findViewById(R.id.divider);
        this.flach = (CustomTextView) view.findViewById(R.id.flach);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        if (lobbyTeaser.getTitle().length() > 0) {
            CustomTextView customTextView = this.caption;
            String title = lobbyTeaser.getTitle();
            Objects.requireNonNull(this.caption);
            customTextView.setHebText(title, "fonts/YonitBold_v2.ttf");
            setFontSize(this.caption, 17.0f);
            this.caption.setVisibility(0);
        } else {
            this.caption.setVisibility(8);
        }
        if (lobbyTeaser.getFlachText().length() > 0) {
            CustomTextView customTextView2 = this.flach;
            String flachText = lobbyTeaser.getFlachText();
            Objects.requireNonNull(this.flach);
            customTextView2.setHebText(flachText, "fonts/YonitMedium_v2.ttf");
            setFontSize(this.flach, 14.0f);
            this.flach.setVisibility(0);
        } else {
            this.flach.setVisibility(8);
        }
        Glide.with(this.image).asBitmap().load(lobbyTeaser.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_ir))).into((RequestBuilder) new CustomTarget() { // from class: com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemBig.1
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
            }

            @Override // com.bumptech.glide.request.target.Target
            public void onResourceReady(Object obj, Transition transition) {
                Bitmap bitmap = (Bitmap) obj;
                float width = bitmap.getWidth();
                float height = bitmap.getHeight();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) TeaserRegularItemBig.this.image.getLayoutParams();
                layoutParams.width = Utils.getScreenWidth(TeaserRegularItemBig.this.image.getContext());
                layoutParams.height = (int) (layoutParams.width / (width / height));
                TeaserRegularItemBig.this.image.requestLayout();
                TeaserRegularItemBig.this.image.setImageBitmap(bitmap);
            }
        });
    }
}
