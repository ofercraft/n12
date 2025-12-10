package com.channel2.mobile.ui.settings.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.settings.models.SettingsItem;
import com.channel2.mobile.ui.settings.views.SettingsDividerViewHolder;
import com.channel2.mobile.ui.settings.views.SettingsVersionHolder;
import com.channel2.mobile.ui.settings.views.SettingsViewHolder;
import com.channel2.mobile.ui.settings.views.ViewHolder;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class SettingsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ClickHandler handler;
    private ArrayList<SettingsItem> settingsItems;

    public interface ClickHandler {
        void onClick(SettingsItem settingsItem);
    }

    public SettingsAdapter(ArrayList<SettingsItem> arrayList, ClickHandler clickHandler) {
        this.settingsItems = arrayList;
        this.handler = clickHandler;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new SettingsDividerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teaser_spacer_divider_thick, viewGroup, false));
        }
        if (i == 2) {
            return new SettingsVersionHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.settings_version_item, viewGroup, false));
        }
        return new SettingsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.settings_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.initial(this.settingsItems.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.settings.controllers.SettingsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingsAdapter.this.handler.onClick((SettingsItem) SettingsAdapter.this.settingsItems.get(i));
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.settingsItems.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.settingsItems.get(i).getType().equals("version")) {
            return 2;
        }
        return this.settingsItems.get(i).getType().equals("divider") ? 0 : 1;
    }
}
