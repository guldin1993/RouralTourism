package com.example.korisnik.rouraltourism.activity.image_activity;

import com.example.korisnik.rouraltourism.base.PerActivity;

import dagger.Subcomponent;

/**
 * Created by Korisnik on 5.4.2017..
 */
@PerActivity
@Subcomponent(modules = ImageActivityModule.class)
public interface ImageActivityComponent {
    void inject(ImageActivity activity);
}
