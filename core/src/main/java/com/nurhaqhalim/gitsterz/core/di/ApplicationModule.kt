package com.nurhaqhalim.gitsterz.core.di

import com.nurhaqhalim.gitsterz.core.data.Repository
import com.nurhaqhalim.gitsterz.core.data.source.local.LocalSource
import com.nurhaqhalim.gitsterz.core.data.source.remote.RemoteSource
import com.nurhaqhalim.gitsterz.core.domain.repository.UserRepository
import com.nurhaqhalim.gitsterz.core.domain.usecase.UserInteractor
import com.nurhaqhalim.gitsterz.core.domain.usecase.UserUseCase
import org.koin.dsl.module

val applicationModule = module {
    single<UserRepository> {
        com.nurhaqhalim.gitsterz.core.data.Repository(get(), get())
    }

    single {
        RemoteSource(get())
    }

    single {
        LocalSource(get())
    }

    single<UserUseCase> {
        UserInteractor(get())
    }
}