package com.example.macwi.auroraforecast.webservices;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macwi.auroraforecast.R;
import com.example.macwi.auroraforecast.utils.HorizontalBarChartStyle;
import com.example.macwi.auroraforecast.utils.LabelsBarChartXaxisFormatter;
import com.example.macwi.auroraforecast.utils.ValueChartFormatter;
import com.example.macwi.auroraforecast.utils.XAxisBarDataSet;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macwi on 30.12.2017.
 */

public class DownloadKPData extends AsyncTask<URL, Integer, Boolean> {

    private ArrayList<String> rawData = new ArrayList<>();

    private String currentKp;
    private String[] hourKp;
    private View view;

    public DownloadKPData(View view) {
        currentKp = "no data";
        hourKp = new String[3];
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

        int offset = 1; //for skipping data missing
        int k = 0;

        while (rawData.get(rawData.size() - offset).substring(40, 45).trim().equals("-1.00")
                || rawData.get(rawData.size() - offset - 1).substring(40, 45).trim().equals("-1.00")
                || rawData.get(rawData.size() - offset - 2).substring(40, 45).trim().equals("-1.00")) {
            k++;
            offset += k;
        }

        currentKp = rawData.get(rawData.size() - 3 - offset).substring(40, 45).trim(); //current kp

        hourKp[0] = rawData.get(rawData.size() - 2 - offset).substring(40, 45).trim();  //in 15min
        hourKp[1] = rawData.get(rawData.size() - 1 - offset).substring(40, 45).trim();  //in 30min
        hourKp[2] = rawData.get(rawData.size() - offset).substring(40, 45).trim();  //in 45min

        setData();
    }

    private void setData() {
        TextView textView = view.findViewById(R.id.kpText);
        textView.setText(currentKp);

        setKpStatusImage(currentKp);
        setKpHourChart();
    }

    private void setKpStatusImage(String kpValue) {

        ImageView kpStatus = view.findViewById(R.id.kpStatus);

        float kp = Float.parseFloat(kpValue);

        if(kp >= 0 && kp <= 2.99) {
            kpStatus.setImageResource(R.drawable.kp1);
        }else if(kp >= 3 && kp <= 3.99) {
            kpStatus.setImageResource(R.drawable.kp2);
        }else if(kp >= 4 && kp <= 5.99) {
            kpStatus.setImageResource(R.drawable.kp3);
        }else if(kp >= 6 && kp <= 6.99) {
            kpStatus.setImageResource(R.drawable.kp4);
        }else {
            kpStatus.setImageResource(R.drawable.kp5);
        }

    }

    private void setKpHourChart() {

        HorizontalBarChart chart = view.findViewById(R.id.hourKpChart);

        List<BarEntry> entries = new ArrayList<>();

        ArrayList<String> labels = new ArrayList<>();
        labels.add("45min");
        labels.add("30min");
        labels.add("15min");

        entries.add(new BarEntry(2, Float.parseFloat(hourKp[0])));
        entries.add(new BarEntry(1, Float.parseFloat(hourKp[1])));
        entries.add(new BarEntry(0, Float.parseFloat(hourKp[2])));


        XAxisBarDataSet set = new XAxisBarDataSet(entries, "NextHourKp");
        set.setColors(ContextCompat.getColor(view.getContext(), R.color.kp_green),
                ContextCompat.getColor(view.getContext(), R.color.kp_yellow),
                ContextCompat.getColor(view.getContext(), R.color.kp_orange),
                ContextCompat.getColor(view.getContext(), R.color.kp_red),
                ContextCompat.getColor(view.getContext(), R.color.kp_cyan));


        BarData data = new BarData(set);

        data.setBarWidth(0.6f);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        data.setValueFormatter(new ValueChartFormatter());

        chart.setData(data);

        HorizontalBarChartStyle chartStyle = new HorizontalBarChartStyle(chart, labels);
        chartStyle.setStyle();

    }

}

