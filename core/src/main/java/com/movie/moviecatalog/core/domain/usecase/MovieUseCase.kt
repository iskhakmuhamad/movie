package com.movie.moviecatalog.core.domain.usecase

import com.movie.moviecatalog.core.data.Resource
import com.movie.moviecatalog.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getFavMovies(): Flow<List<Movie>>
    fun setFavMovie(movie: Movie, state: Boolean)
}