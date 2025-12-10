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
import androidx.viewpager2.widget.ViewPager2;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class FragmentChatGalerryBinding implements ViewBinding {
    public final ChatGalleryVideoControlsBinding chatGalleryVideoControls;
    public final TextView date;
    public final ImageView exit;
    public final TextView footer;
    public final Group header;
    public final View headerBG;
    public final TextView number;
    private final ConstraintLayout rootView;
    public final TextView title;
    public final ViewPager2 viewPager;

    private FragmentChatGalerryBinding(ConstraintLayout constraintLayout, ChatGalleryVideoControlsBinding chatGalleryVideoControlsBinding, TextView textView, ImageView imageView, TextView textView2, Group group, View view, TextView textView3, TextView textView4, ViewPager2 viewPager2) {
        this.rootView = constraintLayout;
        this.chatGalleryVideoControls = chatGalleryVideoControlsBinding;
        this.date = textView;
        this.exit = imageView;
        this.footer = textView2;
        this.header = group;
        this.headerBG = view;
        this.number = textView3;
        this.title = textView4;
        this.viewPager = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChatGalerryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChatGalerryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_chat_galerry, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentChatGalerryBinding bind(View view) {
        int i = R.id.chat_gallery_video_controls;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.chat_gallery_video_controls);
        if (viewFindChildViewById != null) {
            ChatGalleryVideoControlsBinding chatGalleryVideoControlsBindingBind = ChatGalleryVideoControlsBinding.bind(viewFindChildViewById);
            i = R.id.date;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.date);
            if (textView != null) {
                i = R.id.exit;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.exit);
                if (imageView != null) {
                    i = R.id.footer;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.footer);
                    if (textView2 != null) {
                        i = R.id.header;
                        Group group = (Group) ViewBindings.findChildViewById(view, R.id.header);
                        if (group != null) {
                            i = R.id.headerBG;
                            View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.headerBG);
                            if (viewFindChildViewById2 != null) {
                                i = R.id.number;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.number);
                                if (textView3 != null) {
                                    i = R.id.title;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                    if (textView4 != null) {
                                        i = R.id.viewPager;
                                        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewPager);
                                        if (viewPager2 != null) {
                                            return new FragmentChatGalerryBinding((ConstraintLayout) view, chatGalleryVideoControlsBindingBind, textView, imageView, textView2, group, viewFindChildViewById2, textView3, textView4, viewPager2);
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
