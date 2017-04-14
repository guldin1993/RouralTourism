package com.example.korisnik.rouraltourism.activity.image_actvity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.korisnik.rouraltourism.R;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.TouristDestinationSingleActivity;
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends AppCompatActivity {

    @BindView(R.id.dv_full_screen)
    ZoomableDraweeView dvFullScreenImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        Uri uri = Uri.parse(RouralTourismApplication.IMAGE_URL + getIntent().getStringExtra(TouristDestinationSingleActivity.EXTRA_IMAGE_TO_IMAGE_ACTIVITY));
        dvFullScreenImage.setAdjustViewBounds(true);
        dvFullScreenImage.setImageURI(uri);
        setTitle(getIntent().getStringExtra(TouristDestinationSingleActivity.EXTRA_TEXT_TO_IMAGE_ACTIVITY));
    }
}