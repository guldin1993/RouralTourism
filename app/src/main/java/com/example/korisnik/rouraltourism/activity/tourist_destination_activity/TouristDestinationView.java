package com.example.korisnik.rouraltourism.activity.tourist_destination_activity;

import java.util.List;

/**
 * Created by Korisnik on 4.4.2017..
 */

public interface TouristDestinationView {
    void getSingleTextViews(List<String> strings);
    void getPictures(List<String> pictures);
    void getIcoImages(List<Boolean> icoList);
    void getRatings(List<Boolean> ratings);
}
