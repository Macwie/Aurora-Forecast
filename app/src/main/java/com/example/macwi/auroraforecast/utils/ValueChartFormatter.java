package com.example.macwi.auroraforecast.utils;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by macwi on 04.01.2018.
 */

public class ValueChartFormatter implements IValueFormatter {

    private DecimalFormat mFormat;

    public ValueChartFormatter() {
        mFormat = new DecimalFormat("#0.00");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value);
    }
}