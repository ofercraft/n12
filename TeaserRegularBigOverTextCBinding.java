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
public final class TeaserRegularBigOverTextCBinding implements ViewBinding {
    public final TextView author;
    public final TextView caption;
    public final TextView date;
    public final TextView flach;
    public final ImageView image;
    private final ConstraintLayout rootView;
    public final TextView textDevider;
    public final TextView title;

    private TeaserRegularBigOverTextCBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, ImageView imageView, TextView textView5, TextView textView6) {
        this.rootView = constraintLayout;
        this.author = textView;
        this.caption = textView2;
        this.date = textView3;
        this.flach = textView4;
        this.image = imageView;
        this.textDevider = textView5;
        this.title = textView6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserRegularBigOverTextCBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserRegularBigOverTextCBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_regular_big_over_text_c, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserRegularBigOverTextCBinding bind(View view) {
        int i = R.id.author;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.author);
        if (textView != null) {
            i = R.id.caption;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.caption);
            if (textView2 != null) {
                i = R.id.date;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.date);
                if (textView3 != null) {
                    i = R.id.flach;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.flach);
                    if (textView4 != null) {
                        i = R.id.image;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
                        if (imageView != null) {
                            i = R.id.text_devider;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.text_devider);
                            if (textView5 != null) {
                                i = R.id.title;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                if (textView6 != null) {
                                    return new TeaserRegularBigOverTextCBinding((ConstraintLayout) view, textView, textView2, textView3, textView4, imageView, textView5, textView6);
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
