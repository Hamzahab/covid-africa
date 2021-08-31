package com.covid.covidafrica.models;

/*
    Stores the given country, region, current total cases, and monthly average total cases
*/
public class LocationStats {
    private String provRegion;
    private String country;
    private int currentTotal;
    private static int totalCasesInAfrica = 0;
    private int casesSinceLastWeek;
    
    
    
    public LocationStats(String provRegion, String country, int currentTotal, int casesSinceLastWeek) {
        this.provRegion = provRegion;
        this.country = country;
        this.currentTotal = currentTotal;
        this.casesSinceLastWeek = casesSinceLastWeek;
        totalCasesInAfrica += currentTotal;
    }


    public static int getTotalCasesInAfrica() {
        return totalCasesInAfrica;
    }


    public int getCasesSinceLastWeek() {
        return casesSinceLastWeek;
    }


    public void setCasesSinceLastWeek(int casesSinceLastWeek) {
        this.casesSinceLastWeek = casesSinceLastWeek;
    }


    public static void setTotalCasesInAfrica(int totalCasesInAfrica) {
        LocationStats.totalCasesInAfrica = totalCasesInAfrica;
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
