package com.nurhaqhalim.favoriteApp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var listFav = MutableLiveData<ArrayList<MainModel>>()

    fun setUserFavorite(context: Context) {
        val cursor = context.contentResolver.query(
            ContractDatabase.UserFavoriteColumn.Uri, null, null, null, null
        )
        val listConvert = MapHelper.mapCursortoArrayList(cursor)
        listFav.postValue(listConvert)
    }

    fun getDataUserFav(): LiveData<ArrayList<MainModel>> = listFav
}