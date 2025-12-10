package com.channel2.mobile.ui.helpers;

import android.content.Context;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.reports.TransparentWebView;

/* loaded from: classes2.dex */
public class AnalyticsManager {
    private static final AnalyticsManager instance = new AnalyticsManager();

    public static AnalyticsManager getInstance() {
        return instance;
    }

    private AnalyticsManager() {
    }

    public void reportPageView(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String strReplace;
        try {
            String strReplace2 = MainConfig.main.getCurrentSource("reportMetrics").replace("%GUID%", str4.equals("") ? str : str4);
            if (str.equals("")) {
                str = str4;
            }
            String strReplace3 = strReplace2.replace("%VCM_ID%", str).replace("%CONTENT_TYPE%", str2).replace("%TOPIC_NAME%", str7);
            if (str3 != null && str3.length() > 0) {
                strReplace = strReplace3.replace("%FRIENDLY_URL%", str3.replace("=", "%3D"));
            } else {
                strReplace = strReplace3.replace("%FRIENDLY_URL%", "");
            }
            String strReplace4 = strReplace.replace("%PARTNER%", str5);
            if (strReplace4.contains("EVENT_NAME")) {
                strReplace4 = strReplace4.replace("%EVENT_NAME%", str6);
            }
            TransparentWebView.report(context, strReplace4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
