package com.example.korisnik.rouraltourism.activity.home_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.korisnik.rouraltourism.R;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.TouristDestinationSingle;
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;
import com.example.korisnik.rouraltourism.activity.home_activity.presenter.HomePresenter;
import com.example.korisnik.rouraltourism.model.data_model.Location;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView, RecyclerListener{
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;

    @Inject
    HomePresenter presenter;

    @Inject
    ListRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        RouralTourismApplication.get(this).getAppComponent()
                .plus(new HomeModule(this))
                .inject(this);
        presenter.allLocations();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        presenter.stopCall();
    }

    @Override
    public void onLocations(List<Location> locationList) {
        if(adapter != null)
            adapter.setLocationData(locationList);
    }

    @Override
    public void onRecyclerClick(Location location) {
        Intent i = new Intent(this, TouristDestinationSingle.class);
        i.putExtra("TO_TOURIST_DESTINATION_SINGLE", location);
        startActivity(i);
    }
}
