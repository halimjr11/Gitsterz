package com.nurhaqhalim.gitsterz.di

import com.nurhaqhalim.gitsterz.view.detail.DetailViewModel
import com.nurhaqhalim.gitsterz.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}