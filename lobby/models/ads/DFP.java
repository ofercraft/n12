package com.channel2.mobile.ui.lobby.models.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.mako.kscore.ksmeasurements.model.item.ErrorItem$$ExternalSyntheticBackport0;
import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class DFP implements Serializable, Parcelable {
    public static final Parcelable.Creator<DFP> CREATOR = new Parcelable.Creator<DFP>() { // from class: com.channel2.mobile.ui.lobby.models.ads.DFP.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DFP createFromParcel(Parcel parcel) {
            return new DFP(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DFP[] newArray(int i) {
            return new DFP[i];
        }
    };
    JSONObject custParams;
    String iu;
    String makoPage;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DFP(String str, JSONObject jSONObject, String str2) {
        this.iu = str;
        this.custParams = jSONObject;
        this.makoPage = str2;
    }

    public DFP(String str, JSONObject jSONObject) {
        this.iu = str;
        this.custParams = jSONObject;
        this.makoPage = "";
    }

    public DFP(String str, String str2) {
        this.iu = str;
        this.custParams = new JSONObject();
        this.makoPage = str2;
    }

    public DFP(String str) {
        this.iu = str;
        this.custParams = new JSONObject();
        this.makoPage = "";
    }

    public DFP() {
        this.iu = "";
        this.custParams = new JSONObject();
        this.makoPage = "";
    }

    protected DFP(Parcel parcel) {
        this.iu = parcel.readString();
        this.makoPage = parcel.readString();
        try {
            String string = parcel.readString();
            if (string == null || ErrorItem$$ExternalSyntheticBackport0.m(string)) {
                return;
            }
            this.custParams = new JSONObject(string);
        } catch (Exception unused) {
        }
    }

    public String getIu() {
        return this.iu;
    }

    public void setIu(String str) {
        this.iu = str;
    }

    public JSONObject getCustParams() {
        return this.custParams;
    }

    public void setCustParams(JSONObject jSONObject) {
        this.custParams = jSONObject;
    }

    public String getMakoPage() {
        return this.makoPage;
    }

    public void setMakoPage(String str) {
        this.makoPage = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.iu);
        parcel.writeString(this.makoPage);
        parcel.writeString(this.custParams.toString());
    }
}
