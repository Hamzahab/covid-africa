package com.covid.covidafrica.controllers;

import java.text.NumberFormat;
import java.util.Locale;

import com.covid.covidafrica.models.LocationStats;
import com.covid.covidafrica.services.CovidDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    CovidDataService dataInstance;
    @GetMapping("/")
    public String home(Model baseModel){
        NumberFormat format = NumberFormat.getInstance(Locale.US);

        baseModel.addAttribute("statsList", dataInstance.getStatsList());
        baseModel.addAttribute("totalCases", format.format(LocationStats.getTotalCasesInAfrica()));


        return "home";
    }
}
