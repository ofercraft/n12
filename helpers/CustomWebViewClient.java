package com.channel2.mobile.ui.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.core.net.MailTo;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.lobby.models.items.LinkItem;
import com.channel2.mobile.ui.routing.RoutingManager;
import com.channel2.mobile.ui.webView.controllers.WebViewFragment;
import com.channel2.mobile.ui.webView.views.CustomWebView;
import com.cooladata.android.Constants;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.MobileAds;
import com.outbrain.OBSDK.OBBridge;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CustomWebViewClient extends WebViewClient {
    private WebViewFragment fragment;
    private CustomWebViewHandler handler;
    private ArrayList<String> javascriptToRunOnPageEnd = new ArrayList<>();
    private MainActivity mActivity;
    private boolean pageLoaded;
    private int tabId;

    public interface CustomWebViewHandler {
        void onLoaded();

        void onOpenUrl(String str);

        void onUserClicked();
    }

    private void notificationsAdd(WebView webView, String[] strArr) {
    }

    private void notificationsRemove(WebView webView, String[] strArr) {
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
    }

    public CustomWebViewClient(WebViewFragment webViewFragment, MainActivity mainActivity, int i, CustomWebViewHandler customWebViewHandler) {
        this.handler = customWebViewHandler;
        this.tabId = i;
        this.fragment = webViewFragment;
        this.mActivity = mainActivity;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        if (webResourceRequest != null && webResourceRequest.getUrl() != null && this.handler != null) {
            return overrideUrlLoading(webResourceRequest.getUrl().toString(), webView);
        }
        return super.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str != null && this.handler != null) {
            return overrideUrlLoading(str, webView);
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    private boolean overrideUrlLoading(final String str, final WebView webView) throws UnsupportedEncodingException {
        CustomWebViewHandler customWebViewHandler;
        boolean z = false;
        if (str == null || this.handler == null) {
            return false;
        }
        if (OBBridge.isOutbrainPaidUrl(str)) {
            new ChromeCustomTabsApi().openLinkInChromeCustomTabs(this.mActivity, str);
            return true;
        }
        if (str.startsWith("https://traffic.outbrain.com/network/redir")) {
            CustomWebView customWebView = new CustomWebView(this.mActivity);
            MobileAds.registerWebView(customWebView);
            customWebView.setWebViewClient(new WebViewClient() { // from class: com.channel2.mobile.ui.helpers.CustomWebViewClient.1
                @Override // android.webkit.WebViewClient
                public void onPageStarted(WebView webView2, String str2, Bitmap bitmap) throws UnsupportedEncodingException {
                    String strDecode;
                    if (str2.contains("mako.co.il")) {
                        try {
                            strDecode = URLDecoder.decode(Utils.getAbsoluteUrl(str2), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            strDecode = null;
                        }
                        CustomWebViewClient.this.handler.onOpenUrl(strDecode);
                        webView2.destroy();
                    }
                }
            });
            customWebView.loadUrl(str);
            return true;
        }
        if (str.startsWith("https://www.facebook.com/plugins/close_popup.php") && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        if (str.contains(FacebookSdk.FACEBOOK_COM) || str.startsWith("https://fbapi") || str.startsWith("https://fbauth") || str.startsWith("https://fb-messenger-api") || str.contains("access_token=")) {
            new ChromeCustomTabsApi().openLinkInChromeCustomTabs(this.mActivity, str);
            return true;
        }
        if (webView.getHitTestResult() != null && webView.getHitTestResult().getType() > 0) {
            z = true;
        }
        if (z || str.startsWith("app://playVideo")) {
            this.handler.onUserClicked();
        }
        if (str.startsWith("market://")) {
            webView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            this.mActivity.finish();
            return true;
        }
        if (str.equals("file:///android_asset/webkit/")) {
            return true;
        }
        if (str.startsWith("sms:")) {
            Utils.openSms(str, this.mActivity);
            return true;
        }
        if (str.startsWith("tel:")) {
            this.mActivity.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(str)));
            return true;
        }
        if (str.startsWith("js-frame") || str.startsWith("js-bridge")) {
            Handler handler = new Handler(Looper.getMainLooper());
            if (str.contains("makoOpenLightbox")) {
                handler.postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.helpers.CustomWebViewClient.2
                    @Override // java.lang.Runnable
                    public void run() throws JSONException, UnsupportedEncodingException {
                        CustomWebViewClient.this.handleJS(webView, str);
                    }
                }, 10L);
            } else {
                handler.postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.helpers.CustomWebViewClient.3
                    @Override // java.lang.Runnable
                    public void run() throws JSONException, UnsupportedEncodingException {
                        CustomWebViewClient.this.handleJS(webView, str);
                    }
                }, 500L);
            }
            return true;
        }
        if (str.contains(MailTo.MAILTO_SCHEME)) {
            this.mActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        }
        if (str.startsWith("intent:")) {
            parseWebIntent(str);
            return true;
        }
        try {
            String strDecode = URLDecoder.decode(Utils.getAbsoluteUrl(str), "UTF-8");
            if ((z || str.startsWith("app://playVideo")) && (customWebViewHandler = this.handler) != null) {
                customWebViewHandler.onOpenUrl(strDecode);
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            new ChromeCustomTabsApi().openLinkInChromeCustomTabs(this.mActivity, str);
            return true;
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
        WebViewFragment webViewFragment = this.fragment;
        if (webViewFragment != null && (webViewFragment.getDfp() == null || this.fragment.getDfp().getIu().length() == 0)) {
            loadMakoCatDFP(webView);
        }
        WebViewFragment webViewFragment2 = this.fragment;
        if (webViewFragment2 != null && !webViewFragment2.isArticleView()) {
            loadChannelName(webView);
            return;
        }
        setShareFields(webView);
        getCanonical(webView);
        getOgUrl(webView);
        loadVcmID(webView);
        getFontSize(webView);
        loadPresentationArticle(webView);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.pageLoaded = true;
        ArrayList<String> arrayList = this.javascriptToRunOnPageEnd;
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < this.javascriptToRunOnPageEnd.size(); i++) {
                webView.loadUrl(this.javascriptToRunOnPageEnd.get(i));
            }
        }
        CustomWebViewHandler customWebViewHandler = this.handler;
        if (customWebViewHandler != null) {
            customWebViewHandler.onLoaded();
        }
    }

    private void parseWebIntent(String str) throws UnsupportedEncodingException {
        Intent intent;
        String[] strArrSplit = str.split(";");
        if (strArrSplit.length < 3) {
            return;
        }
        try {
            String str2 = strArrSplit[1];
            String strSubstring = str2.substring(str2.indexOf("=") + 1);
            String str3 = strArrSplit[2];
            String strSubstring2 = str3.substring(str3.indexOf("=") + 1);
            try {
                strSubstring = URLDecoder.decode(strSubstring, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
            }
            if (isAppInstalled(this.mActivity, strSubstring2)) {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(strSubstring));
            } else {
                intent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + strSubstring2));
            }
            this.mActivity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isAppInstalled(Context context, String str) throws PackageManager.NameNotFoundException {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleJS(WebView webView, String str) throws JSONException, UnsupportedEncodingException {
        String[] strArrSplit = str.split(CertificateUtil.DELIMITER);
        if (strArrSplit[1].equals("appId")) {
            fetchAppId(webView, strArrSplit);
            return;
        }
        if (strArrSplit[1].equals("makoDeviceID")) {
            fetchDeviceId(webView, strArrSplit);
            return;
        }
        if (strArrSplit[1].equals("disableScreenTimeout")) {
            webView.setKeepScreenOn(true);
            return;
        }
        if (strArrSplit[1].equals("advertisingIdentifier")) {
            fetchAdvertisingId(webView, strArrSplit);
            return;
        }
        if (strArrSplit[1].equals("back")) {
            this.mActivity.onBackPressed();
            return;
        }
        if (strArrSplit[1].equals("makoResolveVariable")) {
            fetchResolveVariable(webView, strArrSplit);
            return;
        }
        if (strArrSplit[1].equals("notificationsAdd")) {
            notificationsAdd(webView, strArrSplit);
            return;
        }
        if (strArrSplit[1].equals("notificationsRemove")) {
            notificationsRemove(webView, strArrSplit);
            return;
        }
        if (strArrSplit[1].equals("notificationsGet")) {
            notificationsGet(webView, strArrSplit);
            return;
        }
        if (strArrSplit[1].equals("identifyUser")) {
            JSONObject jSONObject = new JSONObject();
            try {
                String str2 = "TV_" + MainConfig.appData.getUserId();
                jSONObject.putOpt("device_id", str2);
                jSONObject.putOpt("os_type", Constants.TRACKER_TYPE);
                jSONObject.putOpt("os_version", Build.VERSION.RELEASE);
                jSONObject.putOpt("advertising_id", MainConfig.appData.getAdvertisingId(this.mActivity));
                Calendar calendar = Calendar.getInstance();
                int i = calendar.get(11);
                int i2 = calendar.get(12);
                jSONObject.putOpt("timestamp", Long.valueOf(calendar.getTime().getTime()));
                jSONObject.putOpt("signature", Utils.hashSHA256(str2 + "makoIdentification" + i + i2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String string = jSONObject.toString();
            try {
                string = URLEncoder.encode(string, "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            webView.evaluateJavascript("javascript:" + strArrSplit[2] + "." + strArrSplit[3] + "(" + strArrSplit[4] + ",'" + string + "')", new ValueCallback<String>() { // from class: com.channel2.mobile.ui.helpers.CustomWebViewClient.4
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(String str3) {
                }
            });
            return;
        }
        if (strArrSplit[1].equals("sendFacebookEvent") && strArrSplit.length > 5) {
            String str3 = strArrSplit[5];
            try {
                AppEventsLogger appEventsLoggerNewLogger = AppEventsLogger.newLogger(this.mActivity);
                JSONObject jSONObject2 = new JSONObject(URLDecoder.decode(str3, "UTF-8"));
                Bundle bundle = new Bundle();
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    bundle.putString(next, jSONObject2.getString(next));
                }
                appEventsLoggerNewLogger.logEvent(jSONObject2.optString("eventName", "facebookEvent"), jSONObject2.optDouble("valueToSum", 0.0d), bundle);
                return;
            } catch (Exception e3) {
                e3.printStackTrace();
                return;
            }
        }
        if (strArrSplit[1].equals("shareOnFacebook") && strArrSplit.length > 5) {
            String str4 = strArrSplit[5];
            if (strArrSplit.length > 6) {
                str4 = strArrSplit[5] + CertificateUtil.DELIMITER + strArrSplit[6];
            }
            if (ShareDialog.canShow((Class<? extends ShareContent<?, ?>>) ShareLinkContent.class)) {
                new ShareDialog(this.mActivity).show(new ShareLinkContent.Builder().setContentUrl(Uri.parse(str4)).build());
                return;
            }
            return;
        }
        if (strArrSplit[1].equals("shareOnWhatsapp") && strArrSplit.length > 5) {
            String str5 = strArrSplit[5];
            if (strArrSplit.length > 6) {
                str5 = strArrSplit[5] + CertificateUtil.DELIMITER + strArrSplit[6];
            }
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.setPackage("com.whatsapp");
            try {
                intent.putExtra("android.intent.extra.TEXT", str5);
                this.mActivity.startActivity(intent);
                return;
            } catch (Exception unused) {
                return;
            }
        }
        if (strArrSplit[1].equals("shareOnDeviceShareDialog") && strArrSplit.length > 5) {
            String str6 = strArrSplit[5];
            if (strArrSplit.length > 6) {
                str6 = strArrSplit[5] + CertificateUtil.DELIMITER + strArrSplit[6];
            }
            Intent intent2 = new Intent("android.intent.action.SEND");
            intent2.setType("text/plain");
            intent2.putExtra("android.intent.extra.TEXT", str6);
            this.mActivity.startActivity(Intent.createChooser(intent2, "שתף"));
            return;
        }
        if (strArrSplit[1].equals("shareOnMail") && strArrSplit.length > 5) {
            String str7 = strArrSplit[5];
            if (strArrSplit.length > 6) {
                str7 = strArrSplit[5] + CertificateUtil.DELIMITER + strArrSplit[6];
            }
            Intent intent3 = new Intent("android.intent.action.SEND");
            intent3.setType("message/rfc822");
            intent3.putExtra("android.intent.extra.TEXT", str7);
            this.mActivity.startActivity(Intent.createChooser(intent3, "שלח מייל"));
            return;
        }
        if (strArrSplit[1].equalsIgnoreCase("makoOpenLightbox")) {
            openGallery(strArrSplit, str);
        }
    }

    private void fetchDeviceId(WebView webView, String[] strArr) {
        webView.loadUrl("javascript:" + strArr[2] + "." + strArr[3] + "(" + strArr[4] + ",'" + MainConfig.appData.getUserId() + "')");
    }

    private void fetchAppId(WebView webView, String[] strArr) {
        webView.loadUrl("javascript:" + strArr[2] + "." + strArr[3] + "(" + strArr[4] + ",'" + MainConfig.appData.getAppId() + "')");
    }

    private void fetchAdvertisingId(WebView webView, String[] strArr) {
        webView.loadUrl("javascript:" + strArr[2] + "." + strArr[3] + "(" + strArr[4] + ",'" + MainConfig.appData.getAdvertisingId(this.mActivity) + "')");
    }

    private void notificationsGet(WebView webView, String[] strArr) {
        String str = "javascript:" + strArr[2] + "." + strArr[3] + "(" + strArr[4] + ",'')";
        if (this.pageLoaded) {
            webView.loadUrl(str);
            return;
        }
        if (this.javascriptToRunOnPageEnd == null) {
            this.javascriptToRunOnPageEnd = new ArrayList<>();
        }
        this.javascriptToRunOnPageEnd.add(str);
    }

    private void fetchResolveVariable(WebView webView, String[] strArr) {
        webView.loadUrl("javascript:" + strArr[2] + "." + strArr[3] + "(" + strArr[4] + ",'" + DictionaryUtils.replaceDictionaryValues(strArr[5]) + "')");
    }

    private void openGallery(String[] strArr, String str) throws JSONException, UnsupportedEncodingException {
        try {
            String strDecode = URLDecoder.decode(str, "UTF-8");
            String strSubstring = strDecode.substring(strDecode.indexOf("{"));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", "new12ImageGallery" + strSubstring);
            LinkItem linkItem = new LinkItem(jSONObject);
            linkItem.setMako_ref_comp("article");
            WebViewFragment webViewFragment = this.fragment;
            if (webViewFragment == null || webViewFragment.getFragmentContainerId() == 0) {
                return;
            }
            RoutingManager.goToNextScreen(this.fragment.getFragmentContainerId(), linkItem, this.tabId, this.mActivity, null);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void setShareFields(WebView webView) {
        webView.loadUrl("javascript: var title = '';var url = '';var description = '';var image = '';var links = document.getElementsByTagName('meta');for(var i=0; i < links.length; i++){if(links[i].getAttribute('property') == 'og:url'){url = links[i].getAttribute('content');}if(links[i].getAttribute('property') == 'og:image'){image = links[i].getAttribute('content');}if(links[i].getAttribute('property') == 'og:description'){description = links[i].getAttribute('content');}if(links[i].getAttribute('property') == 'og:title'){title = links[i].getAttribute('content');}}title.length > 0 && Android.setShareFields(title, url, description, image);");
    }

    private void getOgUrl(WebView webView) {
        webView.loadUrl("javascript: var url = document.querySelectorAll(\"meta[property='og:url']\")[0].content;url && Android.setOgUrl(url);");
    }

    private void getCanonical(WebView webView) {
        webView.loadUrl("javascript: var url = document.querySelectorAll(\"link[rel='canonical']\")[0].href;url && Android.setCanonical(url);");
    }

    private void loadChannelName(WebView webView) {
        webView.loadUrl("javascript: var header = document.querySelector(\"meta[name='headertext']\");header && Android.setChannelName(header.content);");
    }

    private void loadVcmID(WebView webView) {
        webView.loadUrl("javascript: if(vcmidOfContent && Android){Android.setVcmID(vcmidOfContent);}");
    }

    private void loadMakoCatDFP(WebView webView) {
        webView.loadUrl("javascript: if(makoCatDFP && Android){Android.setMakoCatDFP(JSON.stringify(makoCatDFP));}");
    }

    private void loadPresentationArticle(WebView webView) {
        webView.loadUrl("javascript: var presentation_article = document.querySelector(\"meta[name='presentation_article']\");presentation_article && Android.setPresentationArticle(presentation_article.content);");
    }

    private void loadChannelID(WebView webView) {
        webView.loadUrl("javascript: if(currentChannelId && Android){Android.setChannelID(currentChannelId);}");
    }

    private void loadHeaderChannelID(WebView webView) {
        webView.loadUrl("javascript: var headerChannelID = document.querySelector(\"meta[name='headertextChannel']\");headerChannelID && Android.setHeaderChannelID(headerChannelID.content);");
    }

    public void setFontSize(WebView webView, int i) {
        webView.loadUrl("javascript: var vv = changeArticleFontSize(" + i + ");");
    }

    public void getFontSize(WebView webView) {
        webView.loadUrl("javascript: var mako_articleFontSize = window.localStorage.getItem('mako_articleFontSize');if(mako_articleFontSize && Android){Android.setFontSize(mako_articleFontSize)};");
    }
}
