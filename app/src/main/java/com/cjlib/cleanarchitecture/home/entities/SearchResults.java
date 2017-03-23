package com.cjlib.cleanarchitecture.home.entities;

import com.cjlib.cleanarchitecture.base.Entity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResults implements Entity {

    @SerializedName("Search")
    public List<Movie> movies;
}
