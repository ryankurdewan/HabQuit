package com.aquamorph.habquit.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aquamorph.habquit.R;

/**
 * Created by ryansummerlin on 2/16/17.
 */

public class AddHabitActivity extends AppCompatActivity {
    EditText habitName;
    EditText habitPricePerUnit;
    Button addHabitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);

        habitName = (EditText) findViewById(R.id.add_habit_name);
        habitPricePerUnit = (EditText) findViewById(R.id.add_habit_price);
        addHabitButton = (Button) findViewById(R.id.add_habit_button);

        addHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.printf("Added new habit with name: %s and price: %s\n",
                        habitName.getText().toString(), habitPricePerUnit.getText().toString());
            }
        });
    }
}
