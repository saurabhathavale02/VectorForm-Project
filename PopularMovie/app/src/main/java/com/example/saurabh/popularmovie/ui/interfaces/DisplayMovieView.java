package com.example.saurabh.popularmovie.ui.interfaces;

import com.example.saurabh.popularmovie.model.pojo.Result;

/**
 * Created by saurabh on 10/03/16.
 */
public interface DisplayMovieView
{
    void showProgress();
    void hideProgress();
    void showClickedMovieData(Result movie);
    Boolean isInternetOn();
    void DisplayMessage();
}
