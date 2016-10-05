package com.example.saurabh.popularmovie.ui.interfaces;

import com.example.saurabh.popularmovie.model.pojo.Result;

/**
 * Created by saurabh on 10/03/16.
 */

public interface DisplayMoviePresenterCall
{
    void loadPopularMovieDataApi();
    void clickMovieItem(Result r);
}
