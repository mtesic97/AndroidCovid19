package com.example.markotesic.model;

import com.google.gson.annotations.SerializedName;

public class CovidSummaryResponse {

    @SerializedName("Global")
    private Global global;

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public CovidSummaryResponse(){
    }


}
