package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class OutbrainSfeedHeaderCustomBinding implements ViewBinding {
    public final ImageView adchoicesHeaderLogo;
    public final ImageButton outbrainLogoButton;
    public final TextView outbrainSponsoredObtextview;
    private final ConstraintLayout rootView;

    private OutbrainSfeedHeaderCustomBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageButton imageButton, TextView textView) {
        this.rootView = constraintLayout;
        this.adchoicesHeaderLogo = imageView;
        this.outbrainLogoButton = imageButton;
        this.outbrainSponsoredObtextview = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static OutbrainSfeedHeaderCustomBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static OutbrainSfeedHeaderCustomBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.outbrain_sfeed_header_custom, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static OutbrainSfeedHeaderCustomBinding bind(View view) {
        int i = R.id.adchoices_header_logo;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.adchoices_header_logo);
        if (imageView != null) {
            i = R.id.outbrain_logo_button;
            ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, R.id.outbrain_logo_button);
            if (imageButton != null) {
                i = R.id.outbrain_sponsored_obtextview;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.outbrain_sponsored_obtextview);
                if (textView != null) {
                    return new OutbrainSfeedHeaderCustomBinding((ConstraintLayout) view, imageView, imageButton, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
