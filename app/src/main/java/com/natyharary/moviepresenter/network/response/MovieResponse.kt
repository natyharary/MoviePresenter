package com.natyharary.moviepresenter.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.natyharary.moviepresenter.model.MovieKotlin

class MovieResponse {

    @SerializedName("results")
    @Expose
    var movies : ArrayList<MovieKotlin>?= null
}