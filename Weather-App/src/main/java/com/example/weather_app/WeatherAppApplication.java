package com.example.weather_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // A Spring Boot alkalmazás belépési pontja
public class WeatherAppApplication {

	public static void main(String[] args) {
		// Az alkalmazás elindítása
		SpringApplication.run(WeatherAppApplication.class, args);
	}
}

