package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class TeaserSectionVerticalParshanimBinding implements ViewBinding {
    public final View backgroundImageView;
    public final ImageView componentIcon;
    public final Group container;
    public final ConstraintLayout frameLayout7;
    private final ConstraintLayout rootView;
    public final TextView title;

    private TeaserSectionVerticalParshanimBinding(ConstraintLayout constraintLayout, View view, ImageView imageView, Group group, ConstraintLayout constraintLayout2, TextView textView) {
        this.rootView = constraintLayout;
        this.backgroundImageView = view;
        this.componentIcon = imageView;
        this.container = group;
        this.frameLayout7 = constraintLayout2;
        this.title = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserSectionVerticalParshanimBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserSectionVerticalParshanimBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_section_vertical_parshanim, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserSectionVerticalParshanimBinding bind(View view) {
        int i = R.id.backgroundImageView;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.backgroundImageView);
        if (viewFindChildViewById != null) {
            i = R.id.componentIcon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.componentIcon);
            if (imageView != null) {
                i = R.id.container;
                Group group = (Group) ViewBindings.findChildViewById(view, R.id.container);
                if (group != null) {
                    ConstraintLayout constraintLayout = (ConstraintLayout) view;
                    i = R.id.title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                    if (textView != null) {
                        return new TeaserSectionVerticalParshanimBinding(constraintLayout, viewFindChildViewById, imageView, group, constraintLayout, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
