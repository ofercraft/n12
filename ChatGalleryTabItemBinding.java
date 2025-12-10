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
public final class ChatGalleryTabItemBinding implements ViewBinding {
    public final ConstraintLayout bg;
    public final ImageView firstMedia;
    public final ImageView fourthMedia;
    public final ImageView imageBg;
    public final ImageView imgSpace;
    public final TextView moreMedia;
    public final ImageView playFirst;
    public final ImageView playFourth;
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

    private ChatGalleryTabItemBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, TextView textView, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, TextView textView2, ImageView imageView9, TextView textView3, ImageView imageView10, ImageView imageView11, ImageView imageView12, TextView textView4, ImageView imageView13, TextView textView5) {
        this.rootView = constraintLayout;
        this.bg = constraintLayout2;
        this.firstMedia = imageView;
        this.fourthMedia = imageView2;
        this.imageBg = imageView3;
        this.imgSpace = imageView4;
        this.moreMedia = textView;
        this.playFirst = imageView5;
        this.playFourth = imageView6;
        this.playSecond = imageView7;
        this.playThird = imageView8;
        this.reporter = textView2;
        this.reporterImage = imageView9;
        this.reporterShared = textView3;
        this.secondMedia = imageView10;
        this.selectedAnimationView = imageView11;
        this.sharedArrowImg = imageView12;
        this.sharedTxt = textView4;
        this.thirdMedia = imageView13;
        this.time = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ChatGalleryTabItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChatGalleryTabItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chat_gallery_tab_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChatGalleryTabItemBinding bind(View view) {
        int i = R.id.bg;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.bg);
        if (constraintLayout != null) {
            i = R.id.first_media;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.first_media);
            if (imageView != null) {
                i = R.id.fourth_media;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.fourth_media);
                if (imageView2 != null) {
                    i = R.id.imageBg;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.imageBg);
                    if (imageView3 != null) {
                        i = R.id.imgSpace;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.imgSpace);
                        if (imageView4 != null) {
                            i = R.id.more_media;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.more_media);
                            if (textView != null) {
                                i = R.id.play_first;
                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.play_first);
                                if (imageView5 != null) {
                                    i = R.id.play_fourth;
                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.play_fourth);
                                    if (imageView6 != null) {
                                        i = R.id.play_second;
                                        ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.play_second);
                                        if (imageView7 != null) {
                                            i = R.id.play_third;
                                            ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.play_third);
                                            if (imageView8 != null) {
                                                i = R.id.reporter;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.reporter);
                                                if (textView2 != null) {
                                                    i = R.id.reporterImage;
                                                    ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, R.id.reporterImage);
                                                    if (imageView9 != null) {
                                                        i = R.id.reporterShared;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.reporterShared);
                                                        if (textView3 != null) {
                                                            i = R.id.second_media;
                                                            ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(view, R.id.second_media);
                                                            if (imageView10 != null) {
                                                                i = R.id.selectedAnimationView;
                                                                ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(view, R.id.selectedAnimationView);
                                                                if (imageView11 != null) {
                                                                    i = R.id.sharedArrowImg;
                                                                    ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(view, R.id.sharedArrowImg);
                                                                    if (imageView12 != null) {
                                                                        i = R.id.sharedTxt;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.sharedTxt);
                                                                        if (textView4 != null) {
                                                                            i = R.id.third_media;
                                                                            ImageView imageView13 = (ImageView) ViewBindings.findChildViewById(view, R.id.third_media);
                                                                            if (imageView13 != null) {
                                                                                i = R.id.time;
                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                                                                                if (textView5 != null) {
                                                                                    return new ChatGalleryTabItemBinding((ConstraintLayout) view, constraintLayout, imageView, imageView2, imageView3, imageView4, textView, imageView5, imageView6, imageView7, imageView8, textView2, imageView9, textView3, imageView10, imageView11, imageView12, textView4, imageView13, textView5);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
