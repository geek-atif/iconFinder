package com.squareboat.iconfinder.model

import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("containers")
    val containers: List<Container>,
    @SerializedName("icon_id")
    val icon_id: Int,
    @SerializedName("is_icon_glyph")
    val is_icon_glyph: Boolean,
    @SerializedName("is_premium")
    val is_premium: Boolean,
    @SerializedName("is_purchased")
    val is_purchased: Boolean,
    @SerializedName("prices")
    val prices: List<Price>,
    @SerializedName("published_at")
    val published_at: String,
    @SerializedName("raster_sizes")
    val raster_sizes: List<RasterSize>,
    @SerializedName("styles")
    val styles: List<Style>,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("type")
    val type: String,
    @SerializedName("vector_sizes")
    val vector_sizes: List<VectorSize>
)