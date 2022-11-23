package com.example.mars_real_estate.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarsApi {
    private const val BASE_URL = "https://mars.udacity.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}