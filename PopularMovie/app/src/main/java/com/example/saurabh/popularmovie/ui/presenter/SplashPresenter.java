package com.example.saurabh.popularmovie.ui.presenter;


import com.example.saurabh.popularmovie.ui.interfaces.SplashPresenterCall;
import com.example.saurabh.popularmovie.ui.interfaces.SplashView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by saurabh on 10/03/16.
 */

public class SplashPresenter implements SplashPresenterCall
{

    SplashView splashView;

    public SplashPresenter(SplashView spview)
    {
        this.splashView=spview;
    }



    public void changeUiAfter5Secs()
    {
        TimerTask task = new TimerTask() {

            @Override
            public void run()
            {
                splashView.DisplayNextscreen();

            }
        };
        Timer t = new Timer();
        t.schedule(task, 1000);


    }
}
