package com.example.saurabh.popularmovie.model;

import com.example.saurabh.popularmovie.model.pojo.MoviesData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by saurabh on 10/03/16.
 */

public class PopularMovieApiImpl
{
    private interface PostService
    {
        @GET("movie?api_key=cf4ca7333426f0c315c6d80afa24be57&sort_by=movie")
        Observable<MoviesData> getPopularMovieList();
    }
    OkHttpClient client = getOkHttpClient().build();
    Observable<MoviesData> postsObservable= new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl("https://api.themoviedb.org/3/discover/")
            .build().create(PostService.class).getPopularMovieList();


    public Observable<MoviesData> getPostsObservable()
    {
        return postsObservable;
    }

    public OkHttpClient.Builder getOkHttpClient()
    {
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();
        okClientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        okClientBuilder.readTimeout(10, TimeUnit.SECONDS);
        return okClientBuilder;
    }
}
