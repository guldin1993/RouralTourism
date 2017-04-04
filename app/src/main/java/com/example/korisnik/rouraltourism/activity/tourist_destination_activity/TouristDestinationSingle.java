package com.example.korisnik.rouraltourism.activity.tourist_destination_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.korisnik.rouraltourism.R;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter.TouristDestinationPresenter;
import com.example.korisnik.rouraltourism.model.data_model.Location;

import javax.inject.Inject;

public class TouristDestinationSingle extends AppCompatActivity implements TouristDestinationView{

    @Inject
    TouristDestinationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        Location location = (Location) getIntent().getParcelableExtra("TO_TOURIST_DESTINATION_SINGLE");
        presenter.getLocation(location);
    }

}
