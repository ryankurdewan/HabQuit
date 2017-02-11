package com.aquamorph.habquit.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.model.HabitSgk;
import com.aquamorph.habquit.utils.Counter;
import com.aquamorph.habquit.utils.HabitParameter;


import java.util.List;

/**
 * Created by Shawn on 2/4/2017.
 */

public class SelectHabitsAdapter extends RecyclerView.Adapter<SelectHabitsAdapter.ViewHolder> {


    private List<HabitSgk> habitSgks;
    private boolean isGrid;

    public SelectHabitsAdapter(List<HabitSgk> habitSgks, boolean isGrid) {
        this.habitSgks = habitSgks;
        this.isGrid = isGrid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(isGrid)
        {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_habit_grid, parent, false);
            return new GridViewHolder(v);
        }
        else{
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.type_of_habit, parent, false);
            return new ListItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HabitSgk habitSgk = habitSgks.get(position);
        if (habitSgk != null) {
            holder.habit = habitSgk;
            if(!isGrid) {
                ListItemViewHolder listItemViewHolder = (ListItemViewHolder) holder;
                listItemViewHolder.habitSwitch.setOnCheckedChangeListener(null);
                listItemViewHolder.habitSwitch.setChecked(HabitParameter.getInstance().getHabitIds().contains(habitSgk.getHabitId()));
                listItemViewHolder.habitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        HabitParameter habitParameter = HabitParameter.getInstance();
                        if (isChecked) {
                            habitParameter.addHabit(habitSgk.getHabitId());
                        } else {
                            habitParameter.removeHabit(habitSgk.getHabitId());
                        }
                    }
                });
                listItemViewHolder.type.setText(habitSgk.getType());
            }
            else
            {
                final Counter counter = Counter.getInstance();
                final GridViewHolder gridViewHolder = (GridViewHolder) holder;
                gridViewHolder.habitName.setText(habitSgk.getType());
                gridViewHolder.habitCount.setText(String.valueOf(counter.getCountFor(habitSgk.getType())));
                gridViewHolder.habitCount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = counter.getCountFor(habitSgk.getType());
                        count ++;
                        counter.setCountFor(habitSgk.getType(), count);
                        SelectHabitsAdapter.this.notifyDataSetChanged();
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return habitSgks != null ? habitSgks.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        HabitSgk habit;

        public ViewHolder(View v) {
            super(v);
        }
    }

    public static class ListItemViewHolder extends ViewHolder{

        SwitchCompat habitSwitch;
        TextView type;

        public ListItemViewHolder(View v){
            super(v);
            habitSwitch = (SwitchCompat) v.findViewById(R.id.habitSwitch);
            type = (TextView) v.findViewById(R.id.type);
        }
    }

    public static class GridViewHolder extends ViewHolder{
        TextView habitName;
        TextView habitCount;
        public GridViewHolder(View v)
        {
            super(v);
            habitName = (TextView)v.findViewById(R.id.habitName);
            habitCount = (TextView)v.findViewById(R.id.habitCount);
        }
    }
}