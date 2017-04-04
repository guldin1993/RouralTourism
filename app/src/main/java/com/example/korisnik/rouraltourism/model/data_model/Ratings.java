package com.example.korisnik.rouraltourism.model.data_model;

/**
 * Created by Korisnik on 3.4.2017..
 */

public class Ratings {

    private String id;
    private float rating;

    public Ratings(String id, float rating) {
        this.id = id;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
