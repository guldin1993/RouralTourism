package com.example.korisnik.rouraltourism.utils;

import com.example.korisnik.rouraltourism.model.data_model.Location;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Korisnik on 3.4.2017..
 */

public interface RestInterface {

    String LOCATIONS = "api_v2/locations";
    String RATINGS = "api_v2/get-ratings";

    @GET(LOCATIONS)
    Observable<List<Location>> readLocations();

    @GET(RATINGS)
    Observable<Map<String, Float>> readRatigns();
}
