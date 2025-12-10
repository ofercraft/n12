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
public final class NotificationNewSmallImageAndroid12Binding implements ViewBinding {
    public final RelativeLayout expanded;
    public final TextView notificationDescription;
    public final ImageView notificationImage;
    private final RelativeLayout rootView;

    private NotificationNewSmallImageAndroid12Binding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, ImageView imageView) {
        this.rootView = relativeLayout;
        this.expanded = relativeLayout2;
        this.notificationDescription = textView;
        this.notificationImage = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static NotificationNewSmallImageAndroid12Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NotificationNewSmallImageAndroid12Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.notification_new_small_image_android12, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NotificationNewSmallImageAndroid12Binding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i = R.id.notificationDescription;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.notificationDescription);
        if (textView != null) {
            i = R.id.notificationImage;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.notificationImage);
            if (imageView != null) {
                return new NotificationNewSmallImageAndroid12Binding(relativeLayout, relativeLayout, textView, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
