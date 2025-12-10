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
public final class TeaserRegularSmallRoundBinding implements ViewBinding {
    public final TextView author;
    public final TextView caption;
    public final View divider;
    public final ImageView image;
    private final ConstraintLayout rootView;
    public final TextView title;

    private TeaserRegularSmallRoundBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, View view, ImageView imageView, TextView textView3) {
        this.rootView = constraintLayout;
        this.author = textView;
        this.caption = textView2;
        this.divider = view;
        this.image = imageView;
        this.title = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserRegularSmallRoundBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserRegularSmallRoundBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_regular_small_round, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserRegularSmallRoundBinding bind(View view) {
        int i = R.id.author;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.author);
        if (textView != null) {
            i = R.id.caption;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.caption);
            if (textView2 != null) {
                i = R.id.divider;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                if (viewFindChildViewById != null) {
                    i = R.id.image;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
                    if (imageView != null) {
                        i = R.id.title;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                        if (textView3 != null) {
                            return new TeaserRegularSmallRoundBinding((ConstraintLayout) view, textView, textView2, viewFindChildViewById, imageView, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
