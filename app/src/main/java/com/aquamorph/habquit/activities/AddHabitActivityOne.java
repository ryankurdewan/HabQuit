package com.aquamorph.habquit.activities;

/**
 * Created by Alex on 3/2/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;

import com.aquamorph.habquit.R;

public class AddHabitActivityOne extends AppCompatActivity {

    ImageButton addCig;
    ImageButton addSmokelessTobacco;
    ImageButton addBeer;
    ImageButton addSoda;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit_page_one);

        // TODO: Link button to add_habit_page_two to complete habit registration

    }
}
