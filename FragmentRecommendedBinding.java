package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;

/* loaded from: classes2.dex */
public final class FragmentRecommendedBinding implements ViewBinding {
    public final CustomTextView cancel;
    public final CoordinatorLayout container;
    public final View overlay;
    public final FrameLayout recommendedFragmentsContainer;
    public final RecyclerView recyclerView;
    private final CoordinatorLayout rootView;
    public final CustomTextView searchHint;
    public final SearchView searchView;

    private FragmentRecommendedBinding(CoordinatorLayout coordinatorLayout, CustomTextView customTextView, CoordinatorLayout coordinatorLayout2, View view, FrameLayout frameLayout, RecyclerView recyclerView, CustomTextView customTextView2, SearchView searchView) {
        this.rootView = coordinatorLayout;
        this.cancel = customTextView;
        this.container = coordinatorLayout2;
        this.overlay = view;
        this.recommendedFragmentsContainer = frameLayout;
        this.recyclerView = recyclerView;
        this.searchHint = customTextView2;
        this.searchView = searchView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentRecommendedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentRecommendedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_recommended, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentRecommendedBinding bind(View view) {
        int i = R.id.cancel;
        CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.cancel);
        if (customTextView != null) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            i = R.id.overlay;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.overlay);
            if (viewFindChildViewById != null) {
                i = R.id.recommended_fragments_container;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.recommended_fragments_container);
                if (frameLayout != null) {
                    i = R.id.recyclerView;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recyclerView);
                    if (recyclerView != null) {
                        i = R.id.searchHint;
                        CustomTextView customTextView2 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.searchHint);
                        if (customTextView2 != null) {
                            i = R.id.searchView;
                            SearchView searchView = (SearchView) ViewBindings.findChildViewById(view, R.id.searchView);
                            if (searchView != null) {
                                return new FragmentRecommendedBinding(coordinatorLayout, customTextView, coordinatorLayout, viewFindChildViewById, frameLayout, recyclerView, customTextView2, searchView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
