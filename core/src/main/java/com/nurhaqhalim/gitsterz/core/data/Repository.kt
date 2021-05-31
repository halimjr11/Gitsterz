package com.nurhaqhalim.gitsterz.core.data

import com.nurhaqhalim.gitsterz.core.data.source.local.LocalSource
import com.nurhaqhalim.gitsterz.core.data.source.remote.RemoteSource
import com.nurhaqhalim.gitsterz.core.data.source.remote.network.ApiResponse
import com.nurhaqhalim.gitsterz.core.domain.model.UserModel
import com.nurhaqhalim.gitsterz.core.domain.repository.UserRepository
import com.nurhaqhalim.gitsterz.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


class Repository constructor(
    private val local: LocalSource,
    private val remote: RemoteSource
) : UserRepository {

    override fun getAllUser(): Flow<List<UserModel>> {
        return flow{
            val data = run{ local.getAllUser().first() }

            if (data.isEmpty()) {
                when (val apiResponse = run { remote.loadAllUser().first() }) {
                    is ApiResponse.Success -> {
                        apiResponse.data.forEach {
                            val dt = DataMapper.responseToEntity(it)
                            runBlocking { local.addToDb(dt) }
                        }
                        emit(DataMapper.entityToDomain(data))
                    }
                    is ApiResponse.Error -> emit(DataMapper.entityToDomain(data))
                }
            } else {
                emit(DataMapper.entityToDomain(data))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getSearch(query: String) : Flow<List<UserModel>> {
        return flow {
            val userCheck = local.checkSearch(query)
            val user = local.getSearchList(query).first()
            if (userCheck == 0){
                when (val apiResponse = run { remote.loadSearchedUser(query).first() }) {
                    is ApiResponse.Success -> {
                        emit(DataMapper.responseToDomain(apiResponse.data))
                    }
                }
            }else{
                emit(DataMapper.entityToDomain(user))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getFavorite() : Flow<List<UserModel>> {
        return flow{
            val user = local.getFavList().first()
            emit(DataMapper.entityToDomain(user))
        }.flowOn(Dispatchers.IO)
    }

    override fun getDetail(username: String) : Flow<UserModel> {
        return flow {
            when (val apiResponse = run { remote.loadDetailUser(username).first() }) {
                is ApiResponse.Success -> {
                    val data = DataMapper.responseToEntity(apiResponse.data)
                    run { local.setDetail(data) }
                }
            }
            val user = run{ local.getDetail(username).first() }
            emit(DataMapper.userToDomain(user))
        }.flowOn(Dispatchers.IO)
    }

    override fun getRepos(username: String) : Flow<List<UserModel>>{
        return flow {
            when (val apiResponse = run { remote.loadReposUser(username).first() }) {
                is ApiResponse.Success -> {
                    val data = DataMapper.responseToDomain(apiResponse.data)
                    emit(data)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getFollower(username: String) : Flow<List<UserModel>>{
        return flow {
            when (val apiResponse = run { remote.loadFollowerUser(username).first() }) {
                is ApiResponse.Success -> {
                    val data = DataMapper.responseToDomain(apiResponse.data)
                    emit(data)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getFollowing(username: String) : Flow<List<UserModel>>{
        return flow {
            when (val apiResponse = run { remote.loadFollowingUser(username).first() }) {
                is ApiResponse.Success -> {
                    val data = DataMapper.responseToDomain(apiResponse.data)
                    emit(data)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun checkFavorite(id:Int) : Int = local.checkFav(id)

    override fun setFavorite(id: Int) = runBlocking { local.setFavorite(id) }

    override fun unSetFavorite(id: Int) = runBlocking { local.unSetFavorite(id) }

}