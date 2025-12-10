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
public final class ChatCollageTabItemBinding implements ViewBinding {
    public final ConstraintLayout bg;
    public final ImageView firstMedia;
    public final ImageView imgSpace;
    public final ImageView playFirst;
    public final ImageView playSecond;
    public final ImageView playThird;
    public final TextView reporter;
    public final ImageView reporterImage;
    public final TextView reporterShared;
    private final ConstraintLayout rootView;
    public final ImageView secondMedia;
    public final ImageView selectedAnimationView;
    public final ImageView sharedArrowImg;
    public final TextView sharedTxt;
    public final ImageView thirdMedia;
    public final TextView time;

    private ChatCollageTabItemBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, TextView textView, ImageView imageView6, TextView textView2, ImageView imageView7, ImageView imageView8, ImageView imageView9, TextView textView3, ImageView imageView10, TextView textView4) {
        this.rootView = constraintLayout;
        this.bg = constraintLayout2;
        this.firstMedia = imageView;
        this.imgSpace = imageView2;
        this.playFirst = imageView3;
        this.playSecond = imageView4;
        this.playThird = imageView5;
        this.reporter = textView;
        this.reporterImage = imageView6;
        this.reporterShared = textView2;
        this.secondMedia = imageView7;
        this.selectedAnimationView = imageView8;
        this.sharedArrowImg = imageView9;
        this.sharedTxt = textView3;
        this.thirdMedia = imageView10;
        this.time = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ChatCollageTabItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChatCollageTabItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chat_collage_tab_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChatCollageTabItemBinding bind(View view) {
        int i = R.id.bg;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.bg);
        if (constraintLayout != null) {
            i = R.id.first_media;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.first_media);
            if (imageView != null) {
                i = R.id.imgSpace;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.imgSpace);
                if (imageView2 != null) {
                    i = R.id.play_first;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.play_first);
                    if (imageView3 != null) {
                        i = R.id.play_second;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.play_second);
                        if (imageView4 != null) {
                            i = R.id.play_third;
                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.play_third);
                            if (imageView5 != null) {
                                i = R.id.reporter;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.reporter);
                                if (textView != null) {
                                    i = R.id.reporterImage;
                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.reporterImage);
                                    if (imageView6 != null) {
                                        i = R.id.reporterShared;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.reporterShared);
                                        if (textView2 != null) {
                                            i = R.id.second_media;
                                            ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.second_media);
                                            if (imageView7 != null) {
                                                i = R.id.selectedAnimationView;
                                                ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.selectedAnimationView);
                                                if (imageView8 != null) {
                                                    i = R.id.sharedArrowImg;
                                                    ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, R.id.sharedArrowImg);
                                                    if (imageView9 != null) {
                                                        i = R.id.sharedTxt;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.sharedTxt);
                                                        if (textView3 != null) {
                                                            i = R.id.third_media;
                                                            ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(view, R.id.third_media);
                                                            if (imageView10 != null) {
                                                                i = R.id.time;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                                                                if (textView4 != null) {
                                                                    return new ChatCollageTabItemBinding((ConstraintLayout) view, constraintLayout, imageView, imageView2, imageView3, imageView4, imageView5, textView, imageView6, textView2, imageView7, imageView8, imageView9, textView3, imageView10, textView4);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
