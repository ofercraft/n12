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
public final class ChatLinkTabItemBinding implements ViewBinding {
    public final ConstraintLayout bg;
    public final TextView content;
    public final View devider;
    public final ImageView imgSpace;
    public final ConstraintLayout linkConstraintView;
    public final TextView linkContent;
    public final TextView linkTitle;
    public final ImageView media;
    public final Button readMore;
    public final TextView reporter;
    public final ImageView reporterImage;
    public final TextView reporterShared;
    private final ConstraintLayout rootView;
    public final ImageView selectedAnimationView;
    public final ImageView sharedArrowImg;
    public final TextView sharedTxt;
    public final TextView time;

    private ChatLinkTabItemBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, View view, ImageView imageView, ConstraintLayout constraintLayout3, TextView textView2, TextView textView3, ImageView imageView2, Button button, TextView textView4, ImageView imageView3, TextView textView5, ImageView imageView4, ImageView imageView5, TextView textView6, TextView textView7) {
        this.rootView = constraintLayout;
        this.bg = constraintLayout2;
        this.content = textView;
        this.devider = view;
        this.imgSpace = imageView;
        this.linkConstraintView = constraintLayout3;
        this.linkContent = textView2;
        this.linkTitle = textView3;
        this.media = imageView2;
        this.readMore = button;
        this.reporter = textView4;
        this.reporterImage = imageView3;
        this.reporterShared = textView5;
        this.selectedAnimationView = imageView4;
        this.sharedArrowImg = imageView5;
        this.sharedTxt = textView6;
        this.time = textView7;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ChatLinkTabItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChatLinkTabItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chat_link_tab_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChatLinkTabItemBinding bind(View view) {
        int i = R.id.bg;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.bg);
        if (constraintLayout != null) {
            i = R.id.content;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.content);
            if (textView != null) {
                i = R.id.devider;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.devider);
                if (viewFindChildViewById != null) {
                    i = R.id.imgSpace;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imgSpace);
                    if (imageView != null) {
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) view;
                        i = R.id.linkContent;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.linkContent);
                        if (textView2 != null) {
                            i = R.id.linkTitle;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.linkTitle);
                            if (textView3 != null) {
                                i = R.id.media;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.media);
                                if (imageView2 != null) {
                                    i = R.id.readMore;
                                    Button button = (Button) ViewBindings.findChildViewById(view, R.id.readMore);
                                    if (button != null) {
                                        i = R.id.reporter;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.reporter);
                                        if (textView4 != null) {
                                            i = R.id.reporterImage;
                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.reporterImage);
                                            if (imageView3 != null) {
                                                i = R.id.reporterShared;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.reporterShared);
                                                if (textView5 != null) {
                                                    i = R.id.selectedAnimationView;
                                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.selectedAnimationView);
                                                    if (imageView4 != null) {
                                                        i = R.id.sharedArrowImg;
                                                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.sharedArrowImg);
                                                        if (imageView5 != null) {
                                                            i = R.id.sharedTxt;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.sharedTxt);
                                                            if (textView6 != null) {
                                                                i = R.id.time;
                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                                                                if (textView7 != null) {
                                                                    return new ChatLinkTabItemBinding(constraintLayout2, constraintLayout, textView, viewFindChildViewById, imageView, constraintLayout2, textView2, textView3, imageView2, button, textView4, imageView3, textView5, imageView4, imageView5, textView6, textView7);
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
