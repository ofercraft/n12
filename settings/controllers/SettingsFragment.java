package com.channel2.mobile.ui.settings.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.net.MailTo;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.configs.models.Tab;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.helpers.ChromeCustomTabsApi;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.reports.TransparentWebView;
import com.channel2.mobile.ui.settings.controllers.SettingsAdapter;
import com.channel2.mobile.ui.settings.models.FetchSettings;
import com.channel2.mobile.ui.settings.models.SettingsItem;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.permutive.android.EventProperties;
import com.permutive.android.PageTracker;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class SettingsFragment extends CustomFragment {
    private SettingsAdapter adapter;
    private View alerFocus;
    private int id;
    private String m_Text = "";
    private MainActivity mainActivity;
    private int numberOfTimePressed;
    private PageTracker pageTracker;
    private RecyclerView recyclerView;
    private String url;
    private View view;

    public static SettingsFragment newInstance(String str, int i) {
        SettingsFragment settingsFragment = new SettingsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        bundle.putString("url", str);
        settingsFragment.setArguments(bundle);
        return settingsFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
            this.url = getArguments().getString("url");
            setTabId(this.id);
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
            this.view = layoutInflater.inflate(R.layout.fragment_settings, viewGroup, false);
            init();
        }
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        PageTracker pageTracker;
        super.onResume();
        this.numberOfTimePressed = 0;
        SettingsAdapter settingsAdapter = this.adapter;
        if (settingsAdapter != null) {
            settingsAdapter.notifyDataSetChanged();
        }
        if (!MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) || (pageTracker = this.pageTracker) == null) {
            return;
        }
        pageTracker.resume();
        Log.i("permutive", "permutive_resume");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        PageTracker pageTracker;
        if (MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) && (pageTracker = this.pageTracker) != null) {
            try {
                pageTracker.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("permutive", "permutive_Off");
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        PageTracker pageTracker;
        super.onPause();
        if (!MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) || (pageTracker = this.pageTracker) == null) {
            return;
        }
        pageTracker.pause();
        Log.i("permutive", "permutive_pause");
    }

    private void init() {
        if (getContext() != null) {
            setHeader();
        }
        setFragmentContainerId(R.id.settings_fragments_container);
        View viewFindViewById = this.view.findViewById(R.id.alerFocus);
        this.alerFocus = viewFindViewById;
        viewFindViewById.setVisibility(8);
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fetchData();
        fragmentOnResume(this.mainActivity);
    }

    private void fetchData() {
        new FetchSettings(getContext(), this.url, new FetchSettings.ResponseHandler() { // from class: com.channel2.mobile.ui.settings.controllers.SettingsFragment.1
            @Override // com.channel2.mobile.ui.settings.models.FetchSettings.ResponseHandler
            public void onFailure() {
            }

            @Override // com.channel2.mobile.ui.settings.models.FetchSettings.ResponseHandler
            public void onSuccess(ArrayList<SettingsItem> arrayList) {
                SettingsFragment.this.adapter = new SettingsAdapter(arrayList, new SettingsAdapter.ClickHandler() { // from class: com.channel2.mobile.ui.settings.controllers.SettingsFragment.1.1
                    @Override // com.channel2.mobile.ui.settings.controllers.SettingsAdapter.ClickHandler
                    public void onClick(SettingsItem settingsItem) {
                        if (settingsItem.getLink().startsWith("http")) {
                            new ChromeCustomTabsApi().openLinkInChromeCustomTabs(SettingsFragment.this.mainActivity, settingsItem.getLink());
                            return;
                        }
                        if (settingsItem.getLink().equals("ContactUs")) {
                            ContactUsFragment contactUsFragmentNewInstance = ContactUsFragment.newInstance(SettingsFragment.this.getTabId(), SettingsFragment.this.url);
                            SettingsFragment.this.mainActivity.navigationManager.addView(SettingsFragment.this.getTabId(), contactUsFragmentNewInstance);
                            if (contactUsFragmentNewInstance != null) {
                                SettingsFragment.this.mainActivity.addFragment(R.id.settings_fragments_container, contactUsFragmentNewInstance, String.valueOf(SettingsFragment.this.getTabId()));
                                return;
                            }
                            return;
                        }
                        if (settingsItem.getLink().equals("share")) {
                            Intent intent = new Intent("android.intent.action.SEND");
                            String shareSubtitle = settingsItem.getShareSubtitle();
                            String shareTitle = settingsItem.getShareTitle();
                            intent.setType("text/plain");
                            intent.putExtra("android.intent.extra.SUBJECT", shareTitle);
                            intent.putExtra("android.intent.extra.TEXT", shareSubtitle + StringUtils.SPACE + settingsItem.getShareUrl());
                            SettingsFragment.this.startActivity(Intent.createChooser(intent, "שתף"));
                            return;
                        }
                        if (settingsItem.getLink().equals("store")) {
                            if (Utils.isHuawei()) {
                                SettingsFragment.this.openHuaweiAppGallery();
                                return;
                            }
                            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + SettingsFragment.this.mainActivity.getPackageName()));
                            intent2.addFlags(1208483840);
                            try {
                                SettingsFragment.this.startActivity(intent2);
                                return;
                            } catch (ActivityNotFoundException unused) {
                                SettingsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + SettingsFragment.this.mainActivity.getPackageName())));
                                return;
                            }
                        }
                        if (settingsItem.getLink().equals("mail")) {
                            Intent intent3 = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", settingsItem.getTo(), null));
                            intent3.setData(Uri.parse(MailTo.MAILTO_SCHEME));
                            intent3.putExtra("android.intent.extra.SUBJECT", settingsItem.getSubject());
                            intent3.putExtra("android.intent.extra.TEXT", Utils.buildMessageFromDeviceData(SettingsFragment.this.getContext()).toString());
                            intent3.putExtra("android.intent.extra.EMAIL", new String[]{settingsItem.getTo()});
                            SettingsFragment.this.startActivity(Intent.createChooser(intent3, "צור קשר באמצעות:"));
                            return;
                        }
                        if (settingsItem.getLink().equals("fontSizeView")) {
                            FontSizeFragment fontSizeFragmentNewInstance = FontSizeFragment.newInstance(SettingsFragment.this.getTabId());
                            SettingsFragment.this.mainActivity.navigationManager.addView(SettingsFragment.this.getTabId(), fontSizeFragmentNewInstance);
                            if (fontSizeFragmentNewInstance != null) {
                                SettingsFragment.this.mainActivity.addFragment(R.id.settings_fragments_container, fontSizeFragmentNewInstance, String.valueOf(SettingsFragment.this.getTabId()));
                                return;
                            }
                            return;
                        }
                        if (settingsItem.getLink().equals("notification")) {
                            NotificationFragment notificationFragmentNewInstance = NotificationFragment.newInstance(SettingsFragment.this.getTabId());
                            SettingsFragment.this.mainActivity.navigationManager.addView(SettingsFragment.this.getTabId(), notificationFragmentNewInstance);
                            if (notificationFragmentNewInstance != null) {
                                SettingsFragment.this.mainActivity.addFragment(R.id.settings_fragments_container, notificationFragmentNewInstance, String.valueOf(SettingsFragment.this.getTabId()));
                                return;
                            }
                            return;
                        }
                        if (settingsItem.getType().equals("version")) {
                            SettingsFragment.this.numberOfTimePressed++;
                            Log.i("numberOfTimePressed", "numberOfTimePressed: " + SettingsFragment.this.numberOfTimePressed);
                            if (SettingsFragment.this.numberOfTimePressed == 7) {
                                SettingsFragment.this.alerFocus.setVisibility(0);
                                SettingsFragment.this.showPasswordDialog();
                            }
                        }
                    }
                });
                SettingsFragment.this.recyclerView.setAdapter(SettingsFragment.this.adapter);
            }
        });
    }

    private void setHeader() {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.header_lobby, (ViewGroup) null, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) frameLayout.findViewById(R.id.logoContainer);
        Tab tab = MainConfig.main.getFooter().tabs.get(getTabId());
        if (tab.headerTitle.length() > 0) {
            TextView textView = (TextView) frameLayout.findViewById(R.id.channelTitle);
            ImageView imageView = (ImageView) frameLayout.findViewById(R.id.appLogo);
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.app_logo_lines);
            textView.setVisibility(0);
            textView.setText(tab.headerTitle);
            textView.setPadding(0, 0, ((int) getResources().getDisplayMetrics().density) * 2, 0);
        }
        constraintLayout.setVisibility(0);
        this.mainActivity.navigationManager.addHeader(getTabId(), frameLayout);
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
            SettingsAdapter settingsAdapter = this.adapter;
            if (settingsAdapter != null) {
                settingsAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void scrollTop() {
        super.scrollTop();
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void reportAnalyticsEvents(Activity activity) {
        super.reportAnalyticsEvents(activity);
        Log.i("reportMetrics", "Settings");
        try {
            FirebaseAnalytics.getInstance(activity).setCurrentScreen(this.mainActivity, "/settings", null);
            TransparentWebView.report(activity, MainConfig.main.getCurrentSource("reportMetrics").replace("%GUID%", "settings").replace("%VCM_ID%", "settings").replace("%CONTENT_TYPE%", "Vertical").replace("%FRIENDLY_URL%", "/settings?partner%3dAppNavBar"));
            Uri uri = Uri.parse("https://www.mako.co.il/settings?partner%3dAppNavBar");
            if (MainConfig.main.getCurrentBooleanParam(activity.getResources().getString(R.string.idx_enable))) {
                this.pageTracker = MainActivity.permutive.trackPage(new EventProperties.Builder().build(), "/settings?partner%3dAppNavBar", uri, null);
                Log.i("permutive", "permutive_On");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Enter Password:");
        final EditText editText = new EditText(getContext());
        editText.setInputType(129);
        builder.setView(editText);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.channel2.mobile.ui.settings.controllers.SettingsFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showPasswordDialog$0(editText, dialogInterface, i);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.channel2.mobile.ui.settings.controllers.SettingsFragment$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showPasswordDialog$1(dialogInterface, i);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPasswordDialog$0(EditText editText, DialogInterface dialogInterface, int i) {
        this.numberOfTimePressed = 0;
        String string = editText.getText().toString();
        this.m_Text = string;
        if (!string.equals("napp989")) {
            showPasswordDialog();
            return;
        }
        DeveloperModeFragment developerModeFragmentNewInstance = DeveloperModeFragment.newInstance(getTabId());
        this.mainActivity.navigationManager.addView(getTabId(), developerModeFragmentNewInstance);
        if (developerModeFragmentNewInstance != null) {
            this.alerFocus.setVisibility(8);
            this.mainActivity.addFragment(R.id.settings_fragments_container, developerModeFragmentNewInstance, String.valueOf(getTabId()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPasswordDialog$1(DialogInterface dialogInterface, int i) {
        this.numberOfTimePressed = 0;
        dialogInterface.cancel();
        this.alerFocus.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openHuaweiAppGallery() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("appmarket://details?id=" + getActivity().getPackageName()));
        boolean z = false;
        Iterator<ResolveInfo> it = getActivity().getPackageManager().queryIntentActivities(intent, 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ResolveInfo next = it.next();
            if (next.activityInfo.applicationInfo.packageName.equals("com.huawei.appmarket")) {
                ComponentName componentName = new ComponentName(next.activityInfo.applicationInfo.packageName, next.activityInfo.name);
                intent.addFlags(337641472);
                intent.setComponent(componentName);
                getActivity().startActivity(intent);
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://appgallery.cloud.huawei.com/marketshare/app/C101554309")));
    }
}
