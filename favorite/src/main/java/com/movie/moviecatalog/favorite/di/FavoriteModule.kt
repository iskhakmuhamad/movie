package com.movie.moviecatalog.favorite.di

import com.movie.moviecatalog.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favModule = module {
    viewModel { FavoriteViewModel(get()) }
}