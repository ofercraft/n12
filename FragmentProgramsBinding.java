package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class FragmentProgramsBinding implements ViewBinding {
    public final CoordinatorLayout container;
    public final FrameLayout leftClick;
    public final FrameLayout placeholder;
    public final ImageView placeholderImage;
    public final FrameLayout programsFragmentsContainer;
    public final FrameLayout rightClick;
    private final CoordinatorLayout rootView;
    public final ViewPager viewPagerBody;
    public final ViewPager viewPagerHeader;

    private FragmentProgramsBinding(CoordinatorLayout coordinatorLayout, CoordinatorLayout coordinatorLayout2, FrameLayout frameLayout, FrameLayout frameLayout2, ImageView imageView, FrameLayout frameLayout3, FrameLayout frameLayout4, ViewPager viewPager, ViewPager viewPager2) {
        this.rootView = coordinatorLayout;
        this.container = coordinatorLayout2;
        this.leftClick = frameLayout;
        this.placeholder = frameLayout2;
        this.placeholderImage = imageView;
        this.programsFragmentsContainer = frameLayout3;
        this.rightClick = frameLayout4;
        this.viewPagerBody = viewPager;
        this.viewPagerHeader = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentProgramsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentProgramsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_programs, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentProgramsBinding bind(View view) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
        int i = R.id.leftClick;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.leftClick);
        if (frameLayout != null) {
            i = R.id.placeholder;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.placeholder);
            if (frameLayout2 != null) {
                i = R.id.placeholderImage;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.placeholderImage);
                if (imageView != null) {
                    i = R.id.programs_fragments_container;
                    FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.programs_fragments_container);
                    if (frameLayout3 != null) {
                        i = R.id.rightClick;
                        FrameLayout frameLayout4 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.rightClick);
                        if (frameLayout4 != null) {
                            i = R.id.view_pager_body;
                            ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(view, R.id.view_pager_body);
                            if (viewPager != null) {
                                i = R.id.view_pager_header;
                                ViewPager viewPager2 = (ViewPager) ViewBindings.findChildViewById(view, R.id.view_pager_header);
                                if (viewPager2 != null) {
                                    return new FragmentProgramsBinding(coordinatorLayout, coordinatorLayout, frameLayout, frameLayout2, imageView, frameLayout3, frameLayout4, viewPager, viewPager2);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
