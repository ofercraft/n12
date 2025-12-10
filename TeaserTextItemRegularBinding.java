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
public final class TeaserTextItemRegularBinding implements ViewBinding {
    public final TextView authorAndDate;
    public final View divider;
    public final TextView flach;
    public final ConstraintLayout rootView;
    private final ConstraintLayout rootView_;
    public final TextView title;

    private TeaserTextItemRegularBinding(ConstraintLayout constraintLayout, TextView textView, View view, TextView textView2, ConstraintLayout constraintLayout2, TextView textView3) {
        this.rootView_ = constraintLayout;
        this.authorAndDate = textView;
        this.divider = view;
        this.flach = textView2;
        this.rootView = constraintLayout2;
        this.title = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView_;
    }

    public static TeaserTextItemRegularBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserTextItemRegularBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_text_item_regular, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserTextItemRegularBinding bind(View view) {
        int i = R.id.authorAndDate;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.authorAndDate);
        if (textView != null) {
            i = R.id.divider;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
            if (viewFindChildViewById != null) {
                i = R.id.flach;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.flach);
                if (textView2 != null) {
                    ConstraintLayout constraintLayout = (ConstraintLayout) view;
                    i = R.id.title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                    if (textView3 != null) {
                        return new TeaserTextItemRegularBinding(constraintLayout, textView, viewFindChildViewById, textView2, constraintLayout, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
