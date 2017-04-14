package com.example.korisnik.rouraltourism.activity.share_activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaDataSource;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.korisnik.rouraltourism.R;
import com.example.korisnik.rouraltourism.activity.share_activity.presenter.SharePresenterImpl;
import com.example.korisnik.rouraltourism.activity.tourist_destination_activity.TouristDestinationSingleActivity;
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareActivity extends AppCompatActivity implements ShareView {

    private static final int CAMERA_REQUEST = 1888;
    private static final int REQUEST_WRITE_PERMISSION = 7;


    @BindView(R.id.iv_cover_image)
    SimpleDraweeView ivCoverImage;
    @BindView(R.id.tv_share_title)
    TextView tvTitle;
    @BindView(R.id.et_share_content)
    EditText etShateContent;
    @BindView(R.id.iv_take_photo)
    ImageView ivTakePhoto;

    @Inject
    SharePresenterImpl presenter;

    private boolean flagTakePhoto = false;

    private Uri imageUri;

    DataSource<CloseableReference<CloseableImage>> dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        RouralTourismApplication.get(this).getAppComponent().plus(new ShareModule(this)).inject(this);
        presenter.initialize(getIntent().getStringExtra(TouristDestinationSingleActivity.EXTRA_IMAGE_TO_SHARE_ACTIVITY), getIntent().getStringExtra(TouristDestinationSingleActivity.EXTRA_TEXT_TO_SHARE_ACTIVITY));
        presenter.setTitle();
        getSupportActionBar().setIcon((R.mipmap.ic_launcher));
    }

    @Override
    public void showAppBarTitle(String title) {
        setTitle(title);
    }

    @OnClick(R.id.iv_take_photo)
    public void onPhotoPictureClick() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
            flagTakePhoto = true;

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    flagTakePhoto = true;

                } else {
                    Toast.makeText(this, "Photo can't be taken without permission to use camera", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            imageUri = data.getData();
            ivCoverImage.setImageURI(imageUri);
        }
    }

    @OnClick(R.id.ll_share_online)
    public void onShareOnlineClick() {
       // Uri uri= Uri.parse("http://slavonijaturizam.eu/cms/photo/516");

        /*
        ImageDecodeOptions decodeOptions = ImageDecodeOptions.newBuilder()
                .build();

        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setImageDecodeOptions(decodeOptions)
                .setAutoRotateEnabled(true)
                .setLocalThumbnailPreviewsEnabled(true)
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .setProgressiveRenderingEnabled(false)
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        dataSource = imagePipeline.fetchDecodedImage(request, this);*/


        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("*/*");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "RouralTourism");
        shareIntent.putExtra(Intent.EXTRA_TEXT, etShateContent.getText());
        if (flagTakePhoto) {
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.putExtra(Intent.EXTRA_STREAM, presenter.getImageUri(this, presenter.getBitmapFromView(ivCoverImage)));
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.putExtra(Intent.EXTRA_STREAM, presenter.getImageUri(this, presenter.getBitmapFromView(ivCoverImage)));
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }

        }
    }

    @Override
    public void getCoverImage(Uri uri) {
        ivCoverImage.setImageURI(uri);

    }

  /*  public void savePicture(){
        ImageRequest downloadRequest = ImageRequest.fromUri(imageUri);
        CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(downloadRequest,null);
        if (ImagePipelineFactory.getInstance().getMainFileCache().hasKey(cacheKey)) {
            BinaryResource resource = ImagePipelineFactory.getInstance().getMainFileCache().getResource(cacheKey);
            File cacheFile = ((FileBinaryResource) resource).getFile();
            try {
                FileInputStream fis = new FileInputStream(cacheFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }*/

    @Override
    public void getTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void getEditTextText(String text) {
        etShateContent.setText(text);
    }
}
