package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class TeaserVideoItemBinding implements ViewBinding {
    public final ImageView image;
    public final ImageView play;
    private final ConstraintLayout rootView;
    public final ConstraintLayout teaserVideoNew;
    public final TextView title;
    public final FrameLayout videoContainer;
    public final TextView videoFlach;

    private TeaserVideoItemBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, ConstraintLayout constraintLayout2, TextView textView, FrameLayout frameLayout, TextView textView2) {
        this.rootView = constraintLayout;
        this.image = imageView;
        this.play = imageView2;
        this.teaserVideoNew = constraintLayout2;
        this.title = textView;
        this.videoContainer = frameLayout;
        this.videoFlach = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserVideoItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserVideoItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_video_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserVideoItemBinding bind(View view) {
        int i = R.id.image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
        if (imageView != null) {
            i = R.id.play;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.play);
            if (imageView2 != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R.id.title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                if (textView != null) {
                    i = R.id.videoContainer;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.videoContainer);
                    if (frameLayout != null) {
                        i = R.id.videoFlach;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.videoFlach);
                        if (textView2 != null) {
                            return new TeaserVideoItemBinding(constraintLayout, imageView, imageView2, constraintLayout, textView, frameLayout, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
