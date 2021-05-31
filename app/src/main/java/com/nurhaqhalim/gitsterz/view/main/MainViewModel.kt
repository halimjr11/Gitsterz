package com.nurhaqhalim.gitsterz.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nurhaqhalim.gitsterz.core.domain.model.UserModel
import com.nurhaqhalim.gitsterz.core.domain.usecase.UserUseCase

class MainViewModel constructor(private val useCase: UserUseCase): ViewModel() {
    fun getAllData() : LiveData<List<UserModel>> = useCase.getAllUser().asLiveData()

    fun getSearchQuery(query: String): LiveData<List<UserModel>> = useCase.getSearch(query).asLiveData()
}