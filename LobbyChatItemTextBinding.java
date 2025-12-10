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
public final class LobbyChatItemTextBinding implements ViewBinding {
    public final View bg;
    public final ImageView chatTyping;
    public final TextView content;
    public final Group groupChat;
    public final Group groupTyping;
    public final TextView reporter;
    public final ImageView reporterImage;
    private final ConstraintLayout rootView;
    public final TextView time;
    public final View typingBG;

    private LobbyChatItemTextBinding(ConstraintLayout constraintLayout, View view, ImageView imageView, TextView textView, Group group, Group group2, TextView textView2, ImageView imageView2, TextView textView3, View view2) {
        this.rootView = constraintLayout;
        this.bg = view;
        this.chatTyping = imageView;
        this.content = textView;
        this.groupChat = group;
        this.groupTyping = group2;
        this.reporter = textView2;
        this.reporterImage = imageView2;
        this.time = textView3;
        this.typingBG = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LobbyChatItemTextBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LobbyChatItemTextBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.lobby_chat_item_text, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LobbyChatItemTextBinding bind(View view) {
        int i = R.id.bg;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.bg);
        if (viewFindChildViewById != null) {
            i = R.id.chat_typing;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.chat_typing);
            if (imageView != null) {
                i = R.id.content;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.content);
                if (textView != null) {
                    i = R.id.groupChat;
                    Group group = (Group) ViewBindings.findChildViewById(view, R.id.groupChat);
                    if (group != null) {
                        i = R.id.groupTyping;
                        Group group2 = (Group) ViewBindings.findChildViewById(view, R.id.groupTyping);
                        if (group2 != null) {
                            i = R.id.reporter;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.reporter);
                            if (textView2 != null) {
                                i = R.id.reporterImage;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.reporterImage);
                                if (imageView2 != null) {
                                    i = R.id.time;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                                    if (textView3 != null) {
                                        i = R.id.typingBG;
                                        View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.typingBG);
                                        if (viewFindChildViewById2 != null) {
                                            return new LobbyChatItemTextBinding((ConstraintLayout) view, viewFindChildViewById, imageView, textView, group, group2, textView2, imageView2, textView3, viewFindChildViewById2);
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
