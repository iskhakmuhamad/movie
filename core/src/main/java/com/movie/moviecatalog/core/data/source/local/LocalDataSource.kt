package com.movie.moviecatalog.core.data.source.local

import com.movie.moviecatalog.core.data.source.local.entity.MovieEntity
import com.movie.moviecatalog.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()

    fun getFavMovies(): Flow<List<MovieEntity>> = movieDao.getFavMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) =
        movieDao.insertMovies(movies)

    fun setFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.fav = newState
        movieDao.updateFavMovie(movie)
    }
}