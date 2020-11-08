package com.example.markotesic.model;

import com.google.gson.annotations.SerializedName;

public class Global {

    public Global(){

    }

    @SerializedName("NewConfirmed")
    private int newConfirmed;

    @SerializedName("TotalConfirmed")
    private int totalConfirmed;

    @SerializedName("NewDeaths")
    private int newDeaths;

    @SerializedName("TotalDeaths")
    private int totalDeath;

    @SerializedName("NewRecovered")
    private int newRecovered;

    @SerializedName("TotalRecovered")
    private int totalRecovered;

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(int newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(int totalDeath) {
        this.totalDeath = totalDeath;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }
}
