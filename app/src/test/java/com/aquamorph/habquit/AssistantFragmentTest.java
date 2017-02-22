package com.aquamorph.habquit;

import android.content.Context;

import com.aquamorph.habquit.activities.MainActivity;
import com.aquamorph.habquit.fragments.AssistantFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for the assistant
 *
 * @author Christian Colglazier
 * @version 2/22/2017
 */

@RunWith(RobolectricTestRunner.class)
@Config( constants = BuildConfig.class, manifest="../../../../../src/main/AndroidManifest.xml",
		sdk = 21 )
public class AssistantFragmentTest {
	Context context = Robolectric.buildActivity(MainActivity.class).create().get();

	@Test
	public void changeMood() throws Exception {
		// Tests mood being set above range
		double expected1 = 100.0;
		AssistantFragment.changeMood(150);
		assertEquals(expected1, AssistantFragment.getMood());

		// Tests mood being set below range
		double expected2 = 0.0;
		AssistantFragment.changeMood(-1500);
		assertEquals(expected2, AssistantFragment.getMood());

		// Tests increasing mood
		double expected3 = 5.0;
		AssistantFragment.changeMood(5.0);
		assertEquals(expected3, AssistantFragment.getMood());

		// Tests decreasing mood
		double expected4 = 3.0;
		AssistantFragment.changeMood(-2.0);
		assertEquals(expected4, AssistantFragment.getMood());
	}
}
