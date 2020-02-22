package com.natyharary.moviepresenter.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.natyharary.moviepresenter.model.MovieKotlin
import com.natyharary.moviepresenter.repositories.MovieRepository

class MovieViewModel(application:Application) : AndroidViewModel(application) {

    //Values
    private var movieRepository : MovieRepository = MovieRepository(application)

    fun getMoviesFromRepo(): MutableLiveData<ArrayList<MovieKotlin>>? {
        return movieRepository.getMoviesFromServer()
    }

    fun getError() : MutableLiveData<String>? {
        return movieRepository.error
    }

    fun sendRepoCommand(command : String){
        when(command){
            "getMoviesFromServer" -> movieRepository.getMoviesFromServer()
            "getMoviesFromRoom" -> movieRepository.getMoviesFromRoom()
            "deleteMovies" -> movieRepository.deleteMovies()
            else -> Toast.makeText(getApplication(), "Error! Unknown command!", Toast.LENGTH_LONG).show()
        }
    }


}