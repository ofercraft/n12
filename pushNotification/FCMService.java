package com.channel2.mobile.ui.pushNotification;

import android.util.Log;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.helpers.Utils;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

/* loaded from: classes2.dex */
public class FCMService extends FirebaseMessagingService {
    private static final String TAG = "FCMService";

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) throws Throwable {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            scheduleJob(remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        Log.i(FirebaseMessaging.INSTANCE_ID_SCOPE, str);
        sendRegistrationToServer(str);
    }

    private void scheduleJob(Map<String, String> map) throws Throwable {
        Log.i(TtmlNode.ANNOTATION_POSITION_OUTSIDE, "user gor oush and register to outpush: " + Utils.getBoolFromPreferences(this, getResources().getString(R.string.outsidePush)));
        WorkManager.getInstance(getApplicationContext()).beginWith(new OneTimeWorkRequest.Builder(FCMWorker.class).setInputData(new Data.Builder().putString("push_title", map.containsKey("push_title") ? map.get("push_title") : "").putString("push_description", map.containsKey("push_description") ? map.get("push_description") : "").putString("alert", map.containsKey("alert") ? map.get("alert") : "").putString("sound", map.containsKey("sound") ? map.get("sound") : "").putString("pushId", map.containsKey("pushId") ? map.get("pushId") : "").putString("url", map.containsKey("url") ? map.get("url") : "").putString("image", map.containsKey("image") ? map.get("image") : "").putString("title", map.containsKey("title") ? map.get("title") : "").putString("newsChat", map.containsKey("newsChat") ? map.get("newsChat") : "").build()).addTag(map.containsKey("pushId") ? map.get("pushId") : "").build()).enqueue();
    }

    private void sendRegistrationToServer(String str) {
        Utils.saveStringToPreferences(this, getResources().getString(R.string.regId), str);
    }
}
