package com.aquamorph.habquit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.ManageHabitsAdapter;
import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.provider.HabitServiceProvider;
import com.aquamorph.habquit.service.HabitService;
import com.aquamorph.habquit.utils.HabitParameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryansummerlin on 3/9/17.
 */

public class ManageHabitActivityOne extends AppCompatActivity implements HabitService.OnHabitSgkListener {

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_habit_page_one);

        recyclerView = (RecyclerView) findViewById(R.id.manage_recycler_view);
        getHabits();
    }

    private void getHabits() {
        HabitServiceProvider provider = new HabitServiceProvider();
        provider.getHabits(this);
    }

    @Override
    public void onSuccess(List<Habit> habits) {
        List<Habit> currentlyTracked = new ArrayList<>();
        HabitParameter habitParameter = HabitParameter.getInstance();

        for (Habit habit : habits) {
            if (habitParameter.getHabitIds().contains(habit.getHabitId())) {
                currentlyTracked.add(habit);
            }
        }

        ManageHabitsAdapter adapter = new ManageHabitsAdapter(currentlyTracked);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onError() {
    }


}
