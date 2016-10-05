package com.example.saurabh.popularmovie.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.saurabh.popularmovie.R;
import com.example.saurabh.popularmovie.ui.interfaces.SplashView;
import com.example.saurabh.popularmovie.ui.presenter.SplashPresenter;

/**
 * Created by saurabh on 10/02/16.
 */

public class SplashActivity extends  AppCompatActivity implements SplashView
{
    private SplashPresenter splashPresenter;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        splashPresenter=new SplashPresenter(this);
        splashPresenter.changeUiAfter5Secs();


    }

    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    @Override
    public void DisplayNextscreen()
    {
        Intent intent = new Intent(this, DisplayMovieActivity.class);
        startActivity(intent);
        finish();

    }
}
