package com.example.macwi.auroraforecast.utils;

/**
 * Created by macwi on 05.01.2018.
 */

public class Magnetometer {

    private Float Bz;
    private Float Bt;

    public Magnetometer(Float Bz, Float Bt) {
        this.Bz = Bz;
        this.Bt = Bt;
    }

    public Float getBz() {
        return Bz;
    }

    public void setBz(Float bz) {
        Bz = bz;
    }

    public Float getBt() {
        return Bt;
    }

    public void setBt(Float bt) {
        Bt = bt;
    }
}
