package com.aquamorph.habquit.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.app.Dialog;


import com.aquamorph.habquit.R;
import com.aquamorph.habquit.utils.HabitParameter;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ryansummerlin on 2/16/17.
 */

public class ManageHabitActivityTwo extends AppCompatActivity {

	private String TAG = "ManageHabitActivityTwo";
	final int CUSTOM_ID = -1;
	private boolean isAddHabit;
	private String habitNameText;
	private SharedPreferences sharedPreferences;
    private int manage_goal_year;
    private  int manage_goal_day;
    private int manage_goal_month;
	EditText habitName;
	EditText habitPrice;
	EditText currPerDay;
	EditText goalPerDay;
	EditText goalDate;
	CheckBox enableHints;
	TextInputLayout addHabitNameWrapper;
	TextInputLayout addHabitGoalPerDayWrapper;
	TextInputLayout addHabitGoalDateWrapper;
	TextInputLayout addHabitPriceWrapper;
	TextInputLayout addHabitCurrPerDayWrapper;
	Button addHabitButton;
	Button saveHabitButton;
	int startUpHabitID;
	DatePicker date_picker;
    static final int DATE_DIALOG_ID = 0;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_habit_page_two);

		startUpHabitID = getIntent().getExtras().getInt("HabitID");
		isAddHabit = getIntent().getBooleanExtra("isAddHabit", true);
		habitNameText = getIntent().getStringExtra("habitName");

		habitName = (EditText) findViewById(R.id.addHabitName);
		habitPrice = (EditText) findViewById(R.id.add_habit_price);
		currPerDay = (EditText) findViewById(R.id.add_habit_curr_per_day);
		goalPerDay = (EditText) findViewById(R.id.add_habit_goal_per_day);
		goalDate = (EditText) findViewById(R.id.add_habit_goal_date);
		enableHints = (CheckBox) findViewById(R.id.add_habit_tips);
		addHabitButton = (Button) findViewById(R.id.add_habit_button);
		saveHabitButton = (Button) findViewById(R.id.add_habit_save_button);
		date_picker = (DatePicker) findViewById(R.id.add_habit_calendar);

		sharedPreferences = PreferenceManager.getDefaultSharedPreferences
				(getApplicationContext());

		habitName.setText(habitNameText);


		goalDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				date_picker.setVisibility(View.VISIBLE);
                Calendar c = Calendar.getInstance();
				date_picker.init(manage_goal_year, manage_goal_month, manage_goal_day, null);

				//Set minimum date to current day
				date_picker.setMinDate(c.getTimeInMillis());
                manage_goal_year  = c.get(Calendar.YEAR);
                manage_goal_month = c.get(Calendar.MONTH);
                manage_goal_day   = c.get(Calendar.DAY_OF_MONTH);

			}
		});
        /*TODO
        1) store dates
        2) set datePicker to invisible after date is selected
         */


		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			if (isAddHabit)
				getSupportActionBar().setTitle(getString(R.string.add_habit_button));
			else {
				getSupportActionBar().setTitle(getString(R.string.manage));
				habitName.setText(sharedPreferences.getString("habitName" + startUpHabitID, ""));
				habitPrice.setText(String.format(Locale.getDefault(), "%f", sharedPreferences
						.getFloat("habitPrice" + startUpHabitID, 0.0f)));
				currPerDay.setText(String.format(Locale.getDefault(), "%s", sharedPreferences
						.getInt("currPerDay" + startUpHabitID, 0)));
				goalPerDay.setText(String.format(Locale.getDefault(), "%s", sharedPreferences
						.getInt("goalPerDay" + startUpHabitID, 0)));
				goalDate.setText(sharedPreferences.getString("goalDate" + startUpHabitID, ""));
				enableHints.setChecked(sharedPreferences.getBoolean("enableHints" +
						startUpHabitID, true));
				saveHabitButton.setVisibility(View.VISIBLE);
			}
		}
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
		saveHabitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				saveHabitSettings();
				finish();
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
					saveHabitSettings();
			}
		} else {
			habitParameter.removeHabit(startUpHabitID);
			finish();
		}
	}


	private void saveHabitSettings() {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("habitName" + startUpHabitID, habitName.getText().toString());
		editor.putFloat("habitPrice" + startUpHabitID, Float.valueOf(habitPrice.getText()
				.toString()));
		editor.putInt("currPerDay" + startUpHabitID, Integer.valueOf(currPerDay.getText()
				.toString()));
		editor.putInt("goalPerDay" + startUpHabitID, Integer.valueOf(goalPerDay.getText()
				.toString()));
		editor.putString("goalDate" + startUpHabitID, goalDate.getText().toString());
		editor.putBoolean("enableHints" + startUpHabitID, enableHints.isChecked());
		editor.apply();
		finish();
	}
	public void setDate(){
		final Calendar c = Calendar.getInstance();
	}

}
