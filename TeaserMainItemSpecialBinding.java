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
public final class TeaserMainItemSpecialBinding implements ViewBinding {
    public final TextView caption;
    public final ConstraintLayout frameLayout;
    public final ImageView image;
    private final ConstraintLayout rootView;
    public final TeaserMainItemSpecialTextBoxBinding teaserMainItemSpecialTextBox;

    private TeaserMainItemSpecialBinding(ConstraintLayout constraintLayout, TextView textView, ConstraintLayout constraintLayout2, ImageView imageView, TeaserMainItemSpecialTextBoxBinding teaserMainItemSpecialTextBoxBinding) {
        this.rootView = constraintLayout;
        this.caption = textView;
        this.frameLayout = constraintLayout2;
        this.image = imageView;
        this.teaserMainItemSpecialTextBox = teaserMainItemSpecialTextBoxBinding;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserMainItemSpecialBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserMainItemSpecialBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_main_item_special, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserMainItemSpecialBinding bind(View view) {
        int i = R.id.caption;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.caption);
        if (textView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.image;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
            if (imageView != null) {
                i = R.id.teaser_main_item_special_text_box;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.teaser_main_item_special_text_box);
                if (viewFindChildViewById != null) {
                    return new TeaserMainItemSpecialBinding(constraintLayout, textView, constraintLayout, imageView, TeaserMainItemSpecialTextBoxBinding.bind(viewFindChildViewById));
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
