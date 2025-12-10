package com.channel2.mobile.ui.settings.views;

import android.view.View;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.settings.models.SettingsItem;
import java.util.Objects;

/* loaded from: classes2.dex */
public class SettingsVersionHolder extends ViewHolder {
    private CustomTextView displayName;

    public SettingsVersionHolder(View view) {
        super(view);
        this.displayName = (CustomTextView) view.findViewById(R.id.displayName);
    }

    @Override // com.channel2.mobile.ui.settings.views.ViewHolder
    public void initial(SettingsItem settingsItem) {
        CustomTextView customTextView = this.displayName;
        String displayName = settingsItem.getDisplayName();
        Objects.requireNonNull(this.displayName);
        customTextView.setHebText(displayName, "fonts/YonitMedium_v2.ttf");
    }
}
