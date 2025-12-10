package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class TeaserSpacerDividerMediumBinding implements ViewBinding {
    private final FrameLayout rootView;

    private TeaserSpacerDividerMediumBinding(FrameLayout frameLayout) {
        this.rootView = frameLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static TeaserSpacerDividerMediumBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserSpacerDividerMediumBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_spacer_divider_medium, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserSpacerDividerMediumBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new TeaserSpacerDividerMediumBinding((FrameLayout) view);
    }
}
