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
public final class ChatTextTabItemBinding implements ViewBinding {
    public final ConstraintLayout bg;
    public final TextView content;
    public final ImageView imgSpace;
    public final ConstraintLayout linkConstraintView;
    public final Button readMore;
    public final TextView reporter;
    public final ImageView reporterImage;
    public final TextView reporterShared;
    private final ConstraintLayout rootView;
    public final ImageView selectedAnimationView;
    public final ImageView sharedArrowImg;
    public final TextView sharedTxt;
    public final TextView time;

    private ChatTextTabItemBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, ImageView imageView, ConstraintLayout constraintLayout3, Button button, TextView textView2, ImageView imageView2, TextView textView3, ImageView imageView3, ImageView imageView4, TextView textView4, TextView textView5) {
        this.rootView = constraintLayout;
        this.bg = constraintLayout2;
        this.content = textView;
        this.imgSpace = imageView;
        this.linkConstraintView = constraintLayout3;
        this.readMore = button;
        this.reporter = textView2;
        this.reporterImage = imageView2;
        this.reporterShared = textView3;
        this.selectedAnimationView = imageView3;
        this.sharedArrowImg = imageView4;
        this.sharedTxt = textView4;
        this.time = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ChatTextTabItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChatTextTabItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chat_text_tab_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChatTextTabItemBinding bind(View view) {
        int i = R.id.bg;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.bg);
        if (constraintLayout != null) {
            i = R.id.content;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.content);
            if (textView != null) {
                i = R.id.imgSpace;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imgSpace);
                if (imageView != null) {
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) view;
                    i = R.id.readMore;
                    Button button = (Button) ViewBindings.findChildViewById(view, R.id.readMore);
                    if (button != null) {
                        i = R.id.reporter;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.reporter);
                        if (textView2 != null) {
                            i = R.id.reporterImage;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.reporterImage);
                            if (imageView2 != null) {
                                i = R.id.reporterShared;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.reporterShared);
                                if (textView3 != null) {
                                    i = R.id.selectedAnimationView;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.selectedAnimationView);
                                    if (imageView3 != null) {
                                        i = R.id.sharedArrowImg;
                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.sharedArrowImg);
                                        if (imageView4 != null) {
                                            i = R.id.sharedTxt;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.sharedTxt);
                                            if (textView4 != null) {
                                                i = R.id.time;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                                                if (textView5 != null) {
                                                    return new ChatTextTabItemBinding(constraintLayout2, constraintLayout, textView, imageView, constraintLayout2, button, textView2, imageView2, textView3, imageView3, imageView4, textView4, textView5);
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
