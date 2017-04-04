package com.example.korisnik.rouraltourism.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class BaseInteractorImpl {
    private CompositeSubscription subscription = new CompositeSubscription();

    protected void addSubscription (Subscription subscription){
        if(subscription == null || subscription.isUnsubscribed())
            this.subscription = new CompositeSubscription();
        this.subscription.add(subscription);
    }

   protected void unsubscribe(){
       this.subscription.unsubscribe();
   }
}
