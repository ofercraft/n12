package com.channel2.mobile.ui.explore.views;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.explore.models.SearchResultsItem;
import com.channel2.mobile.ui.helpers.Utils;

/* loaded from: classes2.dex */
public abstract class SearchRecyclerViewHolder extends RecyclerView.ViewHolder {
    public void initial(SearchResultsItem searchResultsItem) {
    }

    public SearchRecyclerViewHolder(View view) {
        super(view);
    }

    protected void setFontSize(TextView textView, float f) {
        textView.setTextSize(2, f * Utils.getFloatFromPreferences(textView.getContext(), "zoom").floatValue());
    }
}
