package com.example.korisnik.rouraltourism.activity.tourist_destination_activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.korisnik.rouraltourism.R;

import com.example.korisnik.rouraltourism.activity.home_activity.HomeActivity;
import com.example.korisnik.rouraltourism.activity.image_actvity.ImageActivity;
import com.example.korisnik.rouraltourism.activity.share_activity.ShareActivity;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter.TouristDestinationPresenter;
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;
import com.example.korisnik.rouraltourism.model.data_model.Location;
import com.example.korisnik.rouraltourism.model.data_model.TextInformationsSIngle;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
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

public class TouristDestinationSingleActivity extends AppCompatActivity implements TouristDestinationView, OnMapReadyCallback, LocationListener, View.OnClickListener {

    public static final int LOCATION_GPS_REQUEST = 10;
    public static final int CALL_REQUEST = 11;

    public static final String EXTRA_IMAGE_TO_IMAGE_ACTIVITY = "image";
    public static final String EXTRA_TEXT_TO_IMAGE_ACTIVITY = "title";
    public static final String EXTRA_IMAGE_TO_SHARE_ACTIVITY = "image";
    public static final String EXTRA_TEXT_TO_SHARE_ACTIVITY = "title";

    @BindView(R.id.iv_cover_image)
    SimpleDraweeView ivCoverImage;
    @BindView(R.id.iv_location1)
    SimpleDraweeView ivLocation1;
    @BindView(R.id.iv_location2)
    SimpleDraweeView ivLocation2;
    @BindView(R.id.iv_location3)
    SimpleDraweeView ivLocation3;

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
    @BindView(R.id.ll_find_location_lat_lng)
    LinearLayout llFindLocation;
    @BindView(R.id.ll_share)
    LinearLayout llShare;

    @BindView(R.id.sv_single)
    ScrollView scrollView;

    @BindView(R.id.btn_expand)
    Button expandColapseButton;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    TouristDestinationPresenter presenter;

    private boolean expandFlag = false;

    private Intent findLocationIntent;

    private android.location.Location mLastLocation;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.bind(this);

        RouralTourismApplication.get(this).getAppComponent()
                .plus(new TouristDestinationModule(this))
                .inject(this);
        presenter.initialize((Location) getIntent().getParcelableExtra(HomeActivity.EXTRA_TO_TOURIST_DESTINATION_SINGLE));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        presenter.setTitle();
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.bringToFront();

