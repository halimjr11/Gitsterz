package com.nurhaqhalim.gitsterz.favorite.di

import com.nurhaqhalim.gitsterz.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}