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
public final class NotificationNewBigBinding implements ViewBinding {
    public final LinearLayout buttonsLayout;
    public final TextView chatTitle;
    public final ImageView logo12;
    public final TextView notificationDescription;
    public final ImageView notificationImage;
    public final TextView notificationTitle;
    private final RelativeLayout rootView;
    public final ImageView shareBtn;
    public final TextView shareText;
    public final TextView time;

    private NotificationNewBigBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, TextView textView, ImageView imageView, TextView textView2, ImageView imageView2, TextView textView3, ImageView imageView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.buttonsLayout = linearLayout;
        this.chatTitle = textView;
        this.logo12 = imageView;
        this.notificationDescription = textView2;
        this.notificationImage = imageView2;
        this.notificationTitle = textView3;
        this.shareBtn = imageView3;
        this.shareText = textView4;
        this.time = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static NotificationNewBigBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NotificationNewBigBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.notification_new_big, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NotificationNewBigBinding bind(View view) {
        int i = R.id.buttonsLayout;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.buttonsLayout);
        if (linearLayout != null) {
            i = R.id.chatTitle;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.chatTitle);
            if (textView != null) {
                i = R.id.logo12;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.logo12);
                if (imageView != null) {
                    i = R.id.notificationDescription;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.notificationDescription);
                    if (textView2 != null) {
                        i = R.id.notificationImage;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.notificationImage);
                        if (imageView2 != null) {
                            i = R.id.notificationTitle;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.notificationTitle);
                            if (textView3 != null) {
                                i = R.id.shareBtn;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.shareBtn);
                                if (imageView3 != null) {
                                    i = R.id.shareText;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.shareText);
                                    if (textView4 != null) {
                                        i = R.id.time;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                                        if (textView5 != null) {
                                            return new NotificationNewBigBinding((RelativeLayout) view, linearLayout, textView, imageView, textView2, imageView2, textView3, imageView3, textView4, textView5);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
