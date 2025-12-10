package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;

/* loaded from: classes2.dex */
public final class SettingsVersionItemBinding implements ViewBinding {
    public final CustomTextView displayName;
    private final FrameLayout rootView;

    private SettingsVersionItemBinding(FrameLayout frameLayout, CustomTextView customTextView) {
        this.rootView = frameLayout;
        this.displayName = customTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static SettingsVersionItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SettingsVersionItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.settings_version_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SettingsVersionItemBinding bind(View view) {
        CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.displayName);
        if (customTextView != null) {
            return new SettingsVersionItemBinding((FrameLayout) view, customTextView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.displayName)));
    }
}
