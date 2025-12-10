package com.channel2.mobile.ui.routing;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.helpers.ChromeCustomTabsApi;
import com.channel2.mobile.ui.helpers.RegexpKeyedMap;
import com.channel2.mobile.ui.helpers.Transition;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.LobbyItem;
import com.channel2.mobile.ui.network.ApiService;
import com.channel2.mobile.ui.video.PlayerActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mako.kscore.ksplayer.controller.PiPSupportActivityKt;
import com.mako.kscore.ksplayer.helpers.managers.CastManager;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class RoutingManager {
    private static boolean isRoutingEnable = true;
    private static RegexpKeyedMap routingMap;
    private static Handler timerHandler;
    private static Runnable timerRunnable;

    enum ClassType {
        HOMEPAGE,
        LOBBY,
        ARTICLE,
        WEBVIEW,
        ARTICLE_NO_HEADER,
        VIDEO,
        CHROMETABS,
        OUTSIDE,
        PROGRAMS,
        GALLERY,
        CHAT
    }

    public interface ResponseHandler {
        void onFailure();

        void onSuccess();
    }

    public RoutingManager() {
        routingMap = new RegexpKeyedMap();
    }

    public RoutingManager(JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("routing");
        routingMap = new RegexpKeyedMap();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            String strOptString = jSONObjectOptJSONObject.optString("screenName");
            JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("patterns");
            if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0) {
                for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                    Pattern pattern = new Pattern(jSONArrayOptJSONArray2.optJSONObject(i2), strOptString);
                    routingMap.put(pattern.getPattern(), pattern);
                }
            }
        }
    }

    public static Pattern getPattern(String str) {
        return (Pattern) routingMap.get(str);
    }

    public static void goToNextScreen(int i, Item item, int i2, MainActivity mainActivity, View view) {
        goToNextScreen(i, item, i2, null, mainActivity, view);
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x0332  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void goToNextScreen(int r15, com.channel2.mobile.ui.lobby.models.items.Item r16, int r17, android.os.Bundle r18, com.channel2.mobile.ui.MainActivity r19, android.view.View r20) {
        /*
            Method dump skipped, instructions count: 931
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.channel2.mobile.ui.routing.RoutingManager.goToNextScreen(int, com.channel2.mobile.ui.lobby.models.items.Item, int, android.os.Bundle, com.channel2.mobile.ui.MainActivity, android.view.View):void");
    }

    private static void beginTransaction(CustomFragment customFragment, int i, View view, MainActivity mainActivity, int i2) {
        mainActivity.navigationManager.addView(i, customFragment);
        if (view != null) {
            customFragment.setSharedElementEnterTransition(new Transition());
            customFragment.setSharedElementReturnTransition(new Transition());
            if (customFragment.isAdded()) {
                return;
            }
            mainActivity.getSupportFragmentManager().beginTransaction().addSharedElement(view, mainActivity.getResources().getString(R.string.transition_name)).add(i2, customFragment, String.valueOf(i)).commitAllowingStateLoss();
            return;
        }
        if (customFragment.isAdded()) {
            return;
        }
        try {
            mainActivity.getSupportFragmentManager().beginTransaction().add(i2, customFragment, String.valueOf(i)).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startTimer() {
        isRoutingEnable = false;
        timerHandler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() { // from class: com.channel2.mobile.ui.routing.RoutingManager.1
            @Override // java.lang.Runnable
            public void run() {
                RoutingManager.timerHandler.removeCallbacks(RoutingManager.timerRunnable);
            }
        };
        timerRunnable = runnable;
        timerHandler.postDelayed(runnable, 1000L);
    }

    private static void stopTimer() {
        isRoutingEnable = true;
        timerHandler.removeCallbacks(timerRunnable);
    }

    private static void reportScreenName(MainActivity mainActivity, String str, Pattern pattern) {
        if (pattern == null || pattern.getScreenName().equals(ClassType.CHROMETABS.name()) || pattern.getScreenName().equals(ClassType.ARTICLE.name()) || pattern.getScreenName().equals(ClassType.ARTICLE_NO_HEADER.name())) {
            FirebaseAnalytics.getInstance(mainActivity).setCurrentScreen(mainActivity, str, null);
        }
    }

    public void load(Context context) {
        ApiService.getJSONObjectAsync(MainConfig.main.getCurrentSource("routingApi"), context, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.routing.RoutingManager.2
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onFailure(String str, int i) {
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
            public void onSuccess(JSONObject jSONObject) {
                new RoutingManager(jSONObject);
            }
        });
    }

    private static void playVideo(Item item, MainActivity mainActivity) {
        String queryParameter;
        String queryParameter2;
        try {
            Uri uri = Uri.parse(item.getClickUrl());
            Set<String> queryParameterNames = uri.getQueryParameterNames();
            String queryParameter3 = "";
            if (queryParameterNames.contains("vcmid")) {
                queryParameter = uri.getQueryParameter("vcmid");
            } else if (queryParameterNames.contains("vcmId")) {
                queryParameter = uri.getQueryParameter("vcmId");
            } else {
                queryParameter = queryParameterNames.contains("videoId") ? uri.getQueryParameter("videoId") : "";
            }
            if (queryParameterNames.contains("subChannelId")) {
                queryParameter3 = uri.getQueryParameter("subChannelId");
                queryParameter2 = uri.getQueryParameter("subChannelId");
            } else if (queryParameterNames.contains("channelId")) {
                queryParameter3 = uri.getQueryParameter("channelId");
                queryParameter2 = "";
            } else {
                queryParameter2 = "";
            }
            if (queryParameterNames.contains("galleryChannelId")) {
                queryParameter2 = uri.getQueryParameter("galleryChannelId");
            }
            if (queryParameter == null || queryParameter.length() <= 0 || queryParameter3 == null || queryParameter3.length() <= 0 || queryParameter2 == null || queryParameter2.length() <= 0 || CastManager.INSTANCE.getSharedInstance(mainActivity).getCurrentVcmId().equals(queryParameter)) {
                return;
            }
            Intent intent = new Intent(PiPSupportActivityKt.ACTION_VIDEO_CONTROL);
            intent.addFlags(268435456);
            intent.putExtra(PiPSupportActivityKt.EXTRA_CONTROL_TYPE, 5);
            mainActivity.sendBroadcast(intent);
            Bundle bundle = new Bundle();
            bundle.putString("vcmId", queryParameter);
            bundle.putString("channelId", queryParameter3);
            bundle.putString("galleryChannelId", queryParameter2);
            bundle.putLong("seekTo", item.getStartPosition());
            bundle.putString("mako_ref_comp", item.getMako_ref_comp());
            Intent intent2 = new Intent(mainActivity, (Class<?>) PlayerActivity.class);
            intent2.putExtras(bundle);
            mainActivity.resultActivityLauncher.launch(intent2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getLobbyChannelId(final String str, final int i, final MainActivity mainActivity, final int i2) {
        if (!isValidPath(str)) {
            openChromeCustomTab(mainActivity, str);
        } else {
            ApiService.getChannelIdByUrl(null, str, new ApiService.AsyncHTTPJSONResponseHandler() { // from class: com.channel2.mobile.ui.routing.RoutingManager.3
                @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
                public void onSuccess(JSONObject jSONObject) throws JSONException {
                    try {
                        String strOptString = jSONObject.optString("channelId");
                        if (strOptString.length() > 0) {
                            RoutingManager.goToNextScreen(i2, new LobbyItem(strOptString), i, null, mainActivity, null);
                        } else {
                            RoutingManager.openChromeCustomTab(mainActivity, str);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        RoutingManager.openChromeCustomTab(mainActivity, str);
                    }
                }

                @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPJSONResponseHandler
                public void onFailure(String str2, int i3) {
                    RoutingManager.openChromeCustomTab(mainActivity, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void openChromeCustomTab(MainActivity mainActivity, String str) {
        new ChromeCustomTabsApi().openLinkInChromeCustomTabs(mainActivity, str);
    }

    private static boolean isValidPath(String str) {
        String path;
        if (str != null && str.length() != 0) {
            try {
                Uri uri = Uri.parse(str);
                return (uri == null || (path = uri.getPath()) == null || !path.startsWith("/news-")) ? false : true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
