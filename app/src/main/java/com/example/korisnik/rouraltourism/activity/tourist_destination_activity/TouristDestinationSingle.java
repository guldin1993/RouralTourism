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

import com.example.korisnik.rouraltourism.activity.image_actvity.ImageActivity;
import com.example.korisnik.rouraltourism.activity.share_activity.ShareActivity;
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
    @BindView(R.id.linearLayout_cell)
    LinearLayout linearLayoutStars;

    @BindView(R.id.ll_share)
    LinearLayout llShare;

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
        presenter.initialize((Location) getIntent().getParcelableExtra("TO_TOURIST_DESTINATION_SINGLE"));

    }

    @OnClick(R.id.iv_cover_image)
    public void onCoverPictureClick(){
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setCoverImage());
        startActivity(i);
    }

    @OnClick(R.id.iv_location1)
    public void onLocation1PictureClick(){
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocaiton1Image());
        startActivity(i);
    }

    @OnClick(R.id.iv_location2)
    public void onLocation2PictureClick(){
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocation2Image());
        startActivity(i);
    }

    @OnClick(R.id.iv_location3)
    public void onLocation3PictureClick(){
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocation3Image());
        startActivity(i);
    }

    @OnClick(R.id.iv_location4)
    public void onLocation4PictureClick(){
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("TO_IMAGE_ACTIVITY", presenter.setLocation4Image());
        startActivity(i);
    }

    @OnClick(R.id.ll_share)
    public void onShareClick(){
        Intent i = new Intent(this, ShareActivity.class);
        i.putExtra("TO_SHARE_ACTIVITY_IMAGE", presenter.setCoverImage());
        i.putExtra("TO_SHARE_ACTIVITY_TITLE", presenter.shareTitle());
        startActivity(i);
    }

    @OnClick(R.id.ll_find_location)
    public void onLocationFindClick(){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(presenter.setLocation()));
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

        if (strings.get(5).isEmpty()|| strings.get(5).equals("")) {
            llSoruceMail.setVisibility(View.GONE);
        }
        if (strings.get(4).isEmpty()|| strings.get(4).equals("")) {
            llSorucePhone.setVisibility(View.GONE);
        }
        if (strings.get(6).isEmpty()|| strings.get(6).equals("")) {
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
        if (ratings.get(0)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarTwo.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarThree.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarFour.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarFive.setImageResource(R.mipmap.subcategory_star_brawn);
        }else if (ratings.get(1)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarTwo.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarThree.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarFour.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarFive.setImageResource(R.mipmap.subcategory_star_brawn);
        }else if (!ratings.get(2)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarTwo.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarThree.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarFour.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarFive.setImageResource(R.mipmap.subcategory_star_brawn);
        }else if (!ratings.get(3)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarTwo.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarThree.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarFour.setImageResource(R.mipmap.subcategory_star_brawn);
            ivStarFive.setImageResource(R.mipmap.subcategory_star_brawn);
        }else if (!ratings.get(4)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarTwo.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarThree.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarFour.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarFive.setImageResource(R.mipmap.subcategory_star_brawn);
        }else if (!ratings.get(4)) {
            ivStarOne.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarTwo.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarThree.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarFour.setImageResource(R.mipmap.subcategory_star_white_big);
            ivStarFive.setImageResource(R.mipmap.subcategory_star_white_big);
        }
        linearLayoutStars.bringToFront();
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

            if (i == 1 && (pictures.get(i).isEmpty() || pictures.get(i) == null || pictures.get(i).equals("")))
                ivLocation1.setVisibility(View.GONE);

            if (i == 2 && (pictures.get(i).isEmpty() || pictures.get(i) == null || pictures.get(i).equals("")))
                ivLocation2.setVisibility(View.GONE);

            if (i == 3 && (pictures.get(i).isEmpty() || pictures.get(i) == null || pictures.get(i).equals("")))
                ivLocation3.setVisibility(View.GONE);

            if (i == 4 && (pictures.get(i).isEmpty() || pictures.get(i) == null  || pictures.get(i).equals("")))
                ivLocation4.setVisibility(View.GONE);
        }

    }
}
