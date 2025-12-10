package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.webView.views.CustomWebView;

/* loaded from: classes2.dex */
public final class FragmentWebViewBinding implements ViewBinding {
    public final CoordinatorLayout container;
    public final FrameLayout fontSize;
    private final CoordinatorLayout rootView;
    public final AppCompatSeekBar seekBar;
    public final CustomWebView webView;
    public final FrameLayout webViewContainer;
    public final FrameLayout webViewFragmentContainer;

    private FragmentWebViewBinding(CoordinatorLayout coordinatorLayout, CoordinatorLayout coordinatorLayout2, FrameLayout frameLayout, AppCompatSeekBar appCompatSeekBar, CustomWebView customWebView, FrameLayout frameLayout2, FrameLayout frameLayout3) {
        this.rootView = coordinatorLayout;
        this.container = coordinatorLayout2;
        this.fontSize = frameLayout;
        this.seekBar = appCompatSeekBar;
        this.webView = customWebView;
        this.webViewContainer = frameLayout2;
        this.webViewFragmentContainer = frameLayout3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentWebViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentWebViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_web_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentWebViewBinding bind(View view) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
        int i = R.id.fontSize;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fontSize);
        if (frameLayout != null) {
            i = R.id.seekBar;
            AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) ViewBindings.findChildViewById(view, R.id.seekBar);
            if (appCompatSeekBar != null) {
                i = R.id.webView;
                CustomWebView customWebView = (CustomWebView) ViewBindings.findChildViewById(view, R.id.webView);
                if (customWebView != null) {
                    i = R.id.web_view_container;
                    FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.web_view_container);
                    if (frameLayout2 != null) {
                        i = R.id.web_view_fragment_container;
                        FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.web_view_fragment_container);
                        if (frameLayout3 != null) {
                            return new FragmentWebViewBinding(coordinatorLayout, coordinatorLayout, frameLayout, appCompatSeekBar, customWebView, frameLayout2, frameLayout3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
