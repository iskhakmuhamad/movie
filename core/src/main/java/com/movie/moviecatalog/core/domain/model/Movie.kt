package com.movie.moviecatalog.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    var movieId: Int,

    var title: String,

    var overview: String,

    var image: String,

    var fav: Boolean
) : Parcelable