package com.channel2.mobile.ui.helpers;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PlaceholderDemoData {
    public static JSONObject get(Context context, String str) {
        try {
            return new JSONObject(loadJSONFromAsset(context, str));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJSONFromAsset(Context context, String str) throws IOException {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str + ".json");
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            inputStreamOpen.close();
            return new String(bArr, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
