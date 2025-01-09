package com.example.weather_app.controller;

import com.example.weather_app.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    @Value("${api.key}")
    private String apiKey; // Az API kulcs az időjárási adatok lekéréséhez

    @GetMapping("/")
    public String getIndex() {
        return "index"; // A kezdőoldal megjelenítése
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        // Az API URL összeállítása a városnévvel és az API kulccsal
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        RestTemplate restTemplate = new RestTemplate(); // REST kliens inicializálása

        // Az időjárási adatok lekérése és betöltése a WeatherResponse modellbe
        WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);

        if (weatherResponse != null) {
            // Az időjárási adatok hozzáadása a modellhez megjelenítéshez
            model.addAttribute("city", weatherResponse.getName()); // Város neve
            model.addAttribute("country", weatherResponse.getSys().getCountry()); // Ország kódja
            model.addAttribute("weatherDescription", weatherResponse.getWeather().get(0).getDescription()); // Időjárás leírása
            model.addAttribute("temperature", weatherResponse.getMain().getTemp()); // Hőmérséklet
            model.addAttribute("humidity", weatherResponse.getMain().getHumidity()); // Páratartalom
            model.addAttribute("windSpeed", weatherResponse.getWind().getSpeed()); // Szélsebesség

            // Időjárási ikon osztályának összeállítása
            String weatherIcon = "wi wi-owm-" + weatherResponse.getWeather().get(0).getId();
            model.addAttribute("weatherIcon", weatherIcon); // Időjárási ikon
        } else {
            model.addAttribute("error", "City not found."); // Hibaüzenet, ha a város nem található
        }

        return "weather"; // Az időjárási adatok megjelenítése az "weather" oldalon
    }
}
