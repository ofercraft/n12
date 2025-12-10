package com.channel2.mobile.ui.video.fragments;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.splash.MyApplication;
import com.channel2.mobile.ui.video.PlayerFragmentCallbacks;
import com.mako.kscore.helpers.MessageType;
import com.mako.kscore.helpers.PiPSupportCallback;
import com.mako.kscore.helpers.Utils;
import com.mako.kscore.ksmeasurements.helpers.PlayerState;
import com.mako.kscore.ksplayer.controller.CastSupportFragment;
import com.mako.kscore.ksplayer.controller.KsApplication;
import com.mako.kscore.ksplayer.helpers.ActionReport;
import com.mako.kscore.ksplayer.helpers.KillPlayerListener;
import com.mako.kscore.ksplayer.helpers.PlayerCastState;
import com.mako.kscore.ksplayer.helpers.ReasonReport;
import com.mako.kscore.ksplayer.helpers.managers.CastManager;
import com.mako.kscore.ksplayer.helpers.managers.KsPlayerManager;
import com.mako.kscore.ksplayer.model.KsPlayItem;
import com.mako.kscore.ksplayer.model.measurments.PlayerPageParams;
import com.mako.kscore.ksplayer.view.RootControl;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.Token;

/* compiled from: PlayerFragment.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020)H\u0016J&\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\u0018\u00102\u001a\u00020$2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020)H\u0016J\u0010\u00106\u001a\u00020$2\u0006\u00107\u001a\u000201H\u0016J\u0010\u00108\u001a\u00020$2\u0006\u00109\u001a\u00020)H\u0016J\b\u0010:\u001a\u00020$H\u0016J\b\u0010;\u001a\u00020$H\u0016J\b\u0010<\u001a\u00020$H\u0016J\u000e\u0010=\u001a\u00020$2\u0006\u00103\u001a\u000204J\u000e\u0010>\u001a\u00020$2\u0006\u0010?\u001a\u00020@R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0010\u0010\fR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010 \u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u000e\u001a\u0004\b!\u0010\f¨\u0006A"}, d2 = {"Lcom/channel2/mobile/ui/video/fragments/PlayerFragment;", "Lcom/mako/kscore/ksplayer/controller/CastSupportFragment;", "()V", "callbacks", "Lcom/channel2/mobile/ui/video/PlayerFragmentCallbacks;", "getCallbacks", "()Lcom/channel2/mobile/ui/video/PlayerFragmentCallbacks;", "setCallbacks", "(Lcom/channel2/mobile/ui/video/PlayerFragmentCallbacks;)V", "channelId", "", "getChannelId", "()Ljava/lang/String;", "channelId$delegate", "Lkotlin/Lazy;", "galleryChannelId", "getGalleryChannelId", "galleryChannelId$delegate", "pipCallbacks", "Lcom/mako/kscore/helpers/PiPSupportCallback;", "getPipCallbacks", "()Lcom/mako/kscore/helpers/PiPSupportCallback;", "setPipCallbacks", "(Lcom/mako/kscore/helpers/PiPSupportCallback;)V", "playerContainer", "Landroid/widget/FrameLayout;", "getPlayerContainer", "()Landroid/widget/FrameLayout;", "rootControl", "Lcom/mako/kscore/ksplayer/view/RootControl;", "getRootControl", "()Lcom/mako/kscore/ksplayer/view/RootControl;", "vcmid", "getVcmid", "vcmid$delegate", "onCastState", "", "castState", "Lcom/mako/kscore/ksplayer/helpers/PlayerCastState;", "onContentPlayingChanged", "isPlaying", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onError", "messageType", "", "showMessage", "onPlayerClick", "data", "onPlayerFinish", "nextEpisodeExists", "onResume", "onStop", "onStopWatchingTime", "openMessageFragment", "play", "seekTo", "", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PlayerFragment extends CastSupportFragment {
    private PlayerFragmentCallbacks callbacks;
    private PiPSupportCallback pipCallbacks;

    /* renamed from: vcmid$delegate, reason: from kotlin metadata */
    private final Lazy vcmid = LazyKt.lazy(new Function0<String>() { // from class: com.channel2.mobile.ui.video.fragments.PlayerFragment$vcmid$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            Intent intent;
            Bundle extras;
            String string;
            FragmentActivity activity = this.this$0.getActivity();
            return (activity == null || (intent = activity.getIntent()) == null || (extras = intent.getExtras()) == null || (string = extras.getString("vcmId")) == null) ? "" : string;
        }
    });

    /* renamed from: channelId$delegate, reason: from kotlin metadata */
    private final Lazy channelId = LazyKt.lazy(new Function0<String>() { // from class: com.channel2.mobile.ui.video.fragments.PlayerFragment$channelId$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            Intent intent;
            Bundle extras;
            String string;
            FragmentActivity activity = this.this$0.getActivity();
            return (activity == null || (intent = activity.getIntent()) == null || (extras = intent.getExtras()) == null || (string = extras.getString("channelId")) == null) ? "" : string;
        }
    });

    /* renamed from: galleryChannelId$delegate, reason: from kotlin metadata */
    private final Lazy galleryChannelId = LazyKt.lazy(new Function0<String>() { // from class: com.channel2.mobile.ui.video.fragments.PlayerFragment$galleryChannelId$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            Intent intent;
            Bundle extras;
            String string;
            FragmentActivity activity = this.this$0.getActivity();
            return (activity == null || (intent = activity.getIntent()) == null || (extras = intent.getExtras()) == null || (string = extras.getString("galleryChannelId")) == null) ? "" : string;
        }
    });

    /* compiled from: PlayerFragment.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayerCastState.values().length];
            try {
                iArr[PlayerCastState.connecting.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PlayerCastState.connected.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PlayerCastState.casting.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final String getVcmid() {
        return (String) this.vcmid.getValue();
    }

    public final String getChannelId() {
        return (String) this.channelId.getValue();
    }

    public final String getGalleryChannelId() {
        return (String) this.galleryChannelId.getValue();
    }

    public final PlayerFragmentCallbacks getCallbacks() {
        return this.callbacks;
    }

    public final void setCallbacks(PlayerFragmentCallbacks playerFragmentCallbacks) {
        this.callbacks = playerFragmentCallbacks;
    }

    public final PiPSupportCallback getPipCallbacks() {
        return this.pipCallbacks;
    }

    public final void setPipCallbacks(PiPSupportCallback piPSupportCallback) {
        this.pipCallbacks = piPSupportCallback;
    }

    public final FrameLayout getPlayerContainer() {
        View view = getView();
        if (view != null) {
            return (FrameLayout) view.findViewById(R.id.playerContainer);
        }
        return null;
    }

    public final RootControl getRootControl() {
        View view = getView();
        if (view != null) {
            return (RootControl) view.findViewById(R.id.root_control);
        }
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_player, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        Intent intent;
        Bundle extras;
        super.onResume();
        if (getRootControl() != null) {
            RootControl rootControl = getRootControl();
            if (rootControl != null) {
                rootControl.play(ActionReport.resume, ReasonReport.appBackground);
                return;
            }
            return;
        }
        FragmentActivity activity = getActivity();
        play((activity == null || (intent = activity.getIntent()) == null || (extras = intent.getExtras()) == null) ? 0L : extras.getLong("seekTo"));
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        FragmentActivity activity = getActivity();
        Application application = activity != null ? activity.getApplication() : null;
        MyApplication myApplication = application instanceof MyApplication ? (MyApplication) application : null;
        if (myApplication != null) {
            FragmentActivity activity2 = getActivity();
            Application application2 = activity2 != null ? activity2.getApplication() : null;
            MyApplication myApplication2 = application2 instanceof MyApplication ? (MyApplication) application2 : null;
            Boolean boolValueOf = myApplication2 != null ? Boolean.valueOf(myApplication2.isBackground()) : null;
            Log.d("checkPiP", "onStop: bg = " + boolValueOf + " | is pip = " + myApplication.getIsPiP());
            if (myApplication.isBackground() || myApplication.getIsPiP()) {
                RootControl rootControl = getRootControl();
                boolean z = false;
                if (rootControl != null && !rootControl.isLive()) {
                    z = true;
                }
                if (z) {
                    RootControl rootControl2 = getRootControl();
                    if (rootControl2 != null) {
                        RootControl.pause$default(rootControl2, null, ReasonReport.appBackground, 1, null);
                        return;
                    }
                    return;
                }
                FrameLayout playerContainer = getPlayerContainer();
                if (playerContainer != null) {
                    KsPlayerManager.killPlayer$default(KsPlayerManager.INSTANCE, playerContainer, null, myApplication.isBackground() ? ReasonReport.appBackground : ReasonReport.back, new KillPlayerListener() { // from class: com.channel2.mobile.ui.video.fragments.PlayerFragment$$ExternalSyntheticLambda0
                        @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                        public final void onKilled() {
                            PlayerFragment.onStop$lambda$2$lambda$1$lambda$0(this.f$0);
                        }
                    }, 2, null);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStop$lambda$2$lambda$1$lambda$0(PlayerFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.d("checkKillPlayer", "PlayerFragment onStop() - onKilled");
        FrameLayout playerContainer = this$0.getPlayerContainer();
        if (playerContainer != null) {
            playerContainer.removeAllViews();
        }
    }

    @Override // com.mako.kscore.ksplayer.controller.CastSupportFragment, com.mako.kscore.ksplayer.helpers.PlayerCallback
    public void onStopWatchingTime() {
        super.onStopWatchingTime();
        FragmentActivity activity = getActivity();
        Application application = activity != null ? activity.getApplication() : null;
        KsApplication ksApplication = application instanceof KsApplication ? (KsApplication) application : null;
        boolean z = false;
        if (ksApplication != null && ksApplication.getIsPiP()) {
            z = true;
        }
        if (z) {
            Utils utils = Utils.INSTANCE;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            utils.killPiP(contextRequireContext);
            return;
        }
        openMessageFragment(MessageType.INSTANCE.getPLAYER_CONTINUE_WATCHING_ERROR());
        RootControl rootControl = getRootControl();
        if (rootControl != null) {
            rootControl.pause(ActionReport.pause, ReasonReport.userIdle);
        }
    }

    @Override // com.mako.kscore.ksplayer.controller.CastSupportFragment, com.mako.kscore.ksplayer.helpers.PlayerCallback
    public void onPlayerClick(Bundle data) {
        Intrinsics.checkNotNullParameter(data, "data");
        super.onPlayerClick(data);
        PlayerFragmentCallbacks playerFragmentCallbacks = this.callbacks;
        if (playerFragmentCallbacks != null) {
            playerFragmentCallbacks.onPlayerClick(data);
        }
    }

    @Override // com.mako.kscore.ksplayer.controller.CastSupportFragment, com.mako.kscore.ksplayer.helpers.PlayerCallback
    public void onContentPlayingChanged(boolean isPlaying) {
        super.onContentPlayingChanged(isPlaying);
        PiPSupportCallback piPSupportCallback = this.pipCallbacks;
        if (piPSupportCallback != null) {
            piPSupportCallback.onIsPlayingChanged(isPlaying);
        }
    }

    @Override // com.mako.kscore.ksplayer.controller.CastSupportFragment, com.mako.kscore.ksplayer.helpers.PlayerCallback
    public void onError(int messageType, boolean showMessage) {
        super.onError(messageType, showMessage);
        FragmentActivity activity = getActivity();
        Application application = activity != null ? activity.getApplication() : null;
        KsApplication ksApplication = application instanceof KsApplication ? (KsApplication) application : null;
        boolean z = false;
        if (ksApplication != null && ksApplication.getIsPiP()) {
            z = true;
        }
        if (z) {
            return;
        }
        openMessageFragment(messageType);
    }

    public final void openMessageFragment(int messageType) {
        PlayerMessageDialogFragment playerMessageDialogFragment = new PlayerMessageDialogFragment(messageType, new PlayerFragment$openMessageFragment$dialogFragment$1(this));
        playerMessageDialogFragment.setCancelable(false);
        try {
            playerMessageDialogFragment.show(getChildFragmentManager(), (String) null);
        } catch (Exception unused) {
            PlayerFragmentCallbacks playerFragmentCallbacks = this.callbacks;
            if (playerFragmentCallbacks != null) {
                PlayerFragmentCallbacks.DefaultImpls.onExit$default(playerFragmentCallbacks, null, 1, null);
            }
        }
    }

    @Override // com.mako.kscore.ksplayer.controller.CastSupportFragment
    public void onCastState(PlayerCastState castState) {
        RootControl rootControl;
        Intrinsics.checkNotNullParameter(castState, "castState");
        super.onCastState(castState);
        try {
            Log.d("castCheck", "PlayerVodFragment observer castState = " + castState);
            int i = WhenMappings.$EnumSwitchMapping$0[castState.ordinal()];
            boolean z = true;
            if (i == 1) {
                RootControl rootControl2 = getRootControl();
                if (rootControl2 != null) {
                    RootControl.pause$default(rootControl2, null, ReasonReport.cast, 1, null);
                }
            } else if ((i == 2 || i == 3) && (rootControl = getRootControl()) != null) {
                String vcmid = getVcmid();
                CastManager.Companion companion = CastManager.INSTANCE;
                Context contextRequireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                String currentVcmId = companion.getSharedInstance(contextRequireContext).getCurrentVcmId();
                if ((!StringsKt.isBlank(vcmid)) && !Intrinsics.areEqual(currentVcmId, vcmid)) {
                    RootControl rootControl3 = getRootControl();
                    Intrinsics.checkNotNull(rootControl3);
                    if (Intrinsics.areEqual(rootControl3.getPlaylist().getConsumer(), MainConfig.main.getCurrentParam("cast_consumer"))) {
                        z = false;
                    }
                    rootControl.cast(z);
                } else {
                    PlayerFragmentCallbacks playerFragmentCallbacks = this.callbacks;
                    if (playerFragmentCallbacks != null) {
                        PlayerFragmentCallbacks.DefaultImpls.onExit$default(playerFragmentCallbacks, null, 1, null);
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.mako.kscore.ksplayer.controller.CastSupportFragment, com.mako.kscore.ksplayer.helpers.PlayerCallback
    public void onPlayerFinish(boolean nextEpisodeExists) {
        super.onPlayerFinish(nextEpisodeExists);
        PlayerFragmentCallbacks playerFragmentCallbacks = this.callbacks;
        if (playerFragmentCallbacks != null) {
            PlayerFragmentCallbacks.DefaultImpls.onExit$default(playerFragmentCallbacks, null, 1, null);
        }
    }

    public final void play(long seekTo) {
        String string;
        Intent intent;
        Bundle extras;
        Log.d("checkPosition", "play: get position = " + seekTo);
        String vcmid = getVcmid();
        FragmentActivity activity = getActivity();
        Application application = activity != null ? activity.getApplication() : null;
        MyApplication myApplication = application instanceof MyApplication ? (MyApplication) application : null;
        boolean z = false;
        PlayerState playerState = myApplication != null && myApplication.getIsPiP() ? PlayerState.picture_in_picture : PlayerState.full_screen;
        String channelId = getChannelId();
        String galleryChannelId = getGalleryChannelId();
        FragmentActivity activity2 = getActivity();
        if (activity2 == null || (intent = activity2.getIntent()) == null || (extras = intent.getExtras()) == null || (string = extras.getString("mako_ref_comp")) == null) {
            string = "";
        }
        PlayerPageParams playerPageParams = new PlayerPageParams(null, null, 0, 0, null, "player_page", string, null, Token.WITHEXPR, null);
        CastManager.Companion companion = CastManager.INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        boolean z2 = companion.getSharedInstance(contextRequireContext).getCastState().getValue() == PlayerCastState.none;
        FragmentActivity activity3 = getActivity();
        Application application2 = activity3 != null ? activity3.getApplication() : null;
        MyApplication myApplication2 = application2 instanceof MyApplication ? (MyApplication) application2 : null;
        if (myApplication2 != null && myApplication2.getIsPiP()) {
            z = true;
        }
        KsPlayItem ksPlayItem = new KsPlayItem(null, vcmid, channelId, galleryChannelId, Integer.valueOf(R.color.black0C0C0C), null, null, z2, null, false, 0L, 0L, seekTo, false, false, false, false, null, false, null, !z, false, 0, null, playerPageParams, null, playerState, 49278817, null);
        FrameLayout playerContainer = getPlayerContainer();
        if (playerContainer != null) {
            KsPlayerManager.INSTANCE.play(ksPlayItem, playerContainer, this);
        }
    }
}
