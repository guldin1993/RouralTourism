package com.example.korisnik.rouraltourism.activity.home_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.korisnik.rouraltourism.R;
import com.example.korisnik.rouraltourism.activity.home_activity.adapter.ListRecyclerAdapter;
import com.example.korisnik.rouraltourism.activity.home_activity.presenter.HomePresenter;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.TouristDestinationSingleActivity;
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;
import com.example.korisnik.rouraltourism.model.data_model.Location;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView, ListRecyclerAdapter.RecyclerListener {
    public static final String EXTRA_TO_TOURIST_DESTINATION_SINGLE = "location";
    public static final String TOOLBAR_TITLE = "Ruralni turizam";

    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(TOOLBAR_TITLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getAllLocations();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopCall();
    }

    @Override
    public void onLocations(List<Location> locationList) {
        if (adapter != null)
            adapter.setLocationData(locationList);
    }

    @Override
    public void onRecyclerClick(Location location) {
        Intent i = new Intent(this, TouristDestinationSingleActivity.class);
        i.putExtra(EXTRA_TO_TOURIST_DESTINATION_SINGLE, location);
        startActivity(i);
    }
}
