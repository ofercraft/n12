package com.channel2.mobile.ui.helpers;

/* loaded from: classes2.dex */
public class Orientation {
    private static OrientationState orientationState = OrientationState.ORIENTATION_PORTRAIT;

    public enum OrientationState {
        ORIENTATION_LANDSCAPE,
        ORIENTATION_PORTRAIT
    }

    public static OrientationState getOrientationState() {
        return orientationState;
    }

    public static void setOrientationState(OrientationState orientationState2) {
        orientationState = orientationState2;
    }
}
