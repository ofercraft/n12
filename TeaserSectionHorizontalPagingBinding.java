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
public final class TeaserSectionHorizontalPagingBinding implements ViewBinding {
    public final View backgroundImageView;
    public final ImageView chatTabBG;
    public final ConstraintLayout linearLayout;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final TextView title;

    private TeaserSectionHorizontalPagingBinding(ConstraintLayout constraintLayout, View view, ImageView imageView, ConstraintLayout constraintLayout2, RecyclerView recyclerView, TextView textView) {
        this.rootView = constraintLayout;
        this.backgroundImageView = view;
        this.chatTabBG = imageView;
        this.linearLayout = constraintLayout2;
        this.recyclerView = recyclerView;
        this.title = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserSectionHorizontalPagingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserSectionHorizontalPagingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_section_horizontal_paging, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserSectionHorizontalPagingBinding bind(View view) {
        int i = R.id.backgroundImageView;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.backgroundImageView);
        if (viewFindChildViewById != null) {
            i = R.id.chatTabBG;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.chatTabBG);
            if (imageView != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R.id.recyclerView;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recyclerView);
                if (recyclerView != null) {
                    i = R.id.title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                    if (textView != null) {
                        return new TeaserSectionHorizontalPagingBinding(constraintLayout, viewFindChildViewById, imageView, constraintLayout, recyclerView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
