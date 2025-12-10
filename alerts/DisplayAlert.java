package com.channel2.mobile.ui.alerts;

import android.view.View;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.alerts.VersionControlAlert;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.google.android.gms.cast.MediaTrack;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class DisplayAlert {
    public DisplayAlert(JSONObject jSONObject, final ConstraintLayout constraintLayout, final VersionControlAlert.Handler handler) {
        String strOptString = jSONObject.optString("title");
        String strOptString2 = jSONObject.optString(MediaTrack.ROLE_SUBTITLE);
        String strOptString3 = jSONObject.optString("updateButton");
        String strOptString4 = jSONObject.optString("cancelButton");
        CustomTextView customTextView = (CustomTextView) constraintLayout.findViewById(R.id.title);
        Objects.requireNonNull(customTextView);
        customTextView.setHebText(strOptString, "fonts/YonitMedium_v2.ttf");
        CustomTextView customTextView2 = (CustomTextView) constraintLayout.findViewById(R.id.subtitle);
        Objects.requireNonNull(customTextView2);
        customTextView2.setHebText(strOptString2, "fonts/YonitMedium_v2.ttf");
        Button button = (Button) constraintLayout.findViewById(R.id.update);
        button.setText(strOptString3);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.alerts.DisplayAlert.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                handler.onClick();
            }
        });
        if (strOptString4.length() > 0) {
            Button button2 = (Button) constraintLayout.findViewById(R.id.cancel);
            button2.setText(strOptString4);
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.alerts.DisplayAlert.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    constraintLayout.setVisibility(8);
                }
            });
            button2.setVisibility(0);
        }
        constraintLayout.setVisibility(0);
    }
}
