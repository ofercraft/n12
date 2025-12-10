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
public final class TeaserSectionVerticalBinding implements ViewBinding {
    public final View backgroundImageView;
    public final ImageView componentIcon;
    public final Group container;
    public final TextView outbrainViewability;
    public final ImageView rightIcon;
    public final ConstraintLayout rootSection;
    private final ConstraintLayout rootView;
    public final TextView title;

    private TeaserSectionVerticalBinding(ConstraintLayout constraintLayout, View view, ImageView imageView, Group group, TextView textView, ImageView imageView2, ConstraintLayout constraintLayout2, TextView textView2) {
        this.rootView = constraintLayout;
        this.backgroundImageView = view;
        this.componentIcon = imageView;
        this.container = group;
        this.outbrainViewability = textView;
        this.rightIcon = imageView2;
        this.rootSection = constraintLayout2;
        this.title = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserSectionVerticalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserSectionVerticalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_section_vertical, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserSectionVerticalBinding bind(View view) {
        int i = R.id.backgroundImageView;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.backgroundImageView);
        if (viewFindChildViewById != null) {
            i = R.id.componentIcon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.componentIcon);
            if (imageView != null) {
                i = R.id.container;
                Group group = (Group) ViewBindings.findChildViewById(view, R.id.container);
                if (group != null) {
                    i = R.id.outbrainViewability;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.outbrainViewability);
                    if (textView != null) {
                        i = R.id.rightIcon;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.rightIcon);
                        if (imageView2 != null) {
                            ConstraintLayout constraintLayout = (ConstraintLayout) view;
                            i = R.id.title;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                            if (textView2 != null) {
                                return new TeaserSectionVerticalBinding(constraintLayout, viewFindChildViewById, imageView, group, textView, imageView2, constraintLayout, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
