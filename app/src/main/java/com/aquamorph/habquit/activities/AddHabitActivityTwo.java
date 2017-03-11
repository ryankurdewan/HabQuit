package com.aquamorph.habquit.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.provider.HabitSgkServiceProvider;
import com.aquamorph.habquit.utils.HabitParameter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ryansummerlin on 2/16/17.
 */

public class AddHabitActivityTwo extends AppCompatActivity {

    final int CUSTOM_ID = -1;

    EditText habitName;
    EditText habitPrice;
    EditText currPerDay;
    EditText goalPerDay;
    EditText goalDate;
    Button addHabitButton;

    int startUpHabitID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit_page_two);

        startUpHabitID = getIntent().getExtras().getInt("HabitID");
        habitName = (EditText) findViewById(R.id.add_habit_name);

        if (startUpHabitID != CUSTOM_ID) {
            habitName.setFocusable(false);
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
