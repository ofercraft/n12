package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class TeaserPodcastSmallBinding implements ViewBinding {
    public final TextView authorAndDate;
    public final View bg;
    public final TextView caption;
    public final View divider;
    public final TextView flach;
    public final ImageView image;
    public final ImageView imgMask;
    public final ImageView imgPodcast;
    private final ConstraintLayout rootView;
    public final TextView subtitle;
    public final TextView title;

    private TeaserPodcastSmallBinding(ConstraintLayout constraintLayout, TextView textView, View view, TextView textView2, View view2, TextView textView3, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView4, TextView textView5) {
        this.rootView = constraintLayout;
        this.authorAndDate = textView;
        this.bg = view;
        this.caption = textView2;
        this.divider = view2;
        this.flach = textView3;
        this.image = imageView;
        this.imgMask = imageView2;
        this.imgPodcast = imageView3;
        this.subtitle = textView4;
        this.title = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserPodcastSmallBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserPodcastSmallBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_podcast_small, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserPodcastSmallBinding bind(View view) {
        int i = R.id.authorAndDate;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.authorAndDate);
        if (textView != null) {
            i = R.id.bg;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.bg);
            if (viewFindChildViewById != null) {
                i = R.id.caption;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.caption);
                if (textView2 != null) {
                    i = R.id.divider;
                    View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.divider);
                    if (viewFindChildViewById2 != null) {
                        i = R.id.flach;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.flach);
                        if (textView3 != null) {
                            i = R.id.image;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.image);
                            if (imageView != null) {
                                i = R.id.imgMask;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.imgMask);
                                if (imageView2 != null) {
                                    i = R.id.imgPodcast;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.imgPodcast);
                                    if (imageView3 != null) {
                                        i = R.id.subtitle;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.subtitle);
                                        if (textView4 != null) {
                                            i = R.id.title;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                            if (textView5 != null) {
                                                return new TeaserPodcastSmallBinding((ConstraintLayout) view, textView, viewFindChildViewById, textView2, viewFindChildViewById2, textView3, imageView, imageView2, imageView3, textView4, textView5);
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
