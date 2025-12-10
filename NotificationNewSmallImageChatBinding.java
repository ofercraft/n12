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
public final class NotificationNewSmallImageChatBinding implements ViewBinding {
    public final TextView chatTitle;
    public final RelativeLayout expanded;
    public final ImageView logo12;
    public final TextView notificationDescription;
    public final ImageView notificationImage;
    public final TextView notificationTitle;
    private final RelativeLayout rootView;
    public final TextView time;

    private NotificationNewSmallImageChatBinding(RelativeLayout relativeLayout, TextView textView, RelativeLayout relativeLayout2, ImageView imageView, TextView textView2, ImageView imageView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.chatTitle = textView;
        this.expanded = relativeLayout2;
        this.logo12 = imageView;
        this.notificationDescription = textView2;
        this.notificationImage = imageView2;
        this.notificationTitle = textView3;
        this.time = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static NotificationNewSmallImageChatBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NotificationNewSmallImageChatBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.notification_new_small_image_chat, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NotificationNewSmallImageChatBinding bind(View view) {
        int i = R.id.chatTitle;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.chatTitle);
        if (textView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
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
                            i = R.id.time;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                            if (textView4 != null) {
                                return new NotificationNewSmallImageChatBinding(relativeLayout, textView, relativeLayout, imageView, textView2, imageView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
