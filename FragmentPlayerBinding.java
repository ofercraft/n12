package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class FragmentPlayerBinding implements ViewBinding {
    public final ConstraintLayout multiChatsFragment;
    public final FrameLayout playerContainer;
    private final ConstraintLayout rootView;

    private FragmentPlayerBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, FrameLayout frameLayout) {
        this.rootView = constraintLayout;
        this.multiChatsFragment = constraintLayout2;
        this.playerContainer = frameLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPlayerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPlayerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_player, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPlayerBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.playerContainer);
        if (frameLayout != null) {
            return new FragmentPlayerBinding(constraintLayout, constraintLayout, frameLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.playerContainer)));
    }
}
