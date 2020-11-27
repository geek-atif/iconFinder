package com.squareboat.iconfinder.model

import com.google.gson.annotations.SerializedName

data class License(
    @SerializedName("license_id")
    val license_id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("url")
    val url: String
)