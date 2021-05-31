package com.nurhaqhalim.gitsterz.core.domain.usecase

import com.nurhaqhalim.gitsterz.core.domain.model.UserModel
import com.nurhaqhalim.gitsterz.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor constructor(private val repo: UserRepository) : UserUseCase {
    override fun getAllUser(): Flow<List<UserModel>> = repo.getAllUser()

    override fun getSearch(query: String): Flow<List<UserModel>> = repo.getSearch(query)

    override fun getFavorite(): Flow<List<UserModel>> = repo.getFavorite()

    override fun getDetail(username: String): Flow<UserModel> = repo.getDetail(username)

    override fun getRepos(username: String): Flow<List<UserModel>> = repo.getRepos(username)

    override fun getFollower(username: String): Flow<List<UserModel>> = repo.getFollower(username)

    override fun getFollowing(username: String): Flow<List<UserModel>> = repo.getFollowing(username)

    override fun checkFavorite(id: Int): Int = repo.checkFavorite(id)

    override fun setFavorite(id: Int) = repo.setFavorite(id)

    override fun unSetFavorite(id: Int) = repo.unSetFavorite(id)
}