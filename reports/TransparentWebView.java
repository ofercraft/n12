package com.channel2.mobile.ui.reports;

import android.content.Context;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.channel2.mobile.ui.helpers.DictionaryUtils;
import com.channel2.mobile.ui.webView.views.CustomWebView;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class TransparentWebView {
    private static ArrayList<WebView> webViews;

    public static void report(Context context, String str) {
        if (context != null) {
            String strReplaceDictionaryValues = DictionaryUtils.replaceDictionaryValues(str);
            if (webViews == null) {
                webViews = new ArrayList<>();
            }
            CustomWebView customWebView = new CustomWebView(context);
            customWebView.getSettings().setJavaScriptEnabled(true);
            customWebView.setWebChromeClient(new WebChromeClient());
            customWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            customWebView.getSettings().setAllowFileAccess(true);
            customWebView.getSettings().setDomStorageEnabled(true);
            Log.i("TransparentWebView", "Start: " + strReplaceDictionaryValues);
            Log.i("checkReport", "start = " + strReplaceDictionaryValues);
            Log.i("checkReportsForChats", "" + strReplaceDictionaryValues);
            customWebView.setWebViewClient(new WebViewClient() { // from class: com.channel2.mobile.ui.reports.TransparentWebView.1
                @Override // android.webkit.WebViewClient
                public void onPageFinished(WebView webView, String str2) {
                    super.onPageFinished(webView, str2);
                    Log.i("TransparentWebView", "Finish: " + str2);
                    Log.i("checkReport", "Finish = " + str2);
                    if (TransparentWebView.webViews != null && TransparentWebView.webViews.size() > 0) {
                        TransparentWebView.webViews.remove(webView);
                    }
                    if (webView.getContext() != null) {
                        webView.destroy();
                    }
                }

                @Override // android.webkit.WebViewClient
                public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                    super.onReceivedError(webView, webResourceRequest, webResourceError);
                    Log.i("TransparentWebView", "Error: " + webView.getOriginalUrl());
                }
            });
            customWebView.loadUrl(strReplaceDictionaryValues);
            webViews.add(customWebView);
        }
    }
}
