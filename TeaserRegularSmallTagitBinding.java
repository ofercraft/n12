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
public final class TeaserRegularSmallTagitBinding implements ViewBinding {
    public final CustomTextView author;
    public final CustomTextView caption;
    public final CustomTextView collaboration;
    public final CustomTextView date;
    public final View divider;
    public final ImageView image;
    private final FrameLayout rootView;
    public final CustomTextView subtitle;
    public final CustomTextView title;

    private TeaserRegularSmallTagitBinding(FrameLayout frameLayout, CustomTextView customTextView, CustomTextView customTextView2, CustomTextView customTextView3, CustomTextView customTextView4, View view, ImageView imageView, CustomTextView customTextView5, CustomTextView customTextView6) {
        this.rootView = frameLayout;
        this.author = customTextView;
        this.caption = customTextView2;
        this.collaboration = customTextView3;
        this.date = customTextView4;
        this.divider = view;
        this.image = imageView;
        this.subtitle = customTextView5;
        this.title = customTextView6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static TeaserRegularSmallTagitBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserRegularSmallTagitBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_regular_small_tagit, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserRegularSmallTagitBinding bind(View view) {
        int i = R.id.author;
        CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.author);
        if (customTextView != null) {
            i = R.id.caption;
            CustomTextView customTextView2 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.caption);
            if (customTextView2 != null) {
                i = R.id.collaboration;
                CustomTextView customTextView3 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.collaboration);
                if (customTextView3 != null) {
                    i = R.id.date;
                    CustomTextView customTextView4 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.date);
                    if (customTextView4 != null) {
                        i = R.id.divider;
                        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                        if (viewFindChildViewById != null) {
                            i = R.id.image;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
                            if (imageView != null) {
                                i = R.id.subtitle;
                                CustomTextView customTextView5 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.subtitle);
                                if (customTextView5 != null) {
                                    i = R.id.title;
                                    CustomTextView customTextView6 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.title);
                                    if (customTextView6 != null) {
                                        return new TeaserRegularSmallTagitBinding((FrameLayout) view, customTextView, customTextView2, customTextView3, customTextView4, viewFindChildViewById, imageView, customTextView5, customTextView6);
                                    }
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
