package com.example.korisnik.rouraltourism.activity.home_activity;

import com.example.korisnik.rouraltourism.base.PerActivity;

import dagger.Subcomponent;

/**
 * Created by Korisnik on 3.4.2017..
 */

@PerActivity
@Subcomponent(modules = HomeModule.class)
public interface HomeComponent {
    void inject (HomeActivity homeActivity);
}
