package com.channel2.mobile.ui.splash;

import com.channel2.mobile.ui.R;
import com.mako.kscore.ksplayer.controller.KsApplication;
import com.permutive.android.Permutive;
import com.permutive.android.aaid.AaidAliasProvider;
import java.util.UUID;

/* loaded from: classes2.dex */
public class MyApplication extends KsApplication {
    private static MyApplication singleton;
    private Permutive mPermutive;

    public static MyApplication getInstance() {
        return singleton;
    }

    @Override // com.mako.kscore.ksplayer.controller.KsApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        singleton = this;
        getPermutive();
    }

    public Permutive getPermutive() {
        if (this.mPermutive == null) {
            synchronized (this) {
                if (this.mPermutive == null) {
                    this.mPermutive = new Permutive.Builder().context(this).workspaceId(UUID.fromString(getResources().getString(R.string.permutive_projectId))).apiKey(UUID.fromString(getResources().getString(R.string.permutive_apiKey))).aliasProvider(new AaidAliasProvider(this)).build();
                }
            }
        }
        return this.mPermutive;
    }
}
