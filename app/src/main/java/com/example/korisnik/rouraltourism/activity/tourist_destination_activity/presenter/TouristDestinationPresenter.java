package com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter;

import android.widget.LinearLayout;

import com.example.korisnik.rouraltourism.model.data_model.Location;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.model.CameraPosition;

import java.util.List;

/**
 * Created by Korisnik on 4.4.2017..
 */

public interface TouristDestinationPresenter {
    void initialize(Location location);
    String setCoverImage();
    String shareTitle();
    String setLocation();
    CameraPosition setCameraPoistion();
    String setLocaiton1Image();
    String setLocation2Image();
    String setLocation3Image();
    String setLocation4Image();
}
