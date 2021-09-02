package com.covid.covidafrica.models;

/*
    Stores the given country, region, current total cases, and monthly average total cases
*/
public class LocationStats {
    private String provRegion;
    private String country;

    private int countryPopulation;
    private int currentTotal;
    private int casesSinceLastWeek;
    private double percentageSick;

    private static int totalCasesInAfrica = 0;

    
    public LocationStats(String provRegion, String country, int currentTotal, int casesSinceLastWeek, Integer countryPopulation) {
        this.provRegion = provRegion;
        this.country = country;
        this.currentTotal = currentTotal;
        this.casesSinceLastWeek = casesSinceLastWeek;
        totalCasesInAfrica += currentTotal;
        this.countryPopulation = countryPopulation;
        this.percentageSick = ((double)this.currentTotal/(double)this.countryPopulation)*100;
        // System.out.println(this.currentTotal + " " +this.countryPopulation + " " + ((double)this.currentTotal/(double)this.countryPopulation)*100);
    }

    public double getPercentageSick() {
        return percentageSick;
    }

    public void setPercentageSick(int percentageSick) {
        this.percentageSick = percentageSick;
    }

    public int getCountryPopulation() {
        return countryPopulation;
    }


    public void setCountryPopulation(int countryPopulation) {
        this.countryPopulation = countryPopulation;
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
