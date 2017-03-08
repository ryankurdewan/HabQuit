package com.aquamorph.habquit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aquamorph.habquit.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * <p></p>
 *
 * @author Christian Colglazier
 * @version 2/2/2017
 */

public class ChartsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bar_graph, container, false);
		BarChart barChart = (BarChart) view.findViewById(R.id.bar_graph);

        String[] values = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new StringDayAxisValueFormatter(values));

		ArrayList<BarEntry> barEntries = new ArrayList<>();
		barEntries.add(new BarEntry(1f, 4));
		barEntries.add(new BarEntry(2f, 6));
		barEntries.add(new BarEntry(3f, 8));
		barEntries.add(new BarEntry(4f, 2));
		barEntries.add(new BarEntry(5f, 1));
		barEntries.add(new BarEntry(6f, 3));
		barEntries.add(new BarEntry(7f, 5));
		BarDataSet barDataSet = new BarDataSet(barEntries, "Cigarettes This Week");

		BarData theData = new BarData(barDataSet);
		barChart.setData(theData);
		barChart.setDragEnabled(true);
		barChart.setTouchEnabled(true);
		barChart.setScaleEnabled(true);
		return view;
	}
}
