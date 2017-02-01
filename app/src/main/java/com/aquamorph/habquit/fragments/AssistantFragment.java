package com.aquamorph.habquit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquamorph.habquit.R;

/**
 * <p></p>
 *
 * @author Christian Colglazier
 * @version 2/1/2017
 */

public class AssistantFragment extends Fragment {

	TextView assistantMessageText;
	ImageView assistant;
	private int mood = 50;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_assistant, container, false);
		assistantMessageText = (TextView) view.findViewById(R.id.assistant_message);
		assistant = (ImageView) view.findViewById(R.id.assistant);
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
		mood = mood + changeAmount;
		if (mood > 100) mood = 100;
		else if (mood < 0) mood = 0;
		checkMood();
	}

	/**
	 * Checks the mood of the AI and changes the design based on its mood.
	 */
	private void checkMood() {
		if (assistant == null) {

		} else if (getMood() < 40) {
			assistant.setImageResource(R.drawable.ai_mad);
		} else if (getMood() < 60) {
			assistant.setImageResource(R.drawable.ai_neutral);
		} else {
			assistant.setImageResource(R.drawable.ai_happy);
		}
	}
}
