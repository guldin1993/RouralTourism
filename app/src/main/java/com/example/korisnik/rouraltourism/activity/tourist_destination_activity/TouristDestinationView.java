package com.example.korisnik.rouraltourism.activity.tourist_destination_activity;

import android.net.Uri;

import com.example.korisnik.rouraltourism.model.data_model.ServiceImagesSingle;
import com.example.korisnik.rouraltourism.model.data_model.TextInformationsSIngle;

import java.util.List;

/**
 * Created by Korisnik on 4.4.2017..
 */

public interface TouristDestinationView {
    void showTextViews(TextInformationsSIngle informations);
    void showLocationImages(List<String> pictures);
    void showServiceImages(List<Boolean> images);
    void getRatings(List<Boolean> ratings);
    void callImageActivity(String image, String title);
    void callShareActivity(String image, String title);
    void showAppBarTitle(String title);
    void callFindLocation();
}
