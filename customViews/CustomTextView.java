package com.channel2.mobile.ui.customViews;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatTextView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.helpers.Utils;

/* loaded from: classes2.dex */
public class CustomTextView extends AppCompatTextView {
    public final String OPEN_SANS_BOLD_HEBREW;
    public final String OPEN_SANS_BOLD_ITALIC_HEBREW;
    public final String OPEN_SANS_EXTRA_BOLD_HEBREW;
    public final String OPEN_SANS_EXTRA_BOLD_ITALIC_HEBREW;
    public final String OPEN_SANS_ITALIC_HEBREW;
    public final String OPEN_SANS_LIGHT_HEBREW;
    public final String OPEN_SANS_LIGHT_ITALIC_HEBREW;
    public final String OPEN_SANS_REGULAR_HEBREW;
    public final String YONISB;
    public final String YONISBL;
    public final String YONISL;
    public final String YONISM;
    private Context context;
    private boolean enableGradient;
    private String endColor;
    private int normalTextColor;
    private String startColor;

    public CustomTextView(Context context) {
        super(context);
        this.normalTextColor = -1;
        this.YONISB = "fonts/YonitMedium_v2.ttf";
        this.YONISBL = "fonts/YonitBold_v2.ttf";
        this.YONISL = "fonts/YonitLight_v2.ttf";
        this.YONISM = "fonts/YonitRegular_v2.ttf";
        this.OPEN_SANS_BOLD_HEBREW = "fonts/OpenSansHebrew-Bold.ttf";
        this.OPEN_SANS_BOLD_ITALIC_HEBREW = "fonts/OpenSansHebrew-BoldItalic.ttf";
        this.OPEN_SANS_EXTRA_BOLD_HEBREW = "fonts/OpenSansHebrew-ExtraBold.ttf";
        this.OPEN_SANS_EXTRA_BOLD_ITALIC_HEBREW = "fonts/OpenSansHebrew-ExtraBoldItalic.ttf";
        this.OPEN_SANS_ITALIC_HEBREW = "fonts/OpenSansHebrew-Italic.ttf";
        this.OPEN_SANS_LIGHT_HEBREW = "fonts/OpenSansHebrew-Light.ttf";
        this.OPEN_SANS_LIGHT_ITALIC_HEBREW = "fonts/OpenSansHebrew-LightItalic.ttf";
        this.OPEN_SANS_REGULAR_HEBREW = "fonts/OpenSansHebrew-Regular.ttf";
        this.context = context;
        setCustomTypeface(context);
    }

    @Override // android.widget.TextView
    public void setTextColor(int i) {
        setNormalTextColor(i);
        super.setTextColor(i);
    }

