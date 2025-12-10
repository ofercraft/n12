package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class HeaderLobbyBinding implements ViewBinding {
    public final ImageView appLogo;
    public final FrameLayout back;
    public final ConstraintLayout centerView;
    public final TextView channelTitle;
    public final FrameLayout headerContainer;
    public final ImageButton live;
    public final ConstraintLayout logoContainer;
    private final FrameLayout rootView;

    private HeaderLobbyBinding(FrameLayout frameLayout, ImageView imageView, FrameLayout frameLayout2, ConstraintLayout constraintLayout, TextView textView, FrameLayout frameLayout3, ImageButton imageButton, ConstraintLayout constraintLayout2) {
        this.rootView = frameLayout;
        this.appLogo = imageView;
        this.back = frameLayout2;
        this.centerView = constraintLayout;
        this.channelTitle = textView;
        this.headerContainer = frameLayout3;
        this.live = imageButton;
        this.logoContainer = constraintLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static HeaderLobbyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HeaderLobbyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.header_lobby, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HeaderLobbyBinding bind(View view) {
        int i = R.id.appLogo;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.appLogo);
        if (imageView != null) {
            i = R.id.back;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.back);
            if (frameLayout != null) {
                i = R.id.centerView;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.centerView);
                if (constraintLayout != null) {
                    i = R.id.channelTitle;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.channelTitle);
                    if (textView != null) {
                        FrameLayout frameLayout2 = (FrameLayout) view;
                        i = R.id.live;
                        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, R.id.live);
                        if (imageButton != null) {
                            i = R.id.logoContainer;
                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.logoContainer);
                            if (constraintLayout2 != null) {
                                return new HeaderLobbyBinding(frameLayout2, imageView, frameLayout, constraintLayout, textView, frameLayout2, imageButton, constraintLayout2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
