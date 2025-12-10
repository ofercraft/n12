package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.helpers.ObituariesHelper;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.google.android.gms.common.ConnectionResult;
import java.util.Objects;

/* loaded from: classes2.dex */
public class ObituariesTeaserItem extends CustomRecyclerViewHolder {
    int animationDuration;
    Handler handler;
    ImageView imgObituaryItem;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public ObituariesTeaserItem(View view) {
        super(view);
        this.animationDuration = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        this.handler = null;
        Log.i("ObituariesTeaserItem", "ObituariesTeaserItem");
        this.imgObituaryItem = (ImageView) view.findViewById(R.id.imgObituaryItem);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        Log.i("ObituariesTeaserItem", "setViewResources");
        setImageView(false, false);
    }

    public void setTimer() {
        Log.i("ObituariesTeaserItem", "setTimer");
        int animationtime = (ObituariesHelper.getInstance().getAnimationtime() * 1000) + this.animationDuration;
        Handler handler = new Handler(Looper.getMainLooper());
        this.handler = handler;
        handler.postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.lobby.models.teasers.ObituariesTeaserItem.1
            @Override // java.lang.Runnable
            public void run() {
                ObituariesTeaserItem.this.prepareImage();
            }
        }, animationtime);
    }

    public void stopTimer() {
        Log.i("ObituariesTeaserItem", "stopTimer");
        this.handler.removeCallbacksAndMessages(null);
    }

    public void setImageView(boolean z, boolean z2) {
        if (ObituariesHelper.getInstance().getItems().size() > 0) {
            int lastPositionDisplay = ObituariesHelper.getInstance().getLastPositionDisplay() + (z ? 1 : 0);
            if (lastPositionDisplay == ObituariesHelper.getInstance().getItems().size()) {
                lastPositionDisplay = 0;
            }
            Log.i("ObituariesTeaserItem", "setImageView for index : " + lastPositionDisplay);
            Glide.with(this.imgObituaryItem).load(ObituariesHelper.getInstance().getItems().get(lastPositionDisplay).getMobileImgUrl()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.imgObituaryItem.getContext(), R.drawable.obituaries_background))).transition(DrawableTransitionOptions.withCrossFade(this.animationDuration)).into(this.imgObituaryItem);
            ObituariesHelper.getInstance().setLastPositionDisplay(lastPositionDisplay);
            this.lobbyItem.setClickUrl(ObituariesHelper.getInstance().getItems().get(lastPositionDisplay).getLink());
            if (z2) {
                setTimer();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareImage() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(1500L);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.channel2.mobile.ui.lobby.models.teasers.ObituariesTeaserItem.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                ObituariesTeaserItem.this.setImageView(true, true);
                Log.i("ObituariesTeaserItem", "onAnimationEnd");
            }
        });
        this.imgObituaryItem.startAnimation(alphaAnimation);
    }
}
