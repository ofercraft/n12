package com.channel2.mobile.ui.Chats.controllers.Gallery;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.Chats.models.ChatMediaItem;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.TouchImageView;

/* loaded from: classes2.dex */
public class ChatGalleryViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    ChatGalleryHandler handler;
    TouchImageView image;
    int position;
    View videoClick;
    FrameLayout videoContainer;

    public ChatGalleryViewHolder(View view, Context context, ChatGalleryHandler chatGalleryHandler) {
        super(view);
        this.handler = null;
        this.context = context;
        this.videoContainer = (FrameLayout) view.findViewById(R.id.playerContainer);
        this.videoClick = view.findViewById(R.id.videoClick);
        this.image = (TouchImageView) view.findViewById(R.id.image);
        this.handler = chatGalleryHandler;
        view.setTag(R.string.chat_media, this);
    }

    public void setViewResources(ChatMediaItem chatMediaItem, int i) {
        String link1;
        this.position = i;
        if (chatMediaItem.getMediaTypeId() == 2) {
            this.videoContainer.setVisibility(0);
            this.image.setVisibility(8);
            this.videoClick.setVisibility(0);
            this.videoClick.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setViewResources$0(view);
                }
            });
            return;
        }
        this.videoContainer.setVisibility(8);
        this.videoClick.setVisibility(8);
        this.image.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryViewHolder$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setViewResources$1(view);
            }
        });
        this.image.setOnTouchListener(new View.OnTouchListener() { // from class: com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryViewHolder$$ExternalSyntheticLambda2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$setViewResources$2(view, motionEvent);
            }
        });
        if (chatMediaItem.getLink3() != null && chatMediaItem.getLink3().length() > 0) {
            link1 = chatMediaItem.getLink3();
        } else if (chatMediaItem.getLink2() != null && chatMediaItem.getLink2().length() > 0) {
            link1 = chatMediaItem.getLink2();
        } else {
            link1 = chatMediaItem.getLink1();
        }
        Glide.with(this.image).load(link1).into(this.image);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setViewResources$0(View view) {
        this.handler.onItemClick(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setViewResources$1(View view) {
        this.handler.onItemClick(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setViewResources$2(View view, MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        Log.d("scale", "pointercount " + pointerCount);
        if (pointerCount <= 1 && !this.image.isZoomed()) {
            return false;
        }
        this.itemView.getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }
}
