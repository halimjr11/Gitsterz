package com.nurhaqhalim.favoriteApp

import android.provider.BaseColumns

object ContractDatabase {
    const val Authority = "com.nurhaqhalim.gitsterz"
    const val Schema = "content"

    internal class  UserFavoriteColumn: BaseColumns{
        companion object{
            private const val tableName = "favorite_table"
            const val Id = "id"
            const val Username = "login"
            const val Avatar = "avatar_url"

            val Uri = android.net.Uri.Builder().scheme(Schema)
                .authority(Authority)
                .appendPath(tableName)
                .build()
        }
    }
}