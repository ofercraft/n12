package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.mako.kscore.ksplayer.view.KsPlayerMediaRouteButton;

/* loaded from: classes2.dex */
public final class ControlsLayoutVodBinding implements ViewBinding {
    public final Group centerButtons;
    public final ImageView closeBtn;
    public final TextView duration;
    public final ImageView ffwBtn;
    public final KsPlayerMediaRouteButton mediaRouteButton;
    public final ImageView playPauseBtn;
    public final TextView position;
    public final SeekBar progress;
    public final ImageView rewBtn;
    private final ConstraintLayout rootView;
    public final ImageView shareBtn;
    public final TextView speedBtn;
    public final Group thumbnail;
    public final ImageView thumbnailImage;
    public final TextView thumbnailText;
    public final TextView title;
    public final Group topButtons;

    private ControlsLayoutVodBinding(ConstraintLayout constraintLayout, Group group, ImageView imageView, TextView textView, ImageView imageView2, KsPlayerMediaRouteButton ksPlayerMediaRouteButton, ImageView imageView3, TextView textView2, SeekBar seekBar, ImageView imageView4, ImageView imageView5, TextView textView3, Group group2, ImageView imageView6, TextView textView4, TextView textView5, Group group3) {
        this.rootView = constraintLayout;
        this.centerButtons = group;
        this.closeBtn = imageView;
        this.duration = textView;
        this.ffwBtn = imageView2;
        this.mediaRouteButton = ksPlayerMediaRouteButton;
        this.playPauseBtn = imageView3;
        this.position = textView2;
        this.progress = seekBar;
        this.rewBtn = imageView4;
        this.shareBtn = imageView5;
        this.speedBtn = textView3;
        this.thumbnail = group2;
        this.thumbnailImage = imageView6;
        this.thumbnailText = textView4;
        this.title = textView5;
        this.topButtons = group3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ControlsLayoutVodBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ControlsLayoutVodBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_layout_vod, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ControlsLayoutVodBinding bind(View view) {
        int i = R.id.centerButtons;
        Group group = (Group) ViewBindings.findChildViewById(view, R.id.centerButtons);
        if (group != null) {
            i = R.id.closeBtn;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.closeBtn);
            if (imageView != null) {
                i = R.id.duration;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.duration);
                if (textView != null) {
                    i = R.id.ffwBtn;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.ffwBtn);
                    if (imageView2 != null) {
                        i = R.id.media_route_button;
                        KsPlayerMediaRouteButton ksPlayerMediaRouteButton = (KsPlayerMediaRouteButton) ViewBindings.findChildViewById(view, R.id.media_route_button);
                        if (ksPlayerMediaRouteButton != null) {
                            i = R.id.playPauseBtn;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.playPauseBtn);
                            if (imageView3 != null) {
                                i = R.id.position;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.position);
                                if (textView2 != null) {
                                    i = R.id.progress;
                                    SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, R.id.progress);
                                    if (seekBar != null) {
                                        i = R.id.rewBtn;
                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.rewBtn);
                                        if (imageView4 != null) {
                                            i = R.id.shareBtn;
                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.shareBtn);
                                            if (imageView5 != null) {
                                                i = R.id.speedBtn;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.speedBtn);
                                                if (textView3 != null) {
                                                    i = R.id.thumbnail;
                                                    Group group2 = (Group) ViewBindings.findChildViewById(view, R.id.thumbnail);
                                                    if (group2 != null) {
                                                        i = R.id.thumbnail_image;
                                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.thumbnail_image);
                                                        if (imageView6 != null) {
                                                            i = R.id.thumbnail_text;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.thumbnail_text);
                                                            if (textView4 != null) {
                                                                i = R.id.title;
                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                                                if (textView5 != null) {
                                                                    i = R.id.topButtons;
                                                                    Group group3 = (Group) ViewBindings.findChildViewById(view, R.id.topButtons);
                                                                    if (group3 != null) {
                                                                        return new ControlsLayoutVodBinding((ConstraintLayout) view, group, imageView, textView, imageView2, ksPlayerMediaRouteButton, imageView3, textView2, seekBar, imageView4, imageView5, textView3, group2, imageView6, textView4, textView5, group3);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
