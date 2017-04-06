package com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter;

import android.text.BoringLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.example.korisnik.rouraltourism.activity.home_activity.adapter.ListRecyclerAdapter;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.TouristDestinationView;
import com.example.korisnik.rouraltourism.model.data_model.BasicLocationData;
import com.example.korisnik.rouraltourism.model.data_model.Location;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by Korisnik on 4.4.2017..
 */

public class TouristDestinationPresenterImpl implements TouristDestinationPresenter {

    private TouristDestinationView touristDestinationView;
    private List<String> imageList = new ArrayList<>();
    private float ratings = 0f;
    private Location location;
    private String uri;

    @Inject
    public TouristDestinationPresenterImpl(TouristDestinationView touristDestinationView) {
        this.touristDestinationView = touristDestinationView;
    }

    @Override
    public String setCoverImage(){
        return location.getId();
    }

    @Override
    public String shareTitle() {
        return location.getTranslations().getTranslationOne().getTitle();
    }

    @Override
    public String setLocation() {

        return uri = String.format(Locale.ENGLISH, "geo:%f,%f", location.getMeta().getLoc().getLat(), location.getMeta().getLoc().getLng());
    }

    @Override
    public CameraPosition setCameraPoistion() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getMeta().getLoc().getLat(),  location.getMeta().getLoc().getLng()))
                .zoom(11)
                .build();
        return cameraPosition;
    }

    @Override
    public String setLocaiton1Image(){
        return imageList.get(0);
    }

    @Override
    public String setLocation2Image() {
        return imageList.get(1);
    }

    @Override
    public String setLocation3Image() {
        return imageList.get(2);
    }

    @Override
    public String setLocation4Image() {
        return imageList.get(3);
    }

    @Override
    public void initialize(Location location) {
        this.location = location;
        BasicLocationData basicData = this.location.getMeta().getBasic();

        String title = this.location.getTranslations().getTranslationOne().getTitle();
        String content = this.location.getTranslations().getTranslationOne().getContent();
        String address = basicData.getAddress();
        String city = basicData.getCity();
        String phone = basicData.getPhoneLocation();
        String mail = basicData.getMailLocation();
        String web = basicData.getWebLocation();

        List<String> stringList = new ArrayList<>();
        stringList.add(title);
        stringList.add(content);
        stringList.add(address);
        stringList.add(city);
        stringList.add(phone);
        stringList.add(mail);
        stringList.add(web);
        touristDestinationView.getSingleTextViews(stringList);

        String cover = this.location.getId();
        String imageOne = this.location.getImages().getImageZero();
        String imageTwo = this.location.getImages().getImageOne();
        String imageThree = this.location.getImages().getImageTwo();
        String imageFour = this.location.getImages().getImageThree();

        String url = ListRecyclerAdapter.IMAGE_URL;

        imageList.add(url);
        imageList.add(imageOne);
        imageList.add(imageTwo);
        imageList.add(imageThree);
        imageList.add(imageFour);
        imageList.add(cover);
        touristDestinationView.getPictures(imageList);

        Boolean flagOne = false;
        Boolean flagTwo = false;
        Boolean flagThree = false;
        Boolean flagFour = false;
        List<Boolean> flagList = new ArrayList<>();
        for (String service : this.location.getMeta().getServices()) {
            if (service.equals("12")) {
                flagOne = true;
            }
            if (service.equals("13")) {
                flagTwo = true;
            }
            if (service.equals("14")) {
                flagThree = true;
            }
            if (service.equals("16")) {
                flagFour = true;
            }
        }
        flagList.add(flagOne);
        flagList.add(flagTwo);
        flagList.add(flagThree);
        flagList.add(flagFour);
        touristDestinationView.getIcoImages(flagList);

        Boolean starFlagOne = false;
        Boolean starFlagTwo = false;
        Boolean starFlagThree = false;
        Boolean starFlagFour = false;
        Boolean starFlagFive = false;
        Boolean starFlagZero = false;
        List<Boolean> flagStarList = new ArrayList<>();

        ratings = this.location.getRatings();
        if (ratings < 2f && ratings >= 1f) {
            starFlagOne = true;
        } else if (ratings < 3f && ratings >= 2f) {
            starFlagTwo = true;
        } else if (ratings < 4f && ratings >= 3f) {
            starFlagThree = true;
        } else if (ratings < 5f && ratings >= 4f) {
            starFlagFour = true;
        } else if (ratings < 6f && ratings >= 5f) {
            starFlagFive = true;
        } else
            starFlagZero = true;

        flagStarList.add(starFlagZero);
        flagStarList.add(starFlagOne);
        flagStarList.add(starFlagTwo);
        flagStarList.add(starFlagThree);
        flagStarList.add(starFlagFour);
        flagStarList.add(starFlagFive);
        touristDestinationView.getRatings(flagStarList);
    }

}
