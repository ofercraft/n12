package com.channel2.mobile.ui.lobby.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.helpers.CustomVideoControlsHandler;
import com.channel2.mobile.ui.helpers.Utils;
import com.mako.kscore.ksplayer.view.RootControl;

/* loaded from: classes2.dex */
public class CustomVideoControls extends FrameLayout {
    private ImageView muteUnMute;
    private RootControl rootControl;

    public CustomVideoControls(Context context) {
        super(context);
    }

    public CustomVideoControls(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomVideoControls(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void registerCallback(final CustomVideoControlsHandler customVideoControlsHandler) {
        this.rootControl = (RootControl) findViewById(R.id.root_control);
        if (this.muteUnMute == null) {
            View view = new View(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, (getHeight() * 25) / 53);
            layoutParams.gravity = 80;
            view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.main_video_bottom_gradient));
            view.setLayoutParams(layoutParams);
            view.setId(R.id.controls_grad);
            addView(view);
            setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.views.CustomVideoControls$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    customVideoControlsHandler.onClick(false);
                }
            });
            ImageView imageView = new ImageView(getContext());
            this.muteUnMute = imageView;
            imageView.setId(R.id.controls_mute);
            addView(this.muteUnMute);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(Utils.convertDipToPixels(getContext(), 40.0f), Utils.convertDipToPixels(getContext(), 40.0f));
            layoutParams2.gravity = 85;
            layoutParams2.bottomMargin = Utils.convertDipToPixels(getContext(), 6.0f);
            layoutParams2.rightMargin = Utils.convertDipToPixels(getContext(), 49.0f);
            this.muteUnMute.setLayoutParams(layoutParams2);
            this.muteUnMute.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.views.CustomVideoControls$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$registerCallback$1(view2);
                }
            });
            ImageView imageView2 = new ImageView(getContext());
            addView(imageView2);
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(Utils.convertDipToPixels(getContext(), 40.0f), Utils.convertDipToPixels(getContext(), 40.0f));
            layoutParams3.gravity = 85;
            layoutParams3.bottomMargin = Utils.convertDipToPixels(getContext(), 6.0f);
            layoutParams3.rightMargin = Utils.convertDipToPixels(getContext(), 6.0f);
            imageView2.setLayoutParams(layoutParams3);
            imageView2.setImageResource(R.drawable.fullscreen);
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.views.CustomVideoControls$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    customVideoControlsHandler.onClick(true);
                }
            });
            imageView2.setId(R.id.controls_fullScreen);
        }
        this.muteUnMute.setImageResource(R.drawable.volume_off);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$registerCallback$1(View view) {
        if (getVolumeState() == 0.0f) {
            unMute();
        } else {
            mute();
        }
    }

    public void removeControls() {
        this.rootControl = null;
        ImageView imageView = this.muteUnMute;
        if (imageView == null) {
            View viewFindViewById = findViewById(R.id.controls_mute);
            if (viewFindViewById != null) {
                removeView(viewFindViewById);
            }
        } else {
            removeView(imageView);
        }
        this.muteUnMute = null;
        View viewFindViewById2 = findViewById(R.id.controls_grad);
        if (viewFindViewById2 != null) {
            removeView(viewFindViewById2);
        }
        View viewFindViewById3 = findViewById(R.id.controls_fullScreen);
        if (viewFindViewById3 != null) {
            removeView(viewFindViewById3);
        }
    }

    private float getVolumeState() {
        RootControl rootControl = this.rootControl;
        if (rootControl != null) {
            return rootControl.getVolume();
        }
        return 1.0f;
    }

    private void mute() {
        if (this.rootControl != null) {
            this.muteUnMute.setImageResource(R.drawable.volume_off);
            this.rootControl.mute();
        }
    }

    private void unMute() {
        if (this.rootControl != null) {
            this.muteUnMute.setImageResource(R.drawable.volume);
            this.rootControl.unMute();
        }
    }
}
