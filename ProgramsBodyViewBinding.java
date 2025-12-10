package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class ProgramsBodyViewBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final ViewPager2 viewPager;

    private ProgramsBodyViewBinding(FrameLayout frameLayout, ViewPager2 viewPager2) {
        this.rootView = frameLayout;
        this.viewPager = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ProgramsBodyViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ProgramsBodyViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.programs_body_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ProgramsBodyViewBinding bind(View view) {
        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.view_pager);
        if (viewPager2 != null) {
            return new ProgramsBodyViewBinding((FrameLayout) view, viewPager2);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.view_pager)));
    }
}
