package com.aquamorph.habquit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.model.Habit;

public class MainActivity extends AppCompatActivity {

    private TextView textHabitTab;
    private TextView textGraphTab;
    private TextView textSavingsTab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        textHabitTab = (TextView) findViewById(R.id.text_favorites);
        textGraphTab = (TextView) findViewById(R.id.text_schedules);
        textSavingsTab = (TextView) findViewById(R.id.text_music);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                textHabitTab.setVisibility(View.VISIBLE);
                                textGraphTab.setVisibility(View.GONE);
                                textSavingsTab.setVisibility(View.GONE);
                                break;
                            case R.id.action_schedules:
                                textHabitTab.setVisibility(View.GONE);
                                textGraphTab.setVisibility(View.VISIBLE);
                                textSavingsTab.setVisibility(View.GONE);
                                break;
                            case R.id.action_music:
                                textHabitTab.setVisibility(View.GONE);
                                textGraphTab.setVisibility(View.GONE);
                                textSavingsTab.setVisibility(View.VISIBLE);
                                break;
                        }
                        return true;
                    }
                }
        );

		CardView cigarette = (CardView) findViewById(R.id.habit1);
		final TextView smokingCountText = (TextView) findViewById(R.id.textView2);

		final Habit habit1 = new Habit("Cigarettes", getBaseContext());
		final TextView habit1Name = (TextView) findViewById(R.id.habitName);
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
			case R.id.motivation:
				openMotivation();
				break;
			case R.id.graphDisplay:
				openGraphDisplay();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * openLogin() starts login activity
	 */
	public void openLogin() {
		startActivity(new Intent(this, Login.class));
	}


     /**
	  * Takes user to achievement page
	  */
	  public void openAchievement () {
		  startActivity(new Intent(this, AchievementsActivity.class));
	  }

	/**
	 * Takes user to motivation page
	 */
	public void openMotivation () {
//		startActivity(new Intent(this, Motivation.class));
	}

	public void openGraphDisplay(){
		startActivity(new Intent(this, GraphDisplay.class));
	}

}
