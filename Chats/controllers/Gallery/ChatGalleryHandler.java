package com.channel2.mobile.ui.Chats.controllers.Gallery;

import android.view.View;
import android.widget.FrameLayout;

/* loaded from: classes2.dex */
public interface ChatGalleryHandler {
    void onAttach(View view, int i);

    void onDetach(View view, int i);

    void onItemClick(int i);

    void onItemClick(View view);

    void onPauseVideo(FrameLayout frameLayout);

    void onPlayVideo(FrameLayout frameLayout, int i);
}
