package com.example.korisnik.rouraltourism.model.data_model;

import java.util.List;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class DataContainer {
    private List<Ratings> ratingsList;
    private List<Location> locationList;

    public DataContainer(List<Ratings> ratingsList, List<Location> locationList) {
        this.ratingsList = ratingsList;
        this.locationList = locationList;
    }

    public DataContainer(List<Location> locationList) {
        this.locationList = locationList;
    }

    public DataContainer() {
    }

    public List<Ratings> getRatingsList() {
        return ratingsList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }
}
