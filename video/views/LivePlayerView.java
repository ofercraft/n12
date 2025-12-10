package com.channel2.mobile.ui.video.views;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.core.os.BundleKt;
import androidx.media3.common.C;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.helpers.Utils;
import com.mako.kscore.ksplayer.helpers.ActionReport;
import com.mako.kscore.ksplayer.helpers.PlayerCallback;
import com.mako.kscore.ksplayer.helpers.ReasonReport;
import com.mako.kscore.ksplayer.helpers.managers.PlayerReportsManager;
import com.mako.kscore.ksplayer.model.playList.Playlist;
import com.mako.kscore.ksplayer.model.playList.VideoDetails;
import com.mako.kscore.ksplayer.view.KsPlayerView;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LivePlayerView.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\r\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010+\u001a\u00020,J\u0010\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020/H\u0014J\u0010\u00100\u001a\u00020,2\u0006\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u00020,H\u0016J\b\u00104\u001a\u00020,H\u0016J\u0010\u00105\u001a\u00020,2\u0006\u00106\u001a\u000207H\u0016J(\u00108\u001a\u00020,2\u0006\u00109\u001a\u00020\u00072\u0006\u0010:\u001a\u00020\u00072\u0006\u0010;\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\u0007H\u0014J\u0010\u0010=\u001a\u00020,2\u0006\u0010>\u001a\u00020\u0007H\u0016J\u0006\u0010?\u001a\u00020,J\u0010\u0010@\u001a\u00020,2\u0006\u00106\u001a\u00020\u0007H\u0002J\u0006\u0010A\u001a\u00020,J\n\u0010B\u001a\u000202*\u00020\u0007J\n\u0010C\u001a\u000202*\u00020\u0007R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010!\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014R\u0013\u0010#\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0014R\u0013\u0010%\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\fR\u0013\u0010'\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\fR\u0013\u0010)\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0010¨\u0006D"}, d2 = {"Lcom/channel2/mobile/ui/video/views/LivePlayerView;", "Lcom/mako/kscore/ksplayer/view/KsPlayerView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "backToLive", "Landroid/widget/TextView;", "getBackToLive", "()Landroid/widget/TextView;", "centerButtonsGroup", "Landroidx/constraintlayout/widget/Group;", "getCenterButtonsGroup", "()Landroidx/constraintlayout/widget/Group;", "closeBtn", "Landroid/widget/ImageView;", "getCloseBtn", "()Landroid/widget/ImageView;", "dvrStart", "getDvrStart", "ffwBtn", "getFfwBtn", "pipButton", "getPipButton", "playPauseBtn", "getPlayPauseBtn", "progressBar", "Landroid/widget/SeekBar;", "getProgressBar", "()Landroid/widget/SeekBar;", "rewBtn", "getRewBtn", "shareBtn", "getShareBtn", "thumbnailText", "getThumbnailText", "title", "getTitle", "topButtonsGroup", "getTopButtonsGroup", "noDvr", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onContentPlayingChanged", "isPlaying", "", "onInitialized", "onPlayerReady", "onProgress", "progress", "", "onSizeChanged", "w", CmcdHeadersFactory.STREAMING_FORMAT_HLS, "oldw", "oldh", "onVisibilityChanged", "visibility", "seekToLive", "setThumbnailPosition", "togglePlayPause", "isDVR", "isNotDVR", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivePlayerView extends KsPlayerView {
    private final TextView backToLive;
    private final Group centerButtonsGroup;
    private final ImageView closeBtn;
    private final TextView dvrStart;
    private final ImageView ffwBtn;
    private final ImageView pipButton;
    private final ImageView playPauseBtn;
    private final SeekBar progressBar;
    private final ImageView rewBtn;
    private final ImageView shareBtn;
    private final TextView thumbnailText;
    private final TextView title;
    private final Group topButtonsGroup;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LivePlayerView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LivePlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final boolean isDVR(int i) {
        return i > 300000;
    }

    public /* synthetic */ LivePlayerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LivePlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        ImageView imageView = (ImageView) findViewById(R.id.closeBtn);
        this.closeBtn = imageView;
        ImageView imageView2 = (ImageView) findViewById(R.id.shareBtn);
        this.shareBtn = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R.id.pip_button);
        this.pipButton = imageView3;
        this.title = (TextView) findViewById(R.id.title);
        ImageView imageView4 = (ImageView) findViewById(R.id.rewBtn);
        this.rewBtn = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R.id.playPauseBtn);
        this.playPauseBtn = imageView5;
        ImageView imageView6 = (ImageView) findViewById(R.id.ffwBtn);
        this.ffwBtn = imageView6;
        this.dvrStart = (TextView) findViewById(R.id.position);
        SeekBar seekBar = (SeekBar) findViewById(R.id.progress);
        this.progressBar = seekBar;
        TextView textView = (TextView) findViewById(R.id.thumbnail_text);
        this.thumbnailText = textView;
        TextView textView2 = (TextView) findViewById(R.id.backToLive);
        this.backToLive = textView2;
        this.topButtonsGroup = (Group) findViewById(R.id.topButtons);
        this.centerButtonsGroup = (Group) findViewById(R.id.centerButtons);
        if (imageView3 != null) {
            imageView3.setVisibility(MainConfig.main.getCurrentBooleanParam("pipEnable") ? 0 : 8);
        }
        if (imageView3 != null) {
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.LivePlayerView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LivePlayerView._init_$lambda$0(this.f$0, view);
                }
            });
        }
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.LivePlayerView$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LivePlayerView._init_$lambda$1(this.f$0, view);
                }
            });
        }
        if (imageView2 != null) {
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.LivePlayerView$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LivePlayerView._init_$lambda$2(this.f$0, view);
                }
            });
        }
        if (imageView4 != null) {
            imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.LivePlayerView$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LivePlayerView._init_$lambda$4(this.f$0, view);
                }
            });
        }
        if (imageView5 != null) {
            imageView5.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.LivePlayerView$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LivePlayerView._init_$lambda$5(this.f$0, view);
                }
            });
        }
        if (imageView6 != null) {
            imageView6.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.LivePlayerView$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LivePlayerView._init_$lambda$7(this.f$0, view);
                }
            });
        }
        if (textView != null) {
            textView.setClipToOutline(true);
        }
        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.channel2.mobile.ui.video.views.LivePlayerView.7
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                    Intrinsics.checkNotNullParameter(seekBar2, "seekBar");
                    Log.d("checkProgress", "onProgressChanged: progress = " + progress + " | max = " + seekBar2.getMax());
                    TextView thumbnailText = LivePlayerView.this.getThumbnailText();
                    if (thumbnailText != null) {
                        thumbnailText.setText(Utils.utcToTimeString_HH_mm(LivePlayerView.this.getDvrStartTimeUTC() + progress));
                    }
                    LivePlayerView.this.setThumbnailPosition(progress);
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar2) {
                    Intrinsics.checkNotNullParameter(seekBar2, "seekBar");
                    KsPlayerView.pause$default(LivePlayerView.this, null, ReasonReport.dvr, 1, null);
                    Group centerButtonsGroup = LivePlayerView.this.getCenterButtonsGroup();
                    if (centerButtonsGroup != null) {
                        centerButtonsGroup.setVisibility(4);
                    }
                    Group topButtonsGroup = LivePlayerView.this.getTopButtonsGroup();
                    if (topButtonsGroup == null) {
                        return;
                    }
                    topButtonsGroup.setVisibility(4);
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar2) {
                    Intrinsics.checkNotNullParameter(seekBar2, "seekBar");
                    KsPlayerView.ksSeekTo$default(LivePlayerView.this, seekBar2.getProgress(), false, null, null, 14, null);
                }
            });
        }
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.LivePlayerView$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LivePlayerView._init_$lambda$8(this.f$0, view);
                }
            });
        }
    }

    public final ImageView getCloseBtn() {
        return this.closeBtn;
    }

    public final ImageView getShareBtn() {
        return this.shareBtn;
    }

    public final ImageView getPipButton() {
        return this.pipButton;
    }

    public final TextView getTitle() {
        return this.title;
    }

    public final ImageView getRewBtn() {
        return this.rewBtn;
    }

    public final ImageView getPlayPauseBtn() {
        return this.playPauseBtn;
    }

    public final ImageView getFfwBtn() {
        return this.ffwBtn;
    }

    public final TextView getDvrStart() {
        return this.dvrStart;
    }

    public final SeekBar getProgressBar() {
        return this.progressBar;
    }

    public final TextView getThumbnailText() {
        return this.thumbnailText;
    }

    public final TextView getBackToLive() {
        return this.backToLive;
    }

    public final Group getTopButtonsGroup() {
        return this.topButtonsGroup;
    }

    public final Group getCenterButtonsGroup() {
        return this.centerButtonsGroup;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(LivePlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PlayerCallback playerCallback = this$0.getPlayerCallback();
        if (playerCallback != null) {
            playerCallback.onPlayerClick(BundleKt.bundleOf(new Pair("enterPip", null)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(LivePlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PlayerCallback playerCallback = this$0.getPlayerCallback();
        if (playerCallback != null) {
            playerCallback.onPlayerClick(BundleKt.bundleOf(new Pair("closeBtn", null)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(LivePlayerView this$0, View view) {
        String directLink;
        VideoDetails videoDetails;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.pause(ActionReport.pause, ReasonReport.share);
        PlayerCallback playerCallback = this$0.getPlayerCallback();
        if (playerCallback != null) {
            Pair[] pairArr = new Pair[1];
            Playlist playlist = this$0.getPlaylist();
            if (playlist == null || (videoDetails = playlist.getVideoDetails()) == null || (directLink = videoDetails.getDirectLink()) == null) {
                directLink = "";
            }
            pairArr[0] = new Pair("shareBtn", directLink);
            playerCallback.onPlayerClick(BundleKt.bundleOf(pairArr));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$4(LivePlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SeekBar seekBar = this$0.progressBar;
        if (seekBar != null) {
            seekBar.setProgress(seekBar.getProgress() - 10000);
        }
        KsPlayerView.jump$default(this$0, -10, null, null, 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$5(LivePlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.togglePlayPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$7(LivePlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SeekBar seekBar = this$0.progressBar;
        if (seekBar != null) {
            seekBar.setProgress(seekBar.getProgress() + 10000);
        }
        KsPlayerView.jump$default(this$0, 10, null, null, 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$8(LivePlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.seekToLive();
    }

    @Override // com.mako.kscore.ksplayer.view.KsPlayerView
    public void onInitialized() {
        super.onInitialized();
    }

    @Override // com.mako.kscore.ksplayer.view.KsPlayerView
    public void onPlayerReady() {
        String episodeName;
        VideoDetails videoDetails;
        super.onPlayerReady();
        SeekBar seekBar = this.progressBar;
        if (seekBar == null || seekBar.getMax() != 100 || getContentDuration() <= 0) {
            return;
        }
        int dvrEndTimeUTC = (int) (getDvrEndTimeUTC() - getDvrStartTimeUTC());
        Log.d("checkNoDVR", "onPlayerReady: dvrStartTimeUTC = " + getDvrStartTimeUTC() + " | dvr dur = " + dvrEndTimeUTC + " | threshold = 300000 | isDVR = " + isDVR(dvrEndTimeUTC));
        if (getDvrStartTimeUTC() == 0 || isNotDVR(dvrEndTimeUTC)) {
            noDvr();
        } else {
            seekBar.setMax(dvrEndTimeUTC);
            seekBar.setProgress(seekBar.getMax());
            TextView textView = this.dvrStart;
            if (textView != null) {
                textView.setText(Utils.utcToTimeString_HH_mm(getDvrStartTimeUTC()));
            }
        }
        TextView textView2 = this.title;
        if (textView2 == null) {
            return;
        }
        Playlist playlist = getPlaylist();
        if (playlist == null || (videoDetails = playlist.getVideoDetails()) == null || (episodeName = videoDetails.getEpisodeName()) == null) {
            episodeName = "";
        }
        textView2.setText(episodeName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setThumbnailPosition(int progress) {
        TextView textView;
        SeekBar seekBar = this.progressBar;
        if (seekBar == null || (textView = this.thumbnailText) == null) {
            return;
        }
        textView.setX((((getScrubberPosition(seekBar, progress, seekBar.getMax()) + seekBar.getLeft()) + getPaddingLeft()) + Utils.convertDipToPixels(getContext(), 16.0f)) - (textView.getWidth() / 2));
    }

    @Override // com.mako.kscore.ksplayer.view.KsPlayerView
    public void onProgress(long progress) {
        super.onProgress(progress);
        Log.d("investigateTCL", "onProgress: dvrStartTimeUTC = " + getDvrStartTimeUTC() + " | dvrEndTimeUTC = " + getDvrEndTimeUTC() + " | duartion = " + Utils.msToTimeString_HH_mm_ss(getDvrEndTimeUTC() - getDvrStartTimeUTC()));
        TextView textView = this.dvrStart;
        if (textView != null) {
            textView.setText(Utils.utcToTimeString_HH_mm(getDvrStartTimeUTC()));
        }
        TextView textView2 = this.thumbnailText;
        if (textView2 != null) {
            textView2.setText(Utils.utcToTimeString_HH_mm(progress));
        }
        TextView textView3 = this.backToLive;
        if (textView3 != null) {
            TextView textView4 = textView3;
            SeekBar seekBar = this.progressBar;
            int max = seekBar != null ? seekBar.getMax() : 0;
            SeekBar seekBar2 = this.progressBar;
            textView4.setVisibility(max - (seekBar2 != null ? seekBar2.getProgress() : 0) >= 60000 ? 0 : 8);
        }
        ImageView imageView = this.ffwBtn;
        if (imageView == null) {
            return;
        }
        ImageView imageView2 = imageView;
        SeekBar seekBar3 = this.progressBar;
        int max2 = seekBar3 != null ? seekBar3.getMax() : 0;
        SeekBar seekBar4 = this.progressBar;
        imageView2.setVisibility(max2 - (seekBar4 != null ? seekBar4.getProgress() : 0) <= 10000 ? 4 : 0);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        TextView textView = this.thumbnailText;
        if (textView != null) {
            textView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.channel2.mobile.ui.video.views.LivePlayerView.onSizeChanged.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    Log.d("checkThumbnailText", "width = " + v.getWidth() + " | left = " + left + " |  oldLeft = " + oldLeft + " | right = " + right + " | oldRight = " + oldRight);
                    if (left <= 0 || left != oldLeft || right <= 0 || right != oldRight) {
                        return;
                    }
                    LivePlayerView.this.getThumbnailText().removeOnLayoutChangeListener(this);
                    SeekBar progressBar = LivePlayerView.this.getProgressBar();
                    if (progressBar != null) {
                        LivePlayerView.this.setThumbnailPosition(progressBar.getProgress());
                    }
                    v.setVisibility(0);
                }
            });
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        TextView textView = this.thumbnailText;
        Log.d("checkThumbnailText", "onConfigurationChanged width = " + (textView != null ? Integer.valueOf(textView.getWidth()) : null));
    }

    public final void togglePlayPause() {
        if (isPlaying()) {
            pause(ActionReport.pause, ReasonReport.user);
        } else {
            play(ReasonReport.user);
        }
    }

    @Override // com.mako.kscore.ksplayer.view.KsPlayerView
    public void onContentPlayingChanged(boolean isPlaying) {
        ImageView imageView;
        super.onContentPlayingChanged(isPlaying);
        ActionReport action = PlayerReportsManager.INSTANCE.getAction();
        ActionReport actionReport = ActionReport.SkipForward;
        int i = R.drawable.controls_pause;
        if (action != actionReport && PlayerReportsManager.INSTANCE.getAction() != ActionReport.SkipBackward) {
            ImageView imageView2 = this.playPauseBtn;
            if (imageView2 != null) {
                if (!isPlaying) {
                    i = R.drawable.controls_play;
                }
                imageView2.setImageResource(i);
            }
        } else if (isPlaying && (imageView = this.playPauseBtn) != null) {
            imageView.setImageResource(R.drawable.controls_pause);
        }
        if (isPlaying) {
            Group group = this.centerButtonsGroup;
            if (group != null) {
                Group group2 = group;
                if (group2.getVisibility() == 4) {
                    group2.setVisibility(0);
                }
            }
            Group group3 = this.topButtonsGroup;
            if (group3 != null) {
                Group group4 = group3;
                if (group4.getVisibility() == 4) {
                    group4.setVisibility(0);
                }
            }
        }
        ImageView imageView3 = this.pipButton;
        if (imageView3 == null) {
            return;
        }
        imageView3.setEnabled(isPlaying);
    }

    public final void seekToLive() {
        ksSeekTo(C.TIME_UNSET, false, ActionReport.live_rt, ReasonReport.none);
        SeekBar seekBar = this.progressBar;
        if (seekBar != null) {
            seekBar.setProgress(seekBar.getMax());
        }
        TextView textView = this.backToLive;
        if (textView == null) {
            return;
        }
        textView.setVisibility(8);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x002b  */
    @Override // com.mako.kscore.ksplayer.view.KsPlayerView, androidx.media3.ui.PlayerView.ControllerVisibilityListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onVisibilityChanged(int r3) {
        /*
            r2 = this;
            super.onVisibilityChanged(r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "onVisibilityChanged: "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "checkVisibility"
            android.util.Log.d(r1, r0)
            r0 = 8
            if (r3 != r0) goto L38
            android.widget.SeekBar r3 = r2.progressBar
            r0 = 0
            if (r3 == 0) goto L2b
            int r3 = r3.getMax()
            boolean r3 = r2.isDVR(r3)
            r1 = 1
            if (r3 != r1) goto L2b
            goto L2c
        L2b:
            r1 = r0
        L2c:
            if (r1 == 0) goto L38
            android.widget.TextView r3 = r2.thumbnailText
            if (r3 != 0) goto L33
            goto L38
        L33:
            android.view.View r3 = (android.view.View) r3
            r3.setVisibility(r0)
        L38:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.channel2.mobile.ui.video.views.LivePlayerView.onVisibilityChanged(int):void");
    }

    public final void noDvr() {
        SeekBar seekBar = this.progressBar;
        if (seekBar != null) {
            seekBar.setVisibility(8);
        }
        TextView textView = this.thumbnailText;
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView2 = this.backToLive;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
        ImageView imageView = this.rewBtn;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        ImageView imageView2 = this.ffwBtn;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        TextView textView3 = this.dvrStart;
        if (textView3 == null) {
            return;
        }
        textView3.setVisibility(8);
    }

    public final boolean isNotDVR(int i) {
        return !isDVR(i);
    }
}
