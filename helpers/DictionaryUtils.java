package com.channel2.mobile.ui.helpers;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.core.content.ContextCompat;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.splash.MyApplication;
import com.cooladata.android.Constants;
import com.permutive.android.thirdparty.ThirdPartyDataEventProcessorImpl;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class DictionaryUtils {
    private static Map<String, String> dictionary = new HashMap();

    public static void initDictionary(Context context) throws UnsupportedEncodingException {
        String strEncode;
        dictionary.put("%DEVICE_ID%", MainConfig.appData.getUserId());
        try {
            dictionary.put("%DEVICE_MODEL%", URLEncoder.encode(Build.MODEL.replace(StringUtils.SPACE, "_"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dictionary.put("%OS_TYPE%", Constants.TRACKER_TYPE);
        dictionary.put("%OS_VERSION%", Build.VERSION.RELEASE);
        try {
            dictionary.put("%LOCALE%", URLEncoder.encode("עברית", "UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
            if (telephonyManager != null && telephonyManager.getNetworkOperatorName() != null) {
                try {
                    strEncode = URLEncoder.encode(telephonyManager.getNetworkOperatorName(), "UTF-8");
                } catch (UnsupportedEncodingException e3) {
                    e3.printStackTrace();
                }
                dictionary.put("%CARRIER%", strEncode);
            } else {
                strEncode = "";
                dictionary.put("%CARRIER%", strEncode);
            }
        } else {
            dictionary.put("%CARRIER%", "");
        }
        dictionary.put("%APP_ID%", MainConfig.appData.getAppId());
        dictionary.put("%VERSION%", (String) Objects.requireNonNull(Utils.getAppVersionName(context) != null ? Utils.getAppVersionName(context) : ""));
        dictionary.put("%REG_ID%", Utils.getStringFromPreferences(context, context.getResources().getString(R.string.regId)));
        dictionary.put("%DEVICE_TYPE%", "");
        if (MainConfig.appData.getAdvertisingId(context) != null) {
            dictionary.put("%ADVERTISER_ID%", MainConfig.appData.getAdvertisingId(context));
        } else {
            dictionary.put("%ADVERTISER_ID%", "");
        }
        dictionary.put("%LANGUAGE%", Locale.getDefault().getLanguage());
        dictionary.put("%DEVICE_STR%", "");
        dictionary.put("%HOUSE_NUMBERS%", "");
        String stringFromPreferences = Utils.getStringFromPreferences(context, context.getResources().getString(R.string.pushTags));
        if (stringFromPreferences != null && stringFromPreferences.length() > 0) {
            dictionary.put("%PUSH_TAGS%", stringFromPreferences);
        } else {
            dictionary.put("%PUSH_TAGS%", "");
        }
        dictionary.put("%IS_PAYING_USER%", "");
        dictionary.put("%IS_PAID_CONTENT%", "");
        dictionary.put("%PRODUCT_BUNDLE_ID%", "");
        dictionary.put("%LAT%", MainConfig.appData.getLocation() != null ? Double.toString(MainConfig.appData.getLocation().getLatitude()) : "");
        dictionary.put("%LNG%", MainConfig.appData.getLocation() != null ? Double.toString(MainConfig.appData.getLocation().getLongitude()) : "");
        dictionary.put("%LOCATION_CAMPAIGN%", "");
        dictionary.put("%LOCATION_SUB_CAMPAIGN%", "");
        dictionary.put("%LOCATION_SUB_CAMPAIGN_WITH_DISTANCE%", "");
        dictionary.put("%LOCATION_MAKO%", "");
        dictionary.put("%PARTNER%", "");
        dictionary.put("%EVENT_NAME%", "");
        dictionary.put("%MESSAGE_ID%", "");
    }

    public static String replaceDictionaryValues(String str) {
        String strCurrentUserId;
        if (str == null) {
            return null;
        }
        String strReplace = str.replace("%RANDOM_NUMBER%", String.valueOf((int) (Math.random() * 1000000.0d)));
        for (String str2 : dictionary.keySet()) {
            if (dictionary.get(str2) != null) {
                strReplace = strReplace.replace(str2, (CharSequence) Objects.requireNonNull(dictionary.get(str2)));
            }
        }
        String strReplace2 = strReplace.replace("%PUSH_TRIGGER%", "").replace("%TOPIC_NAME%", "").replace("%REFRESH%", "").replace("%MAKO_REF_COMP%", "");
        StringBuilder sb = new StringBuilder();
        if (MyApplication.getInstance().getPermutive() != null) {
            strCurrentUserId = MyApplication.getInstance().getPermutive().currentUserId();
            for (int i = 0; i < MyApplication.getInstance().getPermutive().getCurrentSegments().size(); i++) {
                sb.append(MyApplication.getInstance().getPermutive().getCurrentSegments().get(i));
                if (i != MyApplication.getInstance().getPermutive().getCurrentSegments().size() - 1) {
                    sb.append("_");
                }
            }
        } else {
            strCurrentUserId = "";
        }
        String strReplace3 = strReplace2.replace("%IDX_SEGMENT%", sb.toString()).replace("%IDX_USERID%", strCurrentUserId);
        Log.i(ThirdPartyDataEventProcessorImpl.SEGMENTS, "" + ((Object) sb));
        return strReplace3;
    }

    public static void putValue(String str, String str2) {
        dictionary.put(str, str2);
    }

    public static String getValue(String str) {
        return dictionary.get(str);
    }

    public static Map<String, String> getDictionary() {
        return dictionary;
    }

    public static void setDictionary(Map<String, String> map) {
        dictionary = map;
    }
}
