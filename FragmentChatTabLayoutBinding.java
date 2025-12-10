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
public final class FragmentChatTabLayoutBinding implements ViewBinding {
    public final ImageView iconSelected;
    public final ImageView iconUnSelected;
    private final ConstraintLayout rootView;
    public final TextView tabText;

    private FragmentChatTabLayoutBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, TextView textView) {
        this.rootView = constraintLayout;
        this.iconSelected = imageView;
        this.iconUnSelected = imageView2;
        this.tabText = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChatTabLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChatTabLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_chat_tab_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentChatTabLayoutBinding bind(View view) {
        int i = R.id.iconSelected;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iconSelected);
        if (imageView != null) {
            i = R.id.iconUnSelected;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iconUnSelected);
            if (imageView2 != null) {
                i = R.id.tabText;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tabText);
                if (textView != null) {
                    return new FragmentChatTabLayoutBinding((ConstraintLayout) view, imageView, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
