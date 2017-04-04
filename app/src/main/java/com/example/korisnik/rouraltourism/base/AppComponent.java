package com.example.korisnik.rouraltourism.base;

import com.example.korisnik.rouraltourism.activity.home_activity.HomeComponent;
import com.example.korisnik.rouraltourism.activity.home_activity.HomeModule;
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
}
