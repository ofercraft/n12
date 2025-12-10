package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;

/* loaded from: classes2.dex */
public final class VersionControlAlertBinding implements ViewBinding {
    public final Button cancel;
    private final ConstraintLayout rootView;
    public final CustomTextView subtitle;
    public final CustomTextView title;
    public final Button update;
    public final ConstraintLayout versionControlAlert;

    private VersionControlAlertBinding(ConstraintLayout constraintLayout, Button button, CustomTextView customTextView, CustomTextView customTextView2, Button button2, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.cancel = button;
        this.subtitle = customTextView;
        this.title = customTextView2;
        this.update = button2;
        this.versionControlAlert = constraintLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static VersionControlAlertBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static VersionControlAlertBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.version_control_alert, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static VersionControlAlertBinding bind(View view) {
        int i = R.id.cancel;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.cancel);
        if (button != null) {
            i = R.id.subtitle;
            CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.subtitle);
            if (customTextView != null) {
                i = R.id.title;
                CustomTextView customTextView2 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.title);
                if (customTextView2 != null) {
                    i = R.id.update;
                    Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.update);
                    if (button2 != null) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) view;
                        return new VersionControlAlertBinding(constraintLayout, button, customTextView, customTextView2, button2, constraintLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
