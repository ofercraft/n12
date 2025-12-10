package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;

/* loaded from: classes2.dex */
public final class NavigationItemBinding implements ViewBinding {
    public final ImageView image;
    private final LinearLayout rootView;
    public final CustomTextView title;

    private NavigationItemBinding(LinearLayout linearLayout, ImageView imageView, CustomTextView customTextView) {
        this.rootView = linearLayout;
        this.image = imageView;
        this.title = customTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static NavigationItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NavigationItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.navigation_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NavigationItemBinding bind(View view) {
        int i = R.id.image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
        if (imageView != null) {
            i = R.id.title;
            CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.title);
            if (customTextView != null) {
                return new NavigationItemBinding((LinearLayout) view, imageView, customTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
