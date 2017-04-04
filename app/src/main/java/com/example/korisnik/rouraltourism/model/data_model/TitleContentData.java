package com.example.korisnik.rouraltourism.model.data_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class TitleContentData implements Parcelable {
    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    public TitleContentData() {
    }

    protected TitleContentData(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<TitleContentData> CREATOR = new Parcelable.Creator<TitleContentData>() {
        @Override
        public TitleContentData createFromParcel(Parcel source) {
            return new TitleContentData(source);
        }

        @Override
        public TitleContentData[] newArray(int size) {
            return new TitleContentData[size];
        }
    };
}
