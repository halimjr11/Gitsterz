@file:Suppress("unused")

package com.nurhaqhalim.gitsterz

import android.app.Application
import com.nurhaqhalim.gitsterz.core.di.applicationModule
import com.nurhaqhalim.gitsterz.core.di.databaseModule
import com.nurhaqhalim.gitsterz.core.di.networkModule
import com.nurhaqhalim.gitsterz.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GitsterzApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@GitsterzApplication)
            modules(
                applicationModule,
                networkModule,
                databaseModule,
                viewModelModule
            )
        }
    }
}