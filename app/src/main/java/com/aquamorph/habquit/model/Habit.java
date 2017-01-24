package com.aquamorph.habquit.model;

import android.content.Context;

import com.aquamorph.habquit.R;

import java.util.Locale;

/**
 * Keeps track of information related to a habit
 *
 * @author Christian Colglazier
 * @version 1/23/2017
 */

public class Habit {
	private String name = "";
	private int count = 0;
	private Context context;

	public Habit(String name, Context context) {
		this.name = name;
		this.context = context;
	}

	public void setcount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void incCount() {
		count++;
	}

	public void decCount() {
		if (count > 0) {
			count--;
		}
	}

	public String getText() {
		return String.format(Locale.US, "%s: %d",
				context.getResources().getString(R.string.count_text),
				count);
	}

	public String getName() {
		return name;
	}
}
