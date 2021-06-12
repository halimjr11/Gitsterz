package com.nurhaqhalim.gitsterz.core.di

import com.nurhaqhalim.gitsterz.core.data.source.remote.network.ApiEndpoint
import com.nurhaqhalim.gitsterz.core.utils.Constants
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val certificatePinner = CertificatePinner.Builder()
            .add(Constants.hostName, Constants.sha256Key)
            .add(Constants.hostName, "sha256/azE5Ew0LGsMgkYqiDpYay0olLAS8cxxNGUZ8OJU756k=")
            .add(Constants.hostName, "sha256/vnCogm4QYze/Bc9r88xdA6NTQY74p4BAz2w5gxkLG2M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(150, TimeUnit.SECONDS)
            .readTimeout(150, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single {
        get<Retrofit>().create(ApiEndpoint::class.java)
    }
}