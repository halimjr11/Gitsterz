package com.nurhaqhalim.gitsterz.core.data.source.remote

import com.nurhaqhalim.gitsterz.core.data.source.remote.network.ApiEndpoint
import com.nurhaqhalim.gitsterz.core.data.source.remote.network.ApiResponse
import com.nurhaqhalim.gitsterz.core.data.source.remote.response.MainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.lang.Exception

class RemoteSource constructor(private val api: ApiEndpoint) {

    suspend fun loadAllUser() : Flow<ApiResponse<List<MainModel>>> {
        return flow {
            try {
                val response = api.getAllData()
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loadSearchedUser(username: String) : Flow<ApiResponse<List<MainModel>>>{
        return flow {
            try {
                val response = api.searchUser(username).users
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }
    }

    suspend fun loadDetailUser(username: String) : Flow<ApiResponse<MainModel>>{
        return flow {
            try {
                val response = api.getDetail(username)
                if (response != null){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }
    }

    suspend fun loadReposUser(username: String) : Flow<ApiResponse<List<MainModel>>>{
        return flow {
            try {
                val response = api.getRepos(username)
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loadFollowingUser(username: String) : Flow<ApiResponse<List<MainModel>>>{
        return flow {
            try {
                val response = api.getFollowing(username)
                println("remote flowing : $response")

                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loadFollowerUser(username: String) : Flow<ApiResponse<List<MainModel>>>{
        return flow {
            try {
                val response = api.getFollowers(username)
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)
    }

}