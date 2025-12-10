package com.channel2.mobile.ui.video.fragments;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.DialogFragment;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.channel2.mobile.ui.R;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.mako.kscore.helpers.MessageType;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerMessageDialogFragment.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0012\u0010 \u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J$\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020#2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016R\u001d\u0010\u0007\u001a\u0004\u0018\u00010\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u0004\u0018\u00010\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\f\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0016\u001a\u0004\u0018\u00010\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\f\u001a\u0004\b\u0017\u0010\u0014¨\u0006*"}, d2 = {"Lcom/channel2/mobile/ui/video/fragments/PlayerMessageDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "messageType", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/channel2/mobile/ui/video/fragments/PlayerDialogMessageOnClickListener;", "(ILcom/channel2/mobile/ui/video/fragments/PlayerDialogMessageOnClickListener;)V", "closeBtn", "Landroid/widget/ImageView;", "getCloseBtn", "()Landroid/widget/ImageView;", "closeBtn$delegate", "Lkotlin/Lazy;", "getListener", "()Lcom/channel2/mobile/ui/video/fragments/PlayerDialogMessageOnClickListener;", "getMessageType", "()I", "noButton", "Landroid/widget/TextView;", "getNoButton", "()Landroid/widget/TextView;", "noButton$delegate", "yesButton", "getYesButton", "yesButton$delegate", "onConfigurationChanged", "", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PlayerMessageDialogFragment extends DialogFragment {

    /* renamed from: closeBtn$delegate, reason: from kotlin metadata */
    private final Lazy closeBtn;
    private final PlayerDialogMessageOnClickListener listener;
    private final int messageType;

    /* renamed from: noButton$delegate, reason: from kotlin metadata */
    private final Lazy noButton;

    /* renamed from: yesButton$delegate, reason: from kotlin metadata */
    private final Lazy yesButton;

    public PlayerMessageDialogFragment(int i, PlayerDialogMessageOnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.messageType = i;
        this.listener = listener;
        this.yesButton = LazyKt.lazy(new Function0<TextView>() { // from class: com.channel2.mobile.ui.video.fragments.PlayerMessageDialogFragment$yesButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                View view = this.this$0.getView();
                if (view != null) {
                    return (TextView) view.findViewById(R.id.yesButton);
                }
                return null;
            }
        });
        this.noButton = LazyKt.lazy(new Function0<TextView>() { // from class: com.channel2.mobile.ui.video.fragments.PlayerMessageDialogFragment$noButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                View view = this.this$0.getView();
                if (view != null) {
                    return (TextView) view.findViewById(R.id.noButton);
                }
                return null;
            }
        });
        this.closeBtn = LazyKt.lazy(new Function0<ImageView>() { // from class: com.channel2.mobile.ui.video.fragments.PlayerMessageDialogFragment$closeBtn$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                View view = this.this$0.getView();
                if (view != null) {
                    return (ImageView) view.findViewById(R.id.closeBtn);
                }
                return null;
            }
        });
    }

    public final PlayerDialogMessageOnClickListener getListener() {
        return this.listener;
    }

    public final int getMessageType() {
        return this.messageType;
    }

    public final TextView getYesButton() {
        return (TextView) this.yesButton.getValue();
    }

    public final TextView getNoButton() {
        return (TextView) this.noButton.getValue();
    }

    public final ImageView getCloseBtn() {
        return (ImageView) this.closeBtn.getValue();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.FullScreenDialog);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        int i = this.messageType;
        if (i == MessageType.INSTANCE.getPLAYER_CONTINUE_WATCHING_ERROR()) {
            View viewInflate = inflater.inflate(R.layout.message_dialog_fragment_continue_watching, container);
            Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…inue_watching, container)");
            return viewInflate;
        }
        if (i == MessageType.INSTANCE.getGEO_LOCATION_ERROR()) {
            View viewInflate2 = inflater.inflate(R.layout.message_dialog_fragment_location_error, container);
            Intrinsics.checkNotNullExpressionValue(viewInflate2, "inflater.inflate(R.layou…ocation_error, container)");
            return viewInflate2;
        }
        if (i == MessageType.INSTANCE.getGENERAL_NETWORK_ERROR()) {
            View viewInflate3 = inflater.inflate(R.layout.message_dialog_fragment_communication_error, container);
            Intrinsics.checkNotNullExpressionValue(viewInflate3, "inflater.inflate(R.layou…ication_error, container)");
            return viewInflate3;
        }
        View viewInflate4 = inflater.inflate(R.layout.message_dialog_fragment_general_error, container);
        Intrinsics.checkNotNullExpressionValue(viewInflate4, "inflater.inflate(R.layou…general_error, container)");
        return viewInflate4;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        TextView yesButton = getYesButton();
        if (yesButton != null) {
            yesButton.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.fragments.PlayerMessageDialogFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    PlayerMessageDialogFragment.onViewCreated$lambda$0(this.f$0, view2);
                }
            });
        }
        TextView noButton = getNoButton();
        if (noButton != null) {
            noButton.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.fragments.PlayerMessageDialogFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    PlayerMessageDialogFragment.onViewCreated$lambda$1(this.f$0, view2);
                }
            });
        }
        ImageView closeBtn = getCloseBtn();
        if (closeBtn != null) {
            closeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.fragments.PlayerMessageDialogFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    PlayerMessageDialogFragment.onViewCreated$lambda$2(this.f$0, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(PlayerMessageDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onOkClicked(this$0, this$0.messageType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(PlayerMessageDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onCancelClicked(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(PlayerMessageDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onCancelClicked(this$0);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AppCompatDialog(requireContext(), getTheme()) { // from class: com.channel2.mobile.ui.video.fragments.PlayerMessageDialogFragment.onCreateDialog.1
            @Override // androidx.activity.ComponentDialog, android.app.Dialog
            public void onBackPressed() {
                super.onBackPressed();
                Log.d("checkBack", "DialogBlockedContentFragment onBackPressed: ");
                PlayerMessageDialogFragment.this.getListener().onCancelClicked(PlayerMessageDialogFragment.this);
            }
        };
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        Log.d("testMessage", "onConfigurationChanged: ");
    }
}
