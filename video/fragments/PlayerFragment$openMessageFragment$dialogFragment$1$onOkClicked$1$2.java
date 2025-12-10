package com.channel2.mobile.ui.video.fragments;

import android.util.Log;
import android.widget.FrameLayout;
import com.mako.kscore.ksplayer.helpers.KillPlayerListener;
import com.mako.kscore.ksplayer.helpers.managers.KsPlayerManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: PlayerFragment.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
final class PlayerFragment$openMessageFragment$dialogFragment$1$onOkClicked$1$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ PlayerFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlayerFragment$openMessageFragment$dialogFragment$1$onOkClicked$1$2(PlayerFragment playerFragment) {
        super(0);
        this.this$0 = playerFragment;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        Unit unit;
        FrameLayout playerContainer = this.this$0.getPlayerContainer();
        if (playerContainer != null) {
            final PlayerFragment playerFragment = this.this$0;
            KsPlayerManager.killPlayer$default(KsPlayerManager.INSTANCE, playerContainer, null, null, new KillPlayerListener() { // from class: com.channel2.mobile.ui.video.fragments.PlayerFragment$openMessageFragment$dialogFragment$1$onOkClicked$1$2$$ExternalSyntheticLambda0
                @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                public final void onKilled() {
                    PlayerFragment$openMessageFragment$dialogFragment$1$onOkClicked$1$2.invoke$lambda$1$lambda$0(playerFragment);
                }
            }, 6, null);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            this.this$0.play(0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1$lambda$0(PlayerFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.d("checkKillPlayer", "PlayerFragment PLAYER_CONTINUE_WATCHING_ERROR onOkClicked() - play after onKilled");
        this$0.play(0L);
    }
}
