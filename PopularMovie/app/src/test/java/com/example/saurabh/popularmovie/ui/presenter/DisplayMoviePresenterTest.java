package com.example.saurabh.popularmovie.ui.presenter;

import com.example.saurabh.popularmovie.model.PopularMovieApiImpl;
import com.example.saurabh.popularmovie.model.pojo.MoviesData;
import com.example.saurabh.popularmovie.ui.interfaces.DisplayMovieView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by saurabh on 10/04/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Observable.class, AndroidSchedulers.class})
public class DisplayMoviePresenterTest
{
    private DisplayMoviePresenter displayMoviePresenter;
    @Before
    public void setUp() throws Exception
    {
        displayMoviePresenter = spy(new DisplayMoviePresenter(mock(DisplayMovieView.class),mock(PopularMovieApiImpl.class)));

    }

    @Test
    public void loadPopularMovieDataApi() throws Exception
    {
//create mocks
        Observable<MoviesData> mainObservable = (Observable<MoviesData>) mock(Observable.class);

        //define return values
        when(displayMoviePresenter.popularMovieApi.getPostsObservable()).thenReturn(mainObservable);
        when(mainObservable.subscribeOn(Schedulers.io())).thenReturn(mainObservable);
        when(mainObservable.observeOn(AndroidSchedulers.mainThread())).thenReturn(mainObservable);

        //call test method
        displayMoviePresenter.loadPopularMovieDataApi();

        //verify if all methods in the chain are called with correct arguments

        verify(displayMoviePresenter.popularMovieApi).getPostsObservable();
        verify(mainObservable).subscribeOn(Schedulers.io());
        verify(mainObservable).observeOn(AndroidSchedulers.mainThread());
        verify(mainObservable).subscribe(Matchers.<Subscriber<MoviesData>>any());

    }



}