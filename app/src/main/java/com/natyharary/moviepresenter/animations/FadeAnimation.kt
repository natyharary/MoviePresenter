package com.natyharary.moviepresenter.animations

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Interpolator

object FadeAnimation{

    fun getFadeAnimation(fadeFrom : Float, fadeTo : Float, interpolator : Interpolator, duration : Long) : Animation{
        var animation : Animation = AlphaAnimation(fadeFrom, fadeTo)
        animation.interpolator = interpolator
        animation.duration = duration
        return animation
    }
}