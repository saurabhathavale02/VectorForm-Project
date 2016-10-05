package com.example.saurabh.popularmovie.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saurabh.popularmovie.R;
import com.example.saurabh.popularmovie.model.pojo.Result;
import com.example.saurabh.popularmovie.ui.interfaces.DisplayMovieDetailView;
import com.squareup.picasso.Picasso;

/**
 * Created by saurabh on 10/03/16.
 */

public class DisplayMovieDetail extends AppCompatActivity implements DisplayMovieDetailView
{
    TextView Title,Description,Release_Date;
    ImageView Poster;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaymoviedtail);

        TextView Title=(TextView) findViewById(R.id.title);
        TextView Description=(TextView) findViewById(R.id.description);
        TextView Release_date=(TextView) findViewById(R.id.release_date);
        ImageView Poster=(ImageView) findViewById(R.id.poster);

        Intent intent = this.getIntent();

        Result selectedmovie= (Result) intent.getSerializableExtra("selectedmovie");

        Title.setText(selectedmovie.getTitle().toString());
        Description.setText(selectedmovie.getOverview().toString());
        Release_date.setText(selectedmovie.getReleaseDate().toString());

        String poster_url="https://image.tmdb.org/t/p/w154/"+selectedmovie.getPosterPath();

        Picasso.with(Poster.getContext())
                .load(poster_url)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(Poster);


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        this.finish();
    }
}
