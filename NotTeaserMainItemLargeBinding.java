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
public final class NotTeaserMainItemLargeBinding implements ViewBinding {
    public final ImageView image;
    private final FrameLayout rootView;
    public final CustomTextView subtitle;
    public final CustomTextView title;

    private NotTeaserMainItemLargeBinding(FrameLayout frameLayout, ImageView imageView, CustomTextView customTextView, CustomTextView customTextView2) {
        this.rootView = frameLayout;
        this.image = imageView;
        this.subtitle = customTextView;
        this.title = customTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static NotTeaserMainItemLargeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NotTeaserMainItemLargeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.not_teaser_main_item_large, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NotTeaserMainItemLargeBinding bind(View view) {
        int i = R.id.image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
        if (imageView != null) {
            i = R.id.subtitle;
            CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.subtitle);
            if (customTextView != null) {
                i = R.id.title;
                CustomTextView customTextView2 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.title);
                if (customTextView2 != null) {
                    return new NotTeaserMainItemLargeBinding((FrameLayout) view, imageView, customTextView, customTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
