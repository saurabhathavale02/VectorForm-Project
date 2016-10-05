package com.example.saurabh.popularmovie.ui.presenter;

import android.util.Log;

import com.example.saurabh.popularmovie.model.PopularMovieApiImpl;
import com.example.saurabh.popularmovie.model.pojo.ErrorEvent;
import com.example.saurabh.popularmovie.model.pojo.MoviesData;
import com.example.saurabh.popularmovie.model.pojo.Result;
import com.example.saurabh.popularmovie.ui.interfaces.DisplayMoviePresenterCall;
import com.example.saurabh.popularmovie.ui.interfaces.DisplayMovieView;

import de.greenrobot.event.EventBus;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by saurabh on 10/03/16.
 */

public class DisplayMoviePresenter implements DisplayMoviePresenterCall
{

    public DisplayMovieView displayMovieView;
    public PopularMovieApiImpl popularMovieApi;

    public DisplayMoviePresenter(DisplayMovieView view, PopularMovieApiImpl movieapiimpl)
    {
        this.displayMovieView = view;
        this.popularMovieApi = movieapiimpl;
    }

    @Override
    public void loadPopularMovieDataApi()
    {
        popularMovieApi.getPostsObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread())
                .subscribe(new Subscriber<MoviesData>()
                {

                    @Override
                    public void onNext(MoviesData moviedata)
                    {
                        EventBus.getDefault().post(moviedata);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.e("GithubDemo", e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent());
                    }

                });

    }



    @Override
    public void clickMovieItem(Result r)
    {
        displayMovieView.showClickedMovieData(r);

    }
}
