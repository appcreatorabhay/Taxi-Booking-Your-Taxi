package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.controller") 
@ComponentScan(basePackages = "com.example")

public class TaxiBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiBookingApplication.class, args);
	}

}