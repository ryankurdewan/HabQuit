package com.aquamorph.habquit.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.fragments.AssistantFragment;
import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.provider.TrackHabitServiceProvider;
import com.aquamorph.habquit.utils.Counter;

import java.util.List;

/**
 * Created by Shawn on 2/4/2017.
 */

public class SelectHabitsAdapter extends RecyclerView.Adapter<SelectHabitsAdapter.ViewHolder> {


	private List<Habit> habits;

	//	private PhotoViewAttacher mAttacher;
	public SelectHabitsAdapter(List<Habit> habits) {
		this.habits = habits;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.row_habit_grid, parent, false);
		return new GridViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final Habit habit = habits.get(position);
		if (habit != null) {
			holder.habit = habit;

			final Counter counter = Counter.getInstance();
			final TrackHabitServiceProvider serviceProvider = new TrackHabitServiceProvider();
			final GridViewHolder gridViewHolder = (GridViewHolder) holder;
			gridViewHolder.habitName.setText(habit.getType());
			if (habit.getType().equals("Smoking")) {
				gridViewHolder.habitImage.setImageResource(R.drawable.ic_smoking);
			} else if (habit.getType().equals("Smokeless Tobacco")) {
				gridViewHolder.habitImage.setImageResource(R.drawable.ic_smoke_free_tobacco);
			} else if (habit.getType().equals("Drinking")) {
				gridViewHolder.habitImage.setImageResource(R.drawable.ic_booze);
			} else if (habit.getType().equals("Soft Drinks")) {
				gridViewHolder.habitImage.setImageResource(R.drawable.ic_soda);
			}

			gridViewHolder.habitCount.setText(String.valueOf(counter.getCountFor(habit.getType())));
			gridViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int count = counter.getCountFor(habit.getType());
					AssistantFragment.changeMood(-5.0);
					count++;
					counter.setCountFor(habit.getType(), count);
					serviceProvider.postTrackHabit(habit.getHabitId());
					SelectHabitsAdapter.this.notifyDataSetChanged();
				}
			});
			gridViewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					int count = counter.getCountFor(habit.getType());
					AssistantFragment.changeMood(5.0);
					count--;
					if (count < 0) count = 0;
					counter.setCountFor(habit.getType(), count);
					SelectHabitsAdapter.this.notifyDataSetChanged();
					return true;
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		return habits != null ? habits.size() : 0;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		Habit habit;

		public ViewHolder(View v) {
			super(v);
		}
	}

	public static class ListItemViewHolder extends ViewHolder {

		SwitchCompat habitSwitch;
		TextView type;

		public ListItemViewHolder(View v) {
			super(v);
			habitSwitch = (SwitchCompat) v.findViewById(R.id.habitSwitch);
			type = (TextView) v.findViewById(R.id.type);
		}
	}

	public static class GridViewHolder extends ViewHolder {
		TextView habitName;
		ImageView habitImage;
		TextView habitCount;
		CardView cardView;

		public GridViewHolder(View v) {
			super(v);
			habitName = (TextView) v.findViewById(R.id.habitName);
			habitImage = (ImageView) v.findViewById(R.id.imageView);
			habitCount = (TextView) v.findViewById(R.id.habitCount);
			cardView = (CardView) v.findViewById(R.id.habit_cardview);
		}
	}
}