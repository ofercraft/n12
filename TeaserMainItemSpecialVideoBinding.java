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
public final class TeaserMainItemSpecialVideoBinding implements ViewBinding {
    public final CustomVideoControls customVideoControls;
    public final ConstraintLayout frameLayout;
    public final ImageView image;
    private final ConstraintLayout rootView;
    public final TeaserMainItemSpecialTextBoxBinding teaserMainItemSpecialTextBox;
    public final FrameLayout videoContainer;

    private TeaserMainItemSpecialVideoBinding(ConstraintLayout constraintLayout, CustomVideoControls customVideoControls, ConstraintLayout constraintLayout2, ImageView imageView, TeaserMainItemSpecialTextBoxBinding teaserMainItemSpecialTextBoxBinding, FrameLayout frameLayout) {
        this.rootView = constraintLayout;
        this.customVideoControls = customVideoControls;
        this.frameLayout = constraintLayout2;
        this.image = imageView;
        this.teaserMainItemSpecialTextBox = teaserMainItemSpecialTextBoxBinding;
        this.videoContainer = frameLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserMainItemSpecialVideoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserMainItemSpecialVideoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_main_item_special_video, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserMainItemSpecialVideoBinding bind(View view) {
        int i = R.id.customVideoControls;
        CustomVideoControls customVideoControls = (CustomVideoControls) ViewBindings.findChildViewById(view, R.id.customVideoControls);
        if (customVideoControls != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.image;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
            if (imageView != null) {
                i = R.id.teaser_main_item_special_text_box;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.teaser_main_item_special_text_box);
                if (viewFindChildViewById != null) {
                    TeaserMainItemSpecialTextBoxBinding teaserMainItemSpecialTextBoxBindingBind = TeaserMainItemSpecialTextBoxBinding.bind(viewFindChildViewById);
                    i = R.id.videoContainer;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.videoContainer);
                    if (frameLayout != null) {
                        return new TeaserMainItemSpecialVideoBinding(constraintLayout, customVideoControls, constraintLayout, imageView, teaserMainItemSpecialTextBoxBindingBind, frameLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
