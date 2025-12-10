package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class TeaserSectionHorizontalBinding implements ViewBinding {
    public final View backgroundImageView;
    public final RecyclerView recyclerView;
    public final ImageView rightIcon;
    private final ConstraintLayout rootView;
    public final TextView title;

    private TeaserSectionHorizontalBinding(ConstraintLayout constraintLayout, View view, RecyclerView recyclerView, ImageView imageView, TextView textView) {
        this.rootView = constraintLayout;
        this.backgroundImageView = view;
        this.recyclerView = recyclerView;
        this.rightIcon = imageView;
        this.title = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserSectionHorizontalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserSectionHorizontalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_section_horizontal, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserSectionHorizontalBinding bind(View view) {
        int i = R.id.backgroundImageView;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.backgroundImageView);
        if (viewFindChildViewById != null) {
            i = R.id.recyclerView;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recyclerView);
            if (recyclerView != null) {
                i = R.id.rightIcon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.rightIcon);
                if (imageView != null) {
                    i = R.id.title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                    if (textView != null) {
                        return new TeaserSectionHorizontalBinding((ConstraintLayout) view, viewFindChildViewById, recyclerView, imageView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
