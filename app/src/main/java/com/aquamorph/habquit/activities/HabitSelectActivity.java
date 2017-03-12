package com.aquamorph.habquit.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.SelectHabitsAdapter;
import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.provider.HabitServiceProvider;
import com.aquamorph.habquit.service.HabitService;

import java.util.List;

public class HabitSelectActivity extends AppCompatActivity implements HabitService.OnHabitSgkListener {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_habits);

        recyclerView = (RecyclerView) findViewById(R.id.select_habits);
        getHabitSgk();

    }

    private void getHabitSgk() {
        HabitServiceProvider habitServiceProvider = new HabitServiceProvider();
        habitServiceProvider.getHabits(this);
    }

    @Override
    public void onSuccess(List<Habit> habits) {
        SelectHabitsAdapter selectHabitsAdapter = new SelectHabitsAdapter(habits,false);
        recyclerView.setAdapter(selectHabitsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onError() {

    }
}
