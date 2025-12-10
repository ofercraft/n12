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
public final class TeaserQuoteItemBinding implements ViewBinding {
    public final ImageView bg;
    private final ConstraintLayout rootView;
    public final View separator;
    public final TextView title;

    private TeaserQuoteItemBinding(ConstraintLayout constraintLayout, ImageView imageView, View view, TextView textView) {
        this.rootView = constraintLayout;
        this.bg = imageView;
        this.separator = view;
        this.title = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserQuoteItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserQuoteItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_quote_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserQuoteItemBinding bind(View view) {
        int i = R.id.bg;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.bg);
        if (imageView != null) {
            i = R.id.separator;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.separator);
            if (viewFindChildViewById != null) {
                i = R.id.title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                if (textView != null) {
                    return new TeaserQuoteItemBinding((ConstraintLayout) view, imageView, viewFindChildViewById, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
