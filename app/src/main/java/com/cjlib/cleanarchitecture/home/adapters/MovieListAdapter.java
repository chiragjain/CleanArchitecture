package com.cjlib.cleanarchitecture.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjlib.cleanarchitecture.R;
import com.cjlib.cleanarchitecture.home.viewmodels.MovieViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<MovieViewModel> items;

    public MovieListAdapter(List<MovieViewModel> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieViewModel item = items.get(position);
        holder.titleView.setText(item.getMovieTitle());
        holder.yearView.setText(item.getMovieYear());
        holder.typeView.setText(item.getMovieType());
        holder.imdbIdView.setText(item.getMovieImbdId());

        Picasso.with(holder.titleView.getContext())
                .load(item.getMoviePosterUrl())
                .into(holder.posterView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView posterView;
        public TextView titleView;
        public TextView yearView;
        public TextView typeView;
        public TextView imdbIdView;

        public ViewHolder(View itemView) {
            super(itemView);
            posterView = (ImageView) itemView.findViewById(R.id.movie_poster);
            titleView = (TextView) itemView.findViewById(R.id.movie_name);
            yearView = (TextView) itemView.findViewById(R.id.movie_year);
            typeView = (TextView) itemView.findViewById(R.id.movie_type);
            imdbIdView = (TextView) itemView.findViewById(R.id.movie_imdb_id);
        }
    }
}
