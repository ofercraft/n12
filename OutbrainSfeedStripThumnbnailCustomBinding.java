package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.outbrain.OBSDK.Viewability.OBCardView;

/* loaded from: classes2.dex */
public final class OutbrainSfeedStripThumnbnailCustomBinding implements ViewBinding {
    public final OBCardView cv;
    public final View divider;
    public final TextView obPaidLabel;
    public final ImageView obRecImage;
    public final TextView obRecSource;
    public final TextView obRecTitle;
    public final LinearLayout obSfStripThumbnailItem;
    public final ConstraintLayout outbrainImageWrapperLayout;
    public final ImageView outbrainRecDisclosureImageView;
    public final ImageView outbrainRecLogoImageView;
    private final LinearLayout rootView;

    private OutbrainSfeedStripThumnbnailCustomBinding(LinearLayout linearLayout, OBCardView oBCardView, View view, TextView textView, ImageView imageView, TextView textView2, TextView textView3, LinearLayout linearLayout2, ConstraintLayout constraintLayout, ImageView imageView2, ImageView imageView3) {
        this.rootView = linearLayout;
        this.cv = oBCardView;
        this.divider = view;
        this.obPaidLabel = textView;
        this.obRecImage = imageView;
        this.obRecSource = textView2;
        this.obRecTitle = textView3;
        this.obSfStripThumbnailItem = linearLayout2;
        this.outbrainImageWrapperLayout = constraintLayout;
        this.outbrainRecDisclosureImageView = imageView2;
        this.outbrainRecLogoImageView = imageView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OutbrainSfeedStripThumnbnailCustomBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static OutbrainSfeedStripThumnbnailCustomBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.outbrain_sfeed_strip_thumnbnail_custom, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static OutbrainSfeedStripThumnbnailCustomBinding bind(View view) {
        int i = R.id.cv;
        OBCardView oBCardView = (OBCardView) ViewBindings.findChildViewById(view, R.id.cv);
        if (oBCardView != null) {
            i = R.id.divider;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
            if (viewFindChildViewById != null) {
                i = R.id.ob_paid_label;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.ob_paid_label);
                if (textView != null) {
                    i = R.id.ob_rec_image;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ob_rec_image);
                    if (imageView != null) {
                        i = R.id.ob_rec_source;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.ob_rec_source);
                        if (textView2 != null) {
                            i = R.id.ob_rec_title;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.ob_rec_title);
                            if (textView3 != null) {
                                LinearLayout linearLayout = (LinearLayout) view;
                                i = R.id.outbrain_image_wrapper_layout;
                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.outbrain_image_wrapper_layout);
                                if (constraintLayout != null) {
                                    i = R.id.outbrain_rec_disclosure_image_view;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.outbrain_rec_disclosure_image_view);
                                    if (imageView2 != null) {
                                        i = R.id.outbrain_rec_logo_image_view;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.outbrain_rec_logo_image_view);
                                        if (imageView3 != null) {
                                            return new OutbrainSfeedStripThumnbnailCustomBinding(linearLayout, oBCardView, viewFindChildViewById, textView, imageView, textView2, textView3, linearLayout, constraintLayout, imageView2, imageView3);
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
