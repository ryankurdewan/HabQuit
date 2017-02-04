package com.aquamorph.habquit.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.model.HabitSgk;
import com.aquamorph.habquit.utils.HabitParameter;


import java.util.List;

/**
 * Created by Shawn on 2/4/2017.
 */

public class SelectHabitsAdapter extends RecyclerView.Adapter<SelectHabitsAdapter.ViewHolder> {

    private List<HabitSgk> habitSgks;

    public SelectHabitsAdapter(List<HabitSgk> habitSgks) {
        this.habitSgks = habitSgks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.type_of_habit, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HabitSgk habitSgk = habitSgks.get(position);
        if (habitSgk != null) {
            holder.habit = habitSgk;
            holder.habitSwitch.setOnCheckedChangeListener(null);
            holder.habitSwitch.setChecked(HabitParameter.getInstance().getHabitIds().contains(habitSgk.getHabitId()));
            holder.habitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    HabitParameter habitParameter = HabitParameter.getInstance();
                    if(isChecked){
                        habitParameter.addHabit(habitSgk.getHabitId());
                    }
                    else{
                        habitParameter.removeHabit(habitSgk.getHabitId());
                    }
                }
            });
            holder.type.setText(habitSgk.getType());
        }
    }

    @Override
    public int getItemCount() {
        return habitSgks != null ? habitSgks.size() : 0;
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        HabitSgk habit;
        SwitchCompat habitSwitch;
        TextView type;

        public ViewHolder(View v) {
            super(v);
            habitSwitch = (SwitchCompat) v.findViewById(R.id.habitSwitch);
            type = (TextView) v.findViewById(R.id.type);
        }
    }
}