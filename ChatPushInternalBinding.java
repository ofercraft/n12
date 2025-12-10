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
public final class ChatPushInternalBinding implements ViewBinding {
    public final View pushBG;
    public final TextView pushContent;
    public final ImageView pushImage;
    public final TextView pushReporter;
    public final ImageView pushReporterImage;
    public final ConstraintLayout pushtLayout;
    private final ConstraintLayout rootView;
    public final ImageView shasow;

    private ChatPushInternalBinding(ConstraintLayout constraintLayout, View view, TextView textView, ImageView imageView, TextView textView2, ImageView imageView2, ConstraintLayout constraintLayout2, ImageView imageView3) {
        this.rootView = constraintLayout;
        this.pushBG = view;
        this.pushContent = textView;
        this.pushImage = imageView;
        this.pushReporter = textView2;
        this.pushReporterImage = imageView2;
        this.pushtLayout = constraintLayout2;
        this.shasow = imageView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ChatPushInternalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChatPushInternalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chat_push_internal, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChatPushInternalBinding bind(View view) {
        int i = R.id.pushBG;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.pushBG);
        if (viewFindChildViewById != null) {
            i = R.id.pushContent;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.pushContent);
            if (textView != null) {
                i = R.id.pushImage;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.pushImage);
                if (imageView != null) {
                    i = R.id.pushReporter;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.pushReporter);
                    if (textView2 != null) {
                        i = R.id.pushReporterImage;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.pushReporterImage);
                        if (imageView2 != null) {
                            ConstraintLayout constraintLayout = (ConstraintLayout) view;
                            i = R.id.shasow;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.shasow);
                            if (imageView3 != null) {
                                return new ChatPushInternalBinding(constraintLayout, viewFindChildViewById, textView, imageView, textView2, imageView2, constraintLayout, imageView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
