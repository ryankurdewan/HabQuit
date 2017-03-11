package com.aquamorph.habquit.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.aquamorph.habquit.activities.ManageHabitActivityTwo;

/**
 * Created by ryansummerlin on 3/9/17.
 */

public class ManageHabitListener implements View.OnClickListener {

    Context context;
    int habitID;

    public ManageHabitListener(Context context, int habitID) {
        this.context = context;
        this.habitID = habitID;
    }

    @Override
    public void onClick(View v) {
        Intent manageHabitTwo = new Intent(context, ManageHabitActivityTwo.class);
        manageHabitTwo.putExtra("HabitID", habitID);
        context.startActivity(manageHabitTwo);
    }
}
