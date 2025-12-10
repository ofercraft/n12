package com.channel2.mobile.ui.video;

import android.os.Bundle;
import com.mako.kscore.ksplayer.helpers.ReasonReport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerFragmentCallbacks.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/channel2/mobile/ui/video/PlayerFragmentCallbacks;", "", "onExit", "", "reason", "Lcom/mako/kscore/ksplayer/helpers/ReasonReport;", "onPlayerClick", "data", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface PlayerFragmentCallbacks {
    void onExit(ReasonReport reason);

    void onPlayerClick(Bundle data);

    /* compiled from: PlayerFragmentCallbacks.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void onExit(PlayerFragmentCallbacks playerFragmentCallbacks, ReasonReport reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
        }

        public static void onPlayerClick(PlayerFragmentCallbacks playerFragmentCallbacks, Bundle data) {
            Intrinsics.checkNotNullParameter(data, "data");
        }

        public static /* synthetic */ void onExit$default(PlayerFragmentCallbacks playerFragmentCallbacks, ReasonReport reasonReport, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onExit");
            }
            if ((i & 1) != 0) {
                reasonReport = ReasonReport.none;
            }
            playerFragmentCallbacks.onExit(reasonReport);
        }
    }
}
