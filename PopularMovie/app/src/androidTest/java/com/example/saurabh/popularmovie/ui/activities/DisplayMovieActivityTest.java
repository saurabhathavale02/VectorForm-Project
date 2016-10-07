package com.example.saurabh.popularmovie.ui.activities;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.saurabh.popularmovie.R;
import com.example.saurabh.popularmovie.model.pojo.MoviesData;
import com.example.saurabh.popularmovie.model.pojo.Result;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by saurabh on 10/04/16.
 */

@RunWith(AndroidJUnit4.class)

public class DisplayMovieActivityTest {

    @Rule
    public ActivityTestRule<DisplayMovieActivity> mActivityRule = new ActivityTestRule(DisplayMovieActivity.class);



    @Test
    public void testShouldShowRecyclerViewOnNewPostsAndCheckOnClick()
    {

        List<Result> movielist = new ArrayList<>();
        List<Integer> genre=new  ArrayList<>();
        genre.add(1);

        movielist.add(new Result(1.33,"/i462LXFdZHsHO5FbYclmh5vnLHe.jpg",false,"Test","2009-01-05",genre, 1, "Test_Tile", "English", "T-Tile","/i462LXFdZHsHO5FbYclmh5vnLHe.jpg" ,1.333, 1, false));

        MoviesData movieData =new MoviesData();

        movieData.setResults(movielist);
        EventBus.getDefault().post(movieData);

        onView(withId(R.id.rv_movielist)).check(matches(isDisplayed()));

        onView(withId(R.id.rv_movielist)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.title)).check(matches(isDisplayed()));

    }

}
