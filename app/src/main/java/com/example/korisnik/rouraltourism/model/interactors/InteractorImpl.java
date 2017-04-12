package com.example.korisnik.rouraltourism.model.interactors;

import android.util.Log;

import com.example.korisnik.rouraltourism.base.BaseInteractorImpl;
import com.example.korisnik.rouraltourism.model.data_model.DataContainer;
import com.example.korisnik.rouraltourism.model.data_model.Location;
import com.example.korisnik.rouraltourism.model.data_model.Ratings;
import com.example.korisnik.rouraltourism.model.interactors.listener.Listener;
import com.example.korisnik.rouraltourism.utils.RestInterface;
import com.example.korisnik.rouraltourism.utils.ServiceModule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class InteractorImpl extends BaseInteractorImpl implements Interactor {

    @Inject
    RestInterface restInterface;

    @Inject
    public InteractorImpl() {
    }

    public void getRatedLocations(final Listener listener) {
        addSubscription(Observable.zip(getLocation().defaultIfEmpty(new ArrayList<Location>()), getRatigns(), new Func2<List<Location>, List<Ratings>, DataContainer>() {
            @Override
            public DataContainer call(List<Location> locations, List<Ratings> ratings) {
                List<Location> locationRatedList = new ArrayList<Location>();

                for (Location location : locations) {
                    if (location.getSubtype().contains("10")) {
                        for (int i = 0; i < ratings.size(); i++) {
                            if (location.getId().equals(ratings.get(i).getId())) {
                                location.setRatings(ratings.get(i).getRating());
                            }
                        }
                        locationRatedList.add(location);
                    }
                }
                return new DataContainer(locationRatedList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataContainer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DataContainer dataContainer) {
                        listener.onDataSuccess(dataContainer);
                    }
                }));
    }

    private Observable<List<Location>> getLocation() {
        return restInterface.readLocations();
    }

    private Observable<List<Ratings>> getRatigns() {
        return restInterface.readRatigns().flatMap(new Func1<Map<String, Float>, Observable<List<Ratings>>>() {
            @Override
            public Observable<List<Ratings>> call(Map<String, Float> stringFloatMap) {
                List<String> keyList = new ArrayList<String>(stringFloatMap.keySet());
                List<Ratings> ratingResponseList = new ArrayList<Ratings>();
                for (int i = 0; i < stringFloatMap.size(); i++) {
                    Ratings rating = new Ratings(keyList.get(i), stringFloatMap.get(keyList.get(i)));
                    ratingResponseList.add(rating);
                }
                return Observable.just(ratingResponseList);
            }
        }).defaultIfEmpty(new ArrayList<Ratings>());
    }

    @Override
    public void stop() {
        unsubscribe();
    }
}
