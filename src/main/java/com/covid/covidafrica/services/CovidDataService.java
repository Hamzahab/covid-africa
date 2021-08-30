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

    
    private static String dataURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    

    private List<LocationStats> statsList = new ArrayList<LocationStats>();
    //when application starts up, execute
    @PostConstruct
    /*
    how often to fetch new data
    format: sec min hour day month year
    every hour below
    */
    @Scheduled(cron = "* * 1 * * *")
    public static void getData() throws IOException, InterruptedException{
        //temporary, will end up populating statsList
        List<LocationStats> currentStats = new ArrayList<LocationStats>();

        //sends reuquests and retrieves responses
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(dataURL)).build();

        //send request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvReader = new StringReader(response.body());

        //parse all csv data into vars
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader() .parse(csvReader);
        HashSet<String> hs = new HashSet<>();
        int totalAllTime = 0;
        for (CSVRecord record : records) {
            String provState = record.get("Province/State");
            String countRegion = record.get("Country/Region");
            int currentTotal = Integer.valueOf(record.get(record.size() - 1));

            if(!countRegion.equals("")){
                LocationStats newInst = new LocationStats(provState, countRegion, currentTotal, getTotal(record));

                currentStats.add(newInst);
                hs.add(countRegion);
            }
        }
        //test output
        //System.out.println(currentStats.get(0).getCountry() + " " + currentStats.get(0).getCurrentTotal() + " " + currentStats.get(0).getTotalAllTime());
    }

    public static int getTotal(CSVRecord record){
        int completeTotal = 0;
        for(int i = 4; i < record.size() ;i++){
            completeTotal += Integer.valueOf(record.get(i));
        }
        return completeTotal;
    }


}