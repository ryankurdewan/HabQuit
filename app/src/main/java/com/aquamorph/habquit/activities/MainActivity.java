package com.aquamorph.habquit.activities;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		CardView cigarette = (CardView) findViewById(R.id.rabbit1);
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
		cigaretteCount--;
		return beginText + cigaretteCount;
	}

	/**
	 * openLogin() starts login activity
	 */
	public void openLogin() {
		startActivity(new Intent(this, Login.class));
	}
}
