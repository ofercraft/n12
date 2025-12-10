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
public final class NotificationNewNoImageAndroid12Binding implements ViewBinding {
    public final RelativeLayout expanded;
    public final TextView notificationDescription;
    private final RelativeLayout rootView;

    private NotificationNewNoImageAndroid12Binding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.expanded = relativeLayout2;
        this.notificationDescription = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static NotificationNewNoImageAndroid12Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NotificationNewNoImageAndroid12Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.notification_new_no_image_android12, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NotificationNewNoImageAndroid12Binding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.notificationDescription);
        if (textView != null) {
            return new NotificationNewNoImageAndroid12Binding(relativeLayout, relativeLayout, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.notificationDescription)));
    }
}
