package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class FragmentChatVhBinding implements ViewBinding {
    public final FrameLayout chatFragmentsContainer;
    public final ImageView chatTabBG;
    public final ConstraintLayout errorDialog;
    public final TextView errorText;
    public final TextView headerSectionText;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final ImageButton scrollDown;
    public final Button tryAgainButton;
    public final TextView unreadMessagesText;

    private FragmentChatVhBinding(ConstraintLayout constraintLayout, FrameLayout frameLayout, ImageView imageView, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, RecyclerView recyclerView, ImageButton imageButton, Button button, TextView textView3) {
        this.rootView = constraintLayout;
        this.chatFragmentsContainer = frameLayout;
        this.chatTabBG = imageView;
        this.errorDialog = constraintLayout2;
        this.errorText = textView;
        this.headerSectionText = textView2;
        this.recyclerView = recyclerView;
        this.scrollDown = imageButton;
        this.tryAgainButton = button;
        this.unreadMessagesText = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChatVhBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChatVhBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_chat_vh, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentChatVhBinding bind(View view) {
        int i = R.id.chat_fragments_container;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.chat_fragments_container);
        if (frameLayout != null) {
            i = R.id.chatTabBG;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.chatTabBG);
            if (imageView != null) {
                i = R.id.errorDialog;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.errorDialog);
                if (constraintLayout != null) {
                    i = R.id.errorText;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.errorText);
                    if (textView != null) {
                        i = R.id.headerSectionText;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.headerSectionText);
                        if (textView2 != null) {
                            i = R.id.recyclerView;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recyclerView);
                            if (recyclerView != null) {
                                i = R.id.scrollDown;
                                ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, R.id.scrollDown);
                                if (imageButton != null) {
                                    i = R.id.tryAgainButton;
                                    Button button = (Button) ViewBindings.findChildViewById(view, R.id.tryAgainButton);
                                    if (button != null) {
                                        i = R.id.unreadMessagesText;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.unreadMessagesText);
                                        if (textView3 != null) {
                                            return new FragmentChatVhBinding((ConstraintLayout) view, frameLayout, imageView, constraintLayout, textView, textView2, recyclerView, imageButton, button, textView3);
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
