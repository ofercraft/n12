package com.channel2.mobile.ui.lobby.models.ads;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.helpers.ChromeCustomTabsApi;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.google.android.gms.ads.nativead.NativeCustomFormatAd;

/* loaded from: classes2.dex */
public class NativeAdFeedroll extends LobbyAdViewHolder {
    private FrameLayout adContainer;
    private ImageView muteUnMute;
    private ImageView playPause;
    private TextView title;
    private Button videoCallForAction;

    public NativeAdFeedroll(View view) {
        super(view);
        this.adContainer = (FrameLayout) view.findViewById(R.id.adContainer);
        this.title = (TextView) view.findViewById(R.id.title);
        this.playPause = (ImageView) view.findViewById(R.id.playPause);
        this.muteUnMute = (ImageView) view.findViewById(R.id.muteUnMute);
        this.videoCallForAction = (Button) view.findViewById(R.id.ad_call_to_action);
    }

    @Override // com.channel2.mobile.ui.lobby.models.ads.LobbyAdViewHolder, com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyAd lobbyAd = (LobbyAd) item;
        NativeCustomFormatAd ad = lobbyAd.getAd();
        AdManagerAdView adView = lobbyAd.getAdView();
        if (adView == null && ad == null) {
            lobbyAd.view = this;
            Log.i("NativeAdFeedrollNew", "lobbyAd.getAdView() is null");
        } else {
            initView(adView, ad);
        }
        this.title.setText("פרסומת");
        setFontSize(this.title, 12.0f);
    }

    private void initView(AdManagerAdView adManagerAdView, final NativeCustomFormatAd nativeCustomFormatAd) {
        try {
            LobbyAd lobbyAd = (LobbyAd) this.lobbyItem;
            this.adContainer.removeAllViews();
            this.playPause.setVisibility(8);
            this.muteUnMute.setVisibility(8);
            this.videoCallForAction.setVisibility(8);
            if (nativeCustomFormatAd == null) {
                if (adManagerAdView != null) {
                    this.playPause.setVisibility(8);
                    this.muteUnMute.setVisibility(8);
                    this.videoCallForAction.setVisibility(8);
                    FrameLayout frameLayout = (FrameLayout) lobbyAd.getAdView().getParent();
                    if (frameLayout != null) {
                        frameLayout.removeView(lobbyAd.getAdView());
                    }
                    if (lobbyAd.getAdView().getParent() == null) {
                        this.adContainer.addView(lobbyAd.getAdView());
                        return;
                    }
                    return;
                }
                return;
            }
            this.playPause.setVisibility(0);
            this.muteUnMute.setVisibility(0);
            this.videoCallForAction.setVisibility(0);
            MediaContent mediaContent = nativeCustomFormatAd.getMediaContent();
            if (mediaContent != null) {
                final VideoController videoController = mediaContent.getVideoController();
                if (videoController.hasVideoContent()) {
                    nativeCustomFormatAd.recordImpression();
                    this.videoCallForAction.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.models.ads.NativeAdFeedroll.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            nativeCustomFormatAd.performClick("ClickUrl");
                            new ChromeCustomTabsApi();
                        }
                    });
                    this.muteUnMute.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.models.ads.NativeAdFeedroll$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            this.f$0.lambda$initView$0(videoController, view);
                        }
                    });
                    this.playPause.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.models.ads.NativeAdFeedroll.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (videoController.getPlaybackState() == 1) {
                                videoController.pause();
                                NativeAdFeedroll.this.playPause.setImageResource(R.drawable.news12_play);
                            } else {
                                videoController.play();
                                NativeAdFeedroll.this.playPause.setImageResource(R.drawable.news12_pause);
                            }
                        }
                    });
                    videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() { // from class: com.channel2.mobile.ui.lobby.models.ads.NativeAdFeedroll.3
                        @Override // com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks
                        public void onVideoEnd() {
                            super.onVideoEnd();
                            videoController.play();
                        }

                        @Override // com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks
                        public void onVideoStart() {
                            super.onVideoStart();
                        }
                    });
                    nativeCustomFormatAd.getDisplayOpenMeasurement().setView(this.adContainer);
                    videoController.play();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(VideoController videoController, View view) {
        if (videoController.isMuted()) {
            videoController.mute(false);
            this.muteUnMute.setImageResource(R.drawable.volume);
        } else {
            videoController.mute(true);
            this.muteUnMute.setImageResource(R.drawable.volume_off);
        }
    }

    public void updateAd(AdManagerAdView adManagerAdView, NativeCustomFormatAd nativeCustomFormatAd) {
        Log.i("NativeAdFeedrollNew", "updateAd " + this.adContainer.getChildCount());
        if (!(adManagerAdView == null && nativeCustomFormatAd == null) && this.adContainer.getChildCount() == 0) {
            initView(adManagerAdView, nativeCustomFormatAd);
        }
    }
}
