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
public final class NotificationNewSmallImageChatAndroid12Binding implements ViewBinding {
    public final TextView chatTitle;
    public final RelativeLayout expanded;
    public final TextView notificationDescription;
    public final ImageView notificationImage;
    private final RelativeLayout rootView;

    private NotificationNewSmallImageChatAndroid12Binding(RelativeLayout relativeLayout, TextView textView, RelativeLayout relativeLayout2, TextView textView2, ImageView imageView) {
        this.rootView = relativeLayout;
        this.chatTitle = textView;
        this.expanded = relativeLayout2;
        this.notificationDescription = textView2;
        this.notificationImage = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static NotificationNewSmallImageChatAndroid12Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NotificationNewSmallImageChatAndroid12Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.notification_new_small_image_chat_android12, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NotificationNewSmallImageChatAndroid12Binding bind(View view) {
        int i = R.id.chatTitle;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.chatTitle);
        if (textView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.notificationDescription;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.notificationDescription);
            if (textView2 != null) {
                i = R.id.notificationImage;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.notificationImage);
                if (imageView != null) {
                    return new NotificationNewSmallImageChatAndroid12Binding(relativeLayout, textView, relativeLayout, textView2, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
