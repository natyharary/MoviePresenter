package com.natyharary.moviepresenter.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieKotlin {
    @SerializedName("title")
    @Expose
    var title : String? = null

    @SerializedName("backdrop")
    @Expose
    var backdropPath : String? = null

    @SerializedName("id")
    @Expose
    var id : Int? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath : String? = null

}