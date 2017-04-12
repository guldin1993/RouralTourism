package com.example.korisnik.rouraltourism.model.interactors;

import com.example.korisnik.rouraltourism.base.BaseInteracotr;
import com.example.korisnik.rouraltourism.model.interactors.listener.Listener;

/**
 * Created by Korisnik on 3.4.2017..
 */

public interface Interactor extends BaseInteracotr {
    void getRatedLocations(Listener listener);
}
