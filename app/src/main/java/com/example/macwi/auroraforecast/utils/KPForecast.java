package com.example.macwi.auroraforecast.utils;

/**
 * Created by macwi on 01.01.2018.
 */

public class KPForecast {

    String date;
    String kp;

    public KPForecast(String date, String kp) {
        this.date = date;
        this.kp = kp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKp() {
        return kp;
    }

    public void setKp(String kp) {
        this.kp = kp;
    }
}
