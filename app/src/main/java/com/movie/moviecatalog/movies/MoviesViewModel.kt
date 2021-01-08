package com.movie.moviecatalog.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.movie.moviecatalog.core.domain.usecase.MovieUseCase

class MoviesViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getMovies().asLiveData()
}