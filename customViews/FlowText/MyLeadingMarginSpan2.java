package com.channel2.mobile.ui.customViews.FlowText;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

/* loaded from: classes2.dex */
public class MyLeadingMarginSpan2 implements LeadingMarginSpan.LeadingMarginSpan2 {
    private int lines;
    private int margin;
    private boolean wasDrawCalled = false;
    private int drawLineCount = 0;

    public MyLeadingMarginSpan2(int i, int i2) {
        this.margin = i2;
        this.lines = i;
    }

    @Override // android.text.style.LeadingMarginSpan
    public int getLeadingMargin(boolean z) {
        int i = this.wasDrawCalled ? this.drawLineCount + 1 : 0;
        this.drawLineCount = i;
        this.wasDrawCalled = false;
        if (i <= this.lines) {
            return this.margin;
        }
        return 0;
    }

    @Override // android.text.style.LeadingMarginSpan
    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
        this.wasDrawCalled = true;
    }

    @Override // android.text.style.LeadingMarginSpan.LeadingMarginSpan2
    public int getLeadingMarginLineCount() {
        return this.lines;
    }
}
