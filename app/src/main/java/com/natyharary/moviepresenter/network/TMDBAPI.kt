package com.natyharary.moviepresenter.network

import com.natyharary.moviepresenter.network.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBAPI {

    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String,
                  @Query("language") language: String,
                  @Query("page") page: Int
    ): Call<MovieResponse>
}