package com.example.macwi.auroraforecast.utils.weather;

/**
 * Created by macwi on 21.01.2018.
 */

public class Sys {

    private String country;
    private String sunrise;
    private String sunset;

    public Sys(String country, String sunrise, String sunset) {
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
