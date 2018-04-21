package com.example.macwi.auroraforecast.utils.weather;

/**
 * Created by macwi on 21.01.2018.
 */

public class Coord {

    private String lon;
    private String lat;

    public Coord(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
