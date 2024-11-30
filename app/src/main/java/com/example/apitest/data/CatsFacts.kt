package com.example.apitest.data

data class CatsFacts(
    val fact: String,
    val length: Int
)

data class FactsRespose(
    val data: List<CatsFacts>
)