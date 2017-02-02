package com.aquamorph.habquit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquamorph.habquit.R;

import static com.aquamorph.habquit.fragments.AssistantFragment.Mood.Mad;

/**
 * This fragment creates the assistant.
 *
 * @author Christian Colglazier
 * @version 2/1/2017
 */

public class AssistantFragment extends Fragment {

	private String TAG = "AssistantFragment";
	TextView assistantMessageText;
	ImageView assistant;
	private int mood = 50;
	private Animation clockwise;
	private Animation counter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_assistant, container, false);
		assistantMessageText = (TextView) view.findViewById(R.id.assistant_message);
		assistant = (ImageView) view.findViewById(R.id.assistant);

		clockwise = AnimationUtils.loadAnimation(getContext().getApplicationContext(),
				R.anim.rotate_clockwise);
		counter = AnimationUtils.loadAnimation(getContext().getApplicationContext(),
				R.anim.rotate_counter_clockwise);

		assistant.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				changeMood(5);
				if (getMood() < 60 && getMood() > 45)
					sendMessage("I see you have a clear wish to die alone by age 40");
				else
					sendMessage("My mood is " + getMood());
			}
		});
		assistant.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				changeMood(-5);
				sendMessage("My mood is " + getMood());
				return true;
			}
		});
		checkMood();
		return view;
	}

	/**
	 * Allow to
	 *
	 * @param text
	 */
	public void sendMessage(String text) {
		if (assistantMessageText != null) {
			assistantMessageText.setText(text);
		}
	}

	public int getMood() {
		return mood;
	}

	/**
	 * Changes the mood value of the assistant.
	 *
	 * @param changeAmount sets how much you want to adjust the mood.
	 */
	public void changeMood(int changeAmount) {
		int init = getMood();
		mood = mood + changeAmount;
		if (mood > 100) mood = 100;
		else if (mood < 0) mood = 0;
		checkMood();
		// Sets transition animation if needed
		if (getMoodFromValue(init) != getMoodFromValue(getMood())) {
			if ((init - getMood()) > 0)
				assistant.startAnimation(clockwise);
			else
				assistant.startAnimation(counter);

		}
	}

	/**
	 * Checks the mood of the AI and changes the design based on its mood.
	 */
	private void checkMood() {
		if (assistant != null) {
			Log.i(TAG, "My mood is " + getMoodFromValue(getMood()));
			switch (getMoodFromValue(getMood())) {
				case Mad:
					assistant.setRotation(90);
					assistant.setImageResource(R.drawable.ai_mad);
					break;
				case Neutral:
					assistant.setRotation(45);
					assistant.setImageResource(R.drawable.ai_neutral);
					break;
				case Happy:
				default:
					assistant.setRotation(0);
					assistant.setImageResource(R.drawable.ai_happy);
					break;
			}
		}
	}

	private Mood getMoodFromValue(int moodValue) {
		if (moodValue < 40) return Mad;
		else if (moodValue < 60) return Mood.Neutral;
		else return Mood.Happy;
	}

	public enum Mood {
		Happy, Neutral, Mad
	}

}
