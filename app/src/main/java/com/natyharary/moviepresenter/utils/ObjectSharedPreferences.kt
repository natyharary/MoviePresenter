package com.natyharary.moviepresenter.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.time.Instant

object ObjectSharedPreferences {

    val LAST_UPDATE = "LAST_UPDATE"
    val USER_PASSWORD = "PASSWORD"

    fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.lastUpdate
        get() = getInt(LAST_UPDATE, 0)
        set(aaa) {
            editMe {
                it.putInt(LAST_UPDATE, Instant.now().epochSecond.toInt())
            }
        }
}