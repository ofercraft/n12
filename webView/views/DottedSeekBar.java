package com.channel2.mobile.ui.webView.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public class DottedSeekBar extends AppCompatSeekBar {
    private Bitmap mDotBitmap;
    private int[] mDotsPositions;
    private Paint paint;
    private int radius;
    private int viewWidth;

    public DottedSeekBar(Context context) {
        super(context);
        this.mDotsPositions = null;
        this.mDotBitmap = null;
        this.paint = null;
        init(null);
    }

    public DottedSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDotsPositions = null;
        this.mDotBitmap = null;
        this.paint = null;
        init(attributeSet);
    }

    public DottedSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDotsPositions = null;
        this.mDotBitmap = null;
        this.paint = null;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.DottedSeekBar, 0, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        if (resourceId != 0) {
            this.mDotsPositions = getResources().getIntArray(resourceId);
        }
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId2 != 0) {
            this.mDotBitmap = BitmapFactory.decodeResource(getResources(), resourceId2);
        }
    }

    public void setDots(int[] iArr, String str, int i, int i2) {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(Color.parseColor(str));
        this.paint.setStyle(Paint.Style.FILL);
        this.mDotsPositions = iArr;
        this.radius = i;
        this.viewWidth = i2;
        invalidate();
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getMeasuredWidth();
        float measuredHeight = getMeasuredHeight();
        int i = this.viewWidth / 5;
        int[] iArr = this.mDotsPositions;
        if (iArr != null && iArr.length != 0 && this.paint != null) {
            for (int i2 : iArr) {
                int i3 = i2 * i;
                canvas.drawCircle(i3 + (r6 * 2), measuredHeight / 2.0f, this.radius, this.paint);
            }
        }
    }
}
