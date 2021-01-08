package com.movie.moviecatalog.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "image")
    var image: String,
    @ColumnInfo(name = "fav")
    var fav: Boolean
)
