package com.example.macwi.auroraforecast.utils;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.example.macwi.auroraforecast.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by macwi on 05.01.2018.
 */

public class LineChartCubicStyle {

    LineChart chart;
    LineDataSet dataSet;


    public LineChartCubicStyle(LineChart chart, LineDataSet dataSet) {
        this.chart = chart;
        this.dataSet = dataSet;
    }

    public void setStyle() {

        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getDescription().setText("");
        chart.getAxisRight().setEnabled(false);
        chart.setTouchEnabled(false);
        chart.setDrawGridBackground(false);
        chart.getXAxis().setTextColor(Color.WHITE);
        chart.getXAxis().setTextSize(10f);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setTextColor(Color.WHITE);

        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        dataSet.setDrawFilled(true);
        dataSet.setDrawValues(false);

        dataSet.setFillColor(ContextCompat.getColor(chart.getContext(),R.color.pale_green));
        dataSet.setColor(ContextCompat.getColor(chart.getContext(),R.color.pale_green));
        dataSet.setLineWidth(2);

        dataSet.setFillAlpha(55);
        dataSet.setDrawCircles(false);

    }

}
