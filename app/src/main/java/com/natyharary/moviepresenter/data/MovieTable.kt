package com.natyharary.moviepresenter.data

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieTable")
data class MovieTable(@PrimaryKey
                      @ColumnInfo(name = "title") val movie : String)