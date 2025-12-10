package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class TeaserAdBannerBinding implements ViewBinding {
    public final FrameLayout adContainer;
    public final ConstraintLayout body;
    public final ConstraintLayout container;
    private final ConstraintLayout rootView;
    public final TextView title;

    private TeaserAdBannerBinding(ConstraintLayout constraintLayout, FrameLayout frameLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, TextView textView) {
        this.rootView = constraintLayout;
        this.adContainer = frameLayout;
        this.body = constraintLayout2;
        this.container = constraintLayout3;
        this.title = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserAdBannerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserAdBannerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_ad_banner, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserAdBannerBinding bind(View view) {
        int i = R.id.adContainer;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.adContainer);
        if (frameLayout != null) {
            i = R.id.body;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.body);
            if (constraintLayout != null) {
                ConstraintLayout constraintLayout2 = (ConstraintLayout) view;
                i = R.id.title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                if (textView != null) {
                    return new TeaserAdBannerBinding(constraintLayout2, frameLayout, constraintLayout, constraintLayout2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
