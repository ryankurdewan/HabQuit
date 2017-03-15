package com.aquamorph.habquit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.utils.ManageHabitListener;

import java.util.List;

/**
 * Created by ryansummerlin on 3/9/17.
 */

public class ManageHabitsAdapter
		extends RecyclerView.Adapter<ManageHabitsAdapter.ManageHabitViewHolder> {

	List<Habit> habitsToManage;
	private boolean isAddHabit;

	// Creation of this class assumes the list passed is not null!
	public ManageHabitsAdapter(List<Habit> habitsToManage, boolean isAddHabit) {
		if (habitsToManage == null) {
			System.err.println("Passed invalid parameter to constructor of ManageHabitsAdapter. " +
					"The parameter should be a non-null list.");
			System.exit(1);
		}
		this.isAddHabit = isAddHabit;
		this.habitsToManage = habitsToManage;
	}

	@Override
	public ManageHabitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_habit_item,
				parent, false);
		return new ManageHabitViewHolder(view, parent);
	}

	@Override
	public void onBindViewHolder(ManageHabitViewHolder holder, int position) {
		Habit habit = habitsToManage.get(position);
		ManageHabitListener listener = new ManageHabitListener(holder.parent.getContext(),
				habit.getHabitId(), isAddHabit);
		holder.habitName.setText(habit.getType());
		holder.habitName.setOnClickListener(listener);
	}

	@Override
	public int getItemCount() {
		return habitsToManage.size();
	}

	public static class ManageHabitViewHolder extends RecyclerView.ViewHolder {
		TextView habitName;
		ViewGroup parent;

		public ManageHabitViewHolder(View itemView, ViewGroup parent) {
			super(itemView);
			this.parent = parent;
			habitName = (TextView) itemView.findViewById(R.id.manage_habit_name);
		}
	}
}
