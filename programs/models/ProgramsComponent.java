package com.channel2.mobile.ui.programs.models;

import android.widget.FrameLayout;
import com.channel2.mobile.ui.liveStreaming.Live;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ProgramsComponent extends Item {
    private String channelVcmId;
    private FrameLayout headerView;
    private String image;
    private ArrayList<ProgramsItem> items;
    private String name;
    private String programCodeDisplay;
    private FrameLayout view;

    ProgramsComponent(JSONObject jSONObject) {
        super(jSONObject);
        setImage(jSONObject.optString("backgroundImage"));
        setProgramCodeDisplay(jSONObject.optString("programCodeDisplay"));
        setChannelVcmId(jSONObject.optString("channelVcmId"));
        setName(jSONObject.optString("name"));
        setItems(jSONObject.optJSONArray(FirebaseAnalytics.Param.ITEMS));
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public ArrayList<ProgramsItem> getItems() {
        return this.items;
    }

    public void setItems(JSONArray jSONArray) {
        ArrayList<ProgramsItem> arrayList = new ArrayList<>();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(new ProgramsItem(jSONArray.optJSONObject(i)));
            }
            if (Live.getLivePrograms() != null && Live.getLivePrograms().get(getProgramCodeDisplay()) != null) {
                ProgramsItem programsItem = Live.getLivePrograms().get(getProgramCodeDisplay());
                ProgramsItem programsItem2 = arrayList.get(0);
                Calendar calendar = Calendar.getInstance();
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis((programsItem2.getPublishedDate() * 1000) - TimeZone.getDefault().getOffset(r5));
                if (!programsItem2.isFullEpisode() || calendar.get(5) != calendar2.get(5)) {
                    arrayList.add(0, programsItem);
                }
            }
        }
        this.items = arrayList;
    }

    public FrameLayout getView() {
        return this.view;
    }

    public void setView(FrameLayout frameLayout) {
        this.view = frameLayout;
    }

    public String getProgramCodeDisplay() {
        return this.programCodeDisplay;
    }

    public void setProgramCodeDisplay(String str) {
        this.programCodeDisplay = str;
    }

    public String getChannelVcmId() {
        return this.channelVcmId;
    }

    public void setChannelVcmId(String str) {
        this.channelVcmId = str;
    }

    public FrameLayout getHeaderView() {
        return this.headerView;
    }

    public void setHeaderView(FrameLayout frameLayout) {
        this.headerView = frameLayout;
    }
}
