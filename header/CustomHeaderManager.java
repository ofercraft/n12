package com.channel2.mobile.ui.header;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.channel2.mobile.ui.helpers.Utils;
import java.text.DecimalFormat;
import java.util.Objects;

/* loaded from: classes2.dex */
public class CustomHeaderManager {
    private DecimalFormat format = new DecimalFormat("#.#");
    private FrameLayout headerView;
    private ImageView imageView;
    private ImageView imageViewLines;
    private float startAnimationPosition;

    public CustomHeaderManager(FrameLayout frameLayout) {
        this.headerView = frameLayout;
        this.startAnimationPosition = (float) (Utils.getScreenHeight((Context) Objects.requireNonNull(frameLayout.getContext())) * 0.2d);
    }

    public void setHeaderStyle(int i, int i2) {
        if (i > 100) {
            if (i < this.startAnimationPosition) {
                try {
                    this.headerView.setAlpha((float) ((1.0d - Double.valueOf(this.format.format(r4 / r5)).doubleValue()) * 1.0d));
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            this.headerView.setAlpha(0.0f);
            return;
        }
        this.headerView.setAlpha(1.0f);
    }
}
