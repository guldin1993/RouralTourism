package com.example.korisnik.rouraltourism.activity.share_activity.presenter;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by Korisnik on 6.4.2017..
 */

public interface SharePresenter {
    void initialize(String image, String title);
    String setTitle();
    Bitmap getBitmapFromView(View view);
}
