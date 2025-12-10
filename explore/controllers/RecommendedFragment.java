package com.channel2.mobile.ui.explore.controllers;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.configs.models.Tab;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.explore.controllers.RecommendedChannelsAdapter;
import com.channel2.mobile.ui.explore.models.Channel;
import com.channel2.mobile.ui.explore.models.FetchChannels;
import com.channel2.mobile.ui.header.Header;
import com.channel2.mobile.ui.reports.TransparentWebView;
import com.channel2.mobile.ui.routing.RoutingManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.permutive.android.EventProperties;
import com.permutive.android.PageTracker;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes2.dex */
public class RecommendedFragment extends CustomFragment {
    private RecommendedChannelsAdapter adapter;
    private CustomTextView cancel;
    private int id;
    private LinearLayoutManager layoutManager;
    private MainActivity mainActivity;
    private View overlay;
    private PageTracker pageTracker;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private String url;
    private View view;

    public static RecommendedFragment newInstance(String str, int i) {
        RecommendedFragment recommendedFragment = new RecommendedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        bundle.putString("url", str);
        recommendedFragment.setArguments(bundle);
        return recommendedFragment;
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
            this.mainActivity = (MainActivity) context;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.view == null) {
            this.view = layoutInflater.inflate(R.layout.fragment_recommended, viewGroup, false);
            init();
        }
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        PageTracker pageTracker;
        super.onResume();
        RecommendedChannelsAdapter recommendedChannelsAdapter = this.adapter;
        if (recommendedChannelsAdapter != null) {
            recommendedChannelsAdapter.notifyDataSetChanged();
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
        SearchView searchView = this.searchView;
        if (searchView != null) {
            searchView.setIconified(false);
            this.searchView.clearFocus();
        }
        if (!MainConfig.main.getCurrentBooleanParam(this.mainActivity.getResources().getString(R.string.idx_enable)) || (pageTracker = this.pageTracker) == null) {
            return;
        }
        pageTracker.pause();
        Log.i("permutive", "permutive_pause");
    }

