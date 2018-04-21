package com.example.macwi.auroraforecast.utils.weather;

/**
 * Created by macwi on 21.01.2018.
 */

public class Main {

    private float temp;
    private float humidity;
    private float pressure;
    private float temp_min;
    private float temp_max;
    private float sea_level;
    private float grnd_leve;

    public Main(float temp, float humidity, float pressure, float temp_min, float temp_max, float sea_level, float grnd_leve) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.sea_level = sea_level;
        this.grnd_leve = grnd_leve;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public float getGrnd_leve() {
        return grnd_leve;
    }

    public void setGrnd_leve(float grnd_leve) {
        this.grnd_leve = grnd_leve;
    }

    public float getSea_level() {
        return sea_level;
    }

    public void setSea_level(float sea_level) {
        this.sea_level = sea_level;
    }
}
