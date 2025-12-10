package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class HeaderArticleBinding implements ViewBinding {
    public final FrameLayout aa;
    public final FrameLayout articleContainer;
    public final FrameLayout back;
    public final FrameLayout headerContainer;
    private final FrameLayout rootView;
    public final FrameLayout share;

    private HeaderArticleBinding(FrameLayout frameLayout, FrameLayout frameLayout2, FrameLayout frameLayout3, FrameLayout frameLayout4, FrameLayout frameLayout5, FrameLayout frameLayout6) {
        this.rootView = frameLayout;
        this.aa = frameLayout2;
        this.articleContainer = frameLayout3;
        this.back = frameLayout4;
        this.headerContainer = frameLayout5;
        this.share = frameLayout6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static HeaderArticleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HeaderArticleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.header_article, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HeaderArticleBinding bind(View view) {
        int i = R.id.aa;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.aa);
        if (frameLayout != null) {
            i = R.id.articleContainer;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.articleContainer);
            if (frameLayout2 != null) {
                i = R.id.back;
                FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.back);
                if (frameLayout3 != null) {
                    FrameLayout frameLayout4 = (FrameLayout) view;
                    i = R.id.share;
                    FrameLayout frameLayout5 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.share);
                    if (frameLayout5 != null) {
                        return new HeaderArticleBinding(frameLayout4, frameLayout, frameLayout2, frameLayout3, frameLayout4, frameLayout5);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
