package com.hajarslamah.newsapp.api

import retrofit2.Call
import retrofit2.http.GET

interface Category {
    @GET("category.php")
    fun fetchContents(): Call<List<Category>>
}