package com.squareboat.iconfinder.data.network

import com.squareboat.iconfinder.model.IconFinderResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Atif Qamar on 26-11-2020.
 */

interface ApiService {
    @GET("v4/icons/search")
    suspend fun getIcons(
        @Query("query") iconName: String,
        @Query("count") numberOfIcons: Int
    ): IconFinderResponse


}