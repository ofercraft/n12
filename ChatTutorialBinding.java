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
public final class ChatTutorialBinding implements ViewBinding {
    public final View agree;
    public final ConstraintLayout chatTutorial;
    public final ImageView chatTutorialImg;
    private final ConstraintLayout rootView;

    private ChatTutorialBinding(ConstraintLayout constraintLayout, View view, ConstraintLayout constraintLayout2, ImageView imageView) {
        this.rootView = constraintLayout;
        this.agree = view;
        this.chatTutorial = constraintLayout2;
        this.chatTutorialImg = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ChatTutorialBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChatTutorialBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chat_tutorial, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChatTutorialBinding bind(View view) {
        int i = R.id.agree;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.agree);
        if (viewFindChildViewById != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.chatTutorialImg);
            if (imageView != null) {
                return new ChatTutorialBinding(constraintLayout, viewFindChildViewById, constraintLayout, imageView);
            }
            i = R.id.chatTutorialImg;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
