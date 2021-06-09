package com.nurhaqhalim.gitsterz.core.di

import androidx.room.Room
import com.nurhaqhalim.gitsterz.core.data.source.local.database.DatabaseGitsterz
import com.nurhaqhalim.gitsterz.core.utils.Constants
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        val passPhrase : ByteArray = SQLiteDatabase.getBytes(Constants.passPhrase.toCharArray())
        val factory = SupportFactory(passPhrase)
        Room.databaseBuilder(androidContext(), DatabaseGitsterz::class.java, Constants.dbName)
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
    single {
        get<DatabaseGitsterz>().UserDao()
    }
}