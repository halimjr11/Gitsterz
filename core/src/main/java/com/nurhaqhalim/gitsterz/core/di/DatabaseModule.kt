package com.nurhaqhalim.gitsterz.core.di

import androidx.room.Room
import com.nurhaqhalim.gitsterz.core.data.source.local.database.DatabaseGitsterz
import com.nurhaqhalim.gitsterz.core.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), DatabaseGitsterz::class.java, Constants.dbName)
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<DatabaseGitsterz>().UserDao()
    }
}