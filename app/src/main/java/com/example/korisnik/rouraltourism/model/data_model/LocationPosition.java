package com.example.korisnik.rouraltourism.model.data_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class LocationPosition implements Parcelable {

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
    }

    public LocationPosition() {
    }

    protected LocationPosition(Parcel in) {
        this.lat = in.readDouble();
        this.lng = in.readDouble();
    }

    public static final Parcelable.Creator<LocationPosition> CREATOR = new Parcelable.Creator<LocationPosition>() {
        @Override
        public LocationPosition createFromParcel(Parcel source) {
            return new LocationPosition(source);
        }

        @Override
        public LocationPosition[] newArray(int size) {
            return new LocationPosition[size];
        }
    };
}
