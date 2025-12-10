package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.video.views.LivePlayerView;

/* loaded from: classes2.dex */
public final class PlayerLiveBinding implements ViewBinding {
    private final LivePlayerView rootView;

    private PlayerLiveBinding(LivePlayerView livePlayerView) {
        this.rootView = livePlayerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LivePlayerView getRoot() {
        return this.rootView;
    }

    public static PlayerLiveBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PlayerLiveBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.player_live, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PlayerLiveBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new PlayerLiveBinding((LivePlayerView) view);
    }
}
