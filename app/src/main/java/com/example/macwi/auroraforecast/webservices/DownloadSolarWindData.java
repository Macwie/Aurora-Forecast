package com.example.macwi.auroraforecast.webservices;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.example.macwi.auroraforecast.R;
import com.example.macwi.auroraforecast.utils.HorizontalBarChartStyle;
import com.example.macwi.auroraforecast.utils.ValueChartFormatter;
import com.github.anastr.speedviewlib.TubeSpeedometer;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macwi on 01.01.2018.
 */

public class DownloadSolarWindData extends AsyncTask<URL, Integer, Boolean> {

    private ArrayList<String> rawData = new ArrayList<>();

    private String density, speed;
    private View view;

    public DownloadSolarWindData(View view) {
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

        while (!rawData.get(rawData.size() - offset).substring(35, 37).trim().equals("0")) {
            k++;
            offset += k;
        }

        density = rawData.get(rawData.size()-offset).substring(45, 50).trim();
        speed = rawData.get(rawData.size()-offset).substring(50, 60).trim();

        setData();
    }

    private void setData() {

        TubeSpeedometer windGauge = view.findViewById(R.id.windGauge);
        TubeSpeedometer densityGauge = view.findViewById(R.id.densityGauge);
        windGauge.setWithTremble(false);
        densityGauge.setWithTremble(false);

        windGauge.setMaxSpeed(1000);
        windGauge.setMinSpeed(0);

        densityGauge.setMaxSpeed(20);
        densityGauge.setMinSpeed(0);

        windGauge.speedTo(Float.parseFloat(speed));
        densityGauge.speedTo(Float.parseFloat(density));

    }
}
