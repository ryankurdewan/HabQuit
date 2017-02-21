package com.aquamorph.habquit.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.aquamorph.habquit.R;

/**
 * Created by ryansummerlin on 2/16/17.
 */

public class AddHabitActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.add_habit);
    }
}
