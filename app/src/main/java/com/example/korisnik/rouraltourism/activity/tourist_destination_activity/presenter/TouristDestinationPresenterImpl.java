package com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter;

import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.TouristDestinationView;
import com.example.korisnik.rouraltourism.model.data_model.Location;

import javax.inject.Inject;

/**
 * Created by Korisnik on 4.4.2017..
 */

public class TouristDestinationPresenterImpl implements TouristDestinationPresenter{

    private TouristDestinationView touristDestinationView;

    @Inject
    public TouristDestinationPresenterImpl(TouristDestinationView touristDestinationView) {
        this.touristDestinationView = touristDestinationView;
    }

    @Override
    public void getLocation(Location location) {

    }
}
