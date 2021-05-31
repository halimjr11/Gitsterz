package com.nurhaqhalim.gitsterz.core.data.source.local

import com.nurhaqhalim.gitsterz.core.data.source.local.database.UserDao
import com.nurhaqhalim.gitsterz.core.data.source.local.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class LocalSource constructor(private val dao: UserDao) {
    suspend fun getAllUser(): Flow<List<User>> =
        withContext(Dispatchers.IO){
            dao.getAllList()
        }

    suspend fun getFavList() : Flow<List<User>> =
        withContext(Dispatchers.IO){
            dao.getFavData()
        }

    suspend fun getSearchList(query: String) : Flow<List<User>> =
        withContext(Dispatchers.IO){
            dao.fetchSearchData(query)
        }

    suspend fun getDetail(user: String) : Flow<User> =
        withContext(Dispatchers.IO){
            dao.getDetail(user)
        }

    suspend fun addToDb(user: User) =
        withContext(Dispatchers.IO){
            dao.addToDb(user)
        }

    fun checkFav(id:Int) : Int = runBlocking {
        val data = async {
            dao.checkFavorite(id)
        }
        data.start()
        data.await()
    }

    fun checkUser(id:Int) : Int = runBlocking {
        val data = async {
            dao.checkUser(id)
        }
        data.start()
        data.await()
    }

    fun checkSearch(query:String) : Int = runBlocking {
        val data = async {
            dao.searchCheck(query)
        }
        data.start()
        data.await()
    }

    suspend fun setFavorite(id: Int) =
        withContext(Dispatchers.IO){
            dao.setFavorite(id)
        }

    suspend fun unSetFavorite(id: Int) =
        withContext(Dispatchers.IO){
            dao.unSetFavorite(id)
        }

    suspend fun setDetail(data : User) =
        withContext(Dispatchers.IO){
            dao.setDetail(data)
        }
}