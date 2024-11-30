package com.example.apitest.service

import com.example.apitest.data.BreedResponse
import com.example.apitest.data.CatsFacts
import com.example.apitest.data.FactsRespose
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("fact")
    suspend fun getFacts(): CatsFacts

    @GET("facts")
    suspend fun getAllFacts(): FactsRespose

    @GET("breeds")
    suspend fun getBreeds(): BreedResponse
}

private val retrofit = Retrofit.Builder().baseUrl("https://catfact.ninja/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val catService = retrofit.create(ApiService::class.java)