package com.covid.covidafrica.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import com.covid.covidafrica.models.LocationStats;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.None;

import org.apache.commons.csv.CSVFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import org.apache.commons.csv.*;


/*
class will get data from database (given url), and parse for african covid data
*/
@Service
public class CovidDataService{


    
    private static String covidStatsURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static String africanCountURL = "https://raw.githubusercontent.com/dbouquin/IS_608/master/NanosatDB_munging/Countries-Continents.csv";

    private static List<LocationStats> statsList = new ArrayList<LocationStats>();
    
    //when application starts up, execute
    @PostConstruct
    /*
    how often to fetch new data
    format: sec min hour day month year
    every hour below
    */
    @Scheduled(cron = "* * 1 * * *")
    public static void getData() throws IOException, InterruptedException{

        //get response for both african countries data and covidData
        HttpResponse<String> covidStatsResponse = getHTTPResponse(covidStatsURL);
        HttpResponse<String> africanCountriesResponse = getHTTPResponse(africanCountURL);

        //temporary, will end up populating statsList
        List<LocationStats> currentDayStats = parseCovidData(covidStatsResponse, parseAfricanCountries(africanCountriesResponse));
        statsList = currentDayStats;
       
    }

    //returns total cases
    public static int getTotalCases(CSVRecord record){
        int completeTotal = 0;
        for(int i = 4; i < record.size() ;i++){
            completeTotal += Integer.valueOf(record.get(i));
        }
        return completeTotal;
    }

    public static HttpResponse<String> getHTTPResponse(String url) throws IOException, InterruptedException{

        //send reuquest and retrieve responses
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        //get response
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }


    public static List<LocationStats> parseCovidData(HttpResponse<String> response, HashSet<String> africanCountriesList) throws IOException{
        StringReader csvReader = new StringReader(response.body());
        //stores location stats
        List<LocationStats> currentStats = new ArrayList<LocationStats>();

        //parse all csv data
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader() .parse(csvReader);
        for (CSVRecord record : records) {
            String provState = record.get("Province/State");
            String countRegion = record.get("Country/Region");
            int currentTotal = Integer.valueOf(record.get(record.size() - 1));

            //check if real row and is an african country
            if(!countRegion.equals("") && africanCountriesList.contains(countRegion)){
                LocationStats newInst = new LocationStats(provState, countRegion, currentTotal, getTotalCases(record));

                currentStats.add(newInst);
                System.out.println(newInst.getCountry() + " " + newInst.getCurrentTotal() + " " + newInst.getTotalAllTime());

            }
        }
        return currentStats;
    }

    public static HashSet<String> parseAfricanCountries(HttpResponse<String> response) throws IOException{
        StringReader csvReader = new StringReader(response.body());
        HashSet<String> countries = new HashSet<>();
        
        //parse all csv data
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader() .parse(csvReader);

        for (CSVRecord record : records) {
            String continent = record.get("Continent");
            //if country is africa, save to hashset
            if(continent.equals("Africa")){
                String country = record.get("Country");
                countries.add(country);
            }
        }
        return countries;
    }

    public List<LocationStats> getStatsList() {
        return statsList;
    }

    public static void setStatsList(List<LocationStats> statsList) {
        CovidDataService.statsList = statsList;
    }

    
}