package com.natyharary.moviepresenter.utils

class AppSharedPreferences {

    companion object {

        //SINGLETON
        @Volatile
        private var INSTANCE: AppSharedPreferences? = null

        fun getInstance(): AppSharedPreferences {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = AppSharedPreferences()
                INSTANCE = instance
                return instance
            }
        }

    }
}

