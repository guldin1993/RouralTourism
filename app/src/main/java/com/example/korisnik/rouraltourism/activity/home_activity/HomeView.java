package com.example.korisnik.rouraltourism.activity.home_activity;

import com.example.korisnik.rouraltourism.model.data_model.Location;

import java.util.List;

/**
 * Created by Korisnik on 3.4.2017..
 */

public interface HomeView {
    void onLocations(List<Location> locationList);
}
