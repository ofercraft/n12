package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;

/* loaded from: classes2.dex */
public final class ProgramsHeaderViewBinding implements ViewBinding {
    public final ImageView image;
    private final FrameLayout rootView;
    public final CustomTextView title;

    private ProgramsHeaderViewBinding(FrameLayout frameLayout, ImageView imageView, CustomTextView customTextView) {
        this.rootView = frameLayout;
        this.image = imageView;
        this.title = customTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ProgramsHeaderViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ProgramsHeaderViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.programs_header_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ProgramsHeaderViewBinding bind(View view) {
        int i = R.id.image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
        if (imageView != null) {
            i = R.id.title;
            CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.title);
            if (customTextView != null) {
                return new ProgramsHeaderViewBinding((FrameLayout) view, imageView, customTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
