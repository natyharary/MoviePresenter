package com.natyharary.moviepresenter.repositories

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.natyharary.moviepresenter.BuildConfig
import com.natyharary.moviepresenter.R
import com.natyharary.moviepresenter.data.MovieRoomDatabase
import com.natyharary.moviepresenter.model.MovieKotlin
import com.natyharary.moviepresenter.network.response.MovieResponse
import com.natyharary.moviepresenter.network.TMDBAPI
import com.natyharary.moviepresenter.ui.App
import com.natyharary.moviepresenter.utils.Constants
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository(application: Application) {

    //Values
    private var tmdbapi: TMDBAPI
    private var retrofit = Retrofit.Builder().baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    var movies:  MutableLiveData<ArrayList<MovieKotlin>>
    var error: MutableLiveData<String>? = null
    private var sharedPreferences2 : SharedPreferences = App.instance.getSharedPreferences(App.instance
            .getString(R.string.preferences_last_update_server), Context.MODE_PRIVATE) //TODO turn this into a singleton

    init {
        tmdbapi = retrofit.create()
        movies = MutableLiveData()
        initializeRoomDatabase(application.applicationContext)
    }

    fun initializeRoomDatabase(context : Context) {
        val moviesDatabase : MovieRoomDatabase? = MovieRoomDatabase.getInstance(context)
    }

    // Gets the movies object according to a determined policy
    // (either from livedata, or Room, or server depending on how old the data is)
    fun getMoviesByPolicy() : MutableLiveData<ArrayList<MovieKotlin>> {

        return movies
        return getMoviesFromRoom()
        return getMoviesFromServer()
    }

    fun getMoviesFromServer(): MutableLiveData<ArrayList<MovieKotlin>> {
        tmdbapi.getMovies(BuildConfig.API_KEY, Constants.LANGUAGE, 1)
                .enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                        if (response.isSuccessful) {

                            //TODO remove this log
                            Log.d("NatyDebug", "new sharedPreferences2 is ${sharedPreferences2.getInt(App.instance
                                    .getString(R.string.preferences_last_update_server), Context.MODE_PRIVATE)}")

                            movies.value = response.body()?.movies
                            //TODO add update to Room
                        }
                        else {
                            error?.value = "Bad data!"
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        error?.value = "Data not received from server!"
                        t.printStackTrace()
                    }
                })

        return movies
    }

    fun getMoviesFromRoom(): MutableLiveData<ArrayList<MovieKotlin>> {
        return movies
    }

    fun deleteMovies(){
        Toast.makeText(App.instance, "Deleting movies...", Toast.LENGTH_LONG).show()
    }
}
