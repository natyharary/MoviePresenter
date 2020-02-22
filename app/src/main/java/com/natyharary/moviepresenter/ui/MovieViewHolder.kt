package com.natyharary.moviepresenter.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.natyharary.moviepresenter.BuildConfig
import com.natyharary.moviepresenter.R
import com.natyharary.moviepresenter.animations.FadeAnimation
import com.natyharary.moviepresenter.model.MovieKotlin
import com.squareup.picasso.Picasso

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal var poster: ImageView
    internal var title: TextView

    internal var fadeInTitle: Animation? = null
    internal var fadeOutTitle: Animation? = null
    internal var fadeInPoster: Animation? = null
    internal var fadeOutPoster: Animation? = null

    init {
        poster = itemView.findViewById(R.id.posterImage)
        title = itemView.findViewById(R.id.title)
    }


    @SuppressLint("ClickableViewAccessibility")
    fun bind(movie: MovieKotlin) {
        poster.alpha = 1f
        title.visibility = View.GONE

        // isEmpty
        title.text = movie.title
        val url = BuildConfig.IMAGE_BASE_URL + movie.posterPath
        Picasso.get().load(url).into(poster)

        setListeners()

        //TODO i don't need onclicklistener yet
        //        poster.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Log.d("NATYINFO", "I'm in onClick");
        //                Toast.makeText(v.getContext(), "You've clicked " + title.getText(), Toast.LENGTH_LONG);
        //            }
        //        });

    }

    @SuppressLint("ClickableViewAccessibility")
    fun setListeners() {
        var intent: Intent? = null

        poster.setOnTouchListener(View.OnTouchListener { v, event ->
            initAnimations(v)
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    //todo: use init animation  toggleAnimation(VISIBLE, 0.2);
                    toggleAnimation(View.VISIBLE, fadeInTitle, fadeOutPoster, 0.2)
                    return@OnTouchListener false
                }
                MotionEvent.ACTION_UP -> {
                    toggleAnimation(View.INVISIBLE, fadeOutTitle, fadeInPoster, 1.0)
                    return@OnTouchListener false
                }
            }
            false
        })

        poster.setOnLongClickListener { v ->
            //            intent = Intent(
//                v.context,
//                DetailFragment::class)
            Toast.makeText(v.context, "I've been long clicked!", Toast.LENGTH_LONG).show()
            true
        }
    }

    private fun initAnimations(view: View) {
        //TODO: MANAGE ANIMATION
        fadeInTitle = FadeAnimation.getFadeAnimation(0f, 1f,
                DecelerateInterpolator(), 1000)
        fadeOutTitle = FadeAnimation.getFadeAnimation(1f, 0f,
                DecelerateInterpolator(), 1000)
        fadeInPoster = FadeAnimation.getFadeAnimation(0.2f, 1f,
                DecelerateInterpolator(), 1000)
        fadeOutPoster = FadeAnimation.getFadeAnimation(1f, 0.2f,
                AccelerateInterpolator(), 1000)

        //
        //        fadeInTitle.setAnimationListener(new Animation.AnimationListener() {
        //            @Override
        //            public void onAnimationStart(Animation animation) {
        //
        //            }
        //
        //            @Override
        //            public void onAnimationEnd(Animation animation) {
        //                view.setAnimation(fadeOutTitle);
        //            }
        //
        //            @Override
        //            public void onAnimationRepeat(Animation animation) {
        //
        //            }
        //        });
        //        view.setAnimation(fadeInTitle);

    }

    private fun toggleAnimation(visible: Int, titleAnimation: Animation?,
                                posterAnimation: Animation?, alpha: Double) {
        title.visibility = visible
        title.animation = titleAnimation
        poster.animation = posterAnimation
        poster.alpha = alpha.toFloat()
    }
}