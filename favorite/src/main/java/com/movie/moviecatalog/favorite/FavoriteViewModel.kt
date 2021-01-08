package com.movie.moviecatalog.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.movie.moviecatalog.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favMovies = movieUseCase.getFavMovies().asLiveData()
}