package com.aquamorph.habquit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.SelectHabitsAdapter;
import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.provider.HabitServiceProvider;
import com.aquamorph.habquit.service.HabitService;
import com.aquamorph.habquit.utils.HabitParameter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p></p>
 *
 * @author Christian Colglazier
 * @version 2/2/2017
 */

public class HabitFragment extends Fragment implements HabitService.OnHabitSgkListener {

	private RecyclerView recyclerView;
	@BindView(R.id.empty)
	View empty;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_habit, container, false);
		ButterKnife.bind(this, view);
		recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		getHabit();
	}


	private void getHabit() {
		HabitServiceProvider habitServiceProvider = new HabitServiceProvider();
		habitServiceProvider.getHabits(this);
	}

	@Override
	public void onSuccess(List<Habit> habits) {
		List<Habit> filtered = new ArrayList<>();
		HabitParameter habitParameter = HabitParameter.getInstance();
		for (Habit h : habits) {
			if (habitParameter.getHabitIds().contains(h.getHabitId()))
				filtered.add(h);
		}

		empty.setVisibility(filtered.size() == 0 ? View.VISIBLE : View.GONE);

		SelectHabitsAdapter selectHabitsAdapter = new SelectHabitsAdapter(filtered, true);
		recyclerView.setAdapter(selectHabitsAdapter);

	}

	@Override
	public void onError() {
	}
}
