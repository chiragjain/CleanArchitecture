package com.cjlib.cleanarchitecture.home.interactors;

import com.cjlib.cleanarchitecture.home.entities.Movie;
import com.cjlib.cleanarchitecture.network.ApiService;

import java.util.List;

import io.reactivex.Observable;

public class MovieInteractor {

    private ApiService service;

    public MovieInteractor(ApiService service) {
        this.service = service;
    }

    public Observable<List<Movie>> getListOfMovies(String query) {
        return service.getMoviesByName(query).map(searchResults -> searchResults.movies);
    }
}
