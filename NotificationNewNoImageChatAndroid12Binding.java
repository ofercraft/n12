package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class NotificationNewNoImageChatAndroid12Binding implements ViewBinding {
    public final TextView chatTitle;
    public final RelativeLayout expanded;
    public final TextView notificationDescription;
    private final RelativeLayout rootView;

    private NotificationNewNoImageChatAndroid12Binding(RelativeLayout relativeLayout, TextView textView, RelativeLayout relativeLayout2, TextView textView2) {
        this.rootView = relativeLayout;
        this.chatTitle = textView;
        this.expanded = relativeLayout2;
        this.notificationDescription = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static NotificationNewNoImageChatAndroid12Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NotificationNewNoImageChatAndroid12Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.notification_new_no_image_chat_android12, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NotificationNewNoImageChatAndroid12Binding bind(View view) {
        int i = R.id.chatTitle;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.chatTitle);
        if (textView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.notificationDescription);
            if (textView2 != null) {
                return new NotificationNewNoImageChatAndroid12Binding(relativeLayout, textView, relativeLayout, textView2);
            }
            i = R.id.notificationDescription;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
