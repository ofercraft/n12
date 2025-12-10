package com.channel2.mobile.ui.video;

import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.splash.MyApplication;
import com.channel2.mobile.ui.video.PlayerFragmentCallbacks;
import com.channel2.mobile.ui.video.fragments.ChatGalleryFragment;
import com.channel2.mobile.ui.video.fragments.PlayerFragment;
import com.mako.kscore.ksmeasurements.helpers.PlayerState;
import com.mako.kscore.ksplayer.controller.PiPSupportActivity;
import com.mako.kscore.ksplayer.helpers.KillPlayerListener;
import com.mako.kscore.ksplayer.helpers.ReasonReport;
import com.mako.kscore.ksplayer.helpers.managers.KsPlayerManager;
import com.mako.kscore.ksplayer.model.KsPlayItem;
import com.mako.kscore.ksplayer.view.RootControl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\u0007H\u0016J\u0012\u0010\t\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0007H\u0016J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\b\u0010\u0012\u001a\u00020\u0007H\u0014¨\u0006\u0013"}, d2 = {"Lcom/channel2/mobile/ui/video/PlayerActivity;", "Lcom/mako/kscore/ksplayer/controller/PiPSupportActivity;", "Lcom/channel2/mobile/ui/video/PlayerFragmentCallbacks;", "()V", "canEnterPiP", "", "exit", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onExit", "reason", "Lcom/mako/kscore/ksplayer/helpers/ReasonReport;", "onPiPExited", "onPlayerClick", "data", "onStart", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PlayerActivity extends PiPSupportActivity implements PlayerFragmentCallbacks {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void onExit$lambda$6$lambda$5() {
    }

    @Override // com.mako.kscore.ksplayer.controller.PiPSupportActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("checkPiP", "onCreate: ");
        getSupportFragmentManager().addFragmentOnAttachListener(new FragmentOnAttachListener() { // from class: com.channel2.mobile.ui.video.PlayerActivity$$ExternalSyntheticLambda2
            @Override // androidx.fragment.app.FragmentOnAttachListener
            public final void onAttachFragment(FragmentManager fragmentManager, Fragment fragment) {
                PlayerActivity.onCreate$lambda$1(this.f$0, fragmentManager, fragment);
            }
        });
        setContentView(R.layout.activity_player);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("chatReportItem")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.playerFragment, new ChatGalleryFragment()).disallowAddToBackStack().commit();
        }
        getWindow().setStatusBarColor(getResources().getColor(R.color.black0C0C0C, null));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.black0C0C0C, null));
        getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black0C0C0C, null)));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        Intrinsics.checkNotNullExpressionValue(insetsController, "getInsetsController(window, window.decorView)");
        insetsController.setSystemBarsBehavior(2);
        insetsController.hide(WindowInsetsCompat.Type.systemBars());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(PlayerActivity this$0, FragmentManager fragmentManager, Fragment fragment) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fragmentManager, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        PlayerFragment playerFragment = fragment instanceof PlayerFragment ? (PlayerFragment) fragment : null;
        if (playerFragment != null) {
            playerFragment.setCallbacks(this$0);
            playerFragment.setPipCallbacks(this$0);
        }
        ChatGalleryFragment chatGalleryFragment = fragment instanceof ChatGalleryFragment ? (ChatGalleryFragment) fragment : null;
        if (chatGalleryFragment == null) {
            return;
        }
        chatGalleryFragment.setCallbacks(this$0);
    }

    @Override // com.mako.kscore.ksplayer.controller.PiPSupportActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String appLifeCycleInMin = MainConfig.main.getCurrentParam("appLifeCycleInMin");
            Intrinsics.checkNotNullExpressionValue(appLifeCycleInMin, "appLifeCycleInMin");
            long j = Long.parseLong(appLifeCycleInMin) * 1000 * 60;
            Application application = getApplication();
            Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.channel2.mobile.ui.splash.MyApplication");
            if (!((MyApplication) application).isForeground() && MainConfig.appData.getAppStartTime() > 0 && MainConfig.appData.getAppStartTime() + j < jCurrentTimeMillis) {
                setResult(100);
                PlayerFragmentCallbacks.DefaultImpls.onExit$default(this, null, 1, null);
            }
        } catch (Exception unused) {
        }
        super.onStart();
    }

    @Override // com.channel2.mobile.ui.video.PlayerFragmentCallbacks
    public void onPlayerClick(Bundle data) {
        Intrinsics.checkNotNullParameter(data, "data");
        PlayerFragmentCallbacks.DefaultImpls.onPlayerClick(this, data);
        if (data.containsKey("closeBtn")) {
            onExit(ReasonReport.back);
            return;
        }
        if (data.containsKey("shareBtn")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            String string = data.getString("shareBtn");
            if (string == null) {
                string = "";
            }
            intent.putExtra("android.intent.extra.TEXT", string);
            intent.setFlags(1);
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, null));
            return;
        }
        if (data.containsKey("enterPip")) {
            checkPermissionAndEnterPiP();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        onExit(ReasonReport.back);
    }

    @Override // com.channel2.mobile.ui.video.PlayerFragmentCallbacks
    public void onExit(ReasonReport reason) {
        Unit unit;
        Intrinsics.checkNotNullParameter(reason, "reason");
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.playerContainer);
        if (viewGroup != null) {
            RootControl rootControl = (RootControl) viewGroup.findViewById(R.id.root_control);
            if (rootControl != null ? rootControl.getPlayerExists() : false) {
                KsPlayerManager.killPlayer$default(KsPlayerManager.INSTANCE, viewGroup, null, reason, new KillPlayerListener() { // from class: com.channel2.mobile.ui.video.PlayerActivity$$ExternalSyntheticLambda0
                    @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                    public final void onKilled() {
                        PlayerActivity.onExit$lambda$6$lambda$4(this.f$0);
                    }
                }, 2, null);
            } else {
                KsPlayerManager.killPlayer$default(KsPlayerManager.INSTANCE, viewGroup, null, reason, new KillPlayerListener() { // from class: com.channel2.mobile.ui.video.PlayerActivity$$ExternalSyntheticLambda1
                    @Override // com.mako.kscore.ksplayer.helpers.KillPlayerListener
                    public final void onKilled() {
                        PlayerActivity.onExit$lambda$6$lambda$5();
                    }
                }, 2, null);
                Log.d("checkKillPlayer", "PlayerActivity onExit - finish after onKilled WITHOUT player");
                exit();
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            Log.d("checkKillPlayer", "PlayerActivity onExit - finish after no playerContainer");
            exit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onExit$lambda$6$lambda$4(PlayerActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.d("checkKillPlayer", "PlayerActivity onExit - finish after onKilled WITH player");
        this$0.exit();
    }

    private final void exit() {
        Log.d("checkPiP", "exit: ");
        FragmentContainerView fragmentContainerView = (FragmentContainerView) findViewById(R.id.playerFragment);
        setResult((fragmentContainerView != null ? fragmentContainerView.getFragment() : null) instanceof ChatGalleryFragment ? 2 : 3);
        finish();
    }

    @Override // com.mako.kscore.ksplayer.controller.PiPSupportActivity
    public void onPiPExited() {
        PlayerState playerState;
        RootControl rootControl = (RootControl) findViewById(R.id.root_control);
        if (rootControl != null) {
            KsPlayItem ksPlayItem = rootControl.getKsPlayItem();
            FragmentContainerView fragmentContainerView = (FragmentContainerView) findViewById(R.id.playerFragment);
            if ((fragmentContainerView != null ? fragmentContainerView.getFragment() : null) instanceof ChatGalleryFragment) {
                playerState = PlayerState.undefined;
            } else {
                playerState = PlayerState.full_screen;
            }
            ksPlayItem.setPlayerState(playerState);
        }
        super.onPiPExited();
    }

    @Override // com.mako.kscore.ksplayer.controller.PiPSupportActivity
    public boolean canEnterPiP() {
        RootControl rootControl = (RootControl) findViewById(R.id.root_control);
        return rootControl != null && Build.VERSION.SDK_INT >= 24 && MainConfig.main.getCurrentBooleanParam("pipEnable") && rootControl.isLive() && rootControl.isPlaying() && !rootControl.isAd();
    }
}
