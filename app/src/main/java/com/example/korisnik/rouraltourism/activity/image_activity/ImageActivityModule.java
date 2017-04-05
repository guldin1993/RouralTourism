package com.example.korisnik.rouraltourism.activity.image_activity;

import com.example.korisnik.rouraltourism.activity.image_activity.presenter.ImagePresenter;
import com.example.korisnik.rouraltourism.activity.image_activity.presenter.ImagePresenterImpl;
import com.example.korisnik.rouraltourism.base.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Korisnik on 5.4.2017..
 */

@Module
public class ImageActivityModule {

    private final ImageActivityView view;

    public ImageActivityModule(ImageActivityView view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    ImageActivityView providesImageActivityView(ImageActivityView imageActivityView){
        return this.view;
    }

    @PerActivity
    @Provides
    ImagePresenter providesImagePresenter(ImagePresenterImpl imagePresenter){
        return imagePresenter;
    }
}
