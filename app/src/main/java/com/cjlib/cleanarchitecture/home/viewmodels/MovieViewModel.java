package com.cjlib.cleanarchitecture.home.viewmodels;

import com.cjlib.cleanarchitecture.R;
import com.cjlib.cleanarchitecture.base.ViewModel;
import com.cjlib.cleanarchitecture.components.ResourceProvider;
import com.cjlib.cleanarchitecture.home.entities.Movie;

public class MovieViewModel implements ViewModel {

    private String movieTitle;
    private String movieYear;
    private String movieType;
    private String moviePosterUrl;
    private String movieImbdId;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMoviePosterUrl() {
        return moviePosterUrl;
    }

    public void setMoviePosterUrl(String moviePosterUrl) {
        this.moviePosterUrl = moviePosterUrl;
    }

    public String getMovieImbdId() {
        return movieImbdId;
    }

    public void setMovieImbdId(String movieImbdId) {
        this.movieImbdId = movieImbdId;
    }


    public static MovieViewModel getMovieViewModel(ResourceProvider provider, Movie movie) {

        MovieViewModel movieViewModel = new MovieViewModel();

        movieViewModel.movieTitle = movie.title;
        movieViewModel.movieImbdId = provider.getString(R.string.imdb_id, movie.imdbId);
        movieViewModel.moviePosterUrl = movie.poster;
        movieViewModel.movieYear = movie.year;
        movieViewModel.movieType = provider.getString(R.string.type, movie.type);

        return movieViewModel;

    }
}
