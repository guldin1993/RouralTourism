package com.example.korisnik.rouraltourism.activity.share_activity.presenter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.korisnik.rouraltourism.activity.home_activity.adapter.ListRecyclerAdapter;
import com.example.korisnik.rouraltourism.activity.share_activity.ShareView;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

/**
 * Created by Korisnik on 6.4.2017..
 */

public class SharePresenterImpl implements SharePresenter {

    private ShareView shareView;
    private String image;
    private String title;

    @Inject
    public SharePresenterImpl(ShareView shareView) {
        this.shareView = shareView;
    }


    @Override
    public void initialize(String image, String title) {
        this.image = image;
        this.title = title;
        String editText;

        editText = "Posjetite " + title + " #RouralTourism";

        shareView.getCoverImage(ListRecyclerAdapter.IMAGE_URL, image);
        shareView.getTitle(title);
        shareView.getEditTextText(editText);
    }

    @Override
    public String setTitle() {
        return title;
    }
}

