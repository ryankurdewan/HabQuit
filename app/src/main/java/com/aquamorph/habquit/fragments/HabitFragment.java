package com.aquamorph.habquit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.model.Habit;

/**
 * <p></p>
 *
 * @author Christian Colglazier
 * @version 2/2/2017
 */

public class HabitFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.habit_fragment, container, false);

		CardView cigarette = (CardView) view.findViewById(R.id.habit1);
		final TextView smokingCountText = (TextView) view.findViewById(R.id.textView2);

		final Habit habit1 = new Habit("Cigarettes", view.getContext());
		final TextView habit1Name = (TextView) view.findViewById(R.id.habitName);
		habit1Name.setText(habit1.getName());
		smokingCountText.setText(habit1.getText());

		cigarette.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				habit1.incCount();
				smokingCountText.setText(habit1.getText());
			}
		});

		cigarette.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				habit1.decCount();
				smokingCountText.setText(habit1.getText());
				return true;
			}
		});

		return view;
	}
}
