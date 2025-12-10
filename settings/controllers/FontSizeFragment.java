package com.channel2.mobile.ui.settings.controllers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.helpers.CustomWebViewClient;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.reports.TransparentWebView;
import com.channel2.mobile.ui.webView.views.CustomWebView;
import com.cooladata.android.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

/* loaded from: classes2.dex */
public class FontSizeFragment extends CustomFragment {
    private CustomWebViewClient customWebViewClient;
    private int[] dotsArray;
    private int fontSize;
    private int id;
    private MainActivity mainActivity;
    private AppCompatSeekBar seekBar;
    private CustomTextView title;
    private View view;
    private CustomWebView webView;
    private String titleText = "חזור לגודל ברירת מחדל";
    private String defaultTitleText = "גודל ברירת מחדל";
    int zoomForReports = 1;
    int currentFontSize = 1;
    private boolean isSliderChanged = false;
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.channel2.mobile.ui.settings.controllers.FontSizeFragment.4
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            int progress = seekBar.getProgress();
            int i2 = 0;
            int iAbs = Math.abs(FontSizeFragment.this.dotsArray[0] - progress);
            int i3 = 0;
            for (int i4 = 1; i4 < FontSizeFragment.this.dotsArray.length; i4++) {
                int iAbs2 = Math.abs(FontSizeFragment.this.dotsArray[i4] - progress);
                if (iAbs2 < iAbs) {
                    i3 = i4;
                    iAbs = iAbs2;
                }
            }
            int i5 = FontSizeFragment.this.dotsArray[i3];
            seekBar.setProgress(i5);
            if (i5 == 0) {
                Utils.saveFloatToPreferences(FontSizeFragment.this.mainActivity, "zoom", Float.valueOf(1.5f));
                i2 = 150;
            } else if (i5 == 25) {
                Utils.saveFloatToPreferences(FontSizeFragment.this.mainActivity, "zoom", Float.valueOf(1.4f));
                i2 = 140;
            } else if (i5 == 50) {
                Utils.saveFloatToPreferences(FontSizeFragment.this.mainActivity, "zoom", Float.valueOf(1.3f));
                i2 = 130;
            } else if (i5 == 75) {
                Utils.saveFloatToPreferences(FontSizeFragment.this.mainActivity, "zoom", Float.valueOf(1.2f));
                i2 = 120;
            } else if (i5 == 100) {
                Utils.saveFloatToPreferences(FontSizeFragment.this.mainActivity, "zoom", Float.valueOf(1.0f));
                i2 = 100;
            }
            if (i2 == 100) {
                CustomTextView customTextView = FontSizeFragment.this.title;
                String str = FontSizeFragment.this.defaultTitleText;
                Objects.requireNonNull(FontSizeFragment.this.title);
                customTextView.setHebText(str, "fonts/YonitBold_v2.ttf");
                FontSizeFragment.this.title.setTextColor(Color.parseColor("#80333538"));
            } else {
                CustomTextView customTextView2 = FontSizeFragment.this.title;
                String str2 = FontSizeFragment.this.titleText;
                Objects.requireNonNull(FontSizeFragment.this.title);
                customTextView2.setHebText(str2, "fonts/YonitBold_v2.ttf");
                FontSizeFragment.this.title.setTextColor(Color.parseColor("#AE1600"));
            }
            if (FontSizeFragment.this.customWebViewClient != null) {
                FontSizeFragment.this.customWebViewClient.setFontSize(FontSizeFragment.this.webView, i2);
            }
            Log.i("fontZoom", "onProgressChanged: " + i2);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.i("fontZoom", "onStartTrackingTouch");
            FontSizeFragment.this.isSliderChanged = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.i("fontZoom", "onStopTrackingTouch " + seekBar.getProgress());
            FontSizeFragment.this.zoomForReports = 1;
            int progress = seekBar.getProgress();
            if (progress == 0) {
                FontSizeFragment.this.zoomForReports = 5;
            } else if (progress == 25) {
                FontSizeFragment.this.zoomForReports = 4;
            } else if (progress == 50) {
                FontSizeFragment.this.zoomForReports = 3;
            } else if (progress == 75) {
                FontSizeFragment.this.zoomForReports = 2;
            } else if (progress == 100) {
                FontSizeFragment.this.zoomForReports = 1;
            }
            Log.i("fontZoom", "onStopTrackingTouch reprot - " + FontSizeFragment.this.zoomForReports);
        }
    };

    public static FontSizeFragment newInstance(int i) {
        FontSizeFragment fontSizeFragment = new FontSizeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        fontSizeFragment.setArguments(bundle);
        return fontSizeFragment;
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
            this.view = layoutInflater.inflate(R.layout.fragment_font_size, viewGroup, false);
            init();
        }
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Log.i("FontSizeFragment", "onDestroy");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        Log.i("FontSizeFragment", "onPause");
        super.onPause();
        if (this.currentFontSize != this.zoomForReports || this.isSliderChanged) {
            Bundle bundle = new Bundle();
            bundle.putString("Size", String.valueOf(this.zoomForReports));
            FirebaseAnalytics.getInstance(this.mainActivity).logEvent("Change_Font_Size", bundle);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("FontSizeFragment", "onDestroyView");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Log.i("FontSizeFragment", "onDetach");
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Log.i("FontSizeFragment", "onStop");
    }

    private void init() {
        setHeader();
        this.webView = (CustomWebView) this.view.findViewById(R.id.webView);
        ((CoordinatorLayout) this.view.findViewById(R.id.container)).setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.settings.controllers.FontSizeFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        CustomWebViewClient customWebViewClient = new CustomWebViewClient(null, this.mainActivity, getTabId(), new CustomWebViewClient.CustomWebViewHandler() { // from class: com.channel2.mobile.ui.settings.controllers.FontSizeFragment.2
            @Override // com.channel2.mobile.ui.helpers.CustomWebViewClient.CustomWebViewHandler
            public void onLoaded() {
            }

            @Override // com.channel2.mobile.ui.helpers.CustomWebViewClient.CustomWebViewHandler
            public void onOpenUrl(String str) {
            }

            @Override // com.channel2.mobile.ui.helpers.CustomWebViewClient.CustomWebViewHandler
            public void onUserClicked() {
            }
        });
        this.customWebViewClient = customWebViewClient;
        this.webView.setWebViewClient(customWebViewClient);
        this.webView.addJavascriptInterface(new JavaScriptInterface(getContext()), Constants.TRACKER_TYPE);
        String currentParam = MainConfig.main.getCurrentParam("fontSizeWebView");
        CustomTextView customTextView = (CustomTextView) this.view.findViewById(R.id.title);
        this.title = customTextView;
        customTextView.setTextColor(Color.parseColor("#80333538"));
        CustomTextView customTextView2 = this.title;
        String str = this.defaultTitleText;
        Objects.requireNonNull(customTextView2);
        customTextView2.setHebText(str, "fonts/YonitBold_v2.ttf");
        this.title.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.settings.controllers.FontSizeFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomTextView customTextView3 = FontSizeFragment.this.title;
                String str2 = FontSizeFragment.this.defaultTitleText;
                Objects.requireNonNull(FontSizeFragment.this.title);
                customTextView3.setHebText(str2, "fonts/YonitBold_v2.ttf");
                FontSizeFragment.this.title.setTextColor(Color.parseColor("#80333538"));
                FontSizeFragment.this.seekBar.setProgress(100);
                FontSizeFragment.this.customWebViewClient.setFontSize(FontSizeFragment.this.webView, 100);
                Log.i("fontZoom", "defualt reprot - 1");
            }
        });
        CustomTextView customTextView3 = this.title;
        String str2 = this.defaultTitleText;
        Objects.requireNonNull(customTextView3);
        customTextView3.setHebText(str2, "fonts/YonitBold_v2.ttf");
        CustomTextView customTextView4 = (CustomTextView) this.view.findViewById(R.id.webViewTitle);
        Objects.requireNonNull(this.title);
        customTextView4.setHebText("גודל טקסט נוכחי", "fonts/YonitBold_v2.ttf");
        this.webView.loadUrl(currentParam);
        AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) this.view.findViewById(R.id.seekBar);
        this.seekBar = appCompatSeekBar;
        this.dotsArray = new int[]{100, 75, 50, 25, 0};
        appCompatSeekBar.setOnSeekBarChangeListener(this.seekBarChangeListener);
        this.seekBar.setProgress(100);
        fragmentOnResume(this.mainActivity);
    }

    private void setHeader() {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.header_lobby, (ViewGroup) null, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) frameLayout.findViewById(R.id.logoContainer);
        TextView textView = (TextView) frameLayout.findViewById(R.id.channelTitle);
        ((ImageView) frameLayout.findViewById(R.id.appLogo)).setVisibility(8);
        textView.setVisibility(0);
        textView.setText("גודל פונט");
        constraintLayout.setVisibility(0);
        this.mainActivity.navigationManager.addHeader(getTabId(), frameLayout);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void fragmentOnResume(Activity activity) {
        super.fragmentOnResume(activity);
        Log.i("chatLifeCycle", "fragmentOnResume");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void scrollTop() {
        super.scrollTop();
    }

    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void setFontSize(final String str) {
            FontSizeFragment.this.mainActivity.runOnUiThread(new Runnable() { // from class: com.channel2.mobile.ui.settings.controllers.FontSizeFragment.JavaScriptInterface.1
                /* JADX WARN: Removed duplicated region for block: B:31:0x007c A[Catch: Exception -> 0x00f7, TRY_ENTER, TryCatch #0 {Exception -> 0x00f7, blocks: (B:8:0x001a, B:10:0x0022, B:12:0x002c, B:31:0x007c, B:33:0x00db, B:32:0x00ac, B:23:0x0049, B:24:0x0052, B:25:0x005c, B:26:0x0066, B:27:0x0070), top: B:36:0x001a }] */
                /* JADX WARN: Removed duplicated region for block: B:32:0x00ac A[Catch: Exception -> 0x00f7, TryCatch #0 {Exception -> 0x00f7, blocks: (B:8:0x001a, B:10:0x0022, B:12:0x002c, B:31:0x007c, B:33:0x00db, B:32:0x00ac, B:23:0x0049, B:24:0x0052, B:25:0x005c, B:26:0x0066, B:27:0x0070), top: B:36:0x001a }] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() throws java.lang.NumberFormatException {
                    /*
                        Method dump skipped, instructions count: 248
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.channel2.mobile.ui.settings.controllers.FontSizeFragment.JavaScriptInterface.AnonymousClass1.run():void");
                }
            });
        }
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void reportAnalyticsEvents(Activity activity) {
        super.reportAnalyticsEvents(activity);
        Log.i("reportMetrics", "FontSize");
        FirebaseAnalytics.getInstance(activity).setCurrentScreen(activity, "/settings", null);
        TransparentWebView.report(activity, MainConfig.main.getCurrentSource("reportMetrics").replace("%GUID%", TtmlNode.ATTR_TTS_FONT_SIZE).replace("%VCM_ID%", TtmlNode.ATTR_TTS_FONT_SIZE).replace("%CONTENT_TYPE%", "Vertical").replace("%FRIENDLY_URL%", "/settings/fontSize"));
    }
}
