package com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter;

import com.example.korisnik.rouraltourism.R;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.TouristDestinationView;
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;
import com.example.korisnik.rouraltourism.model.data_model.BasicLocationData;
import com.example.korisnik.rouraltourism.model.data_model.Location;
import com.example.korisnik.rouraltourism.model.data_model.ServiceImagesSingle;
import com.example.korisnik.rouraltourism.model.data_model.TextInformationsSIngle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Korisnik on 4.4.2017..
 */

public class TouristDestinationPresenterImpl implements TouristDestinationPresenter {

    private TouristDestinationView touristDestinationView;
    private TextInformationsSIngle textInformationsSIngle = new TextInformationsSIngle();
    private ServiceImagesSingle serviceImagesSingle;
    private List<String> imageList = new ArrayList<>();
    private float ratings = 0f;
    private Location location;
    private String uri;
    private Double latLoc;
    private Double lngLoc;
    private String title;

    @Inject
    public TouristDestinationPresenterImpl(TouristDestinationView touristDestinationView) {
        this.touristDestinationView = touristDestinationView;
    }

    @Override
    public void initialize(Location location) {
        this.location = location;
        BasicLocationData basicData = this.location.getMeta().getBasic();
        title = location.getTranslations().getTranslationOne().getTitle();

        textInformationsSIngle.setTitle(this.location.getTranslations().getTranslationOne().getTitle());
        textInformationsSIngle.setContent(this.location.getTranslations().getTranslationOne().getContent());
        textInformationsSIngle.setAddress(basicData.getAddress());
        textInformationsSIngle.setCity(basicData.getCity());
        textInformationsSIngle.setPhone(basicData.getPhoneLocation());
        textInformationsSIngle.setMail(basicData.getMailLocation());
        textInformationsSIngle.setWeb(basicData.getWebLocation());
        touristDestinationView.showTextViews(textInformationsSIngle);

        String imageOne = this.location.getImages().getImageZero();
        String imageTwo = this.location.getImages().getImageOne();
        String imageThree = this.location.getImages().getImageTwo();
        String imageFour = this.location.getImages().getImageThree();

        String url = RouralTourismApplication.IMAGE_URL;

        imageList.add(url);
        imageList.add(imageOne);
        imageList.add(imageTwo);
        imageList.add(imageThree);
        imageList.add(imageFour);
        touristDestinationView.showLocationImages(imageList);

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
        touristDestinationView.showServiceImages(flagList);

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
        touristDestinationView.showRatings(flagStarList);
    }

    @Override
    public void setCurrentLocation(Double lat, Double lng) {
        latLoc = lat;
        lngLoc = lng;
    }

    @Override
    public void currentLocation() {
        uri = "http://maps.google.com/maps?f=d&hl=en&saddr=" + latLoc + "," + lngLoc + "&daddr=" + location.getMeta().getLoc().getLat() + "," + location.getMeta().getLoc().getLng();
        touristDestinationView.callFindLocation(uri);
    }

    @Override
    public void setImageId(int id) {
        switch (id) {
            case R.id.iv_cover_image:
                touristDestinationView.callImageActivity(location.getImages().getImageZero(), title);
                break;
            case R.id.iv_location1:
                touristDestinationView.callImageActivity(location.getImages().getImageOne(), title);
                break;
            case R.id.iv_location2:
                touristDestinationView.callImageActivity(location.getImages().getImageTwo(), title);
                break;
            case R.id.iv_location3:
                touristDestinationView.callImageActivity(location.getImages().getImageThree(), title);
                break;
            default:
                break;
        }
    }

    @Override
    public void setShareData() {
        touristDestinationView.callShareActivity(location.getImages().getImageZero(), title);
    }

    @Override
    public void setTitle() {
        touristDestinationView.showAppBarTitle(title);
    }

    @Override
    public void setMapLocation() {
        touristDestinationView.onMapLocation(location.getMeta().getLoc().getLat(), location.getMeta().getLoc().getLng());
    }
}
