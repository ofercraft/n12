package com.channel2.mobile.ui.helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class NoPaddingTextView extends TextView {
    private final Rect mBounds;
    private final Paint mPaint;

    public NoPaddingTextView(Context context) {
        super(context);
        this.mPaint = new Paint();
        this.mBounds = new Rect();
    }

    public NoPaddingTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaint = new Paint();
        this.mBounds = new Rect();
    }

    public NoPaddingTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaint = new Paint();
        this.mBounds = new Rect();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        String strCalculateTextParams = calculateTextParams();
        int i = this.mBounds.left;
        int i2 = this.mBounds.bottom;
        Rect rect = this.mBounds;
        rect.offset(-rect.left, -this.mBounds.top);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(getCurrentTextColor());
        canvas.drawText(strCalculateTextParams, -i, this.mBounds.bottom - i2, this.mPaint);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        calculateTextParams();
        setMeasuredDimension(this.mBounds.width() + 1, (-this.mBounds.top) + 1);
    }

    private String calculateTextParams() {
        String string = getText().toString();
        int length = string.length();
        this.mPaint.setTextSize(getTextSize());
        this.mPaint.getTextBounds(string, 0, length, this.mBounds);
        if (length == 0) {
            Rect rect = this.mBounds;
            rect.right = rect.left;
        }
        return string;
    }
}
