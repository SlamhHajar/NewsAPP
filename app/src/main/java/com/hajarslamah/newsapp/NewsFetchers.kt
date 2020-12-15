package com.hajarslamah.newsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hajarslamah.newsapp.api.NewsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val TAG = "newFetcher"
class NewsFetchers {
    private val newApi: NewsApi

    init {
        val retrofit: Retrofit = Retrofit.
        Builder()
            .baseUrl("https://192.168.1.1/News_Challange/api/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
        newApi = retrofit.create(NewsApi::class.java)
    }
    fun fetchContents(): LiveData<List<NewsData>> {
        val responseLiveData: MutableLiveData<List<NewsData>> = MutableLiveData()
        val newRequst: Call<NewsResponse> = newApi.fetchContents()
        newRequst.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch news", t)            }
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                Log.d(TAG, "Response received ${response.code().toString()} ")
                val newshResponse: NewsResponse? = response.body()
                var newsItems: List<NewsData> = newshResponse?.newItems
                    ?: mutableListOf()
                responseLiveData.value = newsItems
            }
        })
        return responseLiveData}

}
