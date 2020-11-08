package com.example.markotesic;

import com.example.markotesic.model.CountryResponse;
import com.example.markotesic.model.CovidCountryResponse;
import com.example.markotesic.model.CovidSummaryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Covid19API {
    //Definisemo endpoint za api
    @GET("/summary")
    Call<CovidSummaryResponse> getWorldSummary();


    @GET("/country/{country}")
    Call<List<CountryResponse>> getByCountry(@Path("country")String country, @Query("from")String from, @Query("to")String to);
}
