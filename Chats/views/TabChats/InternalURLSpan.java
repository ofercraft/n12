package com.channel2.mobile.ui.Chats.views.TabChats;

import android.text.style.ClickableSpan;
import android.view.View;

/* loaded from: classes2.dex */
public class InternalURLSpan extends ClickableSpan {
    View.OnClickListener mListener;

    public InternalURLSpan(View.OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        this.mListener.onClick(view);
    }
}
