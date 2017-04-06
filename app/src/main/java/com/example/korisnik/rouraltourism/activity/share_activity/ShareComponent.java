package com.example.korisnik.rouraltourism.activity.share_activity;

import com.example.korisnik.rouraltourism.base.PerActivity;

import dagger.Subcomponent;

/**
 * Created by Korisnik on 6.4.2017..
 */

@PerActivity
@Subcomponent(modules = ShareModule.class)
public interface ShareComponent {
    void inject(ShareActivity shareActivity);
}
