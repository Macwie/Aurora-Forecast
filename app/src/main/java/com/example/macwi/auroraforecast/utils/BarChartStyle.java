package com.example.macwi.auroraforecast.utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import java.util.ArrayList;

/**
 * Created by macwi on 05.01.2018.
 */

public class BarChartStyle {


    private BarChart chart;


    public BarChartStyle(BarChart chart) {
        this.chart = chart;
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
