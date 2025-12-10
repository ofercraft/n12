package com.channel2.mobile.ui.configs;

import android.content.Context;
import android.location.Location;
import android.provider.Settings;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.helpers.Utils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import java.util.Random;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes2.dex */
public class AppData {
    private String advertisingId;
    private String appId;
    private long appStartTime;
    private String cityName;
    private String deviceId;
    private Location location;
    private MainActivity mainActivity;
    private String userId;

    public AppData(Context context) {
        setUserId(context);
        setAppId(MainConfig.main.getCurrentParam("appId"));
        setAdvertisingId(context);
        setUserPercentNumber(context);
    }

    private void setUserPercentNumber(Context context) {
        if (Utils.getStringFromPreferences(context, context.getResources().getString(R.string.userPercentNumber)).length() == 0) {
            Utils.saveStringToPreferences(context, context.getResources().getString(R.string.userPercentNumber), String.valueOf(new Random().nextInt(1001)));
        }
    }

    private void setUserId(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (string != null) {
            String strSubstring = "00000000000000000000000".substring(0, 23 - string.length());
            string = "A1" + (strSubstring.length() + strSubstring + string);
        }
        this.userId = string;
    }

    private void setDeviceId(String str) {
        this.deviceId = str;
    }

    private void setAppId(String str) {
        this.appId = str;
    }

    private void setAdvertisingId(final Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
            new Thread(new Runnable() { // from class: com.channel2.mobile.ui.configs.AppData.1
                @Override // java.lang.Runnable
                public void run() {
                    AdvertisingIdClient.Info advertisingIdInfo;
                    try {
                        advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                    } catch (GooglePlayServicesNotAvailableException | IOException | Exception unused) {
                    } catch (Throwable th) {
                        AppData.this.advertisingId = AbstractJsonLexerKt.NULL;
                        Context context2 = context;
                        Utils.saveStringToPreferences(context2, context2.getResources().getString(R.string.advertisingId), AppData.this.advertisingId);
                        throw th;
                    }
                    if (advertisingIdInfo == null || advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        AppData.this.advertisingId = AbstractJsonLexerKt.NULL;
                    } else {
                        AppData.this.advertisingId = advertisingIdInfo.getId();
                    }
                    Context context3 = context;
                    Utils.saveStringToPreferences(context3, context3.getResources().getString(R.string.advertisingId), AppData.this.advertisingId);
                }
            }).start();
        }
    }

    public String getAppId() {
        return this.appId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getAdvertisingId(Context context) {
        try {
            return Utils.getStringFromPreferences(context, context.getResources().getString(R.string.advertisingId));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getUserId() {
        return this.userId;
    }

    public MainActivity getMainActivity() {
        return this.mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public long getAppStartTime() {
        return this.appStartTime;
    }

    public void setAppStartTime(long j) {
        this.appStartTime = j;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getCityName() {
        String str = this.cityName;
        return str == null ? "" : str;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }
}
