package com.channel2.mobile.ui.programs.controllers;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes2.dex */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private float MAX_SCALE = 0.0f;
    private float fadeFactor = 0.5f;

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        if (this.MAX_SCALE == 0.0f && f > 0.0f && f < 1.0f) {
            this.MAX_SCALE = f;
        }
        float f2 = f - this.MAX_SCALE;
        float fAbs = Math.abs(f2);
        if (f2 <= -1.0f || f2 >= 1.0f) {
            view.setAlpha(this.fadeFactor);
            return;
        }
        if (f2 == 0.0f) {
            view.setScaleX(this.MAX_SCALE + 1.0f);
            view.setScaleY(this.MAX_SCALE + 1.0f);
            view.setAlpha(1.0f);
        } else {
            float f3 = 1.0f - fAbs;
            view.setScaleX((this.MAX_SCALE * f3) + 1.0f);
            view.setScaleY((this.MAX_SCALE * f3) + 1.0f);
            view.setAlpha(Math.max(this.fadeFactor, f3));
        }
    }
}
