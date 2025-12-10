package com.channel2.mobile.ui.customViews;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TutorialDialog extends Dialog implements View.OnClickListener {
    private JSONObject tutorialOBJ;

    @Override // android.app.Dialog
    public void onBackPressed() {
    }

    public TutorialDialog(Context context, JSONObject jSONObject) {
        super(context);
        this.tutorialOBJ = jSONObject;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            setContentView(R.layout.tutorial);
            ((Window) Objects.requireNonNull(getWindow())).clearFlags(1024);
            ImageView imageView = (ImageView) findViewById(R.id.tutorial);
            ((ImageView) findViewById(R.id.exit)).setOnClickListener(this);
            Glide.with(imageView).load(this.tutorialOBJ.optString("image")).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
    }
}
