package com.example.macwi.auroraforecast.utils;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

/**
 * Created by macwi on 21.01.2018.
 */

public class YAxisBarDataSet extends BarDataSet {
    public YAxisBarDataSet(List<BarEntry> yVals, String label) {
        super(yVals, label);
    }

    @Override
    public int getColor(int index) {
        if(getEntryForIndex(index).getY() <= 2.99)
            return mColors.get(0);
        else if(getEntryForIndex(index).getY() <= 3.99)
            return mColors.get(1);
        else if(getEntryForIndex(index).getY() <= 5.99)
            return mColors.get(2);
        else if(getEntryForIndex(index).getY() <= 6.99)
            return mColors.get(3);
        else
            return mColors.get(4);
    }

}
