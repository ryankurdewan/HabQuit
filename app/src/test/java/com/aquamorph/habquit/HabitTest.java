package com.aquamorph.habquit;

import android.content.Context;

import com.aquamorph.habquit.activities.MainActivity;
import com.aquamorph.habquit.model.Habit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.TestCase.assertEquals;


/**
 * Test for changing habit count
 *
 * @author Christian Colglazier
 * @version 2/20/2017
 */
@RunWith(RobolectricTestRunner.class)
@Config( constants = BuildConfig.class, manifest="../../../../../src/main/AndroidManifest.xml",
		sdk = 21 )
public class HabitTest {
	Context context = Robolectric.buildActivity(MainActivity.class).create().get();

	@Test
	public void setcount() throws Exception {
		// Tests setting to zero
		Habit habit1 = new Habit("Habit1", context);
		int expected1 = 0;
		habit1.setcount(expected1);
		assertEquals(expected1, habit1.getCount());

		// Tests setting to a positive number
		Habit habit2 = new Habit("Habit2", context);
		int expected2 = 123;
		habit2.setcount(expected2);
		assertEquals(expected2, habit2.getCount());
	}

	@Test
	public void getCount() throws Exception {
		// Tests getting setting initialization count to zero
		Habit habit1 = new Habit("Habit1", context);
		int expected1 = 0;
		assertEquals(expected1, habit1.getCount());
	}

	@Test
	public void incCount() throws Exception {
		// Tests incrementing by one
		Habit habit1 = new Habit("Habit1", context);
		int expected1 = 1;
		habit1.incCount();
		assertEquals(expected1, habit1.getCount());

		// Tests incrementing by three
		Habit habit2 = new Habit("Habit2", context);
		int expected2 = 3;
		habit2.incCount();
		habit2.incCount();
		habit2.incCount();
		assertEquals(expected2, habit2.getCount());

		// Tests incrementing from a set point
		Habit habit3 = new Habit("Habit3", context);
		int expected3 = 124;
		habit3.setcount(123);
		habit3.incCount();
		assertEquals(expected3, habit3.getCount());
	}

	@Test
	public void decCount() throws Exception {
		// Tests decrementing by one
		Habit habit1 = new Habit("Habit1", context);
		int expected1 = 0;
		habit1.setcount(1);
		habit1.decCount();
		assertEquals(expected1, habit1.getCount());

		// Tests decrementing past 0
		Habit habit2 = new Habit("Habit2", context);
		int expected2 = 0;
		habit2.decCount();
		assertEquals(expected2, habit2.getCount());

	}

	@Test
	public void getText() throws Exception {
		// Tests setting title on initialization
		Habit habit1 = new Habit("Habit1", context);
		String expected1 = context.getResources().getString(R.string.count_text) + ": 0";
		assertEquals(expected1, habit1.getText());

		// Tests setting title and incrementing
		Habit habit2 = new Habit("Habit2", context);
		String expected2 = context.getResources().getString(R.string.count_text) + ": 2";
		habit2.incCount();
		habit2.incCount();
		habit2.incCount();
		habit2.decCount();
		assertEquals(expected2, habit2.getText());
	}

	@Test
	public void getName() throws Exception {
		// Tests name initialization
		String expected1 = "Habit1";
		Habit habit1 = new Habit(expected1, context);
		assertEquals(expected1, habit1.getName());
	}
}
