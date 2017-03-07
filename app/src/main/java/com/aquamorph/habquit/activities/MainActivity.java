package com.aquamorph.habquit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.BottomNavPagerAdapter;
import com.aquamorph.habquit.fragments.AssistantFragment;

public class MainActivity extends AppCompatActivity {

	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		BottomNavigationView bottomNavigationView = (BottomNavigationView)
				findViewById(R.id.bottom_navigation);

		// Creates fragments and allows switching between them
		viewPager = (ViewPager) findViewById(R.id.fragment);
		BottomNavPagerAdapter bottomNavPagerAdapter =
				new BottomNavPagerAdapter(getSupportFragmentManager());
		viewPager.setOffscreenPageLimit(bottomNavPagerAdapter.getCount());
		viewPager.setAdapter(bottomNavPagerAdapter);
		// Handles bottom navigation click
		bottomNavigationView.setOnNavigationItemSelectedListener(
				new BottomNavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(@NonNull MenuItem item) {
						switch (item.getItemId()) {
							case R.id.habits_bottom_menu:
								viewPager.setCurrentItem(0);
								break;
							case R.id.charts_bottom_menu:
								viewPager.setCurrentItem(1);
								break;
							case R.id.savings_bottom_menu:
								viewPager.setCurrentItem(2);
								break;
						}
						return true;
					}
				}

		);
		AssistantFragment.sendMessage("Hello, worthless addict human!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_drop_down_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.login:
				openLogin();
				break;
			case R.id.achievements:
				openAchievement();
				break;
			case R.id.motivations:
				openMotivation();
				break;
			case R.id.graphDisplay:
				openGraphDisplay();
				break;
			case R.id.select_habits:
				openHabitSelectActivity();
				break;
            case R.id.add_habit:
                openAddHabitActivity();
                break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * openLogin() starts login activity
	 */
	public void openLogin() {
		startActivity(new Intent(this, LoginActivity.class));
	}


	/**
	 * Takes user to achievement page
	 */
	public void openAchievement() {
		startActivity(new Intent(this, AchievementsActivity.class));
	}

	/**
	 * Takes user to motivation page
	 */
	public void openMotivation() {
		startActivity(new Intent(this, MotivationActivity.class));
	}

	public void openGraphDisplay() {
		startActivity(new Intent(this, GraphDisplayActivity.class));
	}

	public void openHabitSelectActivity() {startActivity(new Intent(this, HabitSelectActivity.class));}

	public void openAddHabitActivity() {
        startActivity(new Intent(this, AddHabitActivityTwo.class));
    }
}
