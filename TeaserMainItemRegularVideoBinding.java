package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.lobby.views.CustomVideoControls;

/* loaded from: classes2.dex */
public final class TeaserMainItemRegularVideoBinding implements ViewBinding {
    public final CustomVideoControls customVideoControls;
    public final ConstraintLayout frameLayout;
    public final ImageView image;
    private final ConstraintLayout rootView;
    public final TeaserMainItemRegularTextBoxBinding teaserMainItemRegularTextBox;
    public final FrameLayout videoContainer;

    private TeaserMainItemRegularVideoBinding(ConstraintLayout constraintLayout, CustomVideoControls customVideoControls, ConstraintLayout constraintLayout2, ImageView imageView, TeaserMainItemRegularTextBoxBinding teaserMainItemRegularTextBoxBinding, FrameLayout frameLayout) {
        this.rootView = constraintLayout;
        this.customVideoControls = customVideoControls;
        this.frameLayout = constraintLayout2;
        this.image = imageView;
        this.teaserMainItemRegularTextBox = teaserMainItemRegularTextBoxBinding;
        this.videoContainer = frameLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserMainItemRegularVideoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserMainItemRegularVideoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_main_item_regular_video, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserMainItemRegularVideoBinding bind(View view) {
        int i = R.id.customVideoControls;
        CustomVideoControls customVideoControls = (CustomVideoControls) ViewBindings.findChildViewById(view, R.id.customVideoControls);
        if (customVideoControls != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.image;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
            if (imageView != null) {
                i = R.id.teaser_main_item_regular_text_box;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.teaser_main_item_regular_text_box);
                if (viewFindChildViewById != null) {
                    TeaserMainItemRegularTextBoxBinding teaserMainItemRegularTextBoxBindingBind = TeaserMainItemRegularTextBoxBinding.bind(viewFindChildViewById);
                    i = R.id.videoContainer;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.videoContainer);
                    if (frameLayout != null) {
                        return new TeaserMainItemRegularVideoBinding(constraintLayout, customVideoControls, constraintLayout, imageView, teaserMainItemRegularTextBoxBindingBind, frameLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
