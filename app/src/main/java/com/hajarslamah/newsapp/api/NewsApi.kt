package com.hajarslamah.newsapp.api


import com.hajarslamah.newsapp.NewsData
import com.hajarslamah.newsapp.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET("newsApi.php")
    fun fetchContents(): Call<NewsResponse>
}