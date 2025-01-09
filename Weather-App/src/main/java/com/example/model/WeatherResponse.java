package com.example.model;

import java.util.List;

public class WeatherResponse {
    private String name; // A város neve
    private Sys sys; // Az ország adatai
    private List<Weather> weather; // Időjárási információk listája
    private Main main; // Főbb meteorológiai adatok
    private Wind wind; // Széladatok

    // Getterek és setterek
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    // Belső osztály az ország adataihoz
    public static class Sys {
        private String country; // Az ország kódja (pl. "HU" Magyarország esetén)

        // Getterek és setterek
        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    // Belső osztály az időjárási adatokhoz
    public static class Weather {
        private int id; // Az időjárási körülmény azonosítója
        private String description; // Az időjárás leírása (pl. "tiszta égbolt")

        // Getterek és setterek
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    // Belső osztály a főbb meteorológiai adatokhoz
    public static class Main {
        private double temp; // Hőmérséklet Celsiusban
        private int humidity; // Páratartalom százalékban

        // Getterek és setterek
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }

    // Belső osztály a széladatokhoz
    public static class Wind {
        private double speed; // Szélsebesség m/s-ban

        // Getterek és setterek
        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }
}