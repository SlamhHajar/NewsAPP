package com.hajarslamah.newsapp.api


import com.hajarslamah.newsapp.Details
import com.hajarslamah.newsapp.NewsData
import com.hajarslamah.newsapp.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/News_Challange/api/newsApi.php")
    fun fetchContents(): Call<NewsResponse>
    @GET("/API/api/api_news.php?")
    fun fetchNew(@Query("ID") id: Int): Call<Details>
}