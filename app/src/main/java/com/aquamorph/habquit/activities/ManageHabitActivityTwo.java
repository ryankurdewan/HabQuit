package com.aquamorph.habquit.activities;

/**
 * Created by Alex on 3/8/2017.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Button;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.utils.HabitParameter;

public class ManageHabitActivityTwo extends AppCompatActivity{

    TextView habitName;
    EditText newGoalNumPerDay;
    EditText newGoalDate;
    Switch toggleTips;
    Button deleteHabit;

    int startUpHabitID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_habit_page_two);

        startUpHabitID = getIntent().getExtras().getInt("HabitID");
        habitName = (TextView) findViewById(R.id.manage_habit_header);

        if (startUpHabitID != HabitParameter.CUSTOM_ID) {
            switch (startUpHabitID) {
                case HabitParameter.SMOKING_ID:
                    habitName.setText("Smoking");
                    break;
                case HabitParameter.DRINKING_ID:
                    habitName.setText("Drinking");
                    break;
                case HabitParameter.SODA_ID:
                    habitName.setText("Soda");
                    break;
                case HabitParameter.SMOKELESS_ID:
                    habitName.setText("Smokeless Tobacco");
                    break;
            }
        }
    }

}

