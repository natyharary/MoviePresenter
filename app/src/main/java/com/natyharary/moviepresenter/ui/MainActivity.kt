package com.natyharary.moviepresenter.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.natyharary.moviepresenter.R
import com.natyharary.moviepresenter.adapter.MovieAdapter
import com.natyharary.moviepresenter.model.MovieKotlin
import com.natyharary.moviepresenter.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Instant

class MainActivity : AppCompatActivity() {

    // Values
    private var movieViewModel: MovieViewModel? = null
    private var movieAdapter: MovieAdapter? = null
    private var isFABOpen = false
    private lateinit var vibrator: Vibrator
    private var currentTime : Int = Instant.now().epochSecond.toInt()
    private lateinit var preferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeWindowTopTranslucent()

        initSystemServices()

        initViewModel()

        // Init activity views and adapterKotlin for recyclerView
        initViews()

        setListeners()

        setAdapter()

        // Init observers for moviesList ArrayList<Movie> and error handling
        getMovies()
        getErrorHandler()
    }

    private fun makeWindowTopTranslucent() {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val attrib = window.attributes
        attrib.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
    }

    private fun initSystemServices() {
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        preferences = getSharedPreferences(App.instance
                .getString(R.string.preferences_last_update_server),Context.MODE_PRIVATE)

        //TODO think where this should go
        preferences.edit().putInt(getString(R.string.preferences_last_update_server), currentTime).apply()
    }

    private fun initViewModel() {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    private fun initViews() {
        movieAdapter = MovieAdapter()
    }

    private fun setListeners() {
        fabMainMenu.setOnClickListener {
            vibrate()

            if (!isFABOpen) {
                showFABMenu()
            } else {
                hideFABMenu()
            }
        }

        fabGetServer.setOnClickListener {
            movieViewModel?.sendRepoCommand("getMoviesFromServer")
            vibrate()
        }

        fabGetRoom.setOnClickListener {
            movieViewModel?.sendRepoCommand("getMoviesFromRoom")
            vibrate()
        }

        fabDelete.setOnClickListener {
            movieViewModel?.sendRepoCommand("deleteMovies")
            vibrate()
        }
    }

    private fun vibrate() {
        vibrator.vibrate(VibrationEffect.createOneShot(100, 1))
    }

    private fun showFABMenu() {
        isFABOpen = true
        layGetServer.animate().translationY(-200f)
        layGetRoom.animate().translationY(-400f)
        layDelete.animate().translationY(-600f)
        tvGetServer.animate().alpha(1f)
        tvGetRoom.animate().alpha(1f)
        tvDelete.animate().alpha(1f)
        recyclerView.animate().alpha(0.5f)
    }

    private fun hideFABMenu() {
        isFABOpen = false
        layGetServer.animate().translationY(0F)
        layGetRoom.animate().translationY(0F)
        layDelete.animate().translationY(0F)
        tvGetServer.animate().alpha(0f)
        tvGetRoom.animate().alpha(0f)
        tvDelete.animate().alpha(0f)
        recyclerView.animate().alpha(1f)
    }

    private fun setAdapter() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
    }

    private fun getMovies() {
        movieViewModel?.getMoviesFromRepo()?.observe(this, Observer { movies -> setMovies(movies) })
    }

    private fun getErrorHandler() {
        movieViewModel?.getError()?.observe(this, Observer { Toast.makeText(this, movieViewModel!!.getError()?.value, Toast.LENGTH_LONG).show() })
    }

    private fun setMovies(moviesList: ArrayList<MovieKotlin>) {
        if (moviesList.size == 0) {
            Toast.makeText(this, "Movie list is empty!", Toast.LENGTH_LONG).show()
        } else {
            movieAdapter?.setData(moviesList)
        }
    }
}
