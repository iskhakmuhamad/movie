package com.movie.moviecatalog.di

import com.movie.moviecatalog.core.domain.usecase.MovieIntractor
import com.movie.moviecatalog.core.domain.usecase.MovieUseCase
import com.movie.moviecatalog.detail.DetailViewModel
import com.movie.moviecatalog.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieIntractor(get()) }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}