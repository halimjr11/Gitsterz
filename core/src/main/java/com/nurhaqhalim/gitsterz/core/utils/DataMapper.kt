package com.nurhaqhalim.gitsterz.core.utils

import com.nurhaqhalim.gitsterz.core.data.source.local.entity.User
import com.nurhaqhalim.gitsterz.core.data.source.remote.response.MainModel
import com.nurhaqhalim.gitsterz.core.domain.model.UserModel

object DataMapper {
    fun responseToEntity(response: MainModel?) : User =
        User(
            response?.id ?: 0,
            response?.avatar_url ?: "",
            response?.login ?: "",
            response?.html_url ?: "",
            response?.name ?: "",
            response?.bio ?: "",
            response?.company ?: "",
            response?.location ?: "",
            response?.followers ?: 0,
            response?.following ?: 0,
            response?.fullname ?: "",
            response?.url ?: "",
            FavoriteUtils.isNotFavorite
        )

    fun entityToDomain(data: List<User>) : List<UserModel> =
        data.map {
            UserModel(
                it.id,
                it.avatar_url,
                it.login,
                it.html_url,
                it.name,
                it.bio,
                it.company,
                it.location,
                it.followers,
                it.following,
                it.fullname,
                it.url,
                it.favorite
            )
        }

    fun userToDomain(response: User) : UserModel =
        UserModel(
            response.id,
            response.avatar_url,
            response.login,
            response.html_url,
            response.name,
            response.bio,
            response.company,
            response.location,
            response.followers,
            response.following,
            response.fullname,
            response.url,
            response.favorite
        )

    fun responseToDomain(data: List<MainModel>) : List<UserModel> =
        data.map {
            UserModel(
                it.id,
                it.avatar_url,
                it.login,
                it.html_url,
                it.name,
                it.bio,
                it.company,
                it.location,
                it.followers,
                it.following,
                it.fullname,
                it.url,
            )
        }
}