package com.example.korisnik.rouraltourism.activity.image_actvity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Xml;
import android.widget.RelativeLayout;

import com.example.korisnik.rouraltourism.R;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.TouristDestinationSingleActivity;
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;

import com.facebook.drawee.view.SimpleDraweeView;

import org.xmlpull.v1.XmlPullParser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends AppCompatActivity {

    @BindView(R.id.dv_full_screen)
    SimpleDraweeView dvFullScreenImage;

    ZoomableDraweeView zoomableDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        Uri uri = Uri.parse(RouralTourismApplication.IMAGE_URL + getIntent().getStringExtra(TouristDestinationSingleActivity.EXTRA_IMAGE_TO_IMAGE_ACTIVITY));
        dvFullScreenImage.setAdjustViewBounds(true);
        dvFullScreenImage.setImageURI(uri);
        setTitle(getIntent().getStringExtra(TouristDestinationSingleActivity.EXTRA_TEXT_TO_IMAGE_ACTIVITY));
        XmlPullParser parser = getResources().getLayout(R.layout.activity_image);
        AttributeSet attribute = Xml.asAttributeSet(parser);
        zoomableDraweeView = new ZoomableDraweeView(this, attribute, 2);
    }
}