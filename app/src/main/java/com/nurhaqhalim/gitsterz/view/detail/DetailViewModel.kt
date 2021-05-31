package com.nurhaqhalim.gitsterz.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nurhaqhalim.gitsterz.core.domain.model.UserModel
import com.nurhaqhalim.gitsterz.core.domain.usecase.UserUseCase

class DetailViewModel constructor(private val useCase: UserUseCase) : ViewModel() {
    fun getDetail(username: String) : LiveData<UserModel> = useCase.getDetail(username).asLiveData()

    fun getReposData(username: String) : LiveData<List<UserModel>> = useCase.getRepos(username).asLiveData()

    fun getFollowersData(username: String) : LiveData<List<UserModel>> = useCase.getFollower(username).asLiveData()

    fun getFollowingData(username: String) : LiveData<List<UserModel>> = useCase.getFollowing(username).asLiveData()

    fun checkFav(id: Int) : Int = useCase.checkFavorite(id)

    fun setFavorite(id: Int) = useCase.setFavorite(id)

    fun unSetFavorite(id: Int) = useCase.unSetFavorite(id)
}