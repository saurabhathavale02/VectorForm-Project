package com.example.saurabh.popularmovie.ui.activities;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.saurabh.popularmovie.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by saurabh on 10/06/16.
 */

@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {


        @Rule
        public ActivityTestRule<SplashActivity> mActivityRule = new ActivityTestRule(SplashActivity.class);

        @Test
        public void testSplashScreenWaitForminsec()
        {
            onView(withId(R.id.appname)).check(matches(isDisplayed()));
            SystemClock.sleep(1000);
            onView(withId(R.id.rv_movielist)).check(matches(isDisplayed()));

        }
}