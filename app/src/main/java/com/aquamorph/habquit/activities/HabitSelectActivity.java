package com.aquamorph.habquit.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.SelectHabitsAdapter;
import com.aquamorph.habquit.model.HabitSgk;
import com.aquamorph.habquit.provider.HabitSgkServiceProvider;
import com.aquamorph.habquit.service.HabitSgkService;

import java.util.List;

public class HabitSelectActivity extends AppCompatActivity implements HabitSgkService.OnHabitSgkListener {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_habits);

        recyclerView = (RecyclerView) findViewById(R.id.select_habits);
        getHabitSgk();

    }

    private void getHabitSgk() {
        HabitSgkServiceProvider habitSgkServiceProvider = new HabitSgkServiceProvider();
        habitSgkServiceProvider.getHabitSgks(this);
    }

    @Override
    public void onSuccess(List<HabitSgk> habitSgks) {
        SelectHabitsAdapter selectHabitsAdapter = new SelectHabitsAdapter(habitSgks,false);
        recyclerView.setAdapter(selectHabitsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onError() {

    }
}
