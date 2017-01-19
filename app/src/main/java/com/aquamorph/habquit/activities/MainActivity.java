package com.aquamorph.habquit.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aquamorph.habquit.R;

public class MainActivity extends AppCompatActivity {

	private static int cigaretteCount = 0;
	private String beginText = "";
    private TextView textFavorites;
    private TextView textSchedules;
    private TextView textMusic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        textFavorites = (TextView) findViewById(R.id.text_favorites);
        textSchedules = (TextView) findViewById(R.id.text_schedules);
        textMusic = (TextView) findViewById(R.id.text_music);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                textFavorites.setVisibility(View.VISIBLE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.GONE);
                                break;
                            case R.id.action_schedules:
                                textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.VISIBLE);
                                textMusic.setVisibility(View.GONE);
                                break;
                            case R.id.action_music:
                                textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);
                                break;
                        }
                        return true;
                    }
                }
        );

		CardView cigarette = (CardView) findViewById(R.id.habit1);
		final TextView smokingCountText = (TextView) findViewById(R.id.textView2);

		beginText = getResources().getString(R.string.count_text) + " ";
		smokingCountText.setText(beginText.concat(Integer.toString(cigaretteCount)));

		cigarette.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				smokingCountText.setText(incCount(beginText));
			}
		});

		cigarette.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				smokingCountText.setText(decCount(beginText));
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
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * incCount() increased the habit count
	 *
	 * @param beginText
	 * @return count text to be displayed
	 */
	public static String incCount(String beginText) {
		cigaretteCount++;
		return beginText + cigaretteCount;
	}

	/**
	 * decCount() decreases the habit count
	 *
	 * @param beginText
	 * @return count text to be displayed
	 */
	public static String decCount(String beginText) {
        if (cigaretteCount > 0)
        {
            cigaretteCount--;
        }
		return beginText + cigaretteCount;
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
		  startActivity(new Intent(this, Achievements.class));
	  }
}
