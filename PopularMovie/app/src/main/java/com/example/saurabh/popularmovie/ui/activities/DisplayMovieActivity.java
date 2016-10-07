package com.example.saurabh.popularmovie.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.saurabh.popularmovie.R;
import com.example.saurabh.popularmovie.adapter.PopularMovieListAdapter;
import com.example.saurabh.popularmovie.model.PopularMovieApiImpl;
import com.example.saurabh.popularmovie.model.pojo.ErrorEvent;
import com.example.saurabh.popularmovie.model.pojo.MoviesData;
import com.example.saurabh.popularmovie.model.pojo.Result;
import com.example.saurabh.popularmovie.ui.interfaces.DisplayMovieView;
import com.example.saurabh.popularmovie.ui.presenter.DisplayMoviePresenter;

import de.greenrobot.event.EventBus;

/**
 * Created by saurabh on 10/02/16.
 */

public class DisplayMovieActivity extends AppCompatActivity implements DisplayMovieView,PopularMovieListAdapter.PopularMovieItemListener
{
    private PopularMovieListAdapter popularMovieListAdapter;
    RecyclerView rv_movielist;
    ProgressBar progressBar;
    private DisplayMoviePresenter displayMoviePresenter;
    ConnectivityManager connectivityManager;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaymovie);

        progressBar=(ProgressBar) findViewById(R.id.progressbar);

        connectivityManager =  (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        initRecyclerView();

        PopularMovieApiImpl popularMovieApi = new PopularMovieApiImpl();
        displayMoviePresenter = new DisplayMoviePresenter(this, popularMovieApi);



            displayMoviePresenter.loadPopularMovieDataApi();






    }

    private void initRecyclerView()
    {
        System.out.println("init recycleview");
        rv_movielist=(RecyclerView) findViewById(R.id.rv_movielist);
        rv_movielist.setHasFixedSize(true);
        rv_movielist.setLayoutManager(new LinearLayoutManager(this));
        popularMovieListAdapter = new PopularMovieListAdapter(this,this);
        rv_movielist.setAdapter(popularMovieListAdapter);


    }


    public void showProgress()
    {

        progressBar.setVisibility(View.VISIBLE);
    }


    public void hideProgress()
    {
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showClickedMovieData(Result movie)
    {
        Intent intent = new Intent(this, DisplayMovieDetail.class);
        intent.putExtra("selectedmovie", movie);
        startActivity(intent);


    }

    @Override
    public Boolean isInternetOn()
    {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return(activeNetwork != null && activeNetwork.isConnectedOrConnecting());


    }

    @Override
    public void DisplayMessage()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.Alert_dialog_msg);
// Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
                if(isInternetOn()==true)
                {

                    displayMoviePresenter.loadPopularMovieDataApi();
                }

                else
                {
                    DisplayMessage();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
    public void onEventMainThread(MoviesData newMovieData)
    {
        System.out.println("eventmainthread="+newMovieData.getResults().get(0).getTitle());
        hideError();
        hideProgress();
        popularMovieListAdapter.addMovie(newMovieData.getResults());
    }

    public void onEventMainThread(ErrorEvent errorEvent)
    {
        hideProgress();
        showError();
    }

    private void hideError()
    {
        System.out.println("hide error");
        rv_movielist.setVisibility(View.VISIBLE);
        //errorView.setVisibility(View.GONE);
    }

    private void showError()
    {
        System.out.println(" error");
        DisplayMessage();
        showProgress();
        //Toast.makeText(DisplayMovieActivity.this,"Error while displaying data",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onMovieClick(Result movieitem)
    {
        displayMoviePresenter.clickMovieItem(movieitem);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        this.finish();
    }
}