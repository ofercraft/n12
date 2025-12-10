package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class FragmentSettingsBinding implements ViewBinding {
    public final View alerFocus;
    public final CoordinatorLayout container;
    public final RecyclerView recyclerView;
    private final CoordinatorLayout rootView;
    public final FrameLayout settingsFragmentsContainer;

    private FragmentSettingsBinding(CoordinatorLayout coordinatorLayout, View view, CoordinatorLayout coordinatorLayout2, RecyclerView recyclerView, FrameLayout frameLayout) {
        this.rootView = coordinatorLayout;
        this.alerFocus = view;
        this.container = coordinatorLayout2;
        this.recyclerView = recyclerView;
        this.settingsFragmentsContainer = frameLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_settings, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSettingsBinding bind(View view) {
        int i = R.id.alerFocus;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.alerFocus);
        if (viewFindChildViewById != null) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            i = R.id.recyclerView;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recyclerView);
            if (recyclerView != null) {
                i = R.id.settings_fragments_container;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.settings_fragments_container);
                if (frameLayout != null) {
                    return new FragmentSettingsBinding(coordinatorLayout, viewFindChildViewById, coordinatorLayout, recyclerView, frameLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
