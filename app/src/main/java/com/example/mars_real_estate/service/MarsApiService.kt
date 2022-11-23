package com.example.mars_real_estate.service

import com.example.mars_real_estate.model.MarsDataModel
import retrofit2.Call
import retrofit2.http.GET

interface MarsApiService {
    //GET, POST, UPDATE, DELETE METHODS.

    @GET("realestate")
    fun getProperties(): Call<List<MarsDataModel>>
}