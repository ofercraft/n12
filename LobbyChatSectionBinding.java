package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class LobbyChatSectionBinding implements ViewBinding {
    public final ImageView chatBG;
    public final ImageView chatButton;
    public final Group chatHeader;
    public final ImageView chatHeaderBG;
    public final ImageView chatHeaderLogo;
    public final TextView chatHeaderText;
    public final ImageView chatIcon;
    public final RecyclerView chatLobbyRecycler;
    private final ConstraintLayout rootView;

    private LobbyChatSectionBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, Group group, ImageView imageView3, ImageView imageView4, TextView textView, ImageView imageView5, RecyclerView recyclerView) {
        this.rootView = constraintLayout;
        this.chatBG = imageView;
        this.chatButton = imageView2;
        this.chatHeader = group;
        this.chatHeaderBG = imageView3;
        this.chatHeaderLogo = imageView4;
        this.chatHeaderText = textView;
        this.chatIcon = imageView5;
        this.chatLobbyRecycler = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LobbyChatSectionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LobbyChatSectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.lobby_chat_section, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LobbyChatSectionBinding bind(View view) {
        int i = R.id.chatBG;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.chatBG);
        if (imageView != null) {
            i = R.id.chat_button;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.chat_button);
            if (imageView2 != null) {
                i = R.id.chat_header;
                Group group = (Group) ViewBindings.findChildViewById(view, R.id.chat_header);
                if (group != null) {
                    i = R.id.chatHeaderBG;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.chatHeaderBG);
                    if (imageView3 != null) {
                        i = R.id.chatHeaderLogo;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.chatHeaderLogo);
                        if (imageView4 != null) {
                            i = R.id.chatHeaderText;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.chatHeaderText);
                            if (textView != null) {
                                i = R.id.chatIcon;
                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.chatIcon);
                                if (imageView5 != null) {
                                    i = R.id.chat_lobby_recycler;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.chat_lobby_recycler);
                                    if (recyclerView != null) {
                                        return new LobbyChatSectionBinding((ConstraintLayout) view, imageView, imageView2, group, imageView3, imageView4, textView, imageView5, recyclerView);
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
