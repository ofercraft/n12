package com.channel2.mobile.ui.settings.views;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.settings.models.SettingsItem;

/* loaded from: classes2.dex */
public abstract class ViewHolder extends RecyclerView.ViewHolder {
    public void initial(SettingsItem settingsItem) {
    }

    public ViewHolder(View view) {
        super(view);
    }

    protected void setFontSize(TextView textView, float f) {
        textView.setTextSize(2, f * Utils.getFloatFromPreferences(textView.getContext(), "zoom").floatValue());
    }
}
