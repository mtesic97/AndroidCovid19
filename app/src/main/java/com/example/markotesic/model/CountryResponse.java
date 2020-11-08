package com.example.markotesic.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CountryResponse {
    public CountryResponse(){

    }
    //Mapiranje iz json u java objekat
    @SerializedName("Country")
    private String country;

    @SerializedName("Confirmed")
    private int confirmed;

    @SerializedName("Deaths")
    private int deadths;

    @SerializedName("Date")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDeadths() {
        return deadths;
    }

    public void setDeadths(int deadths) {
        this.deadths = deadths;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }
}
