package com.example.korisnik.rouraltourism.activity.share_activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareActivity extends AppCompatActivity implements ShareView {

    private static final int CAMERA_REQUEST = 1888;

    Uri imageUri;

    @BindView(R.id.iv_cover_image)
    ImageView ivCoverImage;
    @BindView(R.id.tv_share_title)
    TextView tvTitle;
    @BindView(R.id.et_share_content)
    EditText etShateContent;

    @Inject
    SharePresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        RouralTourismApplication.get(this).getAppComponent().plus(new ShareModule(this)).inject(this);
        presenter.initialize(getIntent().getStringExtra("TO_SHARE_ACTIVITY_IMAGE"), getIntent().getStringExtra("TO_IMAGE_ACTIVITY_TITLE"));
        setTitle(presenter.setTitle());
    }

    @OnClick(R.id.iv_take_photo)
    public void onPhotoPictureClick() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
        }
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);

                } else {

                    Toast.makeText(this, "Photo can't be taken without permission to use camera", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            imageUri = data.getData();
            ivCoverImage.setImageURI(imageUri);
            ivCoverImage.setMinimumWidth(metrics.widthPixels);
            ivCoverImage.setMinimumHeight(190);

            //Bitmap image = (Bitmap) data.getExtras().get("data");
            //presenter.setImage(this, (Bitmap) data.getExtras().get("data"), imageUri);
        }
    }

    @OnClick(R.id.ll_share_online)
    public void onCoverPictureClick() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "RouralTourism");
        //shareIntent.putExtra(Intent.EXTRA_TEXT, "share this");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    @Override
    public void getCoverImage(String baseUrl, String image) {
        Uri uri;
        uri = Uri.parse(baseUrl + image);
        ivCoverImage.setImageURI(uri);
    }

    @Override
    public void getTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void getEditTextText(String text) {
        etShateContent.setText(text);
    }
}
