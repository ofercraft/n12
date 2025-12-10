package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class DividerTopRegularWhiteBinding implements ViewBinding {
    private final ConstraintLayout rootView;

    private DividerTopRegularWhiteBinding(ConstraintLayout constraintLayout) {
        this.rootView = constraintLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DividerTopRegularWhiteBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DividerTopRegularWhiteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.divider_top_regular_white, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DividerTopRegularWhiteBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new DividerTopRegularWhiteBinding((ConstraintLayout) view);
    }
}
