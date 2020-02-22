package com.natyharary.moviepresenter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import com.natyharary.moviepresenter.R

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutInflater.inflate(R.layout.fragment_movie_detail, container)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}