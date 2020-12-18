package com.hajarslamah.newsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class NewsViewModel : ViewModel() {
    val newsLiveData:LiveData<List<NewsData>>
    init {
        newsLiveData = NewsFetchers().fetchContents();
        Log.d("viwmodel", "Response received  ")
    }
}