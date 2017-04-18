package com.example.korisnik.rouraltourism.activity.share_activity.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;

/**
 * Created by Korisnik on 6.4.2017..
 */

public interface SharePresenter {
    void initialize(String image, String title);
    void setTitle();
    void getImageUri(Context inContext, Bitmap inImage);
}
