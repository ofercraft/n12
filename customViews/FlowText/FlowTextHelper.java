package com.channel2.mobile.ui.customViews.FlowText;

import android.graphics.Point;
import android.text.SpannableString;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class FlowTextHelper {
    private static boolean mNewClassAvailable;

    static {
        try {
            Class.forName("android.text.style.LeadingMarginSpan$LeadingMarginSpan2");
            mNewClassAvailable = true;
        } catch (Exception unused) {
            mNewClassAvailable = false;
        }
    }

    public static void tryFlowText(String str, View view, TextView textView, Display display, int i) {
        if (mNewClassAvailable) {
            Point point = new Point();
            display.getSize(point);
            view.measure(point.x, point.y);
            int measuredHeight = view.getMeasuredHeight();
            int measuredWidth = view.getMeasuredWidth() + i;
            textView.measure(measuredWidth, measuredHeight);
            int iRound = Math.round((measuredHeight - textView.getTotalPaddingTop()) / textView.getPaint().getTextSize());
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new MyLeadingMarginSpan2(iRound, measuredWidth), 0, spannableString.length(), 0);
            textView.setText(spannableString);
        }
    }
}
