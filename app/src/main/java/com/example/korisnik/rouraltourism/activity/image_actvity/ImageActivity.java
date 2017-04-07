package com.example.korisnik.rouraltourism.activity.image_actvity;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.example.korisnik.rouraltourism.R;
import com.example.korisnik.rouraltourism.activity.home_activity.adapter.ListRecyclerAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends Activity {

    @BindView(R.id.dv_full_screen)
    SimpleDraweeView dvFullScreenImage;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        uri = Uri.parse(ListRecyclerAdapter.IMAGE_URL + getIntent().getStringExtra("TO_IMAGE_ACTIVITY"));
        //uri = Uri.parse("http://slavonijaturizam.eu/cms/photo/516");
        dvFullScreenImage.setAdjustViewBounds(true);
        dvFullScreenImage.setImageURI(uri);
    }
}
