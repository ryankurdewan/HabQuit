package com.aquamorph.habquit.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.activities.ManageHabitActivityTwo;
import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.model.HabitSgk;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ryansummerlin on 3/9/17.
 */

public class ManageHabitsAdapter extends RecyclerView.Adapter<ManageHabitsAdapter.ManageHabitViewHolder> implements View.OnClickListener {

    List<HabitSgk> habitsToManage;

    // Creation of this class assumes the list passed is not null!
    public ManageHabitsAdapter(List<HabitSgk> habitsToManage) {
        if (habitsToManage == null) {
            System.err.println("Passed invalid parameter to constructor of ManageHabitsAdapter. " +
                    "The parameter should be a non-null list.");
            System.exit(1);
        }

        this.habitsToManage = habitsToManage;
    }

    @Override
    public ManageHabitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_habit_grid, parent, false);
        return new ManageHabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ManageHabitViewHolder holder, int position) {
        HabitSgk habit = habitsToManage.get(position);
        holder.habitName.setText(habit.getType());
        holder.habitCard.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return habitsToManage.size();
    }

    @Override
    public void onClick(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), ManageHabitActivityTwo.class));
    }

    public static class ManageHabitViewHolder extends RecyclerView.ViewHolder {
        CardView habitCard;
        TextView habitName;

        public ManageHabitViewHolder(View itemView) {
            super(itemView);
            habitCard = (CardView) itemView.findViewById(R.id.habit_cardview);
            habitName = (TextView) itemView.findViewById(R.id.habitName);
        }
    }
}
