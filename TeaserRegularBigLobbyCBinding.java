package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;

/* loaded from: classes2.dex */
public final class TeaserRegularBigLobbyCBinding implements ViewBinding {
    public final ConstraintLayout frameLayout6;
    public final ImageView image;
    public final ImageView outBrainDisclosureIcon;
    public final CustomTextView outBrainPaid;
    private final ConstraintLayout rootView;
    public final TeaserRegularBigLobbyTextBoxBinding teaserRegularBigLobbyTextBox;

    private TeaserRegularBigLobbyCBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, ImageView imageView2, CustomTextView customTextView, TeaserRegularBigLobbyTextBoxBinding teaserRegularBigLobbyTextBoxBinding) {
        this.rootView = constraintLayout;
        this.frameLayout6 = constraintLayout2;
        this.image = imageView;
        this.outBrainDisclosureIcon = imageView2;
        this.outBrainPaid = customTextView;
        this.teaserRegularBigLobbyTextBox = teaserRegularBigLobbyTextBoxBinding;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserRegularBigLobbyCBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserRegularBigLobbyCBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_regular_big_lobby_c, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserRegularBigLobbyCBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
        if (imageView != null) {
            i = R.id.outBrainDisclosureIcon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.outBrainDisclosureIcon);
            if (imageView2 != null) {
                i = R.id.outBrainPaid;
                CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.outBrainPaid);
                if (customTextView != null) {
                    i = R.id.teaser_regular_big_lobby_text_box;
                    View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.teaser_regular_big_lobby_text_box);
                    if (viewFindChildViewById != null) {
                        return new TeaserRegularBigLobbyCBinding(constraintLayout, constraintLayout, imageView, imageView2, customTextView, TeaserRegularBigLobbyTextBoxBinding.bind(viewFindChildViewById));
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
