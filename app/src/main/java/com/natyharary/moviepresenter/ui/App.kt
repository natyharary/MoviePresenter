package com.natyharary.moviepresenter.ui

import android.app.Application

class App : Application() {

    init {
        instance = this
    }

    companion object{
        @Volatile
        lateinit var instance : App
        private set
    }

    fun getInstance() : Application{
        return instance
    }
}