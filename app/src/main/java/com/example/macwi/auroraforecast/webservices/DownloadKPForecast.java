package com.example.macwi.auroraforecast.webservices;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.example.macwi.auroraforecast.R;
import com.example.macwi.auroraforecast.utils.BarChartStyle;
import com.example.macwi.auroraforecast.utils.DateXAxisValueFormatter;
import com.example.macwi.auroraforecast.utils.FloatToIntValueFormatter;
import com.example.macwi.auroraforecast.utils.KPForecast;
import com.example.macwi.auroraforecast.utils.ValueChartFormatter;
import com.example.macwi.auroraforecast.utils.XAxisBarDataSet;
import com.example.macwi.auroraforecast.utils.YAxisBarDataSet;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by macwi on 01.01.2018.
 */

public class DownloadKPForecast extends AsyncTask<URL, Integer, Boolean> {

    private ArrayList<String> rawData = new ArrayList<>();

    private KPForecast[] kpForecast;
    private View view;

    public DownloadKPForecast(View view) {
        kpForecast = new KPForecast[27];
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


        for(int i=11; i<rawData.size()-1;i++) {

            kpForecast[i-11] = new KPForecast(rawData.get(i).substring(1, 15).trim(), rawData.get(i).substring(40, 42).trim()); //first date, second kp

        }

        setData();
    }

    private void setData() {

        BarChart chart = view.findViewById(R.id.KpForecastChart);

        List<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i=0; i<14;i++) {
            entries.add(new BarEntry(i, Float.parseFloat(kpForecast[i].getKp())));
        }

        YAxisBarDataSet set = new YAxisBarDataSet(entries, "KP Forecast");
        set.setColors(ContextCompat.getColor(view.getContext(), R.color.kp_green),
                ContextCompat.getColor(view.getContext(), R.color.kp_yellow),
                ContextCompat.getColor(view.getContext(), R.color.kp_orange),
                ContextCompat.getColor(view.getContext(), R.color.kp_red),
                ContextCompat.getColor(view.getContext(), R.color.kp_cyan));

        BarData barData = new BarData(set);
        barData.setValueTextColor(Color.WHITE);
        barData.setValueTextSize(10f);
        barData.setValueFormatter(new FloatToIntValueFormatter());
        chart.setData(barData);
        chart.getXAxis().setValueFormatter(new DateXAxisValueFormatter());
        chart.animateXY(1500, 1500);

        BarChartStyle barChartStyle = new BarChartStyle(chart);
        barChartStyle.setStyle();


    }
}
