package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class ChatGalleryVideoControlsBinding implements ViewBinding {
    public final View bg;
    public final TextView duration;
    public final ImageView muteUnMute;
    public final ImageView playPause;
    public final TextView position;
    private final ConstraintLayout rootView;
    public final SeekBar seekbar;

    private ChatGalleryVideoControlsBinding(ConstraintLayout constraintLayout, View view, TextView textView, ImageView imageView, ImageView imageView2, TextView textView2, SeekBar seekBar) {
        this.rootView = constraintLayout;
        this.bg = view;
        this.duration = textView;
        this.muteUnMute = imageView;
        this.playPause = imageView2;
        this.position = textView2;
        this.seekbar = seekBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ChatGalleryVideoControlsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChatGalleryVideoControlsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chat_gallery_video_controls, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChatGalleryVideoControlsBinding bind(View view) {
        int i = R.id.bg;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.bg);
        if (viewFindChildViewById != null) {
            i = R.id.duration;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.duration);
            if (textView != null) {
                i = R.id.muteUnMute;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.muteUnMute);
                if (imageView != null) {
                    i = R.id.playPause;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.playPause);
                    if (imageView2 != null) {
                        i = R.id.position;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.position);
                        if (textView2 != null) {
                            i = R.id.seekbar;
                            SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, R.id.seekbar);
                            if (seekBar != null) {
                                return new ChatGalleryVideoControlsBinding((ConstraintLayout) view, viewFindChildViewById, textView, imageView, imageView2, textView2, seekBar);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
