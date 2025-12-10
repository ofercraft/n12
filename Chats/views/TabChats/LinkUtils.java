package com.channel2.mobile.ui.Chats.views.TabChats;

import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class LinkUtils {
    public static final Pattern URL_PATTERN = Pattern.compile("((https?|ftp)(:\\/\\/[-_.!~*\\'()a-zA-Z0-9;\\/?:\\@&=+\\$,%#]+))");

    public interface OnClickListener {
        void onClicked();

        void onLinkClicked(String str);
    }

    static class SensibleUrlSpan extends URLSpan {
        private Pattern mPattern;

        public SensibleUrlSpan(String str, Pattern pattern) {
            super(str);
            this.mPattern = pattern;
        }

        public boolean onClickSpan(View view) {
            boolean zMatches = this.mPattern.matcher(getURL()).matches();
            if (zMatches) {
                super.onClick(view);
            }
            return zMatches;
        }
    }

    static class SensibleLinkMovementMethod extends LinkMovementMethod {
        private String mClickedLink;
        private boolean mLinkClicked;

        SensibleLinkMovementMethod() {
        }

        @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
        public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                this.mLinkClicked = false;
                this.mClickedLink = null;
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                int totalPaddingLeft = x - textView.getTotalPaddingLeft();
                int totalPaddingTop = y - textView.getTotalPaddingTop();
                int scrollX = totalPaddingLeft + textView.getScrollX();
                int scrollY = totalPaddingTop + textView.getScrollY();
                Layout layout = textView.getLayout();
                int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), scrollX);
                ClickableSpan[] clickableSpanArr = (ClickableSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
                if (clickableSpanArr.length != 0) {
                    SensibleUrlSpan sensibleUrlSpan = (SensibleUrlSpan) clickableSpanArr[0];
                    this.mLinkClicked = sensibleUrlSpan.onClickSpan(textView);
                    this.mClickedLink = sensibleUrlSpan.getURL();
                    return this.mLinkClicked;
                }
            }
            super.onTouchEvent(textView, spannable, motionEvent);
            return false;
        }

        public boolean isLinkClicked() {
            return this.mLinkClicked;
        }

        public String getClickedLink() {
            return this.mClickedLink;
        }
    }

    public static void autoLink(TextView textView, OnClickListener onClickListener) {
        autoLink(textView, onClickListener, null);
    }

    public static void autoLink(TextView textView, final OnClickListener onClickListener, String str) {
        Pattern patternCompile;
        String string = textView.getText().toString();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        SpannableString spannableString = new SpannableString(string);
        if (TextUtils.isEmpty(str)) {
            patternCompile = URL_PATTERN;
        } else {
            patternCompile = Pattern.compile(str);
        }
        Matcher matcher = patternCompile.matcher(string);
        while (matcher.find()) {
            spannableString.setSpan(new SensibleUrlSpan(matcher.group(1), patternCompile), matcher.start(1), matcher.end(1), 33);
        }
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
        final SensibleLinkMovementMethod sensibleLinkMovementMethod = new SensibleLinkMovementMethod();
        textView.setMovementMethod(sensibleLinkMovementMethod);
        if (onClickListener != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.Chats.views.TabChats.LinkUtils.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (sensibleLinkMovementMethod.isLinkClicked()) {
                        onClickListener.onLinkClicked(sensibleLinkMovementMethod.getClickedLink());
                    } else {
                        onClickListener.onClicked();
                    }
                }
            });
        }
    }
}
