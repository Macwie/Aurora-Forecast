package com.example.macwi.auroraforecast.webservices;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.macwi.auroraforecast.R;
import com.example.macwi.auroraforecast.utils.LineChartCubicStyle;
import com.example.macwi.auroraforecast.utils.Magnetometer;
import com.github.anastr.speedviewlib.ProgressiveGauge;
import com.github.anastr.speedviewlib.TubeSpeedometer;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macwi on 01.01.2018.
 */

public class DownloadMagnetometerData extends AsyncTask<URL, Integer, Boolean> {

    private ArrayList<String> rawData = new ArrayList<>();

    private Float Bz, Bt;
    private View view;

    public DownloadMagnetometerData(View view) {
        this.view = view;
    }

    @Override
    protected Boolean doInBackground(URL... urls) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(urls[0].openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                rawData.add(str);
            }
            in.close();
        } catch (IOException e) {
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {

    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result)
            parseRawData();
        else
            System.out.println("Data download ERROR");
    }

    private void parseRawData() {

        int offset = 1; //for skipping data missing/corrupted
        int k = 0;

        while (!rawData.get(rawData.size()- offset).substring(35, 37).trim().equals("0")) {
            k++;
            offset += k;
        }

                Bz = Float.parseFloat(rawData.get(rawData.size() - offset).substring(55, 65).trim());
                Bt = Float.parseFloat(rawData.get(rawData.size() - offset).substring(65, 70).trim());

        setData();
    }

    private void setData() {

        TubeSpeedometer bzGauge = view.findViewById(R.id.bzGauge);
        TubeSpeedometer btGauge = view.findViewById(R.id.btGauge);
        bzGauge.setWithTremble(false);
        btGauge.setWithTremble(false);

        bzGauge.setMaxSpeed(20);
        bzGauge.setMinSpeed(-20);

        btGauge.setMaxSpeed(20);
        btGauge.setMinSpeed(0);

        bzGauge.speedTo(Bz);
        btGauge.speedTo(Bt);

        bzGauge.setLowSpeedColor(ContextCompat.getColor(view.getContext(), R.color.kp_red));
        bzGauge.setMediumSpeedColor(ContextCompat.getColor(view.getContext(), R.color.kp_yellow));
        bzGauge.setHighSpeedColor(ContextCompat.getColor(view.getContext(), R.color.kp_green));


    }

}
