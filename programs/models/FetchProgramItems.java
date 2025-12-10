package com.channel2.mobile.ui.programs.models;

import android.content.Context;
import com.channel2.mobile.ui.network.ApiService;
import com.channel2.mobile.ui.programs.controllers.ListAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.reactivestreams.Publisher;

/* loaded from: classes2.dex */
public class FetchProgramItems {
    private Context context;
    private String url;

    public FetchProgramItems(String str, Context context, final ArrayList<ProgramsItem> arrayList, final ListAdapter listAdapter, final Subject<Boolean> subject) {
        this.url = str;
        this.context = context;
        fetchData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((FlowableSubscriber<? super JSONObject>) new DisposableSubscriber<JSONObject>() { // from class: com.channel2.mobile.ui.programs.models.FetchProgramItems.1
            @Override // org.reactivestreams.Subscriber
            public void onComplete() {
            }

            @Override // org.reactivestreams.Subscriber
            public void onError(Throwable th) {
            }

            @Override // org.reactivestreams.Subscriber
            public void onNext(JSONObject jSONObject) {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("components");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                    return;
                }
                JSONArray jSONArrayOptJSONArray2 = jSONArrayOptJSONArray.optJSONObject(0).optJSONArray(FirebaseAnalytics.Param.ITEMS);
                for (int i = 0; i < jSONArrayOptJSONArray2.length(); i++) {
                    arrayList.add(new ProgramsItem(jSONArrayOptJSONArray2.optJSONObject(i)));
                }
                subject.onNext(true);
                listAdapter.notifyItemRangeChanged(0, arrayList.size());
            }
        });
    }

    private Flowable<JSONObject> fetchData() {
        return Flowable.defer(new Callable<Publisher<? extends JSONObject>>() { // from class: com.channel2.mobile.ui.programs.models.FetchProgramItems.2
            @Override // java.util.concurrent.Callable
            public Publisher<? extends JSONObject> call() throws Exception {
                return Flowable.just(ApiService.getRouteData(FetchProgramItems.this.url, FetchProgramItems.this.context));
            }
        });
    }
}
