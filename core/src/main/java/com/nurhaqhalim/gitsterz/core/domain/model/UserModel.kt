package com.nurhaqhalim.gitsterz.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var id: Int = 0,
    var avatar_url: String = "",
    var login:String = "",
    var html_url:String = "",
    var name:String = "",
    var bio:String = "",
    var company: String = "",
    var location: String = "",
    var followers: Int = 0,
    var following: Int = 0,
    var fullname: String = "",
    var url: String = "",
    var favorite: Boolean = false
) : Parcelable
