package com.example.korisnik.rouraltourism.activity.tourist_destination_activity;

import com.example.korisnik.rouraltourism.base.PerActivity;

import dagger.Subcomponent;

/**
 * Created by Korisnik on 4.4.2017..
 */

@PerActivity
@Subcomponent(modules = TouristDestinationModule.class)
public interface TouristDestinationComponent {
    void inject (TouristDestinationSingleActivity touristDestinationSingleActivity);
}
