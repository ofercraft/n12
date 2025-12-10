package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.webView.views.CustomWebView;

/* loaded from: classes2.dex */
public final class TeaserIframeBinding implements ViewBinding {
    private final CustomWebView rootView;
    public final CustomWebView webView;

    private TeaserIframeBinding(CustomWebView customWebView, CustomWebView customWebView2) {
        this.rootView = customWebView;
        this.webView = customWebView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CustomWebView getRoot() {
        return this.rootView;
    }

    public static TeaserIframeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserIframeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_iframe, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserIframeBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        CustomWebView customWebView = (CustomWebView) view;
        return new TeaserIframeBinding(customWebView, customWebView);
    }
}
