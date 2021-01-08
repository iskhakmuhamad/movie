package com.movie.moviecatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("id")
    var movieId: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("poster_path")
    var image: String,
)