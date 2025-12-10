package com.channel2.mobile.ui.programs.controllers;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.programs.controllers.ListAdapter;
import com.channel2.mobile.ui.programs.models.FetchProgramItems;
import com.channel2.mobile.ui.programs.models.ProgramsComponent;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class BodyPagerAdapter extends ViewPagerAdapter {
    private ListAdapter adapter;
    private ListAdapter.ClickHandler handler;
    private ArrayList<ProgramsComponent> items;
    private int numberOfRequests;
    private int offsetPx;
    private Subject<Integer> selectedItem;
    private HashMap<Integer, Integer> selectedItemMap;
    private boolean isFirst = true;
    private boolean canRequestedMoreItems = true;
    private Subject<Boolean> observable = PublishSubject.create();

    public interface PaddingHandler {
        void onViewHeight(int i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 1000;
    }

    public BodyPagerAdapter(ArrayList<ProgramsComponent> arrayList, Subject<Integer> subject, HashMap<Integer, Integer> map, int i, ListAdapter.ClickHandler clickHandler) {
        this.items = arrayList;
        this.offsetPx = i;
        this.selectedItem = subject;
        this.selectedItemMap = map;
        this.handler = clickHandler;
    }

    @Override // com.channel2.mobile.ui.programs.controllers.ViewPagerAdapter
    public View getView(int i, ViewPager viewPager) {
        this.canRequestedMoreItems = true;
        this.numberOfRequests = 0;
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(viewPager.getContext()).inflate(R.layout.programs_body_view, (ViewGroup) null);
        final int size = i % this.items.size();
        this.items.get(size).setView(frameLayout);
        final ViewPager2 viewPager2 = (ViewPager2) frameLayout.findViewById(R.id.view_pager);
        viewPager2.setPageTransformer(new RegularPageTransformer(this.offsetPx));
        viewPager2.setOrientation(1);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2);
        ListAdapter listAdapter = new ListAdapter(this.items.get(size).getItems(), this.handler);
        this.adapter = listAdapter;
        viewPager2.setAdapter(listAdapter);
        this.observable.map(new Function<Boolean, Object>() { // from class: com.channel2.mobile.ui.programs.controllers.BodyPagerAdapter.1
            @Override // io.reactivex.functions.Function
            public Object apply(Boolean bool) {
                BodyPagerAdapter.this.canRequestedMoreItems = bool.booleanValue();
                return bool;
            }
        }).subscribe();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.channel2.mobile.ui.programs.controllers.BodyPagerAdapter.2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int i2, float f, int i3) {
                super.onPageScrolled(i2, f, i3);
                Log.v("onPageScrolled", "position - " + i2);
                Log.v("onPageScrolled", "positionOffset - " + f);
                Log.v("onPageScrolled", "positionOffsetPixels - " + i3);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(final int i2) {
                super.onPageSelected(i2);
                BodyPagerAdapter.this.selectedItemMap.put(Integer.valueOf(size), Integer.valueOf(i2));
                if (((ProgramsComponent) BodyPagerAdapter.this.items.get(size)).getChannelVcmId() != null && ((ProgramsComponent) BodyPagerAdapter.this.items.get(size)).getChannelVcmId().length() > 5 && i2 > ((ProgramsComponent) BodyPagerAdapter.this.items.get(size)).getItems().size() - 3 && BodyPagerAdapter.this.canRequestedMoreItems) {
                    BodyPagerAdapter.this.observable.onNext(false);
                    String strReplace = MainConfig.main.getCurrentSource("channelDataApi").replace("%CHANNEL_ID%", ((ProgramsComponent) BodyPagerAdapter.this.items.get(size)).getChannelVcmId() + "_" + BodyPagerAdapter.this.numberOfRequests);
                    BodyPagerAdapter bodyPagerAdapter = BodyPagerAdapter.this;
                    bodyPagerAdapter.numberOfRequests = bodyPagerAdapter.numberOfRequests + 1;
                    new FetchProgramItems(strReplace, viewPager2.getContext(), ((ProgramsComponent) BodyPagerAdapter.this.items.get(size)).getItems(), BodyPagerAdapter.this.adapter, BodyPagerAdapter.this.observable);
                }
                if (BodyPagerAdapter.this.isFirst) {
                    BodyPagerAdapter.this.isFirst = false;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.channel2.mobile.ui.programs.controllers.BodyPagerAdapter.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BodyPagerAdapter.this.selectedItem.onNext(Integer.valueOf(i2));
                        }
                    }, 800L);
                }
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i2) {
                super.onPageScrollStateChanged(i2);
                if (i2 == 0) {
                    BodyPagerAdapter.this.selectedItem.onNext(Integer.valueOf(i2));
                }
            }
        });
        return frameLayout;
    }
}
