package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;

/* loaded from: classes2.dex */
public final class TeaserRegularBigBinding implements ViewBinding {
    public final CustomTextView caption;
    public final View divider;
    public final CustomTextView flach;
    public final ImageView image;
    private final FrameLayout rootView;

    private TeaserRegularBigBinding(FrameLayout frameLayout, CustomTextView customTextView, View view, CustomTextView customTextView2, ImageView imageView) {
        this.rootView = frameLayout;
        this.caption = customTextView;
        this.divider = view;
        this.flach = customTextView2;
        this.image = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static TeaserRegularBigBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserRegularBigBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_regular_big, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserRegularBigBinding bind(View view) {
        int i = R.id.caption;
        CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.caption);
        if (customTextView != null) {
            i = R.id.divider;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
            if (viewFindChildViewById != null) {
                i = R.id.flach;
                CustomTextView customTextView2 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.flach);
                if (customTextView2 != null) {
                    i = R.id.image;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
                    if (imageView != null) {
                        return new TeaserRegularBigBinding((FrameLayout) view, customTextView, viewFindChildViewById, customTextView2, imageView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
