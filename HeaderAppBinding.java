package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.google.android.material.appbar.AppBarLayout;

/* loaded from: classes2.dex */
public final class HeaderAppBinding implements ViewBinding {
    public final AppBarLayout appBarLayout;
    public final FrameLayout headerContainer;
    private final AppBarLayout rootView;
    public final Toolbar toolbar;

    private HeaderAppBinding(AppBarLayout appBarLayout, AppBarLayout appBarLayout2, FrameLayout frameLayout, Toolbar toolbar) {
        this.rootView = appBarLayout;
        this.appBarLayout = appBarLayout2;
        this.headerContainer = frameLayout;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public AppBarLayout getRoot() {
        return this.rootView;
    }

    public static HeaderAppBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HeaderAppBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.header_app, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HeaderAppBinding bind(View view) {
        AppBarLayout appBarLayout = (AppBarLayout) view;
        int i = R.id.headerContainer;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.headerContainer);
        if (frameLayout != null) {
            i = R.id.toolbar;
            Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
            if (toolbar != null) {
                return new HeaderAppBinding(appBarLayout, appBarLayout, frameLayout, toolbar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
