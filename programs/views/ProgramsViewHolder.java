package com.channel2.mobile.ui.programs.views;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.programs.models.ProgramsItem;
import com.squareup.picasso.Picasso;
import java.util.Objects;

/* loaded from: classes2.dex */
public class ProgramsViewHolder extends RecyclerView.ViewHolder {
    public FrameLayout container;
    private CustomTextView date;
    public ImageView imageView;
    public FrameLayout overlay;
    private CustomTextView title;
    private FrameLayout videoContainer;
    private CustomTextView videoFlach;

    public ProgramsViewHolder(View view) {
        super(view);
        this.imageView = (ImageView) view.findViewById(R.id.image);
        this.title = (CustomTextView) view.findViewById(R.id.title);
        this.date = (CustomTextView) view.findViewById(R.id.date);
        this.overlay = (FrameLayout) view.findViewById(R.id.overlay);
        this.container = (FrameLayout) view.findViewById(R.id.container);
        this.videoContainer = (FrameLayout) view.findViewById(R.id.videoContainer);
        this.videoFlach = (CustomTextView) this.itemView.findViewById(R.id.videoFlach);
    }

    public void initial(ProgramsItem programsItem) {
        programsItem.setViewHolder(this);
        if (programsItem.getVideoFlachText() != null && programsItem.getVideoFlachText().length() > 0) {
            CustomTextView customTextView = this.videoFlach;
            String videoFlachText = programsItem.getVideoFlachText();
            Objects.requireNonNull(this.title);
            customTextView.setHebText(videoFlachText, "fonts/YonitMedium_v2.ttf");
            setFontSize(this.videoFlach, 17.0f);
            if (programsItem.getVideoFlachTextColor().length() > 0) {
                this.videoFlach.setTextColor(Color.parseColor(programsItem.getVideoFlachTextColor()));
                this.videoFlach.setBackgroundColor(Color.parseColor(programsItem.getVideoFlachBackgroundColor()));
            } else {
                this.videoFlach.setTextColor(Color.parseColor("#242A32"));
                this.videoFlach.setBackgroundColor(Color.parseColor("#979797"));
            }
            this.videoFlach.setVisibility(0);
        } else {
            this.videoFlach.setVisibility(8);
        }
        CustomTextView customTextView2 = this.title;
        String title = programsItem.getTitle();
        Objects.requireNonNull(this.title);
        customTextView2.setHebText(title, "fonts/YonitMedium_v2.ttf");
        setFontSize(this.title, 18.0f);
        CustomTextView customTextView3 = this.date;
        String date = programsItem.getDate();
        Objects.requireNonNull(this.title);
        customTextView3.setHebText(date, "fonts/YonitMedium_v2.ttf");
        setFontSize(this.date, 14.0f);
        if (programsItem.getImage().length() > 0) {
            Picasso.get().load(programsItem.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.imageView.getContext(), R.drawable.placeholder_ir))).into(this.imageView);
        }
        this.imageView.setImageAlpha(255);
    }

    protected void setFontSize(TextView textView, float f) {
        textView.setTextSize(2, f * Utils.getFloatFromPreferences(textView.getContext(), "zoom").floatValue());
    }
}
