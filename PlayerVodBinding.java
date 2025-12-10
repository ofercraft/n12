package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.video.views.VodPlayerView;

/* loaded from: classes2.dex */
public final class PlayerVodBinding implements ViewBinding {
    private final VodPlayerView rootView;

    private PlayerVodBinding(VodPlayerView vodPlayerView) {
        this.rootView = vodPlayerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public VodPlayerView getRoot() {
        return this.rootView;
    }

    public static PlayerVodBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PlayerVodBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.player_vod, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PlayerVodBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new PlayerVodBinding((VodPlayerView) view);
    }
}
