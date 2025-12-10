package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class TeaserAdFeedrollBinding implements ViewBinding {
    public final Button adCallToAction;
    public final FrameLayout adContainer;
    public final ConstraintLayout body;
    public final ConstraintLayout container;
    public final ImageView muteUnMute;
    public final ImageView playPause;
    private final ConstraintLayout rootView;
    public final TextView title;

    private TeaserAdFeedrollBinding(ConstraintLayout constraintLayout, Button button, FrameLayout frameLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ImageView imageView, ImageView imageView2, TextView textView) {
        this.rootView = constraintLayout;
        this.adCallToAction = button;
        this.adContainer = frameLayout;
        this.body = constraintLayout2;
        this.container = constraintLayout3;
        this.muteUnMute = imageView;
        this.playPause = imageView2;
        this.title = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserAdFeedrollBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserAdFeedrollBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_ad_feedroll, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserAdFeedrollBinding bind(View view) {
        int i = R.id.ad_call_to_action;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.ad_call_to_action);
        if (button != null) {
            i = R.id.adContainer;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.adContainer);
            if (frameLayout != null) {
                i = R.id.body;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.body);
                if (constraintLayout != null) {
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) view;
                    i = R.id.muteUnMute;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.muteUnMute);
                    if (imageView != null) {
                        i = R.id.playPause;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.playPause);
                        if (imageView2 != null) {
                            i = R.id.title;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                            if (textView != null) {
                                return new TeaserAdFeedrollBinding(constraintLayout2, button, frameLayout, constraintLayout, constraintLayout2, imageView, imageView2, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
