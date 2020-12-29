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
class NewsFetcher {
    private val newApi: NewsApi

    init {
        val retrofit: Retrofit = Retrofit.
        Builder()
            .baseUrl("http://192.168.191.1")
            .addConverterFactory(
                GsonConverterFactory.create()            ).build()
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
                Log.d(TAG, "Response data ${newsItems} ")
            }
        })
        return responseLiveData}
    fun fetchNew(id:Int): LiveData<List<NewsData>> {
        val responseLiveData: MutableLiveData<List<NewsData>> = MutableLiveData()
        val NewsRequest: Call<Details> = newApi.fetchNew(id)

        NewsRequest.enqueue(object : Callback<Details> {

            override fun onFailure(call: Call<Details>, t: Throwable) {
                Log.e("fetchDetails", "Failed to fetch  news Details",t)
            }

            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                Log.d("fetchDetails", "Details Response received")
                val detailsNewsResponse: Details? = response.body()
                var newsItems: List<NewsData>? = detailsNewsResponse?.newDetail

                responseLiveData.value = newsItems
            }
        })

        return responseLiveData
    }

}