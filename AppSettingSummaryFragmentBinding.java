package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class AppSettingSummaryFragmentBinding implements ViewBinding {
    public final TextView body;
    private final ConstraintLayout rootView;
    public final Button sendButton;
    public final TextView title;

    private AppSettingSummaryFragmentBinding(ConstraintLayout constraintLayout, TextView textView, Button button, TextView textView2) {
        this.rootView = constraintLayout;
        this.body = textView;
        this.sendButton = button;
        this.title = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static AppSettingSummaryFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AppSettingSummaryFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.app_setting_summary_fragment, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AppSettingSummaryFragmentBinding bind(View view) {
        int i = R.id.body;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.body);
        if (textView != null) {
            i = R.id.send_button;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.send_button);
            if (button != null) {
                i = R.id.title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                if (textView2 != null) {
                    return new AppSettingSummaryFragmentBinding((ConstraintLayout) view, textView, button, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
