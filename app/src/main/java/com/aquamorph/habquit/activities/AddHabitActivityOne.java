package com.aquamorph.habquit.activities;

/**
 * Created by Alex on 3/2/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.utils.HabitParameter;

public class AddHabitActivityOne extends AppCompatActivity {

	ImageButton addCig;
	ImageButton addBeer;
	ImageButton addSoda;
	ImageButton addSmokelessTobacco;
	Button addHabitCustom;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_habit_page_one);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setTitle(getString(R.string.add_habit_button));
		}

		addCig = (ImageButton) findViewById(R.id.add_habit_smoking);
		addBeer = (ImageButton) findViewById(R.id.add_habit_drinking);
		addSoda = (ImageButton) findViewById(R.id.add_habit_soda);
		addSmokelessTobacco = (ImageButton) findViewById(R.id.add_habit_smokeless_tobacco);
		addHabitCustom = (Button) findViewById(R.id.add_habit_custom);
		final Intent addHabitTwo = new Intent(this, AddHabitActivityTwo.class);

		addCig.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addHabitTwo.putExtra("HabitID", HabitParameter.SMOKING_ID);
				addHabitTwo.putExtra("isAddHabit", true);
				startActivity(addHabitTwo);
				finish();
			}
		});
		addBeer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addHabitTwo.putExtra("HabitID", HabitParameter.DRINKING_ID);
				addHabitTwo.putExtra("isAddHabit", true);
				startActivity(addHabitTwo);
				finish();
			}
		});
		addSoda.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addHabitTwo.putExtra("HabitID", HabitParameter.SODA_ID);
				addHabitTwo.putExtra("isAddHabit", true);
				startActivity(addHabitTwo);
				finish();
			}
		});
		addSmokelessTobacco.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addHabitTwo.putExtra("HabitID", HabitParameter.SMOKELESS_ID);
				addHabitTwo.putExtra("isAddHabit", true);
				startActivity(addHabitTwo);
				finish();
			}
		});
		addHabitCustom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addHabitTwo.putExtra("HabitID", HabitParameter.CUSTOM_ID);
				addHabitTwo.putExtra("isAddHabit", true);
				startActivity(addHabitTwo);
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
}
