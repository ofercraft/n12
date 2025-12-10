package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

/* loaded from: classes2.dex */
public final class FragmentDeveloperModeBinding implements ViewBinding {
    public final TextView developerModeTV;
    public final SwitchMaterial developerModeToggle;
    public final DividerSettingsThinBinding divider1;
    public final DividerSettingsThikBinding divider2;
    private final ConstraintLayout rootView;
    public final TextView userPercent;
    public final TextView userText;

    private FragmentDeveloperModeBinding(ConstraintLayout constraintLayout, TextView textView, SwitchMaterial switchMaterial, DividerSettingsThinBinding dividerSettingsThinBinding, DividerSettingsThikBinding dividerSettingsThikBinding, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.developerModeTV = textView;
        this.developerModeToggle = switchMaterial;
        this.divider1 = dividerSettingsThinBinding;
        this.divider2 = dividerSettingsThikBinding;
        this.userPercent = textView2;
        this.userText = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDeveloperModeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentDeveloperModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_developer_mode, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDeveloperModeBinding bind(View view) {
        int i = R.id.developerModeTV;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.developerModeTV);
        if (textView != null) {
            i = R.id.developerModeToggle;
            SwitchMaterial switchMaterial = (SwitchMaterial) ViewBindings.findChildViewById(view, R.id.developerModeToggle);
            if (switchMaterial != null) {
                i = R.id.divider1;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.divider1);
                if (viewFindChildViewById != null) {
                    DividerSettingsThinBinding dividerSettingsThinBindingBind = DividerSettingsThinBinding.bind(viewFindChildViewById);
                    i = R.id.divider2;
                    View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.divider2);
                    if (viewFindChildViewById2 != null) {
                        DividerSettingsThikBinding dividerSettingsThikBindingBind = DividerSettingsThikBinding.bind(viewFindChildViewById2);
                        i = R.id.user_percent;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.user_percent);
                        if (textView2 != null) {
                            i = R.id.user_text;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.user_text);
                            if (textView3 != null) {
                                return new FragmentDeveloperModeBinding((ConstraintLayout) view, textView, switchMaterial, dividerSettingsThinBindingBind, dividerSettingsThikBindingBind, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
