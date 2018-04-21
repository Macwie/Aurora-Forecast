package com.example.macwi.auroraforecast.utils.weather;

/**
 * Created by macwi on 21.01.2018.
 */

public class Wind {

    private float speed;
    private float deg;

    public Wind(float speed, float deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDeg() {
        return deg;
    }

    public void setDeg(float deg) {
        this.deg = deg;
    }
}
