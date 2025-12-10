package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class TeaserPlaceholderBigBinding implements ViewBinding {
    public final LinearLayout rootView;
    private final LinearLayout rootView_;

    private TeaserPlaceholderBigBinding(LinearLayout linearLayout, LinearLayout linearLayout2) {
        this.rootView_ = linearLayout;
        this.rootView = linearLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView_;
    }

    public static TeaserPlaceholderBigBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserPlaceholderBigBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_placeholder_big, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserPlaceholderBigBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        LinearLayout linearLayout = (LinearLayout) view;
        return new TeaserPlaceholderBigBinding(linearLayout, linearLayout);
    }
}
