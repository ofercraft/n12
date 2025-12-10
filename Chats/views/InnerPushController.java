package com.channel2.mobile.ui.Chats.views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.media3.common.PlaybackException;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.channel2.mobile.ui.Chats.models.ChatMediaItem;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.routing.RoutingManager;
import com.facebook.login.widget.ToolTipPopup;
import com.google.android.gms.common.ConnectionResult;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class InnerPushController extends View implements View.OnClickListener {
    private final int ANIMATION_DURATION;
    private final String FILE_ICON;
    private final String IMAGE_ICON;
    private final int MOVE_DOWN;
    private final int MOVE_LEFT;
    private final int MOVE_RIGHT;
    private final int PUSH_DURATION;
    private final int TYPING_DURATION;
    private final String VIDEO_ICON;
    private final String VOICE_ICON;
    private ObjectAnimator animation;
    private ChatReportItem chatReportItem;
    public ViewGroup chat_push_internal;
    private Context context;
    private Handler handler;
    private Runnable hideRunnable;
    private boolean messageIsOnScreen;
    private TextView pushContent;
    private int pushHight;
    private ImageView pushImage;
    CoordinatorLayout.LayoutParams pushParams;
    private TextView pushReporter;
    private ImageView pushReporterImage;
    private int pushWidth;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        hide(0);
    }

    public InnerPushController(Context context) {
        super(context);
        this.MOVE_DOWN = 0;
        this.MOVE_RIGHT = 1;
        this.MOVE_LEFT = 2;
        this.ANIMATION_DURATION = 250;
        this.PUSH_DURATION = PlaybackException.ERROR_CODE_DRM_UNSPECIFIED;
        this.TYPING_DURATION = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        this.IMAGE_ICON = "📷 ";
        this.VIDEO_ICON = "📹 ";
        this.VOICE_ICON = "🎙️ ";
        this.FILE_ICON = "📁 ";
        this.hideRunnable = new Runnable() { // from class: com.channel2.mobile.ui.Chats.views.InnerPushController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0();
            }
        };
        this.context = context;
        MainActivity mainActivity = (MainActivity) context;
        this.chat_push_internal = (ViewGroup) mainActivity.findViewById(R.id.chat_push_internal);
        this.pushParams = new CoordinatorLayout.LayoutParams(-2, -2);
        this.pushReporterImage = (ImageView) this.chat_push_internal.findViewById(R.id.pushReporterImage);
        this.pushReporter = (TextView) this.chat_push_internal.findViewById(R.id.pushReporter);
        this.pushContent = (TextView) this.chat_push_internal.findViewById(R.id.pushContent);
        this.pushImage = (ImageView) this.chat_push_internal.findViewById(R.id.pushImage);
        int i = this.chat_push_internal.getLayoutParams().height;
        this.pushHight = i;
        this.chat_push_internal.setY(i);
        this.pushWidth = mainActivity.getScreenWidth();
        this.chat_push_internal.setOnClickListener(this);
        this.chat_push_internal.setOnTouchListener(new View.OnTouchListener() { // from class: com.channel2.mobile.ui.Chats.views.InnerPushController.1
            private double startX;
            private double startY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("swipe", "move " + motionEvent);
                if (motionEvent.getAction() == 0) {
                    this.startX = motionEvent.getAxisValue(0);
                    this.startY = motionEvent.getAxisValue(1);
                    Log.i("swipecheck", "X=" + this.startX + " Y=" + this.startY);
                }
                if (motionEvent.getAction() == 2) {
                    double axisValue = this.startX - motionEvent.getAxisValue(0);
                    double axisValue2 = this.startY - motionEvent.getAxisValue(1);
                    if (axisValue > 200.0d || axisValue <= -200.0d || axisValue2 >= 100.0d) {
                        view.setClickable(false);
                        motionEvent.setAction(1);
                    }
                    Log.i("swipecheck", "distance_move_X=" + axisValue + " distance_move_Y=" + axisValue2);
                }
                if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                    double axisValue3 = this.startX - motionEvent.getAxisValue(0);
                    double axisValue4 = this.startY - motionEvent.getAxisValue(1);
                    Log.i("swipecheck", "distanceX=" + axisValue3 + " distanceY=" + axisValue4);
                    if (axisValue4 < -100.0d) {
                        InnerPushController.this.hide(0);
                    } else {
                        view.performClick();
                    }
                }
                return true;
            }
        });
    }

    public void setData() {
        try {
            if (this.chatReportItem == null || this.chat_push_internal == null) {
                return;
            }
            Glide.with(this.pushReporterImage).load(this.chatReportItem.getReporterImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(this.pushReporterImage);
            this.pushReporter.setText(this.chatReportItem.getName() + StringUtils.SPACE + this.context.getResources().getString(R.string.inner_push_title));
            if (this.chatReportItem.getNumberOfMedias() > 0) {
                this.pushImage.setVisibility(0);
                RequestOptions requestOptionsTransform = new RequestOptions().transform(new CenterCrop(), new RoundedCorners(Utils.convertDipToPixels(this.context, 8.0f)));
                if (this.chatReportItem.getChatMedia(0).getMediaTypeId() == 2) {
                    Glide.with(this.pushImage).load(this.chatReportItem.getChatMedia(0).getThumbnail()).apply((BaseRequestOptions<?>) requestOptionsTransform).into(this.pushImage);
                } else {
                    Glide.with(this.pushImage).load(this.chatReportItem.getChatMedia(0).getLink1()).apply((BaseRequestOptions<?>) requestOptionsTransform).into(this.pushImage);
                }
                this.pushImage.requestLayout();
            } else {
                this.pushImage.setVisibility(8);
            }
            String strProcessText = processText(this.chatReportItem);
            if (strProcessText.length() > 0) {
                this.pushContent.setVisibility(0);
                setColorLinkText(strProcessText);
            } else {
                this.pushContent.setVisibility(8);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initPush(ChatReportItem chatReportItem) {
        setClickable(true);
        this.chatReportItem = chatReportItem;
        hide(0);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.views.InnerPushController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.show();
            }
        }, 1500L);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String processText(com.channel2.mobile.ui.Chats.models.ChatReportItem r8) {
        /*
            r7 = this;
            int r0 = r8.getNumberOfMedias()
            java.lang.String r1 = ""
            if (r0 <= 0) goto L93
            r2 = 0
            com.channel2.mobile.ui.Chats.models.ChatMediaItem r2 = r8.getChatMedia(r2)
            long r3 = r2.getMediaTypeId()
            int r3 = (int) r3
            r4 = 2
            r5 = 1
            if (r3 != r4) goto L28
            if (r0 != r5) goto L1c
            java.lang.String r0 = "סרטון"
            goto L20
        L1c:
            java.lang.String r0 = r7.getMultiFilesDescription(r8)
        L20:
            r1 = r0
            java.lang.String r0 = "📹 "
        L24:
            r6 = r1
            r1 = r0
            r0 = r6
            goto L3a
        L28:
            if (r3 != r5) goto L39
            if (r0 != r5) goto L30
            java.lang.String r0 = "תמונה"
            goto L34
        L30:
            java.lang.String r0 = r7.getMultiFilesDescription(r8)
        L34:
            r1 = r0
            java.lang.String r0 = "📷 "
            goto L24
        L39:
            r0 = r1
        L3a:
            java.lang.String r3 = r2.getMediaContent()
            if (r3 == 0) goto L5e
            java.lang.String r3 = r2.getMediaContent()
            int r3 = r3.length()
            if (r3 <= 0) goto L5e
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r1)
            java.lang.String r0 = r2.getMediaContent()
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            goto L91
        L5e:
            java.lang.String r2 = r8.getMessageContent()
            if (r2 == 0) goto L82
            java.lang.String r2 = r8.getMessageContent()
            int r2 = r2.length()
            if (r2 <= 0) goto L82
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            java.lang.String r8 = r8.getMessageContent()
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            goto L91
        L82:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r1)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
        L91:
            r1 = r8
            goto Lb3
        L93:
            java.lang.String r0 = r8.getMessageContent()
            if (r0 == 0) goto Lb3
            java.lang.String r0 = r8.getMessageContent()
            int r0 = r0.length()
            if (r0 <= 0) goto Lb3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            java.lang.String r8 = r8.getMessageContent()
            r0.append(r8)
            java.lang.String r1 = r0.toString()
        Lb3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.channel2.mobile.ui.Chats.views.InnerPushController.processText(com.channel2.mobile.ui.Chats.models.ChatReportItem):java.lang.String");
    }

    private String getMultiFilesDescription(ChatReportItem chatReportItem) {
        int size = chatReportItem.getChatMedias().size();
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            ChatMediaItem chatMediaItem = chatReportItem.getChatMedias().get(i);
            if (chatMediaItem.getMediaTypeId() == 1) {
                z = true;
            }
            if (chatMediaItem.getMediaTypeId() == 2) {
                z2 = true;
            }
        }
        String str = size + StringUtils.SPACE;
        if (z && z2) {
            return str + "קבצים";
        }
        if (!z2) {
            return str + "תמונות";
        }
        if (z) {
            return "";
        }
        return str + "סרטונים";
    }

    private void setColorLinkText(String str) {
        ArrayList arrayList = new ArrayList();
        Pattern pattern = Patterns.WEB_URL;
        String lowerCase = str.toLowerCase();
        Log.d("setMessageContent", lowerCase);
        Matcher matcher = pattern.matcher(lowerCase);
        while (matcher.find()) {
            String strReplaceAll = matcher.group().replaceAll("^[\\u0590-\\u05FF]", "");
            Matcher matcher2 = Pattern.compile("^(https?://)?(www\\.)?([a-zA-Z0-9]+(-?[a-zA-Z0-9])*\\.)+[\\w]{2,}(/\\S*)?$").matcher(strReplaceAll);
            while (matcher2.find()) {
                arrayList.add(strReplaceAll);
            }
        }
        SpannableString spannableString = new SpannableString(str);
        for (int i = 0; i < arrayList.size(); i++) {
            String str2 = (String) arrayList.get(i);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#3366BB")), lowerCase.indexOf(str2), lowerCase.indexOf(str2) + str2.length(), 0);
        }
        this.pushContent.setText(spannableString);
    }

    public void show() {
        Log.d("Inner_LOG", "show nofitcation " + this.chatReportItem.getMessageID());
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.hideRunnable);
        }
        this.pushParams.gravity = 80;
        this.chat_push_internal.setLayoutParams(this.pushParams);
        this.chat_push_internal.setVisibility(0);
        this.chat_push_internal.animate().translationY(0.0f).setDuration(250L);
        this.messageIsOnScreen = true;
        Handler handler2 = new Handler(Looper.getMainLooper());
        this.handler = handler2;
        handler2.postDelayed(this.hideRunnable, ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME);
        setData();
        try {
            Bundle bundle = new Bundle();
            bundle.putString("MessageId", String.valueOf(this.chatReportItem.getMessageID()));
            FirebaseAnalytics.getInstance(this.context).logEvent("Inner_Notification_Displayed", bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hide(int i) {
        if (this.messageIsOnScreen) {
            int i2 = this.pushHight + 0;
            Log.i("swipe", "distance to go " + i2);
            Handler handler = this.handler;
            if (handler != null) {
                handler.removeCallbacks(this.hideRunnable);
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.chat_push_internal, "translationY", i2);
            this.animation = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(250L).removeAllListeners();
            this.animation.addListener(new Animator.AnimatorListener() { // from class: com.channel2.mobile.ui.Chats.views.InnerPushController.2
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    InnerPushController.this.chat_push_internal.setVisibility(4);
                    Log.d("Inner_LOG", "hide nofitcation " + InnerPushController.this.chatReportItem.getMessageID());
                }
            });
            this.animation.start();
            this.messageIsOnScreen = false;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Log.i("swipe", "clicked");
        try {
            if (this.chatReportItem != null) {
                Handler handler = this.handler;
                if (handler != null) {
                    handler.removeCallbacks(this.hideRunnable);
                } else {
                    this.handler = new Handler(Looper.getMainLooper());
                }
                this.handler.postDelayed(this.hideRunnable, 100L);
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("MessageId", String.valueOf(this.chatReportItem.getMessageID()));
                    FirebaseAnalytics.getInstance(this.context).logEvent("Inner_Notification_Pressed", bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("partner", "Notification");
                bundle2.putLong("messageId", this.chatReportItem.getMessageID());
                this.chatReportItem.setClickUrl("messageId=" + this.chatReportItem.getMessageID());
                RoutingManager.goToNextScreen(R.id.lobby_fragment_container, this.chatReportItem, ((MainActivity) this.context).currentTab, bundle2, (MainActivity) this.context, null);
            }
        } catch (Exception e2) {
            Log.i("swipe", "BOOM " + e2);
            e2.printStackTrace();
        }
    }
}
