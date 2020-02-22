package com.natyharary.moviepresenter.data

import androidx.room.Query

interface MovieDao {

    @Query("SELECT * FROM movieTable")
    fun selectAll()

    @Query("DELETE FROM movieTable")
    fun deleteAll()
}