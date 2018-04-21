package com.example.macwi.auroraforecast.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by macwi on 22.01.2018.
 */

public class DateXAxisValueFormatter implements IAxisValueFormatter {

    private ArrayList<String> dates = new ArrayList<>();


    public DateXAxisValueFormatter() {

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        String timeStamp;
        c.setTime(dt);
        dt = c.getTime();

        for (int i=0;i<14;i++) {
            c.setTime(dt);
            dt = c.getTime();
            timeStamp = new SimpleDateFormat("MM.dd").format(dt);
            dates.add(timeStamp);
            c.add(Calendar.DATE, 1);
            dt = c.getTime();
        }

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return dates.get((int)value);
    }
}
