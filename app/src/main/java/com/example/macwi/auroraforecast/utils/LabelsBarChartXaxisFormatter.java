package com.example.macwi.auroraforecast.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

/**
 * Created by macwi on 04.01.2018.
 */

public class LabelsBarChartXaxisFormatter implements IAxisValueFormatter {

    private ArrayList<String> mValues;

    public LabelsBarChartXaxisFormatter(ArrayList<String> values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        int val = (int) value;
        String label = "";
        if (val >= 0 && val < mValues.size()) {
            label = mValues.get(val);
        } else {
            label = "";
        }
        return label;
    }
}