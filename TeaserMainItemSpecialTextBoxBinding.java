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
public final class TeaserMainItemSpecialTextBoxBinding implements ViewBinding {
    public final TextView authorAndDate;
    public final View lineBeforeLinks;
    public final View lineSeparator;
    public final TextView linkA;
    public final ImageView linkAIcon;
    public final TextView linkB;
    public final ImageView linkBIcon;
    private final ConstraintLayout rootView;
    public final TextView subtitle;
    public final ConstraintLayout teaserMainItemSpecialTextBoxLayout;
    public final TextView title;

    private TeaserMainItemSpecialTextBoxBinding(ConstraintLayout constraintLayout, TextView textView, View view, View view2, TextView textView2, ImageView imageView, TextView textView3, ImageView imageView2, TextView textView4, ConstraintLayout constraintLayout2, TextView textView5) {
        this.rootView = constraintLayout;
        this.authorAndDate = textView;
        this.lineBeforeLinks = view;
        this.lineSeparator = view2;
        this.linkA = textView2;
        this.linkAIcon = imageView;
        this.linkB = textView3;
        this.linkBIcon = imageView2;
        this.subtitle = textView4;
        this.teaserMainItemSpecialTextBoxLayout = constraintLayout2;
        this.title = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserMainItemSpecialTextBoxBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserMainItemSpecialTextBoxBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_main_item_special_text_box, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserMainItemSpecialTextBoxBinding bind(View view) {
        int i = R.id.author_and_date;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.author_and_date);
        if (textView != null) {
            i = R.id.line_before_links;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.line_before_links);
            if (viewFindChildViewById != null) {
                i = R.id.line_separator;
                View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.line_separator);
                if (viewFindChildViewById2 != null) {
                    i = R.id.linkA;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.linkA);
                    if (textView2 != null) {
                        i = R.id.linkAIcon;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.linkAIcon);
                        if (imageView != null) {
                            i = R.id.linkB;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.linkB);
                            if (textView3 != null) {
                                i = R.id.linkBIcon;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.linkBIcon);
                                if (imageView2 != null) {
                                    i = R.id.subtitle;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.subtitle);
                                    if (textView4 != null) {
                                        ConstraintLayout constraintLayout = (ConstraintLayout) view;
                                        i = R.id.title;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                        if (textView5 != null) {
                                            return new TeaserMainItemSpecialTextBoxBinding(constraintLayout, textView, viewFindChildViewById, viewFindChildViewById2, textView2, imageView, textView3, imageView2, textView4, constraintLayout, textView5);
                                        }
                                    }
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
