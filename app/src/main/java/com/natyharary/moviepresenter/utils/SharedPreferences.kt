//package com.natyharary.moviepresenter.utils
//
//import com.natyharary.moviepresenter.data.MovieRoomDatabase
//
//abstract class SharedPreferences {
//
//    companion object {
//
//        //SINGLETON
//        @Volatile
//        private var INSTANCE: SharedPreferences? = null
//
//        fun getInstance() : SharedPreferences{
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//
//            synchronized(this) {
//                val instance = Prefs()
//                INSTANCE = instance
//                return instance
//            }
//        }
//
//        }
//}
//
