package com.channel2.mobile.ui.video.fragments;

import android.util.Log;
import android.widget.FrameLayout;
import com.channel2.mobile.ui.video.PlayerFragmentCallbacks;
import com.channel2.mobile.ui.video.fragments.PlayerDialogMessageOnClickListener;
import com.mako.kscore.helpers.MessageType;
import com.mako.kscore.ksplayer.helpers.ActionReport;
import com.mako.kscore.ksplayer.helpers.KillPlayerListener;
import com.mako.kscore.ksplayer.helpers.ReasonReport;
import com.mako.kscore.ksplayer.helpers.managers.KsPlayerManager;
import com.mako.kscore.ksplayer.view.RootControl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerFragment.kt */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/channel2/mobile/ui/video/fragments/PlayerFragment$openMessageFragment$dialogFragment$1", "Lcom/channel2/mobile/ui/video/fragments/PlayerDialogMessageOnClickListener;", "onCancelClicked", "", "fragment", "Lcom/channel2/mobile/ui/video/fragments/PlayerMessageDialogFragment;", "onOkClicked", "messageType", "", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PlayerFragment$openMessageFragment$dialogFragment$1 implements PlayerDialogMessageOnClickListener {
    final /* synthetic */ PlayerFragment this$0;

    PlayerFragment$openMessageFragment$dialogFragment$1(PlayerFragment playerFragment) {
        this.this$0 = playerFragment;
    }

    @Override // com.channel2.mobile.ui.video.fragments.PlayerDialogMessageOnClickListener
    public void onCancelClicked(PlayerMessageDialogFragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Log.d("blockContent", "PlayerVodFragment onCancelClicked: ");
        PlayerFragmentCallbacks callbacks = this.this$0.getCallbacks();
        if (callbacks != null) {
            callbacks.onExit(ReasonReport.back);
        }
    }

    @Override // com.channel2.mobile.ui.video.fragments.PlayerDialogMessageOnClickListener
    public void onOkClicked(PlayerMessageDialogFragment fragment, int messageType) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        PlayerDialogMessageOnClickListener.DefaultImpls.onOkClicked(this, fragment, messageType);
        if (messageType == MessageType.INSTANCE.getPLAYER_CONTINUE_WATCHING_ERROR()) {
            if (this.this$0.getPlayerContainer() != null) {
                PlayerFragment playerFragment = this.this$0;
                RootControl rootControl = playerFragment.getRootControl();
                if (rootControl != null) {
                    rootControl.setLastKeyStroke(System.currentTimeMillis());
                    rootControl.play(ActionReport.resume, ReasonReport.userIdle);
                    Unit unit = Unit.INSTANCE;
                } else {
                    new PlayerFragment$openMessageFragment$dialogFragment$1$onOkClicked$1$2(playerFragment);
                }
            } else {
                PlayerFragmentCallbacks callbacks = this.this$0.getCallbacks();
                if (callbacks != null) {
                    callbacks.onExit(ReasonReport.back);
                    Unit unit2 = Unit.INSTANCE;
                }
            }
            RootControl rootControl2 = this.this$0.getRootControl();
            if (rootControl2 == null) {
                return;
            }
            rootControl2.setLastKeyStroke(System.currentTimeMillis());
            return;
        }
        FrameLayout playerContainer = this.this$0.getPlayerContainer();
        if (playerContainer != null) {
            final PlayerFragment playerFragment2 = this.this$0;
            RootControl rootControl3 = playerFragment2.getRootControl();
            Log.d("checkPosition", "onOkClicked: save position = " + (rootControl3 != null ? Long.valueOf(rootControl3.getContentPosition()) : null));
            KsPlayerManager.killPlayer$default(KsPlayerManager.INSTANCE, playerContainer, null, null, new KillPlayerListener() { // from class: com.channel2.mobile.ui.video.fragments.PlayerFragment$openMessageFragment$dialogFragment$1$$ExternalSyntheticLambda0
                @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                public final void onKilled() {
                    PlayerFragment$openMessageFragment$dialogFragment$1.onOkClicked$lambda$3$lambda$2(playerFragment2);
                }
            }, 6, null);
            return;
        }
        PlayerFragmentCallbacks callbacks2 = this.this$0.getCallbacks();
        if (callbacks2 != null) {
            callbacks2.onExit(ReasonReport.back);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onOkClicked$lambda$3$lambda$2(PlayerFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.d("checkKillPlayer", "PlayerFragment else onOkClicked() - play after onKilled");
        RootControl rootControl = this$0.getRootControl();
        this$0.play(rootControl != null ? rootControl.getContentPosition() : 0L);
    }
}
