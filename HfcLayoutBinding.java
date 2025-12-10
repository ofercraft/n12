package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class HfcLayoutBinding implements ViewBinding {
    public final ImageButton btnClose;
    public final ImageView hfcLogo;
    public final TextView hfcTitle;
    public final ConstraintLayout hfcView;
    public final AppCompatTextView htcCities;
    private final ConstraintLayout rootView;

    private HfcLayoutBinding(ConstraintLayout constraintLayout, ImageButton imageButton, ImageView imageView, TextView textView, ConstraintLayout constraintLayout2, AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.btnClose = imageButton;
        this.hfcLogo = imageView;
        this.hfcTitle = textView;
        this.hfcView = constraintLayout2;
        this.htcCities = appCompatTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HfcLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HfcLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.hfc_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HfcLayoutBinding bind(View view) {
        int i = R.id.btn_close;
        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, R.id.btn_close);
        if (imageButton != null) {
            i = R.id.hfc_logo;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.hfc_logo);
            if (imageView != null) {
                i = R.id.hfc_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.hfc_title);
                if (textView != null) {
                    ConstraintLayout constraintLayout = (ConstraintLayout) view;
                    i = R.id.htc_cities;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.htc_cities);
                    if (appCompatTextView != null) {
                        return new HfcLayoutBinding(constraintLayout, imageButton, imageView, textView, constraintLayout, appCompatTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
