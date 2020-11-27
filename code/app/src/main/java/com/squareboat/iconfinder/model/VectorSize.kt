package com.squareboat.iconfinder.model

import com.google.gson.annotations.SerializedName

data class VectorSize(
    @SerializedName("formats")
    val formats: List<FormatX>,
    @SerializedName("size")
    val size: Int,
    @SerializedName("size_height")
    val size_height: Int,
    @SerializedName("size_width")
    val size_width: Int,
    @SerializedName("target_sizes")
    val target_sizes: List<List<Int>>
)