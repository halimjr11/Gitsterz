package com.nurhaqhalim.gitsterz.core.data.source.local.database

import androidx.room.*
import com.nurhaqhalim.gitsterz.core.data.source.local.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToDb(user: User)

    @Query("UPDATE user_table SET favorite = 1 WHERE id = :id")
    suspend fun setFavorite(id: Int)

    @Query("UPDATE user_table SET favorite = 0 WHERE id = :id")
    suspend fun unSetFavorite(id: Int)

    @Query("SELECT * FROM user_table WHERE favorite = 1 ORDER BY login ASC")
    fun getFavData() : Flow<List<User>>

    @Query("SELECT count(*) FROM user_table WHERE favorite = 1 AND id = :id")
    suspend fun checkFavorite(id: Int): Int

    @Query("SELECT count(*) FROM user_table WHERE id = :id")
    suspend fun checkUser(id: Int) : Int

    @Query("SELECT * FROM user_table")
    fun getAllList(): Flow<List<User>>

    @Query("SELECT count(*) FROM user_table WHERE login LIKE :query")
    suspend fun searchCheck(query: String) : Int

    @Query("SELECT * FROM user_table WHERE login LIKE :query")
    fun fetchSearchData(query: String) : Flow<List<User>>

    @Query("SELECT * FROM user_table WHERE login = :username")
    fun getDetail(username: String) : Flow<User>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setDetail(data: User)
}