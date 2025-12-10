package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class ChatVideoTabItemBinding implements ViewBinding {
    public final ConstraintLayout bg;
    public final TextView content;
    public final ImageView imgSpace;
    public final ImageView media;
    public final ImageView play;
    public final Button readMore;
    public final TextView reporter;
    public final ImageView reporterImage;
    public final TextView reporterShared;
    private final ConstraintLayout rootView;
    public final ImageView selectedAnimationView;
    public final ImageView sharedArrowImg;
    public final TextView sharedTxt;
    public final TextView time;

    private ChatVideoTabItemBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, ImageView imageView, ImageView imageView2, ImageView imageView3, Button button, TextView textView2, ImageView imageView4, TextView textView3, ImageView imageView5, ImageView imageView6, TextView textView4, TextView textView5) {
        this.rootView = constraintLayout;
        this.bg = constraintLayout2;
        this.content = textView;
        this.imgSpace = imageView;
        this.media = imageView2;
        this.play = imageView3;
        this.readMore = button;
        this.reporter = textView2;
        this.reporterImage = imageView4;
        this.reporterShared = textView3;
        this.selectedAnimationView = imageView5;
        this.sharedArrowImg = imageView6;
        this.sharedTxt = textView4;
        this.time = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ChatVideoTabItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChatVideoTabItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chat_video_tab_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChatVideoTabItemBinding bind(View view) {
        int i = R.id.bg;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.bg);
        if (constraintLayout != null) {
            i = R.id.content;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.content);
            if (textView != null) {
                i = R.id.imgSpace;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imgSpace);
                if (imageView != null) {
                    i = R.id.media;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.media);
                    if (imageView2 != null) {
                        i = R.id.play;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.play);
                        if (imageView3 != null) {
                            i = R.id.readMore;
                            Button button = (Button) ViewBindings.findChildViewById(view, R.id.readMore);
                            if (button != null) {
                                i = R.id.reporter;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.reporter);
                                if (textView2 != null) {
                                    i = R.id.reporterImage;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.reporterImage);
                                    if (imageView4 != null) {
                                        i = R.id.reporterShared;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.reporterShared);
                                        if (textView3 != null) {
                                            i = R.id.selectedAnimationView;
                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.selectedAnimationView);
                                            if (imageView5 != null) {
                                                i = R.id.sharedArrowImg;
                                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.sharedArrowImg);
                                                if (imageView6 != null) {
                                                    i = R.id.sharedTxt;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.sharedTxt);
                                                    if (textView4 != null) {
                                                        i = R.id.time;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                                                        if (textView5 != null) {
                                                            return new ChatVideoTabItemBinding((ConstraintLayout) view, constraintLayout, textView, imageView, imageView2, imageView3, button, textView2, imageView4, textView3, imageView5, imageView6, textView4, textView5);
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
