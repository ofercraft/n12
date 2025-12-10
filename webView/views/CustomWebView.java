package com.channel2.mobile.ui.webView.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.CookieManager;
import android.webkit.WebSettings;

/* loaded from: classes2.dex */
public class CustomWebView extends NestedWebView {
    public float X_MIN_DISTANCE;
    public float Y_MIN_DISTANCE;
    public float downX;
    public float downY;
    public float upX;
    public float upY;

    public CustomWebView(Context context) {
        super(context);
        this.X_MIN_DISTANCE = 100.0f;
        this.Y_MIN_DISTANCE = 100.0f;
        setCustomWebView();
    }

    public CustomWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.X_MIN_DISTANCE = 100.0f;
        this.Y_MIN_DISTANCE = 100.0f;
        setCustomWebView();
    }

    public CustomWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.X_MIN_DISTANCE = 100.0f;
        this.Y_MIN_DISTANCE = 100.0f;
        setCustomWebView();
    }

    @Override // com.channel2.mobile.ui.webView.views.NestedWebView, android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.downX = motionEvent.getX();
            this.downY = motionEvent.getY();
        } else {
            if (action == 2) {
            }
            return super.onTouchEvent(motionEvent);
        }
        this.upX = motionEvent.getX();
        this.upY = motionEvent.getY();
        if (Math.abs(this.downX - this.upX) > this.X_MIN_DISTANCE) {
            requestDisallowInterceptTouchEvent(true);
        } else if (Math.abs(this.downY - this.upY) > this.Y_MIN_DISTANCE) {
            requestDisallowInterceptTouchEvent(false);
        }
        return super.onTouchEvent(motionEvent);
    }

    private void setCustomWebView() {
        getSettings().setJavaScriptEnabled(true);
        getSettings().setMixedContentMode(0);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
        getSettings().setLoadsImagesAutomatically(true);
        getSettings().setSupportZoom(false);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setAllowFileAccess(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        getSettings().setCacheMode(2);
        setLayerType(2, null);
        setWebContentsDebuggingEnabled(true);
    }
}
