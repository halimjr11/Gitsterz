package com.nurhaqhalim.gitsterz.core.domain.repository

import com.nurhaqhalim.gitsterz.core.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUser(): Flow<List<UserModel>>

    fun getSearch(query: String) : Flow<List<UserModel>>

    fun getFavorite() : Flow<List<UserModel>>

    fun getDetail(username: String) : Flow<UserModel>

    fun getRepos(username: String) : Flow<List<UserModel>>

    fun getFollower(username: String) : Flow<List<UserModel>>

    fun getFollowing(username: String) : Flow<List<UserModel>>

    fun checkFavorite(id:Int) : Int

    fun setFavorite(id: Int)

    fun unSetFavorite(id: Int)
}