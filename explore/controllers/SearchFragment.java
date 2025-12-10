package com.channel2.mobile.ui.explore.controllers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.explore.controllers.SearchAdapter;
import com.channel2.mobile.ui.explore.models.FetchSearchResults;
import com.channel2.mobile.ui.explore.models.SearchResultsItem;
import com.channel2.mobile.ui.helpers.PlaceholderDemoData;
import com.channel2.mobile.ui.lobby.models.items.ItemType;
import com.channel2.mobile.ui.routing.RoutingManager;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes2.dex */
public class SearchFragment extends CustomFragment {
    private SearchAdapter adapter;
    private CustomTextView cancel;
    private int fragmentContainer;
    private int id;
    private boolean isFirst;
    private LinearLayoutManager layoutManager;
    private MainActivity mainActivity;
    private FrameLayout noResults;
    private CustomTextView noResultsText;
    private View overlay;
    private String query;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private String url;
    private View view;

    public static SearchFragment newInstance(String str, int i, String str2, int i2) {
        SearchFragment searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        bundle.putInt("fragmentContainer", i2);
        bundle.putString("url", str);
        bundle.putString(SearchIntents.EXTRA_QUERY, str2);
        searchFragment.setArguments(bundle);
        return searchFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
            this.fragmentContainer = getArguments().getInt("fragmentContainer");
            this.url = getArguments().getString("url");
            this.query = getArguments().getString(SearchIntents.EXTRA_QUERY);
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
            this.view = layoutInflater.inflate(R.layout.fragment_search, viewGroup, false);
            init();
        }
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SearchAdapter searchAdapter = this.adapter;
        if (searchAdapter != null) {
            searchAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    private void init() {
        this.isFirst = true;
        setFragmentContainerId(this.fragmentContainer);
        setHeader();
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.recyclerView);
        this.searchView = (SearchView) this.view.findViewById(R.id.searchView);
        this.cancel = (CustomTextView) this.view.findViewById(R.id.cancel);
        this.noResults = (FrameLayout) this.view.findViewById(R.id.noResults);
        this.noResultsText = (CustomTextView) this.view.findViewById(R.id.noResultsText);
        CustomTextView customTextView = this.cancel;
        Objects.requireNonNull(customTextView);
        customTextView.setHebText("בטל", "fonts/YonitMedium_v2.ttf");
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.explore.controllers.SearchFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchFragment.this.searchView.setQuery("", false);
                SearchFragment.this.searchView.setIconified(false);
                SearchFragment.this.searchView.clearFocus();
            }
        });
        this.overlay = this.view.findViewById(R.id.overlay);
        final CustomTextView customTextView2 = (CustomTextView) this.view.findViewById(R.id.searchHint);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.layoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.searchView.post(new Runnable() { // from class: com.channel2.mobile.ui.explore.controllers.SearchFragment.2
            @Override // java.lang.Runnable
            public void run() {
                SearchFragment.this.searchView.setQuery(SearchFragment.this.query, false);
                SearchFragment.this.searchView.setIconified(false);
                SearchFragment.this.searchView.clearFocus();
            }
        });
        this.searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.channel2.mobile.ui.explore.controllers.SearchFragment.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    SearchFragment.this.overlay.setVisibility(0);
                    SearchFragment.this.cancel.setVisibility(0);
                } else {
                    SearchFragment.this.overlay.setVisibility(8);
                    SearchFragment.this.cancel.setVisibility(8);
                }
            }
        });
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.channel2.mobile.ui.explore.controllers.SearchFragment.4
            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                SearchFragment.this.url = MainConfig.main.getCurrentSource("searchApi");
                SearchFragment.this.query = str;
                SearchFragment searchFragment = SearchFragment.this;
                searchFragment.url = searchFragment.url.replace("%query%", str);
                SearchFragment.this.searchView.setIconified(false);
                SearchFragment.this.searchView.clearFocus();
                SearchFragment.this.fetchData();
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
        SearchAdapter searchAdapter = new SearchAdapter(new ArrayList(), new SearchAdapter.ClickHandler() { // from class: com.channel2.mobile.ui.explore.controllers.SearchFragment.5
            @Override // com.channel2.mobile.ui.explore.controllers.SearchAdapter.ClickHandler
            public void onClick(SearchResultsItem searchResultsItem) {
                searchResultsItem.setMako_ref_comp("search_page");
                RoutingManager.goToNextScreen(SearchFragment.this.fragmentContainer, searchResultsItem, SearchFragment.this.getTabId(), SearchFragment.this.mainActivity, null);
            }
        });
        this.adapter = searchAdapter;
        this.recyclerView.setAdapter(searchAdapter);
        fetchData();
        fragmentOnResume(this.mainActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchData() {
        ArrayList<SearchResultsItem> arrayList = new ArrayList<>();
        SearchResultsItem searchResultsItem = new SearchResultsItem(PlaceholderDemoData.get(getContext(), "placeholderDemoDataSmall"));
        searchResultsItem.setLobbyItemType(ItemType.placeholderSmall);
        arrayList.add(searchResultsItem);
        if (this.isFirst) {
            this.isFirst = false;
            this.adapter.setLobbyItems(arrayList);
            this.adapter.notifyItemRangeChanged(0, arrayList.size());
        }
        new FetchSearchResults(getContext(), this.url, new FetchSearchResults.ResponseHandler() { // from class: com.channel2.mobile.ui.explore.controllers.SearchFragment.6
            @Override // com.channel2.mobile.ui.explore.models.FetchSearchResults.ResponseHandler
            public void onFailure() {
            }

            @Override // com.channel2.mobile.ui.explore.models.FetchSearchResults.ResponseHandler
            public void onSuccess(ArrayList<SearchResultsItem> arrayList2) {
                if (arrayList2 != null && arrayList2.size() > 0) {
                    SearchFragment.this.adapter.setLobbyItems(arrayList2);
                    SearchFragment.this.adapter.notifyDataSetChanged();
                    SearchFragment.this.noResults.setVisibility(8);
                } else {
                    SearchFragment.this.noResults.setVisibility(0);
                    SearchFragment.this.noResultsText.setText("לא נמצאו תוצאות חיפוש עבור \"" + SearchFragment.this.query + '\"');
                }
            }
        });
    }

    private void setHeader() {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.header_lobby, (ViewGroup) null, false);
        ((ImageView) frameLayout.findViewById(R.id.appLogo)).setVisibility(8);
        TextView textView = (TextView) frameLayout.findViewById(R.id.channelTitle);
        textView.setVisibility(0);
        textView.setText("תוצאות חיפוש");
        ((ConstraintLayout) frameLayout.findViewById(R.id.logoContainer)).setVisibility(0);
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
        this.recyclerView.smoothScrollToPosition(0);
    }
}
