package com.example.saurabh.popularmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saurabh.popularmovie.R;
import com.example.saurabh.popularmovie.model.pojo.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 10/03/16.
 */

public class PopularMovieListAdapter  extends RecyclerView.Adapter<PopularMovieListAdapter.ViewHolder>
{
    private static List<Result> movielist;

    private final PopularMovieItemListener mListener;

    public PopularMovieListAdapter(Context context, PopularMovieItemListener listener)
    {
        this.movielist = new ArrayList<>();
        this.mListener = listener;
    }

    public void addMovie(List<Result> newmovie)
    {
        System.out.println("add ="+newmovie.get(0).getTitle());
        movielist.addAll(newmovie);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.storename.setText(movielist.get(position).getTitle());
        holder.storeaddress.setText(movielist.get(position).getReleaseDate());

        holder.logo.setImageBitmap(null);

        String logo_url="https://image.tmdb.org/t/p/w92/"+movielist.get(position).getPosterPath();

        Picasso.with(holder.logo.getContext())
                .load(logo_url)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.logo);
    }



    @Override
    public int getItemCount() {

        return movielist.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView storename;
        TextView storeaddress;
        ImageView logo;

        public ViewHolder(View view)
        {
            super(view);
            view.setOnClickListener(this);

            storename=(TextView) view.findViewById(R.id.storename);
            storeaddress=(TextView) view.findViewById(R.id.storeaddress);
            logo=(ImageView) view.findViewById(R.id.imageView2);

        }

        @Override
        public void onClick(View view)
        {
            int position = getAdapterPosition();
            Result SelectedMovie = movielist.get(position);
            mListener.onMovieClick(SelectedMovie);

        }
    }

    public interface PopularMovieItemListener
    {
        void onMovieClick(Result movieitem);
    }
}
