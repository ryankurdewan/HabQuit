package com.aquamorph.habquit.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shawn on 2/4/2017.
 */
public class HabitParameter {
    private static HabitParameter ourInstance = new HabitParameter();

    private SharedPreferences sharedPreferences;

    public static HabitParameter getInstance() {
        return ourInstance;
    }



    private HabitParameter() {

    }

    public void init(Context context){

    }
}
