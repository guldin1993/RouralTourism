package com.example.korisnik.rouraltourism.activity.home_activity;

import com.example.korisnik.rouraltourism.activity.home_activity.adapter.ListRecyclerAdapter;
import com.example.korisnik.rouraltourism.base.PerActivity;
import com.example.korisnik.rouraltourism.activity.home_activity.presenter.HomePresenter;
import com.example.korisnik.rouraltourism.activity.home_activity.presenter.HomePresenterImpl;
import com.example.korisnik.rouraltourism.model.interactors.Interactor;
import com.example.korisnik.rouraltourism.model.interactors.InteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Korisnik on 3.4.2017..
 */
@Module
public class HomeModule {
    public final HomeView homeView;

    public HomeModule(HomeView homeView) {
        this.homeView = homeView;
    }

    @PerActivity
    @Provides
    HomePresenter provideHomePresenter(HomePresenterImpl homePresenter){
        return homePresenter;
    }

    @PerActivity
    @Provides
    HomeView homeView(){
        return this.homeView;
    }

    @PerActivity
    @Provides
    Interactor providesInteracotr(InteractorImpl interactor){
        return interactor;
    }

    @PerActivity
    @Provides
    ListRecyclerAdapter providesListRecyclerAdapter(){
        return new ListRecyclerAdapter();
    }
}
