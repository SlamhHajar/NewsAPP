package com.hajarslamah.newsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel


class NewsViewModel : ViewModel() {
    val newsLiveData:LiveData<List<NewsData>>
    val newsDetailsLiveData = MutableLiveData<Int>()
    init {
        newsLiveData = NewsFetcher().fetchContents()
        Log.d("viwmodel", "Response received")
    }



}