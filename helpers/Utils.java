package com.channel2.mobile.ui.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import com.appsflyer.AppsFlyerProperties;
import com.channel2.mobile.ui.BuildConfig;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.lobby.models.ads.DFP;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class Utils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Map<String, String> hexTransparentValues;

    public static int getToolBarHeight(Context context) {
        int dimensionPixelSize = 0;
        try {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.actionBarSize});
            dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1);
            typedArrayObtainStyledAttributes.recycle();
            return dimensionPixelSize;
        } catch (Exception e) {
            e.printStackTrace();
            return dimensionPixelSize;
        }
    }

    public static int convertDipToPixels(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        if (context == null) {
            return 1000;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static String getHexTransparentValue(String str) {
        if (hexTransparentValues == null) {
            hexTransparentValues = createMap();
        }
        return hexTransparentValues.get(str);
    }

    public static String getCleanColorValue(String str) {
        return str.replace("#", "").substring(0, 6);
    }

    private static Map<String, String> createMap() {
        HashMap map = new HashMap();
        map.put(IdManager.DEFAULT_VERSION_NAME, "FF");
        map.put("9.0", "E6");
        map.put("8.0", "CC");
        map.put("7.0", "B3");
        map.put("6.0", "99");
        map.put("5.0", "80");
        return map;
    }

    public static String getAbsoluteUrl(String str) {
        if (str.length() > 0) {
            try {
                String currentParam = MainConfig.main.getCurrentParam("articleDomain");
                if (!str.startsWith("http") && !str.startsWith("new12ImageGallery") && !str.startsWith("app://playVideo")) {
                    return currentParam + str;
                }
                Uri uri = Uri.parse(str);
                Uri uri2 = Uri.parse(currentParam);
                String authority = uri.getAuthority();
                CharSequence authority2 = uri2.getAuthority();
                return (authority == null || authority2 == null) ? str : (authority.contains("mobile.mako.co.il") || authority.contains("www.mako.co.il")) ? str.replace(authority, authority2) : str;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String hashSHA256(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            return bytesToHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String bytesToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append('0');
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static void openSms(String str, Context context) throws UnsupportedEncodingException {
        String[] strArrSplit = str.split("[;?]");
        String str2 = strArrSplit[0];
        String strDecode = null;
        String str3 = strArrSplit.length > 1 ? strArrSplit[1] : null;
        String[] strArrSplit2 = str2.split(":/*");
        String str4 = strArrSplit2.length > 1 ? strArrSplit2[1] : "";
        if (str3 != null) {
            try {
                String[] strArrSplit3 = str3.split("=");
                if (strArrSplit3.length > 1) {
                    strDecode = URLDecoder.decode(strArrSplit3[1], "UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + Uri.encode(str4)));
        if (strDecode != null) {
            intent.putExtra("sms_body", strDecode);
        }
        intent.setFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean regexMatch(String str, String str2) {
        try {
            return Pattern.compile(str).matcher(str2).matches();
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getStringFromPreferences(Context context, String str) {
        return context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_file_key), 0).getString(str, "");
    }

    public static void saveStringToPreferences(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_file_key), 0).edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    public static void saveBoolToPreferences(Context context, String str, Boolean bool) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_file_key), 0).edit();
        editorEdit.putBoolean(str, bool.booleanValue());
        editorEdit.apply();
    }

    public static boolean getBoolFromPreferences(Context context, String str) {
        return context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_file_key), 0).getBoolean(str, true);
    }

    public static void saveFloatToPreferences(Context context, String str, Float f) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_file_key), 0).edit();
        editorEdit.putFloat(str, f.floatValue());
        editorEdit.apply();
    }

    public static Float getFloatFromPreferences(Context context, String str) {
        return Float.valueOf(context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_file_key), 0).getFloat(str, 1.0f));
    }

    public static boolean isExistInPreferences(Context context, String str) {
        return context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_file_key), 0).contains(str);
    }

    public static Boolean isFirstTime(Context context) {
        return Boolean.valueOf(context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_file_key), 0).getBoolean("isFirstTime", true));
    }

    public static void setNotFirstTime(Context context) {
        context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences_file_key), 0).edit().putBoolean("isFirstTime", false).apply();
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAppBuildNumber(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getLinkFromDeepLink(String str) {
        if (str.contains("link=")) {
            try {
                String[] strArrSplit = str.split("link=");
                if (strArrSplit.length > 1) {
                    return strArrSplit[1].replace("?a=:", "");
                }
                if (strArrSplit.length == 1) {
                    return "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static StringBuilder buildMessageFromDeviceData(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n\n\n\n\nUDID: ");
        sb.append(MainConfig.appData.getUserId());
        sb.append("\nApp id: ");
        sb.append(MainConfig.appData.getAppId());
        sb.append("\nReg id: ");
        sb.append(getStringFromPreferences(context, context.getResources().getString(R.string.regId)));
        sb.append("\nApp Version: ");
        sb.append(getAppVersionName(context));
        sb.append("\nApp Build Number: ");
        sb.append(getAppBuildNumber(context));
        sb.append("\nDevice: ");
        sb.append(getDeviceModel());
        sb.append("\nAndroid Version: ");
        sb.append(getOsVersion());
        sb.append("\nAllow External Push Notification: ");
        sb.append(getBoolFromPreferences(context, context.getResources().getString(R.string.outsidePush)));
        sb.append("\nAllow Internal Notifications: ");
        sb.append(getBoolFromPreferences(context, context.getResources().getString(R.string.insidePush)));
        sb.append("\nRegistered Push Tags: ");
        sb.append(getStringFromPreferences(context, context.getResources().getString(R.string.pushTags)));
        sb.append("\nAllow Red Dot: ");
        sb.append(getBoolFromPreferences(context, context.getResources().getString(R.string.redDot)));
        sb.append(StringUtils.LF);
        return sb;
    }

    public static String getDeviceModel() {
        return Build.MANUFACTURER + StringUtils.SPACE + Build.MODEL;
    }

    public static boolean isHuawei() {
        return Build.MANUFACTURER.equals("HUAWEI");
    }

    public static String getOsVersion() {
        return Build.VERSION.RELEASE + " Api level " + Build.VERSION.SDK_INT;
    }

    public static String getDesktopUrl(String str) {
        if (Uri.parse(str).getHost() == null) {
            str = MainConfig.main.getCurrentParam("dfpBaseContentURL") + str;
        }
        return str.replace(MainConfig.main.getCurrentParam("articleDomain"), MainConfig.main.getCurrentParam("dfpBaseContentURL"));
    }

    public static void setDfpCustParams(AdManagerAdRequest.Builder builder, DFP dfp) throws JSONException {
        if (MainConfig.appData.getLocation() != null) {
            builder.addCustomTargeting("CITY", MainConfig.appData.getCityName().toUpperCase());
            builder.addCustomTargeting("LATITUDE", Double.toString(MainConfig.appData.getLocation().getLatitude()));
            builder.addCustomTargeting("LONGITUDE", Double.toString(MainConfig.appData.getLocation().getLongitude()));
        }
        builder.addCustomTargeting("version", DictionaryUtils.replaceDictionaryValues("%VERSION%"));
        builder.addCustomTargeting("advertiserId", DictionaryUtils.replaceDictionaryValues("%ADVERTISER_ID%"));
        builder.addCustomTargeting(AppsFlyerProperties.APP_ID, DictionaryUtils.replaceDictionaryValues("%APP_ID%"));
        builder.addCustomTargeting("deviceid", DictionaryUtils.replaceDictionaryValues("%DEVICE_ID%"));
        builder.addCustomTargeting("deviceModel", DictionaryUtils.replaceDictionaryValues("%DEVICE_MODEL%"));
        builder.addCustomTargeting("osVersion", DictionaryUtils.replaceDictionaryValues("%OS_VERSION%"));
        builder.addCustomTargeting("bundleId", BuildConfig.APPLICATION_ID);
        builder.addCustomTargeting("language", DictionaryUtils.replaceDictionaryValues("%DEVICE_MODEL%"));
        builder.addCustomTargeting("osVersion", DictionaryUtils.replaceDictionaryValues("android"));
        if (dfp != null) {
            try {
                builder.addCustomTargeting("FORMAT", dfp.getIu().split(RemoteSettings.FORWARD_SLASH_STRING)[r0.length - 1].toUpperCase());
            } catch (Exception e) {
                e.printStackTrace();
            }
            builder.addCustomTargeting("MAKOPAGE", dfp.getMakoPage());
            if (dfp.getCustParams() != null) {
                Iterator<String> itKeys = dfp.getCustParams().keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    try {
                        Object obj = dfp.getCustParams().get(next);
                        if (!obj.toString().isEmpty() && !obj.toString().startsWith("%")) {
                            builder.addCustomTargeting(next, obj.toString());
                        }
                    } catch (JSONException unused) {
                    }
                }
            }
        }
    }

    public static String msToTimeString_HH_mm_ss(long j) {
        String str = String.format("%02d:%02d:%02d", Long.valueOf(TimeUnit.MILLISECONDS.toHours(j)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(j) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(j))), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j))));
        return str.startsWith("00:") ? str.replaceFirst("00:", "") : str;
    }

    public static String msToTimeString_HH_mm(long j) {
        return String.format("%02d:%02d", Long.valueOf(TimeUnit.MILLISECONDS.toHours(j)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(j) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(j))));
    }

    public static String msToTimeString_mm_ss(long j) {
        return String.format("%02d:%02d", Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(j)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j))));
    }

    public static String utcToTimeString_HH_mm(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
        return simpleDateFormat.format(Long.valueOf(j));
    }

    public static String getFormatDuration(long j, String str) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(j * 1000);
        return DateFormat.format("dd-MM-yyyy", calendar).toString();
    }
}
