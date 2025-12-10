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
public final class TeaserRegularSmallRoundNoCaptionBinding implements ViewBinding {
    public final TextView author;
    public final ImageView image;
    private final ConstraintLayout rootView;
    public final View separator;
    public final TextView title;

    private TeaserRegularSmallRoundNoCaptionBinding(ConstraintLayout constraintLayout, TextView textView, ImageView imageView, View view, TextView textView2) {
        this.rootView = constraintLayout;
        this.author = textView;
        this.image = imageView;
        this.separator = view;
        this.title = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserRegularSmallRoundNoCaptionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserRegularSmallRoundNoCaptionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_regular_small_round_no_caption, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserRegularSmallRoundNoCaptionBinding bind(View view) {
        int i = R.id.author;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.author);
        if (textView != null) {
            i = R.id.image;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
            if (imageView != null) {
                i = R.id.separator;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.separator);
                if (viewFindChildViewById != null) {
                    i = R.id.title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                    if (textView2 != null) {
                        return new TeaserRegularSmallRoundNoCaptionBinding((ConstraintLayout) view, textView, imageView, viewFindChildViewById, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
