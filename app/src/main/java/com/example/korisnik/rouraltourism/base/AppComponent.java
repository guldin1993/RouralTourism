package com.example.korisnik.rouraltourism.base;

import com.example.korisnik.rouraltourism.activity.home_activity.HomeComponent;
import com.example.korisnik.rouraltourism.activity.home_activity.HomeModule;

import com.example.korisnik.rouraltourism.activity.share_activity.ShareComponent;
import com.example.korisnik.rouraltourism.activity.share_activity.ShareModule;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.TouristDestinationComponent;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.TouristDestinationModule;
import com.example.korisnik.rouraltourism.utils.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Korisnik on 3.4.2017..
 */

@Singleton
@Component(modules = ServiceModule.class)
public interface AppComponent {
    HomeComponent plus (HomeModule module);
    TouristDestinationComponent plus (TouristDestinationModule module);
    //ImageActivityComponent plus (ImageActivityModule module);
    ShareComponent plus(ShareModule module);
}
