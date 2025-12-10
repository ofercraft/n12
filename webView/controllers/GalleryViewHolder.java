package com.channel2.mobile.ui.webView.controllers;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.TouchImageView;

/* loaded from: classes2.dex */
public class GalleryViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    TouchImageView image;
    int position;

    public GalleryViewHolder(View view, Context context) {
        super(view);
        this.context = context;
        this.image = (TouchImageView) view.findViewById(R.id.image);
    }

    public void setViewResources(String str, int i) {
        this.position = i;
        Glide.with(this.image).load(str).into(this.image);
    }
}
