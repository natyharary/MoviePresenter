package com.natyharary.moviepresenter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieTable::class], version = 1)
abstract class MovieRoomDatabase : RoomDatabase() {

    companion object {

        //SINGLETON
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getInstance(context: Context): MovieRoomDatabase? {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                        MovieRoomDatabase::class.java, "movie_database")
                        .build()
                INSTANCE = instance
                return instance
            }

        }
    }
}