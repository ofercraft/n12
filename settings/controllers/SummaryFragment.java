package com.channel2.mobile.ui.settings.controllers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.helpers.NavigationManager;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragment;
import com.channel2.mobile.ui.pushNotification.PushTagsManager;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;

/* loaded from: classes2.dex */
public class SummaryFragment extends CustomFragment implements NavigationManager.Handler {
    private int id;
    private MainActivity mainActivity;
    protected Button sendBtn;
    private String url;
    private View view;

    public static SummaryFragment newInstance(int i, String str) {
        SummaryFragment summaryFragment = new SummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        bundle.putString("url", str);
        summaryFragment.setArguments(bundle);
        return summaryFragment;
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
            this.view = layoutInflater.inflate(R.layout.app_setting_summary_fragment, viewGroup, false);
        }
        final Bundle bundle2 = new Bundle();
        bundle2.putString(SDKConstants.PARAM_DEEP_LINK, "");
        bundle2.putString("pushId", "");
        Button button = (Button) this.view.findViewById(R.id.send_button);
        this.sendBtn = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.settings.controllers.SummaryFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LobbyFragment lobbyFragmentNewInstance = LobbyFragment.newInstance(true, "homepage_test", 0, "", "", true, true);
                SummaryFragment.this.mainActivity.navigationManager.addView(SummaryFragment.this.getTabId(), lobbyFragmentNewInstance);
                if (lobbyFragmentNewInstance != null) {
                    SummaryFragment.this.mainActivity.displayFragment(0, bundle2);
                }
            }
        });
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        new PushTagsManager(this.view.getContext()).getTagFromServer(this.view.getContext(), false, new PushTagsManager.Handler() { // from class: com.channel2.mobile.ui.settings.controllers.SummaryFragment.2
            @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
            public void onSuccess(String str) {
                SummaryFragment.this.init(str);
            }

            @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
            public void onFailure() {
                SummaryFragment.this.init(null);
            }
        });
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void fragmentOnResume(Activity activity) {
        super.fragmentOnResume(activity);
        try {
            if (this.mainActivity == null) {
                this.mainActivity = (MainActivity) getActivity();
            }
            MainActivity mainActivity = this.mainActivity;
            if (mainActivity != null) {
                if (mainActivity.adContainer != null) {
                    this.mainActivity.adContainer.setVisibility(8);
                }
                if (this.mainActivity.bottomNavigationView != null) {
                    this.mainActivity.bottomNavigationView.selectItem(getTabId());
                }
                this.mainActivity.setRequestedOrientation(1);
            }
            new PushTagsManager(this.view.getContext()).getTagFromServer(this.view.getContext(), false, new PushTagsManager.Handler() { // from class: com.channel2.mobile.ui.settings.controllers.SummaryFragment.3
                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onSuccess(String str) {
                    SummaryFragment.this.init(str);
                }

                @Override // com.channel2.mobile.ui.pushNotification.PushTagsManager.Handler
                public void onFailure() {
                    SummaryFragment.this.init(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init(String str) {
        setHeader();
        MainConfig.main.getCurrentJsonParam("pushTags");
    }

    private void setHeader() {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.view.getContext()).inflate(R.layout.header_lobby, (ViewGroup) null, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) frameLayout.findViewById(R.id.logoContainer);
        TextView textView = (TextView) frameLayout.findViewById(R.id.channelTitle);
        ImageView imageView = (ImageView) frameLayout.findViewById(R.id.appLogo);
        imageView.setImageResource(R.drawable.app_logo_lines);
        imageView.setVisibility(0);
        textView.setVisibility(0);
        textView.setText(" צור קשר");
        constraintLayout.setVisibility(0);
        this.mainActivity.navigationManager.addHeader(getTabId(), frameLayout);
    }

    @Override // com.channel2.mobile.ui.helpers.NavigationManager.Handler
    public void onHeaderBackPressed() {
        this.mainActivity.onBackPressed();
    }
}
