package com.example.korisnik.rouraltourism.model.data_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class Images implements Parcelable {

    @SerializedName("0")
    private String imageZero;

    @SerializedName("1")
    private String imageOne;

    @SerializedName("2")
    private String imageTwo;

    @SerializedName("3")
    private String imageThree;

    public String getImageZero() {
        return imageZero;
    }

    public void setImageZero(String imageZero) {
        this.imageZero = imageZero;
    }

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }

    public String getImageThree() {
        return imageThree;
    }

    public void setImageThree(String imageThree) {
        this.imageThree = imageThree;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageZero);
        dest.writeString(this.imageOne);
        dest.writeString(this.imageTwo);
        dest.writeString(this.imageThree);
    }

    public Images() {
    }

    protected Images(Parcel in) {
        this.imageZero = in.readString();
        this.imageOne = in.readString();
        this.imageTwo = in.readString();
        this.imageThree = in.readString();
    }

    public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel source) {
            return new Images(source);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}