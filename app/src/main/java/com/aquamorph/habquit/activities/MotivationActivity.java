package com.aquamorph.habquit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.MotivationAdapter;
import com.aquamorph.habquit.model.Motivation;
import com.aquamorph.habquit.provider.MotivationServiceProvider;
import com.aquamorph.habquit.service.MotivationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawn on 2/7/2017.
 */

public class MotivationActivity extends AppCompatActivity implements MotivationService.OnMotivationListener {
	private String TAG = "MotivationActivity";
	private RecyclerView recyclerView;
	private List<Motivation> motivations = new ArrayList<>();
	private MotivationAdapter motivationAdapter;

	@Override
	protected void onCreate(Bundle savedInstancestate) {
		super.onCreate(savedInstancestate);
		setContentView(R.layout.motivations);

		recyclerView = (RecyclerView) findViewById(R.id.motivations);
		motivationAdapter = new MotivationAdapter(motivations);
		LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
		llm.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setAdapter(motivationAdapter);
		recyclerView.setLayoutManager(llm);
		getMotivation();
	}

	/**
	 * Calls achievementServiceProvider passing reference of self (implements OnMotivationListener)
	 * to communicate properly with service
	 */

	private void getMotivation() {
		MotivationServiceProvider motivationServiceProvider = new MotivationServiceProvider();
		motivationServiceProvider.getMotivations(this);
	}

	/**
	 * this function is called when the motivationServiceProvider successfully makes a service call
	 * to get a list of motivations from remote server (http status 200)
	 *
	 * @param motivation
	 */




	@Override
	public void onSuccess(Motivation motivation) {
		motivations.add(motivation);
		motivationAdapter.notifyDataSetChanged();
	}

	@Override
	public void onError() {

	}
}