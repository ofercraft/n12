package com.channel2.mobile.ui.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.configs.MainConfig;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public class PermissionRequestsManager implements LocationListener {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private Context context;
    private LocationManager locationManager;
    private MainActivity mainActivity;
    private final ActivityResultLauncher<String[]> requestPermissionLauncher;

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public PermissionRequestsManager(MainActivity mainActivity, Context context) {
        this.mainActivity = mainActivity;
        this.context = context;
        this.requestPermissionLauncher = mainActivity.registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.channel2.mobile.ui.location.PermissionRequestsManager$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) throws IOException {
                this.f$0.lambda$new$0((Map) obj);
            }
        });
        this.locationManager = (LocationManager) mainActivity.getSystemService(FirebaseAnalytics.Param.LOCATION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Map map) throws IOException {
        Log.d("newPermission", "onResult: " + map.toString());
        if (Boolean.TRUE.equals(map.get("android.permission.ACCESS_FINE_LOCATION"))) {
            checkLocationPermission();
        }
    }

    public void requestPermissions() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        if (ContextCompat.checkSelfPermission(this.mainActivity, "android.permission.POST_NOTIFICATIONS") != 0 && Build.VERSION.SDK_INT >= 33) {
            arrayList.add("android.permission.POST_NOTIFICATIONS");
        }
        this.requestPermissionLauncher.launch((String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    private void checkLocationPermission() throws IOException {
        LocationManager locationManager;
        if (ContextCompat.checkSelfPermission(this.mainActivity, "android.permission.ACCESS_FINE_LOCATION") != 0 || (locationManager = this.locationManager) == null) {
            return;
        }
        Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
        if (lastKnownLocation != null && lastKnownLocation.getTime() > Calendar.getInstance().getTimeInMillis() - 120000) {
            setLocation(lastKnownLocation);
            return;
        }
        try {
            if (this.locationManager.isProviderEnabled("gps")) {
                this.locationManager.requestLocationUpdates("gps", 0L, 0.0f, this);
            }
            if (this.locationManager.isProviderEnabled("network")) {
                this.locationManager.requestLocationUpdates("network", 0L, 0.0f, this);
            }
            if (this.locationManager.isProviderEnabled("passive")) {
                this.locationManager.requestLocationUpdates("passive", 0L, 0.0f, this);
            }
        } catch (Exception unused) {
        }
    }

    private void setLocation(Location location) throws IOException {
        List<Address> fromLocation;
        if (location != null) {
            MainConfig.appData.setLocation(location);
            try {
                fromLocation = new Geocoder(this.context, Locale.getDefault()).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } catch (Exception e) {
                e.printStackTrace();
                fromLocation = null;
            }
            if (fromLocation == null || fromLocation.size() <= 0 || fromLocation.get(0).getLocality() == null) {
                return;
            }
            String locality = fromLocation.get(0).getLocality();
            MainConfig.appData.setCityName(locality);
            Log.d("testCity", locality);
        }
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        if (location != null) {
            try {
                setLocation(location);
                Log.d("LocationChanged", location.getLatitude() + " and " + location.getLongitude());
                this.locationManager.removeUpdates(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
