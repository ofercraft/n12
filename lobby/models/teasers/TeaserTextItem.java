package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class TeaserTextItem extends CustomRecyclerViewHolder {
    private View divider;
    private ConstraintLayout rootView;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserTextItem(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.divider = view.findViewById(R.id.divider);
        this.rootView = (ConstraintLayout) view.findViewById(R.id.rootView);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        customizeItemColors(lobbyTeaser);
        if (lobbyTeaser.getCaption() != null && lobbyTeaser.getCaption().length() > 0) {
            String str = lobbyTeaser.getCaption() + " // ";
            SpannableString spannableString = new SpannableString(str + lobbyTeaser.getTitle());
            int color = Color.parseColor("#AE0000");
            if (!lobbyTeaser.getCaptionTextColor().isEmpty()) {
                color = Color.parseColor(lobbyTeaser.getCaptionTextColor());
            }
            spannableString.setSpan(new ForegroundColorSpan(color), 0, str.length(), 33);
            this.title.setText(spannableString);
        } else {
            this.title.setText(lobbyTeaser.getTitle());
        }
        setFontSize(this.title, 20.0f);
        if (lobbyTeaser.isDisplayDivider()) {
            this.divider.setVisibility(0);
        } else {
            this.divider.setVisibility(8);
        }
    }

    public void customizeItemColors(LobbyTeaser lobbyTeaser) {
        if (!lobbyTeaser.getTeaserTextColor().isEmpty()) {
            this.title.setTextColor(Color.parseColor(lobbyTeaser.getTeaserTextColor()));
        } else {
            TextView textView = this.title;
            textView.setTextColor(ContextCompat.getColor(textView.getContext(), R.color.black0C0C0C));
        }
        if (!lobbyTeaser.getTeaserBackgroundColor().isEmpty()) {
            setGradientBackground(lobbyTeaser, this.rootView);
        } else {
            this.rootView.setBackgroundColor(-1);
        }
    }
}
