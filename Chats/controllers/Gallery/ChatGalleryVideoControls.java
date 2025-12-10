package com.channel2.mobile.ui.Chats.controllers.Gallery;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.helpers.Utils;
import com.mako.kscore.ksplayer.helpers.ActionReport;
import com.mako.kscore.ksplayer.helpers.ReasonReport;
import com.mako.kscore.ksplayer.view.RootControl;

/* loaded from: classes2.dex */
public class ChatGalleryVideoControls {
    private final TextView duration;
    private final ImageView muteUnMute;
    private final ImageView playPause;
    private final TextView position;
    private final RootControl rootControl;
    private final SeekBar seekBar;
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryVideoControls.1
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            ChatGalleryVideoControls.this.updateUi(i);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            ChatGalleryVideoControls.this.rootControl.pause(ActionReport.none, ReasonReport.none);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            ChatGalleryVideoControls.this.rootControl.ksSeekTo(seekBar.getProgress(), false);
        }
    };

    public ChatGalleryVideoControls(final RootControl rootControl, ViewGroup viewGroup) {
        this.rootControl = rootControl;
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.muteUnMute);
        this.muteUnMute = imageView;
        this.position = (TextView) viewGroup.findViewById(R.id.position);
        this.duration = (TextView) viewGroup.findViewById(R.id.duration);
        SeekBar seekBar = (SeekBar) viewGroup.findViewById(R.id.seekbar);
        this.seekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(this.seekBarChangeListener);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryVideoControls$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0(rootControl, view);
            }
        });
        ImageView imageView2 = (ImageView) viewGroup.findViewById(R.id.playPause);
        this.playPause = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryVideoControls$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$1(rootControl, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(RootControl rootControl, View view) {
        if (rootControl.getVolume() == 0.0f) {
            unMute();
        } else {
            mute();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(RootControl rootControl, View view) {
        if (rootControl.isPlaying()) {
            pause();
        } else {
            unPause();
        }
    }

    public void init() {
        this.seekBar.setMax(this.rootControl.getKsContentDuration());
        this.duration.setText(Utils.msToTimeString_mm_ss(this.rootControl.getContentDuration()));
    }

    public void mute() {
        if (this.rootControl.getPlayerExists()) {
            this.muteUnMute.setImageResource(R.drawable.volume_off);
            this.rootControl.mute();
        }
    }

    private void unMute() {
        if (this.rootControl.getPlayerExists()) {
            this.muteUnMute.setImageResource(R.drawable.volume);
            this.rootControl.unMute();
        }
    }

    public void unPause() {
        if (this.rootControl.getPlayerExists()) {
            this.playPause.setImageResource(R.drawable.news12_pause);
            this.rootControl.play(ActionReport.none, ReasonReport.none);
        }
    }

    private void pause() {
        if (this.rootControl.getPlayerExists()) {
            this.playPause.setImageResource(R.drawable.news12_play);
            this.rootControl.pause(ActionReport.none, ReasonReport.none);
        }
    }

    public void updateUi(int i) {
        this.position.setText(Utils.msToTimeString_mm_ss(i));
    }

    public void resetSeekBar() {
        SeekBar seekBar = this.seekBar;
        if (seekBar != null) {
            seekBar.setProgress(0);
        }
    }

    public void onProgress(int i) {
        this.seekBar.setProgress(i);
        updateUi(i);
    }
}
