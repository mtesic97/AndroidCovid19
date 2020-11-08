package com.example.markotesic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
//visak
public class CovidCountryResponse {

    @SerializedName("countries")
    private List<CountryResponse> countries;

    public List<CountryResponse> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryResponse> countries) {
        this.countries = countries;
    }
}
