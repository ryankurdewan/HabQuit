package com.aquamorph.habquit.utils;

import android.app.Activity;
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
    private boolean isAddHabit;

    public ManageHabitListener(Context context, int habitID, boolean isAddHabit) {
        this.context = context;
        this.habitID = habitID;
        this.isAddHabit = isAddHabit;
    }

    @Override
    public void onClick(View v) {
        Intent manageHabitTwo = new Intent(context, ManageHabitActivityTwo.class);
        manageHabitTwo.putExtra("HabitID", habitID);
        manageHabitTwo.putExtra("isAddHabit", isAddHabit);
        context.startActivity(manageHabitTwo);
	    ((Activity) context).finish();
    }
}
