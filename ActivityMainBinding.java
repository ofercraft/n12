package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomBottomNavigationView;

/* loaded from: classes2.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final FrameLayout adContainer;
    public final ConstraintLayout bottomNavigationContainer;
    public final ChatPushInternalBinding chatPushInternal;
    public final CoordinatorLayout container;
    public final FrameLayout fragmentContainer;
    public final View line;
    public final CustomBottomNavigationView navigation;
    private final CoordinatorLayout rootView;
    public final SwipeRefreshLayout swipeRefreshLayout;

    private ActivityMainBinding(CoordinatorLayout coordinatorLayout, FrameLayout frameLayout, ConstraintLayout constraintLayout, ChatPushInternalBinding chatPushInternalBinding, CoordinatorLayout coordinatorLayout2, FrameLayout frameLayout2, View view, CustomBottomNavigationView customBottomNavigationView, SwipeRefreshLayout swipeRefreshLayout) {
        this.rootView = coordinatorLayout;
        this.adContainer = frameLayout;
        this.bottomNavigationContainer = constraintLayout;
        this.chatPushInternal = chatPushInternalBinding;
        this.container = coordinatorLayout2;
        this.fragmentContainer = frameLayout2;
        this.line = view;
        this.navigation = customBottomNavigationView;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_main, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMainBinding bind(View view) {
        int i = R.id.adContainer;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.adContainer);
        if (frameLayout != null) {
            i = R.id.bottomNavigationContainer;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.bottomNavigationContainer);
            if (constraintLayout != null) {
                i = R.id.chat_push_internal;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.chat_push_internal);
                if (viewFindChildViewById != null) {
                    ChatPushInternalBinding chatPushInternalBindingBind = ChatPushInternalBinding.bind(viewFindChildViewById);
                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                    i = R.id.fragment_container;
                    FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fragment_container);
                    if (frameLayout2 != null) {
                        i = R.id.line;
                        View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.line);
                        if (viewFindChildViewById2 != null) {
                            i = R.id.navigation;
                            CustomBottomNavigationView customBottomNavigationView = (CustomBottomNavigationView) ViewBindings.findChildViewById(view, R.id.navigation);
                            if (customBottomNavigationView != null) {
                                i = R.id.swipeRefreshLayout;
                                SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) ViewBindings.findChildViewById(view, R.id.swipeRefreshLayout);
                                if (swipeRefreshLayout != null) {
                                    return new ActivityMainBinding(coordinatorLayout, frameLayout, constraintLayout, chatPushInternalBindingBind, coordinatorLayout, frameLayout2, viewFindChildViewById2, customBottomNavigationView, swipeRefreshLayout);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
