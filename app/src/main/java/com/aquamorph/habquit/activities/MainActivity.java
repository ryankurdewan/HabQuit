package com.aquamorph.habquit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.aquamorph.habquit.R;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                break;
                            case R.id.action_schedules:
                                break;
                            case R.id.action_music:
                                break;
                        }
                        return true;
                    }
                }
        );
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
