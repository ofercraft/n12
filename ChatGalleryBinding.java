package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.TouchImageView;

/* loaded from: classes2.dex */
public final class ChatGalleryBinding implements ViewBinding {
    public final TouchImageView image;
    public final FrameLayout playerContainer;
    private final ConstraintLayout rootView;
    public final View videoClick;

    private ChatGalleryBinding(ConstraintLayout constraintLayout, TouchImageView touchImageView, FrameLayout frameLayout, View view) {
        this.rootView = constraintLayout;
        this.image = touchImageView;
        this.playerContainer = frameLayout;
        this.videoClick = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ChatGalleryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChatGalleryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chat_gallery, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChatGalleryBinding bind(View view) {
        int i = R.id.image;
        TouchImageView touchImageView = (TouchImageView) ViewBindings.findChildViewById(view, R.id.image);
        if (touchImageView != null) {
            i = R.id.playerContainer;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.playerContainer);
            if (frameLayout != null) {
                i = R.id.videoClick;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.videoClick);
                if (viewFindChildViewById != null) {
                    return new ChatGalleryBinding((ConstraintLayout) view, touchImageView, frameLayout, viewFindChildViewById);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
