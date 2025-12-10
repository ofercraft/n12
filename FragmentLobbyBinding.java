package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class FragmentLobbyBinding implements ViewBinding {
    public final FrameLayout headerView;
    public final ImageButton live;
    public final FrameLayout lobbyFragmentContainer;
    public final ConstraintLayout logoContainer;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final ImageView transparentHeaderAppLogo;

    private FragmentLobbyBinding(ConstraintLayout constraintLayout, FrameLayout frameLayout, ImageButton imageButton, FrameLayout frameLayout2, ConstraintLayout constraintLayout2, RecyclerView recyclerView, ImageView imageView) {
        this.rootView = constraintLayout;
        this.headerView = frameLayout;
        this.live = imageButton;
        this.lobbyFragmentContainer = frameLayout2;
        this.logoContainer = constraintLayout2;
        this.recyclerView = recyclerView;
        this.transparentHeaderAppLogo = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentLobbyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentLobbyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_lobby, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentLobbyBinding bind(View view) {
        int i = R.id.headerView;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.headerView);
        if (frameLayout != null) {
            i = R.id.live;
            ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, R.id.live);
            if (imageButton != null) {
                i = R.id.lobby_fragment_container;
                FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.lobby_fragment_container);
                if (frameLayout2 != null) {
                    i = R.id.logoContainer;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.logoContainer);
                    if (constraintLayout != null) {
                        i = R.id.recyclerView;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recyclerView);
                        if (recyclerView != null) {
                            i = R.id.transparentHeaderAppLogo;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.transparentHeaderAppLogo);
                            if (imageView != null) {
                                return new FragmentLobbyBinding((ConstraintLayout) view, frameLayout, imageButton, frameLayout2, constraintLayout, recyclerView, imageView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
