package com.channel2.mobile.ui.settings.controllers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.app.NotificationManagerCompat;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.pushNotification.PushTagsManager;
import com.channel2.mobile.ui.reports.TransparentWebView;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class NotificationFragment extends CustomFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private RadioButton allRadio;
    private int id;
    private RadioButton importatndRadio;
    private ImageView inside;
    private CheckBox insideBox;
    private TextView insideTV;
    private MainActivity mainActivity;
    private Switch mainPushSwitchButton;
    private Group noficiationGroup;
    private ImageView outside;
    private CheckBox outsideBox;
    private TextView outsideTV;
    private PushTagsManager pushTagsManager;
    private Switch redDotSwitchButton;
    private ProgressBar spinner;
    private View spinnerBG;
    private Switch switchHFC;
    private Switch switchSound;
    private RadioGroup tadirutPushRadioGroup;
    private View view;
    String important = "";
    String current = "";
    String removeAll = "";

    public static NotificationFragment newInstance(int i) {
        NotificationFragment notificationFragment = new NotificationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        notificationFragment.setArguments(bundle);
        return notificationFragment;
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            this.mainActivity = mainActivity;
            if (mainActivity.adContainer != null) {
                this.mainActivity.adContainer.setVisibility(8);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.view == null) {
            this.view = layoutInflater.inflate(R.layout.fragment_notifications, viewGroup, false);
        }
        this.mainPushSwitchButton = (Switch) this.view.findViewById(R.id.mainPushSwitchButton);
        this.outside = (ImageView) this.view.findViewById(R.id.outside);
        this.outsideTV = (TextView) this.view.findViewById(R.id.outsideTV);
        this.outsideBox = (CheckBox) this.view.findViewById(R.id.outsideBox);
        this.inside = (ImageView) this.view.findViewById(R.id.inside);
        this.insideTV = (TextView) this.view.findViewById(R.id.insideTV);
        this.tadirutPushRadioGroup = (RadioGroup) this.view.findViewById(R.id.tadirutPushRadioGroup);
        this.insideBox = (CheckBox) this.view.findViewById(R.id.insideBox);
        this.allRadio = (RadioButton) this.view.findViewById(R.id.allRadio);
        this.importatndRadio = (RadioButton) this.view.findViewById(R.id.importatndRadio);
        this.redDotSwitchButton = (Switch) this.view.findViewById(R.id.redDotSwitchButton);
        this.switchSound = (Switch) this.view.findViewById(R.id.switchSound);
        this.noficiationGroup = (Group) this.view.findViewById(R.id.noficiationGroup);
        this.spinner = (ProgressBar) this.view.findViewById(R.id.spinner);
        this.spinnerBG = this.view.findViewById(R.id.spinnerBG);
        this.switchHFC = (Switch) this.view.findViewById(R.id.switchHFC);
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        new PushTagsManager(this.view.getContext()).getTagFromServer(this.view.getContext(), false, new PushTagsManager.Handler() { // from class: com.channel2.mobile.ui.settings.controllers.NotificationFragment.1
            @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
            public void onSuccess(String str) {
                NotificationFragment.this.init(str);
            }

            @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
            public void onFailure() {
                NotificationFragment.this.init(null);
            }
        });
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void fragmentOnResume(Activity activity) {
        super.fragmentOnResume(activity);
        try {
            if (this.mainActivity == null) {
                this.mainActivity = (MainActivity) activity;
            }
            if (this.mainActivity == null) {
                this.mainActivity = (MainActivity) getActivity();
            }
            MainActivity mainActivity = this.mainActivity;
            if (mainActivity != null) {
                mainActivity.swipeRefreshLayout.setEnabled(false);
                this.mainActivity.exitFullScreen(this);
                if (this.mainActivity.adContainer != null) {
                    this.mainActivity.adContainer.setVisibility(8);
                }
                if (this.mainActivity.bottomNavigationView != null) {
                    this.mainActivity.bottomNavigationView.selectItem(getTabId());
                }
                this.mainActivity.setRequestedOrientation(1);
            }
            new PushTagsManager(this.view.getContext()).getTagFromServer(this.view.getContext(), false, new PushTagsManager.Handler() { // from class: com.channel2.mobile.ui.settings.controllers.NotificationFragment.2
                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onSuccess(String str) {
                    NotificationFragment.this.init(str);
                }

                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onFailure() {
                    NotificationFragment.this.init(null);
                }
            });
            setFontsSize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setFontsSize();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init(String str) {
        setHeader();
        JSONObject currentJsonParam = MainConfig.main.getCurrentJsonParam("pushTags");
        View view = this.view;
        if (view != null) {
            this.important = currentJsonParam.optString(view.getContext().getResources().getString(R.string.important));
            this.current = currentJsonParam.optString(this.view.getContext().getResources().getString(R.string.current));
            this.removeAll = currentJsonParam.optString(this.view.getContext().getResources().getString(R.string.removeAll));
            this.pushTagsManager = new PushTagsManager(this.view.getContext());
            this.switchHFC.setOnCheckedChangeListener(null);
            this.mainPushSwitchButton.setOnCheckedChangeListener(null);
            this.outside.setOnClickListener(null);
            this.outsideTV.setOnClickListener(null);
            this.outsideBox.setOnCheckedChangeListener(null);
            this.inside.setOnClickListener(null);
            this.insideTV.setOnClickListener(null);
            this.insideBox.setOnCheckedChangeListener(null);
            this.allRadio.setOnCheckedChangeListener(null);
            this.importatndRadio.setOnCheckedChangeListener(null);
            this.redDotSwitchButton.setOnCheckedChangeListener(null);
            this.switchSound.setOnCheckedChangeListener(null);
            if (str != null && str.length() > 0 && str.equals(this.view.getContext().getResources().getString(R.string.removeAll))) {
                this.mainPushSwitchButton.setChecked(false);
            } else {
                initViews(str);
            }
            if (this.view.getContext() != null && !NotificationManagerCompat.from(this.view.getContext()).areNotificationsEnabled()) {
                this.outsideBox.setChecked(false);
                this.outside.setClickable(false);
                this.outsideTV.setClickable(false);
                this.outsideBox.setClickable(false);
            } else {
                this.outside.setOnClickListener(this);
                this.outsideTV.setOnClickListener(this);
                this.outsideBox.setOnCheckedChangeListener(this);
            }
            this.switchHFC.setOnCheckedChangeListener(this);
            this.mainPushSwitchButton.setOnCheckedChangeListener(this);
            this.inside.setOnClickListener(this);
            this.insideTV.setOnClickListener(this);
            this.insideBox.setOnCheckedChangeListener(this);
            this.allRadio.setOnCheckedChangeListener(this);
            this.importatndRadio.setOnCheckedChangeListener(this);
            this.redDotSwitchButton.setOnCheckedChangeListener(this);
            this.switchSound.setOnCheckedChangeListener(this);
            if (Utils.getBoolFromPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.redDot))) {
                this.redDotSwitchButton.setChecked(true);
            } else {
                this.redDotSwitchButton.setChecked(false);
            }
            if (Utils.getBoolFromPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.enablePushSound))) {
                this.switchSound.setChecked(true);
            } else {
                this.switchSound.setChecked(false);
            }
        }
    }

    private void setHeader() {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.view.getContext()).inflate(R.layout.header_lobby, (ViewGroup) null, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) frameLayout.findViewById(R.id.logoContainer);
        TextView textView = (TextView) frameLayout.findViewById(R.id.channelTitle);
        ((ImageView) frameLayout.findViewById(R.id.appLogo)).setVisibility(8);
        textView.setVisibility(0);
        textView.setText("התראות");
        constraintLayout.setVisibility(0);
        this.mainActivity.navigationManager.addHeader(getTabId(), frameLayout);
    }

    public void initViews(String str) {
        if (str == null) {
            this.outsideBox.setChecked(true);
            this.insideBox.setChecked(true);
            this.importatndRadio.setChecked(true);
            this.noficiationGroup.setVisibility(0);
        } else if (str.length() == 0) {
            if (Utils.getBoolFromPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.insidePush))) {
                this.outsideBox.setChecked(false);
                this.insideBox.setChecked(true);
                if (Utils.getBoolFromPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.important))) {
                    this.importatndRadio.setChecked(true);
                } else {
                    this.allRadio.setChecked(true);
                }
                this.noficiationGroup.setVisibility(0);
                this.mainPushSwitchButton.setChecked(true);
            } else {
                this.outsideBox.setChecked(false);
                this.insideBox.setChecked(false);
                this.noficiationGroup.setVisibility(8);
                this.mainPushSwitchButton.setChecked(false);
            }
        } else {
            JSONObject currentJsonParam = MainConfig.main.getCurrentJsonParam("pushTags");
            this.insideBox.setChecked(Utils.getBoolFromPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.insidePush)));
            this.outsideBox.setChecked(Utils.getBoolFromPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.outsidePush)));
            if (str.equals(currentJsonParam.optString(this.view.getContext().getResources().getString(R.string.current)))) {
                this.tadirutPushRadioGroup.check(R.id.allRadio);
            } else if (str.equals(currentJsonParam.optString(this.view.getContext().getResources().getString(R.string.important)))) {
                this.tadirutPushRadioGroup.check(R.id.importatndRadio);
            }
        }
        if (this.view.getContext() != null && !NotificationManagerCompat.from(this.view.getContext()).areNotificationsEnabled()) {
            this.outsideBox.setChecked(false);
        }
        this.switchHFC.setChecked(Utils.getBoolFromPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.hfcAlert)));
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void reportAnalyticsEvents(Activity activity) {
        super.reportAnalyticsEvents(activity);
        Log.i("reportMetrics", "FontSize");
        FirebaseAnalytics.getInstance(activity).setCurrentScreen(activity, "/settings", null);
        TransparentWebView.report(activity, MainConfig.main.getCurrentSource("reportMetrics").replace("%GUID%", "allerts").replace("%VCM_ID%", "allerts").replace("%CONTENT_TYPE%", "Vertical").replace("%FRIENDLY_URL%", "/settings/allerts"));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.inside /* 2131427934 */:
            case R.id.insideTV /* 2131427936 */:
                this.insideBox.toggle();
                break;
            case R.id.outside /* 2131428280 */:
            case R.id.outsideTV /* 2131428283 */:
                this.outsideBox.toggle();
                break;
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() == R.id.mainPushSwitchButton) {
            Bundle bundle = new Bundle();
            bundle.putString("Action", z ? "ON" : "OFF");
            logEvent("Alerts_Button", bundle);
            if (z) {
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.pushes), true);
                initViews(null);
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.important), true);
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.current), false);
                if (Utils.getBoolFromPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.outsidePush))) {
                    this.spinner.setVisibility(0);
                    this.spinnerBG.setVisibility(0);
                    this.pushTagsManager.subscribeToTopic(this.important, new PushTagsManager.Handler() { // from class: com.channel2.mobile.ui.settings.controllers.NotificationFragment.3
                        @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                        public void onFailure() {
                        }

                        @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                        public void onSuccess(String str) {
                            NotificationFragment.this.spinner.setVisibility(8);
                            NotificationFragment.this.spinnerBG.setVisibility(8);
                            Log.i("PUSH_TAG", "subscribeToTopic finished");
                        }
                    });
                    this.pushTagsManager.updateApi(this.view.getContext(), this.important);
                    return;
                }
                return;
            }
            this.spinner.setVisibility(0);
            this.spinnerBG.setVisibility(0);
            Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.pushes), false);
            Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.insidePush), false);
            this.noficiationGroup.setVisibility(8);
            this.pushTagsManager.unsubscribeFromAllTopics(new PushTagsManager.Handler() { // from class: com.channel2.mobile.ui.settings.controllers.NotificationFragment.4
                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onFailure() {
                }

                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onSuccess(String str) {
                    NotificationFragment.this.spinner.setVisibility(8);
                    NotificationFragment.this.spinnerBG.setVisibility(8);
                    Log.i("PUSH_TAG", "unsubscribeFromAllTopics finished");
                }
            });
            this.pushTagsManager.updateApi(this.view.getContext(), this.removeAll);
            return;
        }
        if (compoundButton.getId() == R.id.switchHFC) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("Action", z ? "ON" : "OFF");
            logEvent("HFC_Button", bundle2);
            Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.hfcAlert), Boolean.valueOf(z));
            try {
                if (z) {
                    ((MainActivity) requireActivity()).startHFCTimer();
                } else {
                    ((MainActivity) requireActivity()).stopHFCTimer();
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (compoundButton.getId() == R.id.insideBox) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("Action", z ? "ON" : "OFF");
            logEvent("Alerts_Button_Inapp", bundle3);
            if (z) {
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.insidePush), true);
                return;
            } else {
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.insidePush), false);
                return;
            }
        }
        if (compoundButton.getId() == R.id.outsideBox) {
            Bundle bundle4 = new Bundle();
            bundle4.putString("Action", z ? "ON" : "OFF");
            logEvent("Alerts_Button_Push_Notifications", bundle4);
            if (z) {
                this.spinner.setVisibility(0);
                this.spinnerBG.setVisibility(0);
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.outsidePush), true);
                String str = this.importatndRadio.isChecked() ? this.important : this.current;
                this.pushTagsManager.subscribeToTopic(str, new PushTagsManager.Handler() { // from class: com.channel2.mobile.ui.settings.controllers.NotificationFragment.5
                    @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                    public void onFailure() {
                    }

                    @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                    public void onSuccess(String str2) {
                        NotificationFragment.this.spinner.setVisibility(8);
                        NotificationFragment.this.spinnerBG.setVisibility(8);
                        Log.i("PUSH_TAG", "subscribeToTopic finished");
                    }
                });
                this.pushTagsManager.updateApi(this.view.getContext(), str);
                return;
            }
            this.spinner.setVisibility(0);
            this.spinnerBG.setVisibility(0);
            Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.outsidePush), false);
            this.pushTagsManager.unsubscribeFromAllTopics(new PushTagsManager.Handler() { // from class: com.channel2.mobile.ui.settings.controllers.NotificationFragment.6
                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onFailure() {
                }

                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onSuccess(String str2) {
                    NotificationFragment.this.spinner.setVisibility(8);
                    NotificationFragment.this.spinnerBG.setVisibility(8);
                    Log.i("PUSH_TAG", "unsubscribeFromAllTopics finished");
                }
            });
            this.pushTagsManager.updateApi(this.view.getContext(), this.removeAll);
            return;
        }
        if (compoundButton.getId() == R.id.allRadio) {
            if (z) {
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.current), true);
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.important), false);
                if (Utils.getBoolFromPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.outsidePush))) {
                    this.spinner.setVisibility(0);
                    this.spinnerBG.setVisibility(0);
                    this.pushTagsManager.subscribeToTopic(this.current, new PushTagsManager.Handler() { // from class: com.channel2.mobile.ui.settings.controllers.NotificationFragment.7
                        @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                        public void onFailure() {
                        }

                        @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                        public void onSuccess(String str2) {
                            NotificationFragment.this.spinner.setVisibility(8);
                            NotificationFragment.this.spinnerBG.setVisibility(8);
                            Log.i("PUSH_TAG", "subscribeToTopic finished");
                        }
                    });
                    this.pushTagsManager.updateApi(this.view.getContext(), this.current);
                    Bundle bundle5 = new Bundle();
                    bundle5.putString("Action", "All_Messages");
                    logEvent("Alerts_Frequency", bundle5);
                    return;
                }
                return;
            }
            return;
        }
        if (compoundButton.getId() == R.id.importatndRadio) {
            if (z) {
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.important), true);
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.current), false);
                if (Utils.getBoolFromPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.outsidePush))) {
                    this.spinner.setVisibility(0);
                    this.spinnerBG.setVisibility(0);
                    this.pushTagsManager.subscribeToTopic(this.important, new PushTagsManager.Handler() { // from class: com.channel2.mobile.ui.settings.controllers.NotificationFragment.8
                        @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                        public void onFailure() {
                        }

                        @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                        public void onSuccess(String str2) {
                            NotificationFragment.this.spinner.setVisibility(8);
                            NotificationFragment.this.spinnerBG.setVisibility(8);
                            Log.i("PUSH_TAG", "subscribeToTopic finished");
                        }
                    });
                    this.pushTagsManager.updateApi(this.view.getContext(), this.important);
                    Bundle bundle6 = new Bundle();
                    bundle6.putString("Action", "Important_Only");
                    logEvent("Alerts_Frequency", bundle6);
                    return;
                }
                return;
            }
            return;
        }
        if (compoundButton.getId() == R.id.redDotSwitchButton) {
            Bundle bundle7 = new Bundle();
            bundle7.putString("Action", z ? "ON" : "OFF");
            logEvent("Alerts_Red_button", bundle7);
            if (z) {
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.redDot), true);
                return;
            } else {
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.redDot), false);
                return;
            }
        }
        if (compoundButton.getId() == R.id.switchSound) {
            Bundle bundle8 = new Bundle();
            bundle8.putString("Action", z ? "ON" : "OFF");
            logEvent("Alerts_Sound", bundle8);
            if (z) {
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.enablePushSound), true);
            } else {
                Utils.saveBoolToPreferences(this.view.getContext(), this.view.getContext().getResources().getString(R.string.enablePushSound), false);
            }
        }
    }

    public void logEvent(String str, Bundle bundle) {
        try {
            FirebaseAnalytics.getInstance(getActivity()).logEvent(str, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFontsSize() {
        View view = this.view;
        if (view != null) {
            setFontSize((TextView) view.findViewById(R.id.mainPushTV), 18.0f);
            setFontSize((TextView) this.view.findViewById(R.id.txtSound), 18.0f);
            setFontSize((TextView) this.view.findViewById(R.id.mikumPushTV), 18.0f);
            setFontSize(this.outsideTV, 15.0f);
            setFontSize(this.insideTV, 15.0f);
            setFontSize((TextView) this.view.findViewById(R.id.tadirutPushTV), 18.0f);
            setFontSize(this.allRadio, 15.0f);
            setFontSize(this.importatndRadio, 15.0f);
            setFontSize((TextView) this.view.findViewById(R.id.redDotImageTV), 18.0f);
        }
    }
}
