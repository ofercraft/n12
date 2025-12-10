package com.channel2.mobile.ui.lobby.models.sections;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragment;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.ItemType;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserVideoItem;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class SectionHorizontalPaging extends CustomRecyclerViewHolder {
    public static LinearLayoutManager linearLayoutManager;
    public CustomViewPagerAdapter adapter;
    public boolean isPlaying;
    public RecyclerView.OnItemTouchListener itemTouchListener;
    public RecyclerView mRecyclerView;
    private int positionForPlay;
    public RecyclerView.OnScrollListener scrollListener;
    public PagerSnapHelper snapHelper;
    String swipeDirection;
    private TeaserVideoItem teaserVideoItemForPlayVideo;
    private TeaserVideoItem teaserVideoItemForStopVideo;
    private TextView title;

    public SectionHorizontalPaging(View view) {
        super(view);
        this.isPlaying = false;
        this.swipeDirection = "";
        this.teaserVideoItemForPlayVideo = null;
        this.title = (TextView) view.findViewById(R.id.title);
        this.mRecyclerView = null;
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        lobbyFragmentHandler.enableVerticleScroll(false);
    }

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
        try {
            if (this.mRecyclerView == null || ((LobbySection) this.lobbyItem).getHorizontalItems().get(0).getLobbyItemType() != ItemType.videoItem || this.isPlaying) {
                return;
            }
            int iFindFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            this.positionForPlay = iFindFirstCompletelyVisibleItemPosition;
            TeaserVideoItem teaserVideoItem = (TeaserVideoItem) this.mRecyclerView.findViewHolderForLayoutPosition(iFindFirstCompletelyVisibleItemPosition);
            this.teaserVideoItemForPlayVideo = teaserVideoItem;
            this.isPlaying = true;
            if (teaserVideoItem != null) {
                Log.d("checkVideoPly", "onScrollStateIdle: ");
                this.handler.onPlayVideo(this.teaserVideoItemForPlayVideo.getLobbyTeaser(), this.teaserVideoItemForPlayVideo.videoContainer, this.teaserVideoItemForPlayVideo.image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initViewPager() {
        LobbySection lobbySection = (LobbySection) this.lobbyItem;
        this.title.setText(lobbySection.getName());
        setFontSize(this.title, 25.0f);
        if (lobbySection.getTitleColor().length() > 0) {
            this.title.setTextColor(Color.parseColor(lobbySection.getTitleColor()));
        }
        ArrayList<Item> horizontalItems = lobbySection.getHorizontalItems();
        if (horizontalItems.size() > 0) {
            this.adapter = null;
            linearLayoutManager = null;
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this.mRecyclerView.getContext(), 0, true);
            linearLayoutManager = linearLayoutManager2;
            linearLayoutManager2.setStackFromEnd(true);
            this.mRecyclerView.setLayoutManager(linearLayoutManager);
            this.mRecyclerView.setItemViewCacheSize(20);
            this.mRecyclerView.setRecycledViewPool(LobbyFragment.getViewPool());
            this.mRecyclerView.setOnFlingListener(null);
            CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(this.mRecyclerView.getContext(), horizontalItems, this.handler);
            this.adapter = customViewPagerAdapter;
            this.mRecyclerView.setAdapter(customViewPagerAdapter);
            this.mRecyclerView.setHasFixedSize(true);
            this.mRecyclerView.requestDisallowInterceptTouchEvent(true);
            if (horizontalItems.get(0).getLobbyItemType() == ItemType.videoItem) {
                this.mRecyclerView.removeOnScrollListener(this.scrollListener);
                this.scrollListener = null;
                this.scrollListener = new RecyclerView.OnScrollListener() { // from class: com.channel2.mobile.ui.lobby.models.sections.SectionHorizontalPaging.1
                    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                        super.onScrollStateChanged(recyclerView, i);
                        if (i == 0) {
                            SectionHorizontalPaging.this.positionForPlay = SectionHorizontalPaging.linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                            if (SectionHorizontalPaging.this.positionForPlay <= -1 || !(SectionHorizontalPaging.this.mRecyclerView.findViewHolderForLayoutPosition(SectionHorizontalPaging.this.positionForPlay) instanceof TeaserVideoItem)) {
                                return;
                            }
                            SectionHorizontalPaging sectionHorizontalPaging = SectionHorizontalPaging.this;
                            sectionHorizontalPaging.teaserVideoItemForPlayVideo = (TeaserVideoItem) sectionHorizontalPaging.mRecyclerView.findViewHolderForLayoutPosition(SectionHorizontalPaging.this.positionForPlay);
                            if (SectionHorizontalPaging.this.teaserVideoItemForPlayVideo != null) {
                                SectionHorizontalPaging.this.isPlaying = true;
                                Log.d("checkVideoPly", "onScrollStateChanged: ");
                                SectionHorizontalPaging.this.handler.onPlayVideo(SectionHorizontalPaging.this.teaserVideoItemForPlayVideo.getLobbyTeaser(), SectionHorizontalPaging.this.teaserVideoItemForPlayVideo.videoContainer, SectionHorizontalPaging.this.teaserVideoItemForPlayVideo.image);
                                if (SectionHorizontalPaging.this.swipeDirection.length() > 0) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("Action", SectionHorizontalPaging.this.swipeDirection);
                                    FirebaseAnalytics.getInstance(MainConfig.appData.getMainActivity()).logEvent("News_Video", bundle);
                                    Log.d("swipeDirection", SectionHorizontalPaging.this.swipeDirection);
                                }
                            }
                        }
                    }

                    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                        super.onScrolled(recyclerView, i, i2);
                        if (i > 0) {
                            SectionHorizontalPaging.this.swipeDirection = "Swipe_Right";
                        } else if (i < 0) {
                            SectionHorizontalPaging.this.swipeDirection = "Swipe_Left";
                        } else {
                            SectionHorizontalPaging.this.swipeDirection = "";
                        }
                    }
                };
                this.mRecyclerView.removeOnItemTouchListener(this.itemTouchListener);
                this.itemTouchListener = null;
                this.itemTouchListener = new RecyclerView.OnItemTouchListener() { // from class: com.channel2.mobile.ui.lobby.models.sections.SectionHorizontalPaging.2
                    private double startX;

                    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                    }

                    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 0) {
                            this.startX = motionEvent.getAxisValue(0);
                            if (SectionHorizontalPaging.this.teaserVideoItemForStopVideo != SectionHorizontalPaging.this.teaserVideoItemForPlayVideo) {
                                SectionHorizontalPaging sectionHorizontalPaging = SectionHorizontalPaging.this;
                                sectionHorizontalPaging.teaserVideoItemForStopVideo = sectionHorizontalPaging.teaserVideoItemForPlayVideo;
                            }
                            if (SectionHorizontalPaging.this.teaserVideoItemForPlayVideo != null && SectionHorizontalPaging.this.teaserVideoItemForPlayVideo.image.getVisibility() != 0) {
                                SectionHorizontalPaging.this.teaserVideoItemForPlayVideo.image.setVisibility(0);
                            }
                        }
                        if (motionEvent.getAction() == 1) {
                            SectionHorizontalPaging.this.isPlaying = false;
                            double axisValue = this.startX - motionEvent.getAxisValue(0);
                            if (axisValue > -15.0d && axisValue < 15.0d) {
                                try {
                                    if (SectionHorizontalPaging.this.teaserVideoItemForPlayVideo != null) {
                                        SectionHorizontalPaging.this.teaserVideoItemForPlayVideo.lobbyItem.setMako_ref_comp("video_galery");
                                        SectionHorizontalPaging.this.handler.onClick(SectionHorizontalPaging.this.teaserVideoItemForPlayVideo.lobbyItem, null);
                                    }
                                } catch (Exception unused) {
                                }
                            } else if (SectionHorizontalPaging.this.teaserVideoItemForStopVideo != null) {
                                SectionHorizontalPaging.this.handler.onPauseVideo(SectionHorizontalPaging.this.teaserVideoItemForStopVideo.getLobbyTeaser(), SectionHorizontalPaging.this.teaserVideoItemForStopVideo.videoContainer, SectionHorizontalPaging.this.teaserVideoItemForStopVideo.image);
                                SectionHorizontalPaging.this.teaserVideoItemForStopVideo = null;
                            }
                        }
                        return false;
                    }

                    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                    public void onRequestDisallowInterceptTouchEvent(boolean z) {
                        Log.d("disallowIntercept", String.valueOf(z));
                    }
                };
                this.mRecyclerView.addOnScrollListener(this.scrollListener);
                this.mRecyclerView.addOnItemTouchListener(this.itemTouchListener);
                this.snapHelper = new PagerSnapHelper();
            } else {
                this.mRecyclerView.setNestedScrollingEnabled(false);
                this.mRecyclerView.removeOnScrollListener(this.scrollListener);
                this.mRecyclerView.removeOnItemTouchListener(this.itemTouchListener);
                this.itemTouchListener = null;
                RecyclerView.OnItemTouchListener onItemTouchListener = new RecyclerView.OnItemTouchListener() { // from class: com.channel2.mobile.ui.lobby.models.sections.SectionHorizontalPaging.3
                    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 3) {
                            motionEvent.setAction(1);
                        }
                        Log.d("SectionHorizontalPaging", "dont miss " + motionEvent);
                        return false;
                    }

                    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                        Log.d("SectionHorizontalPaging", "onTouchEvent " + motionEvent);
                    }

                    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                    public void onRequestDisallowInterceptTouchEvent(boolean z) {
                        Log.d("SectionHorizontalPaging", "disAllowed");
                    }
                };
                this.itemTouchListener = onItemTouchListener;
                this.mRecyclerView.addOnItemTouchListener(onItemTouchListener);
                this.snapHelper = new CustomSnapHelper();
            }
            this.snapHelper.attachToRecyclerView(this.mRecyclerView);
        }
    }

    public int getPositionForPlay() {
        return this.positionForPlay;
    }
}
