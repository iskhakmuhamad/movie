package com.movie.moviecatalog.core.domain.repository

import com.movie.moviecatalog.core.data.Resource
import com.movie.moviecatalog.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface InterfaceMovieRepository {
    fun getMovies(): Flow<Resource<List<Movie>>>

    fun getFavMovies(): Flow<List<Movie>>

    fun setFavMovie(movie: Movie, newState: Boolean)
}