package com.hajarslamah.newsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class NewDetailViewModel : ViewModel() {
  //  val newsDetailLiveData: LiveData<List<NewsData>>
    val newsDetailsLiveData = MutableLiveData<Int>()
    var newsListDetail: LiveData<List<NewsData>> =
        Transformations.switchMap(newsDetailsLiveData) { id ->
            NewsFetcher().fetchNew(id)
        }

    fun loadNewsDetails(id: Int) {
        newsDetailsLiveData.value = id
    }

}