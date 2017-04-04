package com.example.korisnik.rouraltourism.model.data_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class BasicLocationData implements Parcelable {

    @SerializedName("contact")
    private String contact;

    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private String city;

    @SerializedName("phone")
    private String phoneLocation;

    @SerializedName("mail")
    private String mailLocation;

    @SerializedName("web")
    private String webLocation;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneLocation() {
        return phoneLocation;
    }

    public void setPhoneLocation(String phoneLocation) {
        this.phoneLocation = phoneLocation;
    }

    public String getMailLocation() {
        return mailLocation;
    }

    public void setMailLocation(String mailLocation) {
        this.mailLocation = mailLocation;
    }

    public String getWebLocation() {
        return webLocation;
    }

    public void setWebLocation(String webLocation) {
        this.webLocation = webLocation;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.contact);
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeString(this.phoneLocation);
        dest.writeString(this.mailLocation);
        dest.writeString(this.webLocation);
    }

    public BasicLocationData() {
    }

    protected BasicLocationData(Parcel in) {
        this.contact = in.readString();
        this.address = in.readString();
        this.city = in.readString();
        this.phoneLocation = in.readString();
        this.mailLocation = in.readString();
        this.webLocation = in.readString();
    }

    public static final Parcelable.Creator<BasicLocationData> CREATOR = new Parcelable.Creator<BasicLocationData>() {
        @Override
        public BasicLocationData createFromParcel(Parcel source) {
            return new BasicLocationData(source);
        }

        @Override
        public BasicLocationData[] newArray(int size) {
            return new BasicLocationData[size];
        }
    };
}
