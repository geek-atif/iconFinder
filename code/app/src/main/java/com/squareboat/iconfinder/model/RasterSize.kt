package com.squareboat.iconfinder.model

import com.google.gson.annotations.SerializedName

data class RasterSize(
    @SerializedName("formats")
    val formats: List<Format>,
    @SerializedName("size")
    val size: Int,
    @SerializedName("size_height")
    val size_height: Int,
    @SerializedName("size_width")
    val size_width: Int
)