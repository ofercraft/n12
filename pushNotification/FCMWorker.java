package com.channel2.mobile.ui.pushNotification;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.channel2.mobile.ui.BuildConfig;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.splash.MyApplication;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.security.CertificateUtil;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.ToLongFunction;

/* loaded from: classes2.dex */
public class FCMWorker extends Worker {
    private static final String TAG = "FCMWorker";
    private String chatTitle;
    private boolean isChatPush;
    protected boolean isSilentPush;
    protected Context mContext;
    protected Uri path;
    protected String picUrl;
    protected String shareDescription;
    protected String shareTitle;
    protected String shareUrl;
    protected boolean showLive;

    public FCMWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.chatTitle = "";
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() throws NumberFormatException {
        Log.d(TAG, "Performing long running task in scheduled job");
        this.mContext = getApplicationContext();
        if (!isAppRunning() || !isAppOnForeground()) {
            Context context = this.mContext;
            if (Utils.getBoolFromPreferences(context, context.getResources().getString(R.string.outsidePush))) {
                clearNotificationsIfNeeded();
                handleNotificationAfterLoadingConfigs(getInputData());
            }
        }
        return ListenableWorker.Result.success();
    }

    private void handleNotificationAfterLoadingConfigs(Data data) throws NumberFormatException {
        long jNextInt;
        NotificationCompat.Builder builder;
        if (data != null) {
            String string = data.getString("alert");
            String string2 = data.getString("sound");
            String string3 = data.getString("push_description");
            String string4 = data.getString("push_title");
            String string5 = data.getString("url");
            String string6 = data.getString("pushId");
            this.picUrl = data.getString("image");
            this.chatTitle = data.getString("title");
            this.isChatPush = Boolean.parseBoolean(data.getString("newsChat"));
            try {
                jNextInt = Long.parseLong(string6);
            } catch (Exception unused) {
                jNextInt = new Random().nextInt(10000000) + 1000000;
            }
            Log.i("pushnotification", "messageId == " + jNextInt);
            if (string5.length() > 5) {
                string5 = string5 + (string5.contains("?") ? "&" : "?") + ("utm_source=news12_App&utm_medium=Push_Notification&utm_campaign=" + string6);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (string3 == null) {
                string3 = string;
            }
            this.isSilentPush = true;
            Context context = this.mContext;
            boolean boolFromPreferences = Utils.getBoolFromPreferences(context, context.getResources().getString(R.string.enablePushSound));
            if (string2 != null && string2.length() > 0 && boolFromPreferences) {
                this.isSilentPush = false;
                this.path = Uri.parse("android.resource://" + this.mContext.getPackageName() + "/2131951621");
            }
            if (this.isSilentPush) {
                builder = new NotificationCompat.Builder(this.mContext, "news_silent");
            } else {
                builder = new NotificationCompat.Builder(this.mContext, "news");
                builder.setSound(this.path);
            }
            builder.setTicker(string3);
            builder.setContentText(string);
            builder.setSmallIcon(R.drawable.news_icon_white);
            builder.setColor(ContextCompat.getColor(this.mContext, R.color.colorPrimaryDark));
            if (Build.VERSION.SDK_INT >= 31) {
                builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
            }
            builder.setWhen(jCurrentTimeMillis);
            builder.setAutoCancel(true);
            String str = "" + Calendar.getInstance().get(11);
            if (str.length() == 1) {
                str = AppEventsConstants.EVENT_PARAM_VALUE_NO + str;
            }
            String str2 = "" + Calendar.getInstance().get(12);
            if (str2.length() == 1) {
                str2 = AppEventsConstants.EVENT_PARAM_VALUE_NO + str2;
            }
            String str3 = str + CertificateUtil.DELIMITER + str2;
            this.shareUrl = "";
            this.shareTitle = "";
            this.shareDescription = "";
            Intent intent = new Intent();
            intent.setClassName(this.mContext, "com.channel2.mobile.ui.splash.SplashActivity");
            intent.setAction(Long.toString(System.currentTimeMillis()));
            intent.putExtra("OPENED_FROM_NOTIFICATION", true);
            intent.putExtra("pushId", String.valueOf(jNextInt));
            intent.putExtra("url", string5);
            builder.setContentIntent(PendingIntent.getActivity(this.mContext, 0, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL));
            Notification notificationBuild = builder.build();
            if (!string5.startsWith("http")) {
                if (!string5.startsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
                    string5 = RemoteSettings.FORWARD_SLASH_STRING + string5;
                }
                string5 = "http://www.mako.co.il" + string5;
            }
            if (string5.indexOf("?") > 0) {
                this.shareUrl = string5.substring(0, string5.indexOf("?"));
            } else {
                this.shareUrl = string5;
            }
            this.shareTitle = string4;
            this.shareDescription = string;
            processBeutifulNotification(this.mContext, string4, string, str3, notificationBuild, null, jNextInt);
        }
    }

    private void processBeutifulNotification(final Context context, final String str, final String str2, final String str3, final Notification notification, RemoteViews remoteViews, final long j) {
        String str4 = this.picUrl;
        if (str4 != null && !str4.equals("")) {
            int screenWidth = Utils.getScreenWidth(context);
            try {
                Glide.with(context).asBitmap().load(this.picUrl).override(screenWidth, (int) ((screenWidth / 624.0f) * 383.0f)).format(DecodeFormat.PREFER_RGB_565).into((RequestBuilder) new SimpleTarget<Bitmap>() { // from class: com.channel2.mobile.ui.pushNotification.FCMWorker.1
                    @Override // com.bumptech.glide.request.target.Target
                    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                    }

                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        try {
                            FCMWorker.this.processBeutifulNotificationWithImage(context, str, str2, str3, notification, j, bitmap);
                        } catch (Exception unused) {
                            FCMWorker.this.processBeutifulNotificationWithImage(context, str, str2, str3, notification, j, null);
                        }
                    }
                });
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        BitmapFactory.decodeResource(context.getResources(), R.drawable.app_logo_n12);
        processBeutifulNotificationWithImage(context, str, str2, str3, notification, j, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processBeutifulNotificationWithImage(Context context, String str, String str2, String str3, Notification notification, long j, Bitmap bitmap) {
        RemoteViews remoteViews;
        RemoteViews remoteViews2;
        String str4;
        RemoteViews remoteViews3;
        String str5;
        RemoteViews remoteViews4;
        if (bitmap != null) {
            if (Build.VERSION.SDK_INT >= 31) {
                remoteViews4 = new RemoteViews(context.getPackageName(), R.layout.notification_new_big_android12);
            } else {
                remoteViews4 = new RemoteViews(context.getPackageName(), R.layout.notification_new_big);
            }
            remoteViews4.setTextViewText(R.id.notificationDescription, str2);
            remoteViews4.setTextViewText(R.id.time, str3);
            remoteViews4.setImageViewBitmap(R.id.notificationImage, bitmap);
            if (this.isChatPush) {
                remoteViews4.setViewVisibility(R.id.buttonsLayout, 8);
                String str6 = this.chatTitle;
                if (str6 != null && str6.length() > 0) {
                    remoteViews4.setTextViewTextSize(R.id.chatTitle, 2, 13.0f);
                    remoteViews4.setTextViewText(R.id.chatTitle, this.chatTitle);
                }
            } else {
                remoteViews4.setTextViewTextSize(R.id.chatTitle, 2, 0.0f);
            }
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            String str7 = this.shareTitle;
            if (str7 != null) {
                String strReplaceAll = str7.replaceAll("&quot;", "\"");
                this.shareTitle = strReplaceAll;
                intent.putExtra("android.intent.extra.SUBJECT", strReplaceAll);
            }
            if (this.shareDescription == null) {
                this.shareDescription = "";
            }
            this.shareDescription = this.shareDescription.replaceAll("&quot;", "\"");
            intent.putExtra("android.intent.extra.TEXT", this.shareDescription + System.getProperty("line.separator") + this.shareUrl + System.getProperty("line.separator"));
            remoteViews4.setOnClickPendingIntent(R.id.shareBtn, PendingIntent.getActivity(context, 0, Intent.createChooser(intent, "שתף"), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL));
            notification.bigContentView = remoteViews4;
        } else if (this.isChatPush) {
            if (Build.VERSION.SDK_INT >= 31) {
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_new_big_chat_text_android12);
            } else {
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_new_big_chat_text);
            }
            remoteViews.setTextViewText(R.id.notificationDescription, str2);
            remoteViews.setTextViewText(R.id.time, str3);
            remoteViews.setTextViewText(R.id.chatTitle, this.chatTitle);
            notification.bigContentView = remoteViews;
        }
        if (bitmap != null) {
            if (this.isChatPush && (str5 = this.chatTitle) != null && str5.length() > 0) {
                if (Build.VERSION.SDK_INT >= 31) {
                    remoteViews2 = new RemoteViews(context.getPackageName(), R.layout.notification_new_small_image_chat_android12);
                } else {
                    remoteViews2 = new RemoteViews(context.getPackageName(), R.layout.notification_new_small_image_chat);
                }
                remoteViews2.setTextViewText(R.id.chatTitle, this.chatTitle);
            } else if (Build.VERSION.SDK_INT >= 31) {
                remoteViews2 = new RemoteViews(context.getPackageName(), R.layout.notification_new_small_image_android12);
            } else {
                remoteViews2 = new RemoteViews(context.getPackageName(), R.layout.notification_new_small_image);
            }
            remoteViews2.setImageViewBitmap(R.id.notificationImage, bitmap);
        } else if (this.isChatPush && (str4 = this.chatTitle) != null && str4.length() > 0) {
            if (Build.VERSION.SDK_INT >= 31) {
                remoteViews3 = new RemoteViews(context.getPackageName(), R.layout.notification_new_no_image_chat_android12);
            } else {
                remoteViews3 = new RemoteViews(context.getPackageName(), R.layout.notification_new_no_image_chat);
            }
            remoteViews2 = remoteViews3;
            remoteViews2.setTextViewText(R.id.chatTitle, this.chatTitle);
        } else if (Build.VERSION.SDK_INT >= 31) {
            remoteViews2 = new RemoteViews(context.getPackageName(), R.layout.notification_new_no_image_android12);
        } else {
            remoteViews2 = new RemoteViews(context.getPackageName(), R.layout.notification_new_no_image);
        }
        remoteViews2.setTextViewText(R.id.notificationDescription, str2);
        remoteViews2.setTextViewText(R.id.time, str3);
        notification.contentView = remoteViews2;
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.notify((int) j, notification);
            Log.i("pushnotification", "notify == " + j);
        }
    }

