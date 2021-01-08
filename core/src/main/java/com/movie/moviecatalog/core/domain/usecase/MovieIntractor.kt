package com.movie.moviecatalog.core.domain.usecase

import com.movie.moviecatalog.core.domain.model.Movie
import com.movie.moviecatalog.core.domain.repository.InterfaceMovieRepository

class MovieIntractor(private val movieRepository: InterfaceMovieRepository) : MovieUseCase {

    override fun getMovies() = movieRepository.getMovies()

    override fun getFavMovies() = movieRepository.getFavMovies()

    override fun setFavMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavMovie(movie, state)
}