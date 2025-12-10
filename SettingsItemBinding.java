package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;

/* loaded from: classes2.dex */
public final class SettingsItemBinding implements ViewBinding {
    public final ImageView arrowLeft;
    public final CustomTextView displayName;
    public final View divider;
    private final FrameLayout rootView;
    public final Switch switchButton;

    private SettingsItemBinding(FrameLayout frameLayout, ImageView imageView, CustomTextView customTextView, View view, Switch r5) {
        this.rootView = frameLayout;
        this.arrowLeft = imageView;
        this.displayName = customTextView;
        this.divider = view;
        this.switchButton = r5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static SettingsItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SettingsItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.settings_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SettingsItemBinding bind(View view) {
        int i = R.id.arrowLeft;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.arrowLeft);
        if (imageView != null) {
            i = R.id.displayName;
            CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.displayName);
            if (customTextView != null) {
                i = R.id.divider;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                if (viewFindChildViewById != null) {
                    i = R.id.switchButton;
                    Switch r7 = (Switch) ViewBindings.findChildViewById(view, R.id.switchButton);
                    if (r7 != null) {
                        return new SettingsItemBinding((FrameLayout) view, imageView, customTextView, viewFindChildViewById, r7);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
