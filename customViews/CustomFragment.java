package com.channel2.mobile.ui.customViews;

import android.app.Activity;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.channel2.mobile.ui.helpers.Utils;

/* loaded from: classes2.dex */
public abstract class CustomFragment extends Fragment {
    private int fragmentContainerId;
    public boolean isHomePage;
    private int tabId;

    public void fragmentOnResume(Activity activity) {
    }

    public void onInterstitialAd() {
    }

    public void onInterstitialAdClosed() {
    }

    public void refresh() {
    }

    public void reportAnalyticsEvents(Activity activity) {
    }

    public void scrollTop() {
    }

    public int getTabId() {
        return this.tabId;
    }

    public void setTabId(int i) {
        this.tabId = i;
    }

    public int getFragmentContainerId() {
        return this.fragmentContainerId;
    }

    public void setFragmentContainerId(int i) {
        this.fragmentContainerId = i;
    }

    protected void setFontSize(TextView textView, float f) {
        textView.setTextSize(2, f * Utils.getFloatFromPreferences(textView.getContext(), "zoom").floatValue());
    }
}
