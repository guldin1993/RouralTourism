package com.example.korisnik.rouraltourism.activity.tourist_destination_activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.korisnik.rouraltourism.R;
import com.example.korisnik.rouraltourism.activity.image_activity.ImageActivity;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.presenter.TouristDestinationPresenter;
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;
import com.example.korisnik.rouraltourism.model.data_model.Location;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TouristDestinationSingle extends AppCompatActivity implements TouristDestinationView {

    @BindView(R.id.iv_cover_image)
    SimpleDraweeView ivCoverImage;
    @BindView(R.id.iv_location1)
    SimpleDraweeView ivLocation1;
    @BindView(R.id.iv_location2)
    SimpleDraweeView ivLocation2;
    @BindView(R.id.iv_location3)
    SimpleDraweeView ivLocation3;
    @BindView(R.id.iv_location4)
    SimpleDraweeView ivLocation4;
    @BindView(R.id.iv_ico_sleeping)
    ImageView ivIcoSleeping;
    @BindView(R.id.iv_ico_coffe)
    ImageView ivIcoCoffe;
    @BindView(R.id.iv_ico_food)
    ImageView ivIcoFood;
    @BindView(R.id.iv_ico_recreation)
    ImageView ivIcoRecreation;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_source_mail)
    TextView tvSourceMail;
    @BindView(R.id.tv_source_web)
    TextView tvSourceWeb;
    @BindView(R.id.tv_source_telephone)
    TextView tvSoruceTelephone;

    @BindView(R.id.ll_source_mail)
    LinearLayout llSoruceMail;
    @BindView(R.id.ll_source_phone)
    LinearLayout llSorucePhone;
    @BindView(R.id.ll_web)
    LinearLayout llWeb;
    @BindView(R.id.ll_ico_image)
    LinearLayout llIcoImage;

    @Inject
    TouristDestinationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.bind(this);
        RouralTourismApplication.get(this).getAppComponent()
                .plus(new TouristDestinationModule(this))
                .inject(this);
        presenter.initialize((Location) getIntent().getParcelableExtra("TO_TOURIST_DESTINATION_SINGLE"), llSoruceMail, llSorucePhone, llWeb, llIcoImage);
    }

    @OnClick(R.id.iv_cover_image)
    public void onCoverPictureClick(){
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setCoverImage());
    }

    @OnClick(R.id.iv_location1)
    public void onLocation1PictureClick(){
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocaiton1Image());
    }

    @OnClick(R.id.iv_location2)
    public void onLocation2PictureClick(){
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocation2Image());
    }

    @OnClick(R.id.iv_location3)
    public void onLocation3PictureClick(){
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocation3Image());
    }

    @OnClick(R.id.iv_location4)
    public void onLocation4PictureClick(){
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocation4Image());
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
    public void getPictures(List<String> pictures) {
        Uri uri;
        for (int i = 1; i < pictures.size(); i++) {
            uri = Uri.parse(pictures.get(0) + pictures.get(i));

            if (i == 1)
                ivLocation1.setImageURI(uri);
            if (i == 2)
                ivLocation2.setImageURI(uri);
            if (i == 3)
                ivLocation3.setImageURI(uri);
            if (i == 4)
                ivLocation4.setImageURI(uri);
            if (i == 5)
                ivCoverImage.setImageURI(uri);

            if (i == 1 && (pictures.get(i).isEmpty() || pictures.get(i) == null))
                ivLocation1.setVisibility(View.GONE);

            if (i == 2 && (pictures.get(i).isEmpty() || pictures.get(i) == null))
                ivLocation2.setVisibility(View.GONE);

            if (i == 3 && (pictures.get(i).isEmpty() || pictures.get(i) == null))
                ivLocation3.setVisibility(View.GONE);

            if (i == 4 && (pictures.get(i).isEmpty() || pictures.get(i) == null || pictures.get(i) == "1467629180"))
                ivLocation4.setVisibility(View.GONE);
        }

    }
}
