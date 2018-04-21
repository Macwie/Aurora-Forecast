package com.example.macwi.auroraforecast.utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import java.util.ArrayList;

/**
 * Created by macwi on 04.01.2018.
 */

public class HorizontalBarChartStyle {

    HorizontalBarChart chart;
    ArrayList<String> labels;


    public HorizontalBarChartStyle (HorizontalBarChart chart, ArrayList labels) {
        this.chart = chart;
        this.labels = labels;
    }

    public void setStyle() {
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        chart.getLegend().setEnabled(false);
        chart.setTouchEnabled(false);

        XAxis xl = chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        LabelsBarChartXaxisFormatter xaxisFormatter = new LabelsBarChartXaxisFormatter(labels);
        xl.setValueFormatter(xaxisFormatter);
        xl.setGranularity(1f);
        xl.setDrawAxisLine(false);
        xl.setTextColor(Color.WHITE);
        xl.setTextSize(10f);

        YAxis yl = chart.getAxisLeft();
        yl.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yl.setDrawGridLines(false);
        yl.setEnabled(false);
        yl.setAxisMinimum(0f);
        yl.setAxisMaximum(9);

        YAxis yr = chart.getAxisRight();
        yr.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);
        yr.setEnabled(false);
        yr.setDrawLabels(false);
        chart.invalidate();
    }


}
