package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class FragmentVideoBinding implements ViewBinding {
    public final FrameLayout closeBtn;
    private final FrameLayout rootView;
    public final FrameLayout videoContainer;
    public final FrameLayout viewBackground;

    private FragmentVideoBinding(FrameLayout frameLayout, FrameLayout frameLayout2, FrameLayout frameLayout3, FrameLayout frameLayout4) {
        this.rootView = frameLayout;
        this.closeBtn = frameLayout2;
        this.videoContainer = frameLayout3;
        this.viewBackground = frameLayout4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentVideoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentVideoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_video, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentVideoBinding bind(View view) {
        int i = R.id.closeBtn;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.closeBtn);
        if (frameLayout != null) {
            i = R.id.videoContainer;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.videoContainer);
            if (frameLayout2 != null) {
                FrameLayout frameLayout3 = (FrameLayout) view;
                return new FragmentVideoBinding(frameLayout3, frameLayout, frameLayout2, frameLayout3);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
