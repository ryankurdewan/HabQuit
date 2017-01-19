package com.aquamorph.habquit.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.aquamorph.habquit.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by JuiceBox1 on 1/18/2017.
 */

public class GraphDisplay extends AppCompatActivity {
    BarChart barChart;

    protected void onCreate  (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_display);

        barChart = (BarChart) findViewById(R.id.graphDisplay);

        ArrayList<BarEntry>  barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(3f, 0));
        barEntries.add(new BarEntry(4f, 1));
        barEntries.add(new BarEntry(7f, 2));
        barEntries.add(new BarEntry(0f, 3));
        barEntries.add(new BarEntry(2f, 4));
        barEntries.add(new BarEntry(1f, 5));
        barEntries.add(new BarEntry(0f, 6));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        ArrayList<String> dates = new ArrayList<>();
        dates.add("Sunday");
        dates.add("Monday");
        dates.add("Tuesday");
        dates.add("Wednesday");
        dates.add("Thursday");
        dates.add("Friday");
        dates.add("Saturday");

        BarData theData = new BarData(barDataSet);
        barChart.setData(theData);

        barChart.setDragEnabled(true);
        barChart.setTouchEnabled(true);
        barChart.setScaleEnabled(true);







    }

}
