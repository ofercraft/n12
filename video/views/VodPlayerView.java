package com.channel2.mobile.ui.video.views;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.core.os.BundleKt;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.video.views.VodPlayerView;
import com.mako.kscore.ksplayer.helpers.ActionReport;
import com.mako.kscore.ksplayer.helpers.PlayerCallback;
import com.mako.kscore.ksplayer.helpers.ReasonReport;
import com.mako.kscore.ksplayer.helpers.managers.PlayerReportsManager;
import com.mako.kscore.ksplayer.model.KsPlayItem;
import com.mako.kscore.ksplayer.model.playList.Playlist;
import com.mako.kscore.ksplayer.model.playList.VideoDetails;
import com.mako.kscore.ksplayer.view.KsPlayerView;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.apache.commons.lang3.StringUtils;

/* compiled from: VodPlayerView.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010F\u001a\u00020\u0018J\u0006\u0010G\u001a\u00020\u0018J\u0010\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u0007H\u0002J\u0010\u0010K\u001a\u00020I2\u0006\u0010L\u001a\u00020MH\u0014J\u0010\u0010N\u001a\u00020I2\u0006\u0010O\u001a\u00020PH\u0016J\u0010\u0010Q\u001a\u00020I2\u0006\u0010R\u001a\u00020SH\u0016J\b\u0010T\u001a\u00020IH\u0016J\b\u0010U\u001a\u00020IH\u0016J\u0010\u0010V\u001a\u00020I2\u0006\u0010W\u001a\u00020\u0007H\u0016J(\u0010X\u001a\u00020I2\u0006\u0010Y\u001a\u00020\u00072\u0006\u0010Z\u001a\u00020\u00072\u0006\u0010[\u001a\u00020\u00072\u0006\u0010\\\u001a\u00020\u0007H\u0014J\u0010\u0010]\u001a\u00020I2\u0006\u0010^\u001a\u00020\u0007H\u0016J\u0012\u0010_\u001a\u00020I2\b\u0010`\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010a\u001a\u00020IH\u0002J\u0010\u0010b\u001a\u00020I2\u0006\u0010W\u001a\u00020\u0007H\u0002J\u0006\u0010c\u001a\u00020IR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u0013\u0010 \u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0010R\u0013\u0010\"\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0014R\u0013\u0010$\u001a\u0004\u0018\u00010%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0013\u0010(\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0010R\u0013\u0010*\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0010R\u0013\u0010,\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0014R\u0013\u0010.\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0014R\u0013\u00100\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0014R\u0013\u00102\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0014R\u0013\u00104\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0014R\u0013\u00106\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0014R\u0013\u00108\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0014R\u0013\u0010:\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\fR\u0013\u0010<\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0010R\u0013\u0010>\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\fR\u0013\u0010@\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0014R\u0013\u0010B\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\u0014R\u0013\u0010D\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\f¨\u0006d"}, d2 = {"Lcom/channel2/mobile/ui/video/views/VodPlayerView;", "Lcom/mako/kscore/ksplayer/view/KsPlayerView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "centerButtonsGroup", "Landroidx/constraintlayout/widget/Group;", "getCenterButtonsGroup", "()Landroidx/constraintlayout/widget/Group;", "closeBtn", "Landroid/widget/ImageView;", "getCloseBtn", "()Landroid/widget/ImageView;", TypedValues.TransitionType.S_DURATION, "Landroid/widget/TextView;", "getDuration", "()Landroid/widget/TextView;", "ffwBtn", "getFfwBtn", "maxX", "", "getMaxX", "()F", "setMaxX", "(F)V", "minX", "getMinX", "setMinX", "playPauseBtn", "getPlayPauseBtn", "position", "getPosition", "progressBar", "Landroid/widget/SeekBar;", "getProgressBar", "()Landroid/widget/SeekBar;", "rewBtn", "getRewBtn", "shareBtn", "getShareBtn", "speed05X", "getSpeed05X", "speed0f5X", "getSpeed0f5X", "speed125X", "getSpeed125X", "speed15X", "getSpeed15X", "speed1X", "getSpeed1X", "speed2X", "getSpeed2X", "speedBtn", "getSpeedBtn", "speedsGroup", "getSpeedsGroup", "thumbnail", "getThumbnail", "thumbnailGroup", "getThumbnailGroup", "thumbnailText", "getThumbnailText", "title", "getTitle", "topButtonsGroup", "getTopButtonsGroup", "calculateMaxX", "calculateMinX", "modifyThumbnail", "", "orientation", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onContentPlayingChanged", "isPlaying", "", "onImage", "bitmap", "Landroid/graphics/Bitmap;", "onInitialized", "onPlayerReady", "onProgress", "progress", "onSizeChanged", "w", CmcdHeadersFactory.STREAMING_FORMAT_HLS, "oldw", "oldh", "onVisibilityChanged", "visibility", "proccessSpeed", "v", "proccessSpeedLayout", "setThumbnailPosition", "togglePlayPause", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VodPlayerView extends KsPlayerView {
    private final Group centerButtonsGroup;
    private final ImageView closeBtn;
    private final TextView duration;
    private final ImageView ffwBtn;
    private float maxX;
    private float minX;
    private final ImageView playPauseBtn;
    private final TextView position;
    private final SeekBar progressBar;
    private final ImageView rewBtn;
    private final ImageView shareBtn;
    private final TextView speed05X;
    private final TextView speed0f5X;
    private final TextView speed125X;
    private final TextView speed15X;
    private final TextView speed1X;
    private final TextView speed2X;
    private final TextView speedBtn;
    private final Group speedsGroup;
    private final ImageView thumbnail;
    private final Group thumbnailGroup;
    private final TextView thumbnailText;
    private final TextView title;
    private final Group topButtonsGroup;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VodPlayerView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VodPlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ VodPlayerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VodPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        ImageView imageView = (ImageView) findViewById(R.id.closeBtn);
        this.closeBtn = imageView;
        ImageView imageView2 = (ImageView) findViewById(R.id.shareBtn);
        this.shareBtn = imageView2;
        this.title = (TextView) findViewById(R.id.title);
        ImageView imageView3 = (ImageView) findViewById(R.id.rewBtn);
        this.rewBtn = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R.id.playPauseBtn);
        this.playPauseBtn = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R.id.ffwBtn);
        this.ffwBtn = imageView5;
        this.position = (TextView) findViewById(R.id.position);
        this.duration = (TextView) findViewById(R.id.duration);
        SeekBar seekBar = (SeekBar) findViewById(R.id.progress);
        this.progressBar = seekBar;
        ImageView imageView6 = (ImageView) findViewById(R.id.thumbnail_image);
        this.thumbnail = imageView6;
        TextView textView = (TextView) findViewById(R.id.thumbnail_text);
        this.thumbnailText = textView;
        this.topButtonsGroup = (Group) findViewById(R.id.topButtons);
        this.centerButtonsGroup = (Group) findViewById(R.id.centerButtons);
        this.thumbnailGroup = (Group) findViewById(R.id.thumbnail);
        this.minX = calculateMinX();
        this.maxX = calculateMaxX();
        TextView textView2 = (TextView) findViewById(R.id.speedBtn);
        this.speedBtn = textView2;
        this.speedsGroup = (Group) findViewById(R.id.speed_rates);
        TextView textView3 = (TextView) findViewById(R.id.speed_2x);
        this.speed2X = textView3;
        TextView textView4 = (TextView) findViewById(R.id.speed_1_5x);
        this.speed15X = textView4;
        TextView textView5 = (TextView) findViewById(R.id.speed_1_25x);
        this.speed125X = textView5;
        TextView textView6 = (TextView) findViewById(R.id.speed_1x);
        this.speed1X = textView6;
        TextView textView7 = (TextView) findViewById(R.id.speed_0_75x);
        this.speed0f5X = textView7;
        TextView textView8 = (TextView) findViewById(R.id.speed_0_5x);
        this.speed05X = textView8;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VodPlayerView._init_$lambda$0(this.f$0, view);
                }
            });
        }
        if (imageView2 != null) {
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VodPlayerView._init_$lambda$1(this.f$0, view);
                }
            });
        }
        if (imageView3 != null) {
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VodPlayerView._init_$lambda$2(this.f$0, view);
                }
            });
        }
        if (imageView4 != null) {
            imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VodPlayerView._init_$lambda$3(this.f$0, view);
                }
            });
        }
        if (imageView5 != null) {
            imageView5.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VodPlayerView._init_$lambda$4(this.f$0, view);
                }
            });
        }
        if (imageView6 != null) {
            imageView6.setClipToOutline(true);
        }
        if (textView != null) {
            textView.setClipToOutline(true);
        }
        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(new AnonymousClass6());
        }
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VodPlayerView._init_$lambda$5(this.f$0, view);
                }
            });
        }
        if (textView3 != null) {
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Resources.NotFoundException {
                    VodPlayerView._init_$lambda$6(this.f$0, view);
                }
            });
        }
        if (textView4 != null) {
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Resources.NotFoundException {
                    VodPlayerView._init_$lambda$7(this.f$0, view);
                }
            });
        }
        if (textView5 != null) {
            textView5.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Resources.NotFoundException {
                    VodPlayerView._init_$lambda$8(this.f$0, view);
                }
            });
        }
        if (textView6 != null) {
            textView6.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Resources.NotFoundException {
                    VodPlayerView._init_$lambda$9(this.f$0, view);
                }
            });
        }
        if (textView7 != null) {
            textView7.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Resources.NotFoundException {
                    VodPlayerView._init_$lambda$10(this.f$0, view);
                }
            });
        }
        if (textView8 != null) {
            textView8.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Resources.NotFoundException {
                    VodPlayerView._init_$lambda$11(this.f$0, view);
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

    public final TextView getPosition() {
        return this.position;
    }

    public final TextView getDuration() {
        return this.duration;
    }

    public final SeekBar getProgressBar() {
        return this.progressBar;
    }

    public final ImageView getThumbnail() {
        return this.thumbnail;
    }

    public final TextView getThumbnailText() {
        return this.thumbnailText;
    }

    public final Group getTopButtonsGroup() {
        return this.topButtonsGroup;
    }

    public final Group getCenterButtonsGroup() {
        return this.centerButtonsGroup;
    }

    public final Group getThumbnailGroup() {
        return this.thumbnailGroup;
    }

    public final float getMinX() {
        return this.minX;
    }

    public final void setMinX(float f) {
        this.minX = f;
    }

    public final float getMaxX() {
        return this.maxX;
    }

    public final void setMaxX(float f) {
        this.maxX = f;
    }

    public final TextView getSpeedBtn() {
        return this.speedBtn;
    }

    public final Group getSpeedsGroup() {
        return this.speedsGroup;
    }

    public final TextView getSpeed2X() {
        return this.speed2X;
    }

    public final TextView getSpeed15X() {
        return this.speed15X;
    }

    public final TextView getSpeed125X() {
        return this.speed125X;
    }

    public final TextView getSpeed1X() {
        return this.speed1X;
    }

    public final TextView getSpeed0f5X() {
        return this.speed0f5X;
    }

    public final TextView getSpeed05X() {
        return this.speed05X;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(VodPlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PlayerCallback playerCallback = this$0.getPlayerCallback();
        if (playerCallback != null) {
            playerCallback.onPlayerClick(BundleKt.bundleOf(new Pair("closeBtn", null)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(VodPlayerView this$0, View view) {
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
    public static final void _init_$lambda$2(VodPlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        KsPlayerView.jump$default(this$0, -10, null, null, 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$3(VodPlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.togglePlayPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$4(VodPlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        KsPlayerView.jump$default(this$0, 10, null, null, 6, null);
    }

    /* compiled from: VodPlayerView.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0017J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"com/channel2/mobile/ui/video/views/VodPlayerView$6", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "onProgressChanged", "", "seekBar", "Landroid/widget/SeekBar;", "progress", "", "fromUser", "", "onStartTrackingTouch", "onStopTrackingTouch", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: com.channel2.mobile.ui.video.views.VodPlayerView$6, reason: invalid class name */
    public static final class AnonymousClass6 implements SeekBar.OnSeekBarChangeListener {
        AnonymousClass6() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Intrinsics.checkNotNullParameter(seekBar, "seekBar");
            Log.d("checkProgress", "onProgressChanged: last = " + seekBar.getProgress() + " | new = " + progress);
            if (fromUser) {
                VodPlayerView vodPlayerView = VodPlayerView.this;
                long j = progress;
                vodPlayerView.getThumbnail(j, vodPlayerView.getKsContentPosition() < j);
                VodPlayerView.this.setThumbnailPosition(progress);
                VodPlayerView.this.getThumbnail(j, true);
                TextView thumbnailText = VodPlayerView.this.getThumbnailText();
                if (thumbnailText == null) {
                    return;
                }
                thumbnailText.setText(Utils.msToTimeString_HH_mm_ss(j));
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(final SeekBar seekBar) {
            Intrinsics.checkNotNullParameter(seekBar, "seekBar");
            KsPlayerView.pause$default(VodPlayerView.this, null, ReasonReport.dvr, 1, null);
            Group centerButtonsGroup = VodPlayerView.this.getCenterButtonsGroup();
            if (centerButtonsGroup != null) {
                centerButtonsGroup.setVisibility(4);
            }
            Group topButtonsGroup = VodPlayerView.this.getTopButtonsGroup();
            if (topButtonsGroup != null) {
                topButtonsGroup.setVisibility(4);
            }
            VodPlayerView vodPlayerView = VodPlayerView.this;
            vodPlayerView.setMinX(vodPlayerView.calculateMinX());
            VodPlayerView vodPlayerView2 = VodPlayerView.this;
            vodPlayerView2.setMaxX(vodPlayerView2.calculateMaxX());
            Group thumbnailGroup = VodPlayerView.this.getThumbnailGroup();
            if (thumbnailGroup != null) {
                thumbnailGroup.setVisibility(0);
            }
            Group thumbnailGroup2 = VodPlayerView.this.getThumbnailGroup();
            if (thumbnailGroup2 != null) {
                final VodPlayerView vodPlayerView3 = VodPlayerView.this;
                thumbnailGroup2.post(new Runnable() { // from class: com.channel2.mobile.ui.video.views.VodPlayerView$6$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        VodPlayerView.AnonymousClass6.onStartTrackingTouch$lambda$0(vodPlayerView3, seekBar);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onStartTrackingTouch$lambda$0(VodPlayerView this$0, SeekBar seekBar) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(seekBar, "$seekBar");
            this$0.setThumbnailPosition(seekBar.getProgress());
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            Intrinsics.checkNotNullParameter(seekBar, "seekBar");
            KsPlayerView.ksSeekTo$default(VodPlayerView.this, seekBar.getProgress(), false, null, null, 14, null);
            Group thumbnailGroup = VodPlayerView.this.getThumbnailGroup();
            if (thumbnailGroup == null) {
                return;
            }
            thumbnailGroup.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$5(VodPlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.proccessSpeedLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$6(VodPlayerView this$0, View view) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.proccessSpeed(view instanceof TextView ? (TextView) view : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$7(VodPlayerView this$0, View view) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.proccessSpeed(view instanceof TextView ? (TextView) view : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$8(VodPlayerView this$0, View view) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.proccessSpeed(view instanceof TextView ? (TextView) view : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$9(VodPlayerView this$0, View view) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.proccessSpeed(view instanceof TextView ? (TextView) view : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$10(VodPlayerView this$0, View view) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.proccessSpeed(view instanceof TextView ? (TextView) view : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$11(VodPlayerView this$0, View view) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.proccessSpeed(view instanceof TextView ? (TextView) view : null);
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
        seekBar.setMax((int) getContentDuration());
        KsPlayItem ksPlayItem = getKsPlayItem();
        int seekTo = ksPlayItem != null ? (int) ksPlayItem.getSeekTo() : 0;
        seekBar.setProgress(seekTo);
        TextView textView = this.position;
        if (textView != null) {
            textView.setText(Utils.msToTimeString_HH_mm_ss(seekTo));
        }
        TextView textView2 = this.duration;
        if (textView2 != null) {
            textView2.setText(Utils.msToTimeString_HH_mm_ss(getContentDuration()));
        }
        setThumbnailPosition(seekTo);
        TextView textView3 = this.title;
        if (textView3 == null) {
            return;
        }
        Playlist playlist = getPlaylist();
        if (playlist == null || (videoDetails = playlist.getVideoDetails()) == null || (episodeName = videoDetails.getEpisodeName()) == null) {
            episodeName = "";
        }
        textView3.setText(episodeName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setThumbnailPosition(int progress) {
        ImageView imageView;
        SeekBar seekBar = this.progressBar;
        if (seekBar == null || (imageView = this.thumbnail) == null) {
            return;
        }
        float fCoerceIn = RangesKt.coerceIn((((getScrubberPosition(seekBar, progress, seekBar.getMax()) + seekBar.getLeft()) + getPaddingLeft()) + Utils.convertDipToPixels(getContext(), 16.0f)) - (imageView.getWidth() / 2), this.minX, this.maxX);
        imageView.setX(fCoerceIn);
        TextView textView = this.thumbnailText;
        if (textView == null) {
            return;
        }
        textView.setX(fCoerceIn);
    }

    @Override // com.mako.kscore.ksplayer.view.KsPlayerView
    public void onProgress(int progress) {
        super.onProgress(progress);
        SeekBar seekBar = this.progressBar;
        if (seekBar != null) {
            seekBar.setProgress(progress);
        }
        TextView textView = this.position;
        if (textView != null) {
            textView.setText(Utils.msToTimeString_HH_mm_ss(progress));
        }
        setThumbnailPosition(progress);
    }

    public final float calculateMinX() {
        return getControlsContainer().getChildAt(0).getPaddingStart();
    }

    public final float calculateMaxX() {
        int right = getRight();
        float paddingEnd = getControlsContainer().getChildAt(0).getPaddingEnd();
        ImageView imageView = this.thumbnail;
        int width = imageView != null ? imageView.getWidth() : 0;
        Log.d("whatTheMax", "calculateMaxX: right = " + right + " | padding = " + paddingEnd + " | width = " + width + " | minX = " + this.minX);
        return Math.max((((float) getRight()) - ((float) getControlsContainer().getChildAt(0).getPaddingEnd())) - (this.thumbnail != null ? r1.getWidth() : 0), this.minX);
    }

    private final void modifyThumbnail(int orientation) {
        View childAt = getControlsContainer().getChildAt(0);
        ConstraintLayout constraintLayout = childAt instanceof ConstraintLayout ? (ConstraintLayout) childAt : null;
        if (constraintLayout == null || this.thumbnail == null || this.progressBar == null || this.thumbnailText == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.setDimensionRatio(this.thumbnail.getId(), "16:9");
        constraintSet.connect(this.thumbnail.getId(), 4, this.progressBar.getId(), 3, Utils.convertDipToPixels(getContext(), 35.0f));
        constraintSet.connect(this.thumbnail.getId(), 6, constraintLayout.getId(), 6);
        constraintSet.constrainHeight(this.thumbnailText.getId(), Utils.convertDipToPixels(getContext(), 22.0f));
        constraintSet.connect(this.thumbnailText.getId(), 6, this.thumbnail.getId(), 6);
        constraintSet.connect(this.thumbnailText.getId(), 7, this.thumbnail.getId(), 7);
        constraintSet.connect(this.thumbnailText.getId(), 3, this.thumbnail.getId(), 4);
        if (orientation == 1) {
            constraintSet.constrainPercentWidth(this.thumbnail.getId(), 0.35f);
        } else if (orientation == 2) {
            constraintSet.constrainPercentHeight(this.thumbnail.getId(), 0.33f);
        }
        constraintSet.applyTo(constraintLayout);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        Log.d("checkConfig", "onConfigurationChanged: newConfig = " + newConfig);
        modifyThumbnail(newConfig.orientation);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("checkConfig", "onSizeChanged: ");
    }

    @Override // com.mako.kscore.ksplayer.view.KsPlayerView, com.mako.kscore.ksplayer.helpers.ThumbnailManagerCallbacks
    public void onImage(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        ImageView imageView = this.thumbnail;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
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
    }

    private final void proccessSpeed(TextView v) throws Resources.NotFoundException {
        float f;
        if (v != null) {
            switch (v.getId()) {
                case R.id.speed_0_5x /* 2131428454 */:
                    f = 0.5f;
                    break;
                case R.id.speed_0_75x /* 2131428455 */:
                    f = 0.75f;
                    break;
                case R.id.speed_1_25x /* 2131428456 */:
                    f = 1.25f;
                    break;
                case R.id.speed_1_5x /* 2131428457 */:
                    f = 1.5f;
                    break;
                case R.id.speed_1x /* 2131428458 */:
                default:
                    f = 1.0f;
                    break;
                case R.id.speed_2x /* 2131428459 */:
                    f = 2.0f;
                    break;
            }
            setSpeed(f);
            TextView textView = this.speedBtn;
            if (textView != null) {
                String text = v.getText();
                if (text == null) {
                    text = getResources().getString(R.string.speed_1x);
                }
                textView.setText(text);
            }
            Group group = this.speedsGroup;
            if (group == null) {
                return;
            }
            group.setVisibility(8);
        }
    }

    private final void proccessSpeedLayout() {
        Group group = this.speedsGroup;
        if (group != null) {
            group.setVisibility(4);
        }
        TextView textView = this.speed2X;
        if (textView != null) {
            proccessSpeedLayout$setBG(textView, this);
        }
        TextView textView2 = this.speed15X;
        if (textView2 != null) {
            proccessSpeedLayout$setBG(textView2, this);
        }
        TextView textView3 = this.speed125X;
        if (textView3 != null) {
            proccessSpeedLayout$setBG(textView3, this);
        }
        TextView textView4 = this.speed1X;
        if (textView4 != null) {
            proccessSpeedLayout$setBG(textView4, this);
        }
        TextView textView5 = this.speed0f5X;
        if (textView5 != null) {
            proccessSpeedLayout$setBG(textView5, this);
        }
        TextView textView6 = this.speed05X;
        if (textView6 != null) {
            proccessSpeedLayout$setBG(textView6, this);
        }
        Group group2 = this.speedsGroup;
        if (group2 != null) {
            group2.setVisibility(0);
        }
        Group group3 = this.speedsGroup;
        if (group3 != null) {
            group3.requestFocus();
        }
    }

    private static final void proccessSpeedLayout$setBG(TextView textView, VodPlayerView vodPlayerView) {
        CharSequence text = textView.getText();
        TextView textView2 = vodPlayerView.speedBtn;
        Log.d("speedBG", "this text = " + ((Object) text) + " | speed text = " + ((Object) (textView2 != null ? textView2.getText() : null)) + StringUtils.SPACE);
        Context context = textView.getContext();
        TextView textView3 = vodPlayerView.speedBtn;
        textView.setBackgroundColor(ContextCompat.getColor(context, Intrinsics.areEqual(textView3 != null ? textView3.getText() : null, textView.getText()) ? R.color.colorRedSpeed : R.color.black0C0C0C));
    }

    @Override // com.mako.kscore.ksplayer.view.KsPlayerView, androidx.media3.ui.PlayerView.ControllerVisibilityListener
    public void onVisibilityChanged(int visibility) {
        Group group;
        super.onVisibilityChanged(visibility);
        Log.d("checkVisibility", "onVisibilityChanged: " + visibility);
        if (visibility == 0 || (group = this.speedsGroup) == null) {
            return;
        }
        group.setVisibility(8);
    }
}