    private void init() {
        setFragmentContainerId(R.id.recommended_fragments_container);
        setHeader();
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.recyclerView);
        CustomTextView customTextView = (CustomTextView) this.view.findViewById(R.id.cancel);
        this.cancel = customTextView;
        Objects.requireNonNull(customTextView);
        customTextView.setHebText("בטל", "fonts/YonitMedium_v2.ttf");
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.explore.controllers.RecommendedFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendedFragment.this.searchView.setQuery("", false);
                RecommendedFragment.this.searchView.setIconified(false);
                RecommendedFragment.this.searchView.clearFocus();
            }
        });
        this.overlay = this.view.findViewById(R.id.overlay);
        this.searchView = (SearchView) this.view.findViewById(R.id.searchView);
        final CustomTextView customTextView2 = (CustomTextView) this.view.findViewById(R.id.searchHint);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.layoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        fetchData();
        this.searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.channel2.mobile.ui.explore.controllers.RecommendedFragment.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    RecommendedFragment.this.overlay.setVisibility(0);
                    RecommendedFragment.this.cancel.setVisibility(0);
                } else {
                    RecommendedFragment.this.overlay.setVisibility(8);
                    RecommendedFragment.this.cancel.setVisibility(8);
                }
            }
        });
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.channel2.mobile.ui.explore.controllers.RecommendedFragment.3
            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                if (str.length() > 0) {
                    RecommendedFragment.this.searchView.setQuery("", false);
                    RecommendedFragment.this.searchView.setIconified(false);
                    RecommendedFragment.this.searchView.clearFocus();
                    SearchFragment searchFragmentNewInstance = SearchFragment.newInstance(MainConfig.main.getCurrentSource("searchApi").replace("%query%", str), RecommendedFragment.this.getTabId(), str, R.id.recommended_fragments_container);
                    RecommendedFragment.this.mainActivity.navigationManager.addView(RecommendedFragment.this.getTabId(), searchFragmentNewInstance);
                    if (searchFragmentNewInstance != null) {
                        RecommendedFragment.this.mainActivity.addFragment(R.id.recommended_fragments_container, searchFragmentNewInstance, String.valueOf(RecommendedFragment.this.getTabId()));
                    }
                }
                return false;
            }

            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                if (str.length() > 0) {
                    customTextView2.setVisibility(8);
                } else {
                    customTextView2.setVisibility(0);
                }
                return false;
            }
        });
        fragmentOnResume(this.mainActivity);
    }

    private void fetchData() {
        new FetchChannels(getContext(), this.url, new FetchChannels.ResponseHandler() { // from class: com.channel2.mobile.ui.explore.controllers.RecommendedFragment.4
            @Override // com.channel2.mobile.ui.explore.models.FetchChannels.ResponseHandler
            public void onFailure() {
            }

            @Override // com.channel2.mobile.ui.explore.models.FetchChannels.ResponseHandler
            public void onSuccess(ArrayList<Channel> arrayList, String str) {
                RecommendedFragment.this.adapter = new RecommendedChannelsAdapter(arrayList, new RecommendedChannelsAdapter.ClickHandler() { // from class: com.channel2.mobile.ui.explore.controllers.RecommendedFragment.4.1
                    @Override // com.channel2.mobile.ui.explore.controllers.RecommendedChannelsAdapter.ClickHandler
                    public void onClick(Channel channel) {
                        RoutingManager.goToNextScreen(R.id.recommended_fragments_container, channel, RecommendedFragment.this.getTabId(), RecommendedFragment.this.mainActivity, null);
                    }
                });
                RecommendedFragment.this.recyclerView.setAdapter(RecommendedFragment.this.adapter);
            }
        });
    }

    private void setHeader() {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.header_lobby, (ViewGroup) null, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) frameLayout.findViewById(R.id.logoContainer);
        Tab tab = MainConfig.main.getFooter().tabs.get(getTabId());
        TextView textView = (TextView) frameLayout.findViewById(R.id.channelTitle);
        ImageView imageView = (ImageView) frameLayout.findViewById(R.id.appLogo);
        Header header = MainConfig.main.getHeader();
        if (tab.headerTitle.length() > 0) {
            imageView.setImageResource(R.drawable.app_logo_lines);
            textView.setVisibility(0);
            try {
                textView.setPadding(0, 0, ((int) getResources().getDisplayMetrics().density) * 2, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            textView.setText(tab.headerTitle);
        } else {
            imageView.setVisibility(0);
            if (header.mobileLogoImage != null) {
                imageView.setImageBitmap(header.mobileLogoImage);
            } else {
                imageView.setImageResource(R.drawable.app_logo_n12);
            }
            textView.setVisibility(8);
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
                mainActivity.toolbarParams.setScrollFlags(0);
                this.mainActivity.swipeRefreshLayout.setEnabled(false);
                this.mainActivity.exitFullScreen(this);
                if (this.mainActivity.adContainer != null) {
                    this.mainActivity.adContainer.setVisibility(8);
                }
                SearchView searchView = this.searchView;
                if (searchView != null) {
                    searchView.setIconified(false);
                    this.searchView.clearFocus();
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
    public void reportAnalyticsEvents(Activity activity) {
        super.reportAnalyticsEvents(activity);
        Log.i("reportMetrics", "recommended");
        try {
            FirebaseAnalytics.getInstance(activity).setCurrentScreen(this.mainActivity, "/channels", null);
            TransparentWebView.report(activity, MainConfig.main.getCurrentSource("reportMetrics").replace("%GUID%", "channels").replace("%VCM_ID%", "channels").replace("%CONTENT_TYPE%", "Vertical").replace("%FRIENDLY_URL%", "/channels?partner%3dAppNavBar"));
            Uri uri = Uri.parse("https://www.mako.co.il/channels?partner%3dAppNavBar");
            if (MainConfig.main.getCurrentBooleanParam(activity.getResources().getString(R.string.idx_enable))) {
                this.pageTracker = MainActivity.permutive.trackPage(new EventProperties.Builder().build(), "/channels?partner%3dAppNavBar", uri, null);
                Log.i("permutive", "permutive_On");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
