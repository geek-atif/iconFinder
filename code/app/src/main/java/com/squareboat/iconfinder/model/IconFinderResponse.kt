package com.squareboat.iconfinder.model

import com.google.gson.annotations.SerializedName

data class IconFinderResponse(
    @SerializedName("icons")
    val icons: List<Icon>,
    @SerializedName("total_count")
    val total_count: Int
)