    private boolean isAppRunning() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.mContext.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
        while (it.hasNext()) {
            if (it.next().processName.equals(BuildConfig.APPLICATION_ID)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAppOnForeground() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.mContext.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = this.mContext.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return !((MyApplication) getApplicationContext()).getIsPiP();
            }
        }
        return false;
    }

    private void clearNotificationsIfNeeded() {
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        LinkedList linkedList = new LinkedList(Arrays.asList(notificationManager.getActiveNotifications()));
        if (Build.VERSION.SDK_INT >= 24) {
            linkedList.sort(Comparator.comparingLong(new ToLongFunction() { // from class: com.channel2.mobile.ui.pushNotification.FCMWorker$$ExternalSyntheticLambda0
                @Override // java.util.function.ToLongFunction
                public final long applyAsLong(Object obj) {
                    return ((StatusBarNotification) obj).getPostTime();
                }
            }));
        } else {
            Collections.sort(linkedList, new Comparator() { // from class: com.channel2.mobile.ui.pushNotification.FCMWorker$$ExternalSyntheticLambda1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return FCMWorker.lambda$clearNotificationsIfNeeded$0((StatusBarNotification) obj, (StatusBarNotification) obj2);
                }
            });
        }
        if (linkedList.size() > 0) {
            Log.d("checkTray", "FCMWorker: number of notifications before =  " + linkedList.size() + " | first = " + ((StatusBarNotification) linkedList.get(0)).getId());
        }
        while (linkedList.size() >= 24) {
            notificationManager.cancel(((StatusBarNotification) linkedList.get(0)).getId());
            linkedList.remove(0);
            Log.d("checkTray", "FCMWorker: number of notifications after =  " + linkedList.size());
        }
    }

    static /* synthetic */ int lambda$clearNotificationsIfNeeded$0(StatusBarNotification statusBarNotification, StatusBarNotification statusBarNotification2) {
        return (int) (statusBarNotification.getPostTime() - statusBarNotification2.getPostTime());
    }
}
