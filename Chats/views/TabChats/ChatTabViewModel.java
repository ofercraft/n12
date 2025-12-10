package com.channel2.mobile.ui.Chats.views.TabChats;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.channel2.mobile.ui.Chats.models.ChatMediaItem;
import com.channel2.mobile.ui.Chats.models.ChatReportItem;
import com.channel2.mobile.ui.Chats.models.ChatTopic;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.helpers.AnalyticsManager;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.routing.RoutingManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class ChatTabViewModel extends CustomRecyclerViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public static final int MAX_LINES = 11;
    private View bg;
    private TextView content;
    private String contentStr;
    private Context context;
    private Group groupChat;
    private Group groupTyping;
    public Button readMore;
    private TextView reporter;
    private ImageView reporterImage;
    private TextView reporterShared;
    View selectedAnimationView;
    private ImageView sharedArrow;
    private TextView sharedTxt;
    private Long start;
    private TextView time;
    private ChatTopic topic;
    private int topicColor;
    private ImageView typing;
    private View typingBG;

    public void onScrollStateIdle() {
    }

    public ChatTabViewModel(View view) {
        super(view);
        this.topicColor = Color.parseColor("#AE0000");
        view.setOnLongClickListener(this);
        this.context = view.getContext().getApplicationContext();
        this.reporterImage = (ImageView) view.findViewById(R.id.reporterImage);
        this.reporter = (TextView) view.findViewById(R.id.reporter);
        this.time = (TextView) view.findViewById(R.id.time);
        this.bg = view.findViewById(R.id.bg);
        this.typingBG = view.findViewById(R.id.typingBG);
        this.typing = (ImageView) view.findViewById(R.id.chat_typing);
        this.sharedArrow = (ImageView) view.findViewById(R.id.sharedArrowImg);
        this.sharedTxt = (TextView) view.findViewById(R.id.sharedTxt);
        this.reporterShared = (TextView) view.findViewById(R.id.reporterShared);
        this.groupChat = (Group) view.findViewById(R.id.groupChat);
        this.groupTyping = (Group) view.findViewById(R.id.groupTyping);
        this.selectedAnimationView = view.findViewById(R.id.selectedAnimationView);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
    }

    private void setTime() {
        this.time.setText(((ChatReportItem) this.lobbyItem).getTimeString());
        this.time.setTextColor(this.topicColor);
        this.time.setOnLongClickListener(this);
    }

    public void init(ChatTopic chatTopic) {
        this.topic = chatTopic;
        if (chatTopic.getColor() != null && chatTopic.getColor().length() > 0) {
            try {
                this.topicColor = Color.parseColor("#" + chatTopic.getColor());
            } catch (Exception e) {
                e.printStackTrace();
                this.topicColor = Color.parseColor("#AE0000");
            }
        }
        setTime();
        this.reporterImage.setOnLongClickListener(this);
        this.reporter.setOnLongClickListener(this);
        ChatReportItem chatReportItem = (ChatReportItem) this.lobbyItem;
        Glide.with(this.reporterImage).load(chatReportItem.getReporterImage()).placeholder(R.drawable.reporter_place_holder).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(this.reporterImage);
        this.reporter.setText(chatReportItem.getName());
        this.reporter.setTextColor(this.topicColor);
        if (chatReportItem.isFirst()) {
            this.reporter.setVisibility(0);
            this.reporter.setAlpha(1.0f);
            this.reporterImage.setAlpha(1.0f);
            setTopMargin(8);
        } else {
            this.reporterImage.setAlpha(0.0f);
            this.reporter.setVisibility(8);
            setTopMargin(0);
        }
        if (chatReportItem.messageState == ChatReportItem.MessageState.regular) {
            this.reporterShared.setTextSize(0.0f);
            this.reporter.setAlpha(1.0f);
            this.sharedArrow.setVisibility(8);
            this.sharedTxt.setVisibility(8);
            this.time.setTextSize(2, 13.0f);
            return;
        }
        this.sharedArrow.setVisibility(0);
        this.sharedTxt.setVisibility(0);
        if (chatReportItem.messageState == ChatReportItem.MessageState.shared) {
            this.reporterShared.setTextSize(0.0f);
            if (!chatReportItem.isFirst()) {
                this.reporter.setVisibility(0);
                this.reporter.setAlpha(0.0f);
            }
            this.time.setTextSize(2, 13.0f);
            return;
        }
        this.reporter.setVisibility(0);
        this.reporter.setAlpha(1.0f);
        this.reporterShared.setText(chatReportItem.getSharedName());
        this.reporterShared.setTextSize(2, 13.0f);
        this.time.setTextSize(0.0f);
    }

    private void setTopMargin(int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.bg.getLayoutParams();
        float f = this.itemView.getContext().getApplicationContext().getResources().getDisplayMetrics().density;
        marginLayoutParams.setMargins(0, (int) (i * f), (int) (f * 8.0f), 0);
        this.bg.setLayoutParams(marginLayoutParams);
    }

    private void setBottomMargin(View view, int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.bottomMargin = (int) (i * this.itemView.getContext().getApplicationContext().getResources().getDisplayMetrics().density);
        this.sharedArrow.setLayoutParams(marginLayoutParams);
    }

    public void initTyping() {
        this.reporterImage.setVisibility(0);
        this.reporterImage.setAlpha(1.0f);
        this.typing.post(new Runnable() { // from class: com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel.1
            @Override // java.lang.Runnable
            public void run() {
                AnimationDrawable animationDrawable = (AnimationDrawable) ChatTabViewModel.this.typing.getBackground();
                if (animationDrawable.isRunning()) {
                    return;
                }
                ChatTabViewModel.this.start = Long.valueOf(new Date().getTime());
                animationDrawable.start();
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel.2
            @Override // java.lang.Runnable
            public void run() {
                Log.d("typing", "drawcomplete after " + (new Date().getTime() - ChatTabViewModel.this.start.longValue()) + " miliseconds");
                ChatTabViewModel.this.groupTyping.setVisibility(4);
            }
        }, 1500L);
    }

    public String getSource(ChatMediaItem chatMediaItem) {
        if (chatMediaItem.getLink2() != null && !chatMediaItem.getLink2().equals("")) {
            return chatMediaItem.getLink2();
        }
        return chatMediaItem.getLink1();
    }

    public void setVideoImage(ImageView imageView, ImageView imageView2, ChatMediaItem chatMediaItem) {
        String source;
        imageView.setTag(R.string.chat_media, Long.valueOf(chatMediaItem.getAutoId()));
        imageView.setOnClickListener(this);
        imageView.setOnLongClickListener(this);
        if (chatMediaItem.getMediaTypeId() == 2) {
            source = chatMediaItem.getThumbnail();
        } else {
            source = getSource(chatMediaItem);
        }
        Glide.with(imageView).load(source).into(imageView);
        if (imageView2 != null) {
            imageView2.setTag(R.string.chat_media, Long.valueOf(chatMediaItem.getAutoId()));
            imageView2.setOnClickListener(this);
            imageView2.setOnLongClickListener(this);
            if (chatMediaItem.getMediaTypeId() == 2) {
                imageView2.setVisibility(0);
            } else {
                imageView2.setVisibility(8);
            }
        }
    }

    public void animateSelection() throws Resources.NotFoundException {
        this.selectedAnimationView.setAlpha(1.0f);
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.context, R.anim.chat_fade_in_selection);
        this.selectedAnimationView.startAnimation(animationLoadAnimation);
        animationLoadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) throws Resources.NotFoundException {
                if (((ChatReportItem) ChatTabViewModel.this.lobbyItem).messageState == ChatReportItem.MessageState.special) {
                    ((ChatReportItem) ChatTabViewModel.this.lobbyItem).messageState = ChatReportItem.MessageState.blue;
                } else {
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(ChatTabViewModel.this.context, R.anim.chat_fade_out_selection);
                    ChatTabViewModel.this.selectedAnimationView.startAnimation(animationLoadAnimation2);
                    animationLoadAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel.3.1
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation2) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation2) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation2) {
                            ChatTabViewModel.this.selectedAnimationView.setAlpha(0.0f);
                        }
                    });
                }
            }
        });
    }

    protected void handleContent(TextView textView, String str, Button button) {
        this.readMore = button;
        this.content = textView;
        this.contentStr = str;
        textView.setOnLongClickListener(this);
        textView.setOnLongClickListener(this);
        setMessageContent(textView, str);
        String str2 = this.contentStr;
        if (str2 != null && str2.equals("")) {
            this.content.setVisibility(8);
            return;
        }
        this.content.setVisibility(0);
        if (((ChatReportItem) this.lobbyItem).isReadMoreClick()) {
            this.content.setMaxLines(Integer.MAX_VALUE);
        } else {
            this.content.post(new Runnable() { // from class: com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel.4
                @Override // java.lang.Runnable
                public void run() {
                    if (ChatTabViewModel.this.content.getLineCount() > 11) {
                        ChatTabViewModel.this.content.setMaxLines(11);
                        ChatTabViewModel.this.readMore.setVisibility(0);
                    } else {
                        ChatTabViewModel.this.readMore.setVisibility(8);
                    }
                }
            });
        }
    }

    public void readMore() {
        Bundle bundle = new Bundle();
        bundle.putString("MessageId", String.valueOf(((ChatReportItem) this.lobbyItem).getMessageID()));
        bundle.putString("chatName", this.topic.getTopicName());
        FirebaseAnalytics.getInstance(this.context).logEvent("RC_read_more", bundle);
        AnalyticsManager.getInstance().reportPageView(this.context, "RC_More", "RC_Activities", "", "reportersChat", "", "specialEvent", this.topic.getTopicName());
        ((ChatReportItem) this.lobbyItem).setReadMoreClick(true);
        this.content.setMaxLines(Integer.MAX_VALUE);
        this.content.setText(this.contentStr);
        this.readMore.setVisibility(8);
    }

    public void attachedToWindow() {
        if (this.selectedAnimationView != null) {
            if (((ChatReportItem) this.lobbyItem).messageState == ChatReportItem.MessageState.blue) {
                this.selectedAnimationView.setAlpha(1.0f);
            } else {
                this.selectedAnimationView.setAlpha(0.0f);
            }
        }
    }

    public void setMessageContent(TextView textView, String str) {
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
            final String str2 = (String) arrayList.get(i);
            spannableString.setSpan(new InternalURLSpan(new View.OnClickListener() { // from class: com.channel2.mobile.ui.Chats.views.TabChats.ChatTabViewModel.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    view.getContext();
                    String str3 = str2;
                    int i2 = MainConfig.appData.getMainActivity().currentTab;
                    CustomFragment customFragment = MainConfig.appData.getMainActivity().fragments.get(i2);
                    ChatReportItem chatReportItem = (ChatReportItem) ChatTabViewModel.this.lobbyItem;
                    chatReportItem.setClickUrl(str3);
                    chatReportItem.setMako_ref_comp("chat");
                    RoutingManager.goToNextScreen(customFragment.getFragmentContainerId(), chatReportItem, i2, MainConfig.appData.getMainActivity(), null);
                    Log.i("", "");
                    Bundle bundle = new Bundle();
                    bundle.putString("Message_ID", String.valueOf(chatReportItem.getMessageID()));
                    bundle.putString("Refferal", String.valueOf(str3));
                    bundle.putString("chatName", ChatTabViewModel.this.topic.getTopicName());
                    FirebaseAnalytics.getInstance(MainConfig.appData.getMainActivity()).logEvent("RC_click_link", bundle);
                    AnalyticsManager.getInstance().reportPageView(ChatTabViewModel.this.itemView.getContext().getApplicationContext(), "RC_Link", "RC_Activities", "", "reportersChat", "", "specialEvent", ChatTabViewModel.this.topic.getTopicName());
                }
            }), lowerCase.indexOf(str2), lowerCase.indexOf(str2) + str2.length(), 0);
        }
        textView.setText(spannableString);
        textView.setLinkTextColor(this.context.getResources().getColorStateList(R.color.chat_blue_link));
        textView.setLinksClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setFocusable(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long jLongValue = view.getTag(R.string.chat_media) != null ? ((Long) view.getTag(R.string.chat_media)).longValue() : 0L;
        Bundle bundle = new Bundle();
        bundle.putString("clickType", "click");
        bundle.putLong("mediaID", jLongValue);
        this.handler.onClick((ChatReportItem) this.lobbyItem, bundle);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        long jLongValue = view.getTag(R.string.chat_media) != null ? ((Long) view.getTag(R.string.chat_media)).longValue() : 0L;
        Bundle bundle = new Bundle();
        bundle.putString("clickType", "longClick");
        bundle.putLong("mediaID", jLongValue);
        if (this.handler == null) {
            return true;
        }
        this.handler.onClick((ChatReportItem) this.lobbyItem, bundle);
        return true;
    }
}
