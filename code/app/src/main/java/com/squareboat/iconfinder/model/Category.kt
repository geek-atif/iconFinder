package com.squareboat.iconfinder.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("name")
    val name: String
)