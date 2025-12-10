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
public final class NotificationNewBigChatTextAndroid12Binding implements ViewBinding {
    public final TextView chatTitle;
    public final TextView notificationDescription;
    private final RelativeLayout rootView;

    private NotificationNewBigChatTextAndroid12Binding(RelativeLayout relativeLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.chatTitle = textView;
        this.notificationDescription = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static NotificationNewBigChatTextAndroid12Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NotificationNewBigChatTextAndroid12Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.notification_new_big_chat_text_android12, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NotificationNewBigChatTextAndroid12Binding bind(View view) {
        int i = R.id.chatTitle;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.chatTitle);
        if (textView != null) {
            i = R.id.notificationDescription;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.notificationDescription);
            if (textView2 != null) {
                return new NotificationNewBigChatTextAndroid12Binding((RelativeLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
