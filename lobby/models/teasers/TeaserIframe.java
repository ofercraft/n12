package com.channel2.mobile.ui.lobby.models.teasers;

import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.webView.views.CustomWebView;
import com.google.android.gms.ads.MobileAds;

/* loaded from: classes2.dex */
public class TeaserIframe extends CustomRecyclerViewHolder {
    private CustomWebView webView;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserIframe(View view) {
        super(view);
        CustomWebView customWebView = (CustomWebView) view.findViewById(R.id.webView);
        this.webView = customWebView;
        MobileAds.registerWebView(customWebView);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, final LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        final LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        this.webView.loadUrl(lobbyTeaser.getItemUrl());
        this.webView.setWebViewClient(new WebViewClient() { // from class: com.channel2.mobile.ui.lobby.models.teasers.TeaserIframe.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                if (webView.getHitTestResult().getType() > 0) {
                    lobbyTeaser.setClickUrl(webResourceRequest.getUrl().toString());
                    lobbyFragmentHandler.onClick(lobbyTeaser, null);
                }
                return true;
            }
        });
    }
}
