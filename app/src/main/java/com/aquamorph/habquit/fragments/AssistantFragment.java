package com.aquamorph.habquit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
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

	private static String TAG = "AssistantFragment";
	private static TextView assistantMessageText;
	private static ImageView assistant;
	private static int mood = 70;
	private static Animation happyToNeutral;
	private static Animation madToNeutral;
	private static Animation neutralToHappy;
	private static Animation neutralToMad;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_assistant, container, false);
		assistantMessageText = (TextView) view.findViewById(R.id.assistant_message);
		assistant = (ImageView) view.findViewById(R.id.assistant);

		happyToNeutral = AnimationUtils.loadAnimation(getContext().getApplicationContext(),
				R.anim.assistant_happy_to_neutral);
		madToNeutral = AnimationUtils.loadAnimation(getContext().getApplicationContext(),
				R.anim.assistant_mad_to_neutral);
		neutralToHappy = AnimationUtils.loadAnimation(getContext().getApplicationContext(),
				R.anim.assistant_neutral_to_happy);
		neutralToMad = AnimationUtils.loadAnimation(getContext().getApplicationContext(),
				R.anim.assistant_neutral_to_mad);
		happyToNeutral.setAnimationListener(new AssistantAnimationListener());
		madToNeutral.setAnimationListener(new AssistantAnimationListener());
		neutralToHappy.setAnimationListener(new AssistantAnimationListener());
		neutralToMad.setAnimationListener(new AssistantAnimationListener());

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
		switch (getMoodFromValue(getMood())) {
			case Mad:
				assistant.setRotation(90);
				break;
			case Neutral:
				assistant.setRotation(45);
				break;
			case Happy:
			default:
				assistant.setRotation(0);
				break;
		}
		return view;
	}

	/**
	 * Allow to
	 *
	 * @param text
	 */
	public static void sendMessage(String text) {
		if (assistantMessageText != null) {
			final Animation in = new AlphaAnimation(0.0f, 1.0f);
			in.setDuration(500);
			final Animation out = new AlphaAnimation(1.0f, 0.0f);
			out.setDuration(500);

			AnimationSet as = new AnimationSet(true);
			as.addAnimation(in);
			out.setStartOffset(4500);
			as.addAnimation(out);

			assistantMessageText.setText(text);
			assistantMessageText.startAnimation(as);
			assistantMessageText.setVisibility(View.VISIBLE);
			assistantMessageText.postDelayed(new Runnable() {
				public void run() {
					assistantMessageText.setVisibility(View.INVISIBLE);
				}
			}, 5000);
		}
	}

	public static int getMood() {
		return mood;
	}

	/**
	 * Changes the mood value of the assistant.
	 *
	 * @param changeAmount sets how much you want to adjust the mood.
	 */
	public static void changeMood(int changeAmount) {
		int init = getMood();
		mood = mood + changeAmount;
		if (mood > 100) mood = 100;
		else if (mood < 0) mood = 0;
		// Sets transition animation if needed
		if (getMoodFromValue(init) != getMoodFromValue(getMood())) {
			if ((init - getMood()) > 0) {
				if (getMoodFromValue(getMood()) == Mood.Neutral)
					assistant.startAnimation(happyToNeutral);
				else
					assistant.startAnimation(neutralToMad);
			}
			else {
				if (getMoodFromValue(getMood()) == Mood.Neutral)
					assistant.startAnimation(madToNeutral);
				else
					assistant.startAnimation(neutralToHappy);
			}
		}
	}

	/**
	 * Checks the mood of the AI and changes the design based on its mood.
	 */
	public static void checkMood() {
		if (assistant != null) {
			switch (getMoodFromValue(getMood())) {
				case Mad:
					assistant.setImageResource(R.drawable.ai_mad);
					break;
				case Neutral:
					assistant.setImageResource(R.drawable.ai_neutral);
					break;
				case Happy:
				default:
					assistant.setImageResource(R.drawable.ai_happy);
					break;
			}
		}
	}

	private static Mood getMoodFromValue(int moodValue) {
		if (moodValue < 40) return Mad;
		else if (moodValue < 60) return Mood.Neutral;
		else return Mood.Happy;
	}

	public enum Mood {
		Happy, Neutral, Mad
	}

}

