package com.cjlib.cleanarchitecture.network;

import com.cjlib.cleanarchitecture.home.entities.SearchResults;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("./")
    Observable<SearchResults> getMoviesByName(@Query("s") String query);

}
