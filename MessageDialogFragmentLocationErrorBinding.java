package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class MessageDialogFragmentLocationErrorBinding implements ViewBinding {
    public final ImageView closeBtn;
    public final ImageView icon;
    private final ConstraintLayout rootView;
    public final TextView title;

    private MessageDialogFragmentLocationErrorBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, TextView textView) {
        this.rootView = constraintLayout;
        this.closeBtn = imageView;
        this.icon = imageView2;
        this.title = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static MessageDialogFragmentLocationErrorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static MessageDialogFragmentLocationErrorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.message_dialog_fragment_location_error, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static MessageDialogFragmentLocationErrorBinding bind(View view) {
        int i = R.id.closeBtn;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.closeBtn);
        if (imageView != null) {
            i = R.id.icon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.icon);
            if (imageView2 != null) {
                i = R.id.title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                if (textView != null) {
                    return new MessageDialogFragmentLocationErrorBinding((ConstraintLayout) view, imageView, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
