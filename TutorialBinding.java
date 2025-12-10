package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class TutorialBinding implements ViewBinding {
    public final ImageView exit;
    public final ConstraintLayout linearLayout2;
    private final ConstraintLayout rootView;
    public final ImageView tutorial;

    private TutorialBinding(ConstraintLayout constraintLayout, ImageView imageView, ConstraintLayout constraintLayout2, ImageView imageView2) {
        this.rootView = constraintLayout;
        this.exit = imageView;
        this.linearLayout2 = constraintLayout2;
        this.tutorial = imageView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TutorialBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TutorialBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.tutorial, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TutorialBinding bind(View view) {
        int i = R.id.exit;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.exit);
        if (imageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.tutorial);
            if (imageView2 != null) {
                return new TutorialBinding(constraintLayout, imageView, constraintLayout, imageView2);
            }
            i = R.id.tutorial;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
