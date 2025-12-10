package com.channel2.mobile.ui.lobby.models.sections;

import android.view.View;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class CustomSnapHelper extends PagerSnapHelper {
    @Override // androidx.recyclerview.widget.PagerSnapHelper, androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        return new int[]{layoutManager.getDecoratedLeft(view) - (layoutManager.getWidth() - view.getWidth()), 0};
    }

    private int getDistance(RecyclerView.LayoutManager layoutManager, View view, OrientationHelper orientationHelper) {
        int decoratedStart = orientationHelper.getDecoratedStart(view);
        orientationHelper.getStartAfterPadding();
        return decoratedStart - (orientationHelper.getEnd() - view.getWidth());
    }
}
