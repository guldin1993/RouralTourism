package com.example.korisnik.rouraltourism.activity.share_activity.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import com.example.korisnik.rouraltourism.activity.share_activity.ShareView;
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.request.ImageRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.inject.Inject;

/**
 * Created by Korisnik on 6.4.2017..
 */

public class SharePresenterImpl implements SharePresenter {

    private ShareView shareView;
    private String title;

    @Inject
    public SharePresenterImpl(ShareView shareView) {
        this.shareView = shareView;
    }

    @Override
    public void initialize(String image, String title) {
        this.title = title;
        String editText;

        editText = "Posjetite " + title + " #RouralTourism";
        Uri uri = Uri.parse(RouralTourismApplication.IMAGE_URL + image);

        shareView.getCoverImage(uri);
        shareView.getTitle(this.title);
        shareView.getEditTextText(editText);
    }

    @Override
    public void setTitle() {
        shareView.showAppBarTitle(title);
    }

    @Override
    public void getImageUri(Context inContext, Bitmap inImage) {
        Uri uri;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        uri = Uri.parse(path);
        shareView.sharePicture(uri);
    }
}

