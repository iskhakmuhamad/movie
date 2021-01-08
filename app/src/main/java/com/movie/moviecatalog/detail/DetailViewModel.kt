package com.movie.moviecatalog.detail

import androidx.lifecycle.ViewModel
import com.movie.moviecatalog.core.domain.model.Movie
import com.movie.moviecatalog.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavMovie(movie, newStatus)
}