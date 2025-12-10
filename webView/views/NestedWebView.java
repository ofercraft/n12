package com.channel2.mobile.ui.webView.views;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;

/* loaded from: classes2.dex */
public class NestedWebView extends WebView implements NestedScrollingChild {
    private OnScrollUpAdnDownHandler handler;
    private NestedScrollingChildHelper mChildHelper;
    private int mLastY;
    private int mNestedOffsetY;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;

    public interface OnScrollUpAdnDownHandler {
        void onScrollDown();

        void onScrollUp();
    }

    public NestedWebView(Context context) {
        this(context, null);
    }

    public NestedWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.webViewStyle);
    }

    public void setOnScrollListener(OnScrollUpAdnDownHandler onScrollUpAdnDownHandler) {
        this.handler = onScrollUpAdnDownHandler;
    }

    public NestedWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(motionEventObtain);
        if (actionMasked == 0) {
            this.mNestedOffsetY = 0;
        }
        int y = (int) motionEventObtain.getY();
        motionEventObtain.offsetLocation(0.0f, this.mNestedOffsetY);
        if (actionMasked == 0) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEventObtain);
            this.mLastY = y;
            startNestedScroll(2);
            return zOnTouchEvent;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                int i = this.mLastY - y;
                if (dispatchNestedPreScroll(0, i, this.mScrollConsumed, this.mScrollOffset)) {
                    i -= this.mScrollConsumed[1];
                    this.mLastY = y - this.mScrollOffset[1];
                    motionEventObtain.offsetLocation(0.0f, -r1);
                    this.mNestedOffsetY += this.mScrollOffset[1];
                }
                boolean zOnTouchEvent2 = super.onTouchEvent(motionEventObtain);
                int[] iArr = this.mScrollOffset;
                if (!dispatchNestedScroll(0, iArr[1], 0, i, iArr)) {
                    return zOnTouchEvent2;
                }
                motionEventObtain.offsetLocation(0.0f, this.mScrollOffset[1]);
                int i2 = this.mNestedOffsetY;
                int i3 = this.mScrollOffset[1];
                this.mNestedOffsetY = i2 + i3;
                this.mLastY -= i3;
                return zOnTouchEvent2;
            }
            if (actionMasked != 3) {
                return false;
            }
        }
        boolean zOnTouchEvent3 = super.onTouchEvent(motionEventObtain);
        stopNestedScroll();
        return zOnTouchEvent3;
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i) {
        return this.mChildHelper.startNestedScroll(i);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.mChildHelper.hasNestedScrollingParent();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.mChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        if (this.handler != null && Math.abs(i2) > 100) {
            if (i2 < 0) {
                this.handler.onScrollUp();
            } else {
                this.handler.onScrollDown();
            }
        }
        return this.mChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.mChildHelper.dispatchNestedPreFling(f, f2);
    }
}
