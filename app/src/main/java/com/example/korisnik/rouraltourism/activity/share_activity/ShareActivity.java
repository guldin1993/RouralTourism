package com.example.korisnik.rouraltourism.activity.share_activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.korisnik.rouraltourism.R;
import com.example.korisnik.rouraltourism.activity.share_activity.presenter.SharePresenterImpl;
import com.example.korisnik.rouraltourism.base.RouralTourismApplication;
import com.facebook.drawee.view.SimpleDraweeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareActivity extends AppCompatActivity implements ShareView{

    @BindView(R.id.iv_cover_image)
    SimpleDraweeView ivCoverImage;
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
        presenter.initialize(getIntent().getStringExtra("TO_SHARE_ACTIVITY_IMAGE"), getIntent().getStringExtra("TO_SHARE_ACTIVITY_TITLE"));
    }

    @OnClick(R.id.ll_share_online)
    public void onCoverPictureClick(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "RouralTourism");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "share this");
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
    public void getEdiTextText(String text) {
        etShateContent.setText(text);
    }
}
