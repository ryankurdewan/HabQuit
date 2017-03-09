package com.aquamorph.habquit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.ManageHabitsAdapter;
import com.aquamorph.habquit.model.HabitSgk;
import com.aquamorph.habquit.provider.HabitSgkServiceProvider;
import com.aquamorph.habquit.service.HabitSgkService;
import com.aquamorph.habquit.utils.HabitParameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryansummerlin on 3/9/17.
 */

public class ManageHabitActivityOne extends AppCompatActivity implements HabitSgkService.OnHabitSgkListener {

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_habit_page_one);

        recyclerView = (RecyclerView) findViewById(R.id.manage_recycler_view);
        getHabits();
    }

    private void getHabits() {
        HabitSgkServiceProvider provider = new HabitSgkServiceProvider();
        provider.getHabitSgks(this);
    }

    @Override
    public void onSuccess(List<HabitSgk> habitSgks) {
        List<HabitSgk> currentlyTracked = new ArrayList<>();
        HabitParameter habitParameter = HabitParameter.getInstance();

        for (HabitSgk habit : habitSgks) {
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
