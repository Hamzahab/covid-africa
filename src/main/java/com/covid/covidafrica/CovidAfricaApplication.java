package com.covid.covidafrica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/*
1. when app runs, get data from url

*/
@SpringBootApplication
//tells Spring to call scheduled method
@EnableScheduling
public class CovidAfricaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidAfricaApplication.class, args);
	}

}


