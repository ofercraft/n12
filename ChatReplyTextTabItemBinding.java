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
public final class ChatReplyTextTabItemBinding implements ViewBinding {
    public final ConstraintLayout bg;
    public final TextView content;
    public final TextView contentReply;
    public final View devider;
    public final ImageView imgSpace;
    public final Button readMore;
    public final ConstraintLayout replyConstraintView;
    public final TextView reporter;
    public final ImageView reporterImage;
    public final TextView reporterReply;
    public final TextView reporterShared;
    private final ConstraintLayout rootView;
    public final ImageView selectedAnimationView;
    public final ImageView sharedArrowImg;
    public final TextView sharedTxt;
    public final TextView time;

    private ChatReplyTextTabItemBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, View view, ImageView imageView, Button button, ConstraintLayout constraintLayout3, TextView textView3, ImageView imageView2, TextView textView4, TextView textView5, ImageView imageView3, ImageView imageView4, TextView textView6, TextView textView7) {
        this.rootView = constraintLayout;
        this.bg = constraintLayout2;
        this.content = textView;
        this.contentReply = textView2;
        this.devider = view;
        this.imgSpace = imageView;
        this.readMore = button;
        this.replyConstraintView = constraintLayout3;
        this.reporter = textView3;
        this.reporterImage = imageView2;
        this.reporterReply = textView4;
        this.reporterShared = textView5;
        this.selectedAnimationView = imageView3;
        this.sharedArrowImg = imageView4;
        this.sharedTxt = textView6;
        this.time = textView7;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ChatReplyTextTabItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChatReplyTextTabItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chat_reply_text_tab_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChatReplyTextTabItemBinding bind(View view) {
        int i = R.id.bg;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.bg);
        if (constraintLayout != null) {
            i = R.id.content;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.content);
            if (textView != null) {
                i = R.id.contentReply;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.contentReply);
                if (textView2 != null) {
                    i = R.id.devider;
                    View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.devider);
                    if (viewFindChildViewById != null) {
                        i = R.id.imgSpace;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imgSpace);
                        if (imageView != null) {
                            i = R.id.readMore;
                            Button button = (Button) ViewBindings.findChildViewById(view, R.id.readMore);
                            if (button != null) {
                                ConstraintLayout constraintLayout2 = (ConstraintLayout) view;
                                i = R.id.reporter;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.reporter);
                                if (textView3 != null) {
                                    i = R.id.reporterImage;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.reporterImage);
                                    if (imageView2 != null) {
                                        i = R.id.reporterReply;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.reporterReply);
                                        if (textView4 != null) {
                                            i = R.id.reporterShared;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.reporterShared);
                                            if (textView5 != null) {
                                                i = R.id.selectedAnimationView;
                                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.selectedAnimationView);
                                                if (imageView3 != null) {
                                                    i = R.id.sharedArrowImg;
                                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.sharedArrowImg);
                                                    if (imageView4 != null) {
                                                        i = R.id.sharedTxt;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.sharedTxt);
                                                        if (textView6 != null) {
                                                            i = R.id.time;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                                                            if (textView7 != null) {
                                                                return new ChatReplyTextTabItemBinding(constraintLayout2, constraintLayout, textView, textView2, viewFindChildViewById, imageView, button, constraintLayout2, textView3, imageView2, textView4, textView5, imageView3, imageView4, textView6, textView7);
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
