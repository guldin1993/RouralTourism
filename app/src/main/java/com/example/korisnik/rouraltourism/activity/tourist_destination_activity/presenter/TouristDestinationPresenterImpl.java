package com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter;

import android.view.View;
import android.widget.LinearLayout;

import com.example.korisnik.rouraltourism.activity.home_activity.adapter.ListRecyclerAdapter;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.TouristDestinationView;
import com.example.korisnik.rouraltourism.model.data_model.BasicLocationData;
import com.example.korisnik.rouraltourism.model.data_model.Location;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Korisnik on 4.4.2017..
 */

public class TouristDestinationPresenterImpl implements TouristDestinationPresenter {

    private TouristDestinationView touristDestinationView;
    private List<String> imageList = new ArrayList<>();

    @Inject
    public TouristDestinationPresenterImpl(TouristDestinationView touristDestinationView) {
        this.touristDestinationView = touristDestinationView;
    }

    @Override
    public String setCoverImage(){
        return imageList.get(5);
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
    public void initialize(Location location, LinearLayout llSoruceMail, LinearLayout llSorucePhone, LinearLayout llWeb, LinearLayout llIcoImage) {
        BasicLocationData basicData = location.getMeta().getBasic();

        String title = location.getTranslations().getTranslationOne().getTitle();
        String content = location.getTranslations().getTranslationOne().getContent();
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

        String cover = location.getId();
        String imageOne = location.getImages().getImageZero();
        String imageTwo = location.getImages().getImageOne();
        String imageThree = location.getImages().getImageTwo();
        String imageFour = location.getImages().getImageThree();

        String url = ListRecyclerAdapter.IMAGE_URL;

        imageList.add(url);
        imageList.add(imageOne);
        imageList.add(imageTwo);
        imageList.add(imageThree);
        imageList.add(imageFour);
        imageList.add(cover);
        touristDestinationView.getPictures(imageList);

        if (basicData.getWebLocation().equals("") || basicData.getWebLocation().isEmpty()) {
            llWeb.setVisibility(View.GONE);
        }
        if (basicData.getPhoneLocation().isEmpty() || basicData.getPhoneLocation().equals("")) {
            llSoruceMail.setVisibility(View.GONE);
        }
        if (basicData.getMailLocation().equals("") || basicData.getMailLocation().isEmpty()) {
            llSorucePhone.setVisibility(View.GONE);
        }

        Boolean flagOne = false;
        Boolean flagTwo = false;
        Boolean flagThree = false;
        Boolean flagFour = false;
        List<Boolean> flagList = new ArrayList<>();
        for (String service : location.getMeta().getServices()) {
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
    }

}
