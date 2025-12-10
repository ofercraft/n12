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
public final class ControlsLayoutLiveBinding implements ViewBinding {
    public final TextView backToLive;
    public final Group centerButtons;
    public final ImageView closeBtn;
    public final ImageView ffwBtn;
    public final KsPlayerMediaRouteButton mediaRouteButton;
    public final ImageView pipButton;
    public final ImageView playPauseBtn;
    public final TextView position;
    public final SeekBar progress;
    public final ImageView rewBtn;
    private final ConstraintLayout rootView;
    public final ImageView shareBtn;
    public final TextView thumbnailText;
    public final TextView title;
    public final Group topButtons;

    private ControlsLayoutLiveBinding(ConstraintLayout constraintLayout, TextView textView, Group group, ImageView imageView, ImageView imageView2, KsPlayerMediaRouteButton ksPlayerMediaRouteButton, ImageView imageView3, ImageView imageView4, TextView textView2, SeekBar seekBar, ImageView imageView5, ImageView imageView6, TextView textView3, TextView textView4, Group group2) {
        this.rootView = constraintLayout;
        this.backToLive = textView;
        this.centerButtons = group;
        this.closeBtn = imageView;
        this.ffwBtn = imageView2;
        this.mediaRouteButton = ksPlayerMediaRouteButton;
        this.pipButton = imageView3;
        this.playPauseBtn = imageView4;
        this.position = textView2;
        this.progress = seekBar;
        this.rewBtn = imageView5;
        this.shareBtn = imageView6;
        this.thumbnailText = textView3;
        this.title = textView4;
        this.topButtons = group2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ControlsLayoutLiveBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ControlsLayoutLiveBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_layout_live, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ControlsLayoutLiveBinding bind(View view) {
        int i = R.id.backToLive;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.backToLive);
        if (textView != null) {
            i = R.id.centerButtons;
            Group group = (Group) ViewBindings.findChildViewById(view, R.id.centerButtons);
            if (group != null) {
                i = R.id.closeBtn;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.closeBtn);
                if (imageView != null) {
                    i = R.id.ffwBtn;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.ffwBtn);
                    if (imageView2 != null) {
                        i = R.id.media_route_button;
                        KsPlayerMediaRouteButton ksPlayerMediaRouteButton = (KsPlayerMediaRouteButton) ViewBindings.findChildViewById(view, R.id.media_route_button);
                        if (ksPlayerMediaRouteButton != null) {
                            i = R.id.pip_button;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.pip_button);
                            if (imageView3 != null) {
                                i = R.id.playPauseBtn;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.playPauseBtn);
                                if (imageView4 != null) {
                                    i = R.id.position;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.position);
                                    if (textView2 != null) {
                                        i = R.id.progress;
                                        SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, R.id.progress);
                                        if (seekBar != null) {
                                            i = R.id.rewBtn;
                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.rewBtn);
                                            if (imageView5 != null) {
                                                i = R.id.shareBtn;
                                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.shareBtn);
                                                if (imageView6 != null) {
                                                    i = R.id.thumbnail_text;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.thumbnail_text);
                                                    if (textView3 != null) {
                                                        i = R.id.title;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                                        if (textView4 != null) {
                                                            i = R.id.topButtons;
                                                            Group group2 = (Group) ViewBindings.findChildViewById(view, R.id.topButtons);
                                                            if (group2 != null) {
                                                                return new ControlsLayoutLiveBinding((ConstraintLayout) view, textView, group, imageView, imageView2, ksPlayerMediaRouteButton, imageView3, imageView4, textView2, seekBar, imageView5, imageView6, textView3, textView4, group2);
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
