package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class TeaserAdvertisingItemTextBinding implements ViewBinding {
    public final View background;
    public final View divider;
    public final TextView flach;
    private final ConstraintLayout rootView;
    public final TextView title;

    private TeaserAdvertisingItemTextBinding(ConstraintLayout constraintLayout, View view, View view2, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.background = view;
        this.divider = view2;
        this.flach = textView;
        this.title = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserAdvertisingItemTextBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserAdvertisingItemTextBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_advertising_item_text, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserAdvertisingItemTextBinding bind(View view) {
        int i = R.id.background;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.background);
        if (viewFindChildViewById != null) {
            i = R.id.divider;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.divider);
            if (viewFindChildViewById2 != null) {
                i = R.id.flach;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.flach);
                if (textView != null) {
                    i = R.id.title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                    if (textView2 != null) {
                        return new TeaserAdvertisingItemTextBinding((ConstraintLayout) view, viewFindChildViewById, viewFindChildViewById2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
