package com.channel2.mobile.ui.video.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.FragmentActivity;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler;
import com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryRecyclerAdapter;
import com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryVideoControls;
import com.channel2.mobile.ui.Chats.models.ChatMediaItem;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.databinding.FragmentChatGalerryBinding;
import com.channel2.mobile.ui.helpers.AnalyticsManager;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.video.PlayerFragmentCallbacks;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.mako.kscore.ksplayer.controller.CastSupportFragment;
import com.mako.kscore.ksplayer.helpers.ActionReport;
import com.mako.kscore.ksplayer.helpers.KillPlayerListener;
import com.mako.kscore.ksplayer.helpers.PlayerCallback;
import com.mako.kscore.ksplayer.helpers.ReasonReport;
import com.mako.kscore.ksplayer.helpers.managers.KsPlayerManager;
import com.mako.kscore.ksplayer.model.KsPlayItem;
import com.mako.kscore.ksplayer.view.RootControl;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* compiled from: ChatGalleryFragment.kt */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010P\u001a\u00020QH\u0002J\u000e\u0010R\u001a\u00020Q2\u0006\u0010S\u001a\u00020TJ\u0012\u0010U\u001a\u00020Q2\b\u0010V\u001a\u0004\u0018\u00010WH\u0016J&\u0010X\u001a\u0004\u0018\u00010Y2\u0006\u0010Z\u001a\u00020[2\b\u0010\\\u001a\u0004\u0018\u00010L2\b\u0010V\u001a\u0004\u0018\u00010WH\u0016J\b\u0010]\u001a\u00020QH\u0016J\b\u0010^\u001a\u00020QH\u0016J\u001a\u0010_\u001a\u00020Q2\u0006\u0010`\u001a\u00020Y2\b\u0010V\u001a\u0004\u0018\u00010WH\u0016J\b\u0010a\u001a\u00020QH\u0002J\b\u0010b\u001a\u00020QH\u0002J\u0006\u0010c\u001a\u00020QJ\n\u0010d\u001a\u00020Q*\u00020YJ\n\u0010e\u001a\u00020Q*\u00020YJ\u0014\u0010f\u001a\u00020Q*\u00020Y2\u0006\u0010g\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R!\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00107\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u00109\"\u0004\b>\u0010;R\u0011\u0010?\u001a\u00020@¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u001a\u0010C\u001a\u00020DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u0010\u0010I\u001a\u0004\u0018\u00010JX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010K\u001a\u0004\u0018\u00010L8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bO\u0010\f\u001a\u0004\bM\u0010N¨\u0006h"}, d2 = {"Lcom/channel2/mobile/ui/video/fragments/ChatGalleryFragment;", "Lcom/mako/kscore/ksplayer/controller/CastSupportFragment;", "()V", "AUTO_HIDE_DELAY_MILLIS", "", "UI_ANIMATION_DURATION", "", "adapter", "Lcom/channel2/mobile/ui/Chats/controllers/Gallery/ChatGalleryRecyclerAdapter;", "getAdapter", "()Lcom/channel2/mobile/ui/Chats/controllers/Gallery/ChatGalleryRecyclerAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "binding", "Lcom/channel2/mobile/ui/databinding/FragmentChatGalerryBinding;", "getBinding", "()Lcom/channel2/mobile/ui/databinding/FragmentChatGalerryBinding;", "setBinding", "(Lcom/channel2/mobile/ui/databinding/FragmentChatGalerryBinding;)V", "callbacks", "Lcom/channel2/mobile/ui/video/PlayerFragmentCallbacks;", "getCallbacks", "()Lcom/channel2/mobile/ui/video/PlayerFragmentCallbacks;", "setCallbacks", "(Lcom/channel2/mobile/ui/video/PlayerFragmentCallbacks;)V", "chatMedias", "Ljava/util/ArrayList;", "Lcom/channel2/mobile/ui/Chats/models/ChatMediaItem;", "Lkotlin/collections/ArrayList;", "getChatMedias", "()Ljava/util/ArrayList;", "chatReportItem", "Lcom/channel2/mobile/ui/Chats/models/ChatReportItem;", "getChatReportItem", "()Lcom/channel2/mobile/ui/Chats/models/ChatReportItem;", "setChatReportItem", "(Lcom/channel2/mobile/ui/Chats/models/ChatReportItem;)V", "currentItem", "getCurrentItem", "()Lcom/channel2/mobile/ui/Chats/models/ChatMediaItem;", "setCurrentItem", "(Lcom/channel2/mobile/ui/Chats/models/ChatMediaItem;)V", "inflated", "", "getInflated", "()Z", "setInflated", "(Z)V", "mHideRunnable", "Ljava/lang/Runnable;", "mShowHideHandler", "Landroid/os/Handler;", "mVideoContainer", "Landroid/widget/FrameLayout;", "mVisible", "numberOfMedias", "getNumberOfMedias", "()I", "setNumberOfMedias", "(I)V", "showMediaNumber", "getShowMediaNumber", "setShowMediaNumber", "simpleDateFormat", "Ljava/text/SimpleDateFormat;", "getSimpleDateFormat", "()Ljava/text/SimpleDateFormat;", "topicName", "", "getTopicName", "()Ljava/lang/String;", "setTopicName", "(Ljava/lang/String;)V", "videoControls", "Lcom/channel2/mobile/ui/Chats/controllers/Gallery/ChatGalleryVideoControls;", "videoControlsLayout", "Landroid/view/ViewGroup;", "getVideoControlsLayout", "()Landroid/view/ViewGroup;", "videoControlsLayout$delegate", "hide", "", "killPlayer", "reason", "Lcom/mako/kscore/ksplayer/helpers/ReasonReport;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "onDestroy", "onPause", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "setPlayer", "show", "toggle", "fadeIn", "fadeOut", "setBottomMargin", "dp", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ChatGalleryFragment extends CastSupportFragment {
    private FragmentChatGalerryBinding binding;
    private PlayerFragmentCallbacks callbacks;
    private boolean inflated;
    private FrameLayout mVideoContainer;
    private boolean mVisible;
    private int numberOfMedias;
    private int showMediaNumber;
    private ChatGalleryVideoControls videoControls;
    private final long UI_ANIMATION_DURATION = 300;
    private final int AUTO_HIDE_DELAY_MILLIS = PathInterpolatorCompat.MAX_NUM_POINTS;
    private ChatReportItem chatReportItem = new ChatReportItem();
    private final ArrayList<ChatMediaItem> chatMedias = new ArrayList<>();
    private String topicName = "";
    private ChatMediaItem currentItem = new ChatMediaItem(new JSONObject());
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm' 'dd/MM/yyyy", Locale.getDefault());
    private final Handler mShowHideHandler = new Handler(Looper.getMainLooper());
    private final Runnable mHideRunnable = new Runnable() { // from class: com.channel2.mobile.ui.video.fragments.ChatGalleryFragment$$ExternalSyntheticLambda1
        @Override // java.lang.Runnable
        public final void run() {
            ChatGalleryFragment.mHideRunnable$lambda$0(this.f$0);
        }
    };

    /* renamed from: videoControlsLayout$delegate, reason: from kotlin metadata */
    private final Lazy videoControlsLayout = LazyKt.lazy(new Function0<ViewGroup>() { // from class: com.channel2.mobile.ui.video.fragments.ChatGalleryFragment$videoControlsLayout$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewGroup invoke() {
            ConstraintLayout root;
            FragmentChatGalerryBinding binding = this.this$0.getBinding();
            if (binding == null || (root = binding.getRoot()) == null) {
                return null;
            }
            return (ViewGroup) root.findViewById(R.id.chat_gallery_video_controls);
        }
    });

    /* renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter = LazyKt.lazy(new Function0<ChatGalleryRecyclerAdapter>() { // from class: com.channel2.mobile.ui.video.fragments.ChatGalleryFragment$adapter$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ChatGalleryRecyclerAdapter invoke() {
            ArrayList<ChatMediaItem> chatMedias = this.this$0.getChatMedias();
            final ChatGalleryFragment chatGalleryFragment = this.this$0;
            return new ChatGalleryRecyclerAdapter(chatMedias, new ChatGalleryHandler() { // from class: com.channel2.mobile.ui.video.fragments.ChatGalleryFragment$adapter$2.1
                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onDetach(View itemView, int position) {
                    Intrinsics.checkNotNullParameter(itemView, "itemView");
                }

                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onPauseVideo(FrameLayout videoContainer) {
                    Intrinsics.checkNotNullParameter(videoContainer, "videoContainer");
                }

                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onPlayVideo(FrameLayout videoContainer, int position) {
                    Intrinsics.checkNotNullParameter(videoContainer, "videoContainer");
                }

                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onItemClick(int position) {
                    ChatGalleryFragment chatGalleryFragment2 = chatGalleryFragment;
                    ChatMediaItem chatMediaItem = chatGalleryFragment2.getChatMedias().get(position);
                    Intrinsics.checkNotNullExpressionValue(chatMediaItem, "chatMedias[position]");
                    chatGalleryFragment2.setCurrentItem(chatMediaItem);
                    chatGalleryFragment.toggle();
                }

                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onItemClick(View itemView) {
                    Intrinsics.checkNotNullParameter(itemView, "itemView");
                    chatGalleryFragment.toggle();
                }

                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onAttach(View itemView, int position) {
                    TextView textView;
                    TextView textView2;
                    ConstraintLayout root;
                    TextView textView3;
                    TextView textView4;
                    Intrinsics.checkNotNullParameter(itemView, "itemView");
                    ChatGalleryFragment chatGalleryFragment2 = chatGalleryFragment;
                    ChatMediaItem chatMediaItem = chatGalleryFragment2.getChatMedias().get(position);
                    Intrinsics.checkNotNullExpressionValue(chatMediaItem, "chatMedias[position]");
                    chatGalleryFragment2.setCurrentItem(chatMediaItem);
                    RootControl rootControl = null;
                    if (chatGalleryFragment.getNumberOfMedias() > 1) {
                        String str = (position + 1) + RemoteSettings.FORWARD_SLASH_STRING + chatGalleryFragment.getNumberOfMedias();
                        FragmentChatGalerryBinding binding = chatGalleryFragment.getBinding();
                        TextView textView5 = binding != null ? binding.number : null;
                        if (textView5 != null) {
                            textView5.setText(str);
                        }
                    }
                    FragmentChatGalerryBinding binding2 = chatGalleryFragment.getBinding();
                    TextView textView6 = binding2 != null ? binding2.title : null;
                    if (textView6 != null) {
                        textView6.setText(chatGalleryFragment.getChatReportItem().getName());
                    }
                    String str2 = chatGalleryFragment.getSimpleDateFormat().format(Long.valueOf(chatGalleryFragment.getChatReportItem().getUpdatedDate()));
                    Intrinsics.checkNotNullExpressionValue(str2, "simpleDateFormat.format(…atReportItem.updatedDate)");
                    FragmentChatGalerryBinding binding3 = chatGalleryFragment.getBinding();
                    TextView textView7 = binding3 != null ? binding3.date : null;
                    if (textView7 != null) {
                        textView7.setText(str2);
                    }
                    String mediaContent = chatGalleryFragment.getCurrentItem().getMediaContent();
                    Intrinsics.checkNotNullExpressionValue(mediaContent, "currentItem.mediaContent");
                    String str3 = mediaContent;
                    if (!StringsKt.isBlank(str3)) {
                        FragmentChatGalerryBinding binding4 = chatGalleryFragment.getBinding();
                        TextView textView8 = binding4 != null ? binding4.footer : null;
                        if (textView8 != null) {
                            textView8.setText(str3);
                        }
                        if (chatGalleryFragment.mVisible) {
                            FragmentChatGalerryBinding binding5 = chatGalleryFragment.getBinding();
                            if (binding5 != null && (textView4 = binding5.footer) != null) {
                                chatGalleryFragment.fadeIn(textView4);
                            }
                        } else {
                            FragmentChatGalerryBinding binding6 = chatGalleryFragment.getBinding();
                            if (binding6 != null && (textView3 = binding6.footer) != null) {
                                chatGalleryFragment.fadeOut(textView3);
                            }
                        }
                    } else {
                        FragmentChatGalerryBinding binding7 = chatGalleryFragment.getBinding();
                        TextView textView9 = binding7 != null ? binding7.footer : null;
                        if (textView9 != null) {
                            textView9.setText("");
                        }
                    }
                    FragmentChatGalerryBinding binding8 = chatGalleryFragment.getBinding();
                    if (binding8 != null && (root = binding8.getRoot()) != null) {
                        rootControl = (RootControl) root.findViewById(R.id.root_control);
                    }
                    if (rootControl != null && rootControl.getPlayerExists()) {
                        chatGalleryFragment.killPlayer(ReasonReport.none);
                    }
                    FrameLayout frameLayout = chatGalleryFragment.mVideoContainer;
                    if (frameLayout != null) {
                        frameLayout.removeAllViews();
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("MessageId", String.valueOf(chatGalleryFragment.getCurrentItem().getMessageID()));
                    bundle.putString("Auto_ID", String.valueOf(chatGalleryFragment.getCurrentItem().getAutoId()));
                    bundle.putString("chatName", chatGalleryFragment.getTopicName());
                    if (chatGalleryFragment.getCurrentItem().getMediaTypeId() != 2) {
                        FragmentChatGalerryBinding binding9 = chatGalleryFragment.getBinding();
                        if (binding9 != null && (textView2 = binding9.footer) != null) {
                            chatGalleryFragment.setBottomMargin(textView2, 0);
                        }
                        ViewGroup videoControlsLayout = chatGalleryFragment.getVideoControlsLayout();
                        if (videoControlsLayout != null) {
                            videoControlsLayout.setVisibility(8);
                        }
                        FirebaseAnalytics.getInstance(chatGalleryFragment.requireContext()).logEvent("RC_open_picture", bundle);
                        AnalyticsManager.getInstance().reportPageView(chatGalleryFragment.requireContext(), "RC_Pic", "RC_Activities", "", "reportersChat", "", "specialEvent", chatGalleryFragment.getTopicName());
                        return;
                    }
                    FragmentChatGalerryBinding binding10 = chatGalleryFragment.getBinding();
                    if (binding10 != null && (textView = binding10.footer) != null) {
                        chatGalleryFragment.setBottomMargin(textView, 40);
                    }
                    chatGalleryFragment.mVideoContainer = (FrameLayout) itemView.findViewById(R.id.playerContainer);
                    chatGalleryFragment.requireActivity().getWindow().addFlags(128);
                    FrameLayout frameLayout2 = chatGalleryFragment.mVideoContainer;
                    if (frameLayout2 != null) {
                        frameLayout2.setKeepScreenOn(true);
                    }
                    chatGalleryFragment.setPlayer();
                    FirebaseAnalytics.getInstance(chatGalleryFragment.requireContext()).logEvent("RC_play", bundle);
                    AnalyticsManager.getInstance().reportPageView(chatGalleryFragment.requireContext(), "RC_Vid", "RC_Activities", "", "reportersChat", "", "specialEvent", chatGalleryFragment.getTopicName());
                }
            });
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static final void killPlayer$lambda$5$lambda$4() {
    }

    public final PlayerFragmentCallbacks getCallbacks() {
        return this.callbacks;
    }

    public final void setCallbacks(PlayerFragmentCallbacks playerFragmentCallbacks) {
        this.callbacks = playerFragmentCallbacks;
    }

    public final FragmentChatGalerryBinding getBinding() {
        return this.binding;
    }

    public final void setBinding(FragmentChatGalerryBinding fragmentChatGalerryBinding) {
        this.binding = fragmentChatGalerryBinding;
    }

    public final boolean getInflated() {
        return this.inflated;
    }

    public final void setInflated(boolean z) {
        this.inflated = z;
    }

    public final ChatReportItem getChatReportItem() {
        return this.chatReportItem;
    }

    public final void setChatReportItem(ChatReportItem chatReportItem) {
        Intrinsics.checkNotNullParameter(chatReportItem, "<set-?>");
        this.chatReportItem = chatReportItem;
    }

    public final ArrayList<ChatMediaItem> getChatMedias() {
        return this.chatMedias;
    }

    public final int getNumberOfMedias() {
        return this.numberOfMedias;
    }

    public final void setNumberOfMedias(int i) {
        this.numberOfMedias = i;
    }

    public final String getTopicName() {
        return this.topicName;
    }

    public final void setTopicName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.topicName = str;
    }

    public final int getShowMediaNumber() {
        return this.showMediaNumber;
    }

    public final void setShowMediaNumber(int i) {
        this.showMediaNumber = i;
    }

    public final ChatMediaItem getCurrentItem() {
        return this.currentItem;
    }

    public final void setCurrentItem(ChatMediaItem chatMediaItem) {
        Intrinsics.checkNotNullParameter(chatMediaItem, "<set-?>");
        this.currentItem = chatMediaItem;
    }

    public final SimpleDateFormat getSimpleDateFormat() {
        return this.simpleDateFormat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mHideRunnable$lambda$0(ChatGalleryFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ViewGroup getVideoControlsLayout() {
        return (ViewGroup) this.videoControlsLayout.getValue();
    }

    public final ChatGalleryRecyclerAdapter getAdapter() {
        return (ChatGalleryRecyclerAdapter) this.adapter.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Intent intent;
        Bundle extras;
        Intent intent2;
        Bundle extras2;
        Intent intent3;
        Bundle extras3;
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        String string = null;
        Serializable serializable = (activity == null || (intent3 = activity.getIntent()) == null || (extras3 = intent3.getExtras()) == null) ? null : extras3.getSerializable("chatReportItem");
        ChatReportItem chatReportItem = serializable instanceof ChatReportItem ? (ChatReportItem) serializable : null;
        if (chatReportItem == null) {
            chatReportItem = new ChatReportItem();
        }
        this.chatReportItem = chatReportItem;
        FragmentActivity activity2 = getActivity();
        long j = (activity2 == null || (intent2 = activity2.getIntent()) == null || (extras2 = intent2.getExtras()) == null) ? 0L : extras2.getLong("mediaId");
        ArrayList<ChatMediaItem> chatMedias = this.chatReportItem.getChatMedias();
        if (chatMedias != null) {
            this.chatMedias.addAll(chatMedias);
        }
        this.numberOfMedias = this.chatReportItem.getNumberOfMedias();
        FragmentActivity activity3 = getActivity();
        if (activity3 != null && (intent = activity3.getIntent()) != null && (extras = intent.getExtras()) != null) {
            string = extras.getString("chatName");
        }
        if (string == null) {
            string = "";
        }
        this.topicName = string;
        if (j >= 0) {
            int i = this.numberOfMedias;
            for (int i2 = 0; i2 < i; i2++) {
                if (this.chatMedias.get(i2).getAutoId() == j) {
                    this.showMediaNumber = i2;
                    return;
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentChatGalerryBinding fragmentChatGalerryBindingInflate = FragmentChatGalerryBinding.inflate(inflater);
        this.binding = fragmentChatGalerryBindingInflate;
        return fragmentChatGalerryBindingInflate != null ? fragmentChatGalerryBindingInflate.getRoot() : null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageView imageView;
        ViewPager2 viewPager2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        FragmentChatGalerryBinding fragmentChatGalerryBinding = this.binding;
        ViewPager2 viewPager22 = fragmentChatGalerryBinding != null ? fragmentChatGalerryBinding.viewPager : null;
        if (viewPager22 != null) {
            viewPager22.setAdapter(getAdapter());
        }
        FragmentChatGalerryBinding fragmentChatGalerryBinding2 = this.binding;
        if (fragmentChatGalerryBinding2 != null && (viewPager2 = fragmentChatGalerryBinding2.viewPager) != null) {
            viewPager2.setCurrentItem(this.showMediaNumber, false);
        }
        FragmentChatGalerryBinding fragmentChatGalerryBinding3 = this.binding;
        if (fragmentChatGalerryBinding3 == null || (imageView = fragmentChatGalerryBinding3.exit) == null) {
            return;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.video.fragments.ChatGalleryFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ChatGalleryFragment.onViewCreated$lambda$2(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(ChatGalleryFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PlayerFragmentCallbacks playerFragmentCallbacks = this$0.callbacks;
        if (playerFragmentCallbacks != null) {
            playerFragmentCallbacks.onExit(ReasonReport.none);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        RootControl rootControl;
        Log.i("lifecycle", "onPause");
        FrameLayout frameLayout = this.mVideoContainer;
        if (frameLayout != null && (rootControl = (RootControl) frameLayout.findViewById(R.id.root_control)) != null && rootControl.getPlayerExists()) {
            rootControl.pause(ActionReport.none, ReasonReport.none);
        }
        super.onPause();
    }

    public final void toggle() {
        if (this.mVisible) {
            this.mShowHideHandler.removeCallbacks(this.mHideRunnable);
            this.mShowHideHandler.postDelayed(this.mHideRunnable, 0L);
        } else {
            show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPlayer() {
        KsPlayItem ksPlayItem = new KsPlayItem(null, null, null, null, null, null, null, false, null, false, 0L, 0L, 0L, false, false, false, false, null, false, null, false, false, 0, null, null, null, null, 134217727, null);
        ksPlayItem.setLoop(true);
        ksPlayItem.setWithAds(false);
        ksPlayItem.setUseController(false);
        ksPlayItem.setImage(Integer.valueOf(R.color.black0C0C0C));
        String link1 = this.currentItem.getLink1();
        Intrinsics.checkNotNullExpressionValue(link1, "currentItem.link1");
        ksPlayItem.setUrl(link1);
        ksPlayItem.getPlayerPageParams().setMako_video_state("teaser");
        ksPlayItem.getDomoPlay();
        KsPlayerManager ksPlayerManager = KsPlayerManager.INSTANCE;
        FrameLayout frameLayout = this.mVideoContainer;
        Intrinsics.checkNotNull(frameLayout);
        ksPlayerManager.play(ksPlayItem, frameLayout, new PlayerCallback() { // from class: com.channel2.mobile.ui.video.fragments.ChatGalleryFragment.setPlayer.1
            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onCancel() {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onContentPlayingChanged(boolean isPlaying) {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onControlsVisibilityChange(int visibility) {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onCreditTime() {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onError(int messageType, boolean showMessage) {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onPauseForAds() {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onPlayerClick(Bundle data) {
                Intrinsics.checkNotNullParameter(data, "data");
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onPlayerFinish(boolean nextEpisodeExists) {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onPosterLoaded() {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onProgress(long progress) {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onReset() {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onResumeAfterAds() {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onStopWatchingTime() {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void showLoader() {
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onInitialized() {
                FrameLayout frameLayout2 = ChatGalleryFragment.this.mVideoContainer;
                Intrinsics.checkNotNull(frameLayout2);
                RootControl rootControl = (RootControl) frameLayout2.findViewById(R.id.root_control);
                if (rootControl == null || ChatGalleryFragment.this.getVideoControlsLayout() == null) {
                    return;
                }
                ChatGalleryFragment.this.videoControls = new ChatGalleryVideoControls(rootControl, ChatGalleryFragment.this.getVideoControlsLayout());
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onPlayerReady() {
                ChatGalleryVideoControls chatGalleryVideoControls = ChatGalleryFragment.this.videoControls;
                if (chatGalleryVideoControls != null) {
                    chatGalleryVideoControls.init();
                }
                ViewGroup videoControlsLayout = ChatGalleryFragment.this.getVideoControlsLayout();
                if (videoControlsLayout != null) {
                    ChatGalleryFragment chatGalleryFragment = ChatGalleryFragment.this;
                    if (chatGalleryFragment.getInflated()) {
                        return;
                    }
                    videoControlsLayout.setVisibility(0);
                    videoControlsLayout.setAlpha(1.0f);
                    chatGalleryFragment.show();
                    chatGalleryFragment.setInflated(true);
                }
            }

            @Override // com.mako.kscore.ksplayer.helpers.PlayerCallback
            public void onProgress(int progress) {
                if (ChatGalleryFragment.this.videoControls != null) {
                    ChatGalleryVideoControls chatGalleryVideoControls = ChatGalleryFragment.this.videoControls;
                    Intrinsics.checkNotNull(chatGalleryVideoControls);
                    chatGalleryVideoControls.onProgress(progress);
                }
            }
        });
    }

    public final void killPlayer(ReasonReport reason) {
        ConstraintLayout root;
        Intrinsics.checkNotNullParameter(reason, "reason");
        FragmentChatGalerryBinding fragmentChatGalerryBinding = this.binding;
        ViewGroup viewGroup = (fragmentChatGalerryBinding == null || (root = fragmentChatGalerryBinding.getRoot()) == null) ? null : (ViewGroup) root.findViewById(R.id.container);
        if (viewGroup != null) {
            KsPlayerManager.INSTANCE.killPlayer(viewGroup, ActionReport.pause, reason, new KillPlayerListener() { // from class: com.channel2.mobile.ui.video.fragments.ChatGalleryFragment$$ExternalSyntheticLambda0
                @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                public final void onKilled() {
                    ChatGalleryFragment.killPlayer$lambda$5$lambda$4();
                }
            });
        }
        ChatGalleryVideoControls chatGalleryVideoControls = this.videoControls;
        if (chatGalleryVideoControls != null) {
            if (chatGalleryVideoControls != null) {
                chatGalleryVideoControls.resetSeekBar();
            }
            this.videoControls = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void show() {
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        Group group;
        int[] referencedIds;
        ConstraintLayout root;
        View viewFindViewById;
        TextView textView5;
        try {
            if (this.numberOfMedias > 1) {
                FragmentChatGalerryBinding fragmentChatGalerryBinding = this.binding;
                if (fragmentChatGalerryBinding != null && (textView5 = fragmentChatGalerryBinding.number) != null) {
                    fadeIn(textView5);
                }
            } else {
                FragmentChatGalerryBinding fragmentChatGalerryBinding2 = this.binding;
                if (fragmentChatGalerryBinding2 != null && (textView = fragmentChatGalerryBinding2.number) != null) {
                    fadeOut(textView);
                }
            }
            FragmentChatGalerryBinding fragmentChatGalerryBinding3 = this.binding;
            if (fragmentChatGalerryBinding3 != null && (group = fragmentChatGalerryBinding3.header) != null && (referencedIds = group.getReferencedIds()) != null) {
                for (int i : referencedIds) {
                    FragmentChatGalerryBinding fragmentChatGalerryBinding4 = this.binding;
                    if (fragmentChatGalerryBinding4 != null && (root = fragmentChatGalerryBinding4.getRoot()) != null && (viewFindViewById = root.findViewById(i)) != null) {
                        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById<View>(id)");
                        fadeIn(viewFindViewById);
                    }
                }
            }
            FragmentChatGalerryBinding fragmentChatGalerryBinding5 = this.binding;
            String text = (fragmentChatGalerryBinding5 == null || (textView4 = fragmentChatGalerryBinding5.footer) == null) ? null : textView4.getText();
            if (text == null) {
            }
            if (text.length() > 0) {
                FragmentChatGalerryBinding fragmentChatGalerryBinding6 = this.binding;
                if (fragmentChatGalerryBinding6 != null && (textView3 = fragmentChatGalerryBinding6.footer) != null) {
                    fadeIn(textView3);
                }
            } else {
                FragmentChatGalerryBinding fragmentChatGalerryBinding7 = this.binding;
                if (fragmentChatGalerryBinding7 != null && (textView2 = fragmentChatGalerryBinding7.footer) != null) {
                    fadeOut(textView2);
                }
            }
            ChatMediaItem chatMediaItem = this.currentItem;
            if (chatMediaItem != null && chatMediaItem.getMediaTypeId() == 2) {
                ViewGroup videoControlsLayout = getVideoControlsLayout();
                if (videoControlsLayout != null) {
                    fadeIn(videoControlsLayout);
                }
            } else {
                ViewGroup videoControlsLayout2 = getVideoControlsLayout();
                if (videoControlsLayout2 != null) {
                    videoControlsLayout2.setVisibility(8);
                }
            }
            this.mVisible = true;
            if (this.currentItem.getMediaTypeId() == 2) {
                this.mShowHideHandler.removeCallbacks(this.mHideRunnable);
                this.mShowHideHandler.postDelayed(this.mHideRunnable, this.AUTO_HIDE_DELAY_MILLIS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void hide() {
        TextView textView;
        TextView textView2;
        Group group;
        int[] referencedIds;
        ConstraintLayout root;
        View viewFindViewById;
        FragmentChatGalerryBinding fragmentChatGalerryBinding = this.binding;
        if (fragmentChatGalerryBinding != null && (group = fragmentChatGalerryBinding.header) != null && (referencedIds = group.getReferencedIds()) != null) {
            for (int i : referencedIds) {
                FragmentChatGalerryBinding fragmentChatGalerryBinding2 = this.binding;
                if (fragmentChatGalerryBinding2 != null && (root = fragmentChatGalerryBinding2.getRoot()) != null && (viewFindViewById = root.findViewById(i)) != null) {
                    Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById<View>(id)");
                    fadeOut(viewFindViewById);
                }
            }
        }
        FragmentChatGalerryBinding fragmentChatGalerryBinding3 = this.binding;
        if (fragmentChatGalerryBinding3 != null && (textView2 = fragmentChatGalerryBinding3.number) != null) {
            fadeOut(textView2);
        }
        FragmentChatGalerryBinding fragmentChatGalerryBinding4 = this.binding;
        if (fragmentChatGalerryBinding4 != null && (textView = fragmentChatGalerryBinding4.footer) != null) {
            fadeOut(textView);
        }
        ViewGroup videoControlsLayout = getVideoControlsLayout();
        if (videoControlsLayout != null) {
            fadeOut(videoControlsLayout);
        }
        this.mVisible = false;
    }

    public final void fadeIn(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(0);
        view.animate().alpha(1.0f).setDuration(this.UI_ANIMATION_DURATION).setListener(null);
    }

    public final void fadeOut(final View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.animate().alpha(0.0f).setDuration(this.UI_ANIMATION_DURATION).setListener(new AnimatorListenerAdapter() { // from class: com.channel2.mobile.ui.video.fragments.ChatGalleryFragment.fadeOut.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                view.setVisibility(8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBottomMargin(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = Utils.convertDipToPixels(requireContext(), i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
