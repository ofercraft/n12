package com.channel2.mobile.ui.settings.controllers;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.helpers.Utils;
import com.google.android.material.switchmaterial.SwitchMaterial;

/* loaded from: classes2.dex */
public class DeveloperModeFragment extends CustomFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private Context context;
    private TextView developerModeTV;
    private SwitchMaterial developerModeToggle;
    private int id;
    private View view;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    public static DeveloperModeFragment newInstance(int i) {
        DeveloperModeFragment developerModeFragment = new DeveloperModeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        developerModeFragment.setArguments(bundle);
        return developerModeFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            int i = getArguments().getInt("id");
            this.id = i;
            setTabId(i);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.view == null) {
            View viewInflate = layoutInflater.inflate(R.layout.fragment_developer_mode, viewGroup, false);
            this.view = viewInflate;
            this.context = viewInflate.getContext();
        }
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) throws NumberFormatException {
        super.onViewCreated(view, bundle);
        int i = Integer.parseInt(Utils.getStringFromPreferences(requireContext(), requireContext().getResources().getString(R.string.userPercentNumber)));
        if (i > 0) {
            i /= 10;
        }
        ((TextView) view.findViewById(R.id.user_percent)).setText(String.valueOf(i));
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SwitchMaterial switchMaterial = (SwitchMaterial) this.view.findViewById(R.id.developerModeToggle);
        this.developerModeToggle = switchMaterial;
        switchMaterial.setChecked(!Utils.getBoolFromPreferences(this.context, "isProdEnvironment"));
        this.developerModeToggle.setOnCheckedChangeListener(this);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton == this.developerModeToggle) {
            Utils.saveBoolToPreferences(this.context, "isProdEnvironment", Boolean.valueOf(!z));
            Toast.makeText(requireContext(), z ? "Developer mode ON. Goodbye" : "Production mode ON. Goodbye", 1).show();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.settings.controllers.DeveloperModeFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCheckedChanged$0();
                }
            }, 1500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCheckedChanged$0() {
        requireActivity().finishAffinity();
        System.exit(-1);
    }
}
