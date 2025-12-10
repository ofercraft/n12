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
public final class TeaserSectionHorizontalPagingVideoBinding implements ViewBinding {
    public final ImageView chatTabBG;
    public final ConstraintLayout linearLayout;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final TextView title;
    public final View view;
    public final View view2;

    private TeaserSectionHorizontalPagingVideoBinding(ConstraintLayout constraintLayout, ImageView imageView, ConstraintLayout constraintLayout2, RecyclerView recyclerView, TextView textView, View view, View view2) {
        this.rootView = constraintLayout;
        this.chatTabBG = imageView;
        this.linearLayout = constraintLayout2;
        this.recyclerView = recyclerView;
        this.title = textView;
        this.view = view;
        this.view2 = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserSectionHorizontalPagingVideoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserSectionHorizontalPagingVideoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_section_horizontal_paging_video, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserSectionHorizontalPagingVideoBinding bind(View view) {
        int i = R.id.chatTabBG;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.chatTabBG);
        if (imageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.recyclerView;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recyclerView);
            if (recyclerView != null) {
                i = R.id.title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                if (textView != null) {
                    i = R.id.view;
                    View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.view);
                    if (viewFindChildViewById != null) {
                        i = R.id.view2;
                        View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.view2);
                        if (viewFindChildViewById2 != null) {
                            return new TeaserSectionHorizontalPagingVideoBinding(constraintLayout, imageView, constraintLayout, recyclerView, textView, viewFindChildViewById, viewFindChildViewById2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
