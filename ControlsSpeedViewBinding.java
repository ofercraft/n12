package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class ControlsSpeedViewBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView speed05x;
    public final TextView speed075x;
    public final TextView speed125x;
    public final TextView speed15x;
    public final TextView speed1x;
    public final TextView speed2x;
    public final Group speedRates;

    private ControlsSpeedViewBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, Group group) {
        this.rootView = constraintLayout;
        this.speed05x = textView;
        this.speed075x = textView2;
        this.speed125x = textView3;
        this.speed15x = textView4;
        this.speed1x = textView5;
        this.speed2x = textView6;
        this.speedRates = group;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ControlsSpeedViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ControlsSpeedViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_speed_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ControlsSpeedViewBinding bind(View view) {
        int i = R.id.speed_0_5x;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.speed_0_5x);
        if (textView != null) {
            i = R.id.speed_0_75x;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.speed_0_75x);
            if (textView2 != null) {
                i = R.id.speed_1_25x;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.speed_1_25x);
                if (textView3 != null) {
                    i = R.id.speed_1_5x;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.speed_1_5x);
                    if (textView4 != null) {
                        i = R.id.speed_1x;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.speed_1x);
                        if (textView5 != null) {
                            i = R.id.speed_2x;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.speed_2x);
                            if (textView6 != null) {
                                i = R.id.speed_rates;
                                Group group = (Group) ViewBindings.findChildViewById(view, R.id.speed_rates);
                                if (group != null) {
                                    return new ControlsSpeedViewBinding((ConstraintLayout) view, textView, textView2, textView3, textView4, textView5, textView6, group);
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
