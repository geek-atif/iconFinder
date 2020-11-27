package com.squareboat.iconfinder.model

import com.google.gson.annotations.SerializedName

data class Format(
    @SerializedName("download_url")
    val download_url: String,
    @SerializedName("format")
    val format: String,
    @SerializedName("preview_url")
    val preview_url: String
)