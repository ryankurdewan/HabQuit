package com.aquamorph.habquit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aquamorph.habquit.R;
import com.aquamorph.habquit.model.DailyCountHabit;
import com.aquamorph.habquit.model.DailyCounts;
import com.aquamorph.habquit.model.StatAvg;
import com.aquamorph.habquit.provider.DailyHabitCountServiceProvider;
import com.aquamorph.habquit.provider.LoginServiceProvider;
import com.aquamorph.habquit.provider.MotivationServiceProvider;
import com.aquamorph.habquit.provider.StatAvgServiceProvider;
import com.aquamorph.habquit.service.LoginService;
import com.aquamorph.habquit.service.StatAvgService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.aquamorph.habquit.service.DailyHabitCountService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author Josh Derr
 * @version 2/2/2017
 */

public class ChartsFragment extends Fragment implements StatAvgService.OnStatAvgListener, DailyHabitCountService.OnDailyHabitCountListener{

	  private BarChart barChart;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bar_graph, container, false);
		 barChart = (BarChart) view.findViewById(R.id.bar_graph);

       /* String[] values = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
		        "Saturday", "Sunday"};




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
		barChart.setScaleEnabled(true); */
		getStatAvg();
		getDailyHabitCounts();
		return view;
	}

	public void getStatAvg(){
		StatAvgServiceProvider statAvgServiceProvider = new StatAvgServiceProvider();
		statAvgServiceProvider.getStatAvg(this);
	}

	public void getDailyHabitCounts() {
		DailyHabitCountServiceProvider dailyHabitCountServiceProvider = new DailyHabitCountServiceProvider();
		dailyHabitCountServiceProvider.getDailyHabitCounts(this);

	}


	@Override
	public void onSuccess(StatAvg statAvg) {



	}

	@Override
	public void onSuccess(DailyCountHabit dailycounts) {
		ArrayList<BarEntry> barEntries = new ArrayList<>();
		if(dailycounts.getCounts() != null){
			for(int i=0;i<dailycounts.getCounts().size();i++){
				DailyCounts dc = dailycounts.getCounts().get(i);
				barEntries.add(new BarEntry((i+1)/1.0f, dc.getCount()));
			}
			BarDataSet barDataSet = new BarDataSet(barEntries, "Cigarettes This Week");
			BarData theData = new BarData(barDataSet);
			barChart.setData(theData);
		}

	}

	@Override
	public void onError() {

	}
}
