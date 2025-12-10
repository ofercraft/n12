package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.TouchImageView;

/* loaded from: classes2.dex */
public final class GalleyItemBinding implements ViewBinding {
    public final TouchImageView image;
    public final WebView imageStatic;
    private final ConstraintLayout rootView;

    private GalleyItemBinding(ConstraintLayout constraintLayout, TouchImageView touchImageView, WebView webView) {
        this.rootView = constraintLayout;
        this.image = touchImageView;
        this.imageStatic = webView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static GalleyItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static GalleyItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.galley_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static GalleyItemBinding bind(View view) {
        int i = R.id.image;
        TouchImageView touchImageView = (TouchImageView) ViewBindings.findChildViewById(view, R.id.image);
        if (touchImageView != null) {
            i = R.id.imageStatic;
            WebView webView = (WebView) ViewBindings.findChildViewById(view, R.id.imageStatic);
            if (webView != null) {
                return new GalleyItemBinding((ConstraintLayout) view, touchImageView, webView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
