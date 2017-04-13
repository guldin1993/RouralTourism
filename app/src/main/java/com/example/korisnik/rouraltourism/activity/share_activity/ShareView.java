package com.example.korisnik.rouraltourism.activity.share_activity;

import android.net.Uri;

/**
 * Created by Korisnik on 6.4.2017..
 */

public interface ShareView {
    void getCoverImage(Uri uri);
    void getTitle(String title);
    void getEditTextText(String text);
}
