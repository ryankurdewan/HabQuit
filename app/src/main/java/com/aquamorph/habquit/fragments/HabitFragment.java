package com.aquamorph.habquit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.SelectHabitsAdapter;
import com.aquamorph.habquit.model.HabitSgk;
import com.aquamorph.habquit.provider.HabitSgkServiceProvider;
import com.aquamorph.habquit.service.HabitSgkService;
import com.aquamorph.habquit.utils.HabitParameter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p></p>
 *
 * @author Christian Colglazier
 * @version 2/2/2017
 */

public class HabitFragment extends Fragment implements HabitSgkService.OnHabitSgkListener {

	private RecyclerView recyclerView;
    @BindView(R.id.empty) View empty;






	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_habit, container, false);
        ButterKnife.bind(this,view);
		recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));



		/*CardView cigarette = (CardView) view.findViewById(R.id.habit1);
		final TextView smokingCountText = (TextView) view.findViewById(R.id.textView2);


		final Habit habit1 = new Habit("Cigarettes", view.getContext());
		final TextView habit1Name = (TextView) view.findViewById(R.id.habitName);
		habit1Name.setText(habit1.getName());
		smokingCountText.setText(habit1.getText());

		final TrackHabitServiceProvider serviceProvider = new TrackHabitServiceProvider();

		cigarette.setOnClickListener(
				new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//this view should be loading a habit from the dbso we would have a true habit id
				serviceProvider.postTrackHabit(1);
				habit1.incCount();
				if (habit1.getCount() == 2.0)
					AssistantFragment.sendMessage("You sure are set on dying young. Sad!");
				AssistantFragment.changeMood(-5.0);
				smokingCountText.setText(habit1.getText());
			}
		});

		cigarette.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				habit1.decCount();
				if (habit1.getCount() == 6.0)
					AssistantFragment.sendMessage("Nicotine addiction is like an itch. If you" +
							" itch, it's nice to scratch it. But better to have no itch at all."
							+ "- Dali Lama");
				AssistantFragment.changeMood(5.0);
				smokingCountText.setText(habit1.getText());
				return true;
			}
		});
*/
		return view;
	}

    @Override
    public void onResume() {
        super.onResume();
        getHabitSgk();
    }


    private void getHabitSgk() {
        HabitSgkServiceProvider habitSgkServiceProvider = new HabitSgkServiceProvider();
        habitSgkServiceProvider.getHabitSgks(this);
    }

    @Override
    public void onSuccess(List<HabitSgk> habitSgks) {
        List<HabitSgk> filtered = new ArrayList<>();
        HabitParameter habitParameter = HabitParameter.getInstance();
        for(HabitSgk h : habitSgks)
        {
            if(habitParameter.getHabitIds().contains(h.getHabitId()))
            {
                filtered.add(h);
            }
        }

        empty.setVisibility(filtered.size() == 0 ? View.VISIBLE : View.GONE);
        

        SelectHabitsAdapter selectHabitsAdapter = new SelectHabitsAdapter(filtered,true);
        recyclerView.setAdapter(selectHabitsAdapter);

    }

    @Override
    public void onError() {

    }
}
