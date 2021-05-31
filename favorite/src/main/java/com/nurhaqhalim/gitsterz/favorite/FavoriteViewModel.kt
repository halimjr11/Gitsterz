package com.nurhaqhalim.gitsterz.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nurhaqhalim.gitsterz.core.domain.model.UserModel
import com.nurhaqhalim.gitsterz.core.domain.usecase.UserUseCase

class FavoriteViewModel constructor(private val useCase: UserUseCase) : ViewModel() {
    fun getFavoriteList(): LiveData<List<UserModel>> = useCase.getFavorite().asLiveData()
}