package com.channel2.mobile.ui.alerts.HFC;

/* compiled from: HfcAlertHelper.java */
/* loaded from: classes2.dex */
class HFCAlert {
    private long id;
    private boolean isFinished;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void setFinished(boolean z) {
        this.isFinished = z;
    }

    public HFCAlert(long j, boolean z) {
        this.id = j;
        this.isFinished = z;
    }
}
