package com.nurhaqhalim.gitsterz.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("items")
    val users: List<MainModel>
)
