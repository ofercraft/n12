package com.channel2.mobile.ui.explore.views;

import android.view.View;
import android.widget.TextView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.explore.models.Channel;
import com.channel2.mobile.ui.helpers.Utils;

/* loaded from: classes2.dex */
public class RecommendedViewHolder extends RecommendedRecyclerViewHolder {
    private TextView title;

    public RecommendedViewHolder(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
    }

    @Override // com.channel2.mobile.ui.explore.views.RecommendedRecyclerViewHolder
    public void initial(Channel channel) {
        this.title.setText(channel.getDisplayName());
        TextView textView = this.title;
        textView.setTextSize(2, Utils.getFloatFromPreferences(textView.getContext(), "zoom").floatValue() * 20.0f);
    }
}
