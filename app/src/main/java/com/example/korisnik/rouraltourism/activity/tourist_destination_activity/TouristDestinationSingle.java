package com.example.korisnik.rouraltourism.activity.tourist_destination_activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.korisnik.rouraltourism.R;

import com.example.korisnik.rouraltourism.activity.image_actvity.ImageActivity;
import com.example.korisnik.rouraltourism.activity.share_activity.ShareActivity;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter.TouristDestinationPresenter;
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;
import com.example.korisnik.rouraltourism.model.data_model.Location;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TouristDestinationSingle extends AppCompatActivity implements TouristDestinationView, OnMapReadyCallback {

    @BindView(R.id.iv_cover_image)
    SimpleDraweeView ivCoverImage;
    @BindView(R.id.iv_location1)
    SimpleDraweeView ivLocation1;
    @BindView(R.id.iv_location2)
    SimpleDraweeView ivLocation2;
    @BindView(R.id.iv_location3)
    SimpleDraweeView ivLocation3;
    /* @BindView(R.id.iv_location4)
     SimpleDraweeView ivLocation4;*/
    @BindView(R.id.iv_ico_sleeping)
    ImageView ivIcoSleeping;
    @BindView(R.id.iv_ico_coffe)
    ImageView ivIcoCoffe;
    @BindView(R.id.iv_ico_food)
    ImageView ivIcoFood;
    @BindView(R.id.iv_ico_recreation)
    ImageView ivIcoRecreation;

    @BindView(R.id.iv_star_one)
    ImageView ivStarOne;
    @BindView(R.id.iv_star_two)
    ImageView ivStarTwo;
    @BindView(R.id.iv_star_three)
    ImageView ivStarThree;
    @BindView(R.id.iv_star_four)
    ImageView ivStarFour;
    @BindView(R.id.iv_star_five)
    ImageView ivStarFive;

    @BindView(R.id.tv_title_cell)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_address_cell)
    TextView tvAddress;
    @BindView(R.id.tv_city_cell)
    TextView tvCity;
    @BindView(R.id.tv_source_mail_cell)
    TextView tvSourceMail;
    @BindView(R.id.tv_source_web_cell)
    TextView tvSourceWeb;
    @BindView(R.id.tv_source_telephone_cell)
    TextView tvSoruceTelephone;

    @BindView(R.id.ll_source_mail)
    LinearLayout llSoruceMail;
    @BindView(R.id.ll_source_phone_cell)
    LinearLayout llSorucePhone;
    @BindView(R.id.ll_web_cell)
    LinearLayout llWeb;
    @BindView(R.id.ll_ico_image)
    LinearLayout llIcoImage;
    @BindView(R.id.ll_cell)
    LinearLayout linearLayoutStars;
    @BindView(R.id.rl_fragment_container)
    RelativeLayout relativeLayout;
    /*@BindView(R.id.map)
    SupportMapFragment mapFragment;*/
    @BindView(R.id.ll_find_location_lat_lng)
    LinearLayout llFindLocation;

    @BindView(R.id.sv_single)
    ScrollView scrollView;

    @BindView(R.id.ll_share)
    LinearLayout llShare;

    @BindView(R.id.btn_expand)
    Button expandColapseButton;

    @Inject
    TouristDestinationPresenter presenter;

    GoogleMap mMap;

    Boolean expandFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.bind(this);

        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        p.addRule(RelativeLayout.ALIGN_PARENT_TOP);


        RouralTourismApplication.get(this).getAppComponent()
                .plus(new TouristDestinationModule(this))
                .inject(this);
        presenter.initialize((Location) getIntent().getParcelableExtra("TO_TOURIST_DESTINATION_SINGLE"));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(presenter.shareTitle());
    }

    private void callMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @OnClick(R.id.btn_expand)
    public void onClickExpand() {
        /*relativeLayout.animate().scaleX(Resources.getSystem().getDisplayMetrics().widthPixels);
        relativeLayout.animate().scaleY(Resources.getSystem().getDisplayMetrics().heightPixels);
        *//*Expande expande = new Expande(relativeLayout.findViewById(rl_fragment_container), Resources.getSystem().getDisplayMetrics().widthPixels, TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 240,
                this.getResources().getDisplayMetrics() ) , Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels );*//*
        llShare.setVisibility(View.INVISIBLE);
        llFindLocation.setVisibility(View.INVISIBLE);
        relativeLayout.bringToFront();
        callMap();*/

        if (!expandFlag) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels);
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getView().setLayoutParams(layoutParams);
            llShare.setVisibility(View.GONE);
            llFindLocation.setVisibility(View.GONE);
            expandFlag = true;
            expandColapseButton.setBackgroundResource(R.mipmap.zatvori);
        } else {
            llShare.setVisibility(View.VISIBLE);
            llFindLocation.setVisibility(View.VISIBLE);
            Resources r = getResources();
            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 290, r.getDisplayMetrics());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels, Math.round(px));
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getView().setLayoutParams(layoutParams);
            expandFlag = false;
            expandColapseButton.setBackgroundResource(R.mipmap.otvori);
        }

    }

    @OnClick(R.id.iv_cover_image)
    public void onCoverPictureClick() {
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setCoverImage());
        i.putExtra("TO_IMAGE_ACTIVITY_TITLE", presenter.shareTitle());
        startActivity(i);
    }

    @OnClick(R.id.iv_location1)
    public void onLocation1PictureClick() {
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocaiton1Image());
        i.putExtra("TO_IMAGE_ACTIVITY_TITLE", presenter.shareTitle());
        startActivity(i);
    }

    @OnClick(R.id.iv_location2)
    public void onLocation2PictureClick() {
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocation2Image());
        i.putExtra("TO_IMAGE_ACTIVITY_TITLE", presenter.shareTitle());
        startActivity(i);
    }

    @OnClick(R.id.iv_location3)
    public void onLocation3PictureClick() {
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocation3Image());
        i.putExtra("TO_IMAGE_ACTIVITY_TITLE", presenter.shareTitle());
        startActivity(i);
    }

   /* @OnClick(R.id.iv_location4)
    public void onLocation4PictureClick() {
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocation4Image());
        i.putExtra("TO_IMAGE_ACTIVITY_TITLE", presenter.shareTitle());
        startActivity(i);
    }*/

    @OnClick(R.id.ll_share)
    public void onShareClick() {
        Intent i = new Intent(this, ShareActivity.class);
        i.putExtra("TO_SHARE_ACTIVITY_IMAGE", presenter.setCoverImage());
        i.putExtra("TO_IMAGE_ACTIVITY_TITLE", presenter.shareTitle());
        startActivity(i);
    }

    @OnClick(R.id.ll_find_location_lat_lng)
    public void onLocationFindClick() {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(presenter.setLocationUri()));
        //try to send camera positon if you find it
        this.startActivity(i);
    }

    @Override
    public void getSingleTextViews(List<String> strings) {
        tvTitle.setText(strings.get(0));
        tvContent.setText(Html.fromHtml(strings.get(1)));
        tvAddress.setText(strings.get(2));
        tvCity.setText(strings.get(3));
        tvSoruceTelephone.setText(strings.get(4));
        tvSourceMail.setText(strings.get(5));
        tvSourceWeb.setText(strings.get(6));

        if (strings.get(5).isEmpty() || strings.get(5).equals("")) {
            llSoruceMail.setVisibility(View.GONE);
        }
        if (strings.get(4).isEmpty() || strings.get(4).equals("")) {
            llSorucePhone.setVisibility(View.GONE);
        }
        if (strings.get(6).isEmpty() || strings.get(6).equals("")) {
            llWeb.setVisibility(View.GONE);
        }
    }

    @Override
    public void getIcoImages(List<Boolean> flagList) {
        ivIcoCoffe.setImageResource(R.mipmap.ico_kava);
        ivIcoRecreation.setImageResource(R.mipmap.ico_rekreacija);
        ivIcoFood.setImageResource(R.mipmap.ico_hrana);
        ivIcoSleeping.setImageResource(R.mipmap.ico_spavanje);
        if (!flagList.get(0)) {
            ivIcoSleeping.setVisibility(View.GONE);
        }
        if (!flagList.get(1)) {
            ivIcoFood.setVisibility(View.GONE);
        }
        if (!flagList.get(2)) {
            ivIcoCoffe.setVisibility(View.GONE);
        }
        if (!flagList.get(3)) {
            ivIcoRecreation.setVisibility(View.GONE);
        }
    }

    @Override
    public void getRatings(List<Boolean> ratings) {

        if (ratings.get(1)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_white_big);
        } else if (ratings.get(2)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarTwo.setImageResource(R.mipmap.subcategory_star_white_big);

        } else if (ratings.get(3)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarTwo.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarThree.setImageResource(R.mipmap.subcategory_star_white_big);
        } else if (ratings.get(4)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarTwo.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarThree.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarFour.setImageResource(R.mipmap.subcategory_star_white_big);
        } else if (ratings.get(5)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarTwo.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarThree.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarFour.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarFive.setImageResource(R.mipmap.subcategory_star_white_big);
        } else if (ratings.get(0)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarTwo.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarThree.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarFour.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarFive.setImageResource(R.mipmap.subcategory_star_brawn);
        }
        linearLayoutStars.bringToFront();
    }

    @Override
    public void getPictures(List<String> pictures) {
        Uri uri;
        for (int i = 1; i < pictures.size(); i++) {
            if (pictures.get(i).length() <= 4 && pictures.get(i).length() >= 2 || pictures.get(i) == "1421327104") {
                uri = Uri.parse(pictures.get(0) + pictures.get(i));

                if (i == 1) {
                    ivCoverImage.setImageURI(uri);
                }
                if (i == 2)
                    ivLocation1.setImageURI(uri);
                if (i == 3)
                    ivLocation2.setImageURI(uri);
                if (i == 4)
                    ivLocation3.setImageURI(uri);

                /*if (i == 1 && (pictures.get(i).isEmpty() || pictures.get(i) == null || pictures.get(i).equals("")))*/

                if (i == 2 && (pictures.get(i).isEmpty() || pictures.get(i) == null || pictures.get(i).equals("")))
                    ivLocation1.setVisibility(View.GONE);

                if (i == 3 && (pictures.get(i).isEmpty() || pictures.get(i) == null || pictures.get(i).equals("")))
                    ivLocation2.setVisibility(View.GONE);

                if (i == 4 && (pictures.get(i).isEmpty() || pictures.get(i) == null || pictures.get(i).equals("")))
                    ivLocation3.setVisibility(View.GONE);
            } else {

                if (i == 2)
                    ivLocation1.setVisibility(View.GONE);

                if (i == 3)
                    ivLocation2.setVisibility(View.GONE);

                if (i == 4)
                    ivLocation3.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng myLocation = new LatLng(presenter.setLocatinLat(), presenter.setLocatinLng());
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions().position(myLocation).title("Location").icon(BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(getResources(), R.mipmap.pin_zeleni))));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(12).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