        ivCoverImage.setOnClickListener(this);
        ivLocation1.setOnClickListener(this);
        ivLocation2.setOnClickListener(this);
        ivLocation3.setOnClickListener(this);
    }

    protected void onStart() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void showAppBarTitle(String title) {
        toolbar.setTitle(title);
    }

    @OnClick(R.id.btn_expand)
    public void onClickExpand() {

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

    @Override
    public void onClick(View v) {
        presenter.setImageId(v.getId());
    }

    @Override
    public void callImageActivity(String image, String title) {
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra(EXTRA_IMAGE_TO_IMAGE_ACTIVITY, image);
        i.putExtra(EXTRA_TEXT_TO_IMAGE_ACTIVITY, title);
        startActivity(i);
    }

    @OnClick(R.id.ll_share)
    public void onShareClick() {
        presenter.setShareData();
    }

    @Override
    public void callShareActivity(String image, String title) {
        Intent i = new Intent(this, ShareActivity.class);
        i.putExtra(EXTRA_IMAGE_TO_SHARE_ACTIVITY, image);
        i.putExtra(EXTRA_TEXT_TO_SHARE_ACTIVITY, title);
        startActivity(i);
    }

    @OnClick(R.id.ll_source_mail)
    public void onMailClick() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", tvSourceMail.getText().toString(), null));
        startActivity(Intent.createChooser(intent, "Choose an Email client :"));
    }

    @OnClick(R.id.ll_source_phone_cell)
    public void onPhonenNumberClick() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + tvSoruceTelephone.getText().toString()));
            startActivity(callIntent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST);
        }
    }

    @OnClick(R.id.ll_web_cell)
    public void onWebAdressClick() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://" + tvSourceMail.getText().toString().replace("-", "")));
        startActivity(i);
    }

    @OnClick(R.id.ll_find_location_lat_lng)
    public void onLocationFindClick() {
        String provider = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (!provider.contains("gps") && !provider.contains("wifi")) {
                //Toast.makeText(context, "You have to enable gps or WIFI to use this attribute.", Toast.LENGTH_LONG).show();
                builder1.setMessage("You have to enable GPS or WIFI to use this attribute.");
                builder1.setCancelable(true);
                builder1.setNeutralButton("WIFI",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent i = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                startActivity(i);
                            }
                        });

                builder1.setNeutralButton("GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent i = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(i);
                            }
                        });

                builder1.setNegativeButton("Cancle",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            } else {
                if (mLastLocation != null) {
                    presenter.setCurrentLocation(mLastLocation.getLatitude(), (mLastLocation.getLongitude()));
                } else
                    Toast.makeText(this, "Error occured, location wasn't found!", Toast.LENGTH_SHORT).show();
                presenter.currentLocation();
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_GPS_REQUEST);
        }
    }

    @Override
    public void callFindLocation(String url) {
        findLocationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        this.startActivity(findLocationIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        String provider = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        switch (requestCode) {
            case LOCATION_GPS_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (!provider.contains("gps")) {
                        Toast.makeText(this, "You have to enable gps to use this attribute.", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(i);
                    } else {
                        if (mLastLocation != null) {
                            presenter.setCurrentLocation(mLastLocation.getLatitude(), (mLastLocation.getLongitude()));
                        } else
                            presenter.setCurrentLocation(45.5462462, 18.5487755);
                        presenter.currentLocation();
                    }
                }
                break;
            }
            case CALL_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + tvSoruceTelephone.getText().toString()));
                    startActivity(callIntent);
                } else {
                    Toast.makeText(this, "You have to enable usage of phone calls to use this attribute.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    public void showTextViews(TextInformationsSIngle informations) {
        tvTitle.setText(informations.getTitle());
        tvContent.setText(Html.fromHtml(informations.getContent()));
        tvAddress.setText(informations.getAddress());
        tvCity.setText(informations.getCity());
        tvSoruceTelephone.setText(informations.getPhone());
        tvSourceMail.setText(informations.getMail());
        tvSourceWeb.setText(informations.getWeb());

        if (TextUtils.isEmpty(informations.getMail())) {
            llSoruceMail.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(informations.getPhone())) {
            llSorucePhone.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(informations.getWeb())) {
            llWeb.setVisibility(View.GONE);
        }
    }

    @Override
    public void showServiceImages(List<Boolean> services) {
        ivIcoCoffe.setImageResource(R.mipmap.ico_kava);
        ivIcoRecreation.setImageResource(R.mipmap.ico_rekreacija);
        ivIcoFood.setImageResource(R.mipmap.ico_hrana);
        ivIcoSleeping.setImageResource(R.mipmap.ico_spavanje);
        if (!services.get(0)) {
            ivIcoSleeping.setVisibility(View.GONE);
        }
        if (!services.get(1)) {
            ivIcoFood.setVisibility(View.GONE);
        }
        if (!services.get(2)) {
            ivIcoCoffe.setVisibility(View.GONE);
        }
        if (!services.get(3)) {
            ivIcoRecreation.setVisibility(View.GONE);
        }
    }

    @Override
    public void showRatings(List<Boolean> ratings) {

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
    public void showLocationImages(List<String> pictures) {
        Uri uri;

        for (int i = 1; i < pictures.size(); i++) {
            if (pictures.get(i).equals("1421327104") || (pictures.get(i).length() <= 4 && pictures.get(i).length() >= 2)) {
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
        presenter.setMapLocation();
    }

    @Override
    public void onMapLocation(Double latLoc, Double lngLoc) {
        LatLng myLocation = new LatLng(latLoc, lngLoc);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions().position(myLocation).title("Location").icon(BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(getResources(), R.mipmap.pin_zeleni))));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(12).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            android.location.Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                presenter.setCurrentLocation(mLastLocation.getLatitude(), (mLastLocation.getLongitude()));
            }
        }
    }
}
