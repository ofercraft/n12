package com.channel2.mobile.ui.webView.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.webView.models.GalleryItem;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {
    private ChatGalleryHandler handler;
    private ArrayList<GalleryItem> items;

    public GalleryAdapter(ArrayList<GalleryItem> arrayList, Context context, ChatGalleryHandler chatGalleryHandler) {
        this.items = arrayList;
        this.handler = chatGalleryHandler;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public GalleryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.galley_item, (ViewGroup) null);
        viewGroup2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        return new GalleryViewHolder(viewGroup2, viewGroup.getContext());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(GalleryViewHolder galleryViewHolder, int i) {
        galleryViewHolder.setViewResources(this.items.get(i).getPortraitImageUrl(), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(GalleryViewHolder galleryViewHolder) {
        super.onViewAttachedToWindow((GalleryAdapter) galleryViewHolder);
        this.handler.onAttach(galleryViewHolder.itemView, galleryViewHolder.position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(GalleryViewHolder galleryViewHolder) {
        super.onViewDetachedFromWindow((GalleryAdapter) galleryViewHolder);
        this.handler.onDetach(galleryViewHolder.itemView, galleryViewHolder.position);
    }
}
