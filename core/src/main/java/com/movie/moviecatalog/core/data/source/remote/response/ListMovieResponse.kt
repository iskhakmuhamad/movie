package com.movie.moviecatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("results")
    val result: List<MovieResponse>
)