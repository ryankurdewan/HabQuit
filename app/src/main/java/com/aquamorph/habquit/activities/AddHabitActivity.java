package com.aquamorph.habquit.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    EditText habitPrice;
    EditText currentUsage;
    EditText goalUsage;
    EditText goalDate;


    Button habitButton;

    SharedPreferences preferences;
    final String PREFERENCES_FILE_NAME = "HabitPreferences";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit_page_two);

        habitName = (EditText) findViewById(R.id.add_habit_name);
        habitPrice = (EditText) findViewById(R.id.add_habit_price);
        currentUsage = (EditText) findViewById(R.id.add_habit_num_per_day);
        goalUsage = (EditText) findViewById(R.id.add_habit_goal);
        goalDate = (EditText) findViewById(R.id.add_habit_goal_date);
        habitButton = (Button) findViewById(R.id.add_habit_button);
        preferences = getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

        habitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHabit(preferences,
                         habitName.getText().toString(),
                         habitPrice.getText().toString());

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
