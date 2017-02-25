package com.aquamorph.habquit.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aquamorph.habquit.R;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ryansummerlin on 2/16/17.
 */

public class AddHabitActivity extends AppCompatActivity {
    EditText customHabitName;
    EditText customHabitPrice;
    Button customHabitButton;

    SharedPreferences preferences;
    final String PREFERENCES_FILE_NAME = "CustomHabitPreferences";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);

        customHabitName = (EditText) findViewById(R.id.add_habit_name);
        customHabitPrice = (EditText) findViewById(R.id.add_habit_price);
        customHabitButton = (Button) findViewById(R.id.add_habit_button);
        preferences = getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

        customHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHabit(preferences,
                         customHabitName.getText().toString(),
                         customHabitPrice.getText().toString());

            }
        });
    }

    private void addHabit(SharedPreferences preferences, String name, String price) {
//        Set<String> customHabits = preferences.getStringSet("custom", null);
//
//        if (customHabits != null) {
//            customHabits.add(name + ":" + price);
//        } else {
//            // TODO: determine how to map 'custom' string to a name->price collection.
//            customHabits = new HashSet<>();
//        }
    }
}
