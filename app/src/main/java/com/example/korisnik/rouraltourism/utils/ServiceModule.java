package com.example.korisnik.rouraltourism.utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Korisnik on 3.4.2017..
 */

@Module
public class ServiceModule {

    private static final String BASE_URL = "http://slavonijaturizam.eu/cms/";

    @Singleton
    @Provides
    public RestInterface getService(Retrofit retrofit){
        return retrofit.create(RestInterface.class);
    }

    @Singleton
    @Provides
    public Retrofit retro(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}
