package com.channel2.mobile.ui.programs.controllers;

import android.view.View;
import android.widget.FrameLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public class RegularPageTransformer implements ViewPager2.PageTransformer {
    private int offsetPx;
    private float fadeFactor = 0.5f;
    private float MAX_SCALE = 0.0f;

    RegularPageTransformer(int i) {
        this.offsetPx = i;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View view, float f) {
        view.setTranslationY((-(this.offsetPx * 2)) * f);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.overlay);
        if (f <= this.fadeFactor && f >= 0.0f) {
            frameLayout.setAlpha(f);
        } else {
            if (f >= 0.0f || Math.abs(f) >= this.fadeFactor) {
                return;
            }
            frameLayout.setAlpha(Math.abs(f));
        }
    }
}
