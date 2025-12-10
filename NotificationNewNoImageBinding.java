package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class NotificationNewNoImageBinding implements ViewBinding {
    public final RelativeLayout expanded;
    public final ImageView logo12;
    public final TextView notificationDescription;
    public final TextView notificationTitle;
    private final RelativeLayout rootView;
    public final TextView time;

    private NotificationNewNoImageBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.expanded = relativeLayout2;
        this.logo12 = imageView;
        this.notificationDescription = textView;
        this.notificationTitle = textView2;
        this.time = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static NotificationNewNoImageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NotificationNewNoImageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.notification_new_no_image, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NotificationNewNoImageBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i = R.id.logo12;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.logo12);
        if (imageView != null) {
            i = R.id.notificationDescription;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.notificationDescription);
            if (textView != null) {
                i = R.id.notificationTitle;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.notificationTitle);
                if (textView2 != null) {
                    i = R.id.time;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                    if (textView3 != null) {
                        return new NotificationNewNoImageBinding(relativeLayout, relativeLayout, imageView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
