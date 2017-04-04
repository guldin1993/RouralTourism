package com.example.korisnik.rouraltourism.model.data_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class Meta implements Parcelable {

    @SerializedName("basic")
    private BasicLocationData basic;

    @SerializedName("loc")
    private LocationPosition loc;

    @SerializedName("services")
    private List<String> services;

    public BasicLocationData getBasic() {
        return basic;
    }

    public void setBasic(BasicLocationData basic) {
        this.basic = basic;
    }

    public LocationPosition getLoc() {
        return loc;
    }

    public void setLoc(LocationPosition loc) {
        this.loc = loc;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.basic, flags);
        dest.writeParcelable(this.loc, flags);
        dest.writeStringList(this.services);
    }

    public Meta() {
    }

    protected Meta(Parcel in) {
        this.basic = in.readParcelable(BasicLocationData.class.getClassLoader());
        this.loc = in.readParcelable(LocationPosition.class.getClassLoader());
        this.services = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Meta> CREATOR = new Parcelable.Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel source) {
            return new Meta(source);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };
}
