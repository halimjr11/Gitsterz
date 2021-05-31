package com.nurhaqhalim.favoriteApp

import android.database.Cursor

object MapHelper {

    fun mapCursortoArrayList(cursor: Cursor?) : ArrayList<MainModel>{
        val list = ArrayList<MainModel>()
        if (cursor!=null){
            while (cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ContractDatabase.UserFavoriteColumn.Id))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(ContractDatabase.UserFavoriteColumn.Username))
                val avatar = cursor.getString(cursor.getColumnIndexOrThrow(ContractDatabase.UserFavoriteColumn.Avatar))
                list.add(
                    MainModel(
                        id,
                        avatar,
                        username
                    )
                )
            }
        }
        return list
    }
}