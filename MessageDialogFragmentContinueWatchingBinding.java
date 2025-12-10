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
public final class MessageDialogFragmentContinueWatchingBinding implements ViewBinding {
    public final ImageView closeBtn;
    public final TextView noButton;
    private final ConstraintLayout rootView;
    public final TextView title;
    public final TextView yesButton;

    private MessageDialogFragmentContinueWatchingBinding(ConstraintLayout constraintLayout, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.closeBtn = imageView;
        this.noButton = textView;
        this.title = textView2;
        this.yesButton = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static MessageDialogFragmentContinueWatchingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static MessageDialogFragmentContinueWatchingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.message_dialog_fragment_continue_watching, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static MessageDialogFragmentContinueWatchingBinding bind(View view) {
        int i = R.id.closeBtn;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.closeBtn);
        if (imageView != null) {
            i = R.id.noButton;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.noButton);
            if (textView != null) {
                i = R.id.title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                if (textView2 != null) {
                    i = R.id.yesButton;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.yesButton);
                    if (textView3 != null) {
                        return new MessageDialogFragmentContinueWatchingBinding((ConstraintLayout) view, imageView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
