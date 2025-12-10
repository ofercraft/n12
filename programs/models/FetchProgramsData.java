package com.channel2.mobile.ui.programs.models;

import android.content.res.Resources;
import androidx.viewpager.widget.ViewPager;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.liveStreaming.Live;
import com.channel2.mobile.ui.network.ApiService;
import com.channel2.mobile.ui.programs.controllers.BodyPagerAdapter;
import com.channel2.mobile.ui.programs.controllers.HeaderPagerAdapter;
import com.channel2.mobile.ui.programs.controllers.ListAdapter;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.reactivestreams.Publisher;

/* loaded from: classes2.dex */
public class FetchProgramsData {
    private MainActivity mainActivity;
    private String url;

    public interface Handler {
        void onSuccess(JSONObject jSONObject);
    }

    public FetchProgramsData(String str, MainActivity mainActivity, final ViewPager viewPager, final ViewPager viewPager2, final ArrayList<ProgramsComponent> arrayList, final Subject<Integer> subject, final HashMap<Integer, Integer> map, final int i, final String str2, final ListAdapter.ClickHandler clickHandler, final Handler handler) {
        this.url = str;
        this.mainActivity = mainActivity;
        fetchData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((FlowableSubscriber<? super JSONObject>) new DisposableSubscriber<JSONObject>() { // from class: com.channel2.mobile.ui.programs.models.FetchProgramsData.1
            @Override // org.reactivestreams.Subscriber
            public void onComplete() {
            }

            @Override // org.reactivestreams.Subscriber
            public void onError(Throwable th) {
            }

            @Override // org.reactivestreams.Subscriber
            public void onNext(JSONObject jSONObject) throws Resources.NotFoundException {
                int i2;
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("components");
                if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                    for (int i3 = 0; i3 < jSONArrayOptJSONArray.length(); i3++) {
                        arrayList.add(new ProgramsComponent(jSONArrayOptJSONArray.optJSONObject(i3)));
                    }
                }
                Collections.reverse(arrayList);
                int size = arrayList.size() - 1;
                if (str2.length() > 0) {
                    i2 = 0;
                    while (i2 < arrayList.size()) {
                        if (((ProgramsComponent) arrayList.get(i2)).getProgramCodeDisplay().equals(str2)) {
                            size = i2;
                            break;
                        }
                        i2++;
                    }
                } else if (Live.getCurrentProgram() != null && Live.getCurrentProgram().length() > 0) {
                    i2 = 0;
                    while (i2 < arrayList.size()) {
                        if (((ProgramsComponent) arrayList.get(i2)).getProgramCodeDisplay().equals(Live.getCurrentProgram())) {
                            size = i2;
                            break;
                        }
                        i2++;
                    }
                }
                int i4 = 500;
                for (int i5 = 0; i5 < arrayList.size(); i5++) {
                    i4 = i5 + 500;
                    if (i4 % arrayList.size() == size) {
                        break;
                    }
                }
                viewPager.setAdapter(new BodyPagerAdapter(arrayList, subject, map, i, clickHandler));
                viewPager.setCurrentItem(i4);
                viewPager2.setAdapter(new HeaderPagerAdapter(arrayList));
                viewPager2.setCurrentItem(i4);
                handler.onSuccess(jSONObject);
            }
        });
    }

    private Flowable<JSONObject> fetchData() {
        return Flowable.defer(new Callable<Publisher<? extends JSONObject>>() { // from class: com.channel2.mobile.ui.programs.models.FetchProgramsData.2
            @Override // java.util.concurrent.Callable
            public Publisher<? extends JSONObject> call() throws Exception {
                return Flowable.just(ApiService.getRouteData(FetchProgramsData.this.url, FetchProgramsData.this.mainActivity.getApplicationContext()));
            }
        });
    }
}
