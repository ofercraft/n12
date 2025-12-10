package com.channel2.mobile.ui.liveStreaming;

import com.channel2.mobile.ui.programs.models.ProgramsItem;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class Live {
    public static final String ch2Vcmid = "1e2258089b67f510VgnVCM2000002a0c10acRCRD";
    private static String currentProgram = null;
    private static boolean live = false;
    public static final String liveChannelId = "56dda65207e41210VgnVCM100000290c10acRCRD";
    private static HashMap<String, ProgramsItem> livePrograms;

    public static boolean isLive() {
        return live;
    }

    public static void setIsLive(boolean z) {
        live = z;
    }

    public static HashMap<String, ProgramsItem> getLivePrograms() {
        return livePrograms;
    }

    public static void setLivePrograms(HashMap<String, ProgramsItem> map) {
        livePrograms = map;
    }

    public static String getCurrentProgram() {
        return currentProgram;
    }

    public static void setCurrentProgram(String str) {
        currentProgram = str;
    }
}
