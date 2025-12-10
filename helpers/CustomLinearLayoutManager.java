package com.channel2.mobile.ui.helpers;

import android.content.Context;
import com.channel2.mobile.ui.lobby.views.PreCachingLayoutManager;

/* loaded from: classes2.dex */
public class CustomLinearLayoutManager extends PreCachingLayoutManager {
    private boolean isScrollEnabled;

    public CustomLinearLayoutManager(Context context) {
        super(context);
        this.isScrollEnabled = true;
    }

    public CustomLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
        this.isScrollEnabled = true;
    }

    public void setScrollEnabled(boolean z) {
        this.isScrollEnabled = z;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.isScrollEnabled && super.canScrollVertically();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.isScrollEnabled && super.canScrollHorizontally();
    }
}
