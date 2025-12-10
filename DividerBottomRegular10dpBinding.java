package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class DividerBottomRegular10dpBinding implements ViewBinding {
    private final ConstraintLayout rootView;

    private DividerBottomRegular10dpBinding(ConstraintLayout constraintLayout) {
        this.rootView = constraintLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DividerBottomRegular10dpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DividerBottomRegular10dpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.divider_bottom_regular_10dp, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DividerBottomRegular10dpBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new DividerBottomRegular10dpBinding((ConstraintLayout) view);
    }
}
