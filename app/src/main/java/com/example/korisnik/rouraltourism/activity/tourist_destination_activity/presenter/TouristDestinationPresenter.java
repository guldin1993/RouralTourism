package com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.LinearLayout;

import com.example.korisnik.rouraltourism.model.data_model.Location;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Korisnik on 4.4.2017..
 */

public interface TouristDestinationPresenter {
    void initialize(Location location);
    void setTitle();
    String getLocationUri();
    Double getLocatinLat();
    Double getLocatinLng();
    void setCurrentLocation(Double lat, Double lng);
    void setImageId(int id);
    void setShareData();
    void currentLocation();
}