package com.channel2.mobile.ui.explore.views;

import android.view.View;
import android.widget.TextView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.explore.models.Channel;

/* loaded from: classes2.dex */
public class RecommendedHeaderViewHolder extends RecommendedRecyclerViewHolder {
    private TextView title;

    public RecommendedHeaderViewHolder(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
    }

    @Override // com.channel2.mobile.ui.explore.views.RecommendedRecyclerViewHolder
    public void initial(Channel channel) {
        this.title.setText(channel.getDisplayName());
    }
}
