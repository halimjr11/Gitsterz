package com.nurhaqhalim.gitsterz.core.data.source.remote.network

import com.nurhaqhalim.gitsterz.core.BuildConfig
import com.nurhaqhalim.gitsterz.core.data.source.remote.response.MainModel
import com.nurhaqhalim.gitsterz.core.data.source.remote.response.SearchResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val keyApi = BuildConfig.API_TOKEN

interface ApiEndpoint {

    @Headers("Authorization: token $keyApi")
    @GET("users")
    suspend fun getAllData(): List<MainModel>

    @Headers("Authorization: token $keyApi")
    @GET("search/users")
    suspend fun searchUser(@Query("q") query: String) : SearchResult

    @Headers("Authorization: token $keyApi")
    @GET("users/{username}")
    suspend fun getDetail(@Path("username") username: String) : MainModel

    @Headers("Authorization: token $keyApi")
    @GET("users/{username}/repos")
    suspend fun getRepos(@Path("username") username: String) : List<MainModel>

    @Headers("Authorization: token $keyApi")
    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String) : List<MainModel>

    @Headers("Authorization: token $keyApi")
    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String) : List<MainModel>

}