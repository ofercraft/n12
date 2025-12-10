package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class LobbyChatItemMediaBinding implements ViewBinding {
    public final View bg;
    public final ImageView chatTyping;
    public final Group groupChat;
    public final Group groupTyping;
    public final ImageView media1;
    public final ImageView media2;
    public final ImageView media3;
    public final TextView moreMedia;
    public final ImageView play;
    public final TextView reporter;
    public final ImageView reporterImage;
    private final ConstraintLayout rootView;
    public final TextView time;
    public final View typingBG;

    private LobbyChatItemMediaBinding(ConstraintLayout constraintLayout, View view, ImageView imageView, Group group, Group group2, ImageView imageView2, ImageView imageView3, ImageView imageView4, TextView textView, ImageView imageView5, TextView textView2, ImageView imageView6, TextView textView3, View view2) {
        this.rootView = constraintLayout;
        this.bg = view;
        this.chatTyping = imageView;
        this.groupChat = group;
        this.groupTyping = group2;
        this.media1 = imageView2;
        this.media2 = imageView3;
        this.media3 = imageView4;
        this.moreMedia = textView;
        this.play = imageView5;
        this.reporter = textView2;
        this.reporterImage = imageView6;
        this.time = textView3;
        this.typingBG = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LobbyChatItemMediaBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LobbyChatItemMediaBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.lobby_chat_item_media, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LobbyChatItemMediaBinding bind(View view) {
        int i = R.id.bg;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.bg);
        if (viewFindChildViewById != null) {
            i = R.id.chat_typing;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.chat_typing);
            if (imageView != null) {
                i = R.id.groupChat;
                Group group = (Group) ViewBindings.findChildViewById(view, R.id.groupChat);
                if (group != null) {
                    i = R.id.groupTyping;
                    Group group2 = (Group) ViewBindings.findChildViewById(view, R.id.groupTyping);
                    if (group2 != null) {
                        i = R.id.media1;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.media1);
                        if (imageView2 != null) {
                            i = R.id.media2;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.media2);
                            if (imageView3 != null) {
                                i = R.id.media3;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.media3);
                                if (imageView4 != null) {
                                    i = R.id.more_media;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.more_media);
                                    if (textView != null) {
                                        i = R.id.play;
                                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.play);
                                        if (imageView5 != null) {
                                            i = R.id.reporter;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.reporter);
                                            if (textView2 != null) {
                                                i = R.id.reporterImage;
                                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.reporterImage);
                                                if (imageView6 != null) {
                                                    i = R.id.time;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                                                    if (textView3 != null) {
                                                        i = R.id.typingBG;
                                                        View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.typingBG);
                                                        if (viewFindChildViewById2 != null) {
                                                            return new LobbyChatItemMediaBinding((ConstraintLayout) view, viewFindChildViewById, imageView, group, group2, imageView2, imageView3, imageView4, textView, imageView5, textView2, imageView6, textView3, viewFindChildViewById2);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
