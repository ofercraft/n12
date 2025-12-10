package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class NotificationNewBigAndroid12Binding implements ViewBinding {
    public final LinearLayout buttonsLayout;
    public final TextView chatTitle;
    public final TextView notificationDescription;
    public final ImageView notificationImage;
    private final RelativeLayout rootView;
    public final ImageView shareBtn;
    public final TextView shareText;

    private NotificationNewBigAndroid12Binding(RelativeLayout relativeLayout, LinearLayout linearLayout, TextView textView, TextView textView2, ImageView imageView, ImageView imageView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.buttonsLayout = linearLayout;
        this.chatTitle = textView;
        this.notificationDescription = textView2;
        this.notificationImage = imageView;
        this.shareBtn = imageView2;
        this.shareText = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static NotificationNewBigAndroid12Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NotificationNewBigAndroid12Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.notification_new_big_android12, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NotificationNewBigAndroid12Binding bind(View view) {
        int i = R.id.buttonsLayout;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.buttonsLayout);
        if (linearLayout != null) {
            i = R.id.chatTitle;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.chatTitle);
            if (textView != null) {
                i = R.id.notificationDescription;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.notificationDescription);
                if (textView2 != null) {
                    i = R.id.notificationImage;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.notificationImage);
                    if (imageView != null) {
                        i = R.id.shareBtn;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.shareBtn);
                        if (imageView2 != null) {
                            i = R.id.shareText;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.shareText);
                            if (textView3 != null) {
                                return new NotificationNewBigAndroid12Binding((RelativeLayout) view, linearLayout, textView, textView2, imageView, imageView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
