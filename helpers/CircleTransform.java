package com.channel2.mobile.ui.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.squareup.picasso.Transformation;

/* loaded from: classes2.dex */
public class CircleTransform implements Transformation {
    @Override // com.squareup.picasso.Transformation
    public String key() {
        return TtmlNode.TEXT_EMPHASIS_MARK_CIRCLE;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        int iMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, (bitmap.getWidth() - iMin) / 2, (bitmap.getHeight() - iMin) / 2, iMin, iMin);
        if (bitmapCreateBitmap != bitmap) {
            bitmap.recycle();
        }
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(iMin, iMin, bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmapCreateBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float f = iMin / 2.0f;
        canvas.drawCircle(f, f, f, paint);
        bitmapCreateBitmap.recycle();
        return bitmapCreateBitmap2;
    }
}
