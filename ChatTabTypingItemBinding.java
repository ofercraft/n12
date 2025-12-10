package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class ChatTabTypingItemBinding implements ViewBinding {
    public final ImageView bg;
    public final ImageView chatTyping;
    public final ImageView reporterImage;
    private final ConstraintLayout rootView;

    private ChatTabTypingItemBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, ImageView imageView3) {
        this.rootView = constraintLayout;
        this.bg = imageView;
        this.chatTyping = imageView2;
        this.reporterImage = imageView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ChatTabTypingItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChatTabTypingItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chat_tab_typing_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChatTabTypingItemBinding bind(View view) {
        int i = R.id.bg;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.bg);
        if (imageView != null) {
            i = R.id.chat_typing;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.chat_typing);
            if (imageView2 != null) {
                i = R.id.reporterImage;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.reporterImage);
                if (imageView3 != null) {
                    return new ChatTabTypingItemBinding((ConstraintLayout) view, imageView, imageView2, imageView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
