package com.example.korisnik.rouraltourism.activity.home_activity.presenter;

import com.example.korisnik.rouraltourism.activity.home_activity.HomeView;
import com.example.korisnik.rouraltourism.model.data_model.DataContainer;
import com.example.korisnik.rouraltourism.model.data_model.Location;
import com.example.korisnik.rouraltourism.model.interactors.Interactor;
import com.example.korisnik.rouraltourism.model.interactors.listener.Listener;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class HomePresenterImpl implements HomePresenter, Listener {

    private Interactor interactor;
    private HomeView homeView;

    @Inject
    public HomePresenterImpl(Interactor interactor, HomeView homeView) {
        this.interactor = interactor;
        this.homeView = homeView;
    }

    @Override
    public void onDataSuccess(DataContainer dataContainer) {

        homeView.onLocations(dataContainer.getLocationList());
    }

    @Override
    public void allLocations() {
        interactor.getZippedData(this);
    }

    @Override
    public void stopCall() {
        if(interactor != null)
            interactor.stop();
    }
}