    public CustomTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.normalTextColor = -1;
        this.YONISB = "fonts/YonitMedium_v2.ttf";
        this.YONISBL = "fonts/YonitBold_v2.ttf";
        this.YONISL = "fonts/YonitLight_v2.ttf";
        this.YONISM = "fonts/YonitRegular_v2.ttf";
        this.OPEN_SANS_BOLD_HEBREW = "fonts/OpenSansHebrew-Bold.ttf";
        this.OPEN_SANS_BOLD_ITALIC_HEBREW = "fonts/OpenSansHebrew-BoldItalic.ttf";
        this.OPEN_SANS_EXTRA_BOLD_HEBREW = "fonts/OpenSansHebrew-ExtraBold.ttf";
        this.OPEN_SANS_EXTRA_BOLD_ITALIC_HEBREW = "fonts/OpenSansHebrew-ExtraBoldItalic.ttf";
        this.OPEN_SANS_ITALIC_HEBREW = "fonts/OpenSansHebrew-Italic.ttf";
        this.OPEN_SANS_LIGHT_HEBREW = "fonts/OpenSansHebrew-Light.ttf";
        this.OPEN_SANS_LIGHT_ITALIC_HEBREW = "fonts/OpenSansHebrew-LightItalic.ttf";
        this.OPEN_SANS_REGULAR_HEBREW = "fonts/OpenSansHebrew-Regular.ttf";
        this.context = context;
        setCustomTypeface(context);
    }

    public CustomTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.normalTextColor = -1;
        this.YONISB = "fonts/YonitMedium_v2.ttf";
        this.YONISBL = "fonts/YonitBold_v2.ttf";
        this.YONISL = "fonts/YonitLight_v2.ttf";
        this.YONISM = "fonts/YonitRegular_v2.ttf";
        this.OPEN_SANS_BOLD_HEBREW = "fonts/OpenSansHebrew-Bold.ttf";
        this.OPEN_SANS_BOLD_ITALIC_HEBREW = "fonts/OpenSansHebrew-BoldItalic.ttf";
        this.OPEN_SANS_EXTRA_BOLD_HEBREW = "fonts/OpenSansHebrew-ExtraBold.ttf";
        this.OPEN_SANS_EXTRA_BOLD_ITALIC_HEBREW = "fonts/OpenSansHebrew-ExtraBoldItalic.ttf";
        this.OPEN_SANS_ITALIC_HEBREW = "fonts/OpenSansHebrew-Italic.ttf";
        this.OPEN_SANS_LIGHT_HEBREW = "fonts/OpenSansHebrew-Light.ttf";
        this.OPEN_SANS_LIGHT_ITALIC_HEBREW = "fonts/OpenSansHebrew-LightItalic.ttf";
        this.OPEN_SANS_REGULAR_HEBREW = "fonts/OpenSansHebrew-Regular.ttf";
        this.context = context;
    }

    private void setCustomTypeface(Context context) {
        Typeface typeface = getTypeface();
        if (typeface != null ? typeface.isBold() : false) {
            setTypeface(isInEditMode() ? null : Typeface.createFromAsset(context.getAssets(), "fonts/OpenSansHebrew-Bold.ttf"));
        } else {
            setTypeface(isInEditMode() ? null : Typeface.createFromAsset(context.getAssets(), "fonts/OpenSansHebrew-Regular.ttf"));
        }
    }

    public void setHebText(String str, String str2) {
        setHebText(str, str2, false);
    }

    public void setGradient(String str, String str2) {
        this.enableGradient = true;
        this.startColor = str;
        this.endColor = str2;
    }

    public void setHebText(String str, String str2, boolean z) {
        ViewGroup.LayoutParams layoutParams;
        AssetManager assets = this.context.getAssets();
        try {
            setTypeface(Typeface.createFromAsset(assets, str2));
        } catch (Exception unused) {
            setTypeface(Typeface.createFromAsset(assets, str2));
        }
        if (hasHebChars(str) || z) {
            setText("\u200f" + str);
        } else {
            setText(str);
        }
        if ((hasHebChars(str) || z) && (layoutParams = getLayoutParams()) != null && (layoutParams instanceof FrameLayout.LayoutParams) && getTag(R.string.key_heb_offset_done) == null) {
            setTag(R.string.key_heb_offset_done, "done");
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.topMargin -= Utils.convertDipToPixels(this.context, 5.0f);
            setLayoutParams(layoutParams2);
        }
    }

    public void setHebText(SpannableString spannableString, String str) {
        setTypeface(Typeface.createFromAsset(this.context.getAssets(), str));
        setText(spannableString);
    }

    public void setHebText(Spanned spanned, String str) {
        setTypeface(Typeface.createFromAsset(this.context.getAssets(), str));
        setText(spanned);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public int getNormalTextColor() {
        return this.normalTextColor;
    }

    public void setNormalTextColor(int i) {
        if (i == -1) {
            this.normalTextColor = i;
        }
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z && this.enableGradient) {
            getPaint().setShader(new LinearGradient(0.0f, 0.0f, getWidth(), getHeight(), Color.parseColor(this.startColor), Color.parseColor(this.endColor), Shader.TileMode.CLAMP));
        }
    }

    public boolean hasHebChars(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt >= 1488 && cCharAt <= 1514) {
                return true;
            }
        }
        return false;
    }
}
