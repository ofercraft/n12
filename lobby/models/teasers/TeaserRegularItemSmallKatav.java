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
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TeaserRegularItemSmallKatav extends CustomRecyclerViewHolder {
    private ImageView image;
    private ImageView linkA;
    private ImageView linkB;
    private CustomTextView subtitle;
    private CustomTextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserRegularItemSmallKatav(View view) {
        super(view);
        this.title = (CustomTextView) view.findViewById(R.id.title);
        this.subtitle = (CustomTextView) view.findViewById(R.id.subtitle);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.linkA = (ImageView) view.findViewById(R.id.linkA);
        this.linkB = (ImageView) view.findViewById(R.id.linkB);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        CustomTextView customTextView = this.title;
        String title = lobbyTeaser.getTitle();
        Objects.requireNonNull(this.title);
        customTextView.setHebText(title, "fonts/YonitMedium_v2.ttf");
        setFontSize(this.title, 20.0f);
        if (lobbyTeaser.getImage().length() > 0) {
            Picasso.get().load(lobbyTeaser.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_ir))).into(this.image);
        }
        CustomTextView customTextView2 = this.subtitle;
        String subTitle = lobbyTeaser.getSubTitle();
        Objects.requireNonNull(this.title);
        customTextView2.setHebText(subTitle, "fonts/YonitLight_v2.ttf");
        setFontSize(this.subtitle, 17.0f);
        if (lobbyTeaser.getLinks() == null || lobbyTeaser.getLinks().length() <= 0) {
            return;
        }
        JSONObject jSONObjectOptJSONObject = lobbyTeaser.getLinks().optJSONObject(0);
        if (jSONObjectOptJSONObject.optString("image").length() > 0) {
            Picasso.get().load(jSONObjectOptJSONObject.optString("image")).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.linkA.getContext(), R.drawable.placeholder_dound))).into(this.linkA);
        }
        if (lobbyTeaser.getLinks().length() > 1) {
            JSONObject jSONObjectOptJSONObject2 = lobbyTeaser.getLinks().optJSONObject(1);
            if (jSONObjectOptJSONObject2.optString("image").length() > 0) {
                Picasso.get().load(jSONObjectOptJSONObject2.optString("image")).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.linkB.getContext(), R.drawable.placeholder_dound))).into(this.linkB);
            }
        }
    }
}
