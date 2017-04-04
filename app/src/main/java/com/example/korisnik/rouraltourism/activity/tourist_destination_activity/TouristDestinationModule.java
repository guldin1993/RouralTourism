package com.example.korisnik.rouraltourism.activity.tourist_destination_activity;

import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter.TouristDestinationPresenter;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter.TouristDestinationPresenterImpl;
import com.example.korisnik.rouraltourism.base.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Korisnik on 4.4.2017..
 */

@Module
public class TouristDestinationModule {
    private final TouristDestinationView touristDestinationView;

    public TouristDestinationModule(TouristDestinationView touristDestinationView) {
        this.touristDestinationView = touristDestinationView;
    }

    @PerActivity
    @Provides
    TouristDestinationView touristDestinationView(){
    return this.touristDestinationView;
    }

    @PerActivity
    @Provides
    TouristDestinationPresenter providesTouristDestinationPresenter(TouristDestinationPresenterImpl touristDestinationPresenter){
        return touristDestinationPresenter;
    }
}
