package com.natyharary.moviepresenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationSet
import androidx.recyclerview.widget.RecyclerView
import com.natyharary.moviepresenter.R
import com.natyharary.moviepresenter.model.MovieKotlin
import com.natyharary.moviepresenter.ui.MovieViewHolder

var moviesList : ArrayList<MovieKotlin> = ArrayList()

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position])

    }

    fun setData(arrayListMovies : ArrayList<MovieKotlin>){
        //TODO add size checkma
        moviesList.addAll(arrayListMovies)
        notifyDataSetChanged()
    }
}