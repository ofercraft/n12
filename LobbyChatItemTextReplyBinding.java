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
public final class LobbyChatItemTextReplyBinding implements ViewBinding {
    public final View bg;
    public final ImageView chatTyping;
    public final TextView content;
    public final TextView contentReply;
    public final View devider;
    public final Group groupChat;
    public final Group groupTyping;
    public final TextView reporter;
    public final ImageView reporterImage;
    public final TextView reporterReply;
    private final ConstraintLayout rootView;
    public final TextView time;
    public final View typingBG;

    private LobbyChatItemTextReplyBinding(ConstraintLayout constraintLayout, View view, ImageView imageView, TextView textView, TextView textView2, View view2, Group group, Group group2, TextView textView3, ImageView imageView2, TextView textView4, TextView textView5, View view3) {
        this.rootView = constraintLayout;
        this.bg = view;
        this.chatTyping = imageView;
        this.content = textView;
        this.contentReply = textView2;
        this.devider = view2;
        this.groupChat = group;
        this.groupTyping = group2;
        this.reporter = textView3;
        this.reporterImage = imageView2;
        this.reporterReply = textView4;
        this.time = textView5;
        this.typingBG = view3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LobbyChatItemTextReplyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LobbyChatItemTextReplyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.lobby_chat_item_text_reply, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LobbyChatItemTextReplyBinding bind(View view) {
        int i = R.id.bg;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.bg);
        if (viewFindChildViewById != null) {
            i = R.id.chat_typing;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.chat_typing);
            if (imageView != null) {
                i = R.id.content;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.content);
                if (textView != null) {
                    i = R.id.contentReply;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.contentReply);
                    if (textView2 != null) {
                        i = R.id.devider;
                        View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.devider);
                        if (viewFindChildViewById2 != null) {
                            i = R.id.groupChat;
                            Group group = (Group) ViewBindings.findChildViewById(view, R.id.groupChat);
                            if (group != null) {
                                i = R.id.groupTyping;
                                Group group2 = (Group) ViewBindings.findChildViewById(view, R.id.groupTyping);
                                if (group2 != null) {
                                    i = R.id.reporter;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.reporter);
                                    if (textView3 != null) {
                                        i = R.id.reporterImage;
                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.reporterImage);
                                        if (imageView2 != null) {
                                            i = R.id.reporterReply;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.reporterReply);
                                            if (textView4 != null) {
                                                i = R.id.time;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                                                if (textView5 != null) {
                                                    i = R.id.typingBG;
                                                    View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.typingBG);
                                                    if (viewFindChildViewById3 != null) {
                                                        return new LobbyChatItemTextReplyBinding((ConstraintLayout) view, viewFindChildViewById, imageView, textView, textView2, viewFindChildViewById2, group, group2, textView3, imageView2, textView4, textView5, viewFindChildViewById3);
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
