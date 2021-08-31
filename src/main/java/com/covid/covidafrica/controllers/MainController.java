package com.covid.covidafrica.controllers;

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
        baseModel.addAttribute("statsList", "TESTING");
        return "home";
    }
}
