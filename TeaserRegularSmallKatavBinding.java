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
public final class TeaserRegularSmallKatavBinding implements ViewBinding {
    public final View divider;
    public final ImageView image;
    public final ImageView linkA;
    public final ImageView linkB;
    private final FrameLayout rootView;
    public final CustomTextView subtitle;
    public final CustomTextView title;

    private TeaserRegularSmallKatavBinding(FrameLayout frameLayout, View view, ImageView imageView, ImageView imageView2, ImageView imageView3, CustomTextView customTextView, CustomTextView customTextView2) {
        this.rootView = frameLayout;
        this.divider = view;
        this.image = imageView;
        this.linkA = imageView2;
        this.linkB = imageView3;
        this.subtitle = customTextView;
        this.title = customTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static TeaserRegularSmallKatavBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserRegularSmallKatavBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_regular_small_katav, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserRegularSmallKatavBinding bind(View view) {
        int i = R.id.divider;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (viewFindChildViewById != null) {
            i = R.id.image;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
            if (imageView != null) {
                i = R.id.linkA;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.linkA);
                if (imageView2 != null) {
                    i = R.id.linkB;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.linkB);
                    if (imageView3 != null) {
                        i = R.id.subtitle;
                        CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.subtitle);
                        if (customTextView != null) {
                            i = R.id.title;
                            CustomTextView customTextView2 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.title);
                            if (customTextView2 != null) {
                                return new TeaserRegularSmallKatavBinding((FrameLayout) view, viewFindChildViewById, imageView, imageView2, imageView3, customTextView, customTextView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
