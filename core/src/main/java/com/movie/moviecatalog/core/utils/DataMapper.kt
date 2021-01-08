package com.movie.moviecatalog.core.utils

import com.movie.moviecatalog.core.data.source.local.entity.MovieEntity
import com.movie.moviecatalog.core.data.source.remote.response.MovieResponse
import com.movie.moviecatalog.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                image = it.image,
                fav = false
            )
            movies.add(movie)
        }
        return movies
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                image = it.image,
                fav = it.fav
            )
        }

    fun mapDomainToEntity(md: Movie) = MovieEntity(
        movieId = md.movieId,
        title = md.title,
        overview = md.overview,
        image = md.image,
        fav = md.fav
    )
}