package com.channel2.mobile.ui.Chats.models;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.channel2.mobile.ui.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public class ChatTutorialDialog extends Dialog implements View.OnClickListener {
    @Override // android.app.Dialog
    public void onBackPressed() {
    }

    public ChatTutorialDialog(Context context) {
        super(context);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.chat_tutorial);
        ((Window) Objects.requireNonNull(getWindow())).clearFlags(1024);
        findViewById(R.id.agree).setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
    }
}
