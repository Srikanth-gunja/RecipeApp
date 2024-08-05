package com.srikanth.recipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



private val _retrofit=Retrofit.Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService= _retrofit.create(ApiInterface::class.java)

interface ApiInterface{
    @GET("categories.php")
    suspend fun getCategories():CategoryList
}