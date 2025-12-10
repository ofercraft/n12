package com.channel2.mobile.ui.helpers;

import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

/* loaded from: classes2.dex */
public class Transition extends TransitionSet {
    public Transition() {
        setOrdering(0);
        addTransition(new ChangeBounds()).addTransition(new ChangeTransform()).addTransition(new ChangeImageTransform());
    }
}
