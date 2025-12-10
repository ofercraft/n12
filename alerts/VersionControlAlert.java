package com.channel2.mobile.ui.alerts;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.helpers.Utils;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class VersionControlAlert {

    public interface Handler {
        void onClick();
    }

    public VersionControlAlert(ConstraintLayout constraintLayout, Handler handler) throws NumberFormatException {
        String appVersionName;
        JSONObject versionControlAlert = MainConfig.main.getVersionControlAlert();
        if (versionControlAlert == null || (appVersionName = Utils.getAppVersionName(constraintLayout.getContext())) == null || appVersionName.length() <= 0) {
            return;
        }
        double d = Double.parseDouble(appVersionName);
        JSONObject jSONObjectOptJSONObject = versionControlAlert.optJSONObject("mandatory");
        if (d < jSONObjectOptJSONObject.optDouble("maxAppVersion")) {
            new DisplayAlert(jSONObjectOptJSONObject, constraintLayout, handler);
            return;
        }
        JSONObject jSONObjectOptJSONObject2 = versionControlAlert.optJSONObject("optional");
        if (d < jSONObjectOptJSONObject2.optDouble("maxAppVersion")) {
            String stringFromPreferences = Utils.getStringFromPreferences(constraintLayout.getContext(), constraintLayout.getContext().getResources().getString(R.string.versionControlAlertDisplayDate));
            if (stringFromPreferences.length() == 0) {
                new DisplayAlert(jSONObjectOptJSONObject2, constraintLayout, handler);
                Utils.saveStringToPreferences(constraintLayout.getContext(), constraintLayout.getContext().getResources().getString(R.string.versionControlAlertDisplayDate), String.valueOf(System.currentTimeMillis()));
                return;
            }
            if (System.currentTimeMillis() > Long.parseLong(stringFromPreferences) + (jSONObjectOptJSONObject2.optInt("daysToDisplayAgain") * 86400000)) {
                new DisplayAlert(jSONObjectOptJSONObject2, constraintLayout, handler);
                Utils.saveStringToPreferences(constraintLayout.getContext(), constraintLayout.getContext().getResources().getString(R.string.versionControlAlertDisplayDate), String.valueOf(System.currentTimeMillis()));
            }
        }
    }
}
