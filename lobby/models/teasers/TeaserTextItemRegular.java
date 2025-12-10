package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;

/* loaded from: classes2.dex */
public class TeaserTextItemRegular extends CustomRecyclerViewHolder {
    private TextView authorAndDate;
    private View divider;
    private ConstraintLayout rootView;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserTextItemRegular(View view) {
        super(view);
        this.rootView = (ConstraintLayout) view.findViewById(R.id.rootView);
        this.title = (TextView) view.findViewById(R.id.title);
        this.authorAndDate = (TextView) view.findViewById(R.id.authorAndDate);
        this.divider = view.findViewById(R.id.divider);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        String str;
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        this.title.setText(lobbyTeaser.getTitle());
        setFontSize(this.title, 20.0f);
        if (lobbyTeaser.getDate().length() == 0 || lobbyTeaser.getAuthor().length() == 0) {
            str = "";
        } else {
            str = " | ";
            if (lobbyTeaser.getAuthor().length() > 28 && lobbyTeaser.getDate().length() > 1) {
                str = " | " + System.getProperty("line.separator");
            }
        }
        if (lobbyTeaser.getAuthor().length() > 0 && lobbyTeaser.getAuthor().substring(0, 1).matches("[a-zA-Z]")) {
            this.authorAndDate.setText(lobbyTeaser.getDate() + str + lobbyTeaser.getAuthor());
            setFontSize(this.authorAndDate, 12.0f);
            this.authorAndDate.setTextDirection(3);
            this.authorAndDate.setGravity(5);
        } else {
            this.authorAndDate.setText(lobbyTeaser.getAuthor() + str + lobbyTeaser.getDate());
        }
        if (lobbyTeaser.getDate().length() == 0 && lobbyTeaser.getAuthor().length() == 0) {
            this.authorAndDate.setVisibility(8);
        }
        if (lobbyTeaser.isDisplayDivider()) {
            this.divider.setVisibility(0);
        } else {
            this.divider.setVisibility(8);
        }
        customizeItemColors(lobbyTeaser);
    }

    public void customizeItemColors(LobbyTeaser lobbyTeaser) {
        if (!lobbyTeaser.getTeaserTextColor().isEmpty()) {
            this.title.setTextColor(Color.parseColor(lobbyTeaser.getTeaserTextColor()));
            this.authorAndDate.setTextColor(Color.parseColor(lobbyTeaser.getTeaserTextColor()));
        } else {
            TextView textView = this.title;
            textView.setTextColor(ContextCompat.getColor(textView.getContext(), R.color.black0C0C0C));
            TextView textView2 = this.authorAndDate;
            textView2.setTextColor(ContextCompat.getColor(textView2.getContext(), R.color.grey6C6E70));
        }
        if (!lobbyTeaser.getTeaserBackgroundColor().isEmpty()) {
            setGradientBackground(lobbyTeaser, this.rootView);
        } else {
            this.rootView.setBackgroundColor(-1);
        }
    }
}
