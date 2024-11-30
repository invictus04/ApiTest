package com.example.apitest.service

import com.example.apitest.data.Age
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AgeApiService {
    @GET("/")
    suspend fun getAge(@Query("name") name: String): Age
}

val retrofit1 = Retrofit.Builder().baseUrl("https://api.agify.io/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val ageService = retrofit1.create(AgeApiService::class.java)