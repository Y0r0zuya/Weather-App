package com.example.weather_app.controller;

import com.example.weather_app.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    // Az API kulcs az application.properties fájlból van beolvasva
    @Value("${api.key}")
    private String apiKey;

    // Az alkalmazás kezdőlapját kezelő végpont
    @GetMapping("/")
    public String getIndex() {
        return "index"; // Az index.html visszatérése
    }

    // Az időjárási adatok lekéréséért felelős végpont
    @GetMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        // Ellenőrizzük, hogy a város neve nem üres vagy null
        if (city == null || city.trim().isEmpty()) {
            model.addAttribute("error", "A város neve nem lehet üres."); // Hibaüzenet hozzáadása a modellhez
            return "weather"; // Az időjárási oldal visszatérése hibaüzenettel
        }

        try {
            // API URL összeállítása a megadott várossal és API kulccsal
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
            RestTemplate restTemplate = new RestTemplate();

            // API hívás az időjárási adatok lekéréséhez
            WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);

            // Ha van válasz az API-tól, az adatokat hozzáadjuk a modellhez
            if (weatherResponse != null) {
                model.addAttribute("city", weatherResponse.getName()); // Város neve
                model.addAttribute("country", weatherResponse.getSys().getCountry()); // Ország kódja
                model.addAttribute("weatherDescription", weatherResponse.getWeather().get(0).getDescription()); // Időjárási leírás
                model.addAttribute("temperature", weatherResponse.getMain().getTemp()); // Hőmérséklet
                model.addAttribute("humidity", weatherResponse.getMain().getHumidity()); // Páratartalom
                model.addAttribute("windSpeed", weatherResponse.getWind().getSpeed()); // Szélsebesség

                // Időjárási ikon az ID alapján
                String weatherIcon = "wi wi-owm-" + weatherResponse.getWeather().get(0).getId();
                model.addAttribute("weatherIcon", weatherIcon);
            } else {
                // Ha a válasz null, hibaüzenetet adunk hozzá
                model.addAttribute("error", "A város nem található.");
            }
        } catch (RestClientException e) {
            // API hívás közbeni hiba esetén
            model.addAttribute("error", "Hiba történt az időjárási adatok lekérése közben: " + e.getMessage());
        }

        return "weather"; // Az időjárási oldal visszatérése
    }
}