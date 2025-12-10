package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.outbrain.OBSDK.Viewability.OBCardView;

/* loaded from: classes2.dex */
public final class OutbrainSfeedStripThumnbnailWithTitleCustomBinding implements ViewBinding {
    public final OBCardView cv;
    public final TextView obPaidLabel;
    public final ImageView obRecImage;
    public final RelativeLayout obRecImageLayout;
    public final TextView obRecSource;
    public final TextView obRecTitle;
    public final LinearLayout obSfStripThumbnailItem;
    public final View obShadowView;
    public final LinearLayout obStripThumbnailLinearLayout;
    public final RelativeLayout obTitleRelativeLayout;
    public final TextView obTitleTextView;
    public final ConstraintLayout outbrainImageWrapperLayout;
    public final ImageView outbrainRecDisclosureImageView;
    public final ImageView outbrainRecLogoImageView;
    private final LinearLayout rootView;

    private OutbrainSfeedStripThumnbnailWithTitleCustomBinding(LinearLayout linearLayout, OBCardView oBCardView, TextView textView, ImageView imageView, RelativeLayout relativeLayout, TextView textView2, TextView textView3, LinearLayout linearLayout2, View view, LinearLayout linearLayout3, RelativeLayout relativeLayout2, TextView textView4, ConstraintLayout constraintLayout, ImageView imageView2, ImageView imageView3) {
        this.rootView = linearLayout;
        this.cv = oBCardView;
        this.obPaidLabel = textView;
        this.obRecImage = imageView;
        this.obRecImageLayout = relativeLayout;
        this.obRecSource = textView2;
        this.obRecTitle = textView3;
        this.obSfStripThumbnailItem = linearLayout2;
        this.obShadowView = view;
        this.obStripThumbnailLinearLayout = linearLayout3;
        this.obTitleRelativeLayout = relativeLayout2;
        this.obTitleTextView = textView4;
        this.outbrainImageWrapperLayout = constraintLayout;
        this.outbrainRecDisclosureImageView = imageView2;
        this.outbrainRecLogoImageView = imageView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OutbrainSfeedStripThumnbnailWithTitleCustomBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static OutbrainSfeedStripThumnbnailWithTitleCustomBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.outbrain_sfeed_strip_thumnbnail_with_title_custom, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static OutbrainSfeedStripThumnbnailWithTitleCustomBinding bind(View view) {
        int i = R.id.cv;
        OBCardView oBCardView = (OBCardView) ViewBindings.findChildViewById(view, R.id.cv);
        if (oBCardView != null) {
            i = R.id.ob_paid_label;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.ob_paid_label);
            if (textView != null) {
                i = R.id.ob_rec_image;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ob_rec_image);
                if (imageView != null) {
                    i = R.id.ob_rec_image_layout;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ob_rec_image_layout);
                    if (relativeLayout != null) {
                        i = R.id.ob_rec_source;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.ob_rec_source);
                        if (textView2 != null) {
                            i = R.id.ob_rec_title;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.ob_rec_title);
                            if (textView3 != null) {
                                LinearLayout linearLayout = (LinearLayout) view;
                                i = R.id.ob_shadow_view;
                                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.ob_shadow_view);
                                if (viewFindChildViewById != null) {
                                    i = R.id.ob_strip_thumbnail_linear_layout;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ob_strip_thumbnail_linear_layout);
                                    if (linearLayout2 != null) {
                                        i = R.id.ob_title_relative_layout;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ob_title_relative_layout);
                                        if (relativeLayout2 != null) {
                                            i = R.id.ob_title_text_view;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.ob_title_text_view);
                                            if (textView4 != null) {
                                                i = R.id.outbrain_image_wrapper_layout;
                                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.outbrain_image_wrapper_layout);
                                                if (constraintLayout != null) {
                                                    i = R.id.outbrain_rec_disclosure_image_view;
                                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.outbrain_rec_disclosure_image_view);
                                                    if (imageView2 != null) {
                                                        i = R.id.outbrain_rec_logo_image_view;
                                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.outbrain_rec_logo_image_view);
                                                        if (imageView3 != null) {
                                                            return new OutbrainSfeedStripThumnbnailWithTitleCustomBinding(linearLayout, oBCardView, textView, imageView, relativeLayout, textView2, textView3, linearLayout, viewFindChildViewById, linearLayout2, relativeLayout2, textView4, constraintLayout, imageView2, imageView3);
                                                        }
                                                    }
                                                }
                                            }
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
