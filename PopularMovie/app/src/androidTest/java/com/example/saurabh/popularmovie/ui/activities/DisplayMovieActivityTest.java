package com.example.saurabh.popularmovie.ui.activities;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.saurabh.popularmovie.R;
import com.example.saurabh.popularmovie.model.pojo.ErrorEvent;
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
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by saurabh on 10/04/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DisplayMovieActivityTest {

    @Rule
    public ActivityTestRule<DisplayMovieActivity> mActivityRule = new ActivityTestRule(DisplayMovieActivity.class);

    @Test
    public void testShouldShowRecyclerViewOnNewPosts()
    {

        List<Result> movielist = new ArrayList<>();
        List<Integer> genre=new  ArrayList<>();
        genre.add(1);

        movielist.add(new Result(1.33,"/i462LXFdZHsHO5FbYclmh5vnLHe.jpg",false,"Test","2009-01-05",genre, 1, "Test_Tile", "English", "T-Tile","/i462LXFdZHsHO5FbYclmh5vnLHe.jpg" ,1.333, 1, false));

        MoviesData movieData =new MoviesData();

        movieData.setResults(movielist);
        EventBus.getDefault().post(movieData);

        onView(withId(R.id.rv_movielist)).check(matches(isDisplayed()));
        // onView(withId(R.id.error_view)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testShouldShowErrorViewOnNetworkError() {

        EventBus.getDefault().post(new ErrorEvent());

        // onView(withId(R.id.error_view)).check(matches(isDisplayed()));
        onView(withText("Error while displaying data"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testClickOnRecylerViewItem_showsDisplaycontent() {
        // click on first item
        onView(withId(R.id.rv_movielist)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));



        onView(withId(R.id.title)).check(matches(isDisplayed()));
        //onView(withId(R.id.name)).check(matches(isDisplayed()));
        //onView(withId(R.id.cityname)).check(matches(isDisplayed()));

    }

}
/*
    @Test
    public void testShouldShowRecyclerViewOnNewPosts()
    {
        onView(withId(R.id.movie)).check(matches(ViewMatchers.isDisplayed()));

        List<Result> movielist = new ArrayList<>();
        List<Integer> genre=new  ArrayList<>();
        genre.add(1);

        movielist.add(new Result(1.33,"/i462LXFdZHsHO5FbYclmh5vnLHe.jpg",false,"Test","2009-01-05",genre, 1, "Test_Tile", "English", "T-Tile","/i462LXFdZHsHO5FbYclmh5vnLHe.jpg" ,1.333, 1, false));

        MoviesData movieData =new MoviesData();

        movieData.setResults(movielist);
        EventBus.getDefault().post(movieData);

        onView(withId(R.id.rv_movielist)).check(matches(ViewMatchers.isDisplayed()));
        // onView(withId(R.id.error_view)).check(matches(not(isDisplayed())));

    }

    /*
    @Test
    public void testShouldShowErrorViewOnNetworkError() {

        EventBus.getDefault().post(new ErrorEvent());

        // onView(withId(R.id.error_view)).check(matches(isDisplayed()));
        onView(withText("Error while displaying data"))
                .inRoot(withDecorView(not(rule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testClickOnRecylerViewItem_showsDisplaycontent() {
        // click on first item
        onView(withId(R.id.rv_movielist)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


        // should show toast message
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
        //onView(withId(R.id.name)).check(matches(isDisplayed()));
        //onView(withId(R.id.cityname)).check(matches(isDisplayed()));

    }
*/
