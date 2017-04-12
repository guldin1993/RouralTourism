package com.example.korisnik.rouraltourism.base;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import com.example.korisnik.rouraltourism.utils.ServiceModule;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class RouralTourismApplication extends Application {

    public static final String IMAGE_URL = "http://slavonijaturizam.eu/cms/photo/";


    private AppComponent baseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        baseComponent = DaggerAppComponent.builder()
                .serviceModule(new ServiceModule())
                .build();

        Fresco.initialize(this);
    }

    public static RouralTourismApplication get(Context context) {
        return (RouralTourismApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return baseComponent;
    }
}
