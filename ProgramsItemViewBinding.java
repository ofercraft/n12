package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;

/* loaded from: classes2.dex */
public final class ProgramsItemViewBinding implements ViewBinding {
    public final CustomTextView date;
    public final ImageView image;
    public final FrameLayout overlay;
    public final ImageView play;
    private final ConstraintLayout rootView;
    public final CustomTextView title;
    public final FrameLayout videoContainer;
    public final CustomTextView videoFlach;

    private ProgramsItemViewBinding(ConstraintLayout constraintLayout, CustomTextView customTextView, ImageView imageView, FrameLayout frameLayout, ImageView imageView2, CustomTextView customTextView2, FrameLayout frameLayout2, CustomTextView customTextView3) {
        this.rootView = constraintLayout;
        this.date = customTextView;
        this.image = imageView;
        this.overlay = frameLayout;
        this.play = imageView2;
        this.title = customTextView2;
        this.videoContainer = frameLayout2;
        this.videoFlach = customTextView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ProgramsItemViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ProgramsItemViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.programs_item_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ProgramsItemViewBinding bind(View view) {
        int i = R.id.date;
        CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.date);
        if (customTextView != null) {
            i = R.id.image;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
            if (imageView != null) {
                i = R.id.overlay;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.overlay);
                if (frameLayout != null) {
                    i = R.id.play;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.play);
                    if (imageView2 != null) {
                        i = R.id.title;
                        CustomTextView customTextView2 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.title);
                        if (customTextView2 != null) {
                            i = R.id.videoContainer;
                            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.videoContainer);
                            if (frameLayout2 != null) {
                                i = R.id.videoFlach;
                                CustomTextView customTextView3 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.videoFlach);
                                if (customTextView3 != null) {
                                    return new ProgramsItemViewBinding((ConstraintLayout) view, customTextView, imageView, frameLayout, imageView2, customTextView2, frameLayout2, customTextView3);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
