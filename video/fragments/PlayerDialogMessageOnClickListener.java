package com.channel2.mobile.ui.video.fragments;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerDialogMessageOnClickListener.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0017¨\u0006\t"}, d2 = {"Lcom/channel2/mobile/ui/video/fragments/PlayerDialogMessageOnClickListener;", "", "onCancelClicked", "", "fragment", "Lcom/channel2/mobile/ui/video/fragments/PlayerMessageDialogFragment;", "onOkClicked", "messageType", "", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface PlayerDialogMessageOnClickListener {
    void onCancelClicked(PlayerMessageDialogFragment fragment);

    void onOkClicked(PlayerMessageDialogFragment fragment, int messageType);

    /* compiled from: PlayerDialogMessageOnClickListener.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void onOkClicked(PlayerDialogMessageOnClickListener playerDialogMessageOnClickListener, PlayerMessageDialogFragment fragment, int i) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            fragment.dismiss();
        }

        public static void onCancelClicked(PlayerDialogMessageOnClickListener playerDialogMessageOnClickListener, PlayerMessageDialogFragment fragment) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            fragment.dismiss();
        }
    }
}
