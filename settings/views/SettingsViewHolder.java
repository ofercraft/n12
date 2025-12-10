package com.channel2.mobile.ui.settings.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.settings.models.SettingsItem;
import java.util.Objects;

/* loaded from: classes2.dex */
public class SettingsViewHolder extends ViewHolder {
    private ImageView arrowLeft;
    private CustomTextView displayName;
    private View divider;
    private Switch switchButton;

    public SettingsViewHolder(View view) {
        super(view);
        this.displayName = (CustomTextView) view.findViewById(R.id.displayName);
        this.divider = view.findViewById(R.id.divider);
        this.arrowLeft = (ImageView) view.findViewById(R.id.arrowLeft);
        this.switchButton = (Switch) view.findViewById(R.id.switchButton);
    }

    @Override // com.channel2.mobile.ui.settings.views.ViewHolder
    public void initial(SettingsItem settingsItem) {
        CustomTextView customTextView = this.displayName;
        String displayName = settingsItem.getDisplayName();
        Objects.requireNonNull(this.displayName);
        customTextView.setHebText(displayName, "fonts/YonitRegular_v2.ttf");
        setFontSize(this.displayName, 21.0f);
        if (settingsItem.isDefaultDivider()) {
            this.divider.setVisibility(0);
        } else {
            this.divider.setVisibility(8);
        }
        if (settingsItem.getType().equals("arrow")) {
            this.arrowLeft.setVisibility(0);
        } else {
            this.arrowLeft.setVisibility(8);
        }
        this.switchButton.setVisibility(8);
    }
}
