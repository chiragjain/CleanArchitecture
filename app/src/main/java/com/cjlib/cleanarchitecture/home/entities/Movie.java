package com.cjlib.cleanarchitecture.home.entities;

import com.cjlib.cleanarchitecture.base.Entity;
import com.google.gson.annotations.SerializedName;

public class Movie implements Entity {

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("imdbID")
    public String imdbId;

    @SerializedName("Type")
    public String type;

    @SerializedName("Poster")
    public String poster;
}
