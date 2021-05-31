package com.nurhaqhalim.gitsterz.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nurhaqhalim.gitsterz.core.data.source.local.entity.User

@Database(entities = [User::class], version = 1)
abstract class DatabaseGitsterz : RoomDatabase() {
    abstract fun UserDao() : UserDao
}