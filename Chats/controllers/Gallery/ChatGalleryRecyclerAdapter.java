package com.channel2.mobile.ui.Chats.controllers.Gallery;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.Chats.models.ChatMediaItem;
import com.channel2.mobile.ui.R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ChatGalleryRecyclerAdapter extends RecyclerView.Adapter<ChatGalleryViewHolder> {
    private ArrayList<ChatMediaItem> chatMedias;
    private ChatGalleryHandler handler;

    public ChatGalleryRecyclerAdapter(ArrayList<ChatMediaItem> arrayList, ChatGalleryHandler chatGalleryHandler) {
        this.chatMedias = arrayList;
        this.handler = chatGalleryHandler;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ChatGalleryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_gallery, (ViewGroup) null);
        viewGroup2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        return new ChatGalleryViewHolder(viewGroup2, viewGroup.getContext(), this.handler);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ChatGalleryViewHolder chatGalleryViewHolder, int i) {
        chatGalleryViewHolder.setViewResources(this.chatMedias.get(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.chatMedias.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(ChatGalleryViewHolder chatGalleryViewHolder) {
        super.onViewAttachedToWindow((ChatGalleryRecyclerAdapter) chatGalleryViewHolder);
        this.handler.onAttach(chatGalleryViewHolder.itemView, chatGalleryViewHolder.position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(ChatGalleryViewHolder chatGalleryViewHolder) {
        super.onViewDetachedFromWindow((ChatGalleryRecyclerAdapter) chatGalleryViewHolder);
        this.handler.onDetach(chatGalleryViewHolder.itemView, chatGalleryViewHolder.position);
    }
}
