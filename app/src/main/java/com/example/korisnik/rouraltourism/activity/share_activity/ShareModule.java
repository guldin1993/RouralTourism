package com.example.korisnik.rouraltourism.activity.share_activity;

import com.example.korisnik.rouraltourism.activity.share_activity.presenter.SharePresenter;
import com.example.korisnik.rouraltourism.activity.share_activity.presenter.SharePresenterImpl;
import com.example.korisnik.rouraltourism.base.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Korisnik on 6.4.2017..
 */

@Module
public class ShareModule {
    public final ShareView shareView;

    public ShareModule(ShareView shareView) {
        this.shareView = shareView;
    }

    @PerActivity
    @Provides
    SharePresenter providesSharePresenter(SharePresenterImpl sharePresenter){
        return sharePresenter;
    }

    @PerActivity
    @Provides
    ShareView providesShareView(){
        return this.shareView;
    }
}
