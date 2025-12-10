package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;

/* loaded from: classes2.dex */
public final class TeaserFirstReportBinding implements ViewBinding {
    public final CustomTextView body;
    public final ImageView close;
    public final CustomTextView more;
    private final FrameLayout rootView;
    public final CustomTextView title;

    private TeaserFirstReportBinding(FrameLayout frameLayout, CustomTextView customTextView, ImageView imageView, CustomTextView customTextView2, CustomTextView customTextView3) {
        this.rootView = frameLayout;
        this.body = customTextView;
        this.close = imageView;
        this.more = customTextView2;
        this.title = customTextView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static TeaserFirstReportBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserFirstReportBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_first_report, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserFirstReportBinding bind(View view) {
        int i = R.id.body;
        CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.body);
        if (customTextView != null) {
            i = R.id.close;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.close);
            if (imageView != null) {
                i = R.id.more;
                CustomTextView customTextView2 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.more);
                if (customTextView2 != null) {
                    i = R.id.title;
                    CustomTextView customTextView3 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.title);
                    if (customTextView3 != null) {
                        return new TeaserFirstReportBinding((FrameLayout) view, customTextView, imageView, customTextView2, customTextView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
