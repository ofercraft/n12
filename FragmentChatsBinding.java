package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.channel2.mobile.ui.R;
import com.google.android.material.tabs.TabLayout;

/* loaded from: classes2.dex */
public final class FragmentChatsBinding implements ViewBinding {
    public final ImageView chatLoader;
    public final ConstraintLayout multiChatsFragment;
    public final ViewPager2 pager;
    private final ConstraintLayout rootView;
    public final TabLayout tabs;

    private FragmentChatsBinding(ConstraintLayout constraintLayout, ImageView imageView, ConstraintLayout constraintLayout2, ViewPager2 viewPager2, TabLayout tabLayout) {
        this.rootView = constraintLayout;
        this.chatLoader = imageView;
        this.multiChatsFragment = constraintLayout2;
        this.pager = viewPager2;
        this.tabs = tabLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChatsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChatsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_chats, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentChatsBinding bind(View view) {
        int i = R.id.chatLoader;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.chatLoader);
        if (imageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.pager;
            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.pager);
            if (viewPager2 != null) {
                i = R.id.tabs;
                TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(view, R.id.tabs);
                if (tabLayout != null) {
                    return new FragmentChatsBinding(constraintLayout, imageView, constraintLayout, viewPager2, tabLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
