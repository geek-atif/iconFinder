package com.squareboat.iconfinder.model

import com.google.gson.annotations.SerializedName

data class FormatX(
    @SerializedName("download_url")
    val download_url: String,
    @SerializedName("format")
    val format: String
)