package com.channel2.mobile.ui.helpers;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsIntent;

/* loaded from: classes2.dex */
public class ChromeCustomTabsApi {
    private CustomTabsIntent.Builder mBuilder = new CustomTabsIntent.Builder();

    public void setAddressBarColor(String str) {
        this.mBuilder.setToolbarColor(Color.parseColor(str));
    }

    public void setActionButton(Bitmap bitmap, String str, PendingIntent pendingIntent, boolean z) {
        this.mBuilder.setActionButton(bitmap, str, pendingIntent, z);
    }

    public void addMenuItem(String str, PendingIntent pendingIntent) {
        this.mBuilder.addMenuItem(str, pendingIntent);
    }

    public void openLinkInChromeCustomTabs(Context context, String str) {
        try {
            CustomTabsIntent customTabsIntentBuild = this.mBuilder.build();
            if (!str.startsWith("http://") && !str.startsWith("https://")) {
                str = "http://" + str;
            }
            customTabsIntentBuild.launchUrl(context, Uri.parse(str));
        } catch (Exception unused) {
            Log.i("", "");
        }
    }
}
