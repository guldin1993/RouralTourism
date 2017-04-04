package com.example.korisnik.rouraltourism.model.data_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class Location implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("subtype")
    private String subtype;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("translations")
    private Translations translations;

    @SerializedName("slike")
    private Images images;

    @SerializedName("has_child")
    private int hasChild;

    private float ratings;

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public int getHasChild() {
        return hasChild;
    }

    public void setHasChild(int hasChild) {
        this.hasChild = hasChild;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeParcelable(this.meta, flags);
        dest.writeString(this.subtype);
        dest.writeParcelable(this.translations, flags);
        dest.writeParcelable(this.images, flags);
        dest.writeInt(this.hasChild);
        dest.writeFloat(this.ratings);
    }

    public Location() {
    }

    protected Location(Parcel in) {
        this.id = in.readString();
        this.type = in.readString();
        this.meta = in.readParcelable(Meta.class.getClassLoader());
        this.subtype = in.readString();
        this.translations = in.readParcelable(Translations.class.getClassLoader());
        this.images = in.readParcelable(Images.class.getClassLoader());
        this.hasChild = in.readInt();
        this.ratings = in.readFloat();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
