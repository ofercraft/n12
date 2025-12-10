package com.channel2.mobile.ui.explore.views;

import android.content.res.Resources;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.explore.models.SearchResultsItem;

/* loaded from: classes2.dex */
public class SearchPlaceholder extends SearchRecyclerViewHolder {
    private LinearLayout rootView;

    public SearchPlaceholder(View view) {
        super(view);
        this.rootView = (LinearLayout) view.findViewById(R.id.rootView);
    }

    @Override // com.channel2.mobile.ui.explore.views.SearchRecyclerViewHolder
    public void initial(SearchResultsItem searchResultsItem) throws Resources.NotFoundException {
        if (this.rootView != null) {
            for (int i = 0; i < this.rootView.getChildCount(); i++) {
                this.rootView.getChildAt(i).startAnimation(AnimationUtils.loadAnimation(this.rootView.getContext(), R.anim.alpha));
            }
        }
    }
}
