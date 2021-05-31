package com.nurhaqhalim.gitsterz.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    var id: Int = 0,
    var avatar_url: String = "",
    var login:String = "",
    var html_url:String = "",
    var name:String = "",
    var bio:String = "bio",
    var company: String = "",
    var location: String = "",
    var followers: Int = 0,
    var following: Int = 0,
    var fullname: String = "",
    var url: String = "",
    var favorite: Boolean = false
)
