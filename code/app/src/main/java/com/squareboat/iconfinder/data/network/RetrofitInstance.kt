package com.squareboat.iconfinder.data.network

import com.google.gson.GsonBuilder
import com.squareboat.iconfinder.constants.ConstantsApp.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Atif Qamar on 26-11-2020.
 */
object RetrofitInstance {
    val appService: ApiService by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(ApiService::class.java)
    }
}

