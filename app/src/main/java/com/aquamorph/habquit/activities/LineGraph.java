package com.aquamorph.habquit.activities;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.aquamorph.habquit.R;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;

/**
 * Created by JuiceBox1 on 1/26/2017.
 */

public class LineGraph extends GraphDisplay {
    LineChart lineGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_graph);
        // Links lineGraph to  XML id line_graph
        lineGraph = (LineChart) findViewById(R.id.line_graph);
        ArrayList<Entry> entries = new ArrayList<>();
        //Simply for testing purposes, checking what 30 day graph would look like

        for(int i = 1; i <= 30; i++) {
            entries.add(new Entry(i, (i+2)));
        }
        LineDataSet dataSet = new LineDataSet(entries, "30 Day Fap Tracker");
        LineData lineData = new LineData(dataSet);
        lineGraph.setData(lineData);
        lineGraph.setScaleEnabled(true);
        lineGraph.animateXY(2000, 2000);
    }
}
