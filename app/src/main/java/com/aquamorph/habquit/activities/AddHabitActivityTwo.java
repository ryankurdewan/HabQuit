package com.aquamorph.habquit.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.utils.HabitParameter;

/**
 * Created by ryansummerlin on 2/16/17.
 */

public class AddHabitActivityTwo extends AppCompatActivity {

	private String TAG = "AddHabitActivityTwo";
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

		Log.i(TAG, " " + startUpHabitID);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			if (isAddHabit)
				getSupportActionBar().setTitle(getString(R.string.add_habit_button));
			else
				getSupportActionBar().setTitle("Manage Habit");
		}

		habitName = (EditText) findViewById(R.id.addHabitName);
		habitPrice = (EditText) findViewById(R.id.add_habit_goal_per_day);
		currPerDay = (EditText) findViewById(R.id.add_habit_goal_date);
		goalPerDay = (EditText) findViewById(R.id.add_habit_price);
		goalDate = (EditText) findViewById(R.id.add_habit_curr_per_day);

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

		addHabitNameWrapper = (TextInputLayout) findViewById(R.id.add_habit_name_wrapper);
		addHabitGoalPerDayWrapper = (TextInputLayout) findViewById(R.id.add_habit_goal_per_day_wrapper);
		addHabitGoalDateWrapper = (TextInputLayout) findViewById(R.id.add_habit_goal_date_wrapper);
		addHabitPriceWrapper = (TextInputLayout) findViewById(R.id.add_habit_price_wrapper);
		addHabitCurrPerDayWrapper = (TextInputLayout) findViewById(R.id.add_habit_curr_per_day_wrapper);
		addHabitButton = (Button) findViewById(R.id.add_habit_button);

		addHabitNameWrapper.setHint(getString(R.string.add_habit_name_hint));
		addHabitGoalPerDayWrapper.setHint(getString(R.string.add_habit_goal_per_day_hint));
		addHabitGoalDateWrapper.setHint(getString(R.string.add_habit_goal_date_hint));
		addHabitPriceWrapper.setHint(getString(R.string.add_habit_price_hint));
		addHabitCurrPerDayWrapper.setHint(getString(R.string.add_habit_curr_per_day_hint));

		if (isAddHabit)
			addHabitButton.setText(getString(R.string.add_habit_button));
		else
			addHabitButton.setText(getString(R.string.delete_habit_button));

		addHabitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addActivity();
			}
		});
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void addActivity() {
		HabitParameter habitParameter = HabitParameter.getInstance();
		if (isAddHabit) {
			if (habitName.getText().toString().trim().equals(""))
				habitName.setError("Please enter a habit name");
			else if (habitPrice.getText().toString().trim().equals(""))
				habitPrice.setError("Please enter a habit price");
			else if (currPerDay.getText().toString().trim().equals(""))
				currPerDay.setError("Please enter a current number of habit usage");
			else if (goalPerDay.getText().toString().trim().equals(""))
				goalPerDay.setError("Please enter a desired number of habit usage");
			else if (goalDate.getText().toString().trim().equals(""))
				goalDate.setError("Please enter a goal date");
			else {
				if (startUpHabitID == CUSTOM_ID) {
					habitParameter.addHabit(15);
				} else
					habitParameter.addHabit(startUpHabitID);
				finish();
			}
		} else {
			habitParameter.removeHabit(startUpHabitID);
			finish();
		}
	}
}
