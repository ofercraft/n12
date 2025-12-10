package com.channel2.mobile.ui.reports;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.AccessToken;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class CustomBroadcastReceiver extends BroadcastReceiver {
    static BroadcastReceiverCallBack handler;
    String appShare = "Other";

    public interface BroadcastReceiverCallBack {
        void onBroadCastReceive(String str);
    }

    public CustomBroadcastReceiver(BroadcastReceiverCallBack broadcastReceiverCallBack) {
        handler = broadcastReceiverCallBack;
    }

    public CustomBroadcastReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            String strValueOf = intent.getExtras() != null ? String.valueOf(intent.getExtras().get("android.intent.extra.CHOSEN_COMPONENT")) : "";
            if (Pattern.compile(Pattern.quote("whatsapp"), 2).matcher(strValueOf).find()) {
                this.appShare = "WhatsApp";
            } else if (Pattern.compile(Pattern.quote(AccessToken.DEFAULT_GRAPH_DOMAIN), 2).matcher(strValueOf).find()) {
                this.appShare = "Facebook";
            } else if (Pattern.compile(Pattern.quote("mail"), 2).matcher(strValueOf).find()) {
                this.appShare = "Mail";
            } else if (Pattern.compile(Pattern.quote("telegram"), 2).matcher(strValueOf).find()) {
                this.appShare = "Telegram";
            } else if (Pattern.compile(Pattern.quote("slack"), 2).matcher(strValueOf).find()) {
                this.appShare = "Slack";
            }
            handler.onBroadCastReceive(this.appShare);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
