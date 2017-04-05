package com.example.korisnik.rouraltourism.activity.image_activity.presenter;

import com.example.korisnik.rouraltourism.activity.image_activity.ImageActivityView;

import javax.inject.Inject;

/**
 * Created by Korisnik on 5.4.2017..
 */

public class ImagePresenterImpl implements ImagePresenter{

    ImageActivityView view;

    @Inject
    public ImagePresenterImpl(ImageActivityView view) {
        this.view = view;
    }
}
