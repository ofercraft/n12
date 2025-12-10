package com.channel2.mobile.ui.lobby.views;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class PreCachingLayoutManager extends LinearLayoutManager {
    private static final int DEFAULT_EXTRA_LAYOUT_SPACE = 1000;
    private static final float MILLISECONDS_PER_INCH = 300.0f;
    private Context context;
    private int extraLayoutSpace;

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    public PreCachingLayoutManager(Context context) {
        super(context);
        this.extraLayoutSpace = -1;
        this.context = context;
    }

    public PreCachingLayoutManager(Context context, int i) {
        super(context);
        this.context = context;
        this.extraLayoutSpace = i;
    }

    public PreCachingLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
        this.extraLayoutSpace = -1;
        this.context = context;
    }

    public void setExtraLayoutSpace(int i) {
        this.extraLayoutSpace = i;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        int i = this.extraLayoutSpace;
        if (i > 0) {
            return i;
        }
        return 1000;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) { // from class: com.channel2.mobile.ui.lobby.views.PreCachingLayoutManager.1
            @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
            public PointF computeScrollVectorForPosition(int i2) {
                return PreCachingLayoutManager.this.computeScrollVectorForPosition(i2);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return PreCachingLayoutManager.MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
            }
        };
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }
}
