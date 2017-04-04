package com.example.korisnik.rouraltourism.model.data_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class Translations implements Parcelable {

    @SerializedName("1")
    private TitleContentData translationOne;

    @SerializedName("2")
    private TitleContentData translationTwo;

    @SerializedName("3")
    private TitleContentData translationThree;

    @SerializedName("4")
    private TitleContentData translationFour;

    public TitleContentData getTranslationOne() {
        return translationOne;
    }

    public void setTranslationOne(TitleContentData translationOne) {
        this.translationOne = translationOne;
    }

    public TitleContentData getTranslationTwo() {
        return translationTwo;
    }

    public void setTranslationTwo(TitleContentData translationTwo) {
        this.translationTwo = translationTwo;
    }

    public TitleContentData getTranslationThree() {
        return translationThree;
    }

    public void setTranslationThree(TitleContentData translationThree) {
        this.translationThree = translationThree;
    }

    public TitleContentData getTranslationFour() {
        return translationFour;
    }

    public void setTranslationFour(TitleContentData translationFour) {
        this.translationFour = translationFour;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.translationOne, flags);
        dest.writeParcelable(this.translationTwo, flags);
        dest.writeParcelable(this.translationFour, flags);
        dest.writeParcelable(this.translationThree, flags);
    }

    public Translations() {
    }

    protected Translations(Parcel in) {
        this.translationOne = in.readParcelable(TitleContentData.class.getClassLoader());
        this.translationTwo = in.readParcelable(TitleContentData.class.getClassLoader());
        this.translationFour = in.readParcelable(TitleContentData.class.getClassLoader());
        this.translationThree = in.readParcelable(TitleContentData.class.getClassLoader());
    }

    public static final Parcelable.Creator<Translations> CREATOR = new Parcelable.Creator<Translations>() {
        @Override
        public Translations createFromParcel(Parcel source) {
            return new Translations(source);
        }

        @Override
        public Translations[] newArray(int size) {
            return new Translations[size];
        }
    };
}
