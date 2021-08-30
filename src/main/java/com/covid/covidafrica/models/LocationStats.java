package com.covid.covidafrica.models;

/*
    Stores the given country, region, current total cases, and monthly average total cases
*/
public class LocationStats {
    private String provRegion;
    private String country;
    private int currentTotal;
    private int totalAllTime;

    
    
    
    public LocationStats(String provRegion, String country, int currentTotal, int totalAllTime) {
        this.provRegion = provRegion;
        this.country = country;
        this.currentTotal = currentTotal;
        this.totalAllTime = totalAllTime;
    }




    public int getTotalAllTime() {
        return totalAllTime;
    }




    public void setTotalAllTime(int totalAllTime) {
        this.totalAllTime = totalAllTime;
    }




    public String getProvRegion() {
        return provRegion;
    }




    public void setProvRegion(String provRegion) {
        this.provRegion = provRegion;
    }




    public String getCountry() {
        return country;
    }




    public void setCountry(String country) {
        this.country = country;
    }




    public int getCurrentTotal() {
        return currentTotal;
    }




    public void setCurrentTotal(int currentTotal) {
        this.currentTotal = currentTotal;
    }

}
