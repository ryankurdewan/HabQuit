package com.aquamorph.habquit.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.utils.HabitParameter;

/**
 * Created by ryansummerlin on 2/16/17.
 */

public class AddHabitActivityTwo extends AppCompatActivity {

	final int CUSTOM_ID = -1;
	private boolean isAddHabit;

	EditText habitName;
	EditText habitPrice;
	EditText currPerDay;
	EditText goalPerDay;
	EditText goalDate;
	TextInputLayout addHabitNameWrapper;
	TextInputLayout addHabitGoalPerDayWrapper;
	TextInputLayout addHabitGoalDateWrapper;
	TextInputLayout addHabitPriceWrapper;
	TextInputLayout addHabitCurrPerDayWrapper;
	Button addHabitButton;

	int startUpHabitID;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_habit_page_two);

		startUpHabitID = getIntent().getExtras().getInt("HabitID");
		isAddHabit = getIntent().getBooleanExtra("isAddHabit", true);
		habitName = (EditText) findViewById(R.id.addHabitName);

		if (startUpHabitID != CUSTOM_ID) {
			habitName.setFocusable(false);
			switch (startUpHabitID) {
				case HabitParameter.SMOKING_ID:
					habitName.setText("Smoking");
					break;
				case HabitParameter.DRINKING_ID:
					habitName.setText("Drinking");
					break;
				case HabitParameter.SODA_ID:
					habitName.setText("Soda");
					break;
				case HabitParameter.SMOKELESS_ID:
					habitName.setText("Smokeless Tobacco");
					break;
			}
		}

		addHabitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addActivity();
			}
		});

		addHabitNameWrapper = (TextInputLayout) findViewById(R.id.add_habit_name_wrapper);
		addHabitGoalPerDayWrapper = (TextInputLayout) findViewById(R.id.add_habit_goal_per_day_wrapper);
		addHabitGoalDateWrapper = (TextInputLayout) findViewById(R.id.add_habit_goal_date_wrapper);
		addHabitPriceWrapper = (TextInputLayout) findViewById(R.id.add_habit_price_wrapper);
		addHabitCurrPerDayWrapper = (TextInputLayout) findViewById(R.id.add_habit_curr_per_day_wrapper);
		addHabitNameWrapper.setHint(getString(R.string.add_habit_name_hint));
		addHabitGoalPerDayWrapper.setHint(getString(R.string.add_habit_goal_per_day_hint));
		addHabitGoalDateWrapper.setHint(getString(R.string.add_habit_goal_date_hint));
		addHabitPriceWrapper.setHint(getString(R.string.add_habit_price_hint));
		addHabitCurrPerDayWrapper.setHint(getString(R.string.add_habit_curr_per_day_hint));
	}

	private void addActivity() {

	}
}
