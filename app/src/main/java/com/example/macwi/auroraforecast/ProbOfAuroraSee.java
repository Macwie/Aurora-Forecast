package com.example.macwi.auroraforecast;


import android.app.Activity;
import android.location.Location;
import android.view.View;
import android.widget.TextView;

import com.github.anastr.speedviewlib.TubeSpeedometer;

/**
 * Created by macwi on 07.01.2018.
 */

public class ProbOfAuroraSee {

    private int probability = 0;


    public int getProbability(Location location, View view) {

        Double temp = location.getLatitude();
        Integer latitude = temp.intValue();

        TubeSpeedometer bzGauge = view.findViewById(R.id.bzGauge);
        float bz = bzGauge.getSpeed();

        TextView current_kp = view.findViewById(R.id.kpText);
        float kp = Float.parseFloat(current_kp.getText().toString());

        TubeSpeedometer windGauge = view.findViewById(R.id.windGauge);
        float wind = windGauge.getSpeed();

        TubeSpeedometer densityGauge = view.findViewById(R.id.densityGauge);
        float density = densityGauge.getSpeed();

        TubeSpeedometer btGauge = view.findViewById(R.id.btGauge);
        float bt = btGauge.getSpeed();

        if(bz >= 0){
            return 0;
        }

        if(latitude >= 72) {
            probability+=70;
        }else if(latitude >= 70 && kp >= 1) {
            probability+=70;
        }else if(latitude >= 66 && kp >= 2) {
            probability+=70;
        }else if(latitude >= 63 && kp >= 3) {
            probability += 70;
        }else if(latitude >= 61 && kp >= 4) {
            probability += 70;
        }else if(latitude >= 58 && kp >= 5) {
            probability += 70;
        }else if(latitude >= 55 && kp >= 6) {
            probability += 70;
        }else if(latitude >= 53 && kp >= 7) {
            probability += 70;
        }else if(latitude >= 50 && kp >= 8) {
            probability += 70;
        }else if(latitude >= 47 && kp >= 9) {
            probability += 70;
        }

        if(probability == 0) {
            return 0;
        }

        if(bz <= -15) {
            probability+=20;
        }else if(bz <= -10) {
            probability+=15;
        }else if(bz < 0) {
            probability+=10;
        }

        if(bt >= 5) {
            probability+=3;
        }

        if(wind >=350){
            probability+=1;
        }else if(wind >= 500){
            probability+=2;
        }else if(wind >=700) {
            probability+=3;
        }

        if(density >=4){
            probability+=1;
        }else if(density >= 10){
            probability+=2;
        }else if(density >=15) {
            probability+=3;
        }

        return  probability;

    }

}
