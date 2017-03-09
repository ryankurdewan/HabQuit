package com.aquamorph.habquit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.BottomNavPagerAdapter;
import com.aquamorph.habquit.fragments.AssistantFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

	private String TAG = "MainActivity";
	private ViewPager viewPager;
	private GoogleApiClient googleApiClient;
	public static boolean isTesting = false;

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

		checkLogin();
	}

	private void checkLogin() {
		GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions
				.DEFAULT_SIGN_IN).requestEmail().build();
		googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage
				(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
		OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
		if (opr.isDone()) {
			Log.d(TAG, "Got cached sign-in");
		} else {
			Log.d(TAG, "Not signed in");
			if (!isTesting)
				openLogin();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_drop_down_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.logout:
				Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
					@Override
					public void onResult(@NonNull Status status) {
						Intent i = getBaseContext().getPackageManager()
								.getLaunchIntentForPackage( getBaseContext().getPackageName() );
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
						finish();
					}
				});
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
			case R.id.manage_habit:
				openManageHabitActivityTwo();
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

	public void openHabitSelectActivity() {
		startActivity(new Intent(this, HabitSelectActivity.class));
	}

	public void openManageHabitActivityTwo() {
		startActivity(new Intent(this, ManageHabitActivityTwo.class));
	}

	public void openAddHabitActivity() {
		startActivity(new Intent(this, AddHabitActivityOne.class));
	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

	}
}
