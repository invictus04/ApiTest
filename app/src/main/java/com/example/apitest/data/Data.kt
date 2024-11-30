package com.example.apitest.data

data class Breed(
    val breed: String,
    val coat: String?,
    val country: String,
    val origin: String?,
    val pattern: String?
)

data class BreedResponse(val data: List<Breed>)