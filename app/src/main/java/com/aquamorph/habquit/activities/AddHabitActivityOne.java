package com.aquamorph.habquit.activities;

/**
 * Created by Alex on 3/2/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.aquamorph.habquit.R;

public class AddHabitActivityOne extends AppCompatActivity {
//    public class AddHabitActivityOne extends AppCompatActivity implements View.OnClickListener {

    final int SMOKING_ID = 1;
    final int SMOKELESS_ID = 2;
    final int DRINKING_ID = 3;
    final int SODA_ID = 4;
    final int CUSTOM_ID = -1;

    ImageButton addCig;
    ImageButton addBeer;
    ImageButton addSoda;
    ImageButton addSmokelessTobacco;
    Button addHabitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit_page_one);

        addCig  = (ImageButton) findViewById(R.id.add_habit_smoking);
        addBeer = (ImageButton) findViewById(R.id.add_habit_drinking);
        addSoda = (ImageButton) findViewById(R.id.add_habit_soda);
        addSmokelessTobacco = (ImageButton) findViewById(R.id.add_habit_smokeless_tobacco);
        addHabitButton = (Button) findViewById(R.id.add_habit_button);

//        addCig.setOnClickListener(this);
//        addBeer.setOnClickListener(this);
//        addSoda.setOnClickListener(this);
//        addSmokelessTobacco.setOnClickListener(this);
//        addHabitButton.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View view) {
//        Intent addHabitTwo = new Intent(this, AddHabitActivityTwo.class);
//        switch (view.getId()) {
//            case R.id.add_habit_smoking:
//                addHabitTwo.putExtra("HabitID", SMOKING_ID);
//                break;
//
//            case R.id.add_habit_drinking:
//                addHabitTwo.putExtra("HabitID", DRINKING_ID);
//                break;
//
//            case R.id.add_habit_soda:
//                addHabitTwo.putExtra("HabitID", SODA_ID);
//                break;
//
//            case R.id.add_habit_smokeless_tobacco:
//                addHabitTwo.putExtra("HabitID", SMOKELESS_ID);
//                break;
//
//            case R.id.add_habit_button:
//                addHabitTwo.putExtra("HabitID", CUSTOM_ID);
//                break;
//
//            default: break;
//        }
//        startActivity(addHabitTwo);
//    }
}